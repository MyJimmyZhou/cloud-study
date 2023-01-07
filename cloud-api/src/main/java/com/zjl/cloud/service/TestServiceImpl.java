package com.zjl.cloud.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@RefreshScope
@Service
public class TestServiceImpl implements TestService{
    @Value("${nacosConfigDemmo}")
    private String demoName;
    @Override
    public String getName() {
        return demoName;
    }
}
