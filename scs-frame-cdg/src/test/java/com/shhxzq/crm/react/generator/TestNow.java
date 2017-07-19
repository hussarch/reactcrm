package com.shhxzq.crm.react.generator;

import java.util.ArrayList;
import java.util.List;

import com.shhxzq.crm.react.base.page.model.DictData;
import com.shhxzq.crm.react.generator.entity.SourceFrom;

/**
 * @author XiaoYi
 * Created on 2017-07-19 15:17:13
 */
public class TestNow {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(DictData.class.isAssignableFrom(SourceFrom.class));
    }

}
