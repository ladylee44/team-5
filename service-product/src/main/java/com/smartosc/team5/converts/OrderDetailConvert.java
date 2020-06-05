package com.smartosc.team5.converts;

import com.smartosc.team5.dto.OrderdetailDTO;
import com.smartosc.team5.dto.ProductDTO;
import com.smartosc.team5.entities.OrderDetail;

/**
 * Team5
 *
 * @author Huupd
 * @created_at 05/06/2020 - 1:53 PM
 * @created_by Huupd
 */
public class OrderDetailConvert {
    /**Convert OrderDetailEntity to OrderDetailDTO*/
    public OrderdetailDTO convertEntitytoDTO(OrderDetail orderDetail){
        OrderdetailDTO orderdetailDTO = new OrderdetailDTO();
        orderdetailDTO.setDeltailId(orderDetail.getDeltailId());
        orderdetailDTO.setPrice(orderDetail.getPrice());
        orderdetailDTO.setQuantity(orderDetail.getQuantity());
        ProductDTO productDTO = ProductConvert.convertProductToDTO(orderDetail.getProduct());
        orderdetailDTO.setProductDTO(productDTO);
        return orderdetailDTO;
    }
}