package com.quick.frame.commons.other;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 服务器启动成功时执行
 * @author: znegyu
 * @create: 2020-11-23 11:14
 **/
@Component
public class WebRunExecute implements CommandLineRunner {

    @Value("${spring.profiles.active}")
    public String active;

    @Value("${server.port}")
    public Integer port;

    @Value("${server.servlet.context-path}")
    public String contextPath;

    @Value("${project.describe.name}")
    public String projectName;


    @Override
    public void run(String... args) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    欢迎使用：      " + projectName + "  ");
        sb.append("\r\n    项目启动成功：  " + new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()) + "  ");
        sb.append("\r\n    项目运行环境：  " + active + "  ");
        sb.append("\r\n    后端地址：    http://localhost:" + port + contextPath +"/doc.html  ");
        sb.append("\r\n    Druid地址：  http://localhost:" + port + contextPath +"/druid  ");
        sb.append("\r\n    Druid       账号:admin  密码:123456");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
    }
}
