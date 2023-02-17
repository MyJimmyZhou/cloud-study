package com.zjl.springmybatis.service.Impl;

import com.mysql.cj.util.StringUtils;
import com.zjl.springmybatis.mbg.mapper.TbMybatisMapper;
import com.zjl.springmybatis.mbg.model.TbMybatis;
import com.zjl.springmybatis.mbg.model.TbMybatisExample;
import com.zjl.springmybatis.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MybatisServiceImpl implements MybatisService {

    @Autowired
    private TbMybatisMapper tbMybatisMapper;
    @Override
    public TbMybatis selectByName(String name) {
        TbMybatisExample example = new TbMybatisExample();
        TbMybatisExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isNullOrEmpty(name)){
            criteria.andNameLike("%" + name + "%");
        }
        List<TbMybatis> list = tbMybatisMapper.selectByExample(example);
        if(list.isEmpty())
            return null;
        return list.get(0);
    }
}
