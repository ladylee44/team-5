package com.smartosc.team5.controllers;

import com.smartosc.team5.abstracts.AbstractTest;
import com.smartosc.team5.dto.ProductDTO;
import com.smartosc.team5.exception.NotFoundException;
import com.smartosc.team5.services.serviceImpl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 08/06/2020 - 05:22 PM
 * @created_by ThaoPhuong
 * @since 08/06/2020
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProductControllerTest.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductServiceImpl productService;

    private List<ProductDTO> productDTOList;

    private ProductDTO productDTO;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);

        productDTO = new ProductDTO(1, "product", "product", "image", 123);

        productDTOList = new ArrayList<>();
        productDTOList = Arrays.asList(new ProductDTO(), new ProductDTO());

        mockMvc = MockMvcBuilders
                .standaloneSetup(productController)
                .setControllerAdvice(new Exception())
                .addFilter(new CORSFilter())
                .build();
    }

    @Test
    public void getAllProductSuccessTest() throws Exception {
        when(productService.getAllProducts()).thenReturn(productDTOList);
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", hasSize(2)));
    }

    @Test
    public void findByIdSuccessTest() throws Exception {
        when(productService.findById(anyInt())).thenReturn(productDTO);
        mockMvc.perform(get("/api/products/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.productId").value(1))
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void findByIdFailTest() throws Exception {
        when(productService.findById(anyInt())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/api/products/{id}", 123))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void addProductSuccessTest() throws Exception {
        when(productService.addProduct(any(ProductDTO.class))).thenReturn(productDTO);
//        convert the Java object into JSON string
        mockMvc.perform(post("/api/products").contentType(MediaType.APPLICATION_JSON)
                .content(AbstractTest.mapToJson(productDTO)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void addProductFailTest() throws Exception {
        when(productService.addProduct(any(ProductDTO.class))).thenReturn(null);
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void updateProductSuccessTest() throws Exception {
        when(productService.findById(anyInt())).thenReturn(productDTO);
        when(productService.updateProduct(productDTO, productDTO.getProductId())).thenReturn(productDTO);

        mockMvc.perform(put("/api/products/{id}", productDTO.getProductId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractTest.mapToJson(productDTO)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void deleteProductSuccessTest() throws Exception {
        when(productService.findById(anyInt())).thenReturn(productDTO);
        when(productService.deleteProduct(productDTO.getProductId())).thenReturn(false);

        mockMvc.perform(delete("/api/products/{id}", productDTO.getProductId()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log());
    }
}