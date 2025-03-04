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
    @Column(name = "stop_id")
    int id;

    String station;

    int position;

    LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "train_id")
    Train train;
}
