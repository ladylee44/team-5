package com.smartosc.team5.service;

import com.smartosc.team5.dto.OrderDTO;
import com.smartosc.team5.converts.OrderConvert;
import com.smartosc.team5.entities.Order;
import com.smartosc.team5.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 05/06/2020 - 02:49 PM
 * @created_by ThaoPhuong
 * @since 05/06/2020
 */
@Slf4j
@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Get all order
     */
    public List<OrderDTO> getAllOrder() {
        log.info("get all list order");
        List<Order> orderList = orderRepository.findAll();
        log.debug("List order {}", orderList);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orderList.forEach(order -> {
            OrderDTO orderDTO = OrderConvert.convertEntityToDTO(order);
            orderDTOList.add(orderDTO);
        });
        return orderDTOList;
    }

    /**
     * Find order by id
     */
    public OrderDTO findOderById(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return OrderConvert.convertEntityToDTO(orderOptional.get());
        }
        return null;
    }

    /**
     * Create order
     */
    public Order createOrder(OrderDTO orderDTO) {
        log.info("Create order");
        Order order = OrderConvert.convertDTOtoEntity(orderDTO);
        return orderRepository.save(order);
    }

    /**
     * Change order status
     */
    public void changeOrderStatus(OrderDTO orderDTO) {
        log.info("change order status");
        Optional<Order> orderOptional = orderRepository.findById(orderDTO.getOrdersId());
        if (orderOptional.isPresent()) {
            switch (orderDTO.getOrdersId()) {
                case 0:
                    orderOptional.get().setStatus(1);
                    break;
                case 1:
                    orderOptional.get().setStatus(2);
                    break;
                case 2:
                    orderOptional.get().setStatus(3);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Update order status
     */
    public boolean cancelOrderStatus(Integer orderId) {
        log.info("cancel order status");
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            if (orderOptional.get().getStatus() == 0) {
                orderOptional.get().setStatus(3);
                orderOptional.get().setUpdatedAt(new Date());
            } else if (orderOptional.get().getStatus() == 3) {
                orderOptional.get().setStatus(0);
                orderOptional.get().setUpdatedAt(new Date());
            }
            if (orderOptional.get() != null) {
                orderRepository.save(orderOptional.get());
                return true;
            }
        }
        return false;
    }

}