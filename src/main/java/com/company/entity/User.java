package com.company.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;


@Data
public class User {

    private long  id;

    private String  nickname;

    private   String  password;

    private  String  salt;

    private  String  head;

    private Date registerDate;

    private  Date  last_login_date;

    private  Integer  loginCount;

}
