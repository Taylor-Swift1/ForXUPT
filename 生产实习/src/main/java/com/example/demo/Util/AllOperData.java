package com.example.demo.Util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllOperData {

    private String name;
    private Integer num;
    private String spec;
    private String a_chara;
    private String b_chara;
    private Integer remains;
    private Integer choice;
    private String oper_time;
    private String company;
    private String settle_time;
    private Double price;
    private Integer  total_num;
    private Double amount;
    private String approver;
}
