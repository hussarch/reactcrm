package com.shhxzq.crm.react.generator;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.shhxzq.crm.react.base.page.anotations.ApiDesc;
import com.shhxzq.crm.react.base.page.anotations.FieldDesc;
import com.shhxzq.crm.react.base.page.anotations.PageDesc;
import com.shhxzq.crm.react.base.page.model.templet.ApiMetaData;
import com.shhxzq.crm.react.base.page.model.templet.ButtonMetaData;
import com.shhxzq.crm.react.base.page.model.templet.ConfMetaData;
import com.shhxzq.crm.react.base.page.model.templet.FieldMetaData;
import com.shhxzq.crm.react.base.page.model.templet.ModaldialogMetaData;
import com.shhxzq.crm.react.base.page.model.templet.TableMetaData;
import com.shhxzq.crm.react.base.page.type.ApiType;
import com.shhxzq.crm.react.base.page.type.MethodType;
import com.shhxzq.crm.react.base.page.type.PageType;

/**
 * @Entity2MetaGenerator.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年6月22日, ©2017 some rights reserved
 */
public class Entity2Meta {
    
    private String title;
    private Class<?> sourceClass;
    private Map<MethodType, ButtonMetaData> buttonMap = new HashMap<>();
    
    public Entity2Meta(Class<?> clazz){
        this.sourceClass = clazz;
        initButtonMetaData(MethodType.add, "确定");
        initButtonMetaData(MethodType.update, "确定");
        initButtonMetaData(MethodType.delete, "删除");
        initButtonMetaData(MethodType.view, "查看");
        initButtonMetaData(MethodType.search, "查询");
        initButtonMetaData(MethodType.close, "取消");
    }
    
    private void initButtonMetaData(MethodType type, String name){
        buttonMap.put(type, new ButtonMetaData(name, type.name(), type));
    }

    public ConfMetaData getConfMetaData() {
        PageDesc classDesc = this.sourceClass.getAnnotation(PageDesc.class);
        this.title = classDesc.title();
        ConfMetaData data = new ConfMetaData();
        if(StringUtils.isNotBlank(classDesc.hbtEntityClass())){
            data.setClazz(classDesc.hbtEntityClass());
        }else{
            data.setClazz(this.sourceClass.getName());
        }
        data.setMainPage(classDesc.mainPage().toString());
        data.setRenderedJs(classDesc.renderedJs());
        if(classDesc.params() != null){
            for(String name : classDesc.params()){
                data.addParamName(name);
            }
        }
        return data;
    }

    public TableMetaData getTableMetaData() {
        TableMetaData data = new TableMetaData();
        data.setTitle(this.title + "列表");
        data.setSearchFields(getPageFeildMetaDatas(PageType.search));
        data.setButtons(getButtons(MethodType.add, MethodType.update, MethodType.delete, MethodType.search));
        data.setColumns(getPageFeildMetaDatas(PageType.table));
        return data;
    }

    private List<ButtonMetaData> getButtons(MethodType... types) {
        List<ButtonMetaData> list = new ArrayList<>();
        for(MethodType type : types){
            list.add(buttonMap.get(type));
        }
        return list;
    }
    
    private List<FieldMetaData> getFeildMetaDatas(FeildMetaFilter filter) {
        List<FieldMetaData> list = new ArrayList<>();
        Field[] fields = sourceClass.getDeclaredFields();
        FieldMetaData data = null;
        for(Field field : fields){
            data = filter.getFieldMetaData(field);
            if(data != null){
                list.add(data);
            }
        }
        return list;
    }
    
    private List<FieldMetaData> getPageFeildMetaDatas(PageType type) {
        return getFeildMetaDatas(new FeildMetaFilter(){

            @Override
            public FieldMetaData getFieldMetaData(Field field) {
                return Entity2Meta.this.getFieldMetaData(field, type);
            }
            
        });
    }
    
    
    private FieldMetaData getFieldMetaData(Field field, PageType type){
        FieldDesc fieldDesc = field.getAnnotation(FieldDesc.class);
        if(fieldDesc != null){
            if(PageType.search.equals(type)){
                if(!fieldDesc.search()){
                    return null;
                }
            }else if(!isShow(type, fieldDesc.showIn(), fieldDesc.notShowIn())){
                return null;
            }
            return getFieldMetaData(field, type, fieldDesc);
        }else{
            return null;
        }
    }
    
