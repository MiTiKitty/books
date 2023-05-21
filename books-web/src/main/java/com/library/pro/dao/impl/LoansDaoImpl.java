package com.library.pro.dao.impl;

import com.library.pro.dao.LoansDao;
import com.library.pro.model.po.Loans;
import com.library.pro.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class LoansDaoImpl implements LoansDao {

    private final QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public int insertLoan(Loans loans) throws SQLException {
        String sql = "INSERT INTO loans (book_id, borrower_id, loan_date, due_date, return_date) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {loans.getBookId(), loans.getBorrowerId(), loans.getLoanDate(), loans.getDueDate(), loans.getReturnDate()};
        return queryRunner.update(sql, params);
    }

    @Override
    public int updeteLoanById(Loans loans) throws SQLException {
        String sql = "UPDATE loans SET book_id = ?, borrower_id = ?, loan_date = ?, due_date = ?, return_date = ? WHERE id = ?";
        Object[] params = {loans.getBookId(), loans.getBorrowerId(), loans.getLoanDate(), loans.getDueDate(), loans.getReturnDate(), loans.getId()};
        return queryRunner.update(sql, params);
    }

    @Override
    public Loans selectLoanById(Integer id) throws SQLException {
        String sql = "SELECT * FROM loans WHERE id = ?";
        return queryRunner.query(sql, new BeanHandler<>(Loans.class), id);
    }

    @Override
    public Loans selectLoanBybookId(Integer bookId) throws SQLException {
        String sql = "SELECT * FROM loans WHERE book_id = ?";
        return queryRunner.query(sql, new BeanHandler<>(Loans.class), bookId);
    }

    @Override
    public Loans selectLoanByborrowerId(Integer borrowerId) throws SQLException {
        String sql = "SELECT * FROM loans WHERE borrower_id = ?";
        return queryRunner.query(sql, new BeanHandler<>(Loans.class), borrowerId);
    }
}
