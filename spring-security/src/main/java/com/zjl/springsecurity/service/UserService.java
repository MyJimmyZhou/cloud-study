package com.zjl.springsecurity.service;


import com.zjl.springsecurity.dto.UserDto;
import com.zjl.springsecurity.entity.User;

public interface UserService {
    User loadUserByUserName(String userName);
    User register(UserDto userDto);

    String login(String userName, String password);
}
