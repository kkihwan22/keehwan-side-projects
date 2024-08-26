package com.keehwan.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:properties/env-local.properties")
})
@Profile(value = {"default", "local"})
public class LocalEnvPropertyConfig {

}
