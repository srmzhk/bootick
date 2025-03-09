package com.srmzhk.bootick.config;

import com.srmzhk.bootick.dto.*;
import com.srmzhk.bootick.model.*;
import com.srmzhk.bootick.repository.RouteStopRepository;
import com.srmzhk.bootick.repository.SeatRepository;
import com.srmzhk.bootick.repository.TrainRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Lazy;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

    private final TrainRepository trainRepository;
    private final RouteStopRepository routeStopRepository;
    private final SeatRepository seatRepository;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<Integer, Train> trainConverter = ctx -> {
            Integer trainId = ctx.getSource();
            return (trainId != null && trainId != 0)
                    ? trainRepository.findById(trainId).orElse(null)
                    : null;
        };

        Converter<Integer, RouteStop> fromStopConverter = ctx -> {
            Integer stopId = ctx.getSource();
            return (stopId != null && stopId != 0)
                    ? routeStopRepository.findById(stopId).orElse(null)
                    : null;
        };

        Converter<Integer, RouteStop> toStopConverter = ctx -> {
            Integer stopId = ctx.getSource();
            return (stopId != null && stopId != 0)
                    ? routeStopRepository.findById(stopId).orElse(null)
                    : null;
        };

        Converter<Integer, Seat> seatConverter = ctx -> {
            Integer seatId = ctx.getSource();
            return (seatId != null && seatId != 0)
                    ? seatRepository.findById(seatId).orElse(null)
                    : null;
        };

        modelMapper.typeMap(RouteStop.class, RouteStopDto.class)
                .addMappings(mapper -> mapper.map(src -> src.getTrain().getId(), RouteStopDto::setTrainId));

        modelMapper.typeMap(RouteStopDto.class, RouteStop.class)
                .addMappings(mapper -> mapper.using(trainConverter).map(RouteStopDto::getTrainId, RouteStop::setTrain));

        modelMapper.typeMap(Seat.class, SeatDto.class)
                .addMappings(mapper -> mapper.map(src -> src.getTrain().getId(), SeatDto::setTrainId));

        modelMapper.typeMap(SeatDto.class, Seat.class)
                .addMappings(mapper -> mapper.using(trainConverter).map(SeatDto::getTrainId, Seat::setTrain));


        modelMapper.typeMap(BookingDto.class, Booking.class)
                .addMappings(mapper -> {
                    mapper.using(trainConverter).map(BookingDto::getTrainId, Booking::setTrain);

                    mapper.using(fromStopConverter).map(BookingDto::getFromStopId, Booking::setFromStop);

                    mapper.using(toStopConverter).map(BookingDto::getToStopId, Booking::setToStop);

                    mapper.using(seatConverter).map(BookingDto::getSeatId, Booking::setSeat);
                });

        modelMapper.typeMap(Booking.class, BookingDto.class)
                .addMappings(mapper -> {
                    // Маппим train на trainId
                    mapper.map(src -> src.getTrain().getId(), BookingDto::setTrainId);

                    // Маппим fromStop на fromStopId
                    mapper.map(src -> src.getFromStop().getId(), BookingDto::setFromStopId);

                    // Маппим toStop на toStopId
                    mapper.map(src -> src.getToStop().getId(), BookingDto::setToStopId);

                    // Маппим seat на seatId
                    mapper.map(src -> src.getSeat().getId(), BookingDto::setSeatId);
                });

        return modelMapper;
    }
}
