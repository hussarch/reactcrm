package com.shhxzq.dc.scs.frm.base.rest.model.templet;

/**
 * @author XiaoYi Created on 2017-07-31 15:49:21
 */
public class CategoryItemMetaData extends BaseMetaData implements CategoryData{

    private String fieldValue;
    private String pageTitle;

    @Override
    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fieldValue == null) ? 0 : fieldValue.hashCode());
        result = prime * result + ((pageTitle == null) ? 0 : pageTitle.hashCode());
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
        CategoryItemMetaData other = (CategoryItemMetaData) obj;
        if (fieldValue == null) {
            if (other.fieldValue != null)
                return false;
        } else if (!fieldValue.equals(other.fieldValue))
            return false;
        if (pageTitle == null) {
            if (other.pageTitle != null)
                return false;
        } else if (!pageTitle.equals(other.pageTitle))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CategoryItemMetaData [fieldValue=" + fieldValue + ", pageTitle=" + pageTitle + "]";
    }

}
