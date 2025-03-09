package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.RouteStopDto;
import com.srmzhk.bootick.dto.TrainDto;
import com.srmzhk.bootick.model.RouteStop;

import java.util.List;

public interface IRouteStopService {

    RouteStopDto createRouteStop(RouteStopDto routeStopDto);

    void deleteRouteStop(int stopId);

    RouteStopDto updateRouteStop(RouteStopDto routeStopDto);

    List<RouteStopDto> getRouteStopsForTrain(int trainId);

    List<RouteStopDto> getAllRouteStops();

    RouteStopDto getRouteStopById(int id);

    List<String> getRouteStopsForSymbols(String symbols);

    RouteStopDto getRouteStopForStation(List<RouteStop> stops, String station);

}
