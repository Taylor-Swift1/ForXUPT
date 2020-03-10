package com.example.demo.service;


import com.example.demo.Entity.Company;
import com.example.demo.Entity.Goods;
import com.example.demo.Entity.Operation;
import com.example.demo.Entity.User;
import com.example.demo.Util.AllOperData;
import com.example.demo.Util.OperInUtil;
import com.example.demo.Util.OperationData;
import com.example.demo.Util.OperationSpecData;
import com.example.demo.dao.CompanyDao;
import com.example.demo.dao.GoodsDao;
import com.example.demo.dao.OperationDao;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OperationService {

    @Autowired
    OperationDao operationDao;
    @Autowired
    CompanyDao companyDao;
    @Autowired
    UserDao userDao;
    @Autowired
    GoodsDao goodsDao;

    public List<OperationData> getAll(){
        List<Operation> list=operationDao.selectAll();
        List<OperationData> newList=new ArrayList<>();
        for(Operation operation:list){
            //0代表出库 1入库
            Company company=companyDao.selectOne(operation.getCompany_id());
            if(operation.getChoice()==0){
                newList.add(new OperationData(company.getName(),operation.getAmount(),operation.getOper_time()));
            }
            else {
                newList.add(new OperationData(company.getName(),(-1)*operation.getAmount(),operation.getOper_time()));
            }
        }
        return newList;
    }

    public List<OperationData> getOne(String start, String end){
        start+="-01 00:00:00";
        end+="-01 00:00:00";
        List<Operation> list=operationDao.selectTime(start,end);
        List<OperationData> newList=new ArrayList<>();

        for(Operation operation:list){
            Company company=companyDao.selectOne(operation.getCompany_id());
            if(operation.getChoice()==0){
                newList.add(new OperationData(company.getName(),1*operation.getAmount(),operation.getOper_time()));
            }
            else {
                newList.add(new OperationData(company.getName(),operation.getAmount()*(-1),operation.getOper_time()));
            }
        }
        return newList;
    }

    public OperationSpecData getSpec(String start, String end){
        List<OperationData> list=this.getOne(start,end);
        OperationSpecData operationSpecData=new OperationSpecData();
        operationSpecData.setTime(new ArrayList<>());
        operationSpecData.setAmount(new ArrayList<>());
        Integer year1=Integer.parseInt(start.split("-")[0]);
        Integer month1=Integer.parseInt(start.split("-")[1]);

        Double amount=0.0;
        for(OperationData operationData : list){
            Integer year2=Integer.parseInt(operationData.getTime().split("-")[0]);
            Integer month2=Integer.parseInt(operationData.getTime().split("-")[1]);

            while(true) {
                if (year1.equals(year2) && month1.equals(month2)) {
                    amount += operationData.getMoney();
                    break;
                } else {
                    String s="" + year1 + "-" + month1;
                    operationSpecData.getTime().add(s);
                    operationSpecData.getAmount().add(amount);
                    amount = 0.0;

                    month1++;
                    if (month1 > 12) {
                        month1 = 1;
                        year1++;
                    }
                }
            }


        }
        Integer year3=Integer.parseInt(end.split("-")[0]);
        Integer month3=Integer.parseInt(end.split("-")[1]);
        if(month3==1){
            year3--;
            month3=12;
        }
        else{
            month3--;
        }
        while(true){
            String s="" + year1 + "-" + month1;
            operationSpecData.getTime().add(s);
            operationSpecData.getAmount().add(amount);
            amount=0.0;
            if(year1.equals(year3)&&month1.equals(month3)){
                break;
            }
            else {
                month1++;
                if (month1 > 12) {
                    month1 = 1;
                    year1++;
                }
            }
        }
        return operationSpecData;

    }

    public List<AllOperData> getAllOper(){
        List<Operation> list=operationDao.selectAll();
        List<AllOperData> newList=new ArrayList<>();

        for(Operation operation:list){
            Goods goods=goodsDao.findById(operation.getGoods_id()).orElse(new Goods());
            Company company=companyDao.selectOne(operation.getCompany_id());
            newList.add(new AllOperData(goods.getName(),goods.getNum(),goods.getSpec(),goods.getA_chara(),goods.getB_chara(),goods.getRemains(),
                    operation.getChoice(),operation.getOper_time(),company.getName(),operation.getSettle_time(),operation.getPrice(),
                    operation.getTotal_num(),operation.getAmount(),operation.getApprover()));
        }
        return newList;
    }
    public Integer updateout(List<Operation> t_operation){
        for (Operation operation:t_operation){
            operationDao.save(operation);
            Goods goods=goodsDao.findById(operation.getGoods_id()).orElse(new Goods());
            goods.setRemains(goods.getRemains()-operation.getTotal_num());
        }
        return 1;
    }
    public Integer updatein(List<OperInUtil> t_operInUtil){
        for (OperInUtil operInUtil:t_operInUtil){
            Goods goods=goodsDao.selectOne(operInUtil.getNum());
            if(goods==null){
                Goods newGoods=new Goods();
                newGoods.setNum(operInUtil.getNum());
                newGoods.setName(operInUtil.getName());
                newGoods.setSpec(operInUtil.getSpec());
                newGoods.setA_chara(operInUtil.getA_chara());
                newGoods.setB_chara(operInUtil.getB_chara());
                newGoods.setRemains(operInUtil.getTotal_num());
                newGoods.setUnit(operInUtil.getUnit());
                goodsDao.save(newGoods);
                Operation operation=new Operation();
                operation.setNum(operInUtil.getUser_num());
                operation.setChoice(1);
                operation.setGoods_id(goodsDao.selectOne(operInUtil.getNum()).getId());
                operation.setOper_time(operInUtil.getOper_time());
                operation.setCompany_id(operInUtil.getCompany_id());
                operation.setSettle_time(operInUtil.getSettle_time());
                operation.setPrice(operInUtil.getPrice());
                operation.setTotal_num(operInUtil.getTotal_num());
                operation.setAmount(operInUtil.getAmount());
                operation.setApprover(operInUtil.getApprover());
                operationDao.save(operation);
            }else {
                goods.setRemains(goods.getRemains()+operInUtil.getTotal_num());
                goodsDao.save(goods);
                Operation operation=new Operation();
                operation.setNum(operInUtil.getUser_num());
                operation.setChoice(1);
                operation.setGoods_id(goods.getId());
                operation.setOper_time(operInUtil.getOper_time());
                operation.setCompany_id(operInUtil.getCompany_id());
                operation.setSettle_time(operInUtil.getSettle_time());
                operation.setPrice(operInUtil.getPrice());
                operation.setTotal_num(operInUtil.getTotal_num());
                operation.setAmount(operInUtil.getAmount());
                operation.setApprover(operInUtil.getApprover());
                operationDao.save(operation);
            }
        }

        return 1;
    }
}
