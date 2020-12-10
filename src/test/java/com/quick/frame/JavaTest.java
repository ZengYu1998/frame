package com.quick.frame;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 普通Java单元测试类
 * @author: znegyu
 * @create: 2020-11-24 09:27
 **/

public class JavaTest {

    @Test
    public void test_1(){
        List<String> list = Arrays.asList("abc", "abcd");
        System.out.println(String.join(",",list).contentEquals("abc"));
        //throw new RuntimeException("111");
        //throw new ServiceException(500,"1");
    }
}
