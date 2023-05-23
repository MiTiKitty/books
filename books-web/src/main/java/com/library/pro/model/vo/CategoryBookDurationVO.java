package com.library.pro.model.vo;

/**
 * @className: CategoryBookDurationVO <br/>
 * @description: 图书分类平均借阅周期VO <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/23 <br/>
 * @version: 1.0.0 <br/>
 */
public class CategoryBookDurationVO {

    private String name;

    private Double borrowDuration;

    public CategoryBookDurationVO() {
    }

    public CategoryBookDurationVO(String name, Double borrowDuration) {
        this.name = name;
        this.borrowDuration = borrowDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBorrowDuration() {
        return borrowDuration;
    }

    public void setBorrowDuration(Double borrowDuration) {
        this.borrowDuration = borrowDuration;
    }

    @Override
    public String toString() {
        return "CategoryBookDurationVO{" +
                "name='" + name + '\'' +
                ", borrowDuration=" + borrowDuration +
                '}';
    }
}
