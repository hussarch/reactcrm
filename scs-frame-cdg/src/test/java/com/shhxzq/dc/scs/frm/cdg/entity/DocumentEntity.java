package com.shhxzq.dc.scs.frm.cdg.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.shhxzq.dc.scs.frm.base.common.anotations.Comnont;


/**
 * @DocumentEntity.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年7月9日, ©2017 some rights reserved
 */
@Entity
@Table(name = "document")
@Comnont("文档")
public class DocumentEntity {
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comnont("主键")
    private Integer id;
    
    @Column(length = 200)
    @Comnont("标题")
    private String title;
    
    @Column(length = 10)
    @Comnont("类别")
    private DocumentCategory type;

    @Column(length = 500)
    @Comnont("摘要")
    private String summary;
    
    @Enumerated(EnumType.STRING)
    @Column(length=10, name="source_from")
    @Comnont("消息来源")
    private SourceFrom sourceFrom;
    
    @Column(length = 20)
    @Comnont("作者")
    private String author;
    
    @Column(name = "published_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Comnont("发布时间")
    private Date publishedAt;
    
    @Column(columnDefinition="text")
    @Comnont("正文")
    private String content;
    
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Comnont("创建时间")
    private Date createdAt;
    
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Comnont("修改时间")
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

    public SourceFrom getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(SourceFrom sourceFrom) {
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

    public DocumentCategory getType() {
        return type;
    }

    public void setType(DocumentCategory type) {
        this.type = type;
    }
}
