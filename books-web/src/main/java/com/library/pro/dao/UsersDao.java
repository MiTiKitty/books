package com.library.pro.dao;

import com.library.pro.model.po.Users;

import java.sql.SQLException;
import java.util.List;

/**
 * @className: UsersDao <br/>
 * @description: 用户dao接口 <br/>
 * @author: xh <br/>
 * @date: 2023/05/20 <br/>
 * @version: 1.0.0 <br/>
 */
public interface UsersDao {

    /**
     * 添加用户
     *
     * @param user
     *         用户
     * @return
     */
    int insertUser(Users user) throws SQLException;

    /**
     * 根据id修改用户
     *
     * @param user
     *         用户
     * @return
     */
    int updateUserById(Users user) throws SQLException;

    /**
     * 删除用户
     *
     * @param id
     *         id
     * @return
     */
    int deleteUserById(Integer id) throws SQLException;

    /**
     * 根据id查询用户
     *
     * @param id
     *         id
     * @return
     */
    Users selectUserById(Integer id) throws SQLException;

    /**
     * 根据用户名查找用户
     *
     * @param username
     *         用户名
     * @return
     */
    Users selectUserByUsername(String username) throws SQLException;

    /**
     * 查找所有用户
     *
     * @return
     */
    List<Users> selectUsers() throws SQLException;
}
