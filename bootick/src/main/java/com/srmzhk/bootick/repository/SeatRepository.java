package com.srmzhk.bootick.repository;

import com.srmzhk.bootick.model.Seat;
import com.srmzhk.bootick.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    @Query("""
    SELECT s
    FROM Seat s
    WHERE s.train = :train AND s.id NOT IN (
        SELECT b.seat.id
        FROM Booking b
        WHERE b.train = :train
        AND (
            (:fromStopPosition < b.toStop.position AND :toStopPosition > b.fromStop.position)
        )
    )
""")
    List<Seat> findAvailableSeats(Train train, int fromStopPosition, int toStopPosition);

}
