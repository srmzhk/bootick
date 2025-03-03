package com.srmzhk.bootick.controller;

import com.srmzhk.bootick.service.ISeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {

    private final ISeatService seatService;

}
