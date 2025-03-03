package com.srmzhk.bootick.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDto {
    int trainId;
    String number;
    double price;
    int seatsAmount;
    RouteDto route;
}
