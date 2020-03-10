package com.example.demo.Util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupinfoData {
    private Integer num;
    private String name;
    private String department;
    private String position;
    List<String> login;
}
