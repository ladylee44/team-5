package com.smartosc.team5.controllers;


import com.smartosc.team5.dto.OrderDTO;
import com.smartosc.team5.dto.OrderdetailDTO;
import com.smartosc.team5.repositories.OrderRepository;
import com.smartosc.team5.services.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * team5
 *
 * @author Huupd
 * @created_at 08/06/2020 - 2:39 PM
 * @created_by Huupd
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = OrderController.class)
public class OrderControllerTest {
    private MockMvc  mockMvc;

    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(orderController)
                .setControllerAdvice(new Exception())
                .addFilters(new CORSFilter())
                .build();
    }

    @Test
    @DisplayName("Test GetAllOrders()")
    public void testGetAllOrder(){
        List<OrderdetailDTO> orderdetailDTOList1 = new ArrayList<>();
        List<OrderdetailDTO> orderdetailDTOList2 = new ArrayList<>();

        List<OrderDTO> orderDTOList = Arrays.asList(new OrderDTO(1,999,orderdetailDTOList1),
                                                    new OrderDTO(2,999,orderdetailDTOList2));

        when(orderService.getAllOrder()).thenReturn(orderDTOList);
        try {
            mockMvc.perform(get("/api/orders"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$",hasSize(2)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testGetOrderbyId() throws Exception {
        List<OrderdetailDTO> orderdetailDTOList1 = new ArrayList<>();
        OrderDTO ordersDTO = new OrderDTO(1, 123, orderdetailDTOList1);
        when(orderService.findOderById(anyInt())).thenReturn(java.util.Optional.of(ordersDTO));

        mockMvc.perform(get("/api/orders/", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].ordersId", is(1)))
                .andExpect(jsonPath("$[1].totalPrice", is(123.0)));
    }


}
