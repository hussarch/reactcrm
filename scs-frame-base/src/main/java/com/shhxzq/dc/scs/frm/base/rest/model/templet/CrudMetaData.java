package com.shhxzq.dc.scs.frm.base.rest.model.templet;

/**
 * @author XiaoYi Created on 2017-08-02 16:03:25
 */
public class CrudMetaData extends BaseMetaData{

    private TableMetaData table;
    private ModaldialogMetaData add;
    private ModaldialogMetaData update;
    private ModaldialogMetaData view;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((add == null) ? 0 : add.hashCode());
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
        CrudMetaData other = (CrudMetaData) obj;
        if (add == null) {
            if (other.add != null)
                return false;
        } else if (!add.equals(other.add))
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
        return "CrudMetaData [table=" + table + ", add=" + add + ", update=" + update + ", view=" + view + "]";
    }

}
