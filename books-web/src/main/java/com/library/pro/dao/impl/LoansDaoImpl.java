package com.library.pro.dao.impl;

import com.library.pro.dao.LoansDao;
import com.library.pro.model.po.Loans;
import com.library.pro.model.po.LoansNode;
import com.library.pro.model.vo.LoansVO;
import com.library.pro.utils.DruidUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoansDaoImpl implements LoansDao {

    private final QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public int insertLoan(Loans loans) throws SQLException {
        String sql = "INSERT INTO loans (book_id, borrower_id, loan_date, due_date, `status`) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {loans.getBookId(), loans.getBorrowerId(), loans.getLoanDate(), loans.getDueDate(), loans.getStatus()};
        return queryRunner.update(sql, params);
    }

    @Override
    public int updeteLoanById(Loans loans) throws SQLException {
        String sql = "UPDATE loans SET return_date = ? , `status` = ? WHERE id = ?";
        Object[] params = {loans.getReturnDate(), loans.getStatus(), loans.getId()};
        return queryRunner.update(sql, params);
    }

    @Override
    public Loans selectLoanById(Integer id) throws SQLException {
        String sql = "SELECT * FROM loans WHERE id = ?";
        return queryRunner.query(sql, new BeanHandler<>(Loans.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
    }

    @Override
    public Loans selectLoanBybookId(Integer bookId) throws SQLException {
        String sql = "SELECT * FROM loans WHERE book_id = ?";
        return queryRunner.query(sql, new BeanHandler<>(Loans.class, new BasicRowProcessor(new GenerousBeanProcessor())), bookId);
    }

    @Override
    public Loans selectLoanByborrowerId(Integer borrowerId) throws SQLException {
        String sql = "SELECT * FROM loans WHERE borrower_id = ?";
        return queryRunner.query(sql, new BeanHandler<>(Loans.class, new BasicRowProcessor(new GenerousBeanProcessor())), borrowerId);
    }

    @Override
    public LoansNode search(int pageNo, String title, String borrower, Date startDate, Date endDate) throws SQLException {
        String sql = "SELECT b.id as id, bk.title as bookName, r.name as borrower, b.loan_date as loan_date, b.return_date as return_date, b.status, "
                + "bk.title, r.name "
                + "FROM loans b "
                + "inner JOIN books bk ON b.book_id = bk.id "
                + "inner JOIN borrowers r ON b.borrower_id = r.id "
                + "WHERE 1 = 1";
        String countSql = "SELECT count(*) "
                + "FROM loans b "
                + "inner JOIN books bk ON b.book_id = bk.id "
                + "inner JOIN borrowers r ON b.borrower_id = r.id "
                + "WHERE 1 = 1";
        List<Object> params = new ArrayList<>();
        List<Object> countParams = new ArrayList<>();
        if (title != null && !title.isEmpty()) {
            sql += " AND bk.title LIKE ?";
            countSql += " AND bk.title LIKE ?";
            params.add("%" + title + "%");
            countParams.add("%" + title + "%");
        }
        if (borrower != null && !borrower.isEmpty()) {
            sql += " AND r.name LIKE ?";
            countSql += " AND r.name LIKE ?";
            params.add("%" + borrower + "%");
            countParams.add("%" + borrower + "%");
        }
        if (startDate != null) {
            sql += " AND b.loan_date >= ?";
            countSql += " AND b.loan_date >= ?";
            params.add(startDate.getTime());
            countParams.add(startDate.getTime());
        }
        if (endDate != null) {
            sql += " AND b.due_date <= ?";
            countSql += " AND b.due_date <= ?";
            params.add(endDate.getTime());
            countParams.add(endDate.getTime());
        }
        sql += " ORDER BY b.loan_date DESC";
        int offset = (pageNo - 1) * 10;
        sql += " LIMIT ? , ?";
        params.add(offset);
        params.add(10);
        BeanListHandler<LoansVO> handler = new BeanListHandler<>(LoansVO.class, new BasicRowProcessor(new GenerousBeanProcessor()));
        List<LoansVO> data = queryRunner.query(sql, handler, params.toArray());
        long total = queryRunner.query(countSql, new ScalarHandler<>(), countParams.toArray());
        return new LoansNode(data, total);
    }

    @Override
    public LoansVO info(int id) throws SQLException {
        String sql = "SELECT b.id as id, bk.title as book_name, r.name as borrower, b.loan_date, b.return_date, b.status, "
                + "bk.author, b.due_date, bk.cover_url, r.email as user_email, r.phone as user_phone "
                + "FROM loans b "
                + "inner JOIN books bk ON b.book_id = bk.id "
                + "inner JOIN borrowers r ON b.borrower_id = r.id "
                + "WHERE b.id = ?";
        return queryRunner.query(sql, new BeanHandler<>(LoansVO.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
    }

    @Override
    public int delById(int id) throws SQLException {
        String sql = "delete from loans where id = ?";
        return queryRunner.update(sql, id);
    }
}
