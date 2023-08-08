package com.example.Restaurante.controllers;

import com.example.Restaurante.dtos.EmployeeDTO;
import com.example.Restaurante.dtos.EmployeeErrorDTO;
import com.example.Restaurante.dtos.EmployeeResponseDTO;
import com.example.Restaurante.entities.Employee;
import com.example.Restaurante.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("restauranteceiba/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/")
    public ResponseEntity<EmployeeDTO> save(@RequestBody Employee employee) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employee));
        } catch (Exception Error) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new EmployeeErrorDTO(Error.getMessage()));
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> findAll() throws Exception {
        try {
            return ResponseEntity.ok(new ArrayList<>(employeeService.findAll()));
        } catch (Exception e) {
            List<EmployeeDTO> employeeDTOS = new ArrayList<>();
            employeeDTOS.add(new EmployeeErrorDTO(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(employeeDTOS);
        }
    }


}
