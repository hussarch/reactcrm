package com.shhxzq.dc.scs.frm.cdf;

import java.io.File;

import com.shhxzq.dc.scs.frm.base.common.type.ConfFileType;
import com.shhxzq.dc.scs.frm.base.common.utils.CommonFileUtils;

/**
 * @author XiaoYi
 * Created on 2017-07-15 17:55:48
 */
public class TempletGenerator {
    
    public static void writeTemplet(Class<?> clazz, String methodName, String path, boolean overWrite){
        MetaGenerater entity2Meta = new MetaGenerater(clazz, methodName);
        path = path + entity2Meta.getSubPath();
        File fp = new File(path);
        if (!fp.exists()) {
            fp.mkdirs();
        }else {
            if(!overWrite){
                System.err.println(String.format("The conf folder[%s] exist already, would not create.", path));
                return;
            }
        }
        CommonFileUtils.writeJson2File(entity2Meta.getConfMetaData(), path, ConfFileType.common.getFileName(), overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getCrudMetaData(), path, ConfFileType.crud.getFileName(), overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getApiMetaData(), path, ConfFileType.api.getFileName(), overWrite);
    }
    
    
}
