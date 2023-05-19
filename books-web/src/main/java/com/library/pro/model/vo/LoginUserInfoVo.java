package com.library.pro.model.vo;

import com.library.pro.model.po.Users;

/**
 * @className: LoginUserInfoVo <br/>
 * @description: 用户登录成功vo <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
public class LoginUserInfoVo {

    private Integer id;

    private String username;

    private String email;

    private String phone;

    private String address;

    public LoginUserInfoVo() {
    }

    public LoginUserInfoVo(Integer id, String username, String email, String phone, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public LoginUserInfoVo(Users users) {
        id = users.getId();
        username = users.getUsername();
        email = users.getEmail();
        phone = users.getPhone();
        address = users.getAddress();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoginUserInfoVo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
