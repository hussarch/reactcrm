package com.shhxzq.crm.react.generator.entity;

import java.util.Date;

import com.shhxzq.crm.react.base.page.anotations.ClazzDesc;
import com.shhxzq.crm.react.base.page.anotations.FieldDesc;

/**
 * @DocumentEntity.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年7月9日, ©2017 some rights reserved
 */
@ClazzDesc(name = "文档")
public class DocumentEntity {
    
    @FieldDesc(label = "ID")
    private Integer id;
    @FieldDesc(label = "标题")
    private String title;
    @FieldDesc(label = "摘要")
    private String summary;
    @FieldDesc(label = "信息来源")
    private String sourceFrom;
    @FieldDesc(label = "作者")
    private String author;
    @FieldDesc(label = "发布时间")
    private Date publishedAt;
    @FieldDesc(label = "内容")
    private String content;
    private Date createdAt;
    private Date updatedAt;
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
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getSourceFrom() {
        return sourceFrom;
    }
    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Date getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
