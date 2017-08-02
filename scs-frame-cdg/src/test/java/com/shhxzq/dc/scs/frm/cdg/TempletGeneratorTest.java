package com.shhxzq.dc.scs.frm.cdg;

import com.shhxzq.dc.scs.frm.base.common.type.PageType;
import com.shhxzq.dc.scs.frm.cdf.TempletGenerator;
import com.shhxzq.dc.scs.frm.cdf.templete.anotations.ApiDesc;
import com.shhxzq.dc.scs.frm.cdf.templete.anotations.PageDesc;
import com.shhxzq.dc.scs.frm.cdf.templete.anotations.PageFieldDesc;
import com.shhxzq.dc.scs.frm.cdg.entity.DocumentEntity;

/**
 * @author XiaoYi Created on 2017-07-16 13:52:24
 */
public class TempletGeneratorTest {

    public TempletGeneratorTest(String name, String[] array, Class<?> clazz) {

    }

    @PageDesc(path = { "website", "edu", "doc_new" }, name = "文档", category = "type", entityClass = DocumentEntity.class)
    @PageFieldDesc(type = PageType.search, showFileds = {"title", "sourceFrom"})
    @PageFieldDesc(type = PageType.table, notShowFileds = {"content", "createdAt", "updatedAt"})
    @PageFieldDesc(type = PageType.add, notShowFileds = {"id", "createdAt", "updatedAt"})
    @PageFieldDesc(type = PageType.update, notShowFileds = {"createdAt", "updatedAt"}, hiddenFileds = "id")
    @PageFieldDesc(type = PageType.view, notShowFileds = {"createdAt", "updatedAt"}, hiddenFileds = "id")
    @ApiDesc(serviceId = "list", name = "常见问答列表", params = { "type" }, 
        notShowFileds = { "createdAt", "updatedAt" })
    @ApiDesc(serviceId = "detail", name = "常见问答详情", params = { "id" }, 
        notShowFileds = { "createdAt", "updatedAt" })
    public void defineDocumentEntity() {
    }

    public static void main(String[] args) {
        String path = "/Users/sailor/git/adapter-data-sys/adapter-data-sys-conf-data/";
        TempletGenerator.writeTemplet(TempletGeneratorTest.class, "defineDocumentEntity", path, false);

    }

}
