package com.bootdo.system.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class UserRoleDO {

    private Long id;

    private Long userId;

    private Long roleId;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
