package com.shhxzq.dc.scs.frm.base.rest.model.templet;

import java.util.List;
import java.util.Map;

import com.shhxzq.dc.scs.frm.base.common.type.MethodType;

/**
 * @ConfMetaData.java
 * @author XiaoYi(hussarch@126.com) Created on 2017年7月9日, ©2017 some rights
 *         reserved
 */
public class CommonSettingMetaData extends BaseMetaData {

    private String name;
    private String mainPage;
    private String renderedJs;
    private CategoryMetaData category;
    private String clazz;
    private List<FieldMetaData> fields;
    private Map<MethodType, ButtonMetaData> buttons;
    private Map<String, DictMetaData> dicts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public CategoryMetaData getCategory() {
        return category;
    }

    public void setCategory(CategoryMetaData category) {
        this.category = category;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<FieldMetaData> getFields() {
        return fields;
    }

    public void setFields(List<FieldMetaData> fields) {
        this.fields = fields;
    }

    public Map<MethodType, ButtonMetaData> getButtons() {
        return buttons;
    }

    public void setButtons(Map<MethodType, ButtonMetaData> buttons) {
        this.buttons = buttons;
    }

    public Map<String, DictMetaData> getDicts() {
        return dicts;
    }

    public void setDicts(Map<String, DictMetaData> dicts) {
        this.dicts = dicts;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((buttons == null) ? 0 : buttons.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
        result = prime * result + ((dicts == null) ? 0 : dicts.hashCode());
        result = prime * result + ((fields == null) ? 0 : fields.hashCode());
        result = prime * result + ((mainPage == null) ? 0 : mainPage.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((renderedJs == null) ? 0 : renderedJs.hashCode());
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
        CommonSettingMetaData other = (CommonSettingMetaData) obj;
        if (buttons == null) {
            if (other.buttons != null)
                return false;
        } else if (!buttons.equals(other.buttons))
            return false;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (clazz == null) {
            if (other.clazz != null)
                return false;
        } else if (!clazz.equals(other.clazz))
            return false;
        if (dicts == null) {
            if (other.dicts != null)
                return false;
        } else if (!dicts.equals(other.dicts))
            return false;
        if (fields == null) {
            if (other.fields != null)
                return false;
        } else if (!fields.equals(other.fields))
            return false;
        if (mainPage == null) {
            if (other.mainPage != null)
                return false;
        } else if (!mainPage.equals(other.mainPage))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (renderedJs == null) {
            if (other.renderedJs != null)
                return false;
        } else if (!renderedJs.equals(other.renderedJs))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CommonSettingMetaData [name=" + name + ", mainPage=" + mainPage + ", renderedJs=" + renderedJs + ", category=" + category + ", clazz="
                + clazz + ", fields=" + fields + ", buttons=" + buttons + ", dicts=" + dicts + "]";
    }

}
