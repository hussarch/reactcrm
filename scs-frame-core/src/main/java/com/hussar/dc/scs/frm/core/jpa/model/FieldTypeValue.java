package com.hussar.dc.scs.frm.core.jpa.model;

/**
 * @author XiaoYi Created on 2017-07-26 10:32:52
 */
public class FieldTypeValue {

    private Class<?> type;

    private Object value;
    
    public FieldTypeValue(Class<?> type, Object value){
        this.type = type;
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FieldTypeValue [type=" + type + ", value=" + value + "]";
    }

}
