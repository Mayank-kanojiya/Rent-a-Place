package com.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.repo.BookingRepo;
import com.training.model.Booking;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    // Example: Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    // Example: Save booking
    public Booking saveBooking(Booking booking) {
        return bookingRepo.save(booking);
    }

    // Example: Get bookings by status
    public List<Booking> getBookingsByStatus(Boolean status) {
        return bookingRepo.findByStatus(status);
    }

    // Add other business logic methods as needed
}
