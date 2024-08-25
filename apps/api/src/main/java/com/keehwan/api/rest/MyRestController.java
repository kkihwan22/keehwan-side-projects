package com.keehwan.api.rest;

import com.keehwan.api.share.BaseRestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController implements BaseRestController {

    @GetMapping("/api/v1/my")
    public void my() {

    }
}
