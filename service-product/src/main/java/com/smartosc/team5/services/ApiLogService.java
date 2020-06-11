package com.smartosc.team5.services;

import com.smartosc.team5.entities.ApiLog;
import com.smartosc.team5.repositories.ApiLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 11/06/2020 - 03:41 PM
 * @created_by ThaoPhuong
 * @since 11/06/2020
 */
@Service
public class ApiLogService {
    @Autowired
    private ApiLogRepository apiLogRepository;

    public List<ApiLog> apiLogs() {
        return apiLogRepository.findAll();
    }

    public void saveApiLog(ApiLog apiLog) {
        apiLogRepository.save(apiLog);
    }
}
