package com.shhxzq.dc.scs.frame.core.adapter;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author XiaoYi
 * Created on 2017-07-26 16:51:57
 */
public interface JpaApadterService {

    Page<?> getPage(Class<?> clazz, Pageable pageable, Map<String, String> params);

    List<?> getList(Class<?> clazz, Map<String, String> params);

    Object get(Class<?> clazz, Integer id);

    void update(Class<?> clazz, String json);

    void add(Class<?> clazz, String json);

    void delete(Class<?> clazz, Integer primaryKey);

}
