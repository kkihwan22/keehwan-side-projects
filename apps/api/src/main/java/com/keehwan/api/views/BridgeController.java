package com.keehwan.api.views;

import com.keehwan.core.account.service.usecases.UserAccountEmailConfirmUsecase;
import com.keehwan.share.utils.JsonWebTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class BridgeController {
    private final JsonWebTokenUtils jsonWebTokenUtils;
    private final UserAccountEmailConfirmUsecase accountEmailConfirmUsecase;

    @RequestMapping("/pages/auth/email-verify")
    public String pageVerifyEmail(@RequestParam String token) {
        try {
            String username = jsonWebTokenUtils.getUsername(token);
            accountEmailConfirmUsecase.confirmEmail(username);
            return "email/emailConfirmResult";
        } catch (RuntimeException e) {
            return "common/error";
        }
    }
}
