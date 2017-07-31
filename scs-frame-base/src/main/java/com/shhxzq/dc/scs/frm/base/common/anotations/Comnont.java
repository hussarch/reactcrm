package com.shhxzq.dc.scs.frm.base.common.anotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author XiaoYi
 * Created on 2017-07-31 14:08:33
 */
@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Comnont {
    
    String value();
    
}
