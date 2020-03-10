package com.example.demo.dao;

import com.example.demo.Entity.Goods;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsDao extends JpaRepository<Goods, Integer> {
    @Query(value = "select * from  goods",nativeQuery = true)
    List<Goods> selectAll();

    @Query(value = "select * from goods where goods.num=?1",nativeQuery = true)
    Goods selectOne(Integer num);
}
