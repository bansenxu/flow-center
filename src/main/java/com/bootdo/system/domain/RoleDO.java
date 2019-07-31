package com.bootdo.system.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class RoleDO {
    private Long roleId;
    private String roleName;
    private String roleSign;
    private String remark;
    private Long userIdCreate;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private List<Long> menuIds;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
