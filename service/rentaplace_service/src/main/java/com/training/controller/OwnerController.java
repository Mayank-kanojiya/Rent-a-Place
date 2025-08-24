package com.training.controller;

import com.training.Dto.BookingDto;
import com.training.Dto.PropertyDto;
import com.training.Dto.ServerResponse;
import com.training.model.Booking;
import com.training.model.Property;
import com.training.service.PropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin("*")
@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/myProperties/{owner_id}")
    public ResponseEntity<List<Property>> getMyProperties(@PathVariable("owner_id") int owner_id) {
        List<Property> properties = propertyService.getMyProperties(owner_id);
        return ResponseEntity.ok(properties);
    }

    // Add property using owner and price from the path, rest from form/multipart (for file upload)
    @PostMapping("/addProperty/{owner}")
    public ResponseEntity<ServerResponse> addProperty(@PathVariable("owner") String oids, @ModelAttribute PropertyDto propertyDto) throws IOException {
        String[] splitted = oids.split(",");
        propertyDto.setOwner_id(Integer.parseInt(splitted[0]));
        propertyDto.setPrice(Integer.parseInt(splitted[1]));
        propertyService.addProperty(propertyDto);

        ServerResponse response = new ServerResponse();
        response.setMessage("Property added successfully");
        response.setStatus(org.springframework.http.HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/approve/{bid}")
    public ResponseEntity<ServerResponse> approveProperty(@PathVariable("bid") int bid) {
        propertyService.approveProperty(bid);
        ServerResponse response = new ServerResponse();
        response.setMessage("Approved");
        response.setStatus(org.springframework.http.HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/disapprove/{bid}")
    public ResponseEntity<ServerResponse> disapproveProperty(@PathVariable("bid") int bid) {
        propertyService.disapproveProperty(bid);
        ServerResponse response = new ServerResponse();
        response.setMessage("Disapproved");
        response.setStatus(org.springframework.http.HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{pid}")
    public ResponseEntity<ServerResponse> deleteProperty(@PathVariable("pid") int pid) {
        propertyService.deleteProperty(pid);
        ServerResponse response = new ServerResponse();
        response.setMessage("Property deleted successfully");
        response.setStatus(org.springframework.http.HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePrice/{pid}/{price}")
    public ResponseEntity<String> updatePropertyPrice(@PathVariable("pid") int pid, @PathVariable("price") int price) {
        propertyService.updateProperty(price, pid);
        return ResponseEntity.ok("Price updated.");
    }
    @GetMapping("/bookingRequests/{owner_id}")
    public ResponseEntity<List<BookingDto>> getBookingRequests(@PathVariable("owner_id") int owner_id) {
        List<Booking> bookings = propertyService.getBookingsForOwner(owner_id);
        
        // Map each Booking to BookingDto, and pass property.getName() as propertyName
        List<BookingDto> bookingDtos = bookings.stream()
            .map(b -> new BookingDto(
                b.getBookingId(),
                b.getUserId(),
                b.getProperty().getName(),    // <--- Get property name from associated property entity
                b.getCheckin(),
                b.getCheckout(),
                b.isStatus()
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(bookingDtos);
    }

 // Add this method to handle GET /owners/properties/{pid}
    @GetMapping("/properties/{pid}")
    public ResponseEntity<PropertyDto> getPropertyById(@PathVariable("pid") int pid) {
        PropertyDto property = propertyService.findProperty(pid);
        if (property != null) {
            return ResponseEntity.ok(property);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
