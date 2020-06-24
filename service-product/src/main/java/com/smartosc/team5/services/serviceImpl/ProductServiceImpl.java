package com.smartosc.team5.services.serviceImpl;

import com.smartosc.team5.constant.ConstantVariables;
import com.smartosc.team5.converts.ProductConvert;
import com.smartosc.team5.dto.ProductDTO;
import com.smartosc.team5.entities.Product;
import com.smartosc.team5.exception.ExistException;
import com.smartosc.team5.exception.NoContentException;
import com.smartosc.team5.exception.NotFoundException;
import com.smartosc.team5.repositories.ProductRepository;
import com.smartosc.team5.services.ProductService;
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
public class ProductServiceImpl implements ProductService {

    private int retryCount = 0;

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        log.info("Get all products");
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        if (!productList.isEmpty()) {
            productList.forEach(p -> {
                ProductDTO productDTO = ProductConvert.convertProductToDTO(p);
                productDTOList.add(productDTO);
            });
            log.info("Get all products success");
            return productDTOList;
        }
        log.info("No product");
        throw new NoContentException(ConstantVariables.PRODUCT_NO_CONTENT);
    }

    //    @Retryable(value = {NotFoundException.class}, backoff = @Backoff(delay = 10000L))
    @Override
    public ProductDTO findById(int id) {
        log.info("Find product by id " + id);
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return ProductConvert.convertProductToDTO(productOptional.get());
        } else {
            log.info("Attempting at {} time(s)", ++retryCount);
            throw new NotFoundException(ConstantVariables.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        log.info("Create a new product");
        String productName = productDTO.getProductName();
        Optional<Product> productOptional = productRepository.findByName(productName);
        if (productOptional.isPresent()) {
            throw new ExistException(ConstantVariables.PRODUCT_DUPLICATE);
        }
        Product productCreate = productRepository.save(ProductConvert.convertProductDTOtoProduct(productDTO));
        productDTO.setProductId(productCreate.getProductId());
        log.info("Create a new product success");
        return productDTO;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Integer id) {
        log.info("Update product");
        Optional<Product> productUpdate = productRepository.findById(id);
        Optional<Product> updateProductName = productRepository.findByName(productDTO.getProductName());
        if (productUpdate.isPresent()) {
            if (updateProductName.isPresent()) {
                throw new ExistException(ConstantVariables.PRODUCT_DUPLICATE);
            }
            productUpdate.get().setProductId(id);
            productUpdate.get().setName(productDTO.getProductName());
            productUpdate.get().setDescription(productDTO.getDescription());
            productUpdate.get().setImage(productDTO.getImage());
            productUpdate.get().setPrice(productDTO.getPrice());

            productRepository.save(productUpdate.get());
            log.info("Update product success");
            return productDTO;
        } else {
            log.error("Update product fail");
            throw new NotFoundException(ConstantVariables.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        log.info("Delete product");
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.delete(productOptional.get());
            return true;
        } else {
            throw new NotFoundException(ConstantVariables.PRODUCT_NOT_FOUND);
        }
    }

//    @Recover
//    public void recover() {
//        log.info("Recovering");
//    }
}
