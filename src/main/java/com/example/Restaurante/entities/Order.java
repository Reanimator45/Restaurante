package com.example.Restaurante.entities;

import com.example.Restaurante.util.OrderState;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "role", nullable = false)
    private Character role;

    @Column(name = "local", nullable = false)
    private String local;

    @Enumerated(EnumType.STRING)
    @Column(name="stateorder")
    private OrderState status=OrderState.PENDING;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderDetail> details;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeAsigned;

    public Order() {
    }

    public Order(Integer id, Character role, String local, OrderState status, List<OrderDetail> details, Employee employeeAsigned) {
        this.id = id;
        this.role = role;
        this.local = local;
        this.status = status;
        this.details = details;
        this.employeeAsigned = employeeAsigned;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getRole() {
        return role;
    }

    public void setRole(Character role) {
        this.role = role;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public OrderState getStatus() {
        return status;
    }

    public void setStatus(OrderState status) {
        this.status = status;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    public Employee getEmployeeAsigned() {
        return employeeAsigned;
    }

    public void setEmployeeAsigned(Employee employeeAsigned) {
        this.employeeAsigned = employeeAsigned;
    }
}