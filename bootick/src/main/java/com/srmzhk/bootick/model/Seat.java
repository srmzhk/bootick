package com.srmzhk.bootick.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "seats")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"train", "bookings"})
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_id")
    Train train;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Booking> bookings;

}
