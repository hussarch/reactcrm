package com.shhxzq.dc.scs.frm.core.ctlr;

import java.util.Map;

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

import com.shhxzq.dc.scs.frm.base.common.type.PageType;
import com.shhxzq.dc.scs.frm.base.rest.model.ConfDataMetaData;
import com.shhxzq.dc.scs.frm.cdcache.getter.AdapterConfDataGetter;
import com.shhxzq.dc.scs.frm.core.adapter.JpaApadterService;
import com.shhxzq.dc.scs.frm.core.domain.CommonResponse;
import com.shhxzq.dc.scs.frm.core.domain.ModaldialogInfo;
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
            content = new ModaldialogInfo(confData.getCrud().getAdd()); 
        }else if(PageType.update.name().equals(page)){
            content = new ModaldialogInfo(confData.getCrud().getUpdate());
            content.setValues(jpaApadterService.get(adapterConfDataGetter.getEntityClass(confData.getGlobal().getClazz()), id));
        }else if(PageType.view.name().equals(page)){
            content = new ModaldialogInfo(confData.getCrud().getView()); 
            content.setValues(jpaApadterService.get(adapterConfDataGetter.getEntityClass(confData.getGlobal().getClazz()), id));
        }else{
            return new CommonResponse<>(false, "Wrong type: " + page);
        }
        return new CommonResponse<>(true, content);
    }
    
    @RequestMapping(value = "/dialog/post", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<Void> postFieldData(@RequestParam String serviceId, @RequestParam String page, @RequestParam String data){
        ConfDataMetaData confData = adapterConfDataGetter.get(serviceId);
        if(confData == null){
            return new CommonResponse<>(false, "Wrong serviceId: " + serviceId);
        }else if(PageType.add.name().equals(page)){
            jpaApadterService.add(adapterConfDataGetter.getEntityClass(confData.getGlobal().getClazz()), data); 
        }else if(PageType.update.name().equals(page)){
            jpaApadterService.update(adapterConfDataGetter.getEntityClass(confData.getGlobal().getClazz()), data); 
        }else if(PageType.delete.name().equals(page)){
            jpaApadterService.delete(adapterConfDataGetter.getEntityClass(confData.getGlobal().getClazz()), Integer.valueOf(data));
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
        table.setDefine(confData.getCrud().getTable());
        Pageable pageable = new PageRequest(pageNo - 1, size);
        Page<?> page = jpaApadterService.getPage(adapterConfDataGetter.getEntityClass(confData.getGlobal().getClazz()), pageable, params);
        table.setPage(page);
        response.setContent(true, table);
        return response;
    }

}
