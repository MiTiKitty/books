package com.library.pro.model.po;

import com.library.pro.model.vo.LoansVO;

import java.util.List;

/**
 * @className: LoansNode <br/>
 * @description: 借阅内部节点类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public class LoansNode {

    private List<LoansVO> data;

    private long total;

    public LoansNode(List<LoansVO> data, long total) {
        this.data = data;
        this.total = total;
    }

    public List<LoansVO> getData() {
        return data;
    }

    public void setData(List<LoansVO> data) {
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
        return "LoansNode{" +
                "data=" + data +
                ", total=" + total +
                '}';
    }
}
