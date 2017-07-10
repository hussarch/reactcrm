package com.shhxzq.crm.react.base.page.model.templet;

import java.io.Serializable;

import com.shhxzq.crm.react.base.page.type.FieldType;

/**
 * @FieldMetaInfo.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年6月21日, ©2017 some rights reserved
 */
public class FieldMetaData implements Serializable {

    private String label;
    private String name;
    private Boolean hidden;
    private FieldType type;
    private Integer size;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
