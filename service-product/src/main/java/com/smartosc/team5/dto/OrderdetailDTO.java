package com.smartosc.team5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 05/06/2020 - 02:45 PM
 * @created_by ThaoPhuong
 * @since 05/06/2020
 */
@Data
@AllArgsConstructor
public class OrderdetailDTO {
    private int deltailId;
    private ProductDTO productDTO;
    private int quantity;
    private double price;
    private double amount;

    public OrderdetailDTO() {
        this.quantity = 0;
    }

    public double getAmount() {
        return this.productDTO.getPrice() * this.quantity;
    }

}
