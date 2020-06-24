package com.smartosc.team5.controllers;

import com.smartosc.team5.dto.APIResponse;
import com.smartosc.team5.dto.ProductDTO;
import com.smartosc.team5.exception.ExistException;
import com.smartosc.team5.exception.NoContentException;
import com.smartosc.team5.exception.NotFoundException;
import com.smartosc.team5.services.ProductService;
import com.smartosc.team5.services.serviceImpl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    APIResponse apiResponse = new APIResponse();

    /**
     * get all product
     */
    @GetMapping
    public ResponseEntity<APIResponse<List<ProductDTO>>> getAllProducts() {
        List<ProductDTO> productDTOList = productService.getAllProducts();
        log.info("Get all products");
        apiResponse.setData(productDTOList);
        apiResponse.setMessage("Get all products");
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * get product by id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ProductDTO>> findProductById(@PathVariable(value = "id") Integer id) throws NotFoundException {
        log.info("Find product by id " + id);
        ProductDTO productDTO = productService.findById(id);
        apiResponse.setData(productDTO);
        apiResponse.setMessage("Find product by id " + id);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * create new product
     *
     * @param productDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) throws ExistException {
        log.info("Create new product");
        ProductDTO productCreate = productService.addProduct(productDTO);
        apiResponse.setMessage("Create new product");
        apiResponse.setData(productCreate);
        apiResponse.setStatus(HttpStatus.OK.value());
        log.info("Create new product successfully");
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    /**
     * update product
     *
     * @param productDTO
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO,
                                                    @PathVariable(value = "id") int id) throws ExistException, NotFoundException {
        log.info("Update product id " + id);
        ProductDTO updateProduct = productService.updateProduct(productDTO, id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Update product id " + id + " successfully");
        apiResponse.setData(updateProduct);
        log.info("Update product id " + id + " successfully");
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    /**
     * delete product
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable(value = "id") Integer id) throws NotFoundException {
        log.info("Delete product id " + id);
        productService.deleteProduct(id);
        apiResponse.setMessage("Delete product id " + id + " successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        log.info("Delete product successfully");
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }
}
