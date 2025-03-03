package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.BookingDto;
import com.srmzhk.bootick.dto.RouteDto;
import com.srmzhk.bootick.dto.SeatDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISeatService {

    List<SeatDto> addSeatsForTrain(int amount);

    List<SeatDto> getAvailableSeatsForRoute(RouteDto route);

    SeatDto updateSeat(BookingDto bookingData);

}
