package com.example.demo.dao;

import com.example.demo.Entity.Company;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyDao extends JpaRepository<Company, Integer> {

    @Query(value = "select * from  company",nativeQuery = true)
    List<Company> selectAll();
    @Query(value = "select * from company where company.id=?1",nativeQuery = true)
    Company selectOne(Integer id);


    @Modifying
    @Query(value = "update Company company set company.addr=?1, company.tel=?2, company.respon=?3, company.url=?4 where company.name= ?5")
    Integer update1(String addr,String tel,String respon,String url,String name);





}
