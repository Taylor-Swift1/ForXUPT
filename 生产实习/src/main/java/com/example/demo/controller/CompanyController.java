package com.example.demo.controller;


import com.example.demo.Entity.Company;
import com.example.demo.dao.CompanyDao;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowCredentials ="true",origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyDao companyDao;

    @Autowired
    CompanyService companyService;


    @GetMapping("/all")
    public List<Company> selectAll(){
        return companyDao.selectAll();
    }

    @PostMapping("/alter")
    public Integer alter(@RequestBody Company company){
        return companyService.alter(company);
    }

    @PostMapping("/add")
    public Integer add(@RequestBody Company company){
        return companyService.add(company);
    }


}
