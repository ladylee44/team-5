package com.smartosc.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Team5
 *
 * @author Huupd
 * @created_at 04/06/2020 - 3:22 PM
 * @created_by Huupd
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private int ordersId;
    private double totalPrice;
    private int status;
    private Date createdAt;
    private Date updatedAt;
    private List<OrderdetailDTO> orderDetailEntities;


    public OrderDTO(int i, int i1, List<OrderdetailDTO> orderdetailDTOList1) {
    }
}