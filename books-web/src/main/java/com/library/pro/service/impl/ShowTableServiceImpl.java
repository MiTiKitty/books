package com.library.pro.service.impl;

import com.library.pro.dao.ShowTableDao;
import com.library.pro.dao.impl.ShowTableDaoImpl;
import com.library.pro.model.vo.*;
import com.library.pro.service.ShowTableService;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @className: ShowTableServiceImpl <br/>
 * @description: 展示图表服务实现类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/23 <br/>
 * @version: 1.0.0 <br/>
 */
public class ShowTableServiceImpl implements ShowTableService {

    private ShowTableDao showTableDao = new ShowTableDaoImpl();

    @Override
    public Result queryCategoryCount(int month) {
        List<CategoryCountVO> vos = Collections.emptyList();
        try {
            vos = showTableDao.selectCategoryCountByMonth(month);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Result(200, "", vos);
    }

    @Override
    public Result queryBorrowRate(int month) {
        List<BorrowRateVO> vos = Collections.emptyList();
        try {
            vos = showTableDao.selectBorrowRateByMonth(month);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Result(200, "", vos);
    }

    @Override
    public Result queryCategorySize() {
        List<CategorySizeVO> vos = Collections.emptyList();
        try {
            vos = showTableDao.selectCategorySize();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Result(200, "", vos);
    }

    @Override
    public Result queryCategoryBookDuration() {
        List<CategoryBookDurationVO> vos = Collections.emptyList();
        try {
            vos = showTableDao.selectCategoryBookDuration();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Result(200, "", vos);
    }

    @Override
    public Result queryHotBook(int size, int month) {
        List<HotBookVO> vos = Collections.emptyList();
        try {
            vos = showTableDao.selectHotBook(size, month);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Result(200, "", vos);
    }
}
