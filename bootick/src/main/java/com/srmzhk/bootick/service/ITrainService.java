package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.SearchDto;
import com.srmzhk.bootick.dto.TrainDto;

import java.util.List;

public interface ITrainService {

    TrainDto addTrain(TrainDto trainDto);

    void deleteTrain(int trainId);

    TrainDto updateTrain(TrainDto trainDto);

    List<TrainDto> getTrainsForSearch(SearchDto searchData);

    List<TrainDto> getAllTrains();
}
