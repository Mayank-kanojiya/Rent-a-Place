package com.training.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.Dto.BookingDto;
import com.training.Dto.PropertyDto;
import com.training.model.Booking;
import com.training.model.Property;
import com.training.repo.BookingRepo;
import com.training.repo.PropertyRepo;
import com.training.repo.UserRepo;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepo propertyRepo;

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Property> getAllProperties() {
        return propertyRepo.findAll();
    }

    public PropertyDto findProperty(int pid) {
        Property property = propertyRepo.findByPid(pid);
        return convertToPropertyDto(property);
    }

    public void updateProperty(int price, int pid) {
        Property property = propertyRepo.findByPid(pid);
        property.setPrice(price);
        propertyRepo.save(property);
    }

    public void deleteProperty(int pid) {
        propertyRepo.deleteById(pid);
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            // Log or handle
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public String addProperty(PropertyDto propertyDto) {
        Property property = new Property();
        property.setName(propertyDto.getName());
        property.setLocation(propertyDto.getLocation());
        property.setType(propertyDto.getType());
        property.setFeatures(propertyDto.getFeatures());
        property.setDescription(propertyDto.getDescription());
        property.setPhone(propertyDto.getPhone());
        property.setOwner_id(propertyDto.getOwner_id());
        property.setPrice(propertyDto.getPrice());

        try {
            if (propertyDto.getImage() != null) {
                property.setImage(compressBytes(propertyDto.getImage().getBytes()));
            }
            if (propertyDto.getImage1() != null) {
                property.setImage1(compressBytes(propertyDto.getImage1().getBytes()));
            }
            if (propertyDto.getImage2() != null) {
                property.setImage2(compressBytes(propertyDto.getImage2().getBytes()));
            }
            if (propertyDto.getImage3() != null) {
                property.setImage3(compressBytes(propertyDto.getImage3().getBytes()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        property = propertyRepo.save(property);
        System.out.println("Property added with ID: " + property.getPid());

        return "Property successfully added.";
    }

    public List<Property> getMyProperties(int ownerId) {
        return propertyRepo.findByOwnerId(ownerId);
    }

    public List<PropertyDto> getnbProperty() {
        List<PropertyDto> propertyDtoList = new ArrayList<>();
        List<Booking> bookings = bookingRepo.findByStatus(false);
        for (Booking booking : bookings) {
            if (booking != null && booking.getUserId() == 0) {
                Property property = booking.getProperty();
                propertyDtoList.add(convertToPropertyDto(property));
            }
        }
        return propertyDtoList;
    }

    public String book(BookingDto bookingDto) {
        Property property = propertyRepo.findById(bookingDto.getUserId()).orElse(null);
        if (property == null) {
            return "Property not found";
        }
        Booking booking = new Booking();
        booking.setProperty(property);
        booking.setStatus(false);
        booking.setUserId(bookingDto.getUserId());
        booking.setCheckin(bookingDto.getCheckin());
        booking.setCheckout(bookingDto.getCheckout());

        bookingRepo.save(booking);  // Always insert a new row
        return "Booking successful";
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    public PropertyDto convertToPropertyDto(Property property) {
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setPid(property.getPid());
        propertyDto.setName(property.getName());
        propertyDto.setDescription(property.getDescription());
        propertyDto.setFeatures(property.getFeatures());
        propertyDto.setLocation(property.getLocation());
        propertyDto.setOwner_id(property.getOwner_id());
        if (property.getImage() != null) {
            propertyDto.setReturnedImage(decompressBytes(property.getImage()));
        }
        if (property.getImage2() != null) {
            propertyDto.setReturnedImage2(decompressBytes(property.getImage2()));
        }
        if (property.getImage3() != null) {
            propertyDto.setReturnedImage3(decompressBytes(property.getImage3()));
        }
        if (property.getImage1() != null) {
            propertyDto.setReturnedImage1(decompressBytes(property.getImage1()));
        }
        propertyDto.setPrice(property.getPrice());
        propertyDto.setPhone(property.getPhone());
        propertyDto.setType(property.getType());
        return propertyDto;
    }

    public String approveProperty(int bookingId) {
        Booking booking = bookingRepo.findById(bookingId).orElse(null);
        if (booking == null) {
            return "Booking not found";
        }
        booking.setStatus(true);
        bookingRepo.save(booking);
        return "Approved";
    }

    public String disapproveProperty(int bookingId) {
        Booking booking = bookingRepo.findById(bookingId).orElse(null);
        if (booking == null) {
            return "Booking not found";
        }
        booking.setStatus(false);
        booking.setUserId(0);
        booking.setCheckin(null);
        booking.setCheckout(null);
        bookingRepo.save(booking);
        return "Disapproved";
    }

    public List<PropertyDto> searchCheckin(Date checkin) {
        List<PropertyDto> propertyDtos = new ArrayList<>();
        bookingRepo.findByCheckins(checkin).forEach(booking -> {
            Property property = booking.getProperty();
            propertyDtos.add(convertToPropertyDto(property));
        });
        return propertyDtos;
    }

    public List<PropertyDto> searchCinCout(Date checkin, Date checkout) {
        List<PropertyDto> propertyDtos = new ArrayList<>();
        bookingRepo.findByCheckinCheckout(checkin, checkout).forEach(booking -> {
            Property property = booking.getProperty();
            propertyDtos.add(convertToPropertyDto(property));
        });
        return propertyDtos;
    }

    public List<Booking> getBookingsForOwner(int ownerId) {
        List<Property> properties = propertyRepo.findByOwnerId(ownerId);
        List<Booking> allBookings = new ArrayList<>();
        for (Property p : properties) {
            allBookings.addAll(bookingRepo.findByProperty(p));
        }
        return allBookings;
    }
}
