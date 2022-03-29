package com.example.CinemaEBooking.model.securityConfig;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
         
        String redirectURL = request.getContextPath();

        // when customer logs in, redirect to customer home
        // when admin logs in, redirect to admin home
        if (userDetails.hasRole("ADMIN")) {
            redirectURL = "/admin/home";
            
        } else if (userDetails.hasRole("CUSTOMER")) {
            redirectURL = "/user/home";
        }
         
        response.sendRedirect(redirectURL);
         
    }
 
}