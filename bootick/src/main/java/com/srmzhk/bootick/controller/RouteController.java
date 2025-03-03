package com.srmzhk.bootick.controller;

import com.srmzhk.bootick.service.IRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {

    private final IRouteService routeService;
}
