package com.keehwan.api.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/pages/login-form")
    public String pageLogin() {

        return "login";
    }
}
