package com.smartosc.team5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * team5
 *
 * @author Huupd
 * @created_at 09/06/2020 - 4:37 PM
 * @created_by Huupd
 */
@Service
public class RestServiceImpl implements RestService{
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getToken(String url, HttpMethod method, HttpHeaders headers, Object body) {
        try {
            HttpEntity<Object> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> res = restTemplate.exchange(url, method, entity, String.class);
            if (res.getStatusCodeValue() >= HttpStatus.OK.value() && res.getStatusCodeValue() < HttpStatus.MULTIPLE_CHOICES.value()) {
                return res.getBody();
            }
            throw new RestClientException(res.getBody());
        } catch (Exception e) {
            throw new RestClientException(e.getMessage(), e);
        }

    }
}
