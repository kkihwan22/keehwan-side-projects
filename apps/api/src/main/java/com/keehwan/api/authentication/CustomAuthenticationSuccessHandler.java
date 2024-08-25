package com.keehwan.api.authentication;


import com.keehwan.api.authentication.applications.AuthenticationApplication;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthenticationApplication authenticationApplication;

    public CustomAuthenticationSuccessHandler(AuthenticationApplication authenticationApplication) {
        this.authenticationApplication = authenticationApplication;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        CustomPrincipalDetails userDetails = (CustomPrincipalDetails) authentication.getPrincipal();
        if (!userDetails.isVerified()) {
            response.sendRedirect("/pages/verify-email");
        } else if (userDetails.isPasswordChangeNeeded()) {
            response.sendRedirect("/pages/change-password");
        } else {
            authenticationApplication.create(userDetails.getAccount(), response);
            response.getWriter().write("Authentication successful for user: " + userDetails.getUsername());
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().flush();
    }
}