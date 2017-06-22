package com.shhxzq.crm.react.base.page.model;

import java.io.Serializable;
import java.util.List;

/**
 * @TableMetaInfo.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年6月21日, ©2017 some rights reserved
 */
public class TableMetaData implements Serializable{
	
    private String title;
	private List<FieldMetaData> searchFields;
	private List<ButtonMetaData> buttons;
	private List<List<FieldMetaData>> fields;
	private Integer totle;
	private Integer totlePage;
	private Integer pageNo;
	private Integer pageSize;
	

}
