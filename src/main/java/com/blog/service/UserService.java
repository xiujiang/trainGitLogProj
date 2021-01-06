package com.blog.service;

import com.blog.base.BaseService;
import com.blog.dao.UserDao;
import com.blog.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

/**
 * author:xiujiang.liu
 * Date:2018/12/11
 * Time:23:45
 */
@Service
public class UserService extends BaseService<User> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    UserDao userDao;
    public User getUserByEmail(User user){
        logger.info("用户信息1为:{}",user);
        User thisUser = this.userDao.findUserByEmailAndPsd(user.getEmail(),user.getPassword());
        if(ObjectUtils.isEmpty(thisUser)){
            return null;
        }else{
            return thisUser;
        }
    }

    @Autowired
    public UserService(UserDao userDao) {
        super(userDao);
        this.userDao=userDao;
    }

    public boolean isExist(int userId){
        logger.info("log:{}",userId);
        User user = this.userDao.getOne(userId);
        if(ObjectUtils.isEmpty(user)){
            return false;
        }else{
            return true;
        }
    }


    public User getUser(Integer id ){
//        return this.userDao.getUsers();
        return null;
    }
}
