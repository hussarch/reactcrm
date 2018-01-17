package com.hussar.dc.scs.frm.base.rest.model.templet;

/**
 * @author XiaoYi Created on 2017-07-31 15:49:21
 */
public class CategoryItemMetaData extends BaseMetaData implements CategoryData {

    private String fieldValue;
    private String name;
    
    public CategoryItemMetaData(String fieldValue, String name){
        this.fieldValue = fieldValue;
        this.name = name;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fieldValue == null) ? 0 : fieldValue.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        CategoryItemMetaData other = (CategoryItemMetaData) obj;
        if (fieldValue == null) {
            if (other.fieldValue != null)
                return false;
        } else if (!fieldValue.equals(other.fieldValue))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CategoryItemMetaData [fieldValue=" + fieldValue + ", name=" + name + "]";
    }

}
