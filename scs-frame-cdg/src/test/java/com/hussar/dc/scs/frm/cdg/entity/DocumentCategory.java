package com.hussar.dc.scs.frm.cdg.entity;

import com.hussar.dc.scs.frm.base.rest.model.templet.CategoryData;

/**
 * @author XiaoYi
 * Created on 2017-07-31 17:26:32
 */
public enum DocumentCategory implements CategoryData{

    news("资讯新闻"), edu("文档");
    
    private String name;

    DocumentCategory(String name){
        this.name = name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getFieldValue() {
        return this.name();
    }

}
