/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : spring_boot_api

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-07-02 13:24:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for logging_event
-- ----------------------------
DROP TABLE IF EXISTS `logging_event`;
CREATE TABLE `logging_event` (
  `timestmp` bigint(20) NOT NULL,
  `formatted_message` text NOT NULL,
  `logger_name` varchar(254) NOT NULL,
  `level_string` varchar(254) NOT NULL,
  `thread_name` varchar(254) DEFAULT NULL,
  `reference_flag` smallint(6) DEFAULT NULL,
  `arg0` varchar(254) DEFAULT NULL,
  `arg1` varchar(254) DEFAULT NULL,
  `arg2` varchar(254) DEFAULT NULL,
  `arg3` varchar(254) DEFAULT NULL,
  `caller_filename` varchar(254) NOT NULL,
  `caller_class` varchar(254) NOT NULL,
  `caller_method` varchar(254) NOT NULL,
  `caller_line` char(4) NOT NULL,
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logging_event
-- ----------------------------

-- ----------------------------
-- Table structure for logging_event_exception
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_exception`;
CREATE TABLE `logging_event_exception` (
  `event_id` bigint(20) NOT NULL,
  `i` smallint(6) NOT NULL,
  `trace_line` varchar(254) NOT NULL,
  PRIMARY KEY (`event_id`,`i`),
  CONSTRAINT `logging_event_exception_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logging_event_exception
-- ----------------------------

-- ----------------------------
-- Table structure for logging_event_property
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_property`;
CREATE TABLE `logging_event_property` (
  `event_id` bigint(20) NOT NULL,
  `mapped_key` varchar(254) NOT NULL,
  `mapped_value` text,
  PRIMARY KEY (`event_id`,`mapped_key`),
  CONSTRAINT `logging_event_property_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logging_event_property
-- ----------------------------

-- ----------------------------
-- Table structure for system_logging_sql
-- ----------------------------
DROP TABLE IF EXISTS `system_logging_sql`;
CREATE TABLE `system_logging_sql` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(300) NOT NULL COMMENT '内容',
  `level_string` varchar(254) NOT NULL COMMENT '级别',
  `created_time` datetime NOT NULL COMMENT '时间',
  `logger_name` varchar(300) NOT NULL COMMENT '全类名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8 COMMENT='自定义日志记录表';

-- ----------------------------
-- Records of system_logging_sql
-- ----------------------------

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `menu_name` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(100) NOT NULL COMMENT '菜单url（Controller 请求路径）',
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('1', '用户基本信息', '/user/getBaseUserInfo');
INSERT INTO `system_menu` VALUES ('2', '用户详情信息', '/user/getQueryUserInfo');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1', '超级管理员');
INSERT INTO `system_role` VALUES ('2', '管理员');
INSERT INTO `system_role` VALUES ('3', '普通用户');

-- ----------------------------
-- Table structure for system_role_menus
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menus`;
CREATE TABLE `system_role_menus` (
  `rm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `role_id` int(11) DEFAULT NULL COMMENT '角色表id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单表id',
  PRIMARY KEY (`rm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='权限菜单表';

-- ----------------------------
-- Records of system_role_menus
-- ----------------------------
INSERT INTO `system_role_menus` VALUES ('1', '1', '1');
INSERT INTO `system_role_menus` VALUES ('2', '1', '2');
INSERT INTO `system_role_menus` VALUES ('3', '2', '1');
INSERT INTO `system_role_menus` VALUES ('4', '3', '2');

-- ----------------------------
-- Table structure for system_swagger_info
-- ----------------------------
DROP TABLE IF EXISTS `system_swagger_info`;
CREATE TABLE `system_swagger_info` (
  `si_id` int(11) NOT NULL AUTO_INCREMENT,
  `si_title` varchar(50) DEFAULT NULL COMMENT '系统标题',
  `si_version` varchar(20) DEFAULT NULL COMMENT '系统版本',
  `si_description` varchar(200) DEFAULT NULL COMMENT '系统描述',
  `si_schemes` varchar(50) DEFAULT NULL COMMENT '请求模式',
  `si_serverhost` varchar(20) DEFAULT NULL COMMENT 'HostAddress',
  `si_serverport` smallint(10) DEFAULT NULL,
  `si_serverpath` varchar(100) DEFAULT NULL,
  `si_securityDefinitions` varchar(255) DEFAULT NULL COMMENT '全局变量',
  `si_contact_name` varchar(30) DEFAULT NULL,
  `si_contact_url` varchar(200) DEFAULT NULL,
  `si_contact_email` varchar(100) DEFAULT NULL,
  `si_createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`si_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='swagger 信息';

-- ----------------------------
-- Records of system_swagger_info
-- ----------------------------
INSERT INTO `system_swagger_info` VALUES ('1', 'spring-boot-api', '1.0', '后台API简易测试', '[\"http\",\"https\"]', '127.0.0.1', '8011', 'spring-boot-api', '{\"api_key\":{\"type\":\"apiKey\",\"name\":\"api_key\",\"in\":\"header\"}}', 'zhuqiaolun', 'https://github.com/zhuqiaolun/spring-boot-api', 'zhuqlchina@163.com', '2020-07-01 14:38:27');

-- ----------------------------
-- Table structure for system_swagger_tags
-- ----------------------------
DROP TABLE IF EXISTS `system_swagger_tags`;
CREATE TABLE `system_swagger_tags` (
  `st_id` int(11) NOT NULL AUTO_INCREMENT,
  `st_name` varchar(20) DEFAULT NULL COMMENT '标签名称',
  `st_description` varchar(50) DEFAULT NULL COMMENT '标签描述',
  `st_createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`st_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='swagger 标签';

-- ----------------------------
-- Records of system_swagger_tags
-- ----------------------------
INSERT INTO `system_swagger_tags` VALUES ('1', 'Test', '测试', '2020-07-01 13:43:51');
INSERT INTO `system_swagger_tags` VALUES ('2', 'Auth', '认证', '2020-07-01 13:44:29');
INSERT INTO `system_swagger_tags` VALUES ('3', 'User', '用户', '2020-07-01 13:44:03');
INSERT INTO `system_swagger_tags` VALUES ('5', 'Weather', '天气', '2020-07-01 13:44:17');

-- ----------------------------
-- Table structure for system_swagger_url
-- ----------------------------
DROP TABLE IF EXISTS `system_swagger_url`;
CREATE TABLE `system_swagger_url` (
  `su_id` int(11) NOT NULL AUTO_INCREMENT,
  `su_url` varchar(255) DEFAULT NULL,
  `su_method` varchar(50) DEFAULT NULL COMMENT '请求类型',
  `su_tags` varchar(100) DEFAULT NULL COMMENT '标签',
  `su_summary` varchar(100) DEFAULT NULL COMMENT '标题',
  `su_description` varchar(100) DEFAULT NULL COMMENT '描述',
  `su_operationId` varchar(100) DEFAULT NULL COMMENT '标识（可写方法名称）',
  `su_consumes` varchar(255) DEFAULT '["application/json"]' COMMENT '请求参数类型',
  `su_produces` varchar(255) DEFAULT '["application/json"]' COMMENT '返回参数类型',
  `su_parameters` varchar(255) DEFAULT NULL COMMENT '参数',
  `su_responses` varchar(255) DEFAULT '{"200":{"description":"successful operation"}}' COMMENT '响应',
  `su_security` varchar(200) DEFAULT NULL COMMENT '全局api_key',
  `su_deprecated` tinyint(4) DEFAULT '0' COMMENT '是否过时',
  `su_createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`su_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='swagger 的URL 配置';

-- ----------------------------
-- Records of system_swagger_url
-- ----------------------------
INSERT INTO `system_swagger_url` VALUES ('1', 'test', 'get', 'Test', '测试获取MD5值', '通过MessageDigest.getInstance(\"MD5\")执行返回结果', 'getMd5', '[\"application/json\"]', '[\"application/json\"]', null, '{\"200\":{\"description\":\"successful operation\"},\"400\":{\"description\":\"Invalid appId\"},\"404\":{\"description\":\"Page not found\"}}', '[{\"api_key\":[]}]', '0', '2020-07-01 10:16:32');
INSERT INTO `system_swagger_url` VALUES ('2', 'auth/getToken', 'post', 'Auth', '获取token', '返回token以及时效', 'getToken', '[\"application/json\"]', '[\"application/json\"]', '[{\"name\":\"appId\",\"in\":\"query\",\"description\":\"The appId for login username\",\"required\":true,\"type\":\"string\"},{\"name\":\"appKey\",\"in\":\"query\",\"description\":\"The appKey for login password\",\"required\":true,\"type\":\"string\"}]', '{\"200\":{\"description\":\"successful operation\"}}', null, '0', '2020-07-01 13:38:04');
INSERT INTO `system_swagger_url` VALUES ('3', 'user/getBaseUserInfo', 'post', 'User', '获取用户基础信息', '查询数据库，返回用户部分信息', 'getBaseUserInfo', '[\"application/json\"]', '[\"application/json\"]', '[{\"name\":\"token\",\"in\":\"header\",\"required\":true,\"type\":\"string\"}]', '{\"200\":{\"description\":\"successful operation\"}}', null, '0', '2020-07-01 10:50:06');
INSERT INTO `system_swagger_url` VALUES ('4', 'user/getQueryUserInfo', 'post', 'User', '获取用户详细信息', '查询数据库，返回用户全部信息', 'getQueryUserInfo', '[\"application/json\"]', '[\"application/json\"]', '[{\"name\":\"token\",\"in\":\"header\",\"required\":true,\"type\":\"string\"}]', '{\"200\":{\"description\":\"successful operation\"}}', null, '0', '2020-07-01 10:55:51');
INSERT INTO `system_swagger_url` VALUES ('6', 'weather/getToDayInfo', 'post', 'Weather', '获取上海浦东新区今日天气', '通过和风天气返回数据', 'getToDayInfo', '[\"application/json\"]', '[\"application/json\"]', null, '{\"200\":{\"description\":\"successful operation\"}}', '[{\"api_key\":[]}]', '0', '2020-07-01 11:02:20');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `app_id` varchar(50) DEFAULT NULL COMMENT '用户名',
  `app_key` varchar(255) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `user_name` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', '1000000000000000', '{bcrypt}$2a$10$vqbunqAHy9RJ.GPktuUzteQ2ve86q1pcLKABsSysuUia8XdvJEPx6', '真·张三', '张三', '2020-06-03 21:37:43');

-- ----------------------------
-- Table structure for system_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `system_user_roles`;
CREATE TABLE `system_user_roles` (
  `ur_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户表id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色表id',
  PRIMARY KEY (`ur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户权限表';

-- ----------------------------
-- Records of system_user_roles
-- ----------------------------
INSERT INTO `system_user_roles` VALUES ('1', '1000000000000000', '1');
INSERT INTO `system_user_roles` VALUES ('2', '1000000000000000', '3');
INSERT INTO `system_user_roles` VALUES ('3', '2000000000000000', '2');
