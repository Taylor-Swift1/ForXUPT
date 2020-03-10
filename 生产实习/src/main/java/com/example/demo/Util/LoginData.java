package com.example.demo.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class LoginData {
    private Integer status;
    private Integer id;
    private Integer role;
    private String name;
}
