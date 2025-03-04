package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.RouteStopDto;
import com.srmzhk.bootick.dto.TrainDto;

import java.util.List;

public interface IRouteStopService {

    RouteStopDto createRouteStop(RouteStopDto routeStopDto);

    void deleteRouteStop(int stopId);

    RouteStopDto updateRouteStop(RouteStopDto routeStopDto);

    List<RouteStopDto> getRouteStopsForTrain(int trainId);

    List<RouteStopDto> getAllRouteStops();

}
