package com.zjl.springsecurity.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.springsecurity.dto.UserDto;
import com.zjl.springsecurity.entity.User;
import com.zjl.springsecurity.mapper.UserMapper;
import com.zjl.springsecurity.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User loadUserByUserName(String userName) {
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper = userWrapper.eq("user_name", userName);
        User user = userMapper.selectOne(userWrapper);
        return user;
    }

    @Override
    public User register(UserDto userDto) {
        User user = loadUserByUserName(userDto.getUserName());
        if (user != null) return null;
        User entity = new User();
        BeanUtils.copyProperties(userDto, entity);
        int result = userMapper.insert(entity);
        return entity;
    }
}
