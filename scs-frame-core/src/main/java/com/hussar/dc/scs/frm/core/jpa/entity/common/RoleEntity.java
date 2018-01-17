package com.hussar.dc.scs.frm.core.jpa.entity.common;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.hussar.dc.scs.frm.base.common.anotations.Comment;

/**
 * @author XiaoYi Created on 2017-08-03 11:20:21
 */
@Entity
@Table(name = "role")
@Comment("角色")
public class RoleEntity extends BaseEntity {

    @Column(length = 20, nullable = false)
    @Comment("名称")
    private String name;

    @Column(length = 1000)
    @Comment("操作权限")
    private String optAuth;

    @Column(length = 200)
    @Comment("说明")
    private String comments;

    @Comment("用户")
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<UserEntity> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOptAuth() {
        return optAuth;
    }

    public void setOptAuth(String optAuth) {
        this.optAuth = optAuth;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((optAuth == null) ? 0 : optAuth.hashCode());
        result = prime * result + ((users == null) ? 0 : users.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        RoleEntity other = (RoleEntity) obj;
        if (comments == null) {
            if (other.comments != null)
                return false;
        } else if (!comments.equals(other.comments))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (optAuth == null) {
            if (other.optAuth != null)
                return false;
        } else if (!optAuth.equals(other.optAuth))
            return false;
        if (users == null) {
            if (other.users != null)
                return false;
        } else if (!users.equals(other.users))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RoleEntity [name=" + name + ", optAuth=" + optAuth + ", comments=" + comments + ", users=" + users + "]";
    }

}
