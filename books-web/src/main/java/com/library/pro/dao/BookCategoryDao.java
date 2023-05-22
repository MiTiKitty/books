package com.library.pro.dao;

import com.library.pro.model.po.BookCategory;
import com.library.pro.model.po.BookCategoryNode;

import java.util.List;

/**
 * @className: BookCategoryDao <br/>
 * @description: 图书分类持久层接口 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
public interface BookCategoryDao {

    int save(BookCategory category);

    int del(BookCategory category);

    List<BookCategoryNode> groupByCategory();

    List<BookCategory> selectCategoryListByBookId(int bookId);

    void batchSave(List<BookCategory> list);

    void delByBookId(Integer id);
}
