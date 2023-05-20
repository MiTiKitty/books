package com.library.pro.controller;

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
            // 处理注册请求
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
        if (loginUserInfoVo != null) {
            resp.sendRedirect("/books/home.html");
        } else {
            resp.sendRedirect("/books/404.html");
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
