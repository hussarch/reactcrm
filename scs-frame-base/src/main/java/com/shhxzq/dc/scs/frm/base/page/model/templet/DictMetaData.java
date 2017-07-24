package com.shhxzq.dc.scs.frm.base.page.model.templet;

/**
 * @DictMetaInfo.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年6月22日, ©2017 some rights reserved
 */
public class DictMetaData extends BaseMetaData{
	
	private String key;
	private String value;

	public DictMetaData(String key, String value){
	    this.key = key;
	    this.value = value;
	}
	
	public DictMetaData(DictData dict){
        this.key = dict.getLabel();
        this.value = dict.name();
    }
	
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        DictMetaData other = (DictMetaData) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DictMetaData [key=" + key + ", value=" + value + "]";
    }

}
