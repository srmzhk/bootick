package com.srmzhk.bootick.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    int id;

    String phone;

    LocalDateTime bookingTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_id")
    Train train;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_stop_id")
    RouteStop fromStop;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_stop_id")
    RouteStop toStop;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_id")
    Seat seat;

}
