package com.example.demo.controller;


import com.example.demo.Entity.Operation;
import com.example.demo.Util.*;
import com.example.demo.service.GoodsService;
import com.example.demo.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(allowCredentials ="true",origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/operation")
public class OperationController {


    @Autowired
    OperationService operationService;

    @Autowired
    GoodsService goodsService;

    @GetMapping("/all")
    public List<OperationData> getAll(){
        return operationService.getAll();
    }

    @GetMapping("/get")
    public List<OperationData> getOne(@RequestParam(name = "start") String start,
                                      @RequestParam(name = "end") String end){
        return operationService.getOne(start,end);
    }
    @GetMapping("/gettotal")
    public OperationSpecData getSpec(@RequestParam(name = "start") String start,
                                     @RequestParam(name = "end") String end){
        return operationService.getSpec(start, end);
    }

    @GetMapping("/out")
    public GoodAndOperData out(@RequestParam(name = "num") Integer num){
        return goodsService.getOneByGoodsOutAndIn(num,0);
    }

    @GetMapping("/in")
    public GoodAndOperData in(@RequestParam(name = "num") Integer num){
        return goodsService.getOneByGoodsOutAndIn(num,1);
    }

    @GetMapping("/getall")
    public List<AllOperData> getAllOperData(){
        return operationService.getAllOper();
    }
    @PostMapping("/updateout")
    public Integer updateout(@RequestBody List<Operation> operation){
        return operationService.updateout(operation);
    }

    @PostMapping("/updatein")
    public Integer updatein(@RequestBody List<OperInUtil> operInUtil){
        return operationService.updatein(operInUtil);
    }


}
