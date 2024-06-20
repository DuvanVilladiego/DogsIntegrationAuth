package com.example.app.config;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.app.dto.GeneralResponseWithTokenDTO;
import com.example.app.utils.Constants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");        
        GeneralResponseWithTokenDTO generalResponse = new GeneralResponseWithTokenDTO(false, Constants.INVALID_TOKEN, null, null);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", generalResponse.isStatus());
        jsonResponse.put("message", generalResponse.getMessage());
        jsonResponse.put("data", JSONObject.NULL);
        jsonResponse.put("token", JSONObject.NULL);
        response.getWriter().write(jsonResponse.toString());
    }
}