package com.hussar.dc.scs.frm.core.adapter;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hussar.dc.scs.frm.base.rest.model.templet.EnumDictMetaData;
import com.hussar.dc.scs.frm.base.rest.model.templet.FieldMetaData;

/**
 * @author XiaoYi
 * Created on 2017-07-26 16:51:57
 */
public interface JpaApadterService {

    Page<?> getPage(Class<?> clazz, Pageable pageable, Map<String, String> params, List<FieldMetaData> fields);

    List<?> getList(Class<?> clazz, Map<String, String> params, List<FieldMetaData> fields);

    Object get(Class<?> clazz, Integer id);

    void update(Class<?> clazz, String json);

    void add(Class<?> clazz, String json);

    void delete(Class<?> clazz, Integer primaryKey);
    
    List<EnumDictMetaData> queryDictList(String sql);

}
