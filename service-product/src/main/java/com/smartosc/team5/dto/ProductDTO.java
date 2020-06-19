package com.smartosc.team5.dto;

import com.smartosc.team5.validate.annotation.ValidPrice;
import lombok.*;

import javax.validation.constraints.NotNull;

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
@Data
public class ProductDTO {
    private int productId;
    @NotNull
    private String productName;
    @NotNull
    private String image;

    private String description;

    @ValidPrice
    private double price;
}
