package com.keehwan.api.authentication.applications;

import com.keehwan.api.authentication.exceptions.JsonWebTokenExpireException;
import com.keehwan.api.authentication.exceptions.JsonWebTokenInvalidException;
import com.keehwan.api.rest.dto.JoinDTO.JoinRequest;
import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.domain.UserToken;
import com.keehwan.core.account.exception.UserAccountNotExistsException;
import com.keehwan.core.account.service.usecases.UserAccountCreateUsecase;
import com.keehwan.core.account.service.usecases.UserAccountReadUsecase;
import com.keehwan.core.account.service.usecases.UserTokenCreateUsecase;
import com.keehwan.core.account.service.usecases.UserTokenGetUsecase;
import com.keehwan.infra.mail.MailService;
import com.keehwan.share.domain.code.JsonWebTokenType;
import com.keehwan.share.utils.JsonWebTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
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
    private final UserAccountCreateUsecase userAccountCreateUsecase;
    private final UserAccountReadUsecase userAccountReadUsecase;
    private final UserTokenCreateUsecase userTokenCreateUsecase;
    private final UserTokenGetUsecase userTokenGetUsecase;
    private final JsonWebTokenUtils jsonWebTokenUtils;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Transactional
    public UserAccount createUserAccount(JoinRequest request) {
        UserAccount createUserAccount = userAccountCreateUsecase.create(request.toCommand(passwordEncoder));

        // 인증 이메일 발송
        mailService.send(request.username(), "[인증] 이메일 인증을 부탁드립니다.", jsonWebTokenUtils.generate(request.username(), JsonWebTokenType.EMAIL_VERITY));

        return createUserAccount;
    }

    @Transactional
    public void create(UserAccount account, HttpServletResponse response) {
        String username = account.getUsername();
        String accessToken = jsonWebTokenUtils.generate(username, JsonWebTokenType.ACCESS); // 1시간
        String refreshToken = jsonWebTokenUtils.generate(username, JsonWebTokenType.REFRESH); // 1개월

        UserToken createdUserToken = userTokenCreateUsecase.create(account, refreshToken);

        response.setHeader("Authorization", "Bearer " + accessToken);
        Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60); // 1 week
        response.addCookie(refreshTokenCookie);
    }

    @Transactional
    public String renew(String token) {
        UserToken tokenOfUser = userTokenGetUsecase.getUserToken(token);

        try {
            String username = jsonWebTokenUtils.getUsername(token);

            if (tokenOfUser.getAccount().getUsername().equals(username)) {
                return jsonWebTokenUtils.generate(tokenOfUser.getAccount().getUsername(), JsonWebTokenType.ACCESS);
            }

            throw new JsonWebTokenInvalidException("token의 username이 일치하지 않습니다.");

        } catch (ExpiredJwtException e) {
            tokenOfUser.expire("expired");
            throw new JsonWebTokenExpireException("만료된 refresh token 입니다. ");
        }
    }

    public UserAccount getUserAccount(String token) {
        // todo: 레디스 캐시 연결
        try {
            String username = this.jsonWebTokenUtils.getUsername(token);
            return userAccountReadUsecase.getUserAccount(username);
        } catch (ExpiredJwtException e) {
            throw new JsonWebTokenExpireException("만료된 토큰입니다.");
        } catch (UserAccountNotExistsException e) {
            throw new UsernameNotFoundException("알 수 없는 사용자입니다.");
        }
    }

    public void verifyEmail(String code, String username) {
        // UserAccountVerificationUsecase 호출...!!
    }
}
