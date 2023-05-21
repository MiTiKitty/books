package com.library.pro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.pro.model.po.Books;
import com.library.pro.model.vo.Result;
import com.library.pro.service.BooksService;
import com.library.pro.service.impl.BooksServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @className: BooksServlet <br/>
 * @description: 图书Servlet <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
@WebServlet(name = "books", value = "/book/*")
public class BooksServlet extends HttpServlet {

    private BooksService booksService = new BooksServiceImpl();

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
        } else if (path.endsWith("/del")) {
            delete(req, resp);
        } else {
            resp.sendRedirect("/books/404.html");
        }
    }

    /**
     * (分页)查询图书
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");
        String title = req.getParameter("name");
        String author = req.getParameter("isbn");
        String category = req.getParameter("category");
        Result result = booksService.search(Integer.parseInt(pageNo), title, author, Integer.parseInt(category));
        new ObjectMapper().writeValue(resp.getWriter(), result);
    }

    /**
     * 添加图书
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String coverUrl = req.getParameter("coverUrl");
        String isbn = req.getParameter("isbn");
        String price = req.getParameter("price");
        String publicationDate = req.getParameter("publicationDate");
        String publisher = req.getParameter("publisher");
        String total = req.getParameter("total");
        Books books = new Books(null, title, author, coverUrl, publisher, LocalDate.parse(publicationDate), isbn, new BigDecimal(price), Integer
                .parseInt(total), Integer.parseInt(total));
        Result result = booksService.save(books);
        new ObjectMapper().writeValue(resp.getWriter(), result);
    }

    /**
     * 删除图书
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Result result = booksService.delete(Integer.parseInt(id));
        new ObjectMapper().writeValue(resp.getWriter(), result);
    }

    /**
     * 更新图书
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
