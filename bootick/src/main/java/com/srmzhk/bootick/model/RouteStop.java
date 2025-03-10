package com.srmzhk.bootick.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "stops")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"train", "bookings"})
public class RouteStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stop_id")
    int id;

    String station;

    int position;

    LocalDate date;

    LocalTime time;

    @ManyToOne
    @JoinColumn(name = "train_id")
    Train train;

    @OneToMany(mappedBy = "toStop", cascade = CascadeType.REMOVE)
    List<Booking> bookings;
}
