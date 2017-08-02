package com.shhxzq.dc.scs.frm.base.rest.model.templet;

import java.io.Serializable;

/**
 * @author XiaoYi
 * Created on 2017-07-23 13:58:11
 */
public abstract class BaseMetaData implements Serializable, Cloneable{
    
    public BaseMetaData clone(){
        try {
            return (BaseMetaData)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone failed", e);
        }
    }
    
    
}
