package com.srmzhk.bootick.repository;

import com.srmzhk.bootick.dto.BookingDto;
import com.srmzhk.bootick.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("""
    SELECT b FROM Booking b
    WHERE b.train.id = :trainId
      AND b.seat.id = :seatId
      AND (
          (b.fromStop.position < :toPosition AND b.toStop.position > :fromPosition)
      )
""")
    Optional<Booking> findByBookingData(
            @Param("trainId") int trainId,
            @Param("seatId") int seatId,
            @Param("fromPosition") int fromPosition,
            @Param("toPosition") int toPosition
    );

    List<Booking> findByPhone(String phone);

    List<Booking> findByTrainId(int trainId);

    List<Booking> findBySeatId(int seatId);

    Optional<Booking> findByIdAndPhone(int id, String phone);
}
