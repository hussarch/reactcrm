package com.hussar.dc.scs.frm.cdf.templete.anotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.hussar.dc.scs.frm.base.common.type.PageType;

/**
 * @author XiaoYi
 * Created on 2017-08-01 14:08:52
 */
@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Repeatable(PageFieldDescs.class)
public @interface PageFieldDesc {

    PageType type();
    String[] showFileds() default {};
    String[] notShowFileds() default {};
    String[] hiddenFileds() default {};
        
}
