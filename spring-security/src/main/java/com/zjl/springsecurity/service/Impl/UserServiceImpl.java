package com.zjl.springsecurity.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.springsecurity.dto.UserDto;
import com.zjl.springsecurity.entity.User;
import com.zjl.springsecurity.mapper.UserMapper;
import com.zjl.springsecurity.service.UserService;
import com.zjl.springsecurity.util.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

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

    @Override
    public String login(String userName, String password) {
        User user = loadUserByUserName((userName));
        if (user == null) return null;
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles());
        String token = jwtTokenUtil.generateToken(new
                org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), auths));
        return token;
    }
}
