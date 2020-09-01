package com.atguigu.test;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisHelloTest {

    @Test
    public void test() throws IOException {
        // 从 XML 中构建 SqlSessionFactory
        // 读取mybatis的核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 通过sqlSessionFac  toryBuilder创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        System.out.println(sqlSessionFactory);
    }

    @Test
    public void test2() throws IOException {
        // 创建SqlSessionFactory对象
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // SqlSession相当于Jdbc中的Connection连接对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 获取UserMapper的实现类
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = userMapper.queryUserById(1);
            System.out.println(user);
        } finally {
            session.close();
        }

    }




}
