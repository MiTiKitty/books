package com.library.pro.model.vo;

/**
 * @className: HotBookVO <br/>
 * @description: 热门书籍VO <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/23 <br/>
 * @version: 1.0.0 <br/>
 */
public class HotBookVO {

    private Integer bookId;

    private String bookName;

    private Integer borrowCount;

    private Integer currentStock;

    public HotBookVO() {
    }

    public HotBookVO(Integer bookId, String bookName, Integer borrowCount, Integer currentStock) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.borrowCount = borrowCount;
        this.currentStock = currentStock;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    @Override
    public String toString() {
        return "HotBookVO{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", borrowCount=" + borrowCount +
                ", currentStock=" + currentStock +
                '}';
    }
}
