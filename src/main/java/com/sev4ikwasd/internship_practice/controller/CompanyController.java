package com.sev4ikwasd.internship_practice.controller;

import com.sev4ikwasd.internship_practice.dto.AddCompanyRequest;
import com.sev4ikwasd.internship_practice.dto.UpdateCompanyRequest;
import com.sev4ikwasd.internship_practice.entity.Company;
import com.sev4ikwasd.internship_practice.repository.CompanyRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyRepository.getAllCompanies());
    }

    @GetMapping("/{id}")
    ResponseEntity<Company> getCompany(@PathVariable Integer id) {
        return ResponseEntity.ok(companyRepository.getCompany(id));
    }

    @PostMapping
    ResponseEntity<?> addCompany(@Valid @RequestBody AddCompanyRequest request) {
        companyRepository.addCompany(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<?> updateCompany(@Valid @RequestBody UpdateCompanyRequest request) {
        companyRepository.updateCompany(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
