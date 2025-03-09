package com.srmzhk.bootick.dto;

import com.srmzhk.bootick.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDto {
    int id;
    String number;
    double price;
    int seatsAmount;
    List<RouteStopDto> stops;
    List<SeatDto> seats;
}
