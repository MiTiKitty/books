package com.library.pro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.pro.model.po.Borrowers;
import com.library.pro.model.vo.Result;
import com.library.pro.service.BorrowersService;
import com.library.pro.service.impl.BorrowersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: BorrowersServlet <br/>
 * @description: 借阅者servlet类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
@WebServlet(name = "borrowers", value = "/borrowers/*")
public class BorrowersServlet extends HttpServlet {

    private BorrowersService borrowersService = new BorrowersServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String path = req.getRequestURI();
        if (path.endsWith("/search")) {
            search(req, resp);
        } else if (path.endsWith("/add")) {
            add(req, resp);
        } else if (path.endsWith("/edit")) {
            edit(req, resp);
        } else if (path.endsWith("/find")) {
            searchOne(req, resp);
        } else if (path.endsWith("/info")) {
            info(req, resp);
        } else {
            resp.sendRedirect("/books/404.html");
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        Borrowers borrowers = new Borrowers(null, name, email, phone, address);
        Result result = borrowersService.add(borrowers);
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        Borrowers borrowers = new Borrowers(Integer.parseInt(id), name, email, phone, address);
        Result result = borrowersService.edit(borrowers);
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");
        String keyword = req.getParameter("keyword");
        Result result = borrowersService.search(Integer.parseInt(pageNo), keyword);
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }

    private void searchOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        Result result = borrowersService.searchOne(keyword);
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }

    private void info(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Result result = borrowersService.info(Integer.parseInt(id));
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }

}
