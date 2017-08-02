package com.shhxzq.dc.scs.frm.core.ctlr;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shhxzq.dc.scs.frm.base.common.type.MethodType;
import com.shhxzq.dc.scs.frm.base.common.type.PageType;
import com.shhxzq.dc.scs.frm.base.rest.model.ConfDataMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.ButtonMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CommonSettingMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.DictMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.EnumDictMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.FieldMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.ModaldialogMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.TableMetaData;
import com.shhxzq.dc.scs.frm.cdcache.getter.AdapterConfDataGetter;
import com.shhxzq.dc.scs.frm.core.adapter.JpaApadterService;
import com.shhxzq.dc.scs.frm.core.domain.CommonPageDefineInfo;
import com.shhxzq.dc.scs.frm.core.domain.CommonResponse;
import com.shhxzq.dc.scs.frm.core.domain.ModaldialogInfo;
import com.shhxzq.dc.scs.frm.core.domain.TablePageDefineInfo;
import com.shhxzq.dc.scs.frm.core.domain.TablePageInfo;

/**
 * @author XiaoYi Created on 2017-07-26 16:08:50
 */
@RestController
@RequestMapping("/sevice")
@CrossOrigin
public class CommonJpaControllor {

    @Autowired
    private JpaApadterService jpaApadterService;

    @Autowired
    @Qualifier("localAdapterConfDataGetter")
    private AdapterConfDataGetter adapterConfDataGetter;
    
    @RequestMapping(value = "/dialog/get", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<ModaldialogInfo> getPageFieldValueInfo(@RequestParam String serviceId, 
            @RequestParam String page,
            @RequestParam(required = false) Integer id) {
        ConfDataMetaData confData = adapterConfDataGetter.get(serviceId);
        ModaldialogInfo content = null;
        if(confData == null){
            return new CommonResponse<>(false, "Wrong serviceId: " + serviceId);
        }else if(confData.getCrud() == null){
            return new CommonResponse<>(false, "The crud have not defined: " + serviceId);
        }else if(PageType.add.name().equals(page)){
            content = new ModaldialogInfo(getCommonPageDefineInfo(confData.getCommon(), confData.getCrud().getAdd())); 
        }else if(PageType.update.name().equals(page)){
            content = new ModaldialogInfo(getCommonPageDefineInfo(confData.getCommon(), confData.getCrud().getUpdate()));
            content.setValues(jpaApadterService.get(adapterConfDataGetter.getEntityClass(confData.getCommon().getClazz()), id));
        }else if(PageType.view.name().equals(page)){
            content = new ModaldialogInfo(getCommonPageDefineInfo(confData.getCommon(), confData.getCrud().getView())); 
            content.setValues(jpaApadterService.get(adapterConfDataGetter.getEntityClass(confData.getCommon().getClazz()), id));
        }else{
            return new CommonResponse<>(false, "Wrong type: " + page);
        }
        return new CommonResponse<>(true, content);
    }
    
    private CommonPageDefineInfo getCommonPageDefineInfo(CommonSettingMetaData common, ModaldialogMetaData data){
        CommonPageDefineInfo define = new CommonPageDefineInfo();
        define.setFields(getFields(define.getFields(), data.getFields(), data.getHiddenfields()));
        define.setDicts(getDicts(common.getDicts()));
        define.setButtons(getButtons(common.getButtons(), data.getButtons()));
        return define;
    }
    
    private Map<String, List<EnumDictMetaData>> getDicts(Map<String, DictMetaData> dicts) {
        if(dicts == null || dicts.isEmpty()){
            return null;
        }
        Map<String, List<EnumDictMetaData>> dmp = new LinkedHashMap<>();
        DictMetaData dmd;
        List<EnumDictMetaData> list;
        for(String key : dicts.keySet()){
            dmd = dicts.get(key);
            if(dmd.getEnumDict() != null){
                list = dmd.getEnumDict();
            }else if(StringUtils.isNoneBlank(dmd.getEntityDictHql())){
                list = getEnumDictMetaData(dmd.getEntityDictHql());
            }else{
                throw new RuntimeException("The dict dine error");
            }
            dmp.put(key, list);
        }
        return null;
    }

    private List<EnumDictMetaData> getEnumDictMetaData(String entityDictHql) {
        // TODO Auto-generated method stub
        return null;
    }

    private List<ButtonMetaData> getButtons(Map<MethodType, ButtonMetaData> buttonMap, List<String> buttonNames) {
        List<ButtonMetaData> list = new ArrayList<>();
        for(String bn : buttonNames){
            list.add(buttonMap.get(bn));
        }
        return list;
    }

    private List<FieldMetaData> getFields(List<FieldMetaData> fields, List<String> showFields, List<String> hiddenfields) {
        List<FieldMetaData> list = new ArrayList<>();
        FieldMetaData fieldMetaData;
        for(FieldMetaData fd : fields){
            if(showFields.contains(fd.getName())){
                fieldMetaData = fd.clone();
                if(hiddenfields != null && hiddenfields.contains(fd.getName())){
                    fieldMetaData.setHidden(true);
                }
                list.add(fieldMetaData);
            }
        }
        return list;
    }

    @RequestMapping(value = "/dialog/post", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<Void> postFieldData(@RequestParam String serviceId, @RequestParam String page, @RequestParam String data){
        ConfDataMetaData confData = adapterConfDataGetter.get(serviceId);
        if(confData == null){
            return new CommonResponse<>(false, "Wrong serviceId: " + serviceId);
        }else if(PageType.add.name().equals(page)){
            jpaApadterService.add(adapterConfDataGetter.getEntityClass(confData.getCommon().getClazz()), data); 
        }else if(PageType.update.name().equals(page)){
            jpaApadterService.update(adapterConfDataGetter.getEntityClass(confData.getCommon().getClazz()), data); 
        }else if(PageType.delete.name().equals(page)){
            jpaApadterService.delete(adapterConfDataGetter.getEntityClass(confData.getCommon().getClazz()), Integer.valueOf(data));
        }else{
            return new CommonResponse<Void>(false, "Wrong type: " + page);
        }
        return new CommonResponse<>(true, "operation successful");
    }
    
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<TablePageInfo> getPage(String serviceId, int pageNo, int size, Map<String, String> params){
        ConfDataMetaData confData = adapterConfDataGetter.get(serviceId);
        if(confData == null){
            return new CommonResponse<>(false, "Wrong serviceId: " + serviceId);
        }else if(confData.getCrud() == null){
            return new CommonResponse<>(false, "The crud have not defined: " + serviceId);
        }
        CommonResponse<TablePageInfo> response = new CommonResponse<>();
        TablePageInfo table = new TablePageInfo();
        table.setDefine(getTablePageDefineInfo(confData.getCommon(), confData.getCrud().getTable()));
        Pageable pageable = new PageRequest(pageNo - 1, size);
        Page<?> page = jpaApadterService.getPage(adapterConfDataGetter.getEntityClass(confData.getCommon().getClazz()), pageable, params);
        table.setPage(page);
        response.setContent(true, table);
        return response;
    }
    
    private TablePageDefineInfo getTablePageDefineInfo(CommonSettingMetaData common, TableMetaData data){
        TablePageDefineInfo define = new TablePageDefineInfo();
        define.setSearch(getFields(define.getFields(), data.getSearchFields(), null));
        define.setFields(getFields(define.getFields(), data.getFields(), data.getHiddenfields()));
        define.setDicts(getDicts(common.getDicts()));
        define.setButtons(getButtons(common.getButtons(), data.getButtons()));
        return define;
    }

}
