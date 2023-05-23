package com.library.pro.model.vo;

/**
 * @className: CategoryCountVO <br/>
 * @description: 图书和图书借阅数量vo <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/23 <br/>
 * @version: 1.0.0 <br/>
 */
public class CategoryCountVO {

    private String category;

    private String count;

    public CategoryCountVO() {
    }

    public CategoryCountVO(String category, String count) {
        this.category = category;
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CategoryCountVO{" +
                "category='" + category + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
