package com.smartosc.team5.services;

import com.smartosc.team5.dto.ProductDTO;
import com.smartosc.team5.converts.ProductConvert;
import com.smartosc.team5.entities.Product;
import com.smartosc.team5.exception.ProductNotFoundException;
import com.smartosc.team5.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 04/06/2020 - 03:19 PM
 * @created_by ThaoPhuong
 * @since 04/06/2020
 */
@Slf4j
@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        log.info("Get all products");
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        if (productList != null) {
            productList.forEach(p -> {
                ProductDTO productDTO = ProductConvert.convertProductToDTO(p);
                productDTOList.add(productDTO);
            });
            log.info("Get all products success");
            return productDTOList;
        }
        log.info("Get all products null");
        return null;
    }

    public ProductDTO findById(int id) {
        log.info("Find product by id " + id);
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return ProductConvert.convertProductToDTO(productOptional.get());
        } else
            throw new ProductNotFoundException(id);
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        log.info("Create a new product");
        try {
            Product productCreate = productRepository.save(ProductConvert.convertProductDTOtoProduct(productDTO));
            productDTO.setProductId(productCreate.getProductId());
            log.info("Create a new product success");
            return productDTO;
        } catch (Exception e) {
            log.error("Create product fail");
            return null;
        }
    }

    public ProductDTO updateProduct(ProductDTO productDTO, Integer id) {
        log.info("Update product");
        try{
            ProductDTO updateProduct = findById(id);
            Product product = ProductConvert.convertProductDTOtoProduct(productDTO);
            productRepository.save(product);
            updateProduct.setProductId(productDTO.getProductId());
            log.info("Update product success");
            return productDTO;
        } catch (Exception e){
            log.error("Update product fail");
            return null;
        }
    }

    public void deleteProduct(int id) {
        log.info("Delete product");
        try{
            productRepository.deleteById(id);
        } catch (ProductNotFoundException e){

        }
    }
}
