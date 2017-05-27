package com.ml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    //    @PostMapping("/login")
    //    public String login() {
    //        return "redirect:/home";
    //    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/user")
    public String user() {
        return "user";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/denied")
    public String denied() {
        return "denied";
    }

    @RequestMapping("/access")
    public String accessPage() {
        return "access";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String data() {
        return "hello world";
    }
}
