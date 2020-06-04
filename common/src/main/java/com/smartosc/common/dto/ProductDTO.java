package com.smartosc.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 04/06/2020 - 03:22 PM
 * @created_by ThaoPhuong
 * @since 04/06/2020
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private int productId;
    private String productName;
    private String image;
    private String description;
    private double price;
}
