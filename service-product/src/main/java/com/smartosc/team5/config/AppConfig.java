package com.smartosc.team5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * team5
 *
 * @author Huupd
 * @created_at 10/06/2020 - 10:58 AM
 * @created_by Huupd
 */
@Component
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}
