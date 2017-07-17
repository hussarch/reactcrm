package com.shhxzq.crm.react.base.page.anotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.shhxzq.crm.react.base.page.type.ApiType;

/**
 * @author XiaoYi
 * Created on 2017-07-17 11:18:24
 */
@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(ApiDescs.class)
public @interface ApiDesc {
    
    String serviceId();
    String name();
    String[] params() default {};
    ApiType type();
    
}
