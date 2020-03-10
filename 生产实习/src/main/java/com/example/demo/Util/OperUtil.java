package com.example.demo.Util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperUtil {
    private Integer choice;
    private String oper_time;
    private String company;
    private String settle_time;
    private Double price;
    private Integer total_num;
    private Double amount;
    private String approver;
}
