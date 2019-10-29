package com.bootdo.modules.flowable.dao;

import java.util.List;

import com.bootdo.modules.flowable.domain.HiVarInstDO;

public interface HiVarInstDao {

	List<HiVarInstDO> selectHiVarInst(String id);
}
