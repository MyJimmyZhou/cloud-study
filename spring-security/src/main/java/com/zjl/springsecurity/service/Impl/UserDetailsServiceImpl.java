package com.zjl.springsecurity.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.springsecurity.entity.User;
import com.zjl.springsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> userWrapper = new QueryWrapper();
        userWrapper.eq("user_name", username);
        User user =  userMapper.selectOne(userWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名未找到");
        }
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_le");
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                new BCryptPasswordEncoder().encode(user.getPassword()), auths);
    }
}
