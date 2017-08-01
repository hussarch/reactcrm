package com.shhxzq.dc.scs.frm.cdf;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Table;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.shhxzq.dc.scs.frm.base.common.anotations.Comment;
import com.shhxzq.dc.scs.frm.base.common.type.ApiType;
import com.shhxzq.dc.scs.frm.base.common.type.MethodType;
import com.shhxzq.dc.scs.frm.base.common.type.PageType;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.ApiMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.ButtonMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CategoryData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CategoryItemMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CategoryMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.CommonSettingMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.DictData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.DictMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.FieldMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.ModaldialogMetaData;
import com.shhxzq.dc.scs.frm.base.rest.model.templet.TableMetaData;
import com.shhxzq.dc.scs.frm.cdf.templete.anotations.ApiDesc;
import com.shhxzq.dc.scs.frm.cdf.templete.anotations.CategoryDesc;
import com.shhxzq.dc.scs.frm.cdf.templete.anotations.DictDesc;
import com.shhxzq.dc.scs.frm.cdf.templete.anotations.FieldsDesc;
import com.shhxzq.dc.scs.frm.cdf.templete.anotations.PageDesc;
import com.shhxzq.dc.scs.frm.cdf.templete.anotations.PageFieldDesc;

/**
 * @Entity2MetaGenerator.java
 * @author XiaoYi(hussarch@126.com) Created on 2017年6月22日, ©2017 some rights
 *         reserved
 */
public class MetaGenerater {

    private String name;
    private Class<?> entityClass;
    private PageDesc pageDesc;
    private PageFieldDesc[] pageFieldDescs;
    private ApiDesc[] apiDescs;
    private CategoryDesc categoryDesc;

    private Map<MethodType, ButtonMetaData> buttonMap = new HashMap<>();
    private List<FieldMetaData> entityClassFields;
    private CategoryMetaData categoryMetaData;
    
    public MetaGenerater(Class<?> clazz, String metodName) {
        init(clazz, metodName);
        initButtonMetaData(MethodType.add, "确定");
        initButtonMetaData(MethodType.update, "确定");
        initButtonMetaData(MethodType.delete, "删除");
        initButtonMetaData(MethodType.view, "查看");
        initButtonMetaData(MethodType.search, "查询");
        initButtonMetaData(MethodType.close, "取消");
    }

    private void init(Class<?> clazz, String metodName) {
        Method mothod = getMethod(clazz, metodName);
        if (mothod == null) {
            throwRuntimeException("Can not find the method");
        }
        this.pageDesc = mothod.getAnnotation(PageDesc.class);
        if (this.pageDesc == null) {
            throwRuntimeException("Can not find the annotation PageDesc");
        }
        this.name = this.pageDesc.name();
        this.entityClass = pageDesc.entityClass();
        this.entityClassFields = getAllFields();
        this.pageFieldDescs = mothod.getAnnotationsByType(PageFieldDesc.class);
        if (this.pageFieldDescs == null) {
            System.out.println("Warning: the annotation PageFieldDesc have not been set");
        }
        this.apiDescs = mothod.getAnnotationsByType(ApiDesc.class);
        if (this.apiDescs == null || this.apiDescs.length == 0) {
            System.out.println("Info: the annotation ApiDesc have not been set, there is not api defination");
        }
        this.categoryDesc = mothod.getAnnotation(CategoryDesc.class);
    }

    private Method getMethod(Class<?> clazz, String metodName) {
        try {
            return clazz.getMethod(metodName);
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            System.err.println("no such method");
        }
        return null;
    }

    private void initButtonMetaData(MethodType type, String name) {
        buttonMap.put(type, new ButtonMetaData(name, type.name(), type));
    }

    public CommonSettingMetaData getConfMetaData() {
        CommonSettingMetaData data = new CommonSettingMetaData();
        data.setName(name);
        data.setClazz(this.entityClass.getName());
        data.setMainPage(pageDesc.mainPage().toString());
        data.setRenderedJs(pageDesc.renderedJs());
        data.setCategory(getCategoryMetaData());
        data.setFields(entityClassFields);
        return data;
    }

