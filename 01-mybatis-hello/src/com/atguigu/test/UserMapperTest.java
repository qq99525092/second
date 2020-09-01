package com.atguigu.test;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class UserMapperTest {

    static SqlSessionFactory sqlSessionFactory;

    /**
     * @BeforeClass注解要标识在static的方法上,它会在测试方法执行之前执行,做一些初始化工作. <br/>
     * @BeforeClass 属于junit4测试类
     */
    @BeforeClass
    public static void init() throws IOException {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml") );
    }

    @Test
    public void queryUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.queryUserById(1);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }

    }


    @Test
    public void queryUsers() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);

            mapper.queryUsers().forEach(System.out::println);

        } finally {
            session.close();
        }
    }

    @Test
    public void saveUser() {
        SqlSession session = sqlSessionFactory.openSession();
        try {

            User user = new User(null, "ccccccc", 1);

            UserMapper mapper = session.getMapper(UserMapper.class);

            int i = mapper.saveUser(user);

            System.out.println(user);

            session.commit();//手动提交事务,否则事务就默认回滚
        } finally {
            session.close();
        }
    }

    @Test
    public void deleteUserById() {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);

            userMapper.deleteUserById(5);

            // 写操作,都需要手动提交事务
            session.commit();

        } finally {
            session.close();
        }
    }

    @Test
    public void updateUserById() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);

            userMapper.updateUserById(new User(4,"oomm", 0));

            session.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void queryUserForMap() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);

            Map<Integer, User> map = userMapper.queryUserForMap();
            System.out.println( map.getClass() );
            for (Map.Entry<Integer, User>entry : map.entrySet()) {
                System.out.println( entry.getKey() + " = " +  entry.getValue());
            }
        } finally {
            session.close();
        }
    }

}