package com.shhxzq.crm.react.generator.entity;

import java.util.Date;

import com.shhxzq.crm.react.base.page.anotations.ApiDesc;
import com.shhxzq.crm.react.base.page.anotations.FieldDesc;
import com.shhxzq.crm.react.base.page.anotations.PageDesc;
import com.shhxzq.crm.react.base.page.type.ApiType;
import com.shhxzq.crm.react.base.page.type.PageType;

/**
 * @DocumentEntity.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年7月9日, ©2017 some rights reserved
 */
@PageDesc(title = "文档", 
    params = { "type" }, 
    hbtEntityClass = "com.xx.Entity")
@ApiDesc(name = "文档列表", params = { "type" }, type = ApiType.list)
@ApiDesc(name = "文档详情", params = { "id" }, type = ApiType.detail)
public class DocumentEntity {

    @FieldDesc(label = "ID", hidden = true, notShowIn = {PageType.add}, apiType = {ApiType.list, ApiType.detail})
    private Integer id;
    @FieldDesc(label = "标题", search = true, max = 100, apiType = {ApiType.list, ApiType.detail})
    private String title;

    private String type;

    @FieldDesc(label = "摘要", apiType = {ApiType.list, ApiType.detail})
    private String summary;
    @FieldDesc(label = "信息来源", apiType = {ApiType.list, ApiType.detail})
    private String sourceFrom;
    @FieldDesc(label = "作者", search = true, apiType = {ApiType.list, ApiType.detail})
    private String author;
    @FieldDesc(label = "发布时间", apiType = {ApiType.list, ApiType.detail})
    private Date publishedAt;
    @FieldDesc(label = "内容", notShowIn = {PageType.table}, apiType = {ApiType.detail})
    private String content;
    @FieldDesc(label = "创建时间", showIn = {}, apiType = {ApiType.list, ApiType.detail})
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
