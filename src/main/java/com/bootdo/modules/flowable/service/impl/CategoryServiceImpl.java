package com.bootdo.modules.flowable.service.impl;


import com.bootdo.modules.flowable.dao.ActDeCategoryMapper;
import com.bootdo.modules.flowable.domain.ActDeCategory;
import com.bootdo.modules.flowable.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ActDeCategoryMapper actDeCategoryMapper;

    @Override
    public List<ActDeCategory> getCategoryList() {
        return actDeCategoryMapper.getCategoryList();
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actDeCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActDeCategory record) {
        return actDeCategoryMapper.insert(record);
    }

    @Override
    public int insertSelective(ActDeCategory record) {
        return actDeCategoryMapper.insertSelective(record);
    }

    @Override
    public ActDeCategory selectByPrimaryKey(Integer id) {
        return actDeCategoryMapper.selectByPrimaryKey(id);
    }
    @Override
    public ActDeCategory selectByCode(String code) {
        return actDeCategoryMapper.selectByCode(code);
    }
    @Override
    public int updateByPrimaryKeySelective(ActDeCategory record) {
        return actDeCategoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActDeCategory record) {
        return actDeCategoryMapper.updateByPrimaryKey(record);
    }
}
