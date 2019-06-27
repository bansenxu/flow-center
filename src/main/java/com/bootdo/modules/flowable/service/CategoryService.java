package com.bootdo.modules.flowable.service;


import com.bootdo.modules.flowable.domain.ActDeCategory;

import java.util.List;

public interface CategoryService {

    List<ActDeCategory> getCategoryList();

    int deleteByPrimaryKey(Integer id);

    int insert(ActDeCategory record);

    int insertSelective(ActDeCategory record);

    ActDeCategory selectByPrimaryKey(Integer id);
    ActDeCategory selectByCode(String code);

    int updateByPrimaryKeySelective(ActDeCategory record);

    int updateByPrimaryKey(ActDeCategory record);


}
