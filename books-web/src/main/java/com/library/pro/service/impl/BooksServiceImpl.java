package com.library.pro.service.impl;

import com.library.pro.dao.BookCategoryDao;
import com.library.pro.dao.BooksDao;
import com.library.pro.dao.impl.BookCategoryDaoImpl;
import com.library.pro.dao.impl.BooksDaoImpl;
import com.library.pro.model.po.BookCategory;
import com.library.pro.model.po.Books;
import com.library.pro.model.vo.BooksInfoVO;
import com.library.pro.model.vo.PageData;
import com.library.pro.model.vo.Result;
import com.library.pro.service.BooksService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: BooksServiceImpl <br/>
 * @description: 图书服务实现类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public class BooksServiceImpl implements BooksService {

    private BooksDao booksDao = new BooksDaoImpl();

    private BookCategoryDao bookCategoryDao = new BookCategoryDaoImpl();

    @Override
    public Result save(Books books, List<Integer> collect) {
        int result = 0;
        try {
            result = booksDao.insertBook(books);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (result > 0) {
            List<BookCategory> list = new ArrayList<BookCategory>(collect.size());
            for (Integer id : collect) {
                list.add(new BookCategory(books.getId(), id));
            }
            bookCategoryDao.batchSave(list);
            return new Result(200, "添加成功", null);
        }
        return new Result(500, "添加失败", null);
    }

    @Override
    public Result search(int pageNo, String title, String isbn, Integer category) {
        try {
            List<Books> books = booksDao.selectBooks(pageNo, title, isbn, category);
            PageData<Books> pageData = new PageData<>();
            int total = booksDao.searchCount(title, isbn, category);
            pageData.setPageTotal((int) Math.ceil(total / 10.0));
            if (books == null || books.size() == 0) {
                pageData.setPageNo(pageNo);
                pageData.setData(Collections.emptyList());
            } else {
                if ((pageNo + 1) * 10 > total) {
                    pageData.setPageNo(pageNo);
                } else {
                    pageData.setPageNo(pageNo + 1);
                }
                pageData.setPageNo(pageNo);
                pageData.setData(books);
            }
            return new Result(200, "查询成功", pageData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return new Result(500, "查询失败", null);
    }

    @Override
    public Result delete(int id) {
        // 不应该物理删除
        return null;
    }

    @Override
    public Result info(int id) {
        try {
            Books books = booksDao.selectBookById(id);
            List<BookCategory> category = bookCategoryDao.selectCategoryListByBookId(id);
            if (books == null) {
                return new Result(500, "查询失败", null);
            }
            List<Integer> collect = category.stream().map(BookCategory::getCategoryId).collect(Collectors.toList());
            BooksInfoVO booksInfoVO = new BooksInfoVO(books, collect);
            return new Result(200, "查询成功", booksInfoVO);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Result(500, "查询失败", null);
    }

    @Override
    public Result edit(Books books, List<Integer> collect) {
        int i = 0;
        try {
            i = booksDao.updateBookById(books);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (i > 0) {
            List<BookCategory> list = new ArrayList<BookCategory>(collect.size());
            for (Integer id : collect) {
                list.add(new BookCategory(books.getId(), id));
            }
            bookCategoryDao.delByBookId(books.getId());
            bookCategoryDao.batchSave(list);
            return new Result(200, "修改成功", null);
        }
        return new Result(500, "修改失败", null);
    }

    @Override
    public Result searchOne(String title) {
        List<Books> list = Collections.emptyList();
        try {
            list = booksDao.selectOne(title);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Result(200, "", list);
    }
}
