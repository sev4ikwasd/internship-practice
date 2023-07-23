package com.sev4ikwasd.internship_practice.controller;

import com.sev4ikwasd.internship_practice.entity.Company;
import com.sev4ikwasd.internship_practice.entity.Service;
import com.sev4ikwasd.internship_practice.entity.ServiceWithGroupingWithCompanyRelationList;
import com.sev4ikwasd.internship_practice.repository.ServiceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {
    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping
    ResponseEntity<List<Service>> getAllServices() {
        return ResponseEntity.ok(serviceRepository.getAll());
    }

    @GetMapping("/with-company")
    ResponseEntity<List<ServiceWithGroupingWithCompanyRelationList>> getAllServicesWithCompanies() {
        return ResponseEntity.ok(serviceRepository.getAllWithCompanies());
    }

    @GetMapping("/grouping/{id}")
    ResponseEntity<List<Company>> getAllCompaniesForGrouping(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceRepository.getAllCompaniesForGrouping(id));
    }
}
