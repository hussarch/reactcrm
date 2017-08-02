package com.shhxzq.dc.scs.frm.base;

import com.shhxzq.dc.scs.frm.base.rest.model.templet.FieldMetaData;

/**
 * @TestNow.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年8月2日, ©2017 some rights reserved
 */
public class TestNow {
    
    public static void main(String[] args){
        FieldMetaData fmd =  new FieldMetaData();
        fmd.setName("n1");
        fmd.setLabel("L1");
        fmd.setLength(1);
        fmd.setHidden(false);
        fmd.setRequired(true);
        fmd.setType("string");
        
        FieldMetaData fc = (FieldMetaData) fmd.clone();
        System.out.println(fc.equals(fmd));
        fc.setHidden(true);
        System.out.println(fc.equals(fmd));
    }
    
}
