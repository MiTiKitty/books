package com.library.pro.dao.impl;

import com.library.pro.dao.UsersDao;
import com.library.pro.model.po.Users;
import com.library.pro.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @className: UsersDaoImpl <br/>
 * @description: 用户持久层接口实现 <br/>
 * @author: xh <br/>
 * @date: 2023/05/20 <br/>
 * @version: 1.0.0 <br/>
 */
public class UsersDaoImpl implements UsersDao {

    private final QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public int insertUser(Users user) throws SQLException {
        String sql = "INSERT INTO users (`username`, `password`, `email`, `phone`, `address`) values (?, ?, ?, ?, ?)";
        Object[] params = {user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), user.getAddress()};
        return queryRunner.update(sql, params);
    }

    @Override
    public int updateUserById(Users user) throws SQLException {
        String sql = "update users set `password` = ?, email = ?, phone = ?, address = ? where id = ?";
        Object[] params = {user.getPassword(), user.getEmail(), user.getPhone(), user.getAddress(), user.getId()};
        return queryRunner.update(sql, params);
    }

    @Override
    public int deleteUserById(Integer id) throws SQLException {
        String sql = "delete users where id = ?";
        Object[] params = {id};
        return queryRunner.update(sql, params);
    }

    @Override
    public Users selectUserById(Integer id) throws SQLException {
        String sql = "select * from users where id = ?";
        ResultSetHandler<Users> result = new BeanHandler<>(Users.class);
        return queryRunner.query(sql, result, id);
    }

    @Override
    public Users selectUserByUsername(String username) throws SQLException {
        String sql = "select * from users where username = ?";
        ResultSetHandler<Users> result = new BeanHandler<>(Users.class);
        return queryRunner.query(sql, result, username);
    }

    @Override
    public List<Users> selectUsers() throws SQLException {
        String sql = "select * from users";
        BeanListHandler<Users> result = new BeanListHandler<Users>(Users.class);
        return queryRunner.query(sql, result);
    }
}
