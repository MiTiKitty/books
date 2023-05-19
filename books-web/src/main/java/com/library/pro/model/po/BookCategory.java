package com.library.pro.model.po;

import java.io.Serializable;

/**
 * <p>
 * 图书类别，表名：book_category
 * </p>
 *
 * @author CatKitty
 */
public class BookCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图书分类联系ID
     */
    private Integer id;

    /**
     * 图书ID
     */
    private Integer bookId;

    /**
     * 分类ID
     */
    private Integer categoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", categoryId=" + categoryId +
                '}';
    }
}
