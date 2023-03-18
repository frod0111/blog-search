package com.frod.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Swagger Document Page로 Redirect 해주는 Controller입니다. ApiIgnore Attribute를 붙여서 Swagger API 리스트에는 보이지
 * 않게 한다.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @ApiIgnore
    @GetMapping("/")
    public String index() {
        return "redirect:" + "/swagger-ui/index.html";
    }
}
