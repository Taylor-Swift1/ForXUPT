package com.example.demo.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "operation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer num;
    private Integer choice;
    private Integer goods_id;
    private String oper_time;
    private Integer company_id;
    private String settle_time;
    private Double price;
    private Integer total_num;
    private Double amount;
    private String approver;






}
