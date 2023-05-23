package com.library.pro.dao;

import com.library.pro.model.vo.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @className: ShowTableDao <br/>
 * @description: 首页图表持久层类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/23 <br/>
 * @version: 1.0.0 <br/>
 */
public interface ShowTableDao {

    /**
     * 分类查询最近 month 个月借阅出去的图书数量
     *
     * @param month
     *         最近 month 月
     * @return 分类名：借阅数量
     *
     * @throws SQLException
     *         异常
     */
    List<CategoryCountVO> selectCategoryCountByMonth(int month) throws SQLException;

    /**
     * 分类查询最近 month 个月借阅率
     *
     * @param month
     *         最近 month 月
     * @return list
     *
     * @throws SQLException
     *         异常
     */
    List<BorrowRateVO> selectBorrowRateByMonth(int month) throws SQLException;

    /**
     * 查询查询图书各分类下的图书数量列表
     *
     * @return 图书数量列表
     *
     * @throws SQLException
     *         异常
     */
    List<CategorySizeVO> selectCategorySize() throws SQLException;

    /**
     * 查询每个分类的图书的平均借阅周期
     *
     * @return 分类图书平均借阅周期
     *
     * @throws SQLException
     *         异常
     */
    List<CategoryBookDurationVO> selectCategoryBookDuration() throws SQLException;

    /**
     * 查询最近 month 个月内借阅数量最多的前 size 本书籍
     *
     * @param size
     *         前 size 本书
     * @param month
     *         最近 month 个月
     * @return 书籍
     *
     * @throws SQLException
     *         异常
     */
    List<HotBookVO> selectHotBook(int size, int month) throws SQLException;
}
