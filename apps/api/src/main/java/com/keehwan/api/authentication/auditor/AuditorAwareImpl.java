package com.keehwan.api.authentication.auditor;

import com.keehwan.api.authentication.support.SecurityContextSupporter;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        return SecurityContextSupporter.getOptionalId();
    }
}
