package com.shhxzq.dc.scs.frm.core.ctlr;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.shhxzq.dc.scs.frm.base.rest.model.ConfDataMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.ApiMetaData;
import com.shhxzq.dc.scs.frm.cdcache.getter.AdapterConfDataGetter;
import com.shhxzq.dc.scs.frm.core.adapter.JpaApadterService;
import com.shhxzq.dc.scs.frm.core.domain.CommonResponse;

/**
 * @author XiaoYi Created on 2017-07-27 13:52:52
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommonApiController {

    @Autowired
    private JpaApadterService jpaApadterService;

    @Autowired
    @Qualifier("localAdapterConfDataGetter")
    private AdapterConfDataGetter adapterConfDataGetter;

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<?> getDetail(@RequestParam String serviceId, @RequestParam String name, @RequestParam int id) {
        ConfDataMetaData confData = adapterConfDataGetter.get(serviceId);
        if (confData == null) {
            return new CommonResponse<Void>(false, "Wrong serviceId: " + serviceId);
        }
        Map<String, ApiMetaData> apis = confData.getApis();
        if (apis == null || !apis.containsKey(name)) {
            return new CommonResponse<Void>(false, "The api have not been defined");
        }
        Object value = jpaApadterService.get(adapterConfDataGetter.getEntityClass(confData.getGlobal().getClazz()), id);
        if (value == null) {
            return new CommonResponse<Void>(false, "The id does not exist");
        }
        Gson gson = new Gson();
        ApiMetaData apiDefine = apis.get(name);
        return new CommonResponse<JsonObject>(true, getFilteredValue(gson, apiDefine, value));
    }

    private JsonObject getFilteredValue(Gson gson, ApiMetaData apiDefine, Object value) {
        JsonObject jsonTree = (JsonObject) gson.toJsonTree(value);
        Iterator<String> it = jsonTree.keySet().iterator();
        String key;
        while (it.hasNext()) {
            key = it.next();
            if (!isShow(key, apiDefine.getFields())) {
                it.remove();
            }
        }
        return jsonTree;
    }

    private boolean isShow(String key, List<String> fields) {
        if (fields == null || fields.isEmpty()) {
            return false;
        }
        for (String field : fields) {
            if (field.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public <S> CommonResponse<Page<?>> getList(@RequestParam String serviceId, @RequestParam String name, @RequestParam int pageNo,
            @RequestParam int size, @RequestParam(required = false) Map<String, String> params) {
        ConfDataMetaData confData = adapterConfDataGetter.get(serviceId);
        if (confData == null) {
            return new CommonResponse<>(false, "Wrong serviceId: " + serviceId);
        }
        Pageable pageable = new PageRequest(pageNo - 1, size);
        Page<?> page = jpaApadterService.getPage(adapterConfDataGetter.getEntityClass(confData.getGlobal().getClazz()), pageable, params);
        List<?> content = page.getContent();
        if (content == null || content.isEmpty()) {
            return new CommonResponse<>(true, "no result");
        }
        Gson gson = new Gson();
        ApiMetaData apiDefine = confData.getApis().get(name);
        page.map(new Converter<Object, JsonObject>() {

            @Override
            public JsonObject convert(Object source) {
                return getFilteredValue(gson, apiDefine, source);
            }
            
        });
        return new CommonResponse<>(true, page);
    }

}
