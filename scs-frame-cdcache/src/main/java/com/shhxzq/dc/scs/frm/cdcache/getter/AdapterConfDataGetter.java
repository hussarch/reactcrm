package com.shhxzq.dc.scs.frm.cdcache.getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.shhxzq.dc.scs.frm.base.page.model.ConfDataMetaData;

/**
 * @author XiaoYi
 * Created on 2017-07-23 22:57:38
 */
public abstract class AdapterConfDataGetter {
    
    private Logger logger = Logger.getLogger(this.getClass());

    private Map<String, ConfDataMetaData> confDataMetaDataMap = new ConcurrentHashMap<>();
    
    
    public ConfDataMetaData getConfDataMetaData(String pathName){
        ConfDataMetaData data = confDataMetaDataMap.get(pathName);
        if(data == null){
            data = get(pathName);
            if(data != null){
                confDataMetaDataMap.put(pathName, data);
            }else{
                logger.error("Get the conf data failed: " + pathName);
            }
        }
        return data;
    }

    public abstract ConfDataMetaData get(String pathName);
    
    
    public Class<?> getEntityClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            logger.error("Can not find the class: " + className, e);
        }
        return null;
    }
    
}
