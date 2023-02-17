package com.zjl.springmybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zjl.springmybatis.mbg.mapper")
public class MybatisConfig {
}
