package com.shhxzq.dc.scs.frm.cdg;

import java.util.ArrayList;
import java.util.List;

import com.shhxzq.dc.scs.frm.cdf.templete.TempletDefinition;

/**
 * @author XiaoYi
 * Created on 2017-08-01 11:11:04
 */
public class PageTempletDefinitions {
    
    private List<TempletDefinition> list;
    
    private PageTempletDefinitions(){
        this.list = new ArrayList<>();
    }
    
    public static PageTempletDefinitions getInstanse(){
        return new PageTempletDefinitions();
    }
    
    public PageTempletDefinitions add(TempletDefinition def){
        this.list.add(def);
        return this;
    }
    
}
