package com.shhxzq.dc.scs.frm.cdf;

import com.shhxzq.dc.scs.frm.base.common.utils.CommonFileUtils;
import com.shhxzq.dc.scs.frm.base.page.model.DictMetaData;
import com.shhxzq.dc.scs.frm.base.page.model.templet.ApiMetaData;
import com.shhxzq.dc.scs.frm.base.page.model.templet.ConfMetaData;
import com.shhxzq.dc.scs.frm.base.page.model.templet.ModaldialogMetaData;
import com.shhxzq.dc.scs.frm.base.page.model.templet.TableMetaData;

/**
 * @author XiaoYi
 * Created on 2017-07-15 17:55:48
 */
public class TempletGenerator {
    
    
    public static void writeTemplet(Class<?> clazz, String path, boolean overWrite){
        Entity2Meta entity2Meta = new Entity2Meta(clazz);
        path = path + entity2Meta.getSubPath();
        CommonFileUtils.writeJson2File(entity2Meta.getConfMetaData(), path, ConfMetaData.tmpletName, overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getTableMetaData(), path, TableMetaData.tmpletName, overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getAddMetaData(), path, ModaldialogMetaData.tmpletNameAdd, overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getUpdateMetaData(), path, ModaldialogMetaData.tmpletNameUpate, overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getViewMetaData(), path, ModaldialogMetaData.tmpletNameView, overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getApiMetaData(), path, ApiMetaData.tmpletName, overWrite);
        CommonFileUtils.writeJson2File(entity2Meta.getDicts(), path, DictMetaData.tmpletName, overWrite);
    }
    
    
}
