package com.example.Restaurante.controllers;

import com.example.Restaurante.dtos.OrderErrorDTO;
import com.example.Restaurante.dtos.OrderResponseDTO;
import com.example.Restaurante.entities.Employee;
import com.example.Restaurante.entities.Order;
import com.example.Restaurante.services.EmployeeService;
import com.example.Restaurante.services.OrderService;
import com.example.Restaurante.util.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    OrderService orderService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> registrar(@RequestBody Employee employee){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(employeeService.createEmployee(employee));
        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> obtenerOrderPaginadosYFiltrados(
            @RequestParam() Character role,
            @RequestParam() OrderState category,
            @RequestParam() String local,
            @RequestParam() int numerodeRegistros
    ){
        try {
            Page<OrderResponseDTO> edidosPaginados = orderService.obtenerListaPedidosPorEstadoYSede(role, local, category, numerodeRegistros);
            List<OrderResponseDTO> listapedidos = edidosPaginados.getContent();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(listapedidos);
        }catch (Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }


    @PostMapping("/employee/{orderId}")
    public ResponseEntity<String> asignarEmpleado(
            @PathVariable Integer orderId,
            @RequestParam Integer idEmployee) {
        try {
            Order order = employeeService.asignarEmpleadoAPedido(orderId, idEmployee);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Empleado asignado correctamente al pedido");
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al asignar el empleado al pedido: " + error.getMessage());
        }
    }

}
