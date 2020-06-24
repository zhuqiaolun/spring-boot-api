package com.demon.springbootapi.config;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.db.DBAppenderBase;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @ClassName: LogDbAppenderConfig
 * @Description: 重写DBAppender类为LogDBAppender类
 * @Author: Demon
 * @Date: 2020/6/8 14:26
 */
@Configuration
public class LogDbAppenderConfig extends DBAppenderBase<ILoggingEvent> {

    private static final Method GET_GENERATED_KEYS_METHOD;
    /** 插入sql */
    private String insertSql;
    /** message 日志内容 */
    private static final int MESSAGE = 1;
    /** level_string */
    private static final int LEVEL_STRING = 2;
    /** created_time 时间 */
    private static final int CREATE_TIME = 3;
    /** logger_name 全类名 */
    private static final int LOGGER_NAME = 4;

    static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();

    static {
        // PreparedStatement.getGeneratedKeys() method was added in JDK 1.4
        Method getGeneratedKeysMethod;
        try {
            // the
            getGeneratedKeysMethod = PreparedStatement.class.getMethod("getGeneratedKeys", (Class[]) null);
        } catch (Exception ex) {
            getGeneratedKeysMethod = null;
        }
        GET_GENERATED_KEYS_METHOD = getGeneratedKeysMethod;
    }

    @Override
    public void start() {
        // 将写好的sql语句赋值给insertSQL
        insertSql = buildInsertSql();
        super.start();
    }

    /**
     * 自己写新增sql语句
     * @return 返回
     */
    private static String buildInsertSql() {
        return "INSERT INTO `system_logging_sql`(`message`,`level_string`,`created_time`,`logger_name`)" +
                "VALUES (?,?,?,?)";
    }

    @Override
    protected Method getGeneratedKeysMethod() {
        return GET_GENERATED_KEYS_METHOD;
    }

    @Override
    protected String getInsertSQL() {
        return insertSql;
    }

    /**
     * 主要修改的方法
     *
     * @param stmt 预编译
     * @param event 事件
     * @throws SQLException 异常
     */
    private void bindLoggingEventWithInsertStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
        // event.getFormattedMessage() 日志打印内容
        String message = event.getFormattedMessage();
        //判断SQL日志消息首字母为 - 的日志，记录到数据库表
        if(message.startsWith("卐")){
            stmt.setString(MESSAGE, message.substring(1));
            // event.getLevel().toString() 日志级别
            stmt.setString(LEVEL_STRING, event.getLevel().toString());
            // new Timestamp(event.getTimeStamp()) 时间
            stmt.setTimestamp(CREATE_TIME, new Timestamp(event.getTimeStamp()));
            // event.getLoggerName() 全类名
            stmt.setString(LOGGER_NAME, event.getLoggerName());
        }
    }

    @Override
    protected void subAppend(ILoggingEvent eventObject, Connection connection, PreparedStatement statement) throws Throwable {
        bindLoggingEventWithInsertStatement(statement, eventObject);
        // This is expensive... should we do it every time?
        int updateCount = statement.executeUpdate();
        if (updateCount != 1) {
            addWarn("Failed to insert loggingEvent");
        }
    }

    @Override
    protected void secondarySubAppend(ILoggingEvent eventObject, Connection connection, long eventId) throws Throwable {
    }
}
