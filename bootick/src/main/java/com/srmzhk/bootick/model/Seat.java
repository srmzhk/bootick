package com.srmzhk.bootick.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "seats")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "train")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    int id;

    int seatNumber;

    @ManyToOne
    @JoinColumn(name = "train_id")
    Train train;

}
