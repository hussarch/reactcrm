package com.hussar.dc.scs.frm.base.rest.model.templet;

import java.util.List;

/**
 * @author XiaoYi Created on 2017-07-31 15:46:41
 */
public class CategoryMetaData extends BaseMetaData {

    private String fieldName;
    private List<CategoryItemMetaData> group;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<CategoryItemMetaData> getGroup() {
        return group;
    }

    public void setGroup(List<CategoryItemMetaData> group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
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
        CategoryMetaData other = (CategoryMetaData) obj;
        if (fieldName == null) {
            if (other.fieldName != null)
                return false;
        } else if (!fieldName.equals(other.fieldName))
            return false;
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CategoryMetaData [fieldName=" + fieldName + ", group=" + group + "]";
    }

}
