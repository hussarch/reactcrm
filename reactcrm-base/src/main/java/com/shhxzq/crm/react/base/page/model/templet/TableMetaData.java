package com.shhxzq.crm.react.base.page.model.templet;

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

}
