package com.shhxzq.dc.scs.frm.base.rest.model.templet;

import java.util.List;

/**
 * @TableMetaInfo.java
 * @author XiaoYi(hussarch@126.com) Created on 2017年6月21日, ©2017 some rights
 *         reserved
 */
public class TableMetaData extends ModaldialogMetaData {
    
    private List<String> searchFields;

    public List<String> getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(List<String> searchFields) {
        this.searchFields = searchFields;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((searchFields == null) ? 0 : searchFields.hashCode());
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
        TableMetaData other = (TableMetaData) obj;
        if (searchFields == null) {
            if (other.searchFields != null)
                return false;
        } else if (!searchFields.equals(other.searchFields))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TableMetaData [searchFields=" + searchFields + ", super: " + super.toString() + "]";
    }

}
