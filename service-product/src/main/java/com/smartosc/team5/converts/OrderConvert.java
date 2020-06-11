package com.smartosc.team5.converts;


import com.smartosc.team5.dto.OrderDTO;
import com.smartosc.team5.entities.Order;

/**
 * Team5
 *
 * @author Huupd
 * @created_at 04/06/2020 - 3:27 PM
 * @created_by Huupd
 */
public class OrderConvert {

    public static OrderDTO convertEntityToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrdersId(order.getOrderId());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setCreatedAt(order.getCreatedAt());
        orderDTO.setUpdatedAt(order.getUpdatedAt());
        return orderDTO;
    }

    public static Order convertDTOtoEntity(OrderDTO orderDTO){
        Order order = new Order();
        order.setOrderId(orderDTO.getOrdersId());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setStatus(orderDTO.getStatus());
        order.setCreatedAt(orderDTO.getCreatedAt());
        order.setUpdatedAt(orderDTO.getUpdatedAt());
        return order;
    }
}