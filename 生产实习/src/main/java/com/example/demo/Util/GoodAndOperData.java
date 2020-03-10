package com.example.demo.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodAndOperData {

    private String name;
    private Integer num;
    private String spec;
    private String a_chara;
    private String b_chara;
    private Integer remains;
    private List<OperUtil> list;

}
