package com.example.demo.dao;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {

    @Query(value = "select * from  user",nativeQuery = true)
    List<User> selectAll();

    @Query(value = "select * from user where user.num =?1",nativeQuery = true)
    User selectOne(Integer num);
}
