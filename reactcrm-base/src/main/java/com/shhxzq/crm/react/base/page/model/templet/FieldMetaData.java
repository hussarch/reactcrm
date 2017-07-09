package com.shhxzq.crm.react.base.page.model.templet;

import java.io.Serializable;

import com.shhxzq.crm.react.base.page.type.FieldType;

/**
 * @FieldMetaInfo.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年6月21日, ©2017 some rights reserved
 */
public class FieldMetaData implements Serializable{
    
    private String label;
    private String name;
    private Boolean hidden;
    private FieldType type;
    private Integer size;

}
