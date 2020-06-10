package com.smartosc.team5.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * team5
 *
 * @author Huupd
 * @created_at 09/06/2020 - 2:01 PM
 * @created_by Huupd
 */

public interface RestService {
    public String getToken(String url, HttpMethod method, HttpHeaders headers, Object body);
}
