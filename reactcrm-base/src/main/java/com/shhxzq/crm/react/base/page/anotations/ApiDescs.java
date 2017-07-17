package com.shhxzq.crm.react.base.page.anotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author XiaoYi
 * Created on 2017-07-17 11:18:24
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ApiDescs {
    
    ApiDesc[] value();
    
}
