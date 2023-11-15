package com.ouhami.controle_spring.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ouhami.controle_spring.beans.Employee;
import com.ouhami.controle_spring.services.EmployeeService;


@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Integer id) {
        return employeeService.findById(id);
    }

    @GetMapping("/service/{serviceId}")
    public String findEmployeesAndChiefsByService(@PathVariable Integer serviceId) {
        List<Employee> employees = employeeService.findEmployeesAndChiefsByService(serviceId);
    
        StringBuilder output = new StringBuilder();
    
        // Build output for Chef de service
        output.append("Service: ").append(employees.get(0).getServiceName()).append("\n");

        String chief_name = employees.get(1).getChief().getNom() + " " + employees.get(1).getChief().getPrenom();
        output.append("Chef de service: ").append(chief_name).append("\n");

        
    
        // Build output for Collaborateurs excluding Chef de service
        output.append("Collaborateurs: \n");
        employees.stream()
                .skip(1) // Skip the first employee (Chef de service)
                .forEach(employee -> output.append(employee.getNom()).append(" ").append(employee.getPrenom()).append("\n"));
    
        return output.toString();
    }
    


    @PostMapping("/create")
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/update/{id}")
    public Employee update(@RequestBody Employee newEmployee, @PathVariable Integer id) {
        return employeeService.update(newEmployee, id);
    }

    @DeleteMapping("/delete")
    public boolean deleteAll() {
        return employeeService.deleteAll();
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return employeeService.deleteById(id);
    }
}
