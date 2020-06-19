package com.smartosc.team5.controllers;

import com.smartosc.team5.dto.JwtRequest;
import com.smartosc.team5.services.RestService;
import com.smartosc.team5.services.RestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * team5
 *
 * @author Huupd
 * @created_at 09/06/2020 - 4:39 PM
 * @created_by Huupd
 */
@RestController
@RequestMapping("api/authenticate")
public class RestTemplateController {
    @Autowired
    private RestService restService = new RestServiceImpl();

    @PostMapping()
    public String getToken() {
        JwtRequest jwtRequest = new JwtRequest("admin6", "123456");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restService.getToken("http://localhost:8888/api/auth/signin", HttpMethod.POST, headers, jwtRequest);
    }
}
