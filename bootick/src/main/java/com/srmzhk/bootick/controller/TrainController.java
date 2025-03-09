package com.srmzhk.bootick.controller;

import com.srmzhk.bootick.dto.SearchDto;
import com.srmzhk.bootick.dto.SearchTrainDto;
import com.srmzhk.bootick.dto.TrainDto;
import com.srmzhk.bootick.repository.TrainRepository;
import com.srmzhk.bootick.service.ITrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trains")
@RequiredArgsConstructor
public class TrainController {

    private final ITrainService trainService;

    @GetMapping("/all")
    public ResponseEntity<List<TrainDto>> getAllTrains() {
        List<TrainDto> trains = trainService.getAllTrains();
        return ResponseEntity.ok(trains);
    }

    @PostMapping("/search")
    public ResponseEntity<List<SearchTrainDto>> getTrainsFromSearch(@RequestBody SearchDto searchData) {
        List<SearchTrainDto> trains = trainService.getTrainsForSearch(searchData);
        return ResponseEntity.ok(trains);
    }

    @PostMapping("/add")
    public ResponseEntity<TrainDto> addTrain(@RequestBody TrainDto trainDto) {
        TrainDto train = trainService.addTrain(trainDto);
        return ResponseEntity.ok(train);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrain(@PathVariable int id) {
        trainService.deleteTrain(id);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("/update")
    public ResponseEntity<TrainDto> updateTrain(@RequestBody TrainDto trainDto) {
        TrainDto train = trainService.updateTrain(trainDto);
        return ResponseEntity.ok(train);
    }
}
