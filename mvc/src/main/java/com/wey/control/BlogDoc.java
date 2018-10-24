package com.wey.control;

import java.util.Date;

/**
 * @author Yale.Wei
 * @date 2018/10/10 14:35
 */
public class BlogDoc {
    private String title;
    private String author;
    private String content;
    private Date createTime;

    public BlogDoc() {
    }

    public BlogDoc(String title, String author, String content, Date createTime) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.createTime = createTime;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
