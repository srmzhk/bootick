package com.srmzhk.bootick.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTrainDto {
    int trainId;
    String number;
    RouteStopDto fromStop;
    RouteStopDto toStop;
    long travelTime;
    int availableSeats;
    double price;
}
