package com.keehwan.api.authentication.support;

import com.keehwan.api.authentication.CustomPrincipalDetails;
import com.keehwan.core.account.domain.UserAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class SecurityContextSupporter {

    public static UserAccount get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication) && (authentication.getPrincipal() instanceof CustomPrincipalDetails)) {
            CustomPrincipalDetails details = (CustomPrincipalDetails) authentication.getPrincipal();
            return details.getAccount();
        }

        return null;
    }

    public static Long getId() {
        return Optional.ofNullable(SecurityContextSupporter.get())
                .map(UserAccount::getId)
                .orElseThrow(() -> new AuthenticationNotFoundException());
    }

    public static Long getOrNull() {
        return Optional.ofNullable(SecurityContextSupporter.get())
                .map(UserAccount::getId)
                .orElse(null);
    }
}
