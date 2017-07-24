package com.shhxzq.dc.scs.frm.base.page.model.templet;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XiaoYi
 * Created on 2017-07-16 22:10:36
 */
public class ApiMetaData extends BaseMetaData{
    
    private String serviceId;
    private String title;
    private Map<String, String> params;
    private List<FieldMetaData> fields;
    
    public String getServiceId() {
        return serviceId;
    }
    
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fields == null) ? 0 : fields.hashCode());
        result = prime * result + ((params == null) ? 0 : params.hashCode());
        result = prime * result + ((serviceId == null) ? 0 : serviceId.hashCode());
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
        if (serviceId == null) {
            if (other.serviceId != null)
                return false;
        } else if (!serviceId.equals(other.serviceId))
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
        return "ApiMetaData [serviceId=" + serviceId + ", title=" + title + ", params=" + params + ", fields=" + fields + "]";
    }

    
}
