package com.shhxzq.dc.scs.frm.cdcache.getter;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shhxzq.dc.scs.frm.base.common.utils.CommonFileUtils;
import com.shhxzq.dc.scs.frm.base.page.model.ConfDataMetaData;
import com.shhxzq.dc.scs.frm.base.page.model.templet.ApiMetaData;
import com.shhxzq.dc.scs.frm.base.page.model.templet.BaseMetaData;
import com.shhxzq.dc.scs.frm.base.page.model.templet.DictMetaData;
import com.shhxzq.dc.scs.frm.base.page.model.templet.GlobalSettingMetaData;
import com.shhxzq.dc.scs.frm.base.page.model.templet.ModaldialogMetaData;
import com.shhxzq.dc.scs.frm.base.page.model.templet.TableMetaData;
import com.shhxzq.dc.scs.frm.base.page.type.ConfFileType;

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
        confData.setGlobal(getMetaData(path, ConfFileType.global.getFileName(), GlobalSettingMetaData.class));
        confData.setTable(getMetaData(path, ConfFileType.table.getFileName(), TableMetaData.class));
        confData.setAdd(getMetaData(path, ConfFileType.add.getFileName(), ModaldialogMetaData.class));
        confData.setUpdate(getMetaData(path, ConfFileType.update.getFileName(), ModaldialogMetaData.class));
        confData.setView(getMetaData(path, ConfFileType.view.getFileName(), ModaldialogMetaData.class));
        confData.setApis(getApiMetaData(path, ConfFileType.api.getFileName(), new TypeToken<Map<String, ApiMetaData>>() {}.getType()));
        confData.setDicts(getDictsMetaData(path, ConfFileType.dict.getFileName(), new TypeToken<Map<String, DictMetaData[]>>() {}.getType()));
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
    
    private Map<String, DictMetaData[]> getDictsMetaData(String path, String fn, Type type){
        String json = CommonFileUtils.readFileContent(path + "/" + fn);
        if(json == null){
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
    
    
}
