package com.example.Restaurante.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.mapstruct.Mapper;

import java.util.List;

@Entity
@Table(name="Employee")
public class Employee {

    //cambiar el nombre de las columnas, dtos, maps TODOOSS

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employee_Name")
    private String name;

    @OneToMany(mappedBy = "employeeId")
    @JsonManagedReference//para evitar el bucle.
    @JsonIgnore
    private List<Order> orders;


    public Employee() {
    }

    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}