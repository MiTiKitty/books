package com.library.pro.service.impl;

import com.library.pro.dao.BorrowersDao;
import com.library.pro.dao.impl.BorrowersDaoImpl;
import com.library.pro.model.po.Borrowers;
import com.library.pro.model.vo.Result;
import com.library.pro.service.BorrowersService;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @className: BorrowersServiceImpl <br/>
 * @description: 借阅者服务实现类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public class BorrowersServiceImpl implements BorrowersService {

    private BorrowersDao borrowersDao = new BorrowersDaoImpl();

    @Override
    public Result add(Borrowers borrowers) {
        int i = 0;
        try {
            i = borrowersDao.insertBorrowers(borrowers);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (i > 0) {
            return new Result(200, "添加成功", null);
        } else {
            return new Result(500, "添加失败", null);
        }
    }

    @Override
    public Result search(int pageNo, String keyword) {
        // 查询借阅者信息列表，除了借阅者信息

        return null;
    }

    @Override
    public Result searchOne(String keyword) {
        List<Borrowers> borrowers = null;
        try {
            borrowers = borrowersDao.selectBorrower(keyword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            borrowers = Collections.emptyList();
        }
        return new Result(200, "", borrowers);
    }
}
