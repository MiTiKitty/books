package com.library.pro.service;

import com.library.pro.model.vo.Result;

/**
 * @className: ShowTableService <br/>
 * @description: 展示图表服务接口 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/23 <br/>
 * @version: 1.0.0 <br/>
 */
public interface ShowTableService {

    /**
     * 分类查询最近 month 个月借阅出去的图书数量
     *
     * @param month
     * @return
     */
    Result queryCategoryCount(int month);

    /**
     * 分类查询最近 month 个月借阅率
     *
     * @param month
     * @return
     */
    Result queryBorrowRate(int month);

    /**
     * 查询查询图书各分类下的图书数量列表
     *
     * @return
     */
    Result queryCategorySize();

    /**
     * 查询每个分类的图书的平均借阅周期
     *
     * @return
     */
    Result queryCategoryBookDuration();

    /**
     * 查询最近 month 个月内借阅数量最多的前 size 本书籍
     *
     * @param size
     * @param month
     * @return
     */
    Result queryHotBook(int size, int month);

}
