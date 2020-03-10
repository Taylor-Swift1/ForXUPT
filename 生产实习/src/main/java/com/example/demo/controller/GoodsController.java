package com.example.demo.controller;


import com.example.demo.Entity.Goods;
import com.example.demo.Util.GoodAndOperData;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(allowCredentials ="true",origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @GetMapping("/getall")
    public List<Goods> getAll(){
        return goodsService.getAll();
    }

    @GetMapping("/getone")
    public Goods getOne(Integer num){
        return goodsService.getOne(num);
    }

    @GetMapping("/getoper")
    public GoodAndOperData getOper(Integer num){
        return goodsService.getOneByGoods(num);
    }




}
