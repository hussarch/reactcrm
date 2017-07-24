package com.shhxzq.dc.scs.frm.cdcache.getter;

import org.springframework.stereotype.Service;

import com.shhxzq.dc.scs.frm.base.page.model.ConfDataMetaData;
import com.shhxzq.dc.scs.frm.cdcache.AdapterConfDataJson2Java;

/**
 * @author XiaoYi
 * Created on 2017-07-24 11:21:53
 */
@Service("localAdapterConfDataGetter")
public class LocalAdapterConfDataGetter extends AdapterConfDataGetter{
    
    private AdapterConfDataJson2Java adapterConfDataJson2Java;
    
    public LocalAdapterConfDataGetter(String rootPath){
        adapterConfDataJson2Java = new AdapterConfDataJson2Java(rootPath);
    }
    
    @Override
    public ConfDataMetaData get(String pathName){
        return adapterConfDataJson2Java.getConfDataMetaData(pathName);
    }
    
    
}
