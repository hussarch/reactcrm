package com.hussar.dc.scs.frm.base.rest.model.templet;

import com.hussar.dc.scs.frm.base.common.type.MethodType;

/**
 * @ButtionMetaInfo.java
 * @author XiaoYi(hussarch@126.com) Created on 2017年6月21日, ©2017 some rights
 *         reserved
 */
public class ButtonMetaData extends BaseMetaData {

    private String label;
    private String action;
    private MethodType method;

    public ButtonMetaData(String label, String action, MethodType method) {
        this.label = label;
        this.action = action;
        this.method = method;
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((action == null) ? 0 : action.hashCode());
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((method == null) ? 0 : method.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ButtonMetaData other = (ButtonMetaData) obj;
        if (action == null) {
            if (other.action != null)
                return false;
        } else if (!action.equals(other.action))
            return false;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        if (method != other.method)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ButtonMetaData [label=" + label + ", action=" + action + ", method=" + method + "]";
    }

}
