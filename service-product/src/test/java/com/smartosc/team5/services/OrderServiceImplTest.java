package com.smartosc.team5.services;


import com.smartosc.team5.converts.OrderConvert;
import com.smartosc.team5.converts.OrderDetailConvert;
import com.smartosc.team5.converts.ProductConvert;
import com.smartosc.team5.dto.OrderDTO;
import com.smartosc.team5.dto.OrderdetailDTO;
import com.smartosc.team5.dto.ProductDTO;
import com.smartosc.team5.entities.Order;
import com.smartosc.team5.entities.OrderDetail;
import com.smartosc.team5.entities.Product;
import com.smartosc.team5.exception.NoContentException;
import com.smartosc.team5.exception.NotFoundException;
import com.smartosc.team5.repositories.OrderDetailRepository;
import com.smartosc.team5.repositories.OrderRepository;
import com.smartosc.team5.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
public class OrderServiceImplTest {
    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderDetailRepository orderDetailRepository;
    @Mock
    ProductRepository productRepository;

    @Before
    public void init() {

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

    @Test(expected = NoContentException.class)
    public void testGetAllOrderFail(){
        List<Order> orderList = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(orderList);

        List<OrderDTO> orderDTOList = orderService.getAllOrder();

        assertEquals(NoContentException.class, orderDTOList);
    }

    /**
     * Test get order by id success
     */
    @Test
    public void testGetOrderByIdSuccess() {
        Product product = new Product();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetailList.add(orderDetail);

        Order order = new Order();
        order.setOrderId(1);
        order.setTotalPrice(9999);
        order.setStatus(1);
        order.setOrderDetailEntities(orderDetailList);

        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        Optional<OrderDTO> orderDTOptional = (orderService.findOderById(1));

        assertEquals(1, orderDTOptional.get().getOrdersId());
    }


    @Test(expected = NotFoundException.class)
    public void testFindOrderByIdFail() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<OrderDTO> orderDTO = (orderService.findOderById(999));
        assertEquals(NotFoundException.class, orderDTO);
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
        orderDTO.setOrdersId(1);
        orderDTO.setTotalPrice(999);
        orderDTO.setStatus(1);
        orderDTO.setOrderDetailEntities(orderdetailDTOList);

        when(orderRepository.save(any(Order.class))).thenReturn(OrderConvert.convertDTOtoEntity(orderDTO));

        when(orderDetailRepository.save(any(OrderDetail.class))).thenReturn(OrderDetailConvert.convertDTOtoEntity(orderdetailDTO));

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(ProductConvert.convertProductDTOtoProduct(productDTO)));

        OrderDTO createOrder = orderService.createOrder(orderDTO);

        assertNotNull(createOrder.getOrdersId());

        assertEquals(1, createOrder.getOrdersId());
    }

    /**
     * Test order fail
     */
    @Test(expected = NoContentException.class)
    public void testCreateOrderFail() {
        OrderDTO orderDTO = new OrderDTO();

        when(orderRepository.save(any(Order.class))).thenThrow(NoContentException.class);

        OrderDTO createOrder = orderService.createOrder(orderDTO);

        assertEquals(NoContentException.class, createOrder);
    }


    @Test
    public void changeOrderStatusCase0() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<OrderdetailDTO> orderdetailDTOS = new ArrayList<>();
        Order orders = new Order();
        orders.setOrderId(1);
        orders.setTotalPrice(120.0);
        orders.setStatus(0);
        orders.setOrderDetailEntities(orderDetailList);
        when(orderRepository.findById(1)).thenReturn(Optional.of(orders));
        OrderDTO orderDTO3 = new OrderDTO();
        orderDTO3.setOrdersId(1);
        orderDTO3.setTotalPrice(120.0);
        orderDTO3.setStatus(0);
        orderDTO3.setOrderDetailEntities(orderdetailDTOS);
        orderService.changeOrderStatus(orderDTO3);
        assertEquals(orders.getOrderId(), 1);
        assertEquals(0, orderDTO3.getStatus());
    }

    @Test
    public void changeOrderStatusCase1() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<OrderdetailDTO> orderdetailDTOS = new ArrayList<>();
        Order orders = new Order();
        orders.setOrderId(1);
        orders.setTotalPrice(120.0);
        orders.setStatus(1);
        orders.setOrderDetailEntities(orderDetailList);
        when(orderRepository.findById(1)).thenReturn(Optional.of(orders));
        OrderDTO orderDTO3 = new OrderDTO();
        orderDTO3.setOrdersId(1);
        orderDTO3.setTotalPrice(120.0);
        orderDTO3.setStatus(1);
        orderDTO3.setOrderDetailEntities(orderdetailDTOS);
        orderService.changeOrderStatus(orderDTO3);
        assertEquals(orders.getStatus(), 1);
        assertEquals(1, orderDTO3.getStatus());
    }

    @Test
    public void changeOrderStatusCase2() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<OrderdetailDTO> orderdetailDTOS = new ArrayList<>();
        Order orders = new Order();
        orders.setOrderId(1);
        orders.setTotalPrice(120.0);
        orders.setStatus(2);
        orders.setOrderDetailEntities(orderDetailList);
        when(orderRepository.findById(1)).thenReturn(Optional.of(orders));
        OrderDTO orderDTO3 = new OrderDTO();
        orderDTO3.setOrdersId(1);
        orderDTO3.setTotalPrice(120.0);
        orderDTO3.setStatus(2);
        orderDTO3.setOrderDetailEntities(orderdetailDTOS);
        orderService.changeOrderStatus(orderDTO3);
        assertEquals(orders.getStatus(), 2);
        assertEquals(2, orderDTO3.getStatus());
    }

    @Test(expected = NotFoundException.class)
    public void changeOrderStatusFail() {
        //case 1:
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
        OrderDTO orderDTO3 = new OrderDTO();
       Optional<Order> orderDTO =  orderService.changeOrderStatus(orderDTO3);
        assertEquals(NotFoundException.class, orderDTO);
    }
    @Test
    public void testCancelOrderStatusSuccessWithStatus0() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Order orders = new Order();
        orders.setOrderId(1);
        orders.setTotalPrice(120.0);
        orders.setStatus(0);
        orders.setOrderDetailEntities(orderDetailList);

        when(orderRepository.findById(1)).thenReturn(Optional.of(orders));

        Order order = orderService.cancelOrderStatus(1);
        assertEquals(orders, order);
    }
    @Test
    public void testCancelOrderStatusSuccessWithStatus3() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Order orders = new Order();
        orders.setOrderId(1);
        orders.setTotalPrice(120.0);
        orders.setStatus(3);
        orders.setOrderDetailEntities(orderDetailList);

        when(orderRepository.findById(1)).thenReturn(Optional.of(orders));

        Order order = orderService.cancelOrderStatus(1);
        assertEquals(orders, order);
    }



    @Test(expected = NotFoundException.class)
    public void testCancelOrderFail(){
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
      orderService.cancelOrderStatus(999);
        assertEquals(NotFoundException.class, 0 );
    }
}
