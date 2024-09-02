package com.keehwan.api.authentication.applications;

import com.keehwan.api.authentication.exceptions.JwtExpireException;
import com.keehwan.api.rest.dto.JoinDTO.JoinRequest;
import com.keehwan.domain.account.domain.UserAccount;
import com.keehwan.domain.account.domain.UserToken;
import com.keehwan.domain.account.domain.enums.UserRole;
import com.keehwan.domain.account.exception.UserAccountNotExistsException;
import com.keehwan.domain.account.service.usecases.CreateUserAccountUsecase;
import com.keehwan.domain.account.service.usecases.CreateUserTokenUsecase;
import com.keehwan.domain.account.service.usecases.GetUserAccountUsecase;
import com.keehwan.domain.account.service.usecases.GetUserTokenUsecase;
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
    private final CreateUserAccountUsecase createUserAccountUsecase;
    private final GetUserAccountUsecase getUserAccountUsecase;
    private final CreateUserTokenUsecase createUserTokenUsecase;
    private final GetUserTokenUsecase getUserTokenUsecase;
    private final JsonWebTokenUtils jsonWebTokenUtils;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Transactional
    public UserAccount createUserAccount(JoinRequest request) {
        UserAccount createUserAccount = createUserAccountUsecase.create(request.toCommand(passwordEncoder));

        mailService.send(request.username(), "[인증] 이메일 인증을 부탁드립니다.", jsonWebTokenUtils.generate(request.username(), "", JsonWebTokenType.EMAIL_VERITY));

        // 인증 이메일 발송


        return createUserAccount;
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
        try {
            String username = this.jsonWebTokenUtils.getUsername(token);
            return getUserAccountUsecase.getUserAccount(username);
        } catch (ExpiredJwtException e) {
            throw new JwtExpireException("만료된 토큰입니다.");
        } catch (UserAccountNotExistsException e) {
            throw new UsernameNotFoundException("알 수 없는 사용자입니다.");
        }
    }

    public void verifyEmail(String code, String username) {
        // UserAccountVerificationUsecase 호출...!!
    }
}
