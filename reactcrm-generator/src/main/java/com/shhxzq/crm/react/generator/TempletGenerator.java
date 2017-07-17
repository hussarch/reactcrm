package com.shhxzq.crm.react.generator;

import com.shhxzq.crm.react.base.common.utils.CommonFileUtils;

/**
 * @author XiaoYi
 * Created on 2017-07-15 17:55:48
 */
public class TempletGenerator {
    
    
    public static void writeTemplet(Class<?> clazz, String path){
        Entity2Meta entity2Meta = new Entity2Meta(clazz);
        CommonFileUtils.writeJson2File(entity2Meta.getConfMetaData(), path, "conf.json");
        CommonFileUtils.writeJson2File(entity2Meta.getTableMetaData(), path, "table.json");
        CommonFileUtils.writeJson2File(entity2Meta.getAddMetaData(), path, "add.json");
        CommonFileUtils.writeJson2File(entity2Meta.getUpdateMetaData(), path, "update.json");
        CommonFileUtils.writeJson2File(entity2Meta.getViewMetaData(), path, "view.json");
        CommonFileUtils.writeJson2File(entity2Meta.getApiMetaData(), path, "api.json");
    }
    
    
}
