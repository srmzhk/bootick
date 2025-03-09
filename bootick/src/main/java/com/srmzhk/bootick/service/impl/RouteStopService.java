package com.srmzhk.bootick.service.impl;

import com.srmzhk.bootick.dto.RouteStopDto;
import com.srmzhk.bootick.dto.TrainDto;
import com.srmzhk.bootick.exception.ItemAlreadyExistException;
import com.srmzhk.bootick.exception.ItemNotFoundException;
import com.srmzhk.bootick.model.RouteStop;
import com.srmzhk.bootick.repository.RouteStopRepository;
import com.srmzhk.bootick.service.IRouteStopService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.RouteMatcher;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteStopService implements IRouteStopService {

    private final RouteStopRepository routeStopRepository;
    private final ModelMapper modelMapper;

    @Override
    public RouteStopDto createRouteStop(RouteStopDto routeStopDto) {
        Optional<RouteStop> existedRouteStop = routeStopRepository
                .findByStationAndTrainIdAndDateAndTime(
                        routeStopDto.getStation(),
                        routeStopDto.getTrainId(),
                        routeStopDto.getDate(),
                        routeStopDto.getTime()
                );

        if (existedRouteStop.isPresent())
            throw new ItemAlreadyExistException();

        List<RouteStop> stopsWithSamePosition = routeStopRepository
                .findByTrainIdAndPosition(routeStopDto.getTrainId(), routeStopDto.getPosition());

        if (!stopsWithSamePosition.isEmpty())
            throw new ItemAlreadyExistException();

        RouteStop routeStop = modelMapper.map(routeStopDto, RouteStop.class);
        routeStop = routeStopRepository.save(routeStop);
        return modelMapper.map(routeStop, RouteStopDto.class);
    }

    @Override
    public void deleteRouteStop(int stopId) {
        routeStopRepository.findById(stopId)
                .orElseThrow(ItemNotFoundException::new);

        routeStopRepository.deleteById(stopId);
    }

    @Override
    public RouteStopDto updateRouteStop(RouteStopDto routeStopDto) {
        RouteStop routeStop = routeStopRepository.findById(routeStopDto.getId())
                .orElseThrow(ItemNotFoundException::new);

        modelMapper.map(routeStop, RouteStopDto.class);

        RouteStop updatedRouteStop = routeStopRepository.save(routeStop);
        return modelMapper.map(updatedRouteStop, RouteStopDto.class);
    }

    @Override
    public List<RouteStopDto> getRouteStopsForTrain(int trainId) {
        List<RouteStop> routes = routeStopRepository.findAllByTrainId(trainId);
        if(routes.isEmpty())
            throw new ItemNotFoundException();

        return routes
                .stream()
                .map(route -> modelMapper.map(route, RouteStopDto.class))
                .toList();
    }

    @Override
    public List<RouteStopDto> getAllRouteStops() {
        return routeStopRepository.findAll()
                .stream()
                .distinct()
                .map(route -> modelMapper.map(route, RouteStopDto.class))
                .toList();
    }

    @Override
    public RouteStopDto getRouteStopById(int id) {
        RouteStop routeStop = routeStopRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);

        return modelMapper.map(routeStop, RouteStopDto.class);
    }

    @Override
    public List<String> getRouteStopsForSymbols(String symbols) {
        List<String> routeStops = routeStopRepository.findDistinctStationsByStartingWith(symbols);

        if (routeStops.isEmpty())
            throw new ItemNotFoundException();

        return routeStops;
    }

    @Override
    public RouteStopDto getRouteStopForStation(List<RouteStop> stops, String station) {
        RouteStop routeStop = stops.stream()
                .filter(stop -> stop.getStation().equals(station))
                .findFirst()
                .orElseThrow(ItemNotFoundException::new);

        return modelMapper.map(routeStop, RouteStopDto.class);
    }
}
