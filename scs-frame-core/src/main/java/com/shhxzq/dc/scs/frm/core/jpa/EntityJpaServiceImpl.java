package com.shhxzq.dc.scs.frm.core.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shhxzq.dc.scs.frm.core.jpa.model.FieldTypeValue;

/**
 * @author XiaoYi
 * Created on 2017-07-26 09:43:55
 */
@Service
public class EntityJpaServiceImpl implements EntityJpaService{
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Object get(Class<?> clazz, Integer primaryKey){
        return entityManager.find(clazz, primaryKey);
    }
    
    @Override
    public void add(Object entity){
        entityManager.persist(entity);
    }
    
    @Override
    public void update(Object entity){
        entityManager.merge(entity);
    }
    
    @Override
    public void delete(Class<?> clazz, Integer primaryKey){
        entityManager.remove(get(clazz, primaryKey));
    }
    
    @Override
    public <T> List<T> getList(Class<T> clazz,  Map<String, FieldTypeValue> params){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> rootFrom = criteriaQuery.from(clazz);
        setWhereCondition(criteriaBuilder, criteriaQuery, rootFrom, params);
        // 默认按照id排序（从小到大）
        //criteriaQuery.orderBy(criteriaBuilder.asc(rootFrom.get("id")));
        // SQL查询对象
        TypedQuery<T> createQuery = entityManager.createQuery(criteriaQuery);
        return createQuery.getResultList();
    }
    
    @Override
    public <T> Page<T> getPage(Class<T> clazz, Pageable pageable, Map<String, FieldTypeValue> params){
        int total = getTotalCount(clazz, params);
        if(total == 0){
            return new PageImpl<T>(null, pageable, 0);
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> rootFrom = criteriaQuery.from(clazz);
        setWhereCondition(criteriaBuilder, criteriaQuery, rootFrom, params);
        // 默认按照id排序（从小到大）
        //criteriaQuery.orderBy(criteriaBuilder.asc(rootFrom.get("id")));
        // 分页参数
        Integer pageSize = pageable.getPageSize();
        Integer pageNo = pageable.getPageNumber();
        // SQL查询对象
        TypedQuery<T> createQuery = entityManager.createQuery(criteriaQuery);
        // 实际查询返回分页对象
        int startIndex = pageSize * pageNo;
        createQuery.setFirstResult(startIndex);
        createQuery.setMaxResults(pageable.getPageSize());
        Page<T> pageRst = new PageImpl<T>(createQuery.getResultList(), pageable, total);
        return pageRst;
    }
    
    private <T> int getTotalCount(Class<T> clazz, Map<String, FieldTypeValue> params){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        Root<T> rootFrom = criteria.from(clazz);
        criteria.select(criteriaBuilder.count(rootFrom));
        setWhereCondition(criteriaBuilder, criteria, rootFrom, params);
        return entityManager.createQuery(criteria).getSingleResult().intValue();
    }

    private <T> void setWhereCondition(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteria, Root<T> rootFrom,
            Map<String, FieldTypeValue> params) {
        // 查询条件
        if (null != params && !params.isEmpty()) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            FieldTypeValue tv;
            for(String key : params.keySet()){
                tv = params.get(key);
                Predicate predicate = criteriaBuilder.equal(rootFrom.get(key).as(tv.getType()), tv.getValue());
                predicates.add(predicate);
            }
            // 格式化参数
            criteria.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        }
    }
    
}
