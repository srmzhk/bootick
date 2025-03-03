package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.BookingDto;
import com.srmzhk.bootick.dto.RouteDto;
import com.srmzhk.bootick.model.Train;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookingService {

    BookingDto bookSeat(BookingDto data);

    void cancelBooking(int booking_id);

    List<BookingDto> getAllBookings();

    List<BookingDto> getBookingsForUser(String phoneNumber);

    List<BookingDto> getAvailableBookingsForRoute(RouteDto route);

    List<BookingDto> getAvailableBookingsForTrain(Train train);

}
