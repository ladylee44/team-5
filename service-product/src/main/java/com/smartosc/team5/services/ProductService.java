package com.smartosc.team5.services;

import com.smartosc.common.dto.ProductDTO;
import com.smartosc.team5.converts.Convert;
import com.smartosc.team5.entities.Product;
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
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts(){
        log.info("Get all products");
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        productList.forEach(p->{
            ProductDTO productDTO = Convert.convertProductToDTO(p);
            productDTOList.add(productDTO);
        });
        return productDTOList;
    }

    public ProductDTO addProduct(ProductDTO productDTO){
        log.info("Create a new product");
        Product productCreate = productRepository.save(Convert.convertProductDTOtoProduct(productDTO));
        productDTO.setProductId(productCreate.getProductId());
        return productDTO;
    }

    public ProductDTO findById(int id){
        log.info("Find product by Id");
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            return Convert.convertProductToDTO(productOptional.get());
        } else
        return null;
    }

    public ProductDTO updateProduct(ProductDTO productDTO, Integer id){
        log.info("Update product");
        ProductDTO updateProduct = findById(id);
        Product product = Convert.convertProductDTOtoProduct(productDTO);
        productRepository.save(product);
        updateProduct.setProductId(productDTO.getProductId());
        return productDTO;
    }

    public void deleteProduct(int id) {
        log.info("Delete product");
        productRepository.deleteById(id);
    }
}
