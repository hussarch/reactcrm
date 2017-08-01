/**
 * 
 */
package com.shhxzq.dc.scs.frm.base.rest.model.templet;

import java.util.List;

/**
 * @author XiaoYi Created on 2017-06-22 17:55:06
 */
public class ModaldialogMetaData extends BaseMetaData {

    private List<FieldMetaData> fields;
    private List<ButtonMetaData> buttons;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((buttons == null) ? 0 : buttons.hashCode());
        result = prime * result + ((fields == null) ? 0 : fields.hashCode());
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
        return true;
    }

    @Override
    public String toString() {
        return "ModaldialogMetaData [fields=" + fields + ", buttons=" + buttons + "]";
    }

}
