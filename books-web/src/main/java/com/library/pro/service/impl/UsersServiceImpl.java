package com.library.pro.service.impl;

import com.library.pro.dao.UsersDao;
import com.library.pro.dao.impl.UsersDaoImpl;
import com.library.pro.model.dto.RegisterUserDto;
import com.library.pro.model.po.Users;
import com.library.pro.model.vo.LoginUserInfoVo;
import com.library.pro.service.UsersService;
import com.library.pro.utils.PasswordHelper;

import java.sql.SQLException;

/**
 * @className: UsersServiceImpl <br/>
 * @description: 用户服务接口实现类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
public class UsersServiceImpl implements UsersService {

    private final UsersDao usersDao = new UsersDaoImpl();

    @Override
    public LoginUserInfoVo loginByUserNameAndPassword(String userName, String password) {
        Users users = null;
        try {
            users = usersDao.selectUserByUsername(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (users == null) {
            return null;
        }
        if (!PasswordHelper.checkPassword(password, users.getPassword())) {
            return null;
        }
        return new LoginUserInfoVo(users);
    }

    @Override
    public Boolean registerUser(RegisterUserDto dto) {
        Users users = new Users();
        users.setUsername(dto.getUsername());
        users.setPassword(PasswordHelper.generatePassword(dto.getPassword()));
        users.setEmail(dto.getEmail());
        users.setPhone(dto.getPhone());
        users.setAddress(dto.getAddress());
        try {
            usersDao.insertUser(users);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
