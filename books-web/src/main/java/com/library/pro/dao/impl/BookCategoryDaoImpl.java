package com.library.pro.dao.impl;

import com.library.pro.dao.BookCategoryDao;
import com.library.pro.model.po.BookCategory;
import com.library.pro.model.po.BookCategoryNode;
import com.library.pro.utils.DruidUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: BookCategoryDaoImpl <br/>
 * @description: 图书分类持久层实现类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public class BookCategoryDaoImpl implements BookCategoryDao {

    private final QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public int save(BookCategory category) {
        String sql  = "insert into book_category (category_id, book_id) values (?, ?)";
        try {
            return queryRunner.update(sql, category.getCategoryId(), category.getBookId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }

    }

    @Override
    public int del(BookCategory category) {
        String sql  = "delete from book_category where category_id = ? and book_id = ?";
        try {
            return queryRunner.update(sql, category.getCategoryId(), category.getBookId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<BookCategoryNode> groupByCategory() {
        String sql  = "select category_id, count(*) as count from book_category group by category_id";
        try {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("category_id", "categoryId");
            map.put("count", "count");
            BeanListHandler<BookCategoryNode> handler = new BeanListHandler<>(BookCategoryNode.class, new BasicRowProcessor(new BeanProcessor(map)));
            return queryRunner.query(sql, handler);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<BookCategory> selectCategoryListByBookId(int bookId) {
        String sql  = "select * from book_category where book_id =?";
        try {
            BeanListHandler<BookCategory> handler = new BeanListHandler<>(BookCategory.class, new BasicRowProcessor(new GenerousBeanProcessor()));
            return queryRunner.query(sql, handler, bookId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void batchSave(List<BookCategory> list) {
        String sql  = "insert into book_category (category_id, book_id) values (?, ?)";
        try {
            Object[][] params = new Object[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                params[i] = new Object[]{list.get(i).getCategoryId(), list.get(i).getBookId()};
            }
            queryRunner.batch(sql, params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delByBookId(Integer id) {
        String sql = "DELETE FROM book_category WHERE book_id = ?";
        try {
            queryRunner.update(sql, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
