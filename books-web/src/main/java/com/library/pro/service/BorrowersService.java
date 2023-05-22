package com.library.pro.service;

import com.library.pro.model.po.Borrowers;
import com.library.pro.model.vo.Result;

/**
 * @className: BorrowersService <br/>
 * @description: 借阅服务接口 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
public interface BorrowersService {

    Result add(Borrowers borrowers);

    Result search(int pageNo, String keyword);

    Result searchOne(String keyword);

    Result info(int id);

    Result edit(Borrowers borrowers);
}
