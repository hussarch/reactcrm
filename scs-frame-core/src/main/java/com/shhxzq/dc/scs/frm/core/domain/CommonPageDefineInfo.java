package com.shhxzq.dc.scs.frm.core.domain;

import java.util.List;
import java.util.Map;

import com.shhxzq.dc.scs.frm.base.rest.model.templet.ButtonMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.EnumDictMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.FieldMetaData;

/**
 * @author XiaoYi Created on 2017-08-02 17:47:49
 */
public class CommonPageDefineInfo {

    private String name;
    private CategoryInfo category;
    private List<FieldMetaData> fields;
    private List<ButtonMetaData> buttons;
    private Map<String, List<EnumDictMetaData>> dicts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryInfo getCategory() {
        return category;
    }

    public void setCategory(CategoryInfo category) {
        this.category = category;
    }

    public List<FieldMetaData> getFields() {
        return fields;
    }

    public void setFields(List<FieldMetaData> fields) {
        this.fields = fields;
    }

    public List<ButtonMetaData> getButtons() {
        return buttons;
    }

    public void setButtons(List<ButtonMetaData> buttons) {
        this.buttons = buttons;
    }

    public Map<String, List<EnumDictMetaData>> getDicts() {
        return dicts;
    }

    public void setDicts(Map<String, List<EnumDictMetaData>> dicts) {
        this.dicts = dicts;
    }

    @Override
    public String toString() {
        return "CommonPageDefineInfo [name=" + name + ", category=" + category + ", fields=" + fields + ", buttons=" + buttons + ", dicts=" + dicts
                + "]";
    }

}
