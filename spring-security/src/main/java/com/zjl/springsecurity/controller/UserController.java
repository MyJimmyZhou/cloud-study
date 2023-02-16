package com.zjl.springsecurity.controller;

import com.zjl.springsecurity.common.api.CommonResult;
import com.zjl.springsecurity.dto.UserDto;
import com.zjl.springsecurity.entity.User;
import com.zjl.springsecurity.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@Api(tags = "UserController", description = "用户测试模块")
public class UserController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String Hello() {
        return "Hello!";
    }

    @GetMapping("/update")
    @PreAuthorize("hasAuthority('update') and hasRole('user')")
    public String update() {
        return "update!";
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    @ResponseBody
    public CommonResult<User> register(@Validated @RequestBody UserDto userDto) {
        User user = userService.register(userDto);
        if (user == null) return CommonResult.failed("注册失败");
        return CommonResult.success(user);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UserDto userDto) {
        String token = userService.login(userDto.getUserName(), userDto.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
}
