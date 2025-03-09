package com.srmzhk.bootick.service.impl;

import com.srmzhk.bootick.dto.*;
import com.srmzhk.bootick.exception.ItemAlreadyExistException;
import com.srmzhk.bootick.exception.ItemNotFoundException;
import com.srmzhk.bootick.model.RouteStop;
import com.srmzhk.bootick.model.Seat;
import com.srmzhk.bootick.model.Train;
import com.srmzhk.bootick.repository.TrainRepository;
import com.srmzhk.bootick.service.ITrainService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainService implements ITrainService {

    private final TrainRepository trainRepository;
    private final ModelMapper modelMapper;
    private final SeatService seatService;
    private final RouteStopService routeStopService;

    @Override
    public TrainDto addTrain(TrainDto trainDto) {
        Optional<Train> existedTrain = trainRepository.findByNumber(trainDto.getNumber());
        if (existedTrain.isPresent())
            throw new ItemAlreadyExistException();

        Train train = modelMapper.map(trainDto, Train.class);
        train = trainRepository.save(train);

        TrainDto trainResponse = modelMapper.map(train, TrainDto.class);
        List<SeatDto> seats = seatService.addSeatsForTrain(trainResponse);
        trainResponse.setSeats(seats);

        return trainResponse;
    }

    @Override
    public void deleteTrain(int trainId) {
        trainRepository.findById(trainId)
                .orElseThrow(ItemNotFoundException::new);

        trainRepository.deleteById(trainId);
    }

    @Override
    public TrainDto updateTrain(TrainDto trainDto) {
        Train train = trainRepository.findById(trainDto.getId())
                .orElseThrow(ItemNotFoundException::new);

        modelMapper.map(trainDto, train);

        Train updatedTrain = trainRepository.save(train);
        return modelMapper.map(updatedTrain, TrainDto.class);
    }

    @Override
    public List<SearchTrainDto> getTrainsForSearch(SearchDto searchData) {
        List<Train> trains = trainRepository
                .findTrainsBetweenStationsOnDate(searchData.getFromStop(), searchData.getToStop(), searchData.getDate());

        if (trains.isEmpty())
            throw new ItemNotFoundException();

        return initSearchTrainDto(trains, searchData);
    }

    @Override
    public List<TrainDto> getAllTrains() {
        return trainRepository
                .findAll()
                .stream()
                .map(train -> modelMapper.map(train, TrainDto.class))
                .toList();
    }

    @Override
    public TrainDto getTrainById(int id) {
        Train train = trainRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);

        return modelMapper.map(train, TrainDto.class);
    }

    private List<SearchTrainDto> initSearchTrainDto(List<Train> trains, SearchDto searchData) {
        List<SearchTrainDto> searchTrains = new ArrayList<>();
        for (Train train : trains) {
            SearchTrainDto searchTrainDto = new SearchTrainDto();
            RouteStopDto fromStop = routeStopService
                    .getRouteStopForStation(train.getStops(), searchData.getFromStop());
            LocalDateTime dateTimeFrom = fromStop.getDate().atTime(fromStop.getTime());

            RouteStopDto toStop = routeStopService
                    .getRouteStopForStation(train.getStops(), searchData.getToStop());
            LocalDateTime dateTimeTo = toStop.getDate().atTime(toStop.getTime());

            long travelTime = Duration.between(dateTimeFrom, dateTimeTo).toMinutes();

            int availableSeats = seatService
                    .getAvailableSeatsForSearch(train, fromStop.getPosition(), toStop.getPosition())
                    .size();

            searchTrainDto.setTrainId(train.getId());
            searchTrainDto.setPrice(train.getPrice());
            searchTrainDto.setNumber(train.getNumber());
            searchTrainDto.setFromStop(fromStop);
            searchTrainDto.setToStop(toStop);
            searchTrainDto.setAvailableSeats(availableSeats);
            searchTrainDto.setTravelTime(travelTime);

            searchTrains.add(searchTrainDto);
        }
        return searchTrains;
    }
}
