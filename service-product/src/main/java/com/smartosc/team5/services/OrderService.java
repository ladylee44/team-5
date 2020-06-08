package com.smartosc.team5.services;

import com.smartosc.common.dto.OrderDTO;
import com.smartosc.common.dto.OrderdetailDTO;
import com.smartosc.common.dto.ProductDTO;
import com.smartosc.team5.converts.Convert;
import com.smartosc.team5.converts.OrderConvert;
import com.smartosc.team5.converts.OrderDetailConvert;
import com.smartosc.team5.entities.Order;
import com.smartosc.team5.entities.OrderDetail;
import com.smartosc.team5.entities.Product;
import com.smartosc.team5.repositories.OrderDetailRepository;
import com.smartosc.team5.repositories.OrderRepository;
import com.smartosc.team5.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProductRepository productRepository;
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    /**
     * Get all order
     */
    public List<OrderDTO> getAllOrder() {
        log.info("Get all list order");
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
    public Optional<OrderDTO> findOderById(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        OrderDTO orderDTO = new OrderDTO();
        if (orderOptional.isPresent()) {
            List<OrderdetailDTO> orderdetailDTOList = new ArrayList<>();
            List<OrderDetail> orderDetailList = orderOptional.get().getOrderDetailEntities();
            orderDTO = OrderConvert.convertEntityToDTO(orderOptional.get());
            orderDetailList.forEach(orderDetail -> {
                OrderdetailDTO orderdetailDTO = OrderDetailConvert.convertEntitytoDTO(orderDetail);
                Product product = orderDetail.getProduct();
                ProductDTO productDTO = Convert.convertProductToDTO(product);
                orderdetailDTOList.add(orderdetailDTO);
            });
            log.info("find order by id :{}", id);
            orderDTO.setOrderDetailEntities(orderdetailDTOList);
            return Optional.of(orderDTO);
        } else {
            log.info("find order fail ");
            return Optional.empty();
        }
    }

    /**
     * Create order
     */
    public OrderDTO createOrder(OrderDTO orderDTO) {
        try {
            Order order = OrderConvert.convertDTOtoEntity(orderDTO);
            Order createOrder = orderRepository.save(order);
            List<OrderDetail> orderDetailList = new ArrayList<>();
            List<OrderdetailDTO> orderdetailDTOList = orderDTO.getOrderDetailEntities();
            orderdetailDTOList.forEach(orderdetailDTO -> {
                OrderDetail orderDetail = OrderDetailConvert.convertDTOtoEntity(orderdetailDTO);
                Optional<Product> product = productRepository.findById(orderDetail.getProduct().getProductId());
                if (product.isPresent()) {
                    orderDetail.setProduct(product.get());
                    orderDetail.setPrice(product.get().getPrice() * orderDetail.getQuantity());
                    orderDetailList.add(orderDetail);
                    orderDetail.setOrders(order);
                    orderDetailRepository.save(orderDetail);
                }

            });
            log.info("Create order success");
            orderDTO.setOrdersId(order.getOrderId());
            return orderDTO;
        } catch (Exception e) {
            log.error("create fail with error::", e.getMessage());
            return null;
        }

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