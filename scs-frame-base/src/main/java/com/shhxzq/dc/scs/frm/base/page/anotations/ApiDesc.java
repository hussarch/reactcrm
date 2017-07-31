package com.shhxzq.dc.scs.frm.base.page.anotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.shhxzq.dc.scs.frm.base.page.type.ApiType;

/**
 * @author XiaoYi
 * Created on 2017-07-17 11:18:24
 */
@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Repeatable(ApiDescs.class)
public @interface ApiDesc {
    
    String serviceId();
    String name();
    String[] params() default {};
    ApiType type();
    
}
