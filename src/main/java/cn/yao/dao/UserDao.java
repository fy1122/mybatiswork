package cn.yao.dao;

import cn.yao.domain.QueryInfo;
import cn.yao.domain.User;

import java.util.List;

/**
 * 定义持久层
 */
public interface UserDao {
    /**
     * 查询用户
     * @return
     */
    List<User> findAll ();

    /**
     * 保存用户
     */
    void saveUser(User user);
    /**
     * 更新用户
     */
    void upDateUser(User user);
    /**
     * 删除用户
     */
    void deleteUser(int id);
    /**
     * 根据id查找
     */
    User findById(int id);
    /**
     * 根据name模糊查询
     */
    List<User>findByName(String username);
    /**
     * 测试用户的总记录数
     */
    int findTotal();
    /**
     * 通过其他类chax
     */
    List<User>findByOther(QueryInfo info);
}
