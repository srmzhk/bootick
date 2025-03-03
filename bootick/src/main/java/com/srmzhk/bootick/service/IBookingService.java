package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.BookingDto;
import com.srmzhk.bootick.dto.RouteDto;
import com.srmzhk.bootick.dto.TrainDto;

import java.util.List;

public interface IBookingService {

    BookingDto bookSeat(BookingDto data);

    void cancelBooking(int booking_id);

    List<BookingDto> getAllBookings();

    List<BookingDto> getBookingsForUser(String phoneNumber);

    List<BookingDto> getBookingsForRoute(RouteDto route);

    List<BookingDto> getBookingsForTrain(TrainDto train);

}
