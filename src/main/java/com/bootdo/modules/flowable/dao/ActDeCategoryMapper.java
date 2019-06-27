package com.bootdo.modules.flowable.dao;

import com.bootdo.modules.flowable.domain.ActDeCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActDeCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActDeCategory record);

    int insertSelective(ActDeCategory record);

    ActDeCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActDeCategory record);

    int updateByPrimaryKey(ActDeCategory record);

    List<ActDeCategory> getCategoryList();

    ActDeCategory selectByCode(String code);
}