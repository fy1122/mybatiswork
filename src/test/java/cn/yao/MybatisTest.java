package cn.yao;

import cn.yao.dao.UserDao;
import cn.yao.domain.QueryInfo;
import cn.yao.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


public class MybatisTest {
    private  InputStream is;
    private  SqlSession sqlSession;
    private  UserDao dao;
    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(is);
        sqlSession = build.openSession();
        dao = sqlSession.getMapper(UserDao.class);
    }
    /**
     * 测试查询所有
     */
    @Test
    public void findAll() throws IOException {
        init();
        List<User> users = dao.findAll();
        for(User user:users){
            System.out.println(user);
        }
    }
    @Test
    public void saveUser() throws IOException {
        User user=new User("付瑶",new Date(),"男","asdfsa");
        //1读取配置文件，生成字节输入流
//        InputStream is = Resources.getResourceAsStream("SqlMapperConfig.xml");
//        //2获取工厂
//        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
//        //3获取sqlsession对象
//        SqlSession sqlSession = factory.openSession();
//        //4获取dao的代理对象
        init();
        //5执行
        dao.saveUser(user);
        System.out.println(user);

    }
    @Test
    public void upDateUser(){
        User user=new User("付明波",new Date(),"男","要气恼劳务的小屋子");
        user.setId(50);
        dao.upDateUser(user);
    }
    @Test
    public void deleteUser(){
        dao.deleteUser(50);
    }
    @Test
    public void findById(){
        User user = dao.findById(41);
        System.out.println(user);
    }
    @Test
    public void findByName(){
        List<User> byName = dao.findByName("%王%");
        for(User user:byName){
            System.out.println(user);
        }
    }
    @Test
    public void findTotal(){
        int total = dao.findTotal();
        System.out.println(total);
    }
    @Test
    public void findByOther(){
        User user=new User();
        user.setUsername("%王%");
        QueryInfo info=new QueryInfo(user);
        List<User> byOther = dao.findByOther(info);
        for(User user1:byOther){
            System.out.println(user1);
        }
    }
    @After
    public void destroy() throws IOException {
        //手动提交事务
        sqlSession.commit();
        sqlSession.close();
        is.close();

    }
}
