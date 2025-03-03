package com.srmzhk.bootick.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "stops")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int stopId;

    String station;

    LocalDateTime arrivalTime;

    LocalDateTime departureTime;

    @ManyToOne
    @JoinColumn(name = "route_id")
    Route route;

}
