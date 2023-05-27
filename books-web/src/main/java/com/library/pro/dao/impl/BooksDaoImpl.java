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

import java.math.BigInteger;
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
        // 创建 ScalarHandler 对象
        ScalarHandler<BigInteger> scalarHandler = new ScalarHandler<>();
        // 将 ScalarHandler 对象传递给 insert() 方法
        BigInteger bookId = queryRunner.insert(sql, scalarHandler, params);
        if (bookId == null) {
            return -1;
        }
        // 将主键值设置到 Books 对象的 id 属性中
        books.setId(bookId.intValue());
        // 返回自增长生成的主键值
        return bookId.intValue();
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
        return queryRunner.query(sql, new BeanHandler<>(Books.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
    }

    @Override
    public Books selectBookBybookname(String title) throws SQLException {
        String sql = "SELECT * FROM books WHERE `title`=?";
        return queryRunner.query(sql, new BeanHandler<>(Books.class, new BasicRowProcessor(new GenerousBeanProcessor())), title);
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
            String sql = "SELECT DISTINCT b.* FROM books b inner join book_category bc on b.id = bc.book_id where b.title like concat('%', ? , '%') and b.author like concat('%', ? , '%') limit ?, ?";
            BeanListHandler<Books> handler = new BeanListHandler<Books>(Books.class, new BasicRowProcessor(new GenerousBeanProcessor()));
            books = queryRunner.query(sql, handler, title, isbn, (pageNo - 1) * 10, 10);
        } else {
            String sql = "SELECT DISTINCT b.* FROM books b inner join book_category bc on b.id = bc.book_id where b.title like concat('%', ? , '%') and b.author like concat('%', ? , '%') and bc.category_id = ? limit ?, ?";
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
            String sql = "SELECT COUNT(*)\n" +
                    "FROM books b " +
                    "WHERE b.title LIKE CONCAT('%', ?, '%') " +
                    "AND b.author LIKE CONCAT('%', ?, '%') " +
                    "AND EXISTS (" +
                    "  SELECT * FROM book_category bc " +
                    "  WHERE bc.book_id = b.id " +
                    ");";
            count = queryRunner.query(sql, new ScalarHandler<>(), title, isbn);
        } else {
            String sql = "SELECT COUNT(*)\n" +
                    "FROM books b " +
                    "WHERE b.title LIKE CONCAT('%', ?, '%') " +
                    "AND b.author LIKE CONCAT('%', ?, '%') " +
                    "AND EXISTS (" +
                    "  SELECT * FROM book_category bc " +
                    "  WHERE bc.book_id = b.id " +
                    "  and bc.category_id in (?)" +
                    ");";
            count = queryRunner.query(sql, new ScalarHandler<>(), title, isbn, category);
        }
        return count.intValue();
    }

    @Override
    public void updateBookStock(int id, int count) {
        String sql = "UPDATE books SET current_stock = current_stock + ? WHERE id = ?";
        try {
            queryRunner.update(sql, count, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Books> selectOne(String title) throws SQLException {
        String sql = "SELECT id, title, author, current_stock FROM books WHERE (title like ? or author like ?) and current_stock > 0 limit 10";
        BeanListHandler<Books> handler = new BeanListHandler<Books>(Books.class, new BasicRowProcessor(new GenerousBeanProcessor()));
        return queryRunner.query(sql, handler, "%" + title + "%", "%" + title + "%");
    }
}
