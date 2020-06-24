# spring-boot-api
spring-boot 集成

logging集成mysql的数据库sql语句:

    BEGIN;
    DROP TABLE IF EXISTS logging_event_property;
    DROP TABLE IF EXISTS logging_event_exception;
    DROP TABLE IF EXISTS logging_event;
    COMMIT;

    BEGIN;
    CREATE TABLE logging_event
      (
        timestmp         BIGINT NOT NULL,
        formatted_message  TEXT NOT NULL,
        logger_name       VARCHAR(254) NOT NULL,
        level_string      VARCHAR(254) NOT NULL,
        thread_name       VARCHAR(254),
        reference_flag    SMALLINT,
        arg0              VARCHAR(254),
        arg1              VARCHAR(254),
        arg2              VARCHAR(254),
        arg3              VARCHAR(254),
        caller_filename   VARCHAR(254) NOT NULL,
        caller_class      VARCHAR(254) NOT NULL,
        caller_method     VARCHAR(254) NOT NULL,
        caller_line       CHAR(4) NOT NULL,
        event_id          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
      );
    COMMIT;
    
    BEGIN;
    CREATE TABLE logging_event_property
      (
        event_id          BIGINT NOT NULL,
        mapped_key        VARCHAR(254) NOT NULL,
        mapped_value      TEXT,
        PRIMARY KEY(event_id, mapped_key),
        FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
      );
    COMMIT;
    
    BEGIN;
    CREATE TABLE logging_event_exception
      (
        event_id         BIGINT NOT NULL,
        i                SMALLINT NOT NULL,
        trace_line       VARCHAR(254) NOT NULL,
        PRIMARY KEY(event_id, i),
        FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
      );
    COMMIT;


自定义sql表：

    DROP TABLE IF EXISTS `system_logging_sql`;
    CREATE TABLE `system_logging_sql` (
      `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
      `message` VARCHAR(300) NOT NULL COMMENT '内容',
      `level_string` VARCHAR(254) NOT NULL COMMENT '级别',
      `created_time` DATETIME NOT NULL COMMENT '时间',
      `logger_name` VARCHAR(300) NOT NULL COMMENT '全类名',
      PRIMARY KEY (`id`)
    ) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='自定义日志记录表'
