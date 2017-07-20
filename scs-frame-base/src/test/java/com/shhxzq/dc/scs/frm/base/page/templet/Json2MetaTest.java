package com.shhxzq.dc.scs.frm.base.page.templet;

/**
 * @Json2MetaTest.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2017年7月17日, ©2017 some rights reserved
 */
public class Json2MetaTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Json2Meta meta = new Json2Meta("E:/GitRepository/reactcrm/reactcrm-generator/src/test/java/com/shhxzq/crm/react/generator/tmpt");
        System.out.println(meta.getConfMetaData());
        System.out.println(meta.getTableMetaData());
        System.out.println(meta.getAddModaldialogMetaData());
        System.out.println(meta.getUpdateModaldialogMetaData());
        System.out.println(meta.getViewModaldialogMetaData());
        System.out.println(meta.getApiMetaDataMap());
    }

}
