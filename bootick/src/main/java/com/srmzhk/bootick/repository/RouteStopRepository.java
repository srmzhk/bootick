package com.srmzhk.bootick.repository;

import com.srmzhk.bootick.dto.RouteStopDto;
import com.srmzhk.bootick.model.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RouteStopRepository extends JpaRepository<RouteStop, Integer> {

    @Query("""
    SELECT rs FROM RouteStop rs
    WHERE rs.train.id = :trainId
    ORDER BY rs.position
""")
    List<RouteStop> findAllByTrainId(@Param("trainId") int trainId);

    Optional<RouteStop> findByStationAndTrainIdAndArrivalTime(String station, int train_id, LocalDateTime arrivalTime);
}
