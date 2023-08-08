package com.example.Restaurante.repositories;

import com.example.Restaurante.entities.Employee;
import com.example.Restaurante.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmployeeId (Integer employeeId);

}
