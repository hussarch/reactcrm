package com.shhxzq.crm.react.base.page.anotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.shhxzq.crm.react.base.page.type.PageType;


/**
 * @ClazzDesc.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年7月9日, ©2017 some rights reserved
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface PageDesc {
    
    String serviceId();
    String title();
    PageType mainPage() default PageType.table;
    String renderedJs() default "List.js";
    String[] params() default {};
    String hbtEntityClass() default "";
}