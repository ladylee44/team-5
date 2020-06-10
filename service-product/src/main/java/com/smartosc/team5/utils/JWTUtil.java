package com.smartosc.team5.utils;

import com.smartosc.user.security.UserPrinciple;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * team5
 *
 * @author Huupd
 * @created_at 09/06/2020 - 3:11 PM
 * @created_by Huupd
 */
@Component
public class JWTUtil {
//    public static String getJwtTokenFromSecurityContext() {
//        return  SecurityContextHolder.getContext()
//                .getAuthentication().get;
//    }
//
//    public static HttpHeaders getHearder() {
//        String authToken = JWTUtil.getJwtTokenFromSecurityContext();
//        HttpHeaders header = new HttpHeaders();
//        header.setBearerAuth(authToken);
//        header.setContentType(MediaType.APPLICATION_JSON);
//        return header;
//    }
}
