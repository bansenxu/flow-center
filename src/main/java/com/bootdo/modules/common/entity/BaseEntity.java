package com.bootdo.modules.common.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 类的功能描述.
 * 对业务实体做公共属性
 */

@Data
public class BaseEntity {
    /**
     * 新增人
     */
    private String createId;
    /**
     * 修改者
     */
    private String updateId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 新增时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 部门(组织)ID【FK】,直接归属的组织ID
     */
    private String baid;
    /**
     * 机构ID【FK】(上级)
     */
    private String bapid;

    /**
     * 部门ids 部门数据权限
     */
    private List<String> baidList;
    /**
     * 机构ids 机构数据权限
     */
    private List<String> bapidList;

    /**
     * 部门名称
     */
    private String baName;

    /**
     * 机构名称
     */
    private String bapName;

}
