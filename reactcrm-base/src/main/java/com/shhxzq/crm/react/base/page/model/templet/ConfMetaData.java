package com.shhxzq.crm.react.base.page.model.templet;

import java.util.Map;
import java.util.LinkedHashMap;

/**
 * @ConfMetaData.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年7月9日, ©2017 some rights reserved
 */
public class ConfMetaData {
    
    public static final String tmpletName = "conf.json";

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

}
