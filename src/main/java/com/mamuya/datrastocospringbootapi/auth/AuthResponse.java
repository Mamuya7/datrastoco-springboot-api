package com.mamuya.datrastocospringbootapi.auth;

public class AuthResponse {

    private  String jwt;

    public AuthResponse() {
    }

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
