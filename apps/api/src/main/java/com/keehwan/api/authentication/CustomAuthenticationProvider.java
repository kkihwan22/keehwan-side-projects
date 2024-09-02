package com.keehwan.api.authentication;

import com.keehwan.api.authentication.exceptions.UserAccountLockedException;
import com.keehwan.api.authentication.exceptions.UserAccountNotEnableException;
import com.keehwan.core.account.domain.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(noRollbackFor = AuthenticationException.class)
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomPrincipalDetails userDetails = (CustomPrincipalDetails) customUserDetailsService.loadUserByUsername(authentication.getName());
        UserAccount account = userDetails.getAccount();

        if (!account.isEnabled()) {
            throw new UserAccountNotEnableException("UserAccount의 상태를 확인해주세요.");
        }

        if (!passwordEncoder.matches(authentication.getCredentials().toString(), account.getPassword())) {
            account.failurePasswordMatched();
            throw new BadCredentialsException("요청한 패스워드가 일치하지 않습니다.");
        }

        if (account.isLocked()) {
            throw new UserAccountLockedException("로그인 실패 횟수 초과로 계정을 사용할 수 없습니다. ");
        }

        account.successPasswordMatched();

        Set<GrantedAuthority> authorities = account.getRoles().stream()
                .map(role -> (GrantedAuthority) role::name)
                .collect(Collectors.toSet());

        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
