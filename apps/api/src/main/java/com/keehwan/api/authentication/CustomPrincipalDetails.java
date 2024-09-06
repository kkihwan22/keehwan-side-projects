package com.keehwan.api.authentication;

import com.keehwan.core.account.domain.UserAccount;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;


@Getter @ToString
public class CustomPrincipalDetails implements UserDetails, OAuth2User {
    private final UserAccount account;
    private Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return account.getRoles().stream()
                .map(role -> (GrantedAuthority)role::name)
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public String getName() {
        return account.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return account.isEnabled();
    }

    public boolean isPasswordChangeNeeded() {
        return account.isAfterPasswordChangedAt();
    }

    public boolean isVerified() {
        return account.isEmailVerified();
    }

    public CustomPrincipalDetails(UserAccount account) {
        this.account = account;
    }

    public CustomPrincipalDetails(UserAccount account, Map<String,Object> attributes) {
        this.account = account;
        this.attributes = attributes;
    }
}
