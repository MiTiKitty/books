package com.library.pro.dao.impl;

import com.library.pro.dao.BooksDao;
import com.library.pro.model.po.Books;
import com.library.pro.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @className: BooksDaoImpl <br/>
 * @description: 图书持久层接口实现 <br/>
 * @author: xh <br/>
 * @date: 2023/05/20 <br/>
 * @version: 1.0.0 <br/>
 */
public class BooksDaoImpl implements BooksDao {

    private final QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public int insertBook(Books books) throws SQLException {
        String sql = "INSERT INTO books (`title`, `author`, `cover_url`, " +
                "`publisher`, `publication_date`, `isbn`, `price`, `total`, " +
                "`current_stock`) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {books.getTitle(), books.getAuthor(),
                books.getCoverUrl(), books.getPublisher(), books.getPublicationDate(),
                books.getIsbn(),books.getPrice(),books.getTotal(),books.getCurrentStock()};
        return queryRunner.update(sql, params);
    }

    @Override
    public int updateBookById(Books books) throws SQLException {
        String sql = "UPDATE books SET `title`=?, `author`=?, `cover_url`=?, " +
                "`publisher`=?, `publication_date`=?, `isbn`=?, `price`=?, `total`=?, " +
                "`current_stock`=? WHERE `id`=?";
        Object[] params = {books.getTitle(), books.getAuthor(),
                books.getCoverUrl(), books.getPublisher(), books.getPublicationDate(),
                books.getIsbn(),books.getPrice(),books.getTotal(),books.getCurrentStock(),books.getId()};
        return queryRunner.update(sql, params);
    }

    @Override
    public int deleteBookById(Integer id) throws SQLException {
        String sql = "DELETE FROM books WHERE `id`=?";
        return queryRunner.update(sql, id);
    }

    @Override
    public Books selectBookById(Integer id) throws SQLException {
        String sql = "SELECT * FROM books WHERE `id`=?";
        return queryRunner.query(sql, new BeanHandler<>(Books.class), id);
    }

    @Override
    public Books selectBookBybookname(String title) throws SQLException {
        String sql = "SELECT * FROM books WHERE `title`=?";
        return queryRunner.query(sql, new BeanHandler<>(Books.class), title);
    }
}
