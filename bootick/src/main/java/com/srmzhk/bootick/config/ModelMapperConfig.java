package com.srmzhk.bootick.config;

import com.srmzhk.bootick.dto.*;
import com.srmzhk.bootick.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<BookingDto, Booking>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<RouteStopDto, RouteStop>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<SeatDto, Seat>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<TrainDto, Train>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        return modelMapper;
    }
}
