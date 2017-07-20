package com.shhxzq.dc.scs.frm.cdg;

import com.shhxzq.dc.scs.frm.cdf.TempletGenerator;
import com.shhxzq.dc.scs.frm.cdg.entity.DocumentEntity;

/**
 * @author XiaoYi
 * Created on 2017-07-16 13:52:24
 */
public class TempletGeneratorTest {
   
    public static void main(String[] args) {
        TempletGenerator.writeTemplet(DocumentEntity.class, "/Users/sailor/local/tmp/wd/", true);
    }
    
}
