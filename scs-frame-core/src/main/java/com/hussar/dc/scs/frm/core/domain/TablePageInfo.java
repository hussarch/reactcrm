package com.hussar.dc.scs.frm.core.domain;

import org.springframework.data.domain.Page;

/**
 * @TablePageInfo.java
 * @author XiaoYi(hussarch@126.com) Created on 2017年7月27日, ©2017 some rights
 *         reserved
 */
public class TablePageInfo {

    private TablePageDefineInfo define;

    private Page<?> page;

    public TablePageDefineInfo getDefine() {
        return define;
    }

    public void setDefine(TablePageDefineInfo define) {
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
