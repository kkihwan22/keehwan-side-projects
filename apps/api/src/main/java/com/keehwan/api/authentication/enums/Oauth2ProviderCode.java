package com.keehwan.api.authentication.enums;

import com.keehwan.api.authentication.Oauth2UserResponse;
import com.keehwan.core.account.domain.UserAccount.SocialProvider;
import lombok.Getter;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.keehwan.api.authentication.Oauth2UserResponse.GoogleUserResponse;
import static com.keehwan.api.authentication.Oauth2UserResponse.NaverOauth2UserResponse;

public enum Oauth2ProviderCode {

    NAVER(SocialProvider.NAVER) {
        @Override
        public Oauth2UserResponse toOauth2UserResponse(OAuth2User oauth2User) {
            return NaverOauth2UserResponse.of(oauth2User);
        }
    },
    GOOGLE(SocialProvider.GOOGLE) {
        @Override
        public Oauth2UserResponse toOauth2UserResponse(OAuth2User oauth2User) {
            return GoogleUserResponse.of(oauth2User);
        }
    },

    ;

    @Getter
    private SocialProvider socialProvider;

    Oauth2ProviderCode(SocialProvider socialProvider) {
        this.socialProvider = socialProvider;
    }

    private static final Map<String, Oauth2ProviderCode> lookup =
            Arrays.stream(Oauth2ProviderCode.values()).collect(Collectors.toMap(Oauth2ProviderCode::name, Function.identity()));

    public static Oauth2ProviderCode get(String code) {
        return lookup.getOrDefault(code.toUpperCase(), null);
    }

    abstract public Oauth2UserResponse toOauth2UserResponse(OAuth2User oauth2User);
}
