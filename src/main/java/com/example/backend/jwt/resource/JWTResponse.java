package com.example.backend.jwt.resource;

import java.io.Serializable;

//JWT RESPONSE FOR TOKEN
public class JWTResponse implements Serializable {


  private final String token;

    public JWTResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}