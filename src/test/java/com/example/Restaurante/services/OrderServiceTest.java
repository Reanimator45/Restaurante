package com.example.Restaurante.services;

import static org.mockito.Mockito.*;

import com.example.Restaurante.entities.Menu;
import com.example.Restaurante.entities.Order;
import com.example.Restaurante.entities.OrderDetail;
import com.example.Restaurante.maps.MenuMap;
import com.example.Restaurante.maps.OrderMap;
import com.example.Restaurante.repositories.MenuRepository;
import com.example.Restaurante.repositories.OrderRepository;
import com.example.Restaurante.util.OrderState;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepositoryMock;

    @Mock
    private MenuRepository menuRepositoryMock;

    @Mock
    private OrderMap orderMapMock;

    @Mock
    private MenuMap menuMapMock;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService(orderMapMock, orderRepositoryMock, menuRepositoryMock, menuMapMock);
    }

    @Test
    public void testCrearPedido() throws Exception {
        // Crea un objeto Order de prueba
        Order order = new Order();
        OrderDetail detail = new OrderDetail();
        detail.setPlate(new Menu());
        order.setDetails(List.of(detail));

        // Define el comportamiento de los mocks
        when(menuRepositoryMock.findById(anyInt())).thenReturn(Optional.of(new Menu()));
        when(orderRepositoryMock.save(any(Order.class))).thenReturn(new Order());

        // Realiza la llamada al método y verifica
        orderService.crearPedido(order);

        // Verifica que se haya llamado al método save del repository
        verify(orderRepositoryMock, times(1)).save(any(Order.class));
    }

    @Test
    public void testObtenerListaPedidosPorEstadoYSede() throws Exception {
        // Define el comportamiento de los mocks
        when(orderRepositoryMock.findByLocalAndStatus(anyString(), any(OrderState.class), any(Pageable.class)))
                .thenReturn(Page.empty());

        // Realiza la llamada al método
        orderService.obtenerListaPedidosPorEstadoYSede('A', "Local", OrderState.IN_PROCESS, 10);

        // Verifica que se haya llamado al método findByLocalAndStatus del repository
        verify(orderRepositoryMock, times(1)).findByLocalAndStatus(anyString(), any(OrderState.class), any(Pageable.class));
    }

    @Test
    public void testActualizarPedidoAEnPreparacion() throws Exception {
        // Crea un objeto Order de prueba
        Order order = new Order();
        order.setStatus(OrderState.PENDING);

        // Define el comportamiento de los mocks
        when(orderRepositoryMock.findById(anyInt())).thenReturn(Optional.of(order));
        when(orderRepositoryMock.save(any(Order.class))).thenReturn(order);

        // Realiza la llamada al método y verifica
        orderService.actualizarPedidoAEnPreparacion(1, order);

        // Verifica que se haya llamado al método save del repository
        verify(orderRepositoryMock, times(1)).save(any(Order.class));
    }

    @Test
    public void testObtainMenuLocalCategory() throws Exception {
        // Define el comportamiento de los mocks
        when(menuRepositoryMock.findByCategoryAndLocal(anyString(), anyString(), any(Pageable.class)))
                .thenReturn(Page.empty());

        // Realiza la llamada al método
        orderService.obtainMenuLocalCategory("Category", "Local", 10);

        // Verifica que se haya llamado al método findByCategoryAndLocal del repository
        verify(menuRepositoryMock, times(1)).findByCategoryAndLocal(anyString(), anyString(), any(Pageable.class));
    }
}