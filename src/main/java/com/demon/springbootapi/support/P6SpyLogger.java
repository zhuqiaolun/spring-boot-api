package com.demon.springbootapi.support;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: P6SpyLogger
 * @Description:  自定义日志打印
 * @Author: Demon
 * @Date: 2020/6/8 16:56
 */
public class P6SpyLogger implements MessageFormattingStrategy {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    /**
     * 卐 返回message
     * @param connectionId connectionId
     * @param now now
     * @param elapsed now
     * @param category category
     * @param prepared prepared
     * @param sql sql
     * @param url url
     * @return 返回
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? "卐Consume Time：" + elapsed + " ms " + this.format.format(new Date()) + " Execute SQL：" + sql.replaceAll("[\\s]+", " ") : "";
    }
}
