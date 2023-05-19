package com.library.pro.model.po;

import java.io.Serializable;

/**
 * <p>
 * 通知，表名：notifications
 * </p>
 *
 * @author CatKitty
 */
public class Notifications implements Serializable {

    private static final long serialVersionUID = 7L;

    /**
     * 通知ID
     */
    private Integer id;

    /**
     * 接收者id
     */
    private Integer receiverId;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 是否已读
     */
    private Integer isRead;

    /**
     * 时间戳
     */
    private Long createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Notifications{" +
                "id=" + id +
                ", receiverId=" + receiverId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isRead=" + isRead +
                ", createTime=" + createTime +
                '}';
    }
}
