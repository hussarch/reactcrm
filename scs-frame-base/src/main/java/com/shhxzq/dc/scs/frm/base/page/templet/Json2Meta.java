package com.shhxzq.dc.scs.frm.base.page.templet;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shhxzq.dc.scs.frm.base.common.utils.CommonFileUtils;
import com.shhxzq.dc.scs.frm.base.page.model.templet.ApiMetaData;
import com.shhxzq.dc.scs.frm.base.page.model.templet.GlobalSettingMetaData;
import com.shhxzq.dc.scs.frm.base.page.model.templet.ModaldialogMetaData;
import com.shhxzq.dc.scs.frm.base.page.model.templet.TableMetaData;
import com.shhxzq.dc.scs.frm.base.page.type.ConfFileType;

/**
 * @author XiaoYi
 * Created on 2017-07-16 17:27:13
 */
public class Json2Meta {
    
    private String path;
    private Gson gson;
    
    public Json2Meta(String path){
        if(!path.endsWith("/")){
            this.path = path + "/";
        }else{
            this.path = path;
        }
        gson = new Gson();
    }
    
    public GlobalSettingMetaData getConfMetaData(){
        String json = CommonFileUtils.readFileContent(this.path + ConfFileType.global.getFileName());
        return gson.fromJson(json, GlobalSettingMetaData.class);
    }
    
    public TableMetaData getTableMetaData(){
        String json = CommonFileUtils.readFileContent(this.path + ConfFileType.table.getFileName());
        return gson.fromJson(json, TableMetaData.class);
    }
    
    public ModaldialogMetaData getAddModaldialogMetaData(){
        return getAddModaldialogMetaData(ConfFileType.add);
    }
    
    public ModaldialogMetaData getUpdateModaldialogMetaData(){
        return getAddModaldialogMetaData(ConfFileType.update);
    }
    
    public ModaldialogMetaData getViewModaldialogMetaData(){
        return getAddModaldialogMetaData(ConfFileType.view);
    }
    
    private ModaldialogMetaData getAddModaldialogMetaData(ConfFileType type){
        String json = CommonFileUtils.readFileContent(this.path + type.getFileName());
        return gson.fromJson(json, ModaldialogMetaData.class);
    }
    
    public Map<String, ApiMetaData> getApiMetaDataMap(){
        String json = CommonFileUtils.readFileContent(this.path + ConfFileType.api.getFileName());
        return gson.fromJson(json, new TypeToken<Map<String, ApiMetaData>>() {}.getType());
    }
    
   
}
