package com.smartosc.team5.services;

import com.smartosc.team5.converts.ProductConvert;
import com.smartosc.team5.dto.ProductDTO;
import com.smartosc.team5.entities.Product;
import com.smartosc.team5.exception.ExistException;
import com.smartosc.team5.exception.NoContentException;
import com.smartosc.team5.exception.NotFoundException;
import com.smartosc.team5.repositories.ProductRepository;
import com.smartosc.team5.services.serviceImpl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 08/06/2020 - 10:42 AM
 * @created_by ThaoPhuong
 * @since 08/06/2020
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @InjectMocks
    //mock a class or interface
            ProductServiceImpl productService;

    /**
     * Đối tượng Repository sẽ được mock, chứ không phải bean trong context
     */
    @Mock
    ProductRepository productRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllProductsSuccessTestService() {
        List<Product> productList = Arrays.asList(new Product(), new Product(), new Product());
        when(productRepository.findAll()).thenReturn(productList);
        List<ProductDTO> productDTOList = productService.getAllProducts();
        assertEquals(3, productDTOList.size());
    }

    @Test(expected = NoContentException.class)
    public void getAllProductEmptyTestService() {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        List<ProductDTO> productDTOList = productService.getAllProducts();
        assertEquals(0, productDTOList.size());
    }

    @Test
    public void findProductByIdSuccessTestService() {
        Product product = new Product();
        product.setProductId(1);
        product.setName("Product 1");
        product.setDescription("Product Test");
        product.setImage("Product image");
        product.setPrice(123);
        product.setStatus(1);
        when(productRepository.findById((anyInt()))).thenReturn(Optional.of(product));
        ProductDTO productDTO = productService.findById(1);
        assertEquals(1, productDTO.getProductId());
    }

    @Test(expected = NotFoundException.class)
    public void findProductByIdFailTestService() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<ProductDTO> productDTO = Optional.ofNullable(productService.findById(123));
        assertEquals(Optional.empty(), productDTO);
    }

    @Test
    public void  createProductSuccessTestService() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("Thao Xinh Dep");
        productDTO.setPrice(123);
        when(productRepository.save(any(Product.class))).then((Answer<Product>) productAnswer -> {
            Product product1 = (Product) productAnswer.getArguments()[0];
            product1.setProductId(1);
            return product1;
        });
        assertEquals(0, productDTO.getProductId());
        ProductDTO createProduct = productService.addProduct(productDTO);
        assertNotNull(createProduct.getProductId());
        assertEquals(1, createProduct.getProductId());
    }

    @Test
    public void createProductFailTestService() {
        ProductDTO productDTO = new ProductDTO();
        when(productRepository.findByName(any())).thenThrow(ExistException.class);
        assertThrows(ExistException.class, () -> {
            productService.addProduct(productDTO);
        });
    }

    @Test
    public void updateProductSuccessTestService() {
        Product product = new Product();
        product.setProductId(1);
        product.setName("Product 1");
        product.setDescription("Product Test");
        product.setImage("Product image");
        product.setPrice(123);
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        ProductDTO productUpdate = productService.updateProduct(ProductConvert.convertProductToDTO(product), 1);
        assertEquals(1, productUpdate.getProductId());
    }

    @Test
    public void updateProductFailTestService() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(1);
        productDTO.setProductName("Product Name");
        productDTO.setDescription("Product description");
        productDTO.setImage("Product image");
        productDTO.setPrice(10);
        when(productRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            productService.updateProduct(productDTO, 123);
        });
    }

    @Test
    public void deleteProductSuccessTestService() {
        Product product = new Product();
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        boolean deleteProduct = productService.deleteProduct(1);
        assertEquals(true, deleteProduct);
    }
}
