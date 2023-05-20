package com.library.pro.dao;

import com.library.pro.model.po.Books;

import java.sql.SQLException;

/**
 * @className: BooksDao <br/>
 * @description: 图书dao接口 <br/>
 * @author: xh <br/>
 * @date: 2023/05/20 <br/>
 * @version: 1.0.0 <br/>
 */
public interface BooksDao {
    /**
    * 添加书籍
    */
    int insertBook(Books books) throws SQLException;
    /**
     * 根据id修改书籍
     */
    int updateBookById(Books books) throws SQLException;
    /**
     * 根据id删除书籍
     */
    int deleteBookById(Integer id) throws SQLException;
    /**
     * 根据id查询书籍
     */
    Books selectBookById(Integer id) throws SQLException;
    /**
     * 根据书名查询书籍
     */
    Books selectBookBybookname(String title) throws SQLException;
}
