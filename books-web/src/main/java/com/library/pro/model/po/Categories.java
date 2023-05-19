package com.library.pro.model.po;

import java.io.Serializable;

/**
 * <p>
 * 图书分类，表名：categories
 * </p>
 *
 * @author CatKitty
 */
public class Categories implements Serializable {

    private static final long serialVersionUID = 4L;

    /**
     * 分类ID
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
