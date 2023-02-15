package com.zjl.springsecurity.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public UserDetails loadUserByUserName(String userName);
}
