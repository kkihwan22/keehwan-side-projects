package com.keehwan.api.config;

import com.keehwan.share.utils.JsonWebTokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CommonUtilsConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JsonWebTokenUtils jsonWebTokenUtils() {
        return new JsonWebTokenUtils(secret);
    }
}
