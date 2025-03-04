package com.srmzhk.bootick.service.impl;

import com.srmzhk.bootick.dto.BookingDto;
import com.srmzhk.bootick.dto.SeatDto;
import com.srmzhk.bootick.dto.TrainDto;
import com.srmzhk.bootick.exception.ItemAlreadyExistException;
import com.srmzhk.bootick.exception.ItemNotFoundException;
import com.srmzhk.bootick.exception.TimeIsOverException;
import com.srmzhk.bootick.model.Booking;
import com.srmzhk.bootick.repository.BookingRepository;
import com.srmzhk.bootick.service.IBookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    private final SeatService seatService;

    @Override
    public BookingDto bookSeat(BookingDto bookingDto) {
        Optional<Booking> existedBooking = bookingRepository.findByBookingData(
                bookingDto.getTrain().getId(),
                bookingDto.getSeat().getId(),
                bookingDto.getFromStop().getPosition(),
                bookingDto.getToStop().getPosition()
        );
        if (existedBooking.isPresent())
            throw new ItemAlreadyExistException();

        Booking booking = modelMapper.map(bookingDto, Booking.class);

        // check if seat is booking over all stations
        if (bookingDto.getFromStop().equals(bookingDto.getTrain().getRouteStops().getFirst())
                && bookingDto.getToStop().equals(bookingDto.getTrain().getRouteStops().getLast())) {
            bookingDto.getSeat().setAvailable(false);
            seatService.updateSeat(bookingDto.getSeat());
        }

        booking.setBookingTime(LocalDateTime.now());

        booking = bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDto.class);
    }

    @Override
    @Transactional
    public void cancelBooking(int bookingId) {
        Booking existedBooking = bookingRepository.findById(bookingId)
                .orElseThrow(ItemNotFoundException::new);

        // check if time less than 1 hour before arriving
        if (Duration.between(LocalDateTime.now(), existedBooking.getFromStop().getArrivalTime()).toMinutes() < 60)
            throw new TimeIsOverException();

        // change seat availability
        SeatDto seatDto = modelMapper.map(existedBooking.getSeat(), SeatDto.class);
        if (!seatDto.isAvailable()) {
            seatDto.setAvailable(true);
            seatService.updateSeat(seatDto);
        }

        bookingRepository.deleteById(bookingId);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .toList();
    }

    @Override
    public List<BookingDto> getBookingsForUser(String phoneNumber) {
        List<Booking> bookings = bookingRepository.findByPhoneNumber(phoneNumber);
        if (bookings.isEmpty())
            throw new ItemNotFoundException();

        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .toList();
    }

    @Override
    public List<BookingDto> getBookingsForTrain(int trainId) {
        List<Booking> bookings = bookingRepository.findByTrainId((trainId));
        if (bookings.isEmpty())
            throw new ItemNotFoundException();

        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .toList();
    }
}
