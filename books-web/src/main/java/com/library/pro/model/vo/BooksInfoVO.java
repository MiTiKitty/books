package com.library.pro.model.vo;

import com.library.pro.model.po.Books;

import java.util.List;

/**
 * @className: BooksInfoVO <br/>
 * @description: 图书基本信息VO <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/22 <br/>
 * @version: 1.0.0 <br/>
 */
public class BooksInfoVO {

    private Books book;

    private List<Integer> categoryIds;

    public BooksInfoVO(Books book, List<Integer> categoryIds) {
        this.book = book;
        this.categoryIds = categoryIds;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    @Override
    public String toString() {
        return "BooksInfoVO{" +
                "book=" + book +
                ", categoryIds=" + categoryIds +
                '}';
    }
}
