package com.zjl.springsecurity.service.Impl;

import cn.hutool.Hutool;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.springsecurity.entity.User;
import com.zjl.springsecurity.mapper.UserMapper;
import com.zjl.springsecurity.util.MyStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private String defaultRolePrefix = "ROLE_";
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> userWrapper = new QueryWrapper();
        userWrapper.eq("user_name", username);
        User user = userMapper.selectOne(userWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名未找到");
        }
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
        String[] roles = MyStrUtil.addPrefixIfNot(StringUtils.commaDelimitedListToStringArray(user.getRoles()), defaultRolePrefix);
        auths.addAll(AuthorityUtils.createAuthorityList(roles));
        UserDetails userDetail = org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .authorities(auths)
                .build();
        return userDetail;
    }
}
