package com.ouhami.controle_spring.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ouhami.controle_spring.beans.Employee;
import com.ouhami.controle_spring.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> findEmployeesAndChiefsByService(Integer serviceId) {
        List<Employee> employees = employeeRepository.findByServiceId(serviceId);

        // Ensure that service and chief information is loaded
        employees.forEach(employee -> {
            // Ensure service information is loaded
            if (employee.getService() != null) {
                employee.getService().getName();
            }

            // Ensure chief information is loaded
            if (employee.getChief() != null) {
                employee.getChief().getNom();
            }
        });

        // Select one chief as "Chef de service" and remove that chief from the list of collaborators
        if (!employees.isEmpty()) {
            Employee collaborator = employees.get(0); // Choose the first collaborator for demonstration

            if (collaborator.getChief() != null) {
                // Set the chosen collaborator's chief as the "Chef de service"
                Employee chefDeService = collaborator.getChief();

                employees.forEach(employee -> {
                    if (employee.equals(collaborator)) {
                        employee.setChief(null); // Remove the chief from the collaborator
                    } else {
                        employee.setChief(chefDeService);
                    }
                });
            }
        }

        return employees;
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Employee newEmployee, Integer id) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        if (existingEmployee != null) {
            existingEmployee.setNom(newEmployee.getNom());
            existingEmployee.setPrenom(newEmployee.getPrenom());
            existingEmployee.setDateNaissance(newEmployee.getDateNaissance());
            existingEmployee.setPhoto(newEmployee.getPhoto());
            existingEmployee.setService(newEmployee.getService());
            existingEmployee.setChief(newEmployee.getChief());

            // Add more updates as needed

            return employeeRepository.save(existingEmployee);
        }

        return null; // Return null if the entity with the given id is not found
    }

    public boolean deleteAll() {
        try {
            employeeRepository.deleteAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(Integer id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
