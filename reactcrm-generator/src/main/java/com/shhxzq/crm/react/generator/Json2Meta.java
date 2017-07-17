package com.shhxzq.crm.react.generator;

import com.google.gson.Gson;
import com.shhxzq.crm.react.base.common.utils.CommonFileUtils;
import com.shhxzq.crm.react.base.page.model.templet.ConfMetaData;
import com.shhxzq.crm.react.base.page.model.templet.ModaldialogMetaData;
import com.shhxzq.crm.react.base.page.model.templet.TableMetaData;

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
    
    public ConfMetaData getConfMetaData(){
        String json = CommonFileUtils.readFileContent(this.path + ConfMetaData.tmpletName);
        return gson.fromJson(json, ConfMetaData.class);
    }
    
    public TableMetaData getTableMetaData(){
        String json = CommonFileUtils.readFileContent(this.path + TableMetaData.tmpletName);
        return gson.fromJson(json, TableMetaData.class);
    }
    
    public ModaldialogMetaData getAddModaldialogMetaData(){
        return getAddModaldialogMetaData(ModaldialogMetaData.tmpletNameAdd);
    }
    
    public ModaldialogMetaData getUpdateModaldialogMetaData(){
        return getAddModaldialogMetaData(ModaldialogMetaData.tmpletNameUpate);
    }
    
    public ModaldialogMetaData getViewModaldialogMetaData(){
        return getAddModaldialogMetaData(ModaldialogMetaData.tmpletNameView);
    }
    
    public ModaldialogMetaData getAddModaldialogMetaData(String fileName){
        String json = CommonFileUtils.readFileContent(this.path + fileName);
        return gson.fromJson(json, ModaldialogMetaData.class);
    }
    
    
    
    
}
