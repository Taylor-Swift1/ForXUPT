package com.example.demo.service;


import com.example.demo.Entity.Company;
import com.example.demo.dao.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class CompanyService {

    @Autowired
    CompanyDao companyDao;

    public Integer alter(Company company){
        return companyDao.update1(company.getAddr(),company.getTel(),company.getRespon(),company.getUrl(),company.getName());
    }

    public Integer add(Company company){
        Company company1= companyDao.save(company);
        return 1;
    }


}
