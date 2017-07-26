package com.shhxzq.dc.scs.frame.core.domain;

import com.shhxzq.dc.scs.frm.base.page.model.templet.ModaldialogMetaData;

/**
 * @author XiaoYi
 * Created on 2017-07-26 17:25:41
 */
public class ModaldialogInfo {
    
    private ModaldialogMetaData define;
    
    private Object values;
    
    public ModaldialogInfo(ModaldialogMetaData define){
        this.define = define;
    }
    
    public ModaldialogMetaData getDefine() {
        return define;
    }

    
    public void setDefine(ModaldialogMetaData define) {
        this.define = define;
    }

    
    public Object getValues() {
        return values;
    }

    
    public void setValues(Object values) {
        this.values = values;
    }


    @Override
    public String toString() {
        return "ModaldialogInfo [define=" + define + ", values=" + values + "]";
    }
    
}
