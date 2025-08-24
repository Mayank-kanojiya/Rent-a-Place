package com.training.controller;

import com.training.Dto.PropertyDto;
import com.training.model.Property;
import com.training.service.PropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/properties")
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/statusProperty")
    public ResponseEntity<List<Property>> getStatusProperty() {
        List<Property> properties = propertyService.getAllProperties(); // Or filter as needed
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/property/{pid}")
    public ResponseEntity<PropertyDto> getProperty(@PathVariable("pid") int pid) {
        PropertyDto propertyDto = propertyService.findProperty(pid);
        if(propertyDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(propertyDto);
    }
    
}
