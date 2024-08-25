package com.keehwan.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keehwan.api.authentication.*;
import com.keehwan.api.authentication.applications.AuthenticationApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private static final String[] PERMIT_REQUESTS = {"/", "/login", "/logout", "/renew", "/join", "/public/**", "/pages/**", "/statics/**", "/favicon.ico"};
    private final AuthenticationConfiguration authenticationConfiguration;
    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final AuthenticationApplication authenticationApplication;
    private final ObjectMapper objectMapper;

    public SecurityConfig(
            AuthenticationConfiguration authenticationConfiguration,
            CustomAuthenticationProvider customAuthenticationProvider,
            CustomOAuth2UserService customOAuth2UserService,
            RestAuthenticationEntryPoint restAuthenticationEntryPoint,
            CustomAccessDeniedHandler customAccessDeniedHandler,
            AuthenticationApplication authenticationApplication,
            ObjectMapper objectMapper
            ) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.customOAuth2UserService = customOAuth2UserService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.objectMapper = objectMapper;
        this.authenticationApplication = authenticationApplication;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setMaxAge(3600L);
        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

        return request -> configuration;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        http.csrf(CsrfConfigurer::disable);
        http.httpBasic(HttpBasicConfigurer::disable);
        http.formLogin(FormLoginConfigurer::disable);

        http.authenticationProvider(customAuthenticationProvider);
        http.oauth2Login((oauth2) -> {
            oauth2.userInfoEndpoint((config) -> config.userService(customOAuth2UserService));
            oauth2.successHandler(new CustomOauth2SuccessHandler(authenticationApplication));
        });

        http.authorizeHttpRequests(request -> {
            request.requestMatchers(PERMIT_REQUESTS).permitAll();
            request.requestMatchers("/admin/**").hasAnyAuthority("ADMIN");
            request.anyRequest().authenticated();
        });

        http.exceptionHandling(exception -> {
            exception.authenticationEntryPoint(restAuthenticationEntryPoint);
            exception.accessDeniedHandler(customAccessDeniedHandler);
        });

        http.addFilterBefore(new LoginFilter(authenticationManager(authenticationConfiguration), authenticationApplication, objectMapper), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtFilter(authenticationApplication), LoginFilter.class);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}