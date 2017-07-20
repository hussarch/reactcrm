package com.shhxzq.dc.scs.frm.cdf;

import com.shhxzq.dc.scs.frm.base.common.utils.CommonFileUtils;

/**
 * @author XiaoYi
 * Created on 2017-07-15 17:55:48
 */
public class TempletGenerator {
    
    
    public static void writeTemplet(Class<?> clazz, String path, boolean overWrite){
        Entity2Meta entity2Meta = new Entity2Meta(clazz);
        CommonFileUtils.writeJson2File(entity2Meta.getConfMetaData(), path, "conf.json", overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getTableMetaData(), path, "table.json", overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getAddMetaData(), path, "add.json", overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getUpdateMetaData(), path, "update.json", overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getViewMetaData(), path, "view.json", overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getApiMetaData(), path, "api.json", overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getDicts(), path, "dict.json", overWrite);
    }
    
    
}