    private FieldMetaData getFieldMetaData(Field field, PageType type, FieldDesc desc){
        FieldMetaData data = new FieldMetaData();
        data.setLabel(desc.label());
        data.setHidden(desc.hidden());
        data.setName(field.getName());
        data.setType(getFieldType(field));
        if((PageType.add.equals(type) || PageType.update.equals(type)) && Integer.MAX_VALUE - desc.max() != 0){
            data.setSize(desc.max());
        }
        return data;
    }
    
    
    private boolean isShow(PageType type, PageType[] showIn, PageType[] notShowIn){
        if(!inArray(type, showIn)){
            return false;
        }
        if(inArray(type, notShowIn)){
            return false;
        }else{
            return true;
        }
    }
    
    private boolean inArray(PageType type, PageType[] array){
        if(array  != null && array.length > 0){
            for(PageType st : array){
                if(st.equals(type)){
                    return true;
                }
            }
        }
        return false;
    }

    private String getFieldType(Field field) {
        if(field.getClass().isPrimitive()){
            return field.getClass().getName();
        }else if(String.class.equals(field.getClass())){
            return "string";
        }else if(Date.class.equals(field.getClass())){
            return "datetime";
        }
        return null;
    }

    public ModaldialogMetaData getAddMetaData() {
        ModaldialogMetaData data = new ModaldialogMetaData();
        data.setTitle("增加" + this.title);
        data.setFields(getPageFeildMetaDatas(PageType.add));
        data.setButtons(getButtons(MethodType.add, MethodType.close));
        return data;
    }

    public ModaldialogMetaData getUpdateMetaData() {
        ModaldialogMetaData data = new ModaldialogMetaData();
        data.setTitle("修改" + this.title);
        data.setFields(getPageFeildMetaDatas(PageType.update));
        data.setButtons(getButtons(MethodType.update, MethodType.close));
        return data;
    }

    public ModaldialogMetaData getViewMetaData() {
        ModaldialogMetaData data = new ModaldialogMetaData();
        data.setTitle("查看" + this.title);
        data.setFields(getPageFeildMetaDatas(PageType.view));
        data.setButtons(getButtons(MethodType.close));
        return data;
    }
    
    public Map<String, ApiMetaData> getApiMetaData(){
        ApiDesc[] apiDescs = this.sourceClass.getAnnotationsByType(ApiDesc.class);
        if(apiDescs == null || apiDescs.length == 0){
            return null;
        }
        Map<String, ApiMetaData> map = new LinkedHashMap<>();
        for(ApiDesc desc : apiDescs){
            ApiMetaData data = new ApiMetaData();
            data.setTitle(desc.name());
            if(desc.params() != null) {
                for(String name : desc.params()){
                    data.addParamName(name);
                }
            }
            data.setFields(getApiFields(desc.type()));
            map.put(desc.type().name(), data);
        }
        return map;
    }

    private List<FieldMetaData> getApiFields(ApiType type) {
        return getFeildMetaDatas(new FeildMetaFilter(){

            @Override
            public FieldMetaData getFieldMetaData(Field field) {
                FieldDesc fieldDesc = field.getAnnotation(FieldDesc.class);
                if(fieldDesc != null && fieldDesc.apiType() != null && fieldDesc.apiType().length > 0){
                    for(ApiType set : fieldDesc.apiType()){
                        if(set.equals(type)){
                            FieldMetaData data = new FieldMetaData();
                            data.setLabel(fieldDesc.label());
                            data.setName(field.getName());
                            data.setType(getFieldType(field));
                            return data;
                        }
                    }
                }
                return null;
            }
            
        });
    }
    
    private interface FeildMetaFilter{
        
        FieldMetaData getFieldMetaData(Field field);
        
    }
    
}
