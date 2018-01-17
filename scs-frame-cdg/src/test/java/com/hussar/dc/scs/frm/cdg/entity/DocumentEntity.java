package com.hussar.dc.scs.frm.cdg.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hussar.dc.scs.frm.base.common.anotations.Comment;


/**
 * @DocumentEntity.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年7月9日, ©2017 some rights reserved
 */
@Entity
@Table(name = "document")
@Comment("文档")
public class DocumentEntity {
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("主键")
    private Integer id;
    
    @Column(length = 200, nullable = false)
    @Comment("标题")
    private String title;
    
    @Column(length = 10)
    @Comment("类别")
    private DocumentCategory type;

    @Column(length = 500)
    @Comment("摘要")
    private String summary;
    
    @Enumerated(EnumType.STRING)
    @Column(length=10, name="source_from", nullable = false)
    @Comment("消息来源")
    private SourceFrom sourceFrom;
    
    @Comment("作者")
    @ManyToOne
    private AuthorEntity author;
    
    @Column(name = "published_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("发布时间")
    private Date publishedAt;
    
    @Column(columnDefinition="text")
    @Comment("正文")
    private String content;
    
    @Comment("评论")
    @OneToMany()
    private Set<CommentEntity> comments;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("创建时间")
    private Date createdAt;
    
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("修改时间")
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

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
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

    
    public Set<CommentEntity> getComments() {
        return comments;
    }

    
    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }
}
