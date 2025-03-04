package com.srmzhk.bootick.controller;

import com.srmzhk.bootick.dto.BookingDto;
import com.srmzhk.bootick.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final IBookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<BookingDto> bookSeat(@RequestBody BookingDto bookingDto) {
        BookingDto booking = bookingService.bookSeat(bookingDto);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable int id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        List<BookingDto> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{phone}")
    public ResponseEntity<List<BookingDto>> getBookingsForUser(@PathVariable String phone) {
        List<BookingDto> bookings = bookingService.getBookingsForUser(phone);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookingDto>> getBookingsForTrain(@PathVariable int id) {
        List<BookingDto> bookings = bookingService.getBookingsForTrain(id);
        return ResponseEntity.ok(bookings);
    }
}
