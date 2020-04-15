package com.javachen.boot.basic.web.i18n.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }

}
