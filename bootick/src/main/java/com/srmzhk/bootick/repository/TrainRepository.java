package com.srmzhk.bootick.repository;

import com.srmzhk.bootick.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {

    @Query("""
    SELECT t FROM Train t 
    JOIN t.stops rs1 
    JOIN t.stops rs2 
    WHERE rs1.station = :fromStation 
      AND rs2.station = :toStation 
      AND rs1.position < rs2.position
      AND FUNCTION('DATE', rs1.date) = :selectedDate
""")
    List<Train> findTrainsBetweenStationsOnDate(
            @Param("fromStation") String from,
            @Param("toStation") String to,
            @Param("selectedDate") LocalDate selectedDate
    );


    Optional<Train> findByNumber(String number);
}
