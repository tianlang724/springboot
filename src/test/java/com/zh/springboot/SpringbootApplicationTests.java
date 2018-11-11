package com.zh.springboot;

import com.zh.springboot.entity.*;
import com.zh.springboot.mapper.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@SuppressWarnings("all")
	@Autowired
	private UserMapper mapper;

	@SuppressWarnings("all")
	@Autowired
	private UserConfirmMapper confirmMapper;

	@Test
	public void contextLoads() {
//		mapper.insert(new UserEntity("dasdasd", "840859604@qq.com","12346",10,"123456",null));
//		UserEntity usr1=mapper.getUser("840859604@qq.com");
//		UserEntity usr2=mapper.getUserByToken("123456");
//		UserEntity usr3=mapper.getUser("840859604@qq.com");
//		usr3.attendance=100;
//		usr3.confirmToken="qwe";
//		mapper.update(usr3);

		confirmMapper.insert(new UserConfirmEntity("dasdasd", "840859604@qq.com","12346",10,null));
		UserConfirmEntity usr4=confirmMapper.getUser("840859604@qq.com");
		usr4.attendance=100;
		usr4.phone="qwe";
		confirmMapper.update(usr4);


	}

}
