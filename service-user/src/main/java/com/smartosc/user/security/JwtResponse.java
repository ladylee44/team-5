package com.smartosc.user.security;

/**
 * Team5
 *
 * @author Huupd
 * @created_at 03/06/2020 - 1:58 PM
 * @created_by Huupd
 */
public class JwtResponse {
    private String token;
    private String type = "Bearer";

    public JwtResponse(String accessToken) {
        this.token = accessToken;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}
