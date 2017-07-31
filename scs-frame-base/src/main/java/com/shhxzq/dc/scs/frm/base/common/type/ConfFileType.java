package com.shhxzq.dc.scs.frm.base.common.type;


/**
 * @author XiaoYi
 * Created on 2017-07-23 13:28:15
 */
public enum ConfFileType {
    
    common,table,add,update,view,api,dict;
    
    public String getFileName(){
        return name() + ".json";
    }
    
}
