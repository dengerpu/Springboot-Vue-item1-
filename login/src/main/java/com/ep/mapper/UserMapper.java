package com.ep.mapper;


/*
 *
 * 用户持久层接口
 * */

import com.ep.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /*
     * 用户登录
     * 多个参数是需要加上@param
     *
     * */
    @Select("select * from user where username = #{username} and password = #{password} and type = #{type}")
    public User Login(@Param("username") String username, @Param("password")String password, @Param("type") String type);

    /*
     * 用户注册
     * */
    @Insert("insert into user(username,password,name,email,type,date) values (#{username},#{password},#{name},#{email},#{type},#{date})")
    public void saveLoginUser(User user);

    /*
     * 根据用户名查找用户
     * */
    @Select("select * from user where username = #{username}")
    public User findUserByUsername(String username);


    /*
     * 查找所用用户
     * */

    @Select("select * from user where name like concat('%',#{query},'%') limit #{start}, #{pagesize}")
    public List<User> findAllUser(@Param("query") String query, @Param("start") int start, @Param("pagesize") int pagesize);

    /*
     * 查找用户总数
     * */

    @Select("select count(*) from user where name like concat('%',#{query},'%')")
    public int findTotalUser(String query);


    /*
     * 根据id查找用户
     * */
    @Select("select * from user where id = #{id}")
    public User findUserById(int id);

    /*
     * 根据id删除用户
     * */
    @Delete("delete from user where id = #{id}")
    public void deleteUserById(int id);

    /**
     * 更新用户
     * */
    @Update("update user set username = #{username},password = #{password},email = #{email},name = #{name},type = #{type},date = #{date},status = #{status} where id = #{id}")
    public void updateUserById(User user);

}
