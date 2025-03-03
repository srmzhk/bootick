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
    int bookingId;

    String phoneNumber;

    LocalDateTime bookingTime;

    @ManyToOne
    @JoinColumn(name = "train_id")
    Train train;

    @ManyToOne
    @JoinColumn(name = "route_id")
    Route route;

    @ManyToOne
    @JoinColumn(name = "from_stop_id")
    RouteStop fromStop;

    @ManyToOne
    @JoinColumn(name = "to_stop_id")
    RouteStop toStop;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    Seat seat;

}
