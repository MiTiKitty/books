package com.library.pro.model.po;

import java.io.Serializable;

/**
 * 借阅表，表名：loans
 * </p>
 *
 * @author CatKitty
 */
public class Loans implements Serializable {

    private static final long serialVersionUID = 5L;

    /**
     * 借阅ID
     */
    private Integer id;

    /**
     * 图书ID
     */
    private Integer bookId;

    /**
     * 借阅者ID
     */
    private Integer borrowerId;

    /**
     * 借阅日期
     */
    private Long loanDate;

    /**
     * 应还日期
     */
    private Long dueDate;

    /**
     * 归还日期
     */
    private Long returnDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Long getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Long loanDate) {
        this.loanDate = loanDate;
    }

    public Long getDueDate() {
        return dueDate;
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    public Long getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Long returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loans{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", borrowerId=" + borrowerId +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
