//package com.smartosc.team5.services;
//
//
//import com.smartosc.team5.converts.OrderConvert;
//import com.smartosc.team5.converts.OrderDetailConvert;
//import com.smartosc.team5.converts.ProductConvert;
//import com.smartosc.team5.dto.OrderDTO;
//import com.smartosc.team5.dto.OrderdetailDTO;
//import com.smartosc.team5.dto.ProductDTO;
//import com.smartosc.team5.entities.Order;
//import com.smartosc.team5.entities.OrderDetail;
//import com.smartosc.team5.entities.Product;
//import com.smartosc.team5.repositories.OrderDetailRepository;
//import com.smartosc.team5.repositories.OrderRepository;
//import com.smartosc.team5.repositories.ProductRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.*;
//
///**
// * team5
// *
// * @author Huupd
// * @created_at 05/06/2020 - 4:10 PM
// * @created_by Huupd
// */
//@RunWith(MockitoJUnitRunner.class)
//public class OrderServiceTest {
//    @InjectMocks
//    private OrderService orderService;
//    @Mock
//    private OrderRepository orderRepository;
//    @Mock
//    private OrderDetailRepository orderDetailRepository;
//    @Mock
//    ProductRepository productRepository;
//
//    @Before
//    public void init() {
//        //MockitoAnnotations.initMocks(this);
//    }
//
//    /**
//     * Test get all order service
//     */
//    @Test
//    public void testGetAllOrderServiceSuccess() {
//        List<Order> orderList = new ArrayList<>();
//        Order order1 = new Order();
//        Order order2 = new Order();
//        List<OrderDetail> orderDetailList = new ArrayList<>();
//        order1.setOrderDetailEntities(orderDetailList);
//        order2.setOrderDetailEntities(orderDetailList);
//        orderList.add(order1);
//        orderList.add(order2);
//
//        when(orderRepository.findAll()).thenReturn(orderList);
//
//        List<OrderDTO> orderDTOList = orderService.getAllOrder();
//
//        assertEquals(2, orderDTOList.size());
//    }
//
//    /**
//     * Test get order by id success
//     */
//    @Test
//    public void testGetOrderByIdSuccess() {
//        Product product = new Product();
//        List<OrderDetail> orderDetailList = new ArrayList<>();
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setProduct(product);
//        orderDetailList.add(orderDetail);
//
//        Order order = new Order();
//        order.setOrderId(1);
//        order.setTotalPrice(9999);
//        order.setStatus(1);
//        order.setOrderDetailEntities(orderDetailList);
//
//        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
//
//        Optional<OrderDTO> orderDTOptional = (orderService.findOderById(1));
//
//        assertEquals(1, orderDTOptional.get().getOrdersId());
//    }
//
//    @Test
//    public void testFindOrderByIdFail() {
//        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
//        Optional<OrderDTO> orderDTO = (orderService.findOderById(999));
//        assertEquals(Optional.empty(), orderDTO);
//    }
//
//    /**
//     * Test add order success
//     */
//    @Test
//    public void testCreateOrderSuccess() {
//        List<OrderdetailDTO> orderdetailDTOList = new ArrayList<>();
//        OrderdetailDTO orderdetailDTO = new OrderdetailDTO();
//        orderdetailDTO.setDeltailId(1);
//        orderdetailDTO.setAmount(1);
//
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setProductId(1);
//        productDTO.setProductName("PC");
//        productDTO.setDescription("Mô tả");
//        productDTO.setPrice(999);
//        productDTO.setImage("image");
//        orderdetailDTO.setProductDTO(productDTO);
//        double total = (productDTO.getPrice() * orderdetailDTO.getAmount());
//        orderdetailDTO.setPrice(total);
//        orderdetailDTOList.add(orderdetailDTO);
//
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setOrdersId(1);
//        orderDTO.setTotalPrice(999);
//        orderDTO.setStatus(1);
//        orderDTO.setOrderDetailEntities(orderdetailDTOList);
//
//        when(orderRepository.save(any(Order.class))).thenReturn(OrderConvert.convertDTOtoEntity(orderDTO));
//
//        when(orderDetailRepository.save(any(OrderDetail.class))).thenReturn(OrderDetailConvert.convertDTOtoEntity(orderdetailDTO));
//
//        when(productRepository.findById(anyInt())).thenReturn(Optional.of(ProductConvert.convertProductDTOtoProduct(productDTO)));
//
//        OrderDTO createOrder = orderService.createOrder(orderDTO);
//
//        assertNotNull(createOrder.getOrdersId());
//
//        assertEquals(1, createOrder.getOrdersId());
//    }
//
//    /**
//     * Test order fail
//     */
//    @Test(expected = NullPointerException.class)
//    public void testCreateOrderFail() {
//        OrderDTO orderDTO = new OrderDTO();
//
//        when(orderRepository.save(any(Order.class))).thenThrow(NullPointerException.class);
//
//        OrderDTO createOrder = orderService.createOrder(orderDTO);
//
//        assertEquals(NullPointerException.class, createOrder);
//    }
//
//
//    @Test
//    public void changeOrderStatus() {
//        //case 1:
//        List<OrderDetail> orderDetailList = new ArrayList<>();
//        List<OrderdetailDTO> orderdetailDTOS = new ArrayList<>();
//        Order orders3 = new Order();
//        orders3.setOrderId(1);
//        orders3.setTotalPrice(120.0);
//        orders3.setStatus(0);
//        orders3.setOrderDetailEntities(orderDetailList);
//        when(orderRepository.findById(1)).thenReturn(Optional.of(orders3));
//        OrderDTO orderDTO3 = new OrderDTO();
//        orderDTO3.setOrdersId(1);
//        orderDTO3.setTotalPrice(120.0);
//        orderDTO3.setStatus(0);
//        orderDTO3.setOrderDetailEntities(orderdetailDTOS);
//        orderService.changeOrderStatus(orderDTO3);
//        assertEquals(orders3.getStatus(), 1);
//        assertEquals(1, orderDTO3.getOrdersId());
//
//
//        //case 2:
//        Order orders = new Order();
//        orders.setOrderId(1);
//        orders.setTotalPrice(120.0);
//        orders.setStatus(1);
//        orders.setOrderDetailEntities(orderDetailList);
//
//        when(orderRepository.findById(1)).thenReturn(Optional.of(orders));
//
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setOrdersId(1);
//        orderDTO.setTotalPrice(120.0);
//        orderDTO.setStatus(1);
//        orderService.changeOrderStatus(orderDTO);
//        assertEquals(2, orders.getStatus());
//        assertEquals(1, orderDTO.getOrdersId());
//
//        //case 3:
//        Order orders1 = new Order();
//        orders1.setOrderId(1);
//        orders1.setTotalPrice(120.0);
//        orders1.setStatus(2);
//        orders1.setOrderDetailEntities(orderDetailList);
//
//        when(orderRepository.findById(1)).thenReturn(Optional.of(orders1));
//
//        OrderDTO orderDTO1 = new OrderDTO();
//        orderDTO1.setOrdersId(1);
//        orderDTO1.setTotalPrice(120.0);
//        orderDTO1.setStatus(2);
//        orderDTO1.setOrderDetailEntities(orderdetailDTOS);
//        orderService.changeOrderStatus(orderDTO1);
//        assertEquals(3, orders1.getStatus());
//        assertEquals(1, orderDTO1.getOrdersId());
//
//    }
//
//    @Test
//    public void changeOrderStatusFail() {
//        //case 1:
//        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
//        OrderDTO orderDTO3 = new OrderDTO();
//       Optional<Order> orderDTO =  orderService.changeOrderStatus(orderDTO3);
//        assertEquals(Optional.empty(), orderDTO);
//
//    }
//
//
//    @Test
//    public void testCancelOrderStatus() {
//        List<OrderDetail> orderDetailList = new ArrayList<>();
//        Order orders = new Order();
//        orders.setOrderId(1);
//        orders.setTotalPrice(120.0);
//        orders.setStatus(0);
//        orders.setOrderDetailEntities(orderDetailList);
//
//        when(orderRepository.findById(1)).thenReturn(Optional.of(orders));
//
//        boolean check1 = orderService.cancelOrderStatus(1);
//        assertEquals(true, check1);
//
//
//        //case2:
//        Order orders1 = new Order();
//        orders1.setOrderId(1);
//        orders1.setTotalPrice(120.0);
//        orders1.setStatus(3);
//        orders1.setOrderDetailEntities(orderDetailList);
//
//        when(orderRepository.findById(1)).thenReturn(Optional.of(orders1));
//
//        boolean check2 = orderService.cancelOrderStatus(1);
//        assertEquals(true, check2);
//
//
//        //case3:
//        Order orders2 = new Order();
//        orders1.setOrderId(1);
//        orders1.setTotalPrice(120.0);
//        orders1.setStatus(3);
//        orders1.setOrderDetailEntities(orderDetailList);
//        boolean check3 = orderService.cancelOrderStatus(455);
//        assertEquals(false, check3);
//
//
//    }
//
//
//    @Test
//    public void testCancelOrderFail(){
//        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
//        boolean check1 = orderService.cancelOrderStatus(999);
//        assertEquals(false, check1);
//    }
//}
