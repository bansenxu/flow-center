package com.bootdo.modules.flowable.dao;

import com.bootdo.modules.flowable.domain.HiTaskinstDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-13 14:52:13
 */
@Mapper
public interface HiTaskinstDao {

	HiTaskinstDO get(String id);
	
	List<Map<String, Object>> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(HiTaskinstDO hiTaskinst);
	
	int update(HiTaskinstDO hiTaskinst);
	
	int remove(String ID_);
	
	int batchRemove(String[] ids);
}
