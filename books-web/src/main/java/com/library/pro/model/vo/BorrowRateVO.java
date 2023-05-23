package com.library.pro.model.vo;

/**
 * @className: BorrowRateVO <br/>
 * @description: 借阅周期VO <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/23 <br/>
 * @version: 1.0.0 <br/>
 */
public class BorrowRateVO {

    private String month;

    private Integer borrowCount;

    private Integer returnCount;

    private Double returnRate;

    public BorrowRateVO() {
    }

    public BorrowRateVO(String month, Integer borrowCount, Integer returnCount, Double returnRate) {
        this.month = month;
        this.borrowCount = borrowCount;
        this.returnCount = returnCount;
        this.returnRate = returnRate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

    public Integer getReturnCount() {
        return returnCount;
    }

    public void setReturnCount(Integer returnCount) {
        this.returnCount = returnCount;
    }

    public Double getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(Double returnRate) {
        this.returnRate = returnRate;
    }

    @Override
    public String toString() {
        return "BorrowRateVO{" +
                "month='" + month + '\'' +
                ", borrowCount=" + borrowCount +
                ", returnCount=" + returnCount +
                ", returnRate=" + returnRate +
                '}';
    }
}
