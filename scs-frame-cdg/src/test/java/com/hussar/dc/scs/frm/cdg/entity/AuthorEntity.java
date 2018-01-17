package com.hussar.dc.scs.frm.cdg.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hussar.dc.scs.frm.base.common.anotations.Comment;

/**
 * @author XiaoYi Created on 2017-08-02 16:59:16
 */
@Entity
@Table(name = "author")
@Comment("作者")
public class AuthorEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("主键")
    private Integer id;

    @Column(length = 200)
    @Comment("名字")
    private String name;

    @Column(length = 20)
    @Comment("电话号码")
    private String phoneNumber;
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
