package com.smartosc.team5.controllers;

import com.smartosc.team5.constant.Constant;
import com.smartosc.team5.dto.OrderDTO;
import com.smartosc.team5.exception.NotFoundException;
import com.smartosc.team5.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 05/06/2020 - 02:51 PM
 * @created_by ThaoPhuong
 * @since 05/06/2020
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Get all order
     */
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrder() {
        List<OrderDTO> orderDTOList = orderService.getAllOrder();
        if (orderDTOList.isEmpty()) {
            LOGGER.info("Not found orders");
        }
        LOGGER.info("Get all order");
        return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Optional<OrderDTO>> getOrderById(@PathVariable("orderId") int orderId) throws NotFoundException {
        Optional<OrderDTO> ordersDTO = orderService.findOderById(orderId);
        if (ordersDTO.isPresent()) {
            LOGGER.info("Get order by id success:");
            return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
        }
        LOGGER.info("Not found id order");
        throw new NotFoundException(Constant.ORDER_NOT_FOUND + orderId);
    }

    /**
     * Create new order
     */
    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    /**
     * Update order status
     */
    @PutMapping()
    public ResponseEntity<OrderDTO> changeOrderStatus(@RequestBody OrderDTO orderDTO) {
        orderService.changeOrderStatus(orderDTO);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    /**
     * Cancel order
     */
    @GetMapping("/cancel")
    public ResponseEntity<Boolean> cancelOrder(@RequestParam("orderId") Integer orderId) {
        return new ResponseEntity<>(orderService.cancelOrderStatus(orderId), HttpStatus.OK);

    }

}