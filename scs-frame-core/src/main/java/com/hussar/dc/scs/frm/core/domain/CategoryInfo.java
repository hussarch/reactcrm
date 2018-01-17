package com.hussar.dc.scs.frm.core.domain;

/**
 * @author XiaoYi Created on 2017-08-03 15:39:53
 */
public class CategoryInfo {

    private String fieldName;

    private String fieldValue;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        return "CategoryInfo [fieldName=" + fieldName + ", fieldValue=" + fieldValue + "]";
    }

}
