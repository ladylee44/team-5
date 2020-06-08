package com.smartosc.team5.repositories;

import com.smartosc.team5.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * team5
 *
 * @author Huupd
 * @created_at 08/06/2020 - 11:08 AM
 * @created_by Huupd
 */
public interface OrderDetailRepository  extends JpaRepository<OrderDetail,Integer> {
}
