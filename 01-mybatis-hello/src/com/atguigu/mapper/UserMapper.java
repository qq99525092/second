package com.atguigu.mapper;

import com.atguigu.pojo.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;


public interface UserMapper {

    // mybatis的hello world ==>>> 要求我们查询数据库表t_user中id为1的记录
    public User queryUserById(Integer id);

    public List<User> queryUsers();

    public int saveUser(User user);

    public int deleteUserById(Integer id);

    public int updateUserById(User user);

    /**
     * 1 将查询回来每一行记录都封装为USer对象. <br>
     * 2 按照指定的列做为key保存查询到的数据,封装为Map <br/>
     * @return
     */
    @MapKey("id")
    public Map<Integer,User>queryUserForMap();

}
