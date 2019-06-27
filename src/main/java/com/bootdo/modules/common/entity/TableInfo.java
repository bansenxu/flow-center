package com.bootdo.modules.common.entity;

import lombok.Data;

/**
 * 类的功能描述.
 * 动态表信息
 */

@Data
public class TableInfo {
    public static final String TAB_FIELDS = "fields";
    public static final String TAB_PKNAME = "pkName";
    public static final String TAB_TABLENAME = "tableName";
    public static final String TAB_ID = "id";
    private String fieldName;
    private Object fieldValue;

    public TableInfo(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
