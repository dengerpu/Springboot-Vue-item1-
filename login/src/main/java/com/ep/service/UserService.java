package com.ep.service;

import com.ep.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
/*
* 用户业务层
* */
@Service
public interface UserService {
    /*
     * 用户登录
     * */
    public User Login(String username, String password, String type);

    /*
     * 用户注册
     * */
    public void saveLoginUser(User user);

    /*
     * 根据用户名查找用户
     * */
    public User findUserByUsername(String username);

    /*
     * 查找所用用户
     * */
    public List<User> findAllUser(String query, int pagenum, int pagesize);

    /*
     * 查询用户总数
     * */
    public int findTotalUser(String query);


    /*
     * 根据id查找用户
     * */
    public User findUserById(int id);

    /*
     * 根据id删除用户
     * */
    public void deleteUserById(int id);

    /**
     * 更新用户
     * */
    public void updateUserById(User user);
}
