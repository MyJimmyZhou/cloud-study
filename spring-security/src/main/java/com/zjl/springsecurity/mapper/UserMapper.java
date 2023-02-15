package com.zjl.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjl.springsecurity.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
