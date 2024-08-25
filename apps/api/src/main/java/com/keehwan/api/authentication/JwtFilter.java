package com.keehwan.api.authentication;

import com.keehwan.api.authentication.applications.AuthenticationApplication;
import com.keehwan.api.authentication.exceptions.JwtInvalidException;
import com.keehwan.core.account.domain.UserAccount;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JwtFilter extends OncePerRequestFilter {
    private final AuthenticationApplication authenticationApplication;

    public JwtFilter(AuthenticationApplication authenticationApplication) {
        this.authenticationApplication = authenticationApplication;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationValue = Optional
                .ofNullable(request.getHeader(AUTHORIZATION))
                .orElse(null);

        if (authorizationValue != null) {
            if (!authorizationValue.startsWith("Bearer")) {
                throw new JwtInvalidException("토큰의 형식이 유효하지 않습니다.");
            }

            String[] splitValue = authorizationValue.split(" ");

            if (splitValue.length != 2) {
                throw new JwtInvalidException("토큰의 형식이 유효하지 않습니다.");
            }

            UserAccount userAccount = authenticationApplication.getUserAccount(splitValue[1]);

            //UserDetails에 회원 정보 객체 담기
            CustomPrincipalDetails customPrincipalDetails = new CustomPrincipalDetails(userAccount);

            //스프링 시큐리티 인증 토큰 생성
            Authentication authToken = new UsernamePasswordAuthenticationToken(customPrincipalDetails, null, customPrincipalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

}
