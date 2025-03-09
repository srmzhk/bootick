package com.srmzhk.bootick.service.impl;

import com.srmzhk.bootick.dto.SeatDto;
import com.srmzhk.bootick.dto.TrainDto;
import com.srmzhk.bootick.exception.ItemNotFoundException;
import com.srmzhk.bootick.model.Seat;
import com.srmzhk.bootick.model.Train;
import com.srmzhk.bootick.repository.SeatRepository;
import com.srmzhk.bootick.service.ISeatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService implements ISeatService {

    private final SeatRepository seatRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<SeatDto> addSeatsForTrain(TrainDto trainDto) {
        List<Seat> seats = new ArrayList<>();
        Train train = modelMapper.map(trainDto, Train.class);
        for (int i = 1; i <= trainDto.getSeatsAmount(); i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            seat.setTrain(train);
            seats.add(seat);
        }

        return seatRepository.saveAll(seats)
                .stream()
                .map(seat -> modelMapper.map(seat, SeatDto.class))
                .toList();
    }

    @Override
    public SeatDto updateSeat(SeatDto seatDto) {
        Seat seat = seatRepository.findById(seatDto.getId())
                .orElseThrow(ItemNotFoundException::new);

        modelMapper.map(seatDto, seat);

        Seat updatedSeat = seatRepository.save(seat);
        return modelMapper.map(updatedSeat, SeatDto.class);
    }

    @Override
    public SeatDto getSeatById(int id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);

        return modelMapper.map(seat, SeatDto.class);
    }

    @Override
    public List<SeatDto> getAvailableSeatsForSearch(Train train, int fromPosition, int toPosition) {
         return seatRepository.findAvailableSeats(train, fromPosition, toPosition)
                .stream()
                .map(seat -> modelMapper.map(seat, SeatDto.class))
                .toList();
    }
}
