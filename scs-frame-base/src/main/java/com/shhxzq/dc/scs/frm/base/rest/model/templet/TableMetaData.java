package com.shhxzq.dc.scs.frm.base.rest.model.templet;

import java.util.List;

/**
 * @TableMetaInfo.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年6月21日, ©2017 some rights reserved
 */
public class TableMetaData extends BaseMetaData {
    
    private List<FieldMetaData> searchFields;
    private List<ButtonMetaData> buttons;
    private List<FieldMetaData> columns;
    
    public List<FieldMetaData> getSearchFields() {
        return searchFields;
    }
    
    public void setSearchFields(List<FieldMetaData> searchFields) {
        this.searchFields = searchFields;
    }
    
    public List<ButtonMetaData> getButtons() {
        return buttons;
    }
    
    public void setButtons(List<ButtonMetaData> buttons) {
        this.buttons = buttons;
    }
    
    public List<FieldMetaData> getColumns() {
        return columns;
    }
    
    public void setColumns(List<FieldMetaData> columns) {
        this.columns = columns;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((buttons == null) ? 0 : buttons.hashCode());
        result = prime * result + ((columns == null) ? 0 : columns.hashCode());
        result = prime * result + ((searchFields == null) ? 0 : searchFields.hashCode());
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
        TableMetaData other = (TableMetaData) obj;
        if (buttons == null) {
            if (other.buttons != null)
                return false;
        } else if (!buttons.equals(other.buttons))
            return false;
        if (columns == null) {
            if (other.columns != null)
                return false;
        } else if (!columns.equals(other.columns))
            return false;
        if (searchFields == null) {
            if (other.searchFields != null)
                return false;
        } else if (!searchFields.equals(other.searchFields))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TableMetaData [searchFields=" + searchFields + ", buttons=" + buttons + ", columns=" + columns + "]";
    }

}
