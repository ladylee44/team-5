package com.smartosc.team5.converts;

import com.smartosc.common.dto.ProductDTO;
import com.smartosc.team5.entities.Product;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 04/06/2020 - 03:36 PM
 * @created_by ThaoPhuong
 * @since 04/06/2020
 */
public class Convert {
    public static ProductDTO convertProductToDTO (Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(product.getName());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    public static Product convertProductDTOtoProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getProductName());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}
