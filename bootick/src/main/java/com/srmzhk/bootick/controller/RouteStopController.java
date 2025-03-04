package com.srmzhk.bootick.controller;

import com.srmzhk.bootick.dto.RouteStopDto;
import com.srmzhk.bootick.service.IRouteStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stops")
@RequiredArgsConstructor
public class RouteStopController {

    private final IRouteStopService routeStopService;

    @GetMapping("/all")
    public ResponseEntity<List<RouteStopDto>> getAllRouteStops() {
        List<RouteStopDto> routeStops = routeStopService.getAllRouteStops();
        return ResponseEntity.ok(routeStops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<RouteStopDto>> getRouteStopsForTrain(@PathVariable int id) {
        List<RouteStopDto> routeStops = routeStopService.getRouteStopsForTrain(id);
        return ResponseEntity.ok(routeStops);
    }

    @PostMapping("/add")
    public ResponseEntity<RouteStopDto> addRouteStop(@RequestBody RouteStopDto routeStopDto) {
        RouteStopDto routeStop = routeStopService.createRouteStop(routeStopDto);
        return ResponseEntity.ok(routeStop);
    }

    @PutMapping("/update")
    public ResponseEntity<RouteStopDto> updateRouteStop(@RequestBody RouteStopDto routeStopDto) {
        RouteStopDto routeStop = routeStopService.updateRouteStop(routeStopDto);
        return ResponseEntity.ok(routeStop);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRouteStop(@PathVariable int id) {
        routeStopService.deleteRouteStop(id);
        return ResponseEntity.ok("Success");
    }
}
