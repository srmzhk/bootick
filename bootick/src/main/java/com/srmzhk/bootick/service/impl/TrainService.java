package com.srmzhk.bootick.service.impl;

import com.srmzhk.bootick.dto.RouteDto;
import com.srmzhk.bootick.dto.TrainDto;
import com.srmzhk.bootick.repository.TrainRepository;
import com.srmzhk.bootick.service.ITrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainService implements ITrainService {

    private final TrainRepository trainRepository;

    @Override
    public TrainDto addTrain(TrainDto train) {
        return null;
    }

    @Override
    public void deleteTrain(int train_id) {

    }

    @Override
    public TrainDto updateTrain(TrainDto train) {
        return null;
    }

    @Override
    public List<TrainDto> getTrainsForRoute(RouteDto routeDto) {
        return List.of();
    }

    @Override
    public List<TrainDto> getAllTrains() {
        return List.of();
    }
}
