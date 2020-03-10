package com.example.demo.dao;

import com.example.demo.Entity.Signupinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SignupinfoDao extends JpaRepository<Signupinfo, Integer> {

    @Query(value = "select * from  signupinfo",nativeQuery = true)
    List<Signupinfo> selectAll();

    @Query(value = "select * from signupinfo where signupinfo.num=?1", nativeQuery = true)
    List<Signupinfo> selectOne(Integer num);
}
