package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.SeatDto;
import com.srmzhk.bootick.dto.TrainDto;

import java.util.List;

public interface ISeatService {

    List<SeatDto> addSeatsForTrain(TrainDto trainDto);

    SeatDto updateSeat(SeatDto seatDto);
}
