package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.RouteDto;
import com.srmzhk.bootick.dto.RouteStopDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRouteStopService {

    RouteStopDto addRouteStop(RouteStopDto routeStop);

    void deleteRouteStop(int stop_id);

    RouteStopDto updateRouteStop(RouteStopDto routeStop);

    List<RouteStopDto> getRouteStopsForRoute(RouteDto route);

    List<RouteStopDto> getAllRouteStops();

}
