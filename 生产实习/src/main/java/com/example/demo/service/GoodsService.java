package com.example.demo.service;


import com.example.demo.Entity.Goods;
import com.example.demo.Entity.Operation;
import com.example.demo.Util.GoodAndOperData;
import com.example.demo.Util.OperUtil;
import com.example.demo.dao.CompanyDao;
import com.example.demo.dao.GoodsDao;
import com.example.demo.dao.OperationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

    @Autowired
    OperationDao operationDao;

    @Autowired
    CompanyDao companyDao;


    public List<Goods> getAll(){

        return goodsDao.selectAll();
    }

    public Goods getOne(Integer num){
        return goodsDao.selectOne(num);
    }

    public GoodAndOperData getOneByGoods(Integer num){
        Goods goods=goodsDao.selectOne(num);
        GoodAndOperData goodAndOperData=new GoodAndOperData();
        goodAndOperData.setA_chara(goods.getA_chara());
        goodAndOperData.setB_chara(goods.getB_chara());
        goodAndOperData.setName(goods.getName());
        goodAndOperData.setRemains(goods.getRemains());
        goodAndOperData.setNum(goods.getNum());
        goodAndOperData.setSpec(goods.getSpec());
        goodAndOperData.setList(new ArrayList<>());

        List<Operation> list=operationDao.selectBygoodsid(goods.getId());
        for (Operation operation:list){
            goodAndOperData.getList().add(new OperUtil(operation.getChoice(),operation.getOper_time(),
                    companyDao.selectOne(operation.getCompany_id()).getName(),
                    operation.getSettle_time(),operation.getPrice(),
                    operation.getTotal_num(),operation.getAmount(),operation.getApprover()));
        }
        return goodAndOperData;
    }
    public GoodAndOperData getOneByGoodsOutAndIn(Integer num, Integer choice){
        Goods goods=goodsDao.selectOne(num);
        GoodAndOperData goodAndOperData=new GoodAndOperData();
        goodAndOperData.setA_chara(goods.getA_chara());
        goodAndOperData.setB_chara(goods.getB_chara());
        goodAndOperData.setName(goods.getName());
        goodAndOperData.setRemains(goods.getRemains());
        goodAndOperData.setNum(goods.getNum());
        goodAndOperData.setSpec(goods.getSpec());
        goodAndOperData.setList(new ArrayList<>());

        List<Operation> list=operationDao.selectBygoodsidAndOut(goods.getId(),choice);
        for (Operation operation:list){

            goodAndOperData.getList().add(new OperUtil(operation.getChoice(),operation.getOper_time(),
                    companyDao.selectOne(operation.getCompany_id()).getName(),
                    operation.getSettle_time(),operation.getPrice(),
                    operation.getTotal_num(),operation.getAmount(),operation.getApprover()));
        }
        return goodAndOperData;
    }

}
