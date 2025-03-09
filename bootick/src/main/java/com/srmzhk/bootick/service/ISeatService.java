package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.SeatDto;
import com.srmzhk.bootick.dto.TrainDto;
import com.srmzhk.bootick.model.Train;

import java.util.List;

public interface ISeatService {

    List<SeatDto> addSeatsForTrain(TrainDto trainDto);

    SeatDto updateSeat(SeatDto seatDto);

    SeatDto getSeatById(int id);

    List<SeatDto> getAvailableSeatsForSearch(Train train, int fromPosition, int toPosition);
}
