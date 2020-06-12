package com.smartosc.team5.repositories;

import com.smartosc.team5.entities.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 11/06/2020 - 03:34 PM
 * @created_by ThaoPhuong
 * @since 11/06/2020
 */
@Repository
public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {
}
