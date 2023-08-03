package com.example.Restaurante.validations;


import com.example.Restaurante.entities.Order;

public class OrderValidation {

    public  static Boolean validarPresenicaSede(Order order){
        return  order.getLocal() == null || order.getLocal().isEmpty() ||
                order.getDetails() == null || order.getDetails().isEmpty();

    }





}