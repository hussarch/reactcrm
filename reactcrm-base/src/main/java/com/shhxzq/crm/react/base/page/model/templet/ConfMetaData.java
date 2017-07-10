package com.shhxzq.crm.react.base.page.model.templet;

import java.util.HashMap;
import java.util.Map;

/**
 * @ConfMetaData.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年7月9日, ©2017 some rights reserved
 */
public class ConfMetaData {

    private String mainPage;
    private String renderedJs;
    private Map<String, String> params;
    private String clazz;

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
            this.params = new HashMap<>();
        }
        this.params.put(name, null);
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

}
