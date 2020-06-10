package com.smartosc.team5.controllers;

import com.smartosc.team5.abstracts.AbstractTest;
import com.smartosc.team5.dto.ProductDTO;
import com.smartosc.team5.exception.ProductNotFoundException;
import com.smartosc.team5.services.ProductService;
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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
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
    private ProductService productService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(productController)
                .build();
    }

    @Test
    public void getAllProductSuccessTest() throws Exception {
        List<ProductDTO> productDTOList = Arrays.asList(new ProductDTO(), new ProductDTO());
        when(productService.getAllProducts()).thenReturn(productDTOList);
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void getAllProductFailTest() throws Exception {
        when(productService.getAllProducts()).thenReturn(null);
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isNoContent())
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void findByIdSuccessTest() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(1);
        productDTO.setProductName("Product name");
        productDTO.setDescription("Product Description");
        productDTO.setImage("Product image");
        productDTO.setPrice(123);
        when(productService.findById(anyInt())).thenReturn(productDTO);
        mockMvc.perform(get("/api/products/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(1))
                .andDo(MockMvcResultHandlers.log());
    }

    @Test(expected = ProductNotFoundException.class)
    public void findByIdFailTest() throws Exception {
        when(productService.findById(anyInt())).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(get("/api/products/{id}", 123))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void addProductSuccessTest() throws Exception {
        ProductDTO productDTO = new ProductDTO(1, "product", "product", "image", 123);

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
    public void updateProductSuccess() throws Exception {
        ProductDTO productDTO = new ProductDTO(1, "product", "product", "image", 123);

        when(productService.findById(anyInt())).thenReturn(productDTO);
        when(productService.updateProduct(productDTO, productDTO.getProductId())).thenReturn(productDTO);

        mockMvc.perform(put("/api/products/{id}", productDTO.getProductId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractTest.mapToJson(productDTO)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log());
    }

    //test fail
    @Test
    public void updateProductFailTest() throws Exception {
        ProductDTO productDTO = new ProductDTO(1, "product", "product", "image", 123);

        when(productService.findById(anyInt())).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(put("/api/products/{id}", productDTO.getProductId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteProductSuccessTest() throws Exception {
        ProductDTO productDTO = new ProductDTO(1, "product", "product", "image", 123);
        when(productService.findById(anyInt())).thenReturn(productDTO);
        when(productService.deleteProduct(productDTO.getProductId())).thenReturn(true);

        mockMvc.perform(delete("/api/products/{id}", productDTO.getProductId()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log());
    }

    //test fail
    @Test(expected = ProductNotFoundException.class)
    public void deleteProductFailTest() throws Exception {
        ProductDTO productDTO = new ProductDTO(1, "product", "product", "image", 123);
        when(productService.findById(anyInt())).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(delete("/api/products/{id}",productDTO.getProductId()))
                .andExpect(status().isNotFound());
    }
}