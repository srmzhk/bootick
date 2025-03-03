package com.srmzhk.bootick.service.impl;

import com.srmzhk.bootick.dto.RouteDto;
import com.srmzhk.bootick.dto.SearchDto;
import com.srmzhk.bootick.dto.TrainDto;
import com.srmzhk.bootick.repository.RouteRepository;
import com.srmzhk.bootick.service.IRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService implements IRouteService {

    private final RouteRepository routeRepository;

    @Override
    public RouteDto addRoute(RouteDto route) {
        return null;
    }

    @Override
    public void deleteRoute(int id) {

    }

    @Override
    public RouteDto updateRoute(RouteDto route) {
        return null;
    }

    @Override
    public List<RouteDto> getRoutesForTrain(TrainDto train) {
        return List.of();
    }

    @Override
    public List<RouteDto> getRoutesForSearch(SearchDto search) {
        return List.of();
    }
}
