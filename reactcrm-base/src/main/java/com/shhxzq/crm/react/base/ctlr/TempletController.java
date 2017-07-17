package com.shhxzq.crm.react.base.ctlr;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shhxzq.crm.react.base.page.templet.Json2Meta;

/**
 * @TempletContruller.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年7月18日, ©2017 some rights reserved
 */
@RestController
@RequestMapping("/tmplet")
public class TempletController {
    
    @Value("templet.folder")
    private String templetFolderPath;
    
    
    @RequestMapping("/get")
    public Object getTemplet(@RequestParam String subPath, @RequestParam(required = false) String name){
        String path = templetFolderPath;
        if(!path.endsWith("/")){
            path = path + "/";
        }
        path = path + subPath ;
        File file = new File(path);
        if(!file.exists()){
            return "Wrong path: " + path;
        }
        Json2Meta json2Meta = new Json2Meta(path);
        if(StringUtils.isNoneBlank(name)){
            file = new File(path + "/" + name);
            if(!file.exists()){
                return "Wrong path: " + path;
            }
            return getMetaData(json2Meta, name);
        }else{
            Map<String, Object> map = new LinkedHashMap<>();
            name = "conf";
            map.put(name, getMetaData(json2Meta, name));
            name = "add";
            map.put(name, getMetaData(json2Meta, name));
            name = "update";
            map.put(name, getMetaData(json2Meta, name));
            name = "view";
            map.put(name, getMetaData(json2Meta, name));
            name = "api";
            map.put(name, getMetaData(json2Meta, name));
            return map;
        }
    }
    
    private Object getMetaData(Json2Meta json2Meta, String name){
        if(name.equals("conf")){
            return json2Meta.getConfMetaData();
        }else if(name.equals("conf")){
            return json2Meta.getTableMetaData();
        }else if(name.equals("add")){
            return json2Meta.getAddModaldialogMetaData();
        }else if(name.equals("update")){
            return json2Meta.getUpdateModaldialogMetaData();
        }else if(name.equals("view")){
            return json2Meta.getViewModaldialogMetaData();
        }else if(name.equals("api")){
            return json2Meta.getApiMetaDataMap();
        }else{
            return null;
        }
    }
    
    
}
