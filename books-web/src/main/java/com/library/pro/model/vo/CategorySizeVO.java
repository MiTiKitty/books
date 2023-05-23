package com.library.pro.model.vo;

/**
 * @className: CategorySizeVO <br/>
 * @description: 图书分类书总量类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/23 <br/>
 * @version: 1.0.0 <br/>
 */
public class CategorySizeVO {

    private String name;

    private Integer count;

    public CategorySizeVO() {
    }

    public CategorySizeVO(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CategorySizeVO{" +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

}
