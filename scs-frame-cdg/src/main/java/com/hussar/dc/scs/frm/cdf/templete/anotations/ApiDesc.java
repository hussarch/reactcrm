package com.hussar.dc.scs.frm.cdf.templete.anotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

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
    String[] showFileds() default {};
    String[] notShowFileds() default {};
    
}
