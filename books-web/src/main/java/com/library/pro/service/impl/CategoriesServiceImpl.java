package com.library.pro.service.impl;

import com.library.pro.dao.BookCategoryDao;
import com.library.pro.dao.CategoriesDao;
import com.library.pro.dao.impl.BookCategoryDaoImpl;
import com.library.pro.dao.impl.CategoriesDaoImpl;
import com.library.pro.model.po.BookCategory;
import com.library.pro.model.po.BookCategoryNode;
import com.library.pro.model.po.Categories;
import com.library.pro.model.vo.CategoryVO;
import com.library.pro.model.vo.Result;
import com.library.pro.service.CategoriesService;

import java.util.*;

/**
 * @className: CategoriesServiceImpl <br/>
 * @description: 图书分类服务实现类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public class CategoriesServiceImpl implements CategoriesService {

    private CategoriesDao categoriesDao = new CategoriesDaoImpl();

    private BookCategoryDao bookCategoryDao = new BookCategoryDaoImpl();

    @Override
    public Result save(String name) {
        Categories categories = new Categories();
        categories.setName(name);
        int i = categoriesDao.saveCategory(categories);
        if (i > 0) {
            return new Result(200, "保存成功", null);
        } else {
            return new Result(500, "保存失败", null);
        }
    }

    @Override
    public Result searchAll() {
        List<Categories> all = categoriesDao.selectAll();
        if (all == null || all.isEmpty()) {
            return new Result(200, "查询成功", Collections.emptyList());
        }
        List<BookCategoryNode> list = bookCategoryDao.groupByCategory();
        Map<Integer, Integer> map = new HashMap<>();
        for (BookCategoryNode node : list) {
            map.put(node.getCategoryId(), node.getCount());
        }
        List<CategoryVO> result = new ArrayList<>();
        for (Categories categories : all) {
            CategoryVO vo = new CategoryVO();
            vo.setName(categories.getName());
            vo.setId(categories.getId());
            vo.setCount(map.getOrDefault(categories.getId(), 0));
            result.add(vo);
        }
        return new Result(200, "查询成功", result);
    }
}