    private CategoryMetaData getCategoryMetaData() {
        this.categoryMetaData = getPageDescCategory();
        if(this.categoryMetaData == null){
            this.categoryMetaData = getCategoryDescCategory();
        }
        return categoryMetaData;
    }

    private CategoryMetaData getPageDescCategory() {
        String categoryType = pageDesc.category();
        if(StringUtils.isBlank(categoryType)){
            return null;
        }
        CategoryMetaData categoryMetaData = new CategoryMetaData();
        categoryMetaData.setFieldName(categoryType);
        Field categoryField = this.getField(categoryType);
        if(categoryField == null){
            throwRuntimeException("The " + categoryType + " does not exist in the enity field, " + this.entityClass.getName());
        }
        List<CategoryItemMetaData> group = new ArrayList<>();
        if(CategoryData.class.isAssignableFrom(categoryField.getType())){
            Enum<?>[] enums = (Enum[]) categoryField.getType().getEnumConstants();
            for(Enum<?> item : enums){
                CategoryData data = (CategoryData)item;
                group.add(new CategoryItemMetaData(data.getFieldValue(), data.getName()));
            }
        }else{
            throwRuntimeException("The " + categoryType + " should be an ENUM type and implements the , " + CategoryData.class.getName());
        }
        categoryMetaData.setGroup(group);
        return categoryMetaData;
    }

    private void throwRuntimeException(String msg) {
        System.err.println(msg);
        throw new RuntimeException(msg);
    }
    
    private CategoryMetaData getCategoryDescCategory() {
        if(categoryDesc == null){
            return null;
        }
        Field categoryField = this.getField(categoryDesc.fieldName());
        if(categoryField == null){
            throwRuntimeException("The " + categoryDesc.fieldName() + " does not exist in the enity field, " + this.entityClass.getName());
        }
        CategoryMetaData categoryMetaData = new CategoryMetaData();
        categoryMetaData.setFieldName(categoryDesc.fieldName());
        String[] group = categoryDesc.group();
        if(group == null || group.length == 0){
            List<CategoryItemMetaData> groupList = new ArrayList<>();
            for(String item : group){
                groupList.add(getCategoryItemMetaData(item));
            }
            categoryMetaData.setGroup(groupList);
            return categoryMetaData;
        }else{
            throwRuntimeException("The group in CategoryDesc should be set");
            return null;
        }
    }
    
    private CategoryItemMetaData getCategoryItemMetaData(String item) {
        String[] array = item.split(":");
        if(array.length != 2){
            throwRuntimeException("The group item should be like \"type:name\"");
        }
        return new CategoryItemMetaData(array[0], array[1]);
    }

