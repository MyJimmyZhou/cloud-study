package com.zjl.springsecurity.controller;

import com.zjl.springsecurity.common.api.CommonResult;
import com.zjl.springsecurity.dto.UserDto;
import com.zjl.springsecurity.entity.User;
import com.zjl.springsecurity.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/hello")
    public String Hello(){
        return "Hello!";
    }

    @GetMapping("/update")
    @PostAuthorize("hasAnyAuthority('admins')")
    public String update(){
        return "update!";
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    @ResponseBody
    public CommonResult<User> register(@Validated @RequestBody UserDto userDto){
        User user = userService.register(userDto);
        if(user == null) return CommonResult.failed("注册失败");
        return CommonResult.success(user);
    }
}
