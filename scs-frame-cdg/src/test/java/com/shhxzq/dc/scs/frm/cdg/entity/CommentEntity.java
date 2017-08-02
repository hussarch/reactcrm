package com.shhxzq.dc.scs.frm.cdg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shhxzq.dc.scs.frm.base.common.anotations.Comment;

/**
 * @author XiaoYi Created on 2017-08-02 16:59:16
 */
@Entity
@Table(name = "comments")
@Comment("评论")
public class CommentEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("主键")
    private Integer id;

    @Column(length = 200)
    @Comment("标题")
    private String title;
    
    @Column(length = 2000)
    @Comment("标题")
    private String detail;

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

    
    public String getDetail() {
        return detail;
    }

    
    public void setDetail(String detail) {
        this.detail = detail;
    }

}
