package com.library.pro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.pro.model.dto.RegisterUserDto;
import com.library.pro.model.vo.LoginUserInfoVo;
import com.library.pro.service.UsersService;
import com.library.pro.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: UsersServlet <br/>
 * @description: 用户Servlet <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
@WebServlet(name = "users", value = "/users/*")
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String path = req.getRequestURI();
        if (path.endsWith("/login")) {
            // 处理登录请求
            login(req, resp);
        } else if (path.endsWith("/register")) {
            register(req, resp);
        } else {
            resp.sendRedirect("/books/404.html");
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || password == null) {
            resp.sendRedirect("/books/404.html");
            return;
        }
        UsersService usersService = new UsersServiceImpl();
        LoginUserInfoVo loginUserInfoVo = usersService.loginByUserNameAndPassword(username, password);
        if (loginUserInfoVo == null) {
            resp.sendRedirect("/books/404.html");
        }else {
            new ObjectMapper().writeValue(resp.getWriter(), loginUserInfoVo);
        }
    }

    /**
     * 处理用户提交的注册请求
     * @param req HTTP请求对象
     * @param resp HTTP响应对象
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从请求参数中获取用户名、密码、电子邮件、电话和地址等信息
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        // 如果其中有一个为空，则返回错误信息
        if (username == null || password == null || email == null || phone == null || address == null) {
            resp.getWriter().write("Error: Missing required fields.");
            return;
        }
        // 调用UsersService的register方法进行注册，该方法返回一个布尔值，表示注册是否成功
        UsersService usersService = new UsersServiceImpl();
        RegisterUserDto registerUserDto = new RegisterUserDto(username, password, email, phone, address);
        boolean result = usersService.registerUser(registerUserDto);
        // 如果注册成功，则返回成功信息，否则返回错误信息
        if (!result) {
            resp.getWriter().write("Error: Failed to register.");
        } else {
            new ObjectMapper().writeValue(resp.getWriter(), true);
        }
    }

}
