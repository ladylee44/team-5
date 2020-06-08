package com.smartosc.team5.services;

import com.smartosc.common.dto.OrderDTO;
import com.smartosc.common.dto.OrderdetailDTO;
import com.smartosc.common.dto.ProductDTO;
import com.smartosc.team5.converts.Convert;
import com.smartosc.team5.entities.Order;
import com.smartosc.team5.entities.OrderDetail;
import com.smartosc.team5.repositories.OrderDetailRepository;
import com.smartosc.team5.repositories.OrderRepository;
import com.smartosc.team5.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * team5
 *
 * @author Huupd
 * @created_at 05/06/2020 - 4:10 PM
 * @created_by Huupd
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    //tạo class
    @InjectMocks
    private OrderService orderService;
    //gọi inject trong class
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderDetailRepository orderDetailRepository;
    @Mock
    ProductRepository productRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test get all order service
     */
    @Test
    public void testGetAllOrderServiceSuccess() {
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order();
        Order order2 = new Order();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        order1.setOrderDetailEntities(orderDetailList);
        order2.setOrderDetailEntities(orderDetailList);
        orderList.add(order1);
        orderList.add(order2);

        when(orderRepository.findAll()).thenReturn(orderList);

        List<OrderDTO> orderDTOList = orderService.getAllOrder();

        assertEquals(2, orderDTOList.size());
    }

    /**
     * Test get order by id success
     */
    @Test
    public void testGetOrderByIdSuccess() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Order order = new Order();
        order.setOrderId(1);
        order.setTotalPrice(9999);
        order.setStatus(1);
        order.setOrderDetailEntities(orderDetailList);

        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        Optional<OrderDTO> orderDTOptional = orderService.findOderById(1);

        assertEquals(1, orderDTOptional.get().getOrdersId());
    }

    @Test
    public void testFindOrderByIdFail() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<OrderDTO> orderDTO = orderService.findOderById(999);
        assertEquals(Optional.empty(), orderDTO);
    }

    /**
     * Test add order success
     */
    @Test
    public void testCreateOrderSuccess() {
        List<OrderdetailDTO> orderdetailDTOList = new ArrayList<>();
        OrderdetailDTO orderdetailDTO = new OrderdetailDTO();
        orderdetailDTO.setDeltailId(1);
        orderdetailDTO.setAmount(1);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(1);
        productDTO.setProductName("PC");
        productDTO.setDescription("Mô tả");
        productDTO.setPrice(999);
        productDTO.setImage("image");
        orderdetailDTO.setProductDTO(productDTO);
        double total = (productDTO.getPrice() * orderdetailDTO.getAmount());
        orderdetailDTO.setPrice(total);
        orderdetailDTOList.add(orderdetailDTO);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotalPrice(998);
        orderDTO.setStatus(1);
        orderDTO.setOrderDetailEntities(orderdetailDTOList);

        when(productRepository.findById(1)).thenReturn(Optional.of(Convert.convertProductDTOtoProduct(productDTO)));

        when(orderRepository.save(any(Order.class))).thenAnswer((Answer<Order>) invocation -> {
            Order order = (Order) invocation.getArguments()[0];
            order.setOrderId(1);
            return order;
        });

        when(orderDetailRepository.save(any(OrderDetail.class))).thenAnswer((Answer<OrderDetail>) invocation -> {
            OrderDetail orderDetail = (OrderDetail) invocation.getArguments()[0];
            orderDetail.setDeltailId(1);
            return orderDetail;
        });

        assertEquals(0, orderDTO.getOrdersId());

        OrderDTO createOrder = orderService.createOrder(orderDTO);

        assertNotNull(createOrder.getOrdersId());

        assertEquals(1, createOrder.getOrdersId());
    }

    /**
     * Test order fail
     */
    @Test(expected = Exception.class)
    public void testCreateOrderFail() {
        OrderDTO orderDTO = new OrderDTO();

        when(orderRepository.save(any(Order.class))).thenThrow(Exception.class);

        OrderDTO createOrder = orderService.createOrder(orderDTO);


    }


}
