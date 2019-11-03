package com.company.service;

import com.company.entity.User;
import com.company.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserMapper userMapper;



    public User getById(long id) {
        //取数据库
        User user = userMapper.getById(id);
        return user;
    }

}
