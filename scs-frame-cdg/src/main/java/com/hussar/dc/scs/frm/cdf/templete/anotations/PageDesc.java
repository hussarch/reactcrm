package com.hussar.dc.scs.frm.cdf.templete.anotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.hussar.dc.scs.frm.base.common.type.PageType;


/**
 * @ClazzDesc.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年7月9日, ©2017 some rights reserved
 */
@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface PageDesc {
    
    String[] path();
    String name();
    Class<?> entityClass();
    PageType mainPage() default PageType.table;
    String renderedJs() default "List.js";
    String category() default "";
    String[] showFileds() default {};
    String[] notShowFileds() default {};
    
}
