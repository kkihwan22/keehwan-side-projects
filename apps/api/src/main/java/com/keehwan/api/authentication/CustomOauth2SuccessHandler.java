package com.keehwan.api.authentication;


import com.keehwan.api.authentication.applications.AuthenticationApplication;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;
import java.util.Objects;

public class CustomOauth2SuccessHandler implements AuthenticationSuccessHandler {

    private final AuthenticationApplication authenticationApplication;

    public CustomOauth2SuccessHandler(AuthenticationApplication authenticationApplication) {
        this.authenticationApplication = authenticationApplication;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        CustomPrincipalDetails userDetails = (CustomPrincipalDetails) authentication.getPrincipal();
        authenticationApplication.create(userDetails.getAccount(), response);
        SavedRequest savedRequest = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

        String redirectionUrl = Objects.isNull(savedRequest) ? "/" : savedRequest.getRedirectUrl();
        response.sendRedirect(redirectionUrl);
    }
}