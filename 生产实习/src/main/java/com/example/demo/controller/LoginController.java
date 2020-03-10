package com.example.demo.controller;


import com.example.demo.Util.LoginData;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(allowCredentials ="true",origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/")
    public LoginData login(@RequestParam(name = "username") Integer username,
                           @RequestParam(name = "pwd") String pwd){
        return loginService.login(username,pwd);
    }

}
