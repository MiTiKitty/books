package com.library.pro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.pro.model.vo.Result;
import com.library.pro.service.ShowTableService;
import com.library.pro.service.impl.ShowTableServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: BaseServlet <br/>
 * @description: 基础转发Servlet <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
@WebServlet(name = "baseServlet", value = "/base/*")
public class BaseServlet extends HttpServlet {

    private ShowTableService showTableService = new ShowTableServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 进行转发请求
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 进行转发请求
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String path = req.getRequestURI();
        if (path.endsWith("/rate")) {
            // 处理分页搜索请求
            rate(req, resp);
        } else if (path.endsWith("/hot")) {
            hot(req, resp);
        } else if (path.endsWith("/size")) {
            size(req, resp);
        } else if (path.endsWith("/count")) {
            count(req, resp);
        } else if (path.endsWith("/duration")) {
            duration(req, resp);
        }else {
            resp.sendError(404);
        }
    }

    private void rate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String month = req.getParameter("month");
        int m = 6;
        if (StringUtils.isNotBlank(month)) {
            m = Math.max(Integer.parseInt(month), 6);
        }
        Result result = showTableService.queryBorrowRate(m);
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }

    private void hot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String size = req.getParameter("size");
        String month = req.getParameter("month");
        int m = 6, s = 10;
        if (StringUtils.isNotBlank(size)) {
            s = Math.max(Integer.parseInt(month), 10);
        }
        if (StringUtils.isNotBlank(month)) {
            m = Math.max(Integer.parseInt(month), 6);
        }
        Result result = showTableService.queryHotBook(s, m);
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }

    private void size(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = showTableService.queryCategorySize();
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }

    private void count(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String month = req.getParameter("month");
        int m = 6;
        if (StringUtils.isNotBlank(month)) {
            m = Math.max(Integer.parseInt(month), 6);
        }
        Result result = showTableService.queryCategoryCount(m);
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }

    private void duration(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = showTableService.queryCategoryBookDuration();
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        mapper.writeValue(resp.getWriter(), result);
    }
}
