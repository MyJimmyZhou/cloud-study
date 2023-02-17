package com.zjl.springtask.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskScheduleOne {
    private static Logger LOGGER = LoggerFactory.getLogger(TaskScheduleOne.class);
    @Scheduled(cron = "0/10 * * * * *")
    private void Print1(){
        LOGGER.info("执行输出任务\n");
    }
}
