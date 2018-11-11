package com.zh.springboot.mapper;

import com.zh.springboot.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "userName",  column = "userName"),
            @Result(property = "email", column = "email"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "confirmToken", column = "confirmToken"),

    })
    List<UserEntity> getAll();


    @Insert("INSERT INTO user(email,userName,phone,attendance,confirmToken) " +
            "VALUES(#{email}, #{userName},#{phone},#{attendance},#{confirmToken})")
    void insert(UserEntity user);


    @Update("UPDATE user SET userName=#{userName},phone=#{phone},attendance=#{attendance}," +
            "confirmToken=#{confirmToken} WHERE email =#{email}")
    void update(UserEntity user);

    @Select("SELECT * FROM user WHERE email = #{email}")
    @Results({
            @Result(property = "userName",  column = "userName"),
            @Result(property = "email", column = "email"),
            @Result(property = "confirmToken", column = "confirmToken"),
            @Result(property = "phone", column = "phone")
    })
    UserEntity getUser(String email);

    @Select("SELECT * FROM user WHERE confirmToken = #{token}")
    @Results({
            @Result(property = "userName",  column = "userName"),
            @Result(property = "email", column = "email"),
            @Result(property = "confirmToken", column = "confirmToken"),
            @Result(property = "phone", column = "phone")
    })
    UserEntity getUserByToken(String token);

}
