package com.zh.springboot.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by zhang hui on /11/09/2018
 */

public class UserConfirmEntity {
    public String userName;
    public String email;
    public String phone;
    public int attendance;
    public Timestamp confirmTime;

    public UserConfirmEntity(String userName, String email, String phone, int attendance, Timestamp confirmTime){
        this.userName=userName;
        this.email=email;
        this.phone=phone;
        this.attendance=attendance;
        this.confirmTime=confirmTime;
    }
}
