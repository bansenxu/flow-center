package com.bootdo.system.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class RoleMenuDO {

    private Long id;

    private Long roleId;

    private Long menuId;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
