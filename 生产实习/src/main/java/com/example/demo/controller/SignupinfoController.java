package com.example.demo.controller;



import com.example.demo.Util.AllSignupinfoData;
import com.example.demo.Util.SignupinfoData;
import com.example.demo.service.SignupinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(allowCredentials ="true",origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/signupinfo")
public class SignupinfoController {

    @Autowired
    SignupinfoService signupinfoService;

    @GetMapping("/all")
    public SignupinfoData getAll(@RequestParam(name = "num") Integer num){
        return signupinfoService.selectAll(num);
    }

    @GetMapping("/getall")
    public List<AllSignupinfoData> getAllM(){
        return signupinfoService.selectAllM();
    }



}
