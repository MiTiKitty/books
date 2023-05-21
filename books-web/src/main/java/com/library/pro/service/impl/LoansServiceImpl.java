package com.library.pro.service.impl;

import com.library.pro.dao.LoansDao;
import com.library.pro.model.vo.Result;
import com.library.pro.service.LoansService;

import java.sql.Date;

/**
 * @className: LoansServiceImpl <br/>
 * @description: 借阅服务实现类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public class LoansServiceImpl implements LoansService {

    @Override
    public Result update(int id, int status, Date date) {
        return null;
    }

    @Override
    public Result add(int userId, int bookId) {
        return null;
    }

    @Override
    public Result search(int pageNo, String title, String borrower, Date startDate, Date endDate) {
        return null;
    }
}
