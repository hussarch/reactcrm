package com.shhxzq.dc.scs.frm.base.rest.model;

import java.util.Map;

import com.shhxzq.dc.scs.frm.base.rest.model.templet.ApiMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CommonSettingMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CrudMetaData;

/**
 * @author XiaoYi Created on 2017-07-23 14:07:37
 */
public class ConfDataMetaData {

    private CommonSettingMetaData global;
    private CrudMetaData crud;
    private Map<String, ApiMetaData> apis;

    public CommonSettingMetaData getGlobal() {
        return global;
    }

    public void setGlobal(CommonSettingMetaData global) {
        this.global = global;
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
        result = prime * result + ((crud == null) ? 0 : crud.hashCode());
        result = prime * result + ((global == null) ? 0 : global.hashCode());
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
        if (crud == null) {
            if (other.crud != null)
                return false;
        } else if (!crud.equals(other.crud))
            return false;
        if (global == null) {
            if (other.global != null)
                return false;
        } else if (!global.equals(other.global))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ConfDataMetaData [global=" + global + ", crud=" + crud + ", apis=" + apis + "]";
    }

}
