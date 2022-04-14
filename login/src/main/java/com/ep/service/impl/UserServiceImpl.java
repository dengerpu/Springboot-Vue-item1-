package com.ep.service.impl;

import com.ep.domain.User;
import com.ep.mapper.UserMapper;
import com.ep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /*
     * 用户登录
     * */
    @Override
    public User Login(String username, String password, String type) {
        User user = userMapper.Login(username, password, type);
        return user;
    }

    /*
     *
     * 用户注册
     * */
    @Override
    public void saveLoginUser(User user) {
        userMapper.saveLoginUser(user);
    }

    /*
     * 根据用户名查找用户
     * */
    @Override
    public User findUserByUsername(String username) {
        User user = userMapper.findUserByUsername(username);
        return user;
    }


    /*
     * 查询所用用户
     * */
    @Override
    public List<User> findAllUser(String query, int pagenum, int pagesize) {
        int start = (pagenum-1)*pagesize;  //pagenum，第几页，pagesize每页的大小
        List<User> allUser = userMapper.findAllUser(query,start,pagesize);
        return allUser;
    }
    /*
     * 查询用户总数
     * */
    @Override
    public int findTotalUser(String query){
        int totalUser = userMapper.findTotalUser(query);
        return totalUser;
    }
    /*
     *
     * 根据id查询用户
     * */
    @Override
    public User findUserById(int id) {
        User user = userMapper.findUserById(id);
        return user;
    }

    /*
     * 根据id删除用户
     * */
    @Override
    public void deleteUserById(int id) {
        userMapper.deleteUserById(id);
    }

    /*
     * 根据id修改用户
     * */
    @Override
    public void updateUserById(User user) {
        userMapper.updateUserById(user);
    }
}
