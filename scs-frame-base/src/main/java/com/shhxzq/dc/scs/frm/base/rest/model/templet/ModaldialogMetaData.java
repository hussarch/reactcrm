/**
 * 
 */
package com.shhxzq.dc.scs.frm.base.rest.model.templet;

import java.util.List;

/**
 * @author XiaoYi Created on 2017-06-22 17:55:06
 */
public class ModaldialogMetaData extends BaseMetaData {

    private List<String> fields;
    private List<String> hiddenfields;
    private List<String> buttons;
    
    public List<String> getFields() {
        return fields;
    }
    
    public void setFields(List<String> fields) {
        this.fields = fields;
    }
    
    public List<String> getHiddenfields() {
        return hiddenfields;
    }
    
    public void setHiddenfields(List<String> hiddenfields) {
        this.hiddenfields = hiddenfields;
    }
    
    public List<String> getButtons() {
        return buttons;
    }
    
    public void setButtons(List<String> buttons) {
        this.buttons = buttons;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((buttons == null) ? 0 : buttons.hashCode());
        result = prime * result + ((fields == null) ? 0 : fields.hashCode());
        result = prime * result + ((hiddenfields == null) ? 0 : hiddenfields.hashCode());
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
        ModaldialogMetaData other = (ModaldialogMetaData) obj;
        if (buttons == null) {
            if (other.buttons != null)
                return false;
        } else if (!buttons.equals(other.buttons))
            return false;
        if (fields == null) {
            if (other.fields != null)
                return false;
        } else if (!fields.equals(other.fields))
            return false;
        if (hiddenfields == null) {
            if (other.hiddenfields != null)
                return false;
        } else if (!hiddenfields.equals(other.hiddenfields))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ModaldialogMetaData [fields=" + fields + ", hiddenfields=" + hiddenfields + ", buttons=" + buttons + "]";
    }

    
}
