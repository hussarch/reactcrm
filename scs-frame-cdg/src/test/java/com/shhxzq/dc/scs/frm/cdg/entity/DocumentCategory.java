package com.shhxzq.dc.scs.frm.cdg.entity;

import com.shhxzq.dc.scs.frm.base.rest.model.templet.CategoryData;

/**
 * @author XiaoYi
 * Created on 2017-07-31 17:26:32
 */
public enum DocumentCategory implements CategoryData{

    news("资讯新闻"), edu("文档");
    
    private String title;

    DocumentCategory(String title){
        this.title = title;
    }
    
    @Override
    public String getPageTitle() {
        return this.title;
    }

    @Override
    public String getFieldValue() {
        return this.name();
    }

}
