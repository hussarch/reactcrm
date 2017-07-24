package com.shhxzq.dc.scs.frm.base.page.model.templet;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ConfMetaData.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年7月9日, ©2017 some rights reserved
 */
public class GlobalSettingMetaData extends BaseMetaData{
    
    private String serviceId;
    private String mainPage;
    private String renderedJs;
    private Map<String, String> params;
    private String clazz;
    
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getMainPage() {
        return mainPage;
    }

    public void setMainPage(String mainPage) {
        this.mainPage = mainPage;
    }

    public String getRenderedJs() {
        return renderedJs;
    }

    public void setRenderedJs(String renderedJs) {
        this.renderedJs = renderedJs;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void addParamName(String name) {
        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        this.params.put(name, "");
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
        result = prime * result + ((mainPage == null) ? 0 : mainPage.hashCode());
        result = prime * result + ((params == null) ? 0 : params.hashCode());
        result = prime * result + ((renderedJs == null) ? 0 : renderedJs.hashCode());
        result = prime * result + ((serviceId == null) ? 0 : serviceId.hashCode());
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
        GlobalSettingMetaData other = (GlobalSettingMetaData) obj;
        if (clazz == null) {
            if (other.clazz != null)
                return false;
        } else if (!clazz.equals(other.clazz))
            return false;
        if (mainPage == null) {
            if (other.mainPage != null)
                return false;
        } else if (!mainPage.equals(other.mainPage))
            return false;
        if (params == null) {
            if (other.params != null)
                return false;
        } else if (!params.equals(other.params))
            return false;
        if (renderedJs == null) {
            if (other.renderedJs != null)
                return false;
        } else if (!renderedJs.equals(other.renderedJs))
            return false;
        if (serviceId == null) {
            if (other.serviceId != null)
                return false;
        } else if (!serviceId.equals(other.serviceId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ConfMetaData [serviceId=" + serviceId + ", mainPage=" + mainPage + ", renderedJs=" + renderedJs + ", params=" + params + ", clazz="
                + clazz + "]";
    }

}
