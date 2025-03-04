package com.srmzhk.bootick.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteStopDto {
    int id;
    String station;
    LocalDateTime arrivalTime;
    int position;
    int trainId;
}
