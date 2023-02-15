package com.zjl.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

@Data
@Getter
@Setter
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("user_name")
    private String userName;
    private String password;
}
