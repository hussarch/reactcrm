package com.shhxzq.dc.scs.frm.cdg.entity;

import com.shhxzq.dc.scs.frm.base.rest.model.templet.DictData;

/**
 * @author XiaoYi
 * Created on 2017-07-19 09:56:49
 */
public enum LevelType implements DictData{
    
    A("一级"), B("二级"), C("三级");
    
    private String label;

    private LevelType(String label){
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }

}
