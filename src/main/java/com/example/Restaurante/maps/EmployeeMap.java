package com.example.Restaurante.maps;

import com.example.Restaurante.dtos.ClaimResponseDTO;
import com.example.Restaurante.dtos.EmployeeResponseDTO;
import com.example.Restaurante.entities.Claim;
import com.example.Restaurante.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMap {

        @Mapping(target = "id", source = "id")

        @Mapping(target = "orders", source = "orders")




    public EmployeeResponseDTO listEmployee (Employee employee);
    public List<EmployeeResponseDTO> listEmployees(List<Employee> employeeList);

}
