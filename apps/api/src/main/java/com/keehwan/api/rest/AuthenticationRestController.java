package com.keehwan.api.rest;

import com.keehwan.api.authentication.applications.AuthenticationApplication;
import com.keehwan.api.rest.dto.ApiResponse;
import com.keehwan.api.share.BaseRestController;
import com.keehwan.core.account.domain.UserAccount;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.keehwan.api.rest.dto.JoinDTO.JoinRequest;

@RequiredArgsConstructor
@RestController
public class AuthenticationRestController implements BaseRestController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationRestController.class);
    private final AuthenticationApplication authenticationApplication;

    @PostMapping("/join")
    public ApiResponse<Void> join(@Valid @RequestBody JoinRequest request, BindingResult bindingResult) {
        UserAccount account = authenticationApplication.createUserAccount(request);
        return ApiResponse.just(null);
    }

    @PostMapping("/renew")
    public ApiResponse<String> renew(@Valid @RequestBody RenewRequest renewRequest, BindingResult bindingResult) {
        String renewToken = authenticationApplication.renew(renewRequest.token);
        return ApiResponse.just(renewToken);
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@CookieValue("refresh_token") String token) {
        log.debug("token: {}", token);
        return ApiResponse.just(null);
    }

    public record RenewRequest(@NotBlank String token) {}
}
