package com.library.pro.service;

import com.library.pro.model.po.Books;
import com.library.pro.model.vo.Result;

/**
 * @className: BooksService <br/>
 * @description: 图书服务接口 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
public interface BooksService {


    /**
     * 添加图书
     *
     * @param books
     * @return
     */
    Result save(Books books);

    /**
     * 查询
     *
     * @param pageNo
     *         当前页
     * @param title
     *         书名
     * @param isbn
     *         作者
     * @param category
     *         图书分类
     * @return
     */
    Result search(int pageNo, String title, String isbn, Integer category);

    /**
     * 根据id删除书籍
     *
     * @param id
     * @return
     */
    Result delete(int id);

    /**
     * 根据id查询图书
     *
     * @param id
     * @return
     */
    Result info(int id);

    /**
     * 修改图书
     *
     * @param books
     * @return
     */
    Result edit(Books books);
}
