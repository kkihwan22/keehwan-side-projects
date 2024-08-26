package com.keehwan.api.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    private final Logger log = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    public String pageIndex(Model model) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String name = authentication.getName();
//        CustomPrincipalDetails details = (CustomPrincipalDetails) authentication.getDetails();
//        UserAccount account = details.getAccount();
//
//        model.addAttribute("name", name);
//        model.addAttribute("account", account);

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