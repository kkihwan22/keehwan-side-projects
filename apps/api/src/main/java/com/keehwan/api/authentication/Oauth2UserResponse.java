package com.keehwan.api.authentication;

import com.keehwan.api.authentication.enums.Oauth2ProviderCode;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public interface Oauth2UserResponse {
    String provider();
    String providerId();
    String email();
    String name();

    // google의 경우 email verified 값도 전달해준다.


    record GoogleUserResponse(Map<String, Object> attribute) implements Oauth2UserResponse {
        @Override
        public String provider() {
            return Oauth2ProviderCode.GOOGLE.name();
        }

        @Override
        public String providerId() {
            return attribute.get("sub").toString();
        }

        @Override
        public String email() {
            return attribute.get("email").toString();
        }

        @Override
        public String name() {
            return attribute.get("name").toString();
        }

        public static GoogleUserResponse of(OAuth2User oauth2User) {
            return new GoogleUserResponse(oauth2User.getAttributes());
        }
    };

    record NaverOauth2UserResponse(Map<String, Object> attribute) implements Oauth2UserResponse {

        @Override
        public String provider() {
            return Oauth2ProviderCode.NAVER.name();
        }

        @Override
        public String providerId() {
            return attribute.get("id").toString();
        }

        @Override
        public String email() {
            return attribute.get("email").toString();
        }

        @Override
        public String name() {
            return attribute.get("name").toString();
        }

        public static NaverOauth2UserResponse of(OAuth2User oauth2User) {
            return new NaverOauth2UserResponse(oauth2User.getAttribute("response"));
        }
    }
}
