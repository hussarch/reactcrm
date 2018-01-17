package com.hussar.dc.scs.frm.core.adapter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

/**
 * @author XiaoYi
 * Created on 2017-07-26 16:22:49
 */
public class FileTypeService {
    
    private Logger logger = Logger.getLogger(this.getClass());
    
    private Map<String, Class<?>> fieldTypeMap = new ConcurrentHashMap<String, Class<?>>();
    
    private ReentrantLock locker = new ReentrantLock();
    
    public Class<?> getFiledType(Class<?> entityClass, String filedName){
        String className = entityClass.getName();
        String typeKey = className + "." + filedName;
        Class<?> type = fieldTypeMap.get(typeKey);
        if(type == null){
            try{
                locker.lock();
                type = entityClass.getField(filedName).getType();
                fieldTypeMap.put(typeKey, type);
            } catch (NoSuchFieldException e) {
                logger.error("getFiledType", e);
            } catch (SecurityException e) {
                logger.error("getFiledType", e);
            }finally {
                locker.unlock();
            }
        }
        return type;
    }
    
    
    
    
}
