package com.keehwan.api.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keehwan.api.authentication.applications.AuthenticationApplication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.stream.Collectors;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);

    private final AuthenticationManager authenticationManager;
    private final AuthenticationApplication authenticationApplication;
    private final ObjectMapper objectMapper;

    public LoginFilter(
            AuthenticationManager authenticationManager, AuthenticationApplication authenticationApplication, ObjectMapper objectMapper) {
        super(new AntPathRequestMatcher("/login", "POST"));
        this.authenticationManager = authenticationManager;
        this.authenticationApplication = authenticationApplication;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginRequest parameter = null;

        try {
            parameter = objectMapper.readValue(
                    request.getReader().lines().collect(Collectors.joining()), LoginRequest.class); //TODO: 유효성 검증 로직 확인
            log.debug("Login param: {}", parameter);
        } catch(IOException e) {
            e.printStackTrace();
        }

        //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(parameter.email(), parameter.password(), null);

        //token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(token);
    }

    /**
     * 로그인 성공 시 실행되는 부분입니다. JWT 발급 로직을 이곳에 정의해도 되지만,
     * Oauth2 와 로직 공유를 위해 SuccessHandler 쪽에 JWT 토큰 생성 로직은 이관합니다.
     * @param request
     * @param response
     * @param chain
     * @param authentication the object returned from the <tt>attemptAuthentication</tt>
     * method.
     */
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws ServletException, IOException {

        CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler(authenticationApplication);
        successHandler.onAuthenticationSuccess(request, response, authentication);
    }

    /**
     * 로그인 실패시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
     * @param request
     * @param response
     * @param failed
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws ServletException, IOException {
        CustomAuthenticationFailureHandler failureHandler = new CustomAuthenticationFailureHandler();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    private boolean isPostRequest(HttpServletRequest request) {
        return request.getMethod().equals(HttpMethod.POST.name());
    }
}
