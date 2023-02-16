package com.zjl.springsecurity;


import com.zjl.springsecurity.util.MyStrUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class MyStrUtilTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyStrUtilTests.class);

    @Test
    public void addPrefixIfNot_test() {
        String[] arr = {"a", "b"};
        arr = MyStrUtil.addPrefixIfNot(arr, "z");
        System.out.println(Arrays.toString(arr));
    }
}
