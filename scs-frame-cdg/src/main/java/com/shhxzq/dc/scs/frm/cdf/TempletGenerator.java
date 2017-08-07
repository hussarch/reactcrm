package com.shhxzq.dc.scs.frm.cdf;

import java.io.File;
import java.util.Map;

import com.shhxzq.dc.scs.frm.base.common.type.ConfFileType;
import com.shhxzq.dc.scs.frm.base.common.type.MethodType;
import com.shhxzq.dc.scs.frm.base.common.utils.CommonFileUtils;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.ApiMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CommonSettingMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CrudMetaData;

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
        CommonSettingMetaData confMetaData = entity2Meta.getConfMetaData();
        Map<String, ApiMetaData> apiMetaData = entity2Meta.getApiMetaData();
        CrudMetaData crudMetaData = entity2Meta.getCrudMetaData();
        if(crudMetaData == null){
            confMetaData.setMainPage(null);
            confMetaData.setRenderedJs(null);
            confMetaData.setButtons(null);
        }else{
            if(crudMetaData.getAdd() == null && crudMetaData.getUpdate() == null){
                confMetaData.getButtons().remove(MethodType.submit);
            }
            if(crudMetaData.getTable() == null || crudMetaData.getView() == null){
                confMetaData.getButtons().remove(MethodType.add);
                confMetaData.getButtons().remove(MethodType.update);
                confMetaData.getButtons().remove(MethodType.search);
                confMetaData.getButtons().remove(MethodType.delete);
                confMetaData.getButtons().remove(MethodType.view);
            }
        }
        CommonFileUtils.writeJson2File(confMetaData, path, ConfFileType.common.getFileName(), overWrite);
        CommonFileUtils.writeJson2File(crudMetaData, path, ConfFileType.crud.getFileName(), overWrite);
        CommonFileUtils.writeJson2File(apiMetaData, path, ConfFileType.api.getFileName(), overWrite);
    }
    
    
}
