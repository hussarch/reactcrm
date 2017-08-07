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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shhxzq.dc.scs.frm.base.common.type.MethodType;
import com.shhxzq.dc.scs.frm.base.common.type.PageType;
import com.shhxzq.dc.scs.frm.base.rest.model.ConfDataMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.ButtonMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CategoryItemMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CategoryMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CommonSettingMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.DictMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.EnumDictMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.FieldMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.ModaldialogMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.TableMetaData;
import com.shhxzq.dc.scs.frm.cdcache.getter.AdapterConfDataGetter;
import com.shhxzq.dc.scs.frm.core.adapter.JpaApadterService;
import com.shhxzq.dc.scs.frm.core.domain.CategoryInfo;
import com.shhxzq.dc.scs.frm.core.domain.CommonPageDefineInfo;
import com.shhxzq.dc.scs.frm.core.domain.CommonResponse;
import com.shhxzq.dc.scs.frm.core.domain.ModaldialogInfo;
import com.shhxzq.dc.scs.frm.core.domain.TablePageDefineInfo;
import com.shhxzq.dc.scs.frm.core.domain.TablePageInfo;

/**
 * @author XiaoYi Created on 2017-07-26 16:08:50
 */
@RestController
@RequestMapping("/crud")
@CrossOrigin
public class CommonJpaControllor {

    @Autowired
    private JpaApadterService jpaApadterService;

    @Autowired
    @Qualifier("localAdapterConfDataGetter")
    private AdapterConfDataGetter adapterConfDataGetter;

