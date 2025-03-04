package com.srmzhk.bootick.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    int id;
    String phoneNumber;
    LocalDateTime bookingTime;
    TrainDto train;
    RouteStopDto fromStop;
    RouteStopDto toStop;
    SeatDto seat;
}
