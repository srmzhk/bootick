package com.srmzhk.bootick.service.impl;

import com.srmzhk.bootick.dto.SearchDto;
import com.srmzhk.bootick.dto.TrainDto;
import com.srmzhk.bootick.exception.ItemAlreadyExistException;
import com.srmzhk.bootick.exception.ItemNotFoundException;
import com.srmzhk.bootick.model.Train;
import com.srmzhk.bootick.repository.TrainRepository;
import com.srmzhk.bootick.service.ITrainService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainService implements ITrainService {

    private final TrainRepository trainRepository;
    private final ModelMapper modelMapper;

    @Override
    public TrainDto addTrain(TrainDto trainDto) {
        Optional<Train> existedTrain = trainRepository.findByNumber(trainDto.getNumber());
        if (existedTrain.isPresent())
            throw new ItemAlreadyExistException();

        Train train = modelMapper.map(trainDto, Train.class);
        train = trainRepository.save(train);
        return modelMapper.map(train, TrainDto.class);
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
    public List<TrainDto> getTrainsForSearch(SearchDto searchData) {

        List<TrainDto> trains =  trainRepository
                .findTrainsBetweenStationsOnDate(searchData.getFromStop(), searchData.getToStop(), searchData.getDate())
                .stream()
                .map(train -> modelMapper.map(train, TrainDto.class))
                .toList();

        if (trains.isEmpty())
            throw new ItemNotFoundException();

        return trains;
    }

    @Override
    public List<TrainDto> getAllTrains() {
        return trainRepository
                .findAll()
                .stream()
                .map(train -> modelMapper.map(train, TrainDto.class))
                .toList();
    }
}
