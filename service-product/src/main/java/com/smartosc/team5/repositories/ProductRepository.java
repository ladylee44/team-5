package com.smartosc.team5.repositories;

import com.smartosc.team5.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 04/06/2020 - 03:14 PM
 * @created_by ThaoPhuong
 * @since 04/06/2020
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
