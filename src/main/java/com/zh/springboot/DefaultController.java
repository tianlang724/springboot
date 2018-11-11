package com.zh.springboot;

import com.zh.springboot.entity.*;
import com.zh.springboot.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhang hui on /11/08/2018
 */

@Controller
@RequestMapping("/")
public class DefaultController{


    @Autowired
    private JavaMailSender mailSender;

    @SuppressWarnings("all")
    @Autowired
    private UserMapper userMapper;

    @SuppressWarnings("all")
    @Autowired
    private UserConfirmMapper userConfirmMapper;

    @RequestMapping("/index")
    public String index()  {
        return "forward:index.html";
    }

    @RequestMapping("/register")
    public String register()  {
        return "forward:register.html";
    }

    @RequestMapping("/information")
    public String helloHtml(Map<String,Object> map){

        map.put("hello","from TemplateController.helloHtml");
        return"/information";
    }



    @RequestMapping("/confirm")
    public String confirm(HttpServletRequest request)  {
        System.out.println(request.getParameter("token"));
        String token=request.getParameter("token");
        UserEntity usr=userMapper.getUserByToken(token);
        if(usr!=null&& usr.confirmToken.equals(token)){
            UserConfirmEntity confirm=userConfirmMapper.getUser(usr.email);
            if(confirm==null){
                UserConfirmEntity nusr=new UserConfirmEntity(usr.userName,usr.email,usr.phone,usr.attendance,null);
                userConfirmMapper.insert(nusr);
            }else{
                confirm.phone=usr.phone;
                confirm.userName=usr.userName;
                confirm.attendance=usr.attendance;
                userConfirmMapper.update(confirm);
            }

            return "forward:confirm_success.html";
        }
        return "forward:confirm_fail.html";
    }


    @RequestMapping(value = "/register_result", method = {RequestMethod.POST,RequestMethod.GET})
    public String register(HttpServletRequest request) {

        String uuid = UUID.randomUUID().toString();
        try {
            String name = request.getParameter("username");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            Integer attandence = Integer.parseInt(request.getParameter("attendance"));

            UserEntity usr = userMapper.getUser(email);

            if (usr != null) {
                usr.userName=name;
                usr.phone=phone;
                usr.attendance=attandence;
                usr.confirmToken=uuid;
                userMapper.update(usr);
            } else {
                UserEntity newUsr = new UserEntity(name, email, phone, attandence, uuid,null);
                userMapper.insert(newUsr);
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("840859604@qq.com");
            message.setTo(request.getParameter("email"));
            message.setSubject("Register comfirm");
            String url = "http://115.159.111.231:8080/move2019/confirm?token=" + uuid;
            StringBuilder sb=new StringBuilder();
            sb.append("Hi ");
            sb.append(name);
            sb.append(",\n");
            sb.append("You register ");
            sb.append(attandence);
            sb.append(" people for the move2019. Please click this url: ");
            sb.append(url);
            sb.append(" to confirm.");

            message.setText(sb.toString());
            mailSender.send(message);

            System.out.println("send");
            return "redirect:register_success.html";

        } catch (Exception e) {
            System.out.println("send failed");
        }
        return "redirect:register_fail.html";
    }
}
