package com.zjl.springsecurity.dto;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserDto {
    @NotEmpty
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    @NotEmpty
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
