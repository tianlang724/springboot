package com.zh.springboot.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by zhang hui on /11/09/2018
 */

public class UserEntity {
    public String userName;
    public String email;
    public String phone;
    public int attendance;
    public String confirmToken;
    public Timestamp registerTime;

    public UserEntity(String userName, String email, String phone, int attendance, String confirmToken, Timestamp time){
        this.userName=userName;
        this.email=email;
        this.phone=phone;
        this.attendance=attendance;
        this.confirmToken=confirmToken;
        this.registerTime=time;
    }
}
