package com.shhxzq.dc.scs.frm.cdf.templete;

import com.shhxzq.dc.scs.frm.base.common.type.PageType;

/**
 * @author XiaoYi Created on 2017-08-01 09:59:32
 */
public class TempletDefinition {

    private PageType pageType;
    private String[] showFileds;
    private String[] notShowFileds;
    private String[] hiddenFileds;

    public TempletDefinition(PageType pageType) {
        this.pageType = pageType;
    }

    public TempletDefinition setShowFields(String... fields) {
        this.showFileds = fields;
        return this;
    }

    public TempletDefinition setNotShowFields(String... fields) {
        this.notShowFileds = fields;
        return this;
    }

    public TempletDefinition setHiddenFields(String... fields) {
        this.hiddenFileds = fields;
        return this;
    }

    public PageType getPageType() {
        return pageType;
    }

    public String[] getShowFileds() {
        return showFileds;
    }

    public String[] getNotShowFileds() {
        return notShowFileds;
    }

    public String[] getHiddenFileds() {
        return hiddenFileds;
    }

}
