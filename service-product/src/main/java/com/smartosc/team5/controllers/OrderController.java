package com.smartosc.team5.controllers;


import com.smartosc.team5.constant.ConstantVariables;
import com.smartosc.team5.dto.APIResponse;
import com.smartosc.team5.dto.OrderDTO;
import com.smartosc.team5.entities.Order;

import com.smartosc.team5.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
    public ResponseEntity<APIResponse<List<OrderDTO>>> getAllOrder() {
        List<OrderDTO> orderDTOList = orderService.getAllOrder();
        LOGGER.info("Get all order");
        APIResponse<List<OrderDTO>> listAPIResponse = new APIResponse<>();
        listAPIResponse.setData(orderDTOList);
        listAPIResponse.setMessage(ConstantVariables.SUCCESS);
        listAPIResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(listAPIResponse, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<APIResponse<Optional<OrderDTO>>> getOrderById(@PathVariable("orderId") int orderId) {
        Optional<OrderDTO> ordersDTO = orderService.findOderById(orderId);
        LOGGER.info("Get order by id success:");
        APIResponse<Optional<OrderDTO>> optionalAPIResponse = new APIResponse<>();
        optionalAPIResponse.setStatus(HttpStatus.OK.value());
        optionalAPIResponse.setMessage(ConstantVariables.SUCCESS);
        optionalAPIResponse.setData(ordersDTO);
        return new ResponseEntity<>(optionalAPIResponse, HttpStatus.OK);
    }

    /**
     * Create new order
     */
    @PostMapping()
    public ResponseEntity<APIResponse<OrderDTO>> createOrder(@RequestBody OrderDTO orderDTO) {
        APIResponse<OrderDTO> orderAPIResponse = new APIResponse<>();
        orderAPIResponse.setData(orderService.createOrder(orderDTO));
        orderAPIResponse.setMessage(ConstantVariables.SUCCESS);
        orderAPIResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(orderAPIResponse, HttpStatus.OK);
    }

    /**
     * Update order status
     */
    @PutMapping()
    public ResponseEntity<APIResponse<Optional<Order>>> changeOrderStatus(@RequestBody OrderDTO orderDTO) {
        APIResponse<Optional<Order>> apiResponse = new APIResponse<>();
        apiResponse.setData(orderService.changeOrderStatus(orderDTO));
        apiResponse.setMessage(ConstantVariables.SUCCESS);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Cancel order
     */
    @GetMapping("/cancel/{orderId}")
    public ResponseEntity<APIResponse<Order>> cancelOrder(@PathVariable("orderId") Integer orderId) {
        APIResponse<Order> orderAPIResponse = new APIResponse<>();
        orderAPIResponse.setData(orderService.cancelOrderStatus(orderId));
        orderAPIResponse.setMessage(ConstantVariables.SUCCESS);
        orderAPIResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(orderAPIResponse, HttpStatus.OK);
    }

}