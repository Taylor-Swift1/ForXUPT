package com.example.demo.service;

import com.example.demo.Entity.Signupinfo;
import com.example.demo.Entity.User;
import com.example.demo.Util.LoginData;
import com.example.demo.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
@Transactional
public class LoginService {


    @Autowired
    UserDao userDao;
    @Autowired
    SignupinfoDao signupinfoDao;

    //status状态 0，账户名无效 1，成功登录 2，密码错误
    public LoginData login(Integer username, String pwd){
        LoginData loginData=new LoginData();
        User user=userDao.selectOne(username);
        if(user.getId()==null){
            loginData.setStatus(0);
        }
        else if(user.getPwd().equals(pwd)){
            loginData.setStatus(1);
            loginData.setName(user.getName());
            loginData.setRole(user.getRole());
            loginData.setId(user.getId());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String newDate=df.format(new Date());
            Signupinfo signupinfo=new Signupinfo();
            signupinfo.setNum(username);
            signupinfo.setTime(newDate);
            signupinfoDao.save(signupinfo);
        }
        else {
            loginData.setStatus(2);
        }


        return loginData;

    }

}
