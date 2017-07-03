package com.shhxzq.crm.react.base.page.model;

import java.io.Serializable;
import java.util.List;

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
	private String value;
	private List<DictMetaData> dicts;

}
