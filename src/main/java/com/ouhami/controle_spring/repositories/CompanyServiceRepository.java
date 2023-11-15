package com.ouhami.controle_spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ouhami.controle_spring.beans.CompanyService;


@Repository
public interface CompanyServiceRepository extends JpaRepository<CompanyService, Integer> {
    // Additional query methods can be added if needed
}
