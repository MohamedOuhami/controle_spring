package com.ouhami.controle_spring.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ouhami.controle_spring.beans.CompanyService;
import com.ouhami.controle_spring.repositories.CompanyServiceRepository;


@Service
public class CompanyServiceService {

    @Autowired
    private CompanyServiceRepository companyServiceRepository;

    public List<CompanyService> findAll() {
        return companyServiceRepository.findAll();
    }

    public CompanyService findById(Integer id) {
        return companyServiceRepository.findById(id).orElse(null);
    }

    public CompanyService create(CompanyService companyService) {
        return companyServiceRepository.save(companyService);
    }

    public CompanyService update(CompanyService newCompanyService, Integer id) {
        CompanyService existingCompanyService = companyServiceRepository.findById(id).orElse(null);

        if (existingCompanyService != null) {
            existingCompanyService.setName(newCompanyService.getName());
            // Add more updates as needed

            return companyServiceRepository.save(existingCompanyService);
        }

        return null; // Return null if the entity with the given id is not found
    }

    public boolean deleteAll() {
        try {
            companyServiceRepository.deleteAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(Integer id) {
        try {
            companyServiceRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
