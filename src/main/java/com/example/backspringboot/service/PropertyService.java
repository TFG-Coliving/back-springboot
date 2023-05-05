package com.example.backspringboot.service;

import com.example.backspringboot.model.Property;
import com.example.backspringboot.model.Rent_review;
import com.example.backspringboot.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepo;
    private final SimpMessagingTemplate messagingTemplate;

    public List<Property> listAllProperties() {
        return propertyRepo.findAll();
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepo.findById(id);
    }

    public void saveProperty(Property property) {
        propertyRepo.save(property);

        messagingTemplate.convertAndSend("/topic/properties", property);
    }
}
