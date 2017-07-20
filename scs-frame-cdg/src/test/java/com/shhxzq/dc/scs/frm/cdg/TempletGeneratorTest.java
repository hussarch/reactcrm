package com.shhxzq.dc.scs.frm.cdg;

import com.shhxzq.dc.scs.frm.cdf.TempletGenerator;
import com.shhxzq.dc.scs.frm.cdg.entity.DocumentEntity;

/**
 * @author XiaoYi
 * Created on 2017-07-16 13:52:24
 */
public class TempletGeneratorTest {
   
    public static void main(String[] args) {
        String path = "/Users/sailor/git/adapter-data-sys/conf-data/";
        TempletGenerator.writeTemplet(DocumentEntity.class, path, true);
    }
    
}
