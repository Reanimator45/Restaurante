package com.example.Restaurante.dtos;

import com.example.Restaurante.entities.Order;

import java.util.List;

public class EmployeeResponseDTO extends EmployeeDTO {

    private Integer id;
    private List<Order> orders;

    public EmployeeResponseDTO() {
    }

    public EmployeeResponseDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
