package com.smartosc.team5.converts;

import com.smartosc.team5.dto.ProductDTO;
import com.smartosc.team5.entities.Product;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 04/06/2020 - 03:36 PM
 * @created_by ThaoPhuong
 * @since 04/06/2020
 */
public class ProductConvert {
    /**
     * convert product to DTO
     * @param product
     * @return
     */
    public static ProductDTO convertProductToDTO (Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getName());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    /**
     * convert DTO to product entity
     * @param productDTO
     * @return
     */
    public static Product convertProductDTOtoProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getProductName());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}
