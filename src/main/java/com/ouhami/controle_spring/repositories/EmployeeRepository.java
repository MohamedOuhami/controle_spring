package com.ouhami.controle_spring.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ouhami.controle_spring.beans.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByServiceId(Integer serviceId);

}
