package com.shhxzq.dc.scs.frm.cdcache.getter;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shhxzq.dc.scs.frm.base.common.type.ConfFileType;
import com.shhxzq.dc.scs.frm.base.common.utils.CommonFileUtils;
import com.shhxzq.dc.scs.frm.base.rest.model.ConfDataMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.ApiMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.BaseMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CommonSettingMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CrudMetaData;

/**
 * @author XiaoYi
 * Created on 2017-07-24 11:21:53
 */
@Service("localAdapterConfDataGetter")
public class LocalAdapterConfDataGetter extends AdapterConfDataGetter{
    
    @Value("${adapter.data.folder}")
    private String confDataRootPath;
    
    @Override
    public ConfDataMetaData get(String pathName){
        return get(pathName.split("[.]"));
    }
        
    public ConfDataMetaData get(String[] pathNames){
        String path = getPath(pathNames);
        File file = new File(path);
        if(!file.exists()){
            return null;
        }
        ConfDataMetaData confData = new ConfDataMetaData();
        confData.setCommon(getMetaData(path, ConfFileType.common.getFileName(), CommonSettingMetaData.class));
        confData.setCrud(getMetaData(path, ConfFileType.crud.getFileName(), CrudMetaData.class));
        confData.setApis(getApiMetaData(path, ConfFileType.api.getFileName(), new TypeToken<Map<String, ApiMetaData>>() {}.getType()));
        return confData;
    }

    private String getPath(String[] pathNames){
        String path = "/";
        for(String item : pathNames){
            path = path + item + "/";
        }
        return confDataRootPath + path;
    }
    
    private <T extends BaseMetaData> T getMetaData(String path, String fn, Class<T> clazz){
        String json = CommonFileUtils.readFileContent(path + "/" + fn);
        if(json == null){
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
    
    private Map<String, ApiMetaData> getApiMetaData(String path, String fn, Type type){
        String json = CommonFileUtils.readFileContent(path + "/" + fn);
        if(json == null){
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
    
    
}
