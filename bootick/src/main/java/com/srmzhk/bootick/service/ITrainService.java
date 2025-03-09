package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.SearchDto;
import com.srmzhk.bootick.dto.SearchTrainDto;
import com.srmzhk.bootick.dto.TrainDto;
import com.srmzhk.bootick.model.Train;

import java.util.List;

public interface ITrainService {

    TrainDto addTrain(TrainDto trainDto);

    void deleteTrain(int trainId);

    TrainDto updateTrain(TrainDto trainDto);

    List<SearchTrainDto> getTrainsForSearch(SearchDto searchData);

    List<TrainDto> getAllTrains();

    TrainDto getTrainById(int id);
}
