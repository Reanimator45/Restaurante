package com.example.Restaurante.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "plate_id", nullable = false)
    private Menu plate;

    @Column(name="quantity", nullable = false)
    private Integer quantity;


    public OrderDetail() {
    }

    public OrderDetail(Integer id, Menu plate, Integer quantity) {
        this.id = id;
        this.plate = plate;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Menu getPlate() {
        return plate;
    }

    public void setPlate(Menu plate) {
        this.plate = plate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
