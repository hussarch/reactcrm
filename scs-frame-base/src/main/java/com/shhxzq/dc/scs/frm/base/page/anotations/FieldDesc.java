package com.shhxzq.dc.scs.frm.base.page.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.shhxzq.dc.scs.frm.base.page.type.ApiType;
import com.shhxzq.dc.scs.frm.base.page.type.PageType;

/**
 * @TempletDesc.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年7月9日, ©2017 some rights reserved
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME) 
public @interface FieldDesc {
    
    String label();
    String fieldAlias() default "";
    boolean search() default false;
    boolean reqired() default false;
    boolean hidden() default false;
    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
    PageType[] showIn() default {PageType.table, PageType.add, PageType.update, PageType.view};
    PageType[] notShowIn() default {};
    ApiType[] apiType() default {};
    
}