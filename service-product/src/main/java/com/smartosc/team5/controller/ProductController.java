package com.smartosc.team5.controller;

import com.smartosc.common.dto.ProductDTO;
import com.smartosc.team5.entities.Product;
import com.smartosc.team5.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 04/06/2020 - 04:58 PM
 * @created_by ThaoPhuong
 * @since 04/06/2020
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOList = productService.getAllProducts();
        if (productDTOList.isEmpty()) {
            LOGGER.info("No product found");
            return ResponseEntity.noContent().build();
        }
        LOGGER.info("Get all products");
        return ResponseEntity.ok().body(productDTOList);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO productCreate = productService.addProduct(productDTO);
        return ResponseEntity.ok().body(productCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable(value = "id") int id) {
        ProductDTO updateProduct = productService.updateProduct(productDTO, id);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable(value = "id") Integer id) {
        productService.deleteProduct(id);
        LOGGER.info("Delete product");
        return ResponseEntity.ok().build();
    }
}
