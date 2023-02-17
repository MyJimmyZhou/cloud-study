package com.zjl.springmybatis.controller;

import com.zjl.springmybatis.common.api.CommonResult;
import com.zjl.springmybatis.mbg.model.TbMybatis;
import com.zjl.springmybatis.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mybatis")
public class MybatisController {

    @Autowired
    private MybatisService mybatisService;

    @RequestMapping(value = "/get/{name}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getByName(@PathVariable("name") String name){
        TbMybatis mybatis = mybatisService.selectByName(name);
        return CommonResult.success(mybatis);
    }
}
