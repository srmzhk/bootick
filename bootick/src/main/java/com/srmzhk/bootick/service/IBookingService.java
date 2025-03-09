package com.srmzhk.bootick.service;

import com.srmzhk.bootick.dto.BookingDto;
import com.srmzhk.bootick.dto.TrainDto;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface IBookingService {

    BookingDto bookSeat(BookingDto bookingDto);

    void cancelBooking(int bookingId, String phone);

    List<BookingDto> getAllBookings();

    List<BookingDto> getBookingsForUser(String phone);

    List<BookingDto> getBookingsForTrain(int trainId);

    List<BookingDto> getBookingsForSeat(int seatId);

}
