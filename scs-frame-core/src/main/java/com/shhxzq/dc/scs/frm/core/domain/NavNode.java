package com.shhxzq.dc.scs.frm.core.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author XiaoYi Created on 2017-08-07 16:06:13
 */
public class NavNode implements Comparable<NavNode> {

    private Integer id;

    private String name;

    private String targetUrl;

    private Integer order = 0;

    private List<NavNode> children;
    
    public void addNavNode(NavNode child){
        if(children == null){
            children = new ArrayList<>();
        }
        children.add(child);
        if(children.size() > 0){
            Collections.sort(children);
        }
    }

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

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<NavNode> getChildren() {
        return children;
    }

    public void setChildren(List<NavNode> children) {
        this.children = children;
    }

    @Override
    public int compareTo(NavNode other) {
        return this.order - other.order;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((children == null) ? 0 : children.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((targetUrl == null) ? 0 : targetUrl.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NavNode other = (NavNode) obj;
        if (children == null) {
            if (other.children != null)
                return false;
        } else if (!children.equals(other.children))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
        if (targetUrl == null) {
            if (other.targetUrl != null)
                return false;
        } else if (!targetUrl.equals(other.targetUrl))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "NavNode [id=" + id + ", name=" + name + ", targetUrl=" + targetUrl + ", order=" + order + ", children=" + children + "]";
    }

}
