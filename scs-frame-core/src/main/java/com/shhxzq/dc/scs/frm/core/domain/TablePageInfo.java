package com.shhxzq.dc.scs.frm.core.domain;

import org.springframework.data.domain.Page;

import com.shhxzq.dc.scs.frm.base.page.model.templet.TableMetaData;

/**
 * @TablePageInfo.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年7月27日, ©2017 some rights reserved
 */
public class TablePageInfo {
    
    private TableMetaData define;
    
    private Page<?> page;

    public TableMetaData getDefine() {
        return define;
    }

    public void setDefine(TableMetaData define) {
        this.define = define;
    }

    public Page<?> getPage() {
        return page;
    }

    public void setPage(Page<?> page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "TablePageInfo [define=" + define + ", page=" + page + "]";
    }
    
}
