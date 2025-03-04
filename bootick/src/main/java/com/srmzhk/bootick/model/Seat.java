package com.srmzhk.bootick.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    int id;

    int seatNumber;

    boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "train_id")
    Train train;

}
