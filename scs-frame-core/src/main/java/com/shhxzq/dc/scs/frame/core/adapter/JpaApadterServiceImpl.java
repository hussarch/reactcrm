package com.shhxzq.dc.scs.frame.core.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.shhxzq.dc.scs.frame.core.jpa.EntityJpaService;
import com.shhxzq.dc.scs.frame.core.jpa.model.FieldTypeValue;

/**
 * @author XiaoYi
 * Created on 2017-07-26 16:11:27
 */
@Service
public class JpaApadterServiceImpl implements JpaApadterService{
    
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private EntityJpaService entityJpaService;
    
    @Autowired
    private FileTypeService fileTypeService;
    
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
    public List<?> getList(Class<?> clazz, Map<String, String> params){
        return entityJpaService.getList(clazz, getTypeValueParams(clazz, params));
    }
    
    @Override
    public Page<?> getPage(Class<?> clazz, Pageable pageable, Map<String, String> params){
        return entityJpaService.getPage(clazz, pageable, getTypeValueParams(clazz, params));
    }

    private Map<String, FieldTypeValue> getTypeValueParams(Class<?> clazz, Map<String, String> params) {
        if(params == null || params.isEmpty()){
            return null;
        }
        Map<String, FieldTypeValue> typeValueMap = new HashMap<>();
        for(String key : params.keySet()){
            typeValueMap.put(key, getTypeValue(clazz, key, params.get(key)));
        }
        return typeValueMap;
    }

    private FieldTypeValue getTypeValue(Class<?> clazz, String key, String value) {
        Class<?> feildType = fileTypeService.getFiledType(clazz, key);
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
            throw new RuntimeException("Not support param type" + clazz.getName());
        }
        return new FieldTypeValue(feildType, feildValue);
    }
    
    @Override
    public void delete(Class<?> clazz, Integer primaryKey){
        entityJpaService.delete(clazz, primaryKey);
    }
    
    
}
