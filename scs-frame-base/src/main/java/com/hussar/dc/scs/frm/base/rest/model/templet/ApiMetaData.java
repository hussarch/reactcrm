package com.hussar.dc.scs.frm.base.rest.model.templet;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XiaoYi Created on 2017-07-16 22:10:36
 */
public class ApiMetaData extends BaseMetaData {

    private String title;
    private Map<String, String> params;
    private List<String> fields;
    
    public void addParamName(String name) {
        if(params == null){
            params = new LinkedHashMap<>();
        }
        params.put(name, "");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fields == null) ? 0 : fields.hashCode());
        result = prime * result + ((params == null) ? 0 : params.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        ApiMetaData other = (ApiMetaData) obj;
        if (fields == null) {
            if (other.fields != null)
                return false;
        } else if (!fields.equals(other.fields))
            return false;
        if (params == null) {
            if (other.params != null)
                return false;
        } else if (!params.equals(other.params))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ApiMetaData [title=" + title + ", params=" + params + ", fields=" + fields + "]";
    }

}
