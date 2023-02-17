package com.zjl.springmybatis.service;

import com.zjl.springmybatis.mbg.model.TbMybatis;

public interface MybatisService {
    TbMybatis selectByName(String name);
}
