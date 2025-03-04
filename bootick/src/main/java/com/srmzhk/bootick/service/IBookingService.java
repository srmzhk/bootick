package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.BookingDto;
import com.srmzhk.bootick.dto.TrainDto;

import java.util.List;
import java.util.Optional;

public interface IBookingService {

    BookingDto bookSeat(BookingDto bookingDto);

    void cancelBooking(int bookingId);

    List<BookingDto> getAllBookings();

    List<BookingDto> getBookingsForUser(String phoneNumber);

    List<BookingDto> getBookingsForTrain(int trainId);

}
