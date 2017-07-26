package com.shhxzq.dc.scs.frame.core.ctlr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shhxzq.dc.scs.frame.core.adapter.JpaApadterService;
import com.shhxzq.dc.scs.frame.core.domain.CommonResponse;
import com.shhxzq.dc.scs.frame.core.domain.ModaldialogInfo;
import com.shhxzq.dc.scs.frm.base.page.model.ConfDataMetaData;
import com.shhxzq.dc.scs.frm.base.page.type.PageType;
import com.shhxzq.dc.scs.frm.cdcache.getter.AdapterConfDataGetter;

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
    
    @RequestMapping("/fields")
    @ResponseBody
    public CommonResponse<ModaldialogInfo> getPageFieldValueInfo(@RequestParam String serviceId, 
            @RequestParam String page,
            @RequestParam(required = false) Integer id) {
        CommonResponse<ModaldialogInfo> response = new CommonResponse<>();
        ConfDataMetaData confData = adapterConfDataGetter.get(serviceId);
        ModaldialogInfo content = null;
        if(confData == null){
            response.setSuccess(false);
            response.setMsg("Wrong serviceId: " + serviceId);
            return response;
        }else if(PageType.add.name().equals(page)){
            content = new ModaldialogInfo(confData.getAdd()); 
        }else if(PageType.update.name().equals(page)){
            content = new ModaldialogInfo(confData.getUpdate()); 
        }else if(PageType.view.name().equals(page)){
            content = new ModaldialogInfo(confData.getView()); 
        }else{
            response.setSuccess(false);
            response.setMsg("Wrong type: " + page);
            return response;
        }
        response.setSuccess(true);
        response.setContent(content);
        return response;
    }

}
