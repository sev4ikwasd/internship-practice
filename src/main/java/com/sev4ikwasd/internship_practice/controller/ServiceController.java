package com.sev4ikwasd.internship_practice.controller;

import com.sev4ikwasd.internship_practice.entity.Service;
import com.sev4ikwasd.internship_practice.repository.ServiceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ServiceController {
    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/service")
    ResponseEntity<List<Service>> getAllServices() {
        return ResponseEntity.ok(serviceRepository.getAll());
    }

}
