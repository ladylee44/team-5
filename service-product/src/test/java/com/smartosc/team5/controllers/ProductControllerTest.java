package com.smartosc.team5.controllers;

import com.smartosc.team5.dto.ProductDTO;
import com.smartosc.team5.repositories.ProductRepository;
import com.smartosc.team5.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 08/06/2020 - 05:22 PM
 * @created_by ThaoPhuong
 * @since 08/06/2020
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllProductTest() {

    }
}
