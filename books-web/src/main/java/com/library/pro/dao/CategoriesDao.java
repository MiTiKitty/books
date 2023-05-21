package com.library.pro.dao;

import com.library.pro.model.po.Categories;

import java.util.List;

/**
 * @className: CategoriesDao <br/>
 * @description: 分类持久层接口 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
public interface CategoriesDao {

    int saveCategory(Categories category);

    List<Categories> selectAll();

}
