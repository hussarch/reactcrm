package com.shhxzq.crm.react.base.page.anotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author XiaoYi
 * Created on 2017-07-19 09:22:55
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface DictDesc {
    
    
    String dictSql() default "";
    
    Class<?> dictEnumClass() default Object.class;
    
}
