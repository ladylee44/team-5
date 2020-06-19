package com.smartosc.team5.controllers;

import com.smartosc.team5.dto.ProductDTO;
import com.smartosc.team5.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/api/products")
@Validated
@Slf4j
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * get all product
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOList = productService.getAllProducts();
        log.info("Get all products");
        if (productDTOList.isEmpty()) {
            log.info("No product found");
            return ResponseEntity.noContent().build();
        }
        log.info("Get all products successfully");
        return ResponseEntity.ok().body(productDTOList);
    }

    /**
     * get product by id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable(value = "id") Integer id) {
        log.info("Find product by id " + id);
        ProductDTO productDTO = productService.findById(id);
        return ResponseEntity.ok().body(productDTO);
//        if (productDTO == null) {
//            log.error("Product not found");
//            throw new NotFoundException(ConstantVariables.PRODUCT_NOT_FOUND + id);
//        } else {
//            log.info("Find product by id successfully");
//            return ResponseEntity.ok().body(productDTO);
//        }
    }

    /**
     * create new product
     *
     * @param productDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        log.info("Create new product");
        ProductDTO productCreate = productService.addProduct(productDTO);
        log.info("Create new product successfully");
        return ResponseEntity.ok().body(productCreate);
    }

    /**
     * update product
     *
     * @param productDTO
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable(value = "id") int id) {
        log.info("Update product id " + id);
        ProductDTO updateProduct = productService.updateProduct(productDTO, id);
        log.info("Update product id " + id + " successfully");
        return ResponseEntity.ok(updateProduct);
    }

    /**
     * delete product
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")

    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") Integer id) {
        log.info("Delete product id " + id);
        if (productService.findById(id) == null) {
            return null;
        } else {
            productService.deleteProduct(id);
            log.info("Delete product successfully");
            return ResponseEntity.ok().body("Delete successfully");
        }
    }
}
