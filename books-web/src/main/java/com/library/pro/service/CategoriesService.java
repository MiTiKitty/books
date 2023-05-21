package com.library.pro.service;

import com.library.pro.model.vo.Result;

/**
 * @className: CategoriesService <br/>
 * @description: 图书分类服务接口 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
public interface CategoriesService {

    Result save(String name);

    Result searchAll();

}
