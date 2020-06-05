package com.smartosc.team5.repositories;

import com.smartosc.team5.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Team5
 *
 * @author Huupd
 * @created_at 04/06/2020 - 2:51 PM
 * @created_by Huupd
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
