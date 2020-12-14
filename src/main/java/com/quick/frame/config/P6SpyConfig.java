package com.quick.frame.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

/**
 * @description: SQL分析打印工具(不能用于生产环境)
 * @author: znegyu
 * @create: 2020-12-14 09:03
 **/
public class P6SpyConfig implements MessageFormattingStrategy {
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? " 执行SQL消耗时间：" +
                elapsed + " 毫秒 " + "执行时间: "+ now + "\n 执行的 SQL 语句：" + "\n "+
                sql.replaceAll("[\\s]+", " ") + "\n" : "";
    }
}
