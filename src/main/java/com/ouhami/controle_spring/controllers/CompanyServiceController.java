package com.ouhami.controle_spring.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ouhami.controle_spring.beans.CompanyService;
import com.ouhami.controle_spring.services.CompanyServiceService;


@RestController
@RequestMapping("/api/v1/company-services")
public class CompanyServiceController {

    @Autowired
    private CompanyServiceService companyServiceService;

    @GetMapping
    public List<CompanyService> findAll() {
        return companyServiceService.findAll();
    }

    @GetMapping("/{id}")
    public CompanyService findById(@PathVariable Integer id) {
        return companyServiceService.findById(id);
    }

    @PostMapping("/create")
    public CompanyService create(@RequestBody CompanyService companyService) {
        return companyServiceService.create(companyService);
    }

    @PutMapping("/update/{id}")
    public CompanyService update(@RequestBody CompanyService newCompanyService, @PathVariable Integer id) {
        return companyServiceService.update(newCompanyService, id);
    }

    @DeleteMapping("/delete")
    public boolean deleteAll() {
        return companyServiceService.deleteAll();
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return companyServiceService.deleteById(id);
    }
}
