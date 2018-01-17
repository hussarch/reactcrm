package com.hussar.dc.scs.frm.core.jpa.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hussar.dc.scs.frm.base.common.anotations.Comment;

/**
 * @author XiaoYi Created on 2017-08-04 16:52:31
 */
@Entity
@Table(name = "menu")
@Comment("导航菜单目录")
public class MenuEntity extends BaseEntity {

    @Column(length = 50, nullable = false)
    @Comment("系统名称")
    private String sysName;

    @Column(length = 50, nullable = false)
    @Comment("菜单名称")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @Comment("父节点")
    private MenuEntity parent;

    @Column(name = "order_num")
    @Comment("顺序")
    private Integer order;

    @Column(name = "image_url", length = 200)
    @Comment("图片地址")
    private String imageUrl;

    @Column(name = "target_url", length = 200)
    @Comment("指向目标地址")
    private String targetUrl;

    @Column(name = "href_url", length = 200)
    @Comment("链接地址")
    private String hrefUrl;

    @Column(name = "default_url", length = 200)
    @Comment("默认链接地址")
    private String defaultUrl;

    @Column(name = "host", length = 200)
    @Comment("主机")
    private String host;

    @Column
    @Comment("状态")
    private Integer status;

    @Column(length = 500)
    @Comment("备注")
    private String remark;

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuEntity getParent() {
        return parent;
    }

    public void setParent(MenuEntity parent) {
        this.parent = parent;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getHrefUrl() {
        return hrefUrl;
    }

    public void setHrefUrl(String hrefUrl) {
        this.hrefUrl = hrefUrl;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((defaultUrl == null) ? 0 : defaultUrl.hashCode());
        result = prime * result + ((host == null) ? 0 : host.hashCode());
        result = prime * result + ((hrefUrl == null) ? 0 : hrefUrl.hashCode());
        result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((remark == null) ? 0 : remark.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((sysName == null) ? 0 : sysName.hashCode());
        result = prime * result + ((targetUrl == null) ? 0 : targetUrl.hashCode());
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
        MenuEntity other = (MenuEntity) obj;
        if (defaultUrl == null) {
            if (other.defaultUrl != null)
                return false;
        } else if (!defaultUrl.equals(other.defaultUrl))
            return false;
        if (host == null) {
            if (other.host != null)
                return false;
        } else if (!host.equals(other.host))
            return false;
        if (hrefUrl == null) {
            if (other.hrefUrl != null)
                return false;
        } else if (!hrefUrl.equals(other.hrefUrl))
            return false;
        if (imageUrl == null) {
            if (other.imageUrl != null)
                return false;
        } else if (!imageUrl.equals(other.imageUrl))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        if (parent == null) {
            if (other.parent != null)
                return false;
        } else if (!parent.equals(other.parent))
            return false;
        if (remark == null) {
            if (other.remark != null)
                return false;
        } else if (!remark.equals(other.remark))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (sysName == null) {
            if (other.sysName != null)
                return false;
        } else if (!sysName.equals(other.sysName))
            return false;
        if (targetUrl == null) {
            if (other.targetUrl != null)
                return false;
        } else if (!targetUrl.equals(other.targetUrl))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MenuEntity [sysName=" + sysName + ", name=" + name + ", parent=" + parent + ", order=" + order + ", imageUrl=" + imageUrl
                + ", targetUrl=" + targetUrl + ", hrefUrl=" + hrefUrl + ", defaultUrl=" + defaultUrl + ", host=" + host + ", status=" + status
                + ", remark=" + remark + "]";
    }

}
