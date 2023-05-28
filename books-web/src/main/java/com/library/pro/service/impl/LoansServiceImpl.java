package com.library.pro.service.impl;

import com.library.pro.dao.BooksDao;
import com.library.pro.dao.LoansDao;
import com.library.pro.dao.impl.BooksDaoImpl;
import com.library.pro.dao.impl.LoansDaoImpl;
import com.library.pro.model.po.Loans;
import com.library.pro.model.po.LoansNode;
import com.library.pro.model.vo.LoansVO;
import com.library.pro.model.vo.PageData;
import com.library.pro.model.vo.Result;
import com.library.pro.service.LoansService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @className: LoansServiceImpl <br/>
 * @description: 借阅服务实现类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public class LoansServiceImpl implements LoansService {

    private LoansDao loansDao = new LoansDaoImpl();

    private BooksDao booksDao = new BooksDaoImpl();

    @Override
    public Result update(int id, int status, Date date) {
        Loans loans = new Loans();
        loans.setId(id);
        loans.setStatus(status);
        if (date != null) {
            loans.setReturnDate(date.getTime());
        }
        int i = 0;
        Loans loan = null;
        try {
            loan = loansDao.selectLoanById(id);
            if (loan == null) {
                return new Result(500, "更新失败", null);
            }
            int bookId = loan.getBookId();
            if (loan.getStatus() != status) {
                if (status == 3) {
                    booksDao.updateBookStock(bookId, 1);
                }
            }
            i = loansDao.updeteLoanById(loans);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (i > 0) {
            return new Result(200, "更新成功", null);
        } else {
            return new Result(500, "更新失败", null);
        }
    }

    @Override
    public Result add(int userId, int bookId, Date startDate, Date endDate, int status) {
        Loans loans = new Loans();
        loans.setBookId(bookId);
        loans.setBorrowerId(userId);
        if (startDate!= null) {
            loans.setLoanDate(startDate.getTime());
        }
        if (endDate!= null) {
            loans.setDueDate(endDate.getTime());
        }
        loans.setStatus(status);
        int i = 0;
        try {
            i = loansDao.insertLoan(loans);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (i > 0) {
            if (status == 1) {
                booksDao.updateBookStock(bookId, -1);
            }
            return new Result(200, "添加成功", null);
        } else {
            return new Result(500, "添加失败", null);
        }
    }

    @Override
    public Result search(int pageNo, String title, String borrower, Date startDate, Date endDate) {
        PageData<LoansVO> result = new PageData<>();
        try {
            LoansNode node = loansDao.search(pageNo, title, borrower, startDate, endDate);
            long total = node.getTotal();
            List<LoansVO> data = node.getData();
            if (data == null || data.isEmpty()) {
                result.setPageNo(pageNo);
                result.setPageTotal((int)Math.ceil(total / 10.0));
                result.setData(Collections.emptyList());
            } else {
                if ((pageNo + 1) * 10 > total) {
                    result.setPageNo(pageNo);
                } else {
                    result.setPageNo(pageNo + 1);
                }
                result.setPageTotal((int)Math.ceil(total / 10.0));
                result.setData(data);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Result(200, "", result);
    }

    @Override
    public Result info(int id) {
        LoansVO vo = null;
        try {
            vo = loansDao.info(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Result(200, "", vo);
    }

    @Override
    public Result del(int id) {
        int i = 0;
        try {
            i = loansDao.delById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (i > 0) {
            return new Result(200, "删除成功", null);
        } else {
            return new Result(500, "删除失败", null);
        }
    }
}
