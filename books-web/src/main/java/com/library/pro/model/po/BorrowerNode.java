package com.library.pro.model.po;

import java.util.List;

/**
 * @className: BorrowerNode <br/>
 * @description: 借阅者内部node类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public class BorrowerNode {

    private List<Borrowers> data;

    private long total;

    public BorrowerNode(List<Borrowers> data, long total) {
        this.data = data;
        this.total = total;
    }

    public List<Borrowers> getData() {
        return data;
    }

    public void setData(List<Borrowers> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "BorrowerNode{" +
                "data=" + data +
                ", total=" + total +
                '}';
    }
}
