package com.keehwan.api.authentication.applications;

import com.keehwan.api.authentication.exceptions.JwtExpireException;
import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.domain.UserToken;
import com.keehwan.core.account.domain.enums.UserRole;
import com.keehwan.core.account.exception.UserAccountNotExistsException;
import com.keehwan.core.account.service.usecases.CreateUserAccountUsecase;
import com.keehwan.core.account.service.usecases.CreateUserTokenUsecase;
import com.keehwan.core.account.service.usecases.GetUserAccountUsecase;
import com.keehwan.core.account.service.usecases.GetUserTokenUsecase;
import com.keehwan.share.domain.code.JsonWebTokenType;
import com.keehwan.share.utils.JsonWebTokenUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class AuthenticationApplication {
    private final CreateUserAccountUsecase createUserAccountUsecase;
    private final GetUserAccountUsecase getUserAccountUsecase;
    private final CreateUserTokenUsecase createUserTokenUsecase;
    private final GetUserTokenUsecase getUserTokenUsecase;
    private final JsonWebTokenUtils jsonWebTokenUtils;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserAccount createUserAccount(String username, String password) {
        return createUserAccountUsecase.create(username, passwordEncoder.encode(password));
    }

    @Transactional
    public void create(UserAccount account, HttpServletResponse response) {
        String username = account.getUsername();
        String accessToken = jsonWebTokenUtils.generate(username, UserRole.USER.name(), JsonWebTokenType.ACCESS); // 1시간
        String refreshToken = jsonWebTokenUtils.generate(username, UserRole.USER.name(), JsonWebTokenType.REFRESH); // 1개월

        UserToken createdUserToken = createUserTokenUsecase.create(account, refreshToken);

        response.setHeader("Authorization", "Bearer " + accessToken);
        Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60); // 1 week
        response.addCookie(refreshTokenCookie);
    }

    @Transactional
    public String renew(String token) {
        UserToken findUserToken = getUserTokenUsecase.getUserToken(token);

        if (jsonWebTokenUtils.isExpired(token)) {
            findUserToken.expire("expired");
            throw new JwtExpireException("만료된 refresh token 입니다. ");
        }

        return jsonWebTokenUtils.generate(findUserToken.getAccount().getUsername(), "USER", JsonWebTokenType.ACCESS);
    }

    public UserAccount getUserAccount(String token) {
        // todo: 레디스 캐시 연결

        if (jsonWebTokenUtils.isExpired(token)) {
            throw new JwtExpireException("만료된 토큰입니다.");
        }

        try {
            String username = this.jsonWebTokenUtils.getUsername(token);
            return getUserAccountUsecase.getUserAccount(username);
        } catch (UserAccountNotExistsException e) {
            throw new UsernameNotFoundException("알 수 없는 사용자입니다.");
        }
    }
}
