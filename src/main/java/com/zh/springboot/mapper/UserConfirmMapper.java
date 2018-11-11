package com.zh.springboot.mapper;

import com.zh.springboot.entity.UserConfirmEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserConfirmMapper {
    @Select("SELECT * FROM userconfirm")
    @Results({
            @Result(property = "userName",  column = "userName"),
            @Result(property = "email", column = "email"),
            @Result(property = "phone", column = "phone")

    })
    List<UserConfirmEntity> getAll();

    @Insert("INSERT INTO userconfirm(email,userName,phone,attendance) " +
            "VALUES(#{email}, #{userName},#{phone},#{attendance})")
    void insert(UserConfirmEntity user);


    @Update("UPDATE userconfirm SET userName=#{userName},phone=#{phone},attendance=#{attendance}" +
            " WHERE email =#{email}")
    void update(UserConfirmEntity user);

    @Select("SELECT * FROM userconfirm WHERE email = #{email}")
    @Results({
            @Result(property = "userName",  column = "userName"),
            @Result(property = "email", column = "email"),
            @Result(property = "phone", column = "phone")
    })
    UserConfirmEntity getUser(String email);
}
