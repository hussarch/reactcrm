package com.shhxzq.crm.react.generator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.shhxzq.crm.react.base.page.anotations.ClazzDesc;
import com.shhxzq.crm.react.base.page.anotations.FieldDesc;
import com.shhxzq.crm.react.base.page.model.templet.ConfMetaData;
import com.shhxzq.crm.react.base.page.model.templet.ModaldialogMetaData;
import com.shhxzq.crm.react.base.page.model.templet.TableMetaData;

/**
 * @Entity2MetaGenerator.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年6月22日, ©2017 some rights reserved
 */
public class Entity2MetaGenerator {
    
    private List<FieldDesc> fieldDescList;
    private String title;
    private Class<?> sourceClass;
    
    public Entity2MetaGenerator(Class<?> clazz){
        this.sourceClass = clazz;
        this.fieldDescList = this.getClassFieldDesc(clazz);
    }

    public ConfMetaData getConfJson() {
        ClazzDesc classDesc = this.sourceClass.getAnnotation(ClazzDesc.class);
        this.title = classDesc.title();
        ConfMetaData data = new ConfMetaData();
        data.setClazz(this.sourceClass.toString());
        data.setMainPage(classDesc.mainPage().toString());
        data.setRenderedJs(classDesc.renderedJs());
        if(classDesc.params() != null){
            for(String name : classDesc.params()){
                data.addParamName(name);
            }
        }
        return data;
    }

    public TableMetaData getTableJson() {
        TableMetaData data = new TableMetaData();
        
        
        
        return null;
    }

    public ModaldialogMetaData getAddJson() {
        return null;
    }

    public ModaldialogMetaData getUpdateJson() {
        return null;
    }

    public ModaldialogMetaData getViewJson() {
        return null;
    }
    
    private List<FieldDesc> getClassFieldDesc(Class<?> clazz){
        List<FieldDesc> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for( Field field : fields){
            FieldDesc fieldDesc = field.getAnnotation(FieldDesc.class);
            if(fieldDesc != null){
                list.add(fieldDesc);
            }
        }
        return list;
    }

}
