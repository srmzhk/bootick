package com.srmzhk.bootick.service.impl;

import com.srmzhk.bootick.dto.BookingDto;
import com.srmzhk.bootick.dto.RouteDto;
import com.srmzhk.bootick.dto.SeatDto;
import com.srmzhk.bootick.repository.SeatRepository;
import com.srmzhk.bootick.service.ISeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService implements ISeatService {

    private final SeatRepository seatRepository;

    @Override
    public List<SeatDto> addSeatsForTrain(int amount) {
        return List.of();
    }

    @Override
    public List<SeatDto> getAvailableSeatsForRoute(RouteDto route) {
        return List.of();
    }

    @Override
    public SeatDto updateSeat(BookingDto bookingData) {
        return null;
    }
}
