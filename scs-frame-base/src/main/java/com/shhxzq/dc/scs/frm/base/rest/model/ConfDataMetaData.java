package com.shhxzq.dc.scs.frm.base.rest.model;

import java.util.Map;

import com.shhxzq.dc.scs.frm.base.rest.model.templet.ApiMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.DictMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CommonSettingMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.ModaldialogMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.TableMetaData;

/**
 * @author XiaoYi
 * Created on 2017-07-23 14:07:37
 */
public class ConfDataMetaData {
    
    private CommonSettingMetaData global;
    private TableMetaData table;
    private ModaldialogMetaData add;
    private ModaldialogMetaData update;
    private ModaldialogMetaData view;
    private Map<String, ApiMetaData> apis;
    private Map<String, DictMetaData[]> dicts;
    
    public CommonSettingMetaData getGlobal() {
        return global;
    }
    
    public void setGlobal(CommonSettingMetaData global) {
        this.global = global;
    }
    
    public TableMetaData getTable() {
        return table;
    }
    
    public void setTable(TableMetaData table) {
        this.table = table;
    }
    
    public ModaldialogMetaData getAdd() {
        return add;
    }
    
    public void setAdd(ModaldialogMetaData add) {
        this.add = add;
    }
    
    public ModaldialogMetaData getUpdate() {
        return update;
    }
    
    public void setUpdate(ModaldialogMetaData update) {
        this.update = update;
    }
    
    public ModaldialogMetaData getView() {
        return view;
    }
    
    public void setView(ModaldialogMetaData view) {
        this.view = view;
    }

    
    public Map<String, ApiMetaData> getApis() {
        return apis;
    }

    
    public void setApis(Map<String, ApiMetaData> apis) {
        this.apis = apis;
    }

    
    public Map<String, DictMetaData[]> getDicts() {
        return dicts;
    }

    
    public void setDicts(Map<String, DictMetaData[]> dicts) {
        this.dicts = dicts;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((add == null) ? 0 : add.hashCode());
        result = prime * result + ((apis == null) ? 0 : apis.hashCode());
        result = prime * result + ((dicts == null) ? 0 : dicts.hashCode());
        result = prime * result + ((global == null) ? 0 : global.hashCode());
        result = prime * result + ((table == null) ? 0 : table.hashCode());
        result = prime * result + ((update == null) ? 0 : update.hashCode());
        result = prime * result + ((view == null) ? 0 : view.hashCode());
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
        if (add == null) {
            if (other.add != null)
                return false;
        } else if (!add.equals(other.add))
            return false;
        if (apis == null) {
            if (other.apis != null)
                return false;
        } else if (!apis.equals(other.apis))
            return false;
        if (dicts == null) {
            if (other.dicts != null)
                return false;
        } else if (!dicts.equals(other.dicts))
            return false;
        if (global == null) {
            if (other.global != null)
                return false;
        } else if (!global.equals(other.global))
            return false;
        if (table == null) {
            if (other.table != null)
                return false;
        } else if (!table.equals(other.table))
            return false;
        if (update == null) {
            if (other.update != null)
                return false;
        } else if (!update.equals(other.update))
            return false;
        if (view == null) {
            if (other.view != null)
                return false;
        } else if (!view.equals(other.view))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ConfDataMetaData [global=" + global + ", table=" + table + ", add=" + add + ", update=" + update + ", view=" + view + ", apis=" + apis
                + ", dicts=" + dicts + "]";
    }

    
}
