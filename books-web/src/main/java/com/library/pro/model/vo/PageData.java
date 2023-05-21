package com.library.pro.model.vo;

import java.util.List;

/**
 * @className: PageData <br/>
 * @description: 分页结果vo <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public class PageData<T> {

    private Integer pageNo;

    private Integer pageTotal;

    private List<T> data;

    public PageData() {
    }

    public PageData(Integer pageNo, Integer pageTotal, List<T> data) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.data = data;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
