package com.training.controller;

import com.training.Dto.BookingDto;
import com.training.Dto.PropertyDto;
import com.training.Dto.SignUpRequest;
import com.training.Dto.UserDto;
import com.training.model.Booking;
import com.training.model.User;
import com.training.repo.BookingRepo;
import com.training.service.PropertyService;
import com.training.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private BookingRepo bookingRepo; // CORRECT!

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyService propertyService;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequest signUpRequest) {
        UserDto userDto = userService.signup(signUpRequest);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/booking")
    public ResponseEntity<?> book(@RequestBody BookingDto bookingDto) {
        String message = propertyService.book(bookingDto);
        return ResponseEntity.ok(Collections.singletonMap("message", message));
    }

    @GetMapping("/search/{checkin}")
    public ResponseEntity<List<PropertyDto>> searchByCheckin(@PathVariable Date checkin) {
        List<PropertyDto> results = propertyService.searchCheckin(checkin);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/search/{checkin}/{checkout}")
    public ResponseEntity<List<PropertyDto>> searchByCheckinCheckout(@PathVariable Date checkin, @PathVariable Date checkout) {
        List<PropertyDto> results = propertyService.searchCinCout(checkin, checkout);
        return ResponseEntity.ok(results);
    }
    @GetMapping("/bookings/{user_id}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable int user_id) {
        
		List<Booking> bookings = bookingRepo.findByUserId(user_id);
        return ResponseEntity.ok(bookings);
    }

}
