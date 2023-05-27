package com.library.pro.dao.impl;

import com.library.pro.dao.CategoriesDao;
import com.library.pro.model.po.Categories;
import com.library.pro.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @className: CategoriesDaoImpl <br/>
 * @description: 分类持久层实现类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public class CategoriesDaoImpl implements CategoriesDao {

    private final QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public int saveCategory(Categories category) {
        String sql = "INSERT INTO categories (`name`) values(?)";
        try {
            return queryRunner.update(sql, category.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Categories> selectAll() {
        String sql = "SELECT * FROM categories";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(Categories.class));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
