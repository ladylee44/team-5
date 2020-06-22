//package com.smartosc.team5.controllers;
//
//
//import com.smartosc.team5.abstracts.AbstractTest;
//import com.smartosc.team5.dto.OrderDTO;
//import com.smartosc.team5.dto.OrderdetailDTO;
//import com.smartosc.team5.entities.Order;
//import com.smartosc.team5.exception.NotFoundException;
//import com.smartosc.team5.repositories.OrderRepository;
//import com.smartosc.team5.services.OrderService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.core.Is.is;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
///**
// * team5
// *
// * @author Huupd
// * @created_at 08/06/2020 - 2:39 PM
// * @created_by Huupd
// */
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(value = OrderController.class)
//public class OrderControllerTest {
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private OrderController orderController;
//    @Mock
//    private OrderService orderService;
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(orderController)
//                .setControllerAdvice(new Exception())
//                .addFilters(new CORSFilter())
//                .build();
//    }
//
//    @Test
//    @DisplayName("Test GetAllOrders()")
//    public void testGetAllOrder() {
//        List<OrderdetailDTO> orderdetailDTOList1 = new ArrayList<>();
//        List<OrderdetailDTO> orderdetailDTOList2 = new ArrayList<>();
//
//        List<OrderDTO> orderDTOList = Arrays.asList(new OrderDTO(1, 999, orderdetailDTOList1),
//                new OrderDTO(2, 999, orderdetailDTOList2));
//
//        when(orderService.getAllOrder()).thenReturn(orderDTOList);
//        try {
//            mockMvc.perform(get("/api/orders"))
//                    .andExpect(status().isOk())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(jsonPath("$.*", hasSize(3)));
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    @DisplayName("Test GetAllOrders False()")
//    public void testGetAllOrderFalse() throws Exception {
//        List<OrderDTO> orderDTOList = new ArrayList<>();
//        when(orderService.getAllOrder()).thenReturn(orderDTOList);
//        mockMvc.perform(get("/api/orders"))
//                .andExpect(status().isNoContent());
//
//    }
//
//    @Test
//    public void testGetOrderbyId() throws Exception {
//        List<OrderdetailDTO> orderdetailDTOList1 = new ArrayList<>();
//        OrderDTO ordersDTO = new OrderDTO(1, 123, orderdetailDTOList1);
//        when(orderService.findOderById(anyInt())).thenReturn(java.util.Optional.of(ordersDTO));
//
//        mockMvc.perform(get("/api/orders/{id}", 1))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.data.ordersId", is(1)))
//                .andExpect(jsonPath("$.data.totalPrice", is(123.0)));
//    }
//
//    @Test
//    public void testGetOrderbyIdFail() throws Exception {
//        when(orderService.findOderById(anyInt())).thenReturn(Optional.empty());
//        mockMvc.perform(get("/api/orders/{id}", 111))
//                .andExpect(status().isNotFound());
//    }
//
//
//    @Test
//    public void testCreateOrderSuccess() throws Exception {
//        List<OrderdetailDTO> orderdetailDTOList1 = new ArrayList<>();
//        OrderDTO ordersDTO = new OrderDTO(1, 123, orderdetailDTOList1);
//        when(orderService.createOrder(any(OrderDTO.class))).thenReturn(ordersDTO);
//
//        mockMvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON)
//                .content(AbstractTest.mapToJson(ordersDTO)))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.log());
//    }
//
//    @Test
//    public void testCancelOrderStatusSuccess() throws Exception {
//        List<OrderdetailDTO> orderdetailDTOList1 = new ArrayList<>();
//        OrderDTO ordersDTO = new OrderDTO();
//        ordersDTO.setOrdersId(1);
//        ordersDTO.setTotalPrice(9999);
//        ordersDTO.setStatus(0);
//        ordersDTO.setOrderDetailEntities(orderdetailDTOList1);
//        when(orderService.cancelOrderStatus(anyInt())).thenReturn(true);
//
//        mockMvc.perform(get("/api/orders/cancel/{id}", 1))
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    public void testCancelOrderStatusFail() throws Exception {
//        when(orderService.cancelOrderStatus(anyInt())).thenReturn(false);
//
//        mockMvc.perform(get("/api/orders/cancel/{id}", 999))
//                .andExpect(status().isNotFound());
//    }
////
////    @Test
////    public void changeOrderStatus() throws Exception {
////        List<OrderdetailDTO> orderdetailDTOList1 = new ArrayList<>();
////        OrderDTO ordersDTO = new OrderDTO();
////        ordersDTO.setOrdersId(1);
////        ordersDTO.setTotalPrice(9999);
////        ordersDTO.setStatus(0);
////        ordersDTO.setOrderDetailEntities(orderdetailDTOList1);
////        when(orderService.findOderById(anyInt())).thenReturn(Optional.of(ordersDTO));
////        when(orderService.changeOrderStatus(any(OrderDTO.class))).thenReturn(Optional.of(Order.class));
////        mockMvc.perform(put("/api/orders/cancel/{id}", ordersDTO))
////                .andExpect(status().isNotFound());
////    }
//
//}
