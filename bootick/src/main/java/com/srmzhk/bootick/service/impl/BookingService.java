package com.srmzhk.bootick.service.impl;

import com.srmzhk.bootick.dto.BookingDto;
import com.srmzhk.bootick.dto.RouteStopDto;
import com.srmzhk.bootick.dto.SeatDto;
import com.srmzhk.bootick.dto.TrainDto;
import com.srmzhk.bootick.exception.ItemAlreadyExistException;
import com.srmzhk.bootick.exception.ItemNotFoundException;
import com.srmzhk.bootick.exception.TimeIsOverException;
import com.srmzhk.bootick.model.Booking;
import com.srmzhk.bootick.model.Train;
import com.srmzhk.bootick.repository.BookingRepository;
import com.srmzhk.bootick.service.IBookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    private final SeatService seatService;
    private final RouteStopService routeStopService;
    private final TrainService trainService;

    @Override
    public BookingDto bookSeat(BookingDto bookingDto) {
        RouteStopDto fromStop = routeStopService.getRouteStopById(bookingDto.getFromStopId());
        RouteStopDto toStop = routeStopService.getRouteStopById(bookingDto.getToStopId());
        TrainDto trainDto = trainService.getTrainById(bookingDto.getTrainId());
        Train train = modelMapper.map(trainDto, Train.class);
        List<SeatDto> availableSeats = seatService
                .getAvailableSeatsForSearch(train, fromStop.getPosition(), toStop.getPosition());

        if (availableSeats.isEmpty())
            throw new ItemNotFoundException();

        Optional<Booking> existedBooking = bookingRepository.findByBookingData(
                bookingDto.getTrainId(),
                availableSeats.getFirst().getId(),
                fromStop.getPosition(),
                toStop.getPosition()
        );

        if (existedBooking.isPresent())
            throw new ItemAlreadyExistException();

        bookingDto.setSeatId(availableSeats.getFirst().getId());
        Booking booking = modelMapper.map(bookingDto, Booking.class);
        booking.setBookingTime(LocalDateTime.now());

        booking = bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDto.class);
    }

    @Override
    @Transactional
    public void cancelBooking(int bookingId, String phone) {
        Booking existedBooking = bookingRepository.findByIdAndPhone(bookingId, phone)
                .orElseThrow(ItemNotFoundException::new);

        // check if time less than 1 hour before arriving
        if ((Duration.between(LocalTime.now(), existedBooking.getFromStop().getTime()).toMinutes() < 60)
            || LocalDate.now().isAfter(existedBooking.getFromStop().getDate()))
            throw new TimeIsOverException();

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
    public List<BookingDto> getBookingsForUser(String phone) {
        List<Booking> bookings = bookingRepository.findByPhone(phone);
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

    @Override
    public List<BookingDto> getBookingsForSeat(int seatId) {
        List<Booking> bookings = bookingRepository.findBySeatId(seatId);
        if (bookings.isEmpty())
            throw new ItemNotFoundException();

        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .toList();
    }
}
