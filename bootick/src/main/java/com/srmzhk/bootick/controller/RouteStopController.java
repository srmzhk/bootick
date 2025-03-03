package com.srmzhk.bootick.controller;

import com.srmzhk.bootick.service.IRouteStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stops")
@RequiredArgsConstructor
public class RouteStopController {

    private final IRouteStopService routeStopService;

}
