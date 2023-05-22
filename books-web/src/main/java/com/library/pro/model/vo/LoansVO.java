package com.library.pro.model.vo;

/**
 * @className: LoansVO <br/>
 * @description: 借阅视图VO <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/21 <br/>
 * @version: 1.0.0 <br/>
 */
public class LoansVO {

    private Integer id;

    private String bookName;

    private String author;

    private String borrower;

    private String coverUrl;

    private String username;

    private String userEmail;

    private String userPhone;

    private Long dueDate;

    private Long returnDate;

    private Long loanDate;

    private String status;

    public LoansVO() {
    }

    public LoansVO(Integer id,
                   String bookName,
                   String author,
                   String borrower,
                   String coverUrl,
                   String username,
                   String userEmail,
                   String userPhone,
                   Long dueDate,
                   Long returnDate,
                   Long loanDate,
                   String status) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.borrower = borrower;
        this.coverUrl = coverUrl;
        this.username = username;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.loanDate = loanDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
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

    public Long getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Long loanDate) {
        this.loanDate = loanDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "LoansVO{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", borrower='" + borrower + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", username='" + username + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", loanDate=" + loanDate +
                ", status='" + status + '\'' +
                '}';
    }
}
