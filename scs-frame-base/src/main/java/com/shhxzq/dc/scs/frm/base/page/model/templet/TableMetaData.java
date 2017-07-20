package com.shhxzq.dc.scs.frm.base.page.model.templet;

import java.io.Serializable;
import java.util.List;

/**
 * @TableMetaInfo.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年6月21日, ©2017 some rights reserved
 */
public class TableMetaData implements Serializable {
    
    public static final String tmpletName = "table.json";
    
    private String title;
    private List<FieldMetaData> searchFields;
    private List<ButtonMetaData> buttons;
    private List<FieldMetaData> columns;
    private Integer totle;
    private Integer totlePage;
    private Integer pageNo;
    private Integer pageSize;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public Integer getTotle() {
        return totle;
    }

    public void setTotle(Integer totle) {
        this.totle = totle;
    }

    public Integer getTotlePage() {
        return totlePage;
    }

    public void setTotlePage(Integer totlePage) {
        this.totlePage = totlePage;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((buttons == null) ? 0 : buttons.hashCode());
        result = prime * result + ((columns == null) ? 0 : columns.hashCode());
        result = prime * result + ((pageNo == null) ? 0 : pageNo.hashCode());
        result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
        result = prime * result + ((searchFields == null) ? 0 : searchFields.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((totle == null) ? 0 : totle.hashCode());
        result = prime * result + ((totlePage == null) ? 0 : totlePage.hashCode());
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
        if (pageNo == null) {
            if (other.pageNo != null)
                return false;
        } else if (!pageNo.equals(other.pageNo))
            return false;
        if (pageSize == null) {
            if (other.pageSize != null)
                return false;
        } else if (!pageSize.equals(other.pageSize))
            return false;
        if (searchFields == null) {
            if (other.searchFields != null)
                return false;
        } else if (!searchFields.equals(other.searchFields))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (totle == null) {
            if (other.totle != null)
                return false;
        } else if (!totle.equals(other.totle))
            return false;
        if (totlePage == null) {
            if (other.totlePage != null)
                return false;
        } else if (!totlePage.equals(other.totlePage))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TableMetaData [title=" + title + ", searchFields=" + searchFields + ", buttons=" + buttons + ", columns=" + columns + ", totle="
                + totle + ", totlePage=" + totlePage + ", pageNo=" + pageNo + ", pageSize=" + pageSize + "]";
    }

}
