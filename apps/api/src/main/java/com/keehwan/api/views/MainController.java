package com.keehwan.api.views;

import com.keehwan.api.authentication.CustomPrincipalDetails;
import com.keehwan.core.account.domain.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    private final Logger log = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    public String pageIndex(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("authentication: {}", authentication);

        if (authentication.getDetails() instanceof CustomPrincipalDetails) {
            // 로그인 된 사용자!!

            CustomPrincipalDetails details = (CustomPrincipalDetails) authentication.getDetails();
            String name = authentication.getName();
            UserAccount account = details.getAccount();

            log.info("username: {}, nickname: {}", name, account.getNickname());

            model.addAttribute("username", name);
            model.addAttribute("nickname", account.getNickname());
            model.addAttribute("profileImage", account.getProfileImage());
        }
        return "index";
    }

    @RequestMapping("/pages/login-form")
    public String pageLoginForm() {
        return "login";
    }

    @RequestMapping("/pages/join-form")
    public String pageJoinForm() {
        return "join";
    }


    @RequestMapping("/pages/email-confirm-form")
    public String pageVerifyEmail() {
        return "index";
    }
}