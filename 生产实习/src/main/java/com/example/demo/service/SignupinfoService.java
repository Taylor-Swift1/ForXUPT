package com.example.demo.service;


import com.example.demo.Entity.Signupinfo;
import com.example.demo.Entity.User;
import com.example.demo.Util.AllSignupinfoData;
import com.example.demo.Util.SignupinfoData;
import com.example.demo.dao.SignupinfoDao;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SignupinfoService {

    @Autowired
    SignupinfoDao signupinfoDao;
    @Autowired
    UserDao userDao;

    public SignupinfoData selectAll(Integer num){
        List<Signupinfo> list=signupinfoDao.selectOne(num);

        User user=userDao.selectOne(num);
        SignupinfoData signupinfoData=new SignupinfoData();
        signupinfoData.setNum(num);
        signupinfoData.setName(userDao.selectOne(num).getName());
        signupinfoData.setDepartment(user.getDepartment());
        signupinfoData.setPosition(user.getPosition());
        signupinfoData.setLogin(new ArrayList<>());
        for (Signupinfo s:list){
            signupinfoData.getLogin().add(s.getTime());
        }
        return signupinfoData;
    }

    public List<AllSignupinfoData> selectAllM(){

        List<AllSignupinfoData> newList=new ArrayList<>();
        List<Signupinfo> list=signupinfoDao.selectAll();
        for(Signupinfo signupinfo: list){
            User user=userDao.selectOne(signupinfo.getNum());
            newList.add(new AllSignupinfoData(signupinfo.getNum(),user.getName(),user.getDepartment(),user.getPosition(),signupinfo.getTime()));
        }
        return newList;
    }
}
