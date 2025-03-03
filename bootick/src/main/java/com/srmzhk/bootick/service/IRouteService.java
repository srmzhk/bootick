package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.RouteDto;
import com.srmzhk.bootick.dto.SearchDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRouteService {

    RouteDto addRoute(RouteDto route);

    void deleteRoute(int id);

    RouteDto updateRoute(RouteDto route);

    List<RouteDto> getRoutesForSearch(SearchDto search);

}
