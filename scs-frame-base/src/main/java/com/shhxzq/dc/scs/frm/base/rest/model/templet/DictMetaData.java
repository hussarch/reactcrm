package com.shhxzq.dc.scs.frm.base.rest.model.templet;

import java.util.List;

/**
 * @DictMetaInfo.java
 * @author XiaoYi(hussarch@126.com) Created on 2017年6月22日, ©2017 some rights
 *         reserved
 */
public class DictMetaData extends BaseMetaData {

    private List<EnumDictMetaData> enumDict;

    private String entityDictHql;

    public List<EnumDictMetaData> getEnumDict() {
        return enumDict;
    }

    public void setEnumDict(List<EnumDictMetaData> enumDict) {
        this.enumDict = enumDict;
    }

    public String getEntityDictHql() {
        return entityDictHql;
    }

    public void setEntityDictHql(String entityDictHql) {
        this.entityDictHql = entityDictHql;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((entityDictHql == null) ? 0 : entityDictHql.hashCode());
        result = prime * result + ((enumDict == null) ? 0 : enumDict.hashCode());
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
        if (entityDictHql == null) {
            if (other.entityDictHql != null)
                return false;
        } else if (!entityDictHql.equals(other.entityDictHql))
            return false;
        if (enumDict == null) {
            if (other.enumDict != null)
                return false;
        } else if (!enumDict.equals(other.enumDict))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DictMetaData [enumDict=" + enumDict + ", entityDictHql=" + entityDictHql + "]";
    }

}
