package com.keehwan.api.authentication;

import com.keehwan.api.authentication.enums.Oauth2ProviderCode;
import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.persistence.repository.account.UserAccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private static final Logger log = LoggerFactory.getLogger(CustomOAuth2UserService.class);
    private final UserAccountJpaRepository userAccountJpaRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(request);
        Oauth2ProviderCode code = this.getOauthProviderCode(request);

        if (Objects.isNull(code)) return null;

        Oauth2UserResponse oauth2UserResponse = code.toOauth2UserResponse(oauth2User);

        log.info("Oauth2 user: {}", oauth2User);
        log.info("Oauth2 response: {}", oauth2UserResponse);

        UserAccount account = userAccountJpaRepository.findUserAccountByUsername(oauth2UserResponse.email())
                .orElse(UserAccount.registerSocial(
                        oauth2UserResponse.email(),
                        oauth2UserResponse.nickname(),
                        oauth2UserResponse.profileImage(),
                        code.getSocialProvider(),
                        oauth2UserResponse.providerId()));

        userAccountJpaRepository.save(account);

        return new CustomPrincipalDetails(account, oauth2User.getAttributes());
    }


    private Oauth2ProviderCode getOauthProviderCode(OAuth2UserRequest request) {
        return Optional.ofNullable(request.getClientRegistration())
                .map(registration -> Oauth2ProviderCode.get(registration.getRegistrationId()))
                .orElse(null);
    }
}
