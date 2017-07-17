package com.shhxzq.crm.react.base.page.model.templet;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * @author XiaoYi
 * Created on 2017-07-16 22:10:36
 */
public class ApiMetaData implements Serializable{
    
    private String title;
    private Map<String, String> params;
    private List<FieldMetaData> fields;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void addParamName(String name) {
        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        this.params.put(name, "");
    }
    
    public Map<String, String> getParams() {
        return params;
    }
    
    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    
    public List<FieldMetaData> getFields() {
        return fields;
    }
    
    public void setFields(List<FieldMetaData> fields) {
        this.fields = fields;
    }
    
}
