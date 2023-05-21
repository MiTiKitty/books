package com.library.pro.model.po;

/**
 * @className: BookCategoryNode <br/>
 * @description: 图书分类node类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public class BookCategoryNode {

    private Integer categoryId;

    private Integer count;

    public BookCategoryNode() {
    }

    public BookCategoryNode(Integer categoryId, Integer count) {
        this.categoryId = categoryId;
        this.count = count;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BookCategoryNode{" +
                "categoryId=" + categoryId +
                ", count=" + count +
                '}';
    }
}
