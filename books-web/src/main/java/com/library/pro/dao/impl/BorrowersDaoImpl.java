package com.library.pro.dao.impl;

import com.library.pro.dao.BorrowersDao;
import com.library.pro.model.po.BorrowerNode;
import com.library.pro.model.po.Borrowers;
import com.library.pro.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @className: BorrowersDaoImpl <br/>
 * @description: 借阅者持久层接口实现 <br/>
 * @author: xh <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */

public class BorrowersDaoImpl implements BorrowersDao {

    private final QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
    @Override
    public int insertBorrowers(Borrowers borrowers) throws SQLException {
        String sql = "INSERT INTO borrowers (`id`, `name`, `email`, `phone`, `address`) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {borrowers.getId(), borrowers.getName(), borrowers.getEmail(), borrowers.getPhone(), borrowers.getAddress()};
        return queryRunner.update(sql, params);
    }

    @Override
    public int updateBorrowerById(Borrowers borrowers) throws SQLException {
        String sql = "UPDATE borrowers SET name = ?, email = ?, phone = ?, address = ? WHERE id = ?";
        Object[] params = {borrowers.getName(), borrowers.getEmail(), borrowers.getPhone(), borrowers.getAddress(), borrowers.getId()};
        return queryRunner.update(sql, params);
    }

    @Override
    public Borrowers selectBorrowerById(Integer id) throws SQLException {
        String sql = "SELECT * FROM borrowers WHERE id = ?";
        return queryRunner.query(sql, new BeanHandler<>(Borrowers.class), id);
    }

    @Override
    public Borrowers selectBorrowerByName(String name) throws SQLException {
        String sql = "SELECT * FROM borrowers WHERE name = ?";
        return  queryRunner.query(sql, new BeanHandler<>(Borrowers.class), name);
    }

    @Override
    public List<Borrowers> selectBorrower(String keyword) throws SQLException {
        String sql = "SELECT * FROM borrowers WHERE name LIKE ? OR email LIKE ? OR phone LIKE ? limit 10";
        return queryRunner.query(sql, new BeanListHandler<>(Borrowers.class), "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }

    @Override
    public BorrowerNode search(int pageNo, String keyword) throws SQLException {
        String sql = "SELECT * FROM borrowers WHERE name LIKE ? OR email LIKE ? OR phone LIKE ? limit ?, ?";
        String countSql = "SELECT count(*) FROM borrowers WHERE name LIKE ? OR email LIKE ? OR phone LIKE ?";
        List<Borrowers> borrowers = queryRunner.query(sql, new BeanListHandler<>(Borrowers.class), "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", (pageNo - 1) * 10, 10);
        long total = queryRunner.query(countSql, new ScalarHandler<>(), "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
        return new BorrowerNode(borrowers, total);
    }

}