    @RequestMapping(value = "/dialog/get", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<ModaldialogInfo> getPageFieldValueInfo(@RequestParam String serviceId, @RequestParam(required = false) String type,
            @RequestParam String page, @RequestParam(required = false) Integer id) {
        ConfDataMetaData confData = adapterConfDataGetter.get(serviceId);
        ModaldialogInfo content = null;
        if (confData == null) {
            return new CommonResponse<>(false, "Wrong serviceId: " + serviceId);
        } else if (confData.getCrud() == null) {
            return new CommonResponse<>(false, "The crud have not defined: " + serviceId);
        } else if (PageType.add.name().equals(page)) {
            content = new ModaldialogInfo(getCommonPageDefineInfo(confData.getCommon(), confData.getCrud().getAdd()));
        } else if (PageType.update.name().equals(page)) {
            Object value = jpaApadterService.get(adapterConfDataGetter.getEntityClass(confData.getCommon().getClazz()), id);
            if(value == null){
                return new CommonResponse<>(false, "Wrong id: " + id);
            }
            content = new ModaldialogInfo(getCommonPageDefineInfo(confData.getCommon(), confData.getCrud().getUpdate()));
            content.setValues(value);
        } else if (PageType.view.name().equals(page)) {
            Object value = jpaApadterService.get(adapterConfDataGetter.getEntityClass(confData.getCommon().getClazz()), id);
            if(value == null){
                return new CommonResponse<>(false, "Wrong id: " + id);
            }
            content = new ModaldialogInfo(getCommonPageDefineInfo(confData.getCommon(), confData.getCrud().getView()));
            content.setValues(value);
        } 
        if(content == null){
            return new CommonResponse<>(false, "Wrong type: " + page);
        }else{
            setNameCategory(content.getDefine(), confData.getCommon(), type);
        }
        return new CommonResponse<>(true, content);
    }

    private CommonPageDefineInfo getCommonPageDefineInfo(CommonSettingMetaData common, ModaldialogMetaData data) {
        CommonPageDefineInfo define = new CommonPageDefineInfo();
        define.setFields(getFields(common.getFields(), data.getFields(), data.getHiddenfields()));
        define.setDicts(getDicts(common.getDicts()));
        define.setButtons(getButtons(common.getButtons(), data.getButtons()));
        return define;
    }

    private Map<String, List<EnumDictMetaData>> getDicts(Map<String, DictMetaData> dicts) {
        if (dicts == null || dicts.isEmpty()) {
            return null;
        }
        Map<String, List<EnumDictMetaData>> dmp = new LinkedHashMap<>();
        DictMetaData dmd;
        List<EnumDictMetaData> list;
        for (String key : dicts.keySet()) {
            dmd = dicts.get(key);
            if (dmd.getEnumDict() != null) {
                list = dmd.getEnumDict();
            } else if (StringUtils.isNoneBlank(dmd.getEntityDictHql())) {
                list = getEnumDictMetaData(dmd.getEntityDictHql());
            } else {
                throw new RuntimeException("The dict define error");
            }
            if(list != null && list.size() > 0){
                dmp.put(key, list);
            }
        }
        if(dmp.isEmpty()){
            return null;
        }
        return dmp;
    }

    private List<EnumDictMetaData> getEnumDictMetaData(String sql) {
        return this.jpaApadterService.queryDictList(sql);
    }

    private List<ButtonMetaData> getButtons(Map<MethodType, ButtonMetaData> buttonMap, List<String> buttonNames) {
        List<ButtonMetaData> list = new ArrayList<>();
        MethodType button = null;
        for (String bn : buttonNames) {
            for(MethodType item : MethodType.values()){
                if(item.name().equals(bn)){
                    button = item;
                    break;
                }
            }
            ButtonMetaData bt = buttonMap.get(button);
            if(bt != null){
                list.add(bt);
            }
            
        }
        return list;
    }

    private List<FieldMetaData> getFields(List<FieldMetaData> fields, List<String> showFields, List<String> hiddenfields) {
        if(fields == null || fields.size() == 0){
            return null;
        }
        List<FieldMetaData> list = new ArrayList<>();
        FieldMetaData fieldMetaData;
        for (FieldMetaData fd : fields) {
            if (showFields.contains(fd.getName()) || (hiddenfields != null && hiddenfields.contains(fd.getName()))) {
                fieldMetaData = fd.clone();
                if (hiddenfields != null && hiddenfields.contains(fd.getName())) {
                    fieldMetaData.setHidden(true);
                }
                fieldMetaData.setClazz(null);
                list.add(fieldMetaData);
            }
        }
        return list;
    }

    @RequestMapping(value = "/dialog/post", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<Void> postFieldData(@RequestParam String serviceId, @RequestParam String page,  @RequestBody String data) {
        ConfDataMetaData confData = adapterConfDataGetter.get(serviceId);
        if (confData == null) {
            return new CommonResponse<>(false, "Wrong serviceId: " + serviceId);
        } else if (PageType.add.name().equals(page)) {
            jpaApadterService.add(adapterConfDataGetter.getEntityClass(confData.getCommon().getClazz()), data);
        } else if (PageType.update.name().equals(page)) {
            jpaApadterService.update(adapterConfDataGetter.getEntityClass(confData.getCommon().getClazz()), data);
        } else if (PageType.delete.name().equals(page)) {
            jpaApadterService.delete(adapterConfDataGetter.getEntityClass(confData.getCommon().getClazz()), Integer.valueOf(data));
        } else {
            return new CommonResponse<Void>(false, "Wrong type: " + page);
        }
        return new CommonResponse<>(true, "operation successful");
    }

    @RequestMapping(value = "/table")
    @ResponseBody
    public CommonResponse<TablePageInfo> getTable(@RequestParam String serviceId, @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer size, @RequestBody(required = false) Map<String, String> params) {
        ConfDataMetaData confData = adapterConfDataGetter.get(serviceId);
        if (confData == null) {
            return new CommonResponse<>(false, "Wrong serviceId: " + serviceId);
        } else if (confData.getCrud() == null) {
            return new CommonResponse<>(false, "The crud have not defined: " + serviceId);
        }
        CommonResponse<TablePageInfo> response = new CommonResponse<>();
        TablePageInfo table = new TablePageInfo();
        table.setDefine(getTablePageDefineInfo(confData.getCommon(), confData.getCrud().getTable()));
        setNameCategory(table.getDefine(), confData.getCommon(), type);
        if(pageNo == null){
            pageNo = 1;
        }
        if(size == null){
            size = 20;
        }
        Pageable pageable = new PageRequest(pageNo - 1, size);
        Page<?> page = jpaApadterService.getPage(adapterConfDataGetter.getEntityClass(confData.getCommon().getClazz()), pageable, params,
                confData.getCommon().getFields());
        table.setPage(page);
        response.setContent(true, table);
        return response;
    }
    
    private void setNameCategory(CommonPageDefineInfo define, CommonSettingMetaData commonSetting, String type){
        define.setName(commonSetting.getName());
        CategoryMetaData category = commonSetting.getCategory();
        if(StringUtils.isNoneBlank(type) && category != null && category.getGroup() != null){
            CategoryInfo categoryInfo = new CategoryInfo();
            categoryInfo.setFieldName(category.getFieldName());
            for(CategoryItemMetaData item : category.getGroup()){
                if(type.equals(item.getFieldValue())){
                    categoryInfo.setFieldValue(item.getFieldValue());
                    define.setName(item.getName());
                    define.setCategory(categoryInfo);
                    break;
                }
            }
        }
    }

    private TablePageDefineInfo getTablePageDefineInfo(CommonSettingMetaData common, TableMetaData data) {
        TablePageDefineInfo define = new TablePageDefineInfo();
        define.setSearch(getFields(common.getFields(), data.getSearchFields(), null));
        define.setFields(getFields(common.getFields(), data.getFields(), data.getHiddenfields()));
        define.setDicts(getDicts(common.getDicts()));
        define.setButtons(getButtons(common.getButtons(), data.getButtons()));
        return define;
    }

}
