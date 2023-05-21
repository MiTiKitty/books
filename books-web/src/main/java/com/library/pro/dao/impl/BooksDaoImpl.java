package com.library.pro.dao.impl;

import com.library.pro.dao.BooksDao;
import com.library.pro.model.po.Books;
import com.library.pro.utils.DruidUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.List;

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
                books.getIsbn(), books.getPrice(), books.getTotal(), books.getCurrentStock()};
        return queryRunner.update(sql, params);
    }

    @Override
    public int updateBookById(Books books) throws SQLException {
        String sql = "UPDATE books SET `title`=?, `author`=?, `cover_url`=?, " +
                "`publisher`=?, `publication_date`=?, `isbn`=?, `price`=?, `total`=?, " +
                "`current_stock`=? WHERE `id`=?";
        Object[] params = {books.getTitle(), books.getAuthor(),
                books.getCoverUrl(), books.getPublisher(), books.getPublicationDate(),
                books.getIsbn(), books.getPrice(), books.getTotal(), books.getCurrentStock(), books.getId()};
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

    @Override
    public List<Books> selectBooks(int pageNo, String title, String isbn, Integer category) throws SQLException {
        if (StringUtils.isBlank(title)) {
            title = "%";
        }
        if (StringUtils.isBlank(isbn)) {
            isbn = "%";
        }
        List<Books> books = null;
        if (category == null) {
            String sql = "SELECT b.* FROM books b inner join book_category bc on b.id = bc.book_id where b.title like concat('%', ? , '%') and b.author like concat('%', ? , '%') limit ?, ?";
            BeanListHandler<Books> handler = new BeanListHandler<Books>(Books.class, new BasicRowProcessor(new GenerousBeanProcessor()));
            books = queryRunner.query(sql, handler, title, isbn, (pageNo - 1) * 10, 10);
        } else {
            String sql = "SELECT b.* FROM books b inner join book_category bc on b.id = bc.book_id where b.title like concat('%', ? , '%') and b.author like concat('%', ? , '%') and bc.category_id = ? limit ?, ?";
            BeanListHandler<Books> handler = new BeanListHandler<Books>(Books.class, new BasicRowProcessor(new GenerousBeanProcessor()));
            books = queryRunner.query(sql, handler, title, isbn, category, (pageNo - 1) * 10, 10);
        }
        return books;
    }

    @Override
    public int searchCount(String title, String isbn, Integer category) throws SQLException {
        if (StringUtils.isBlank(title)) {
            title = "%";
        }
        if (StringUtils.isBlank(isbn)) {
            isbn = "%";
        }
        Long count = 0L;
        if (category == null) {
            String sql = "SELECT count(*) FROM books b inner join book_category bc on b.id = bc.book_id where b.title like concat('%', ? , '%') and b.author like concat('%', ? , '%')";
            count = queryRunner.query(sql, new ScalarHandler<>(), title, isbn);
        } else {
            String sql = "SELECT count(*) FROM books b inner join book_category bc on b.id = bc.book_id where b.title like concat('%', ? , '%') and b.author like concat('%', ? , '%') and bc.category_id = ?";
            count = queryRunner.query(sql, new ScalarHandler<>(), title, isbn, category);
        }
        return count.intValue();
    }
}
