package com.shhxzq.dc.scs.frm.cdg.entity;

import com.shhxzq.dc.scs.frm.base.page.model.templet.DictData;

/**
 * @author XiaoYi
 * Created on 2017-07-19 09:56:49
 */
public enum SourceFrom implements DictData{
    
    tencent("腾讯"), wangyi("网易"), cctv("中央电视台");
    
    private String label;

    private SourceFrom(String label){
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }

}
