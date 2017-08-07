package com.shhxzq.dc.scs.frm.core.jpa.model;


/**
 * @author XiaoYi
 * Created on 2017-08-07 16:27:34
 */
public class FieldOrderInfo {
    
    private String name;
    private boolean asc;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isAsc() {
        return asc;
    }
    
    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (asc ? 1231 : 1237);
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
        FieldOrderInfo other = (FieldOrderInfo) obj;
        if (asc != other.asc)
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
        return "FieldOrderInfo [name=" + name + ", asc=" + asc + "]";
    }
    
}
