package com.example.Restaurante.entities;

import com.example.Restaurante.util.OrderState;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
   @JsonBackReference//para listar = poner numeros
    @JoinColumn(name = "EmployeeId")
   private Employee employeeid = null;



    public Integer getOrder() {
        return id;
    }

    public void setOrder(Integer order) {
        this.id = order;
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

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderState getStatus() {
        return status;
    }

    public void setStatus(OrderState status) {
        this.status = status;
    }

    public Employee getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Employee employeeid) {
        this.employeeid = employeeid;
    }

    public Order() {
    }


    public Order(Integer id, Character role, String local, OrderState status, List<OrderDetail> details) {
        this.id = id;
        this.role = role;
        this.local = local;
        this.status = status;
        this.details = details;
    }

    public Order(Employee employeeid) {
        this.employeeid = employeeid;
    }
}