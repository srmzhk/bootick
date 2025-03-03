package com.srmzhk.bootick.controller;

import com.srmzhk.bootick.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final IBookingService bookingService;
}