    private Field getField(String fn){
        try {
            return this.entityClass.getField(fn);
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private List<FieldMetaData> getAllFields() {
        List<FieldMetaData> list = new ArrayList<>();
        Field[] fields = this.entityClass.getDeclaredFields();
        FieldMetaData data = null;
        for (Field field : fields) {
            data = this.getFieldMetaData(field);
            if (data != null) {
                list.add(data);
            }
        }
        return list;
    }

    /**
     * "label": "标题", 
     * "name": "title", 
     * "size": 100, 
     * "type": "string"
     */
    private FieldMetaData getFieldMetaData(Field field) {
        FieldMetaData data = new FieldMetaData();
        Comment comment = field.getAnnotation(Comment.class);
        if (comment != null) {
            data.setLabel(comment.value());
        }
        data.setName(field.getName());
        data.setType(getFieldType(field));
        Column column = field.getAnnotation(Column.class);
        if (column != null && data.getType().equals("string")) {
            data.setLength(column.length());
        }
        return data;
    }

    private String getFieldType(Field field) {
        if (field.getClass().isPrimitive()) {
            return field.getClass().getName();
        } else if (Date.class.equals(field.getClass())) {
            return "datetime";
        } else if (String.class.equals(field.getClass())) {
            return "string";
        } else if (Integer.class.equals(field.getClass())) {
            return "int";
        } else if (Long.class.equals(field.getClass())) {
            return "long";
        } else if (Double.class.equals(field.getClass())) {
            return "double";
        } else if (Float.class.equals(field.getClass())) {
            return "float";
        }else if(DictData.class.isAssignableFrom(field.getType())){
            return "select";
        }else if (List.class.isAssignableFrom(field.getType()) || Set.class.isAssignableFrom(field.getType())) {
            ParameterizedType pt = (ParameterizedType) field.getGenericType() ; 
            Class<?> clazz = (Class<?>) pt.getActualTypeArguments()[0]; 
            if(DictData.class.isAssignableFrom(clazz)){
                return "multi_select";
            }
            if(clazz.getAnnotation(Table.class) != null){
                return "multi_select";
            }
        }
        return field.getType().getName();
    }

    public String getSubPath() {
        String[] path = this.pageDesc.path();
        if (path == null || path.length == 0) {
            throw new RuntimeException("The page path should be assigned");
        }
        StringBuilder builder = new StringBuilder();
        for (String item : path) {
            builder.append(item).append("/");
        }
        return builder.toString();
    }

    public TableMetaData getTableMetaData() {
        TableMetaData data = new TableMetaData();
        data.setSearchFields(getPageFeildMetaDatas(PageType.search));
        data.setButtons(getButtons(MethodType.add, MethodType.update, MethodType.delete, MethodType.search));
        data.setColumns(getPageFeildMetaDatas(PageType.table));
        return data;
    }

    private List<ButtonMetaData> getButtons(MethodType... types) {
        List<ButtonMetaData> list = new ArrayList<>();
        for (MethodType type : types) {
            list.add(buttonMap.get(type));
        }
        return list;
    }

    private List<FieldMetaData> getFeildMetaDatas(FeildMetaFilter filter) {
        List<FieldMetaData> list = new ArrayList<>();
        FieldMetaData filteredFieldMetaData;
        for (FieldMetaData fieldMetaData : this.entityClassFields) {
            filteredFieldMetaData = filter.getFieldMetaData(fieldMetaData);
            if (filteredFieldMetaData != null) {
                list.add(filteredFieldMetaData);
            }
        }
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    private List<FieldMetaData> getPageFeildMetaDatas(PageType type) {
        PageFieldDesc pageFieldDesc = getPageFieldDesc(type);
        return getFeildMetaDatas(new FeildMetaFilter() {

            @Override
            public FieldMetaData getFieldMetaData(FieldMetaData fieldMetaData) {
                return MetaGenerater.this.getFieldMetaData(fieldMetaData, pageFieldDesc);
            }

        });
    }

    private FieldMetaData getFieldMetaData(FieldMetaData fieldMetaData, PageFieldDesc pageFieldDesc) {
        if(this.categoryMetaData != null && this.categoryMetaData.getFieldName().equals(fieldMetaData.getName())){
            return null;
        }
        FieldMetaData fd = new FieldMetaData();
        fd.setName(fieldMetaData.getName());
        if(pageFieldDesc == null){
            return fd;
        }
        if(ArrayUtils.contains(pageFieldDesc.notShowFileds(), fieldMetaData.getName())){
            return null;
        }else if(ArrayUtils.contains(pageFieldDesc.hiddenFileds(), fieldMetaData.getName())){
            fd.setHidden(true);
        }
        if(pageFieldDesc.showFileds() == null || pageFieldDesc.showFileds().length == 0){
            return fd;
        }else if(ArrayUtils.contains(pageFieldDesc.showFileds(), fieldMetaData.getName())){
            return fd;
        }else{
            return null;
        }
    }
    
    private PageFieldDesc getPageFieldDesc(PageType type){
        if (this.pageFieldDescs != null && this.pageFieldDescs.length > 0) {
            for(PageFieldDesc desc : this.pageFieldDescs){
                if(type.equals(desc.type())){
                    return desc;
                }
            }
        }
        return null;
    }


    public ModaldialogMetaData getAddMetaData() {
        ModaldialogMetaData data = new ModaldialogMetaData();
        data.setFields(getPageFeildMetaDatas(PageType.add));
        if(data.getFields() == null){
            return null;
        }
        data.setButtons(getButtons(MethodType.add, MethodType.close));
        return data;
    }

    public ModaldialogMetaData getUpdateMetaData() {
        ModaldialogMetaData data = new ModaldialogMetaData();
        data.setFields(getPageFeildMetaDatas(PageType.update));
        if(data.getFields() == null){
            return null;
        }
        data.setButtons(getButtons(MethodType.update, MethodType.close));
        return data;
    }

    public ModaldialogMetaData getViewMetaData() {
        ModaldialogMetaData data = new ModaldialogMetaData();
        data.setFields(getPageFeildMetaDatas(PageType.view));
        if(data.getFields() == null){
            return null;
        }
        data.setButtons(getButtons(MethodType.close));
        return data;
    }

    public Map<String, ApiMetaData> getApiMetaData() {
        ApiDesc[] apiDescs = this.apiDescs;
        if (apiDescs == null || apiDescs.length == 0) {
            return null;
        }
        Map<String, ApiMetaData> map = new LinkedHashMap<>();
        for (ApiDesc desc : apiDescs) {
            ApiMetaData data = new ApiMetaData();
            data.setTitle(desc.name());
            if (desc.params() != null) {
                for (String name : desc.params()) {
                    data.addParamName(name);
                }
            }
            data.setFields(getApiFields(desc));
            map.put(desc.serviceId(), data);
        }
        return map;
    }

    private List<FieldMetaData> getApiFields(ApiDesc desc) {
        return getFeildMetaDatas(new FeildMetaFilter() {

            @Override
            public FieldMetaData getFieldMetaData(FieldMetaData fieldMetaData) {
                if(ArrayUtils.contains(desc.notShowFileds(), fieldMetaData.getName())){
                    return null;
                }
                FieldMetaData fd = new FieldMetaData();
                fd.setLabel(fieldMetaData.getLabel());
                fd.setName(fieldMetaData.getName());
                if(desc.showFileds() == null || desc.showFileds().length == 0){
                    return fd;
                }else if(ArrayUtils.contains(desc.showFileds(), fieldMetaData.getName())){
                    return fd;
                }else{
                    return null;
                }
            }

        });
    }

    private interface FeildMetaFilter {

        FieldMetaData getFieldMetaData(FieldMetaData fieldMetaData);

    }

    // Fixed it 
    public Map<String, Object> getDicts() {
        Map<String, Object> dicts = new LinkedHashMap<>();
        Field[] fields = this.entityClass.getDeclaredFields();
        DictDesc dictDesc = null;
        for (Field field : fields) {
            dictDesc = field.getAnnotation(DictDesc.class);
            if (dictDesc != null) {
                if (!dictDesc.dictEnumClass().equals(Object.class)) {
                    dicts.put(field.getName(), getDictDataList(dictDesc.dictEnumClass()));
                } else if (StringUtils.isNoneBlank(dictDesc.dictSql())) {
                    dicts.put(field.getName(), dictDesc.dictSql());
                }
            }
        }
        if (dicts.isEmpty()) {
            return null;
        } else {
            return dicts;
        }
    }

    private <T> List<DictMetaData> getDictDataList(Class<T> clazz) {
        if (DictData.class.isAssignableFrom(clazz)) {
            List<DictMetaData> list = new ArrayList<>();
            Enum<?>[] enums = (Enum[]) clazz.getEnumConstants();
            for (Enum<?> item : enums) {
                list.add(new DictMetaData((DictData) item));
            }
            return list;
        } else {
            throw new RuntimeException(clazz.getName() + " should implements the " + DictData.class.getName());
        }

    }

}
