package com.example.demo.dao;

import com.example.demo.Entity.Company;
import com.example.demo.Entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationDao extends JpaRepository<Operation, Integer> {
    @Query(value = "select * from  operation",nativeQuery = true)
    List<Operation> selectAll();

    @Query(value = "select * from operation where operation.oper_time>?1 and operation.oper_time<?2",nativeQuery = true)
    List<Operation> selectTime(String start, String end);

    @Query(value = "select * from operation where operation.goods_id=?1",nativeQuery = true)
    List<Operation> selectBygoodsid(Integer num);

    @Query(value = "select * from operation where operation.goods_id=?1 and operation.choice=?2",nativeQuery = true)
    List<Operation> selectBygoodsidAndOut(Integer num,Integer choice);
}
