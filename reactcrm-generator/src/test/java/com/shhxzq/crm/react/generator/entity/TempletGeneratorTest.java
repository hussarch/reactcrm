package com.shhxzq.crm.react.generator.entity;

import com.shhxzq.crm.react.generator.TempletGenerator;

/**
 * @author XiaoYi
 * Created on 2017-07-16 13:52:24
 */
public class TempletGeneratorTest {
   
    public static void main(String[] args) {
        TempletGenerator.writeTemplet(DocumentEntity.class, "/Users/sailor/local/tmp/wd/", false);
    }
    
}
