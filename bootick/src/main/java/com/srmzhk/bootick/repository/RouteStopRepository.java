package com.srmzhk.bootick.repository;

import com.srmzhk.bootick.dto.RouteStopDto;
import com.srmzhk.bootick.model.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Query("SELECT DISTINCT r.station FROM RouteStop r WHERE r.station LIKE :symbols%")
    List<String> findDistinctStationsByStartingWith(@Param("symbols") String symbols);

    List<RouteStop> findByTrainIdAndPosition(int trainId, int position);

    Optional<RouteStop> findByStationAndTrainIdAndDateAndTime(String station, int trainId, LocalDate date, LocalTime time);

    Optional<RouteStop> findByTrainIdAndStation(int trainId, String station);
}
