package com.shhxzq.dc.scs.frm.core.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shhxzq.dc.scs.frm.base.rest.model.templet.EnumDictMetaData;
import com.shhxzq.dc.scs.frm.core.jpa.model.FieldTypeValue;

/**
 * @author XiaoYi Created on 2017-07-26 09:44:10
 */
public interface EntityJpaService {

    Object get(Class<?> clazz, Integer primaryKey);

    void add(Object entity);

    void update(Object entity);

    void delete(Class<?> clazz, Integer primaryKey);

    <T> List<T> getList(Class<T> clazz, Map<String, FieldTypeValue> params);

    <T> Page<T> getPage(Class<T> clazz, Pageable pageable, Map<String, FieldTypeValue> params);

    List<EnumDictMetaData> queryDictList(String sqlString);

}
