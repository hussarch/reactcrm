package com.shhxzq.dc.scs.frm.cdf;

import java.io.File;

import com.shhxzq.dc.scs.frm.base.common.utils.CommonFileUtils;
import com.shhxzq.dc.scs.frm.base.page.type.ConfFileType;

/**
 * @author XiaoYi
 * Created on 2017-07-15 17:55:48
 */
public class TempletGenerator {
    
    
    public static void writeTemplet(Class<?> clazz, String path, boolean overWrite){
        Entity2Meta entity2Meta = new Entity2Meta(clazz);
        path = path + entity2Meta.getSubPath();
        File fp = new File(path);
        if (!fp.exists()) {
            fp.mkdirs();
        }else {
            if(!overWrite){
                throw new RuntimeException(String.format("The conf folder[%s] exist already, would not create.", path));
            }
        }
        CommonFileUtils.writeJson2File(entity2Meta.getConfMetaData(), path, ConfFileType.global.getFileName(), overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getTableMetaData(), path, ConfFileType.table.getFileName(), overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getAddMetaData(), path, ConfFileType.add.getFileName(), overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getUpdateMetaData(), path, ConfFileType.update.getFileName(), overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getViewMetaData(), path, ConfFileType.view.getFileName(), overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getApiMetaData(), path, ConfFileType.api.getFileName(), overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getDicts(), path, ConfFileType.dict.getFileName(), overWrite);
    }
    
    
}
