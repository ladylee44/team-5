package com.smartosc.team5.services;

import com.smartosc.team5.dto.OrderDTO;
import com.smartosc.team5.entities.Order;

import java.util.List;
import java.util.Optional;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 22/06/2020 - 03:22 PM
 * @created_by ThaoPhuong
 * @since 22/06/2020
 */
public interface OrderService {
    List<OrderDTO> getAllOrder();
    Optional<OrderDTO> findOderById(Integer id);
    OrderDTO createOrder(OrderDTO orderDTO);
    Optional<Order> changeOrderStatus(OrderDTO orderDTO);
    boolean cancelOrderStatus(Integer orderId);
}
