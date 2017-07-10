package com.shhxzq.crm.react.base.page.model.templet;

import java.io.Serializable;

import com.shhxzq.crm.react.base.page.type.MethodType;

/**
 * @ButtionMetaInfo.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年6月21日, ©2017 some rights reserved
 */
public class ButtonMetaData implements Serializable {

    private String label;
    private String action;
    private MethodType method;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public MethodType getMethod() {
        return method;
    }

    public void setMethod(MethodType method) {
        this.method = method;
    }

}
