package com.example.Restaurante.services;

import com.example.Restaurante.dtos.OrderResponseDTO;
import com.example.Restaurante.entities.Employee;
import com.example.Restaurante.entities.Order;
import com.example.Restaurante.maps.OrderMap;
import com.example.Restaurante.repositories.EmployeeRepository;
import com.example.Restaurante.repositories.OrderRepository;
import com.example.Restaurante.util.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    OrderRepository  orderRepository;

    @Autowired
    EmployeeRepository employeeRepository;




    @Autowired
    OrderMap orderMap;


    public Employee createEmployee(Employee dataEmployee)throws Exception{
        try {

            return employeeRepository.save(dataEmployee);

        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public Page<OrderResponseDTO> obtenerListaPedidosPorEstadoYSede(Character rol, String local, OrderState status, int numerodeRegistros) throws Exception{
        try{


            Pageable pager = PageRequest.of(0, numerodeRegistros);
            Page<Order> ordersPaged= orderRepository.findByLocalAndStatus(local,status,pager);
            return ordersPaged.map(order -> orderMap.transformOrder(order));
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }


    public Order asignarEmpleadoAPedido(Integer idOrder, Integer idEmployee) throws Exception {
        try {
            Optional<Order> orderOptional = orderRepository.findById(idOrder);
            Optional<Employee> optionalEmployee = employeeRepository.findById(idEmployee);
            Order order = null;
            if (orderOptional.isPresent() && optionalEmployee.isPresent()) {
                order = orderOptional.get();
                Employee employee = optionalEmployee.get();
                order.setEmployeeAsigned(employee);
            }
            return orderRepository.save(order);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }




}
