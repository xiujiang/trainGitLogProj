package com.blog.controller;

import com.blog.base.BaseController;
import com.blog.base.Response;
import com.blog.dao.AnimalDao;
import com.blog.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.blog.service.UserService;

/**
 * author:xiujiang.liu
 * Date:2018/12/11
 * Time:23:49
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User> {

    @Autowired
    AnimalDao animalDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public UserController(UserService userService) {
        super(userService);
        this.userService= userService;
    }
    UserService userService;
    @GetMapping("/login")
    public Response login(String email,String password){
        User user = new User(null,password,email,null,null,null);
        logger.info("user信息为:{}",user);
        if(ObjectUtils.isEmpty(user) || StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPassword())){
            return new Response("10","用户名或密码错误",null);
        }
        User loginUser = this.userService.getUserByEmail(user);
        if(!ObjectUtils.isEmpty(loginUser)){
            logger.info("登录成功");
            loginUser.setPassword("");
            return new Response().success(loginUser);
        }else{
            logger.info("登录失败");
            return new Response("10","用户名或密码错误",null);
        }
    }

    @GetMapping("/register")
    public Response register(User user){
        logger.info("user信息为:{}",user);
        if(ObjectUtils.isEmpty(user) || StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPassword())){
            return new Response("10","注册信息不完整",null);
        }
        User regisgUser = this.userService.getUserByEmail(user);
        if(!ObjectUtils.isEmpty(regisgUser)){
            return new Response("10","当前邮箱已经注册，请登录",null);
        }
        if(ObjectUtils.isEmpty(user.getName())){
            user.setName("用户"+System.currentTimeMillis());
        }
        this.userService.add(user);
        return new Response("00","注册成功",user);
    }

    @GetMapping("/login/out")
    public Response loginOut(String token){
        logger.info("token信息为:{}",token);
        if(ObjectUtils.isEmpty(token)){
            return new Response("10","退出失败",null);
        }
        //先用redis退出吧
        return new Response("00","退出成功",null);
    }

    @GetMapping("/getUser")
    @ResponseBody
    public Response<User> getUser(Integer id){
        System.out.println(animalDao.getById(1));
        return new Response().success(this.userService.getUser(id));
    }
}
