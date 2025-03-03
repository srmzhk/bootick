package com.srmzhk.bootick.service.impl;

import com.srmzhk.bootick.dto.RouteDto;
import com.srmzhk.bootick.dto.RouteStopDto;
import com.srmzhk.bootick.repository.RouteStopRepository;
import com.srmzhk.bootick.service.IRouteStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteStopService implements IRouteStopService {

    private final RouteStopRepository routeStopRepository;

    @Override
    public RouteStopDto addRouteStop(RouteStopDto routeStop) {
        return null;
    }

    @Override
    public void deleteRouteStop(int stop_id) {

    }

    @Override
    public RouteStopDto updateRouteStop(RouteStopDto routeStop) {
        return null;
    }

    @Override
    public List<RouteStopDto> getRouteStopsForRoute(RouteDto route) {
        return List.of();
    }

    @Override
    public List<RouteStopDto> getAllRouteStops() {
        return List.of();
    }
}
