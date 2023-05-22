package com.library.pro.dao;

import com.library.pro.model.po.Loans;
import com.library.pro.model.po.LoansNode;
import com.library.pro.model.vo.LoansVO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * @className: LoansDao <br/>
 * @description: 借阅持久层接口 <br/>
 * @author: xh <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public interface LoansDao {
    //添加借阅记录
    int insertLoan(Loans loans) throws SQLException;
    //修改借阅记录
    int updeteLoanById(Loans loans) throws SQLException;
    //根据借阅id查询借阅记录
    Loans selectLoanById(Integer id) throws SQLException;
    //根据图书id查询借阅记录
    Loans selectLoanBybookId(Integer bookId) throws SQLException;
    //根据借阅者id查询借阅记录
    Loans selectLoanByborrowerId(Integer borrowerId) throws SQLException;

    LoansNode search(int pageNo, String title, String borrower, Date startDate, Date endDate) throws SQLException;

    LoansVO info(int id) throws SQLException;
}
