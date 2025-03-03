package com.srmzhk.bootick.repository;

import com.srmzhk.bootick.model.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteStopRepository extends JpaRepository<RouteStop, Integer> {

}
