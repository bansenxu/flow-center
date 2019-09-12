package com.bootdo.modules.flowable.dao;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.modules.flowable.domain.DeModel;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-10 19:40:02
 */
@Mapper
public interface DeModelDao {

	DeModel get(String name);

}
