package com.library.pro.service;

import com.library.pro.model.vo.Result;

import java.sql.Date;

/**
 * @className: LoansService <br/>
 * @description: 借阅服务接口 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
public interface LoansService {

    Result update(int id, int status, Date date);

    Result add(int userId, int bookId, Date startDate, Date endDate, int status);

    Result search(int pageNo, String title, String borrower, Date startDate, Date endDate);

    Result info(int id);

    Result del(int id);
}
