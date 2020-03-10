package com.example.demo.Util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperInUtil {
    private Integer user_num;
    private String name;
    private Integer num;
    private String spec;
    private String a_chara;
    private String b_chara;
    private String unit;
    private Integer total_num;
    private Double price;
    private Double amount;
    private String oper_time;
    private Integer company_id;
    private String settle_time;
    private String approver;
}
