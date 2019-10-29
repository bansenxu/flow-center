package com.bootdo.modules.flowable.service;


import java.util.List;

import com.bootdo.modules.flowable.domain.HiVarInstDO;

public interface HiVarInstService {

	List<HiVarInstDO> selectHiVarInst(String id);
}
