package com.hussar.dc.scs.frm.core.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.hussar.dc.scs.frm.base.rest.model.templet.EnumDictMetaData;
import com.hussar.dc.scs.frm.base.rest.model.templet.FieldMetaData;
import com.hussar.dc.scs.frm.core.jpa.EntityJpaService;
import com.hussar.dc.scs.frm.core.jpa.model.FieldTypeValue;

/**
 * @author XiaoYi
 * Created on 2017-07-26 16:11:27
 */
@Service
public class JpaApadterServiceImpl implements JpaApadterService{
    
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private EntityJpaService entityJpaService;
    
    @Override
    public void add(Class<?> clazz, String json){
        Gson gson = new Gson();
        Object entity = gson.fromJson(json, clazz);
        entityJpaService.add(entity);
    }
    
    @Override
    public void update(Class<?> clazz, String json){
        Gson gson = new Gson();
        Object entity = gson.fromJson(json, clazz);
        entityJpaService.update(entity);
    }
    
    @Override
    public Object get(Class<?> clazz, Integer id){
        return entityJpaService.get(clazz, id);
    }
    
    @Override
    public List<?> getList(Class<?> clazz, Map<String, String> params, List<FieldMetaData> fields){
        return entityJpaService.getList(clazz, getTypeValueParams(clazz, params, fields));
    }
    
    @Override
    public Page<?> getPage(Class<?> clazz, Pageable pageable, Map<String, String> params, List<FieldMetaData> fields){
        return entityJpaService.getPage(clazz, pageable, getTypeValueParams(clazz, params, fields));
    }

    private Map<String, FieldTypeValue> getTypeValueParams(Class<?> clazz, Map<String, String> params, List<FieldMetaData> fields) {
        if(params == null || params.isEmpty()){
            return null;
        }
        Map<String, FieldTypeValue> typeValueMap = new HashMap<>();
        for(String key : params.keySet()){
            typeValueMap.put(key, getTypeValue(key, fields, params.get(key)));
        }
        return typeValueMap;
    }

    private FieldTypeValue getTypeValue(String name, List<FieldMetaData> fields, String value) {
        String clazz = null;
        for(FieldMetaData fmd : fields){
            if(fmd.getName().equals(name)){
                clazz = fmd.getClazz();
            }
        }
        if(StringUtils.isBlank(clazz)){
            throw new RuntimeException("The field " + name + " have not define");
        }
        Class<?> feildType;
        try {
            feildType = Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not find: " + clazz, e);
        }
        Object feildValue;
        if(Integer.class.equals(feildType)){
            feildValue = Integer.valueOf(value);
        }else if(Integer.class.equals(feildType)){
            feildValue = Integer.valueOf(value);
        }else if(Long.class.equals(feildType)){
            feildValue = Long.valueOf(value);
        }else if(Float.class.equals(feildType)){
            feildValue = Float.valueOf(value);
        }else if(Date.class.equals(feildType)){
            try {
                feildValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
            } catch (ParseException e) {
                logger.error("parse error" + value);
                throw new RuntimeException("parse error" + value, e);
            }
        }else if(String.class.equals(feildType)){
            feildValue = value;
        }else{
            throw new RuntimeException("Not support param type" + clazz);
        } 
        return new FieldTypeValue(feildType, feildValue);
    }
    
    @Override
    public void delete(Class<?> clazz, Integer primaryKey){
        entityJpaService.delete(clazz, primaryKey);
    }

    @Override
    public List<EnumDictMetaData> queryDictList(String sql) {
        return this.entityJpaService.queryDictList(sql);
    }
    
    
}
