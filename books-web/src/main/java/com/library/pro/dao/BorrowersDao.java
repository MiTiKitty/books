package com.library.pro.dao;

import com.library.pro.model.po.BorrowerNode;
import com.library.pro.model.po.Borrowers;

import java.sql.SQLException;
import java.util.List;

/**
 * @className: BorrowersDao <br/>
 * @description: 借阅者持久层接口 <br/>
 * @author: xh <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public interface BorrowersDao {
    //添加借阅者信息
    int insertBorrowers(Borrowers borrowers) throws SQLException;
    //根据id修改借阅者信息
    int updateBorrowerById(Borrowers borrowers) throws SQLException;
    //根据id查找借阅者信息
    Borrowers selectBorrowerById(Integer id) throws SQLException;
    //根据姓名查找借阅者信息
    Borrowers selectBorrowerByName(String name) throws SQLException;

    List<Borrowers> selectBorrower(String keyword) throws SQLException;

    BorrowerNode search(int pageNo, String keyword) throws SQLException;
}
