package com.shhxzq.dc.scs.frm.core.domain;

/**
 * @author XiaoYi Created on 2017-07-26 17:25:41
 */
public class ModaldialogInfo {

    private CommonPageDefineInfo define;

    private Object values;

    public ModaldialogInfo(CommonPageDefineInfo define) {
        this.define = define;
    }

    public CommonPageDefineInfo getDefine() {
        return define;
    }

    public void setDefine(CommonPageDefineInfo define) {
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
