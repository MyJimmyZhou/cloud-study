package com.zjl.springsecurity.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.springsecurity.mapper.UserMapper;
import com.zjl.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUserName(String userName) {
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        User user = userWrapper.eq("user_name", userName).getEntity();
        if (user == null) {
            throw new UsernameNotFoundException("用户名未找到");
        }
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_le");
        return new User(user.getUsername(), new BCryptPasswordEncoder().encode(user
                .getPassword()), auths);
    }
}
