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
        for (int i = 1; i <= trainDto.getSeatsAmount(); i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            seat.setTrain(modelMapper.map(trainDto, Train.class));
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
}
