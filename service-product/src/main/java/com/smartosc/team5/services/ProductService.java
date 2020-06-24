package com.smartosc.team5.services;

import com.smartosc.team5.dto.ProductDTO;

import java.util.List;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 22/06/2020 - 03:22 PM
 * @created_by ThaoPhuong
 * @since 22/06/2020
 */
public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO findById(int id);
    ProductDTO addProduct(ProductDTO productDTO);
    ProductDTO updateProduct(ProductDTO productDTO, Integer id);
    boolean deleteProduct(int id);
}
