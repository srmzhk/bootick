package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.RouteDto;
import com.srmzhk.bootick.dto.SearchDto;
import com.srmzhk.bootick.dto.TrainDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ITrainService {

    TrainDto addTrain(TrainDto train);

    void deleteTrain(int train_id);

    TrainDto updateTrain(TrainDto train);

    List<TrainDto> getTrainsForSearch(SearchDto searchData);

    List<TrainDto> getAllTrains();
}
