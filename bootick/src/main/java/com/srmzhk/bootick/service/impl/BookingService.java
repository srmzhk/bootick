package com.srmzhk.bootick.service.impl;

import com.srmzhk.bootick.dto.BookingDto;
import com.srmzhk.bootick.dto.RouteDto;
import com.srmzhk.bootick.model.Train;
import com.srmzhk.bootick.repository.BookingRepository;
import com.srmzhk.bootick.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;

    @Override
    public BookingDto bookSeat(BookingDto data) {
        return null;
    }

    @Override
    public void cancelBooking(int booking_id) {

    }

    @Override
    public List<BookingDto> getAllBookings() {
        return List.of();
    }

    @Override
    public List<BookingDto> getBookingsForUser(String phoneNumber) {
        return List.of();
    }

    @Override
    public List<BookingDto> getAvailableBookingsForRoute(RouteDto route) {
        return List.of();
    }

    @Override
    public List<BookingDto> getAvailableBookingsForTrain(Train train) {
        return List.of();
    }
}
