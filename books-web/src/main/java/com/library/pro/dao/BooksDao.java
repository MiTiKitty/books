package com.library.pro.dao;

import com.library.pro.model.po.Books;

import java.sql.SQLException;
import java.util.List;

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

    /**
     * 搜索图书
     *
     * @param pageNo
     * @param title
     * @param isbn
     * @param category
     * @return
     */
    List<Books> selectBooks(int pageNo, String title, String isbn, Integer category) throws SQLException;

    /**
     * 搜索图书数量
     *
     * @param title
     * @param isbn
     * @return 数量
     */
    int searchCount(String title, String isbn, Integer category) throws SQLException;

    void updateBookStock(int bookId, int count);

    List<Books> selectOne(String title) throws SQLException;
}
