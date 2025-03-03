package com.srmzhk.bootick.controller;

import com.srmzhk.bootick.service.ITrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trains")
@RequiredArgsConstructor
public class TrainController {

    private final ITrainService trainService;

}
