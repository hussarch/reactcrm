package com.hussar.dc.scs.frm.base.rest.model;

import java.util.Map;

import com.hussar.dc.scs.frm.base.rest.model.templet.ApiMetaData;
import com.hussar.dc.scs.frm.base.rest.model.templet.CommonSettingMetaData;
import com.hussar.dc.scs.frm.base.rest.model.templet.CrudMetaData;

/**
 * @author XiaoYi Created on 2017-07-23 14:07:37
 */
public class ConfDataMetaData {

    private CommonSettingMetaData common;
    private CrudMetaData crud;
    private Map<String, ApiMetaData> apis;

    public CommonSettingMetaData getCommon() {
        return common;
    }

    public void setCommon(CommonSettingMetaData common) {
        this.common = common;
    }

    public CrudMetaData getCrud() {
        return crud;
    }

    public void setCrud(CrudMetaData crud) {
        this.crud = crud;
    }

    public Map<String, ApiMetaData> getApis() {
        return apis;
    }

    public void setApis(Map<String, ApiMetaData> apis) {
        this.apis = apis;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((apis == null) ? 0 : apis.hashCode());
        result = prime * result + ((common == null) ? 0 : common.hashCode());
        result = prime * result + ((crud == null) ? 0 : crud.hashCode());
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
        ConfDataMetaData other = (ConfDataMetaData) obj;
        if (apis == null) {
            if (other.apis != null)
                return false;
        } else if (!apis.equals(other.apis))
            return false;
        if (common == null) {
            if (other.common != null)
                return false;
        } else if (!common.equals(other.common))
            return false;
        if (crud == null) {
            if (other.crud != null)
                return false;
        } else if (!crud.equals(other.crud))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ConfDataMetaData [common=" + common + ", crud=" + crud + ", apis=" + apis + "]";
    }

}
