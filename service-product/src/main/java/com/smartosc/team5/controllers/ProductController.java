package com.smartosc.team5.controllers;

import com.smartosc.team5.dto.ProductDTO;
import com.smartosc.team5.exception.ProductNotFoundException;
import com.smartosc.team5.service.ProductService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
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
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable(value = "id") Integer id) {
        log.info("Find product by id " + id);
        ProductDTO productDTO = productService.findById(id);
        if (productDTO.equals(null)) {
            log.error("Product not found");
            throw new ProductNotFoundException(id);
        } else {
            log.info("Find product by id successfully");
            return ResponseEntity.ok().body(productDTO);
        }
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
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable(value = "id") Integer id) {
        log.info("Delete product id " + id);
        if(productService.findById(id).equals(null)){
            log.info("Product not found");
            throw new ProductNotFoundException(id);
        } else {
            productService.deleteProduct(id);
            log.info("Delete product successfully");
            return ResponseEntity.ok().build();
        }


    }
}
