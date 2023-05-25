package com.library.pro.service;

import com.library.pro.model.dto.RegisterUserDto;
import com.library.pro.model.vo.LoginUserInfoVo;


/**
 * @className: UsersService <br/>
 * @description: 用户服务接口 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
public interface UsersService {

    /**
     * 用户使用用户名和密码进行登录
     *
     * @param userName
     *         用户名
     * @param password
     *         密码
     * @return 视图用户对象，若为null则表示登录失败
     */
    LoginUserInfoVo loginByUserNameAndPassword(String userName, String password);

    /**
     * 注册一个新用户
     *
     * @param dto
     *         注册用户dto对象
     * @return 成功与否
     */
    Boolean registerUser(RegisterUserDto dto);
}
