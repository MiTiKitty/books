package com.library.pro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.pro.model.vo.Result;
import com.library.pro.service.LoansService;
import com.library.pro.service.impl.LoansServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * @className: LoansServlet <br/>
 * @description: 借阅Servlet <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
@WebServlet(name = "loans", value = "/loans/*")
public class LoansServlet extends HttpServlet {

    private LoansService loansService = new LoansServiceImpl();

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
            // 处理分页搜索请求
            search(req, resp);
        } else if (path.endsWith("/add")) {
            add(req, resp);
        } else if (path.endsWith("/edit")) {
            update(req, resp);
        } else if (path.endsWith("/info")) {
            info(req, resp);
        } else {
            resp.sendRedirect("/books/404.html");
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String date = req.getParameter("date");
        String status = req.getParameter("status");
        Result result = loansService.update(Integer.parseInt(id), Integer.parseInt(status), StringUtils.isBlank(date) ? null : Date
                .valueOf(date));
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String bookId = req.getParameter("bookId");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String status = req.getParameter("status");
        Result result = loansService.add(Integer.parseInt(userId), Integer.parseInt(bookId), Date.valueOf(startDate), Date
                .valueOf(endDate), Integer.parseInt(status));
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");
        String title = req.getParameter("name");
        String borrower = req.getParameter("borrower");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        Date d1 = StringUtils.isBlank(startDate) ? null : Date.valueOf(startDate);
        Date d2 = StringUtils.isBlank(endDate) ? null : Date.valueOf(endDate);
        Result result = loansService.search(Integer.parseInt(pageNo), title, borrower, d1, d2);
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }

    private void info(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Result result = loansService.info(Integer.parseInt(id));
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }

}
