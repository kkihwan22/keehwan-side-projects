package com.keehwan.api.authentication;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.persistence.repository.account.UserAccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountJpaRepository userAccountJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount account = userAccountJpaRepository.findUserAccountByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다. username : " + username));
        return new CustomPrincipalDetails(account);
    }
}
