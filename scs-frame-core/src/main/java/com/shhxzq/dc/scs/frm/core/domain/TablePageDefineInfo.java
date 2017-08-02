package com.shhxzq.dc.scs.frm.core.domain;

import java.util.List;

import com.shhxzq.dc.scs.frm.base.rest.model.templet.FieldMetaData;

/**
 * @author XiaoYi Created on 2017-08-02 17:53:49
 */
public class TablePageDefineInfo extends CommonPageDefineInfo {

    private List<FieldMetaData> search;
    
    public List<FieldMetaData> getSearch() {
        return search;
    }

    public void setSearch(List<FieldMetaData> search) {
        this.search = search;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((search == null) ? 0 : search.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        TablePageDefineInfo other = (TablePageDefineInfo) obj;
        if (search == null) {
            if (other.search != null)
                return false;
        } else if (!search.equals(other.search))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TablePageDefineInfo [search=" + search + ", super: " + super.toString() +  "]";
    }
    
}
