package com.ouhami.controle_spring.beans;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String photo;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private CompanyService service;

    @ManyToOne
    @JoinColumn(name = "chief_id")
    private Employee chief;

    public String getServiceName() {
        return service != null ? service.getName() : null;
    }

    public String getChiefName() {
        return chief != null ? chief.getNom() : null;
    }

    // Constructors, getters, setters, and other methods if needed
}
