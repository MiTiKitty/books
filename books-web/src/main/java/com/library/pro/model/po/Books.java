package com.library.pro.model.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 图书，表名：books
 * </p>
 *
 * @author CatKitty
 */
public class Books implements Serializable {

    private static final long serialVersionUID = 2L;

    public Books() {
    }

    public Books(Integer id,
                 String title,
                 String author,
                 String coverUrl,
                 String publisher,
                 LocalDate publicationDate, String isbn, BigDecimal price, Integer total, Integer currentStock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.coverUrl = coverUrl;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.price = price;
        this.total = total;
        this.currentStock = currentStock;
    }

    /**
     * 图书ID
     */
    private Integer id;

    /**
     * 书名
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 图书封面
     */
    private String coverUrl;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 出版日期
     */
    private LocalDate publicationDate;

    /**
     * ISBN号
     */
    private String isbn;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 总量
     */
    private Integer total;

    /**
     * 当前库存
     */
    private Integer currentStock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publicationDate=" + publicationDate +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                ", total=" + total +
                ", currentStock=" + currentStock +
                '}';
    }
}
