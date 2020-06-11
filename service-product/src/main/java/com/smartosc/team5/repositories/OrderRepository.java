package com.smartosc.team5.repositories;

import com.smartosc.team5.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 05/06/2020 - 02:50 PM
 * @created_by ThaoPhuong
 * @since 05/06/2020
 */
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
