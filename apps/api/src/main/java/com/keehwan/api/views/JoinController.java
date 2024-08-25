package com.keehwan.api.views;

import com.keehwan.api.views.model.JoinModel;
import com.keehwan.core.account.service.usecases.CreateUserAccountUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class JoinController {

    private final CreateUserAccountUsecase createUserAccountUsecase;
    private final BCryptPasswordEncoder encoder;

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinModel model) {
        createUserAccountUsecase.create(model.username(), encoder.encode(model.password()));
        return "redirect:/login";
    }
}
