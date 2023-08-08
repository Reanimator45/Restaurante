package com.example.Restaurante.repositories;


import com.example.Restaurante.entities.Order;
import com.example.Restaurante.util.OrderState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository

public interface OrderRepository extends JpaRepository<Order, Integer> {


    Page<Order> findByLocalAndStatus(String local, OrderState status, Pageable pager);

    Page<Order> findByStatus(OrderState status, Pageable pager);
}

