/**
 * 
 */
package com.shhxzq.crm.react.base.page.model.templet;

import java.util.List;

/**
 * @author XiaoYi
 * Created on 2017-06-22 17:55:06
 */
public class ModaldialogMetaData {

    public static final String tmpletNameAdd = "add.json";
    public static final String tmpletNameUpate = "update.json";
    public static final String tmpletNameView = "view.json";
    
    private String title;
    private List<FieldMetaData> fields;
    private List<ButtonMetaData> buttons;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

}
