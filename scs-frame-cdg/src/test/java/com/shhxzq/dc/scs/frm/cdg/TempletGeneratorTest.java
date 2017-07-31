package com.shhxzq.dc.scs.frm.cdg;

import com.shhxzq.dc.scs.frm.base.common.type.ApiType;
import com.shhxzq.dc.scs.frm.cdf.TempletGenerator;
import com.shhxzq.dc.scs.frm.cdf.templete.anotations.ApiDesc;
import com.shhxzq.dc.scs.frm.cdf.templete.anotations.PageDesc;
import com.shhxzq.dc.scs.frm.cdg.entity.DocumentEntity;

/**
 * @author XiaoYi Created on 2017-07-16 13:52:24
 */
public class TempletGeneratorTest {

    @PageDesc(path = { "website", "edu", "doc" }, 
            serviceId = "document", 
            title = "文档", 
            category = "type")
    @ApiDesc(serviceId = "list", name = "常见问答列表", params = { "type" }, type = ApiType.list)
    @ApiDesc(serviceId = "detail", name = "常见问答详情", params = { "id" }, type = ApiType.detail)
    public void defineDocumentEntity() {

    }

    public static void main(String[] args) {
        String path = "/Users/sailor/git/adapter-data-sys/conf-data/";
        TempletGenerator.writeTemplet(DocumentEntity.class, path, true);
    }

}
