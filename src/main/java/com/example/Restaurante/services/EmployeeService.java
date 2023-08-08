package com.example.Restaurante.services;

import com.example.Restaurante.dtos.EmployeeResponseDTO;
import com.example.Restaurante.dtos.MenuResponseDTO;
import com.example.Restaurante.entities.Employee;
import com.example.Restaurante.entities.Menu;
import com.example.Restaurante.maps.EmployeeMap;
import com.example.Restaurante.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
   private EmployeeMap employeeMap;

    @Autowired
    private EmployeeRepository employeeRepository;


    public EmployeeResponseDTO save(Employee employee)throws Exception{
        try {

            return employeeMap.listEmployee (employeeRepository.save(employee));

        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public List<EmployeeResponseDTO> findAll()throws Exception{

        try {
            return employeeMap.listEmployees(employeeRepository.findAll());
        }catch (Exception Error){
            throw new Exception(Error.getMessage());

        }

    }


}
