/*
Navicat MySQL Data Transfer

Source Server         : 192.168.199.100
Source Server Version : 50505
Source Host           : 192.168.199.100:3306
Source Database       : base

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-08-11 18:19:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pay_account
-- ----------------------------
DROP TABLE IF EXISTS `pay_account`;
CREATE TABLE `pay_account` (
  `pay_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '支付账号id',
  `partner` varchar(32) DEFAULT NULL COMMENT '支付合作id,商户id，差不多是支付平台的账号或id',
  `appid` varchar(32) DEFAULT NULL COMMENT '应用id',
  `public_key` varchar(1204) DEFAULT NULL COMMENT '支付公钥，sign_type只有单一key时public_key与private_key相等，比如sign_type=MD5的情况',
  `private_key` varchar(2048) DEFAULT NULL COMMENT '支付私钥',
  `notify_url` varchar(1024) DEFAULT NULL COMMENT '异步回调地址',
  `return_url` varchar(1024) DEFAULT NULL COMMENT '同步回调地址',
  `seller` varchar(256) DEFAULT NULL COMMENT '收款账号, 针对支付宝',
  `sign_type` varchar(16) DEFAULT NULL COMMENT '签名类型',
  `input_charset` varchar(16) DEFAULT NULL COMMENT '枚举值，字符编码 utf-8,gbk等等',
  `pay_type` char(16) DEFAULT NULL COMMENT '支付类型,aliPay：支付宝，wxPay：微信, youdianPay: 友店微信,此处开发者自定义对应com.egzosn.pay.demo.entity.PayType枚举值',
  `msg_type` char(8) DEFAULT NULL COMMENT '消息类型，text,xml,json',
  `is_test` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为测试环境',
  `create_by` char(32) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`pay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='支付系统配置表';

-- ----------------------------
-- Records of pay_account
-- ----------------------------
INSERT INTO `pay_account` VALUES ('1', '2088621908936903', '2017062007529706', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB', 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKdSzE9Kp7qU3XfB5q6Hcik6mbErr5A1jX8nEjXwsFLil7jna+vlgomRUc4mMc0jzawtp0tOguva1WeM9pj6D9ycDNxzYCMFNsTm7/XtXpFB8Gl1CknnxoZyCwTUG0LUw5QsbFWDzf/jPXzG7m/RgaEC05EzCbgA1GHNZHcIp3SnAgMBAAECgYBXhAEEwAF2n4IuryZexs05L0NP3Y+YF8qIZQErYVZ4I7hDqBdUbNiX54AoGOYXa9CZa8+Cne/wOKdB4ALjBHfii/kJFgkulYRLszqqpvgYj0hGiLWWeUT6yACig/OnwhfpUQ+4ZQJFYMR3F6WIKhVhc+VrLK62ChLTYOyU/Bg+EQJBAOLmy9ommZpdWb7OMZ9c2qAgZrPnh8MQ0Z/3ze+5APaHxAtIjt7Z0FNFlySMizkRUmev1gk07XOJKiGOXtwpRq0CQQC8yAud2mZ9e9TDQvymDYv6Pxiowhgc08nYGVHVYteM81wIwVy1NwZMqi60KtZ0Cpd1KftJLycfqr3hGdFttlcjAkBNWw/unLBLXz4EffmO8jIa21EITowLZcWBuxL7gmAgrGKa0i7bLPkIGraPoFaqqwwkC192HhRCCmZRsF8Iwg21AkAPPO6DHV/sfRRddojCNpG372PkK1aa7RV71f9fBA12GSaypjj/f6OIiKwgatzInRpAoNThwWadDcEp7FWVT4SVAkEAjde5paIi4S0K6rvP7Pm51gOEw4XNAayeY5sbeZErsBi8wpE/wH5B0rROONN5pGBAuIEuRUy2tPMhIJKucOnsxA==', 'http://45.32.47.112:8080/CDSystem/payBack1.json', null, '2088621908936903', 'RSA', 'UTF-8', 'aliPay', 'text', '0', 'david', '2017-06-19 20:26:56');
INSERT INTO `pay_account` VALUES ('2', '1352325001', 'wx37057309fa439183', 'ZMKJzhimaikeji702dZMKJzhimaikeji', 'ZMKJzhimaikeji702dZMKJzhimaikeji', 'http://45.32.47.112:8080/CDSystem/payBack2.json', 'http://45.32.47.112:8080/CDSystem/payBack2.jso', '1352325001', 'MD5', 'UTF-8', 'wxPay', 'xml', '0', 'david', '2017-06-19 20:26:56');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('scheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for s_classified
-- ----------------------------
DROP TABLE IF EXISTS `s_classified`;
CREATE TABLE `s_classified` (
  `u_id` varchar(32) NOT NULL COMMENT 'uid',
  `name` varchar(256) NOT NULL COMMENT '分类名称',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `type` varchar(256) DEFAULT NULL COMMENT '类型',
  `parent_id` varchar(32) NOT NULL COMMENT '父级分类',
  `icon` varchar(256) DEFAULT NULL COMMENT '图标',
  `config` text COMMENT '分类配置',
  `sort_index` decimal(32,0) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据分类表';

-- ----------------------------
-- Records of s_classified
-- ----------------------------

-- ----------------------------
-- Table structure for s_config
-- ----------------------------
DROP TABLE IF EXISTS `s_config`;
CREATE TABLE `s_config` (
  `u_id` varchar(32) NOT NULL COMMENT 'uid',
  `content` text NOT NULL COMMENT '配置内容',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `classified_id` varchar(32) DEFAULT NULL COMMENT '分类id',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置文件表';

-- ----------------------------
-- Records of s_config
-- ----------------------------

-- ----------------------------
-- Table structure for s_data_source
-- ----------------------------
DROP TABLE IF EXISTS `s_data_source`;
CREATE TABLE `s_data_source` (
  `u_id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(64) NOT NULL COMMENT '数据源名称',
  `url` varchar(512) NOT NULL COMMENT 'url',
  `username` varchar(128) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `enabled` decimal(4,0) NOT NULL COMMENT '是否启用',
  `create_date` year(4) NOT NULL COMMENT '创建日期',
  `properties` text COMMENT '其他配置',
  `comment` varchar(512) DEFAULT NULL COMMENT '备注',
  `test_sql` varchar(512) DEFAULT NULL COMMENT '测试链接时使用的sql',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据源';

-- ----------------------------
-- Records of s_data_source
-- ----------------------------

-- ----------------------------
-- Table structure for s_form
-- ----------------------------
DROP TABLE IF EXISTS `s_form`;
CREATE TABLE `s_form` (
  `u_id` varchar(32) NOT NULL COMMENT 'uid',
  `classified_id` varchar(32) DEFAULT NULL COMMENT '分类id',
  `name` varchar(256) NOT NULL COMMENT '名称',
  `html` text COMMENT 'html内容',
  `meta` text COMMENT '结构定义',
  `config` text COMMENT '配置',
  `version` decimal(32,0) DEFAULT NULL COMMENT '版本',
  `revision` decimal(32,0) DEFAULT NULL COMMENT '修订版',
  `release` decimal(32,0) DEFAULT NULL COMMENT '当前发布版本',
  `using` decimal(4,0) DEFAULT NULL COMMENT '是否使用中',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态表单';

-- ----------------------------
-- Records of s_form
-- ----------------------------

-- ----------------------------
-- Table structure for s_history
-- ----------------------------
DROP TABLE IF EXISTS `s_history`;
CREATE TABLE `s_history` (
  `u_id` varchar(32) NOT NULL COMMENT 'ID',
  `type` varchar(64) NOT NULL COMMENT '历史记录类型',
  `describe` varchar(512) DEFAULT NULL COMMENT '记录描述',
  `primary_key_name` varchar(32) DEFAULT NULL COMMENT '主键名',
  `primary_key_value` varchar(64) DEFAULT NULL COMMENT '主键值',
  `change_before` text COMMENT '修改前数据',
  `change_after` text COMMENT '修改后数据',
  `create_date` datetime NOT NULL COMMENT '修改时间',
  `creator_id` varchar(32) DEFAULT NULL COMMENT '修改人ID',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史记录表';

-- ----------------------------
-- Records of s_history
-- ----------------------------

-- ----------------------------
-- Table structure for s_modules
-- ----------------------------
DROP TABLE IF EXISTS `s_modules`;
CREATE TABLE `s_modules` (
  `u_id` varchar(32) NOT NULL COMMENT 'uid',
  `name` varchar(256) NOT NULL COMMENT '模块名称',
  `uri` varchar(1024) DEFAULT NULL COMMENT 'uri',
  `icon` varchar(256) DEFAULT NULL COMMENT '图标',
  `parent_id` varchar(256) NOT NULL COMMENT '上级菜单',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` decimal(4,0) DEFAULT NULL COMMENT '状态',
  `optional` text NOT NULL COMMENT '可选权限',
  `sort_index` decimal(32,0) NOT NULL COMMENT '排序',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统模块';

-- ----------------------------
-- Records of s_modules
-- ----------------------------
INSERT INTO `s_modules` VALUES ('activity', '活动管理', 'admin/activity/list.html', 'fa fa-yelp', 'ad-manager', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '1');
INSERT INTO `s_modules` VALUES ('ad-manager', '广告管理', '', 'fa fa-youtube-play', '-1', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"}]', '7');
INSERT INTO `s_modules` VALUES ('area', '区域管理', 'admin/area/list.html', 'fa fa-pie-chart', 'base', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '2');
INSERT INTO `s_modules` VALUES ('base', '基础管理', '', 'fa fa-bars', '-1', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"}]', '1');
INSERT INTO `s_modules` VALUES ('code-generator', '代码生成器', 'admin/system-dev/generator/code-generator.html', 'fa  fa-code', 'sys-parent', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"}]', '407');
INSERT INTO `s_modules` VALUES ('database', '数据库维护', 'admin/database/list.html', 'fa fa-database', 'sys-parent', '', '1', '[{\"id\":\"drop\"},{\"id\":\"comment\"},{\"id\":\"create\"},{\"id\":\"alter\"},{\"id\":\"R\"},{\"id\":\"M\",\"text\":\"菜单可见\",\"checked\":true},{\"id\":\"select\",\"text\":\"查询\",\"checked\":true},{\"id\":\"insert\",\"text\":\"新增\",\"checked\":true},{\"id\":\"update\",\"text\":\"修改\",\"checked\":true},{\"id\":\"delete\",\"text\":\"删除\",\"checked\":false}]', '406');
INSERT INTO `s_modules` VALUES ('datasource', '数据源', 'admin/datasource/list.html', 'fa fa-database', 'sys-parent', '', '1', '[{\"id\":\"M\",\"text\":\"菜单可见\",\"checked\":true},{\"id\":\"import\",\"text\":\"导入excel\",\"checked\":true},{\"id\":\"export\",\"text\":\"导出excel\",\"checked\":true},{\"id\":\"R\",\"text\":\"查询\",\"checked\":true},{\"id\":\"C\",\"text\":\"新增\",\"checked\":true},{\"id\":\"U\",\"text\":\"修改\",\"checked\":true},{\"id\":\"D\",\"text\":\"删除\",\"checked\":false}]', '409');
INSERT INTO `s_modules` VALUES ('datastatistics', '数据统计', '', 'fa fa-database', '-1', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"}]', '6');
INSERT INTO `s_modules` VALUES ('device', '设备管理', 'admin/device/list.html', 'fa fa-hospital-o', 'devices', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '2');
INSERT INTO `s_modules` VALUES ('device-ad', '设备广告', 'admin/device-ad/list.html', 'fa fa-yelp', 'ad-manager', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '0');
INSERT INTO `s_modules` VALUES ('devicedist', '设备分配', 'admin/devicedist/list.html', 'fa fa-hospital-o', 'devices', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '3');
INSERT INTO `s_modules` VALUES ('devices', '设备管理', '', 'fa fa-connectdevelop', '-1', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":false,\"id\":\"D\",\"text\":\"删除\"}]', '3');
INSERT INTO `s_modules` VALUES ('dividend', '分佣管理', 'admin/dividend/list.html', 'fa fa-cny', 'marketing-manager', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '0');
INSERT INTO `s_modules` VALUES ('featurestatistics', '特征分析', 'admin/statistics/feature/list.html', 'fa fa-video-camera', 'datastatistics', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '3');
INSERT INTO `s_modules` VALUES ('goods', '服装管理', '', 'fa fa-yelp', '-1', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"}]', '2');
INSERT INTO `s_modules` VALUES ('goodsclass', '商品类别(接口)', '', '', 'goodsinfo', '商品类别模块，用于控制权限问题。请勿开启 【菜单可见】 功能', '1', '[{\"checked\":false,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '0');
INSERT INTO `s_modules` VALUES ('goodsinfo', '基本信息', 'admin/goodsinfo/list.html', 'fa fa-user-md', 'goods', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '0');
INSERT INTO `s_modules` VALUES ('goodsinfospec', '服装分配', 'admin/goodsinfospec/list.html', 'fa fa-hospital-o', 'goods', '服装分配', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '1');
INSERT INTO `s_modules` VALUES ('goodsorderinfo', '订单信息', 'admin/goodsorderinfo/list.html', 'fa fa-reorder', 'order-manager', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '0');
INSERT INTO `s_modules` VALUES ('marketing-manager', '营销管理', '', 'fa fa-cny', '-1', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"}]', '5');
INSERT INTO `s_modules` VALUES ('module', '菜单管理', 'admin/module/list.html', 'fa fa-list-alt', 'sys', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":false,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":false,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":false,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":false,\"id\":\"D\",\"text\":\"删除\"}]', '303');
INSERT INTO `s_modules` VALUES ('order-manager', '订单管理', '', 'fa fa-reorder', '-1', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"}]', '4');
INSERT INTO `s_modules` VALUES ('radiofrequency', '射频管理', 'admin/radiofrequency/list.html', 'fa fa-xing', 'devices', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '0');
INSERT INTO `s_modules` VALUES ('radiofrequency-dist', '射频分配', 'admin/radiofrequency-dist/list.html', 'fa fa-yelp', 'devices', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '1');
INSERT INTO `s_modules` VALUES ('role', '角色管理', 'admin/role/list.html', 'fa fa-users', 'sys', '初始数据', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":false,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":false,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":false,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":false,\"id\":\"D\",\"text\":\"删除\"}]', '302');
INSERT INTO `s_modules` VALUES ('salestatistics', '销量数据', 'admin/statistics/sales/list.html', 'fa fa-usd', 'datastatistics', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '0');
INSERT INTO `s_modules` VALUES ('shop', '店铺管理', 'admin/shop/list.html', 'fa fa-shopping-cart', 'base', '店铺管理', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '1');
INSERT INTO `s_modules` VALUES ('sys', '系统管理', '', 'fa fa-cog', '-1', '系统权限', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"}]', '0');
INSERT INTO `s_modules` VALUES ('sys-parent', '系统开发', '', 'fa fa-cog', '-1', '', '1', '[{\"checked\":false,\"id\":\"M\",\"text\":\"菜单可见\"}]', '99');
INSERT INTO `s_modules` VALUES ('teststatistics', '试衣统计', 'admin/statistics/test/list.html', 'fa fa-user', 'datastatistics', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '1');
INSERT INTO `s_modules` VALUES ('user', '用户管理', 'admin/user/list.html', 'fa fa-user', 'sys', '初始数据', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":false,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":false,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":false,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":false,\"id\":\"D\",\"text\":\"删除\"},{\"checked\":false,\"id\":\"enable\",\"text\":\"启用\"},{\"checked\":false,\"id\":\"disable\",\"text\":\"禁用\"}]', '301');
INSERT INTO `s_modules` VALUES ('userInfo', '会员管理', 'admin/userInfo/list.html', 'fa fa-users', 'base', '会员列表', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '0');
INSERT INTO `s_modules` VALUES ('video', '视频列表', 'admin/video/list.html', 'fa fa-video-camera', 'video-manager', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"},{\"checked\":true,\"id\":\"R\",\"text\":\"查询\"},{\"checked\":true,\"id\":\"C\",\"text\":\"新增\"},{\"checked\":true,\"id\":\"U\",\"text\":\"修改\"},{\"checked\":true,\"id\":\"D\",\"text\":\"删除\"}]', '0');
INSERT INTO `s_modules` VALUES ('video-manager', '视频管理', '', 'fa fa-file-video-o', '-1', '', '1', '[{\"checked\":true,\"id\":\"M\",\"text\":\"菜单可见\"}]', '8');

-- ----------------------------
-- Table structure for s_module_meta
-- ----------------------------
DROP TABLE IF EXISTS `s_module_meta`;
CREATE TABLE `s_module_meta` (
  `u_id` varchar(32) NOT NULL COMMENT 'uid',
  `key` varchar(256) NOT NULL COMMENT '名称',
  `module_id` varchar(32) NOT NULL COMMENT '模块id',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色id',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `meta` text COMMENT '内容',
  `status` decimal(4,0) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统模块配置';

-- ----------------------------
-- Records of s_module_meta
-- ----------------------------

-- ----------------------------
-- Table structure for s_oauth2_access
-- ----------------------------
DROP TABLE IF EXISTS `s_oauth2_access`;
CREATE TABLE `s_oauth2_access` (
  `u_id` varchar(32) NOT NULL COMMENT 'ID',
  `client_id` varchar(32) NOT NULL COMMENT '客户端ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `access_token` varchar(128) NOT NULL COMMENT '授权码',
  `refresh_token` varchar(128) NOT NULL COMMENT '授权更新码',
  `expire_in` decimal(32,0) NOT NULL COMMENT '有效期',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='OAuth2授权信息';

-- ----------------------------
-- Records of s_oauth2_access
-- ----------------------------

-- ----------------------------
-- Table structure for s_oauth2_client
-- ----------------------------
DROP TABLE IF EXISTS `s_oauth2_client`;
CREATE TABLE `s_oauth2_client` (
  `u_id` varchar(32) NOT NULL COMMENT 'ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `name` varchar(128) DEFAULT NULL COMMENT '客户端名称',
  `secret` varchar(128) DEFAULT NULL COMMENT '密钥',
  `comment` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='OAuth2客户端信息';

-- ----------------------------
-- Records of s_oauth2_client
-- ----------------------------

-- ----------------------------
-- Table structure for s_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `s_quartz_job`;
CREATE TABLE `s_quartz_job` (
  `u_id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '任务名称',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `cron` varchar(512) NOT NULL COMMENT 'cron表达式',
  `script` text NOT NULL COMMENT '执行脚本',
  `language` varchar(32) NOT NULL COMMENT '脚本语言',
  `enabled` decimal(4,0) DEFAULT NULL COMMENT '是否启用',
  `parameters` text COMMENT '启动参数',
  `type` decimal(4,0) DEFAULT NULL COMMENT '任务类型',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of s_quartz_job
-- ----------------------------

-- ----------------------------
-- Table structure for s_quartz_job_his
-- ----------------------------
DROP TABLE IF EXISTS `s_quartz_job_his`;
CREATE TABLE `s_quartz_job_his` (
  `u_id` varchar(32) NOT NULL COMMENT '主键',
  `job_id` varchar(32) NOT NULL COMMENT '任务id',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `result` text COMMENT '执行结果',
  `status` decimal(4,0) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务执行记录';

-- ----------------------------
-- Records of s_quartz_job_his
-- ----------------------------

-- ----------------------------
-- Table structure for s_query_plan
-- ----------------------------
DROP TABLE IF EXISTS `s_query_plan`;
CREATE TABLE `s_query_plan` (
  `u_id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(256) NOT NULL COMMENT '方案名称',
  `type` varchar(256) NOT NULL COMMENT '方案分类',
  `config` text COMMENT '方案配置',
  `sharing` decimal(4,0) DEFAULT NULL COMMENT '是否共享方案',
  `creator_id` varchar(32) NOT NULL COMMENT '创建人id',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='查询方案表';

-- ----------------------------
-- Records of s_query_plan
-- ----------------------------

-- ----------------------------
-- Table structure for s_resources
-- ----------------------------
DROP TABLE IF EXISTS `s_resources`;
CREATE TABLE `s_resources` (
  `u_id` varchar(32) NOT NULL COMMENT 'uid',
  `name` varchar(256) NOT NULL COMMENT '资源名称',
  `path` varchar(1024) NOT NULL COMMENT '路径',
  `type` varchar(256) NOT NULL COMMENT '类型',
  `md5` varchar(256) NOT NULL COMMENT 'md5校验值',
  `size` decimal(32,0) DEFAULT NULL COMMENT '资源大小',
  `status` decimal(4,0) DEFAULT NULL COMMENT '状态',
  `classified` varchar(32) DEFAULT NULL COMMENT '分类',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `creator_id` varchar(256) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of s_resources
-- ----------------------------
INSERT INTO `s_resources` VALUES ('08VEhR', '3.png', '/file/2017-08-08', 'file', '99bd40479523aef5d19adc5836e8358b', '200166', '1', null, '2017-08-08 15:19:08', 'YThv5x');
INSERT INTO `s_resources` VALUES ('100', 'ssdsfsd', '/file/2017-07-27', 'file', '7795ed31dbf4c1771856a91dcf4045f7', '45435', '1', null, '2017-07-27 14:32:55', 'noTTpW');
INSERT INTO `s_resources` VALUES ('10000', 'tupian', '/file/2017-08-08', 'file', '97c66f67bcec4b0cb0306c392b047451', '1', '1', null, '2017-08-08 14:41:50', 'noTTpW');
INSERT INTO `s_resources` VALUES ('10001', 'tupian', '/file/2017-08-08', 'file', 'eab3e43e7a56822e4acafa31f0da27a5', '2', '1', null, '2017-08-08 14:41:50', 'noTTpW');
INSERT INTO `s_resources` VALUES ('10002', 'tupian', '/file/2017-08-08', 'file', 'c4dee1aefc7a83cab8625ce52844532d', '23', '1', null, '2017-08-08 14:41:50', 'noTTpW');
INSERT INTO `s_resources` VALUES ('10003', 'tupian', '/file/2017-08-08', 'file', '7795ed31dbf4c1771856a91dcf4045f7', '4', '1', null, '2017-08-08 14:41:50', 'noTTpW');
INSERT INTO `s_resources` VALUES ('121212', '1', '/file/2017-07-27', 'file', '294daf43305c5e77cdd8664d80d131bf', '21', '1', null, '2017-07-27 14:32:55', 'noTTpW');
INSERT INTO `s_resources` VALUES ('131313', '2', '/file/2017-07-27', 'file', '294daf43305c5e77cdd8664d80d131bf', '21', '1', null, '2017-07-27 14:32:55', 'noTTpW');
INSERT INTO `s_resources` VALUES ('141414', '3', '/file/2017-07-27', 'file', '294daf43305c5e77cdd8664d80d131bf', '21', '1', null, '2017-07-27 14:32:55', 'noTTpW');
INSERT INTO `s_resources` VALUES ('15', '454', '/file/2017-07-27', 'file', '294daf43305c5e77cdd8664d80d131bf', null, '1', null, '2017-07-27 14:32:55', 'noTTpW');
INSERT INTO `s_resources` VALUES ('151515', '4', '/file/2017-07-27', 'file', '294daf43305c5e77cdd8664d80d131bf', '21', '1', null, '2017-07-27 14:32:55', 'noTTpW');
INSERT INTO `s_resources` VALUES ('161616', '5', '/file/2017-07-27', 'file', '294daf43305c5e77cdd8664d80d131bf', '21', '1', null, '2017-07-27 14:32:55', 'noTTpW');
INSERT INTO `s_resources` VALUES ('1EANY6', '018r.jpg', '/file/2017-07-25', 'file', 'b4680c62ce0e6ac4da57982eb7df3d9b', '725787', '1', null, '2017-07-25 15:41:53', 'noTTpW');
INSERT INTO `s_resources` VALUES ('1Twgre', '17.jpg', '/file/2017-08-08', 'file', '2340278bae394a349b8c696b90245390', '75446', '1', null, '2017-08-08 16:07:08', 'noTTpW');
INSERT INTO `s_resources` VALUES ('1vCGx7', '4.png', '/file/2017-08-09', 'file', 'c2f8a064007c580a8d8b8466f1193e38', '177230', '1', null, '2017-08-09 14:19:17', 'YThv5x');
INSERT INTO `s_resources` VALUES ('222222', ' 试衣服', '', 'file', 'a56c189e9d18fea2b758c2854cf83413', null, null, null, '0000-00-00 00:00:00', '111111');
INSERT INTO `s_resources` VALUES ('333333', '20170719175543.jpg', '/file/2017-07-20', 'file', 'e38f492f0a10c91c0a6a457652104427', '1740', '1', null, '2017-07-20 14:39:16', '111111');
INSERT INTO `s_resources` VALUES ('3qMFKP', 'p_large_4RuO_1ec100005ced2d12.jpg', '/file/2017-08-08', 'file', '9c762fe9c5fa856068d6ef2f49222c5a', '24346', '1', null, '2017-08-08 11:33:20', 'noTTpW');
INSERT INTO `s_resources` VALUES ('666666', '试衣服', '/file/2017-07-20', 'file', 'e38f492f0a10c91c0a6a457652104427', null, '1', null, '2017-07-20 14:39:16', '');
INSERT INTO `s_resources` VALUES ('6Jm9tY', 'zxwl.g.json', '/file/2017-07-19', 'file', '8959f36b9e4c275dd9a27a2f268c2bcb', '14453', '1', null, '2017-07-19 02:25:43', 'YThv5x');
INSERT INTO `s_resources` VALUES ('70', 'e2ee', '/file/2017-07-27', 'file', '294daf43305c5e77cdd8664d80d131bf', null, '1', null, '2017-07-27 14:32:55', '');
INSERT INTO `s_resources` VALUES ('777777', 'dddsas', '/file/2017-07-27', 'file', '294daf43305c5e77cdd8664d80d131bf', null, '1', null, '0000-00-00 00:00:00', '111111');
INSERT INTO `s_resources` VALUES ('7pseHA', '022.jpg', '/file/2017-07-25', 'file', 'a56c189e9d18fea2b758c2854cf83413', '187940', '1', null, '2017-07-25 15:42:09', 'noTTpW');
INSERT INTO `s_resources` VALUES ('80', 'qweqw', '/file/2017-07-27', 'file', '294daf43305c5e77cdd8664d80d131bf', null, '1', null, '2017-07-27 14:32:55', 'noTTpW');
INSERT INTO `s_resources` VALUES ('888888', 'sdfe', '/file/2017-07-27', 'file', '294daf43305c5e77cdd8664d80d131bf', null, '1', null, '0000-00-00 00:00:00', '111111');
INSERT INTO `s_resources` VALUES ('90', 'weqq', '/file/2017-07-27', 'file', '294daf43305c5e77cdd8664d80d131bf', null, '1', null, '2017-07-27 14:32:55', 'noTTpW');
INSERT INTO `s_resources` VALUES ('9090', 'dcdscds', '/file/2017-08-08', 'file', '97c66f67bcec4b0cb0306c392b0474sd', '3422', '1', null, '2017-08-08 14:41:50', '');
INSERT INTO `s_resources` VALUES ('999999', 'werwe', '/file/2017-07-27', 'file', '294daf43305c5e77cdd8664d80d131bf', null, '1', null, '0000-00-00 00:00:00', '');
INSERT INTO `s_resources` VALUES ('9autw9', 'zxwl.g.json', '/file/2017-07-12', 'file', '021123837e500d6b9b565a5c9444e205', '14139', '1', null, '2017-07-12 10:00:15', 'admin');
INSERT INTO `s_resources` VALUES ('9bxSaZ', 'zxwl.g.json', '/file/2017-07-12', 'file', '8f1873ae9b889c8a06d5b882a581ba55', '14150', '1', null, '2017-07-12 08:58:39', 'YThv5x');
INSERT INTO `s_resources` VALUES ('b6c9AG', '2006110207162226183.jpg', '/file/2017-07-27', 'file', 'bd7c526d53af29341fcce121f1406cc8', '413902', '1', null, '2017-07-27 14:32:55', 'noTTpW');
INSERT INTO `s_resources` VALUES ('Db4Iod', '069cq.jpg', '/file/2017-07-25', 'file', 'b9e8f6124f3ec46ad56e21c8f7f48693', '193255', '1', null, '2017-07-25 15:42:19', 'noTTpW');
INSERT INTO `s_resources` VALUES ('eeeeeee', 'wewewe', '/file/2017-07-27', 'file', '0c7055e09b7f08e7feea7a0de9fb813c', '215199', '1', null, '2017-07-27 14:32:55', 'admin');
INSERT INTO `s_resources` VALUES ('FXpCgQ', '107.jpg', '/file/2017-07-27', 'file', '06685cf2621fea377ad8602d615f7bcd', '112645', '1', null, '2017-07-27 14:32:21', 'noTTpW');
INSERT INTO `s_resources` VALUES ('g9mGXt', 'p_large_1VOa_1a55000161212d14.jpg', '/file/2017-08-08', 'file', 'ad5b849404833b5f2d7f4e2ceab866fa', '19227', '1', null, '2017-08-08 14:33:55', 'noTTpW');
INSERT INTO `s_resources` VALUES ('HdmWvc', '._u2.png', '/file/2017-08-10', 'file', '9b08a74e437324b3b4af5c77cf85dc6f', '172', '1', null, '2017-08-10 10:09:31', 'RtLs8E');
INSERT INTO `s_resources` VALUES ('HGSIGL', 'p_large_abqY_1ec100005cf52d12.jpg', '/file/2017-08-08', 'file', '53eaf3ee43ab20bf4629632bb9603302', '21125', '1', null, '2017-08-08 11:34:45', 'noTTpW');
INSERT INTO `s_resources` VALUES ('hZ0h5E', 'p_large_1sls_47440002565f2d11.jpg', '/file/2017-08-08', 'file', '3f5ec5d3c91a0ed680c995bc7473c9ab', '24775', '1', null, '2017-08-08 11:34:20', 'noTTpW');
INSERT INTO `s_resources` VALUES ('I32OzH', '20170719175543.jpg', '/file/2017-07-20', 'file', '83881d954424fefb3c2da7a016a69da8', '1740', '1', null, '2017-07-20 15:45:33', 'YThv5x');
INSERT INTO `s_resources` VALUES ('I9y07l', 'zxwl.g.json', '/file/2017-07-21', 'file', '0c7055e09b7f08e7feea7a0de9fb813c', '14458', '1', null, '2017-07-21 01:58:33', 'admin');
INSERT INTO `s_resources` VALUES ('KkEO2V', 'large_0wsG_3252d132095.jpg', '/file/2017-07-28', 'file', 'edc67506c91fbce5e6ad25470453fa5b', '76241', '1', null, '2017-07-28 17:17:48', 'noTTpW');
INSERT INTO `s_resources` VALUES ('mgpkRA', '镜子1.png', '/file/2017-07-20', 'file', 'e38f492f0a10c91c0a6a457652104427', '508831', '1', null, '2017-07-20 14:39:16', 'YThv5x');
INSERT INTO `s_resources` VALUES ('mLjMpR', '98.jpg', '/file/2017-07-25', 'file', '4998edaceeedb77e8dbcf59271f00f3c', '123009', '1', null, '2017-07-25 15:42:19', 'noTTpW');
INSERT INTO `s_resources` VALUES ('MpYtTs', '00001.JPG', '/file/2017-08-09', 'file', 'd53904f43bb237bc09bb98854bb4592c', '1515934', '1', null, '2017-08-09 10:15:48', 'noTTpW');
INSERT INTO `s_resources` VALUES ('N83Wpt', '2.png', '/file/2017-08-08', 'file', '2d1211c74b193b5f180e385b91b5d777', '174750', '1', null, '2017-08-08 15:18:12', 'YThv5x');
INSERT INTO `s_resources` VALUES ('NL5NPt', 'TB2NcJ_gVXXXXbJXXXXXXXXXXXX_!!286774869 _1_.jpg', '/file/2017-08-03', 'file', '7bc2b2591118149bf8b32411d99b586f', '494356', '1', null, '2017-08-03 15:36:53', 'YThv5x');
INSERT INTO `s_resources` VALUES ('o4it6L', '2031076634.jpg', '/file/2017-07-27', 'file', '64d45a91547e2dd479174bd868f4ad6c', '906864', '1', null, '2017-07-27 14:32:55', 'noTTpW');
INSERT INTO `s_resources` VALUES ('PKz776', '1.png', '/file/2017-08-08', 'file', '784bc767f095a6096ece72aa9c4cb883', '132574', '1', null, '2017-08-08 14:50:12', 'YThv5x');
INSERT INTO `s_resources` VALUES ('qp5CWb', '演示模板.g.json', '/file/2017-07-12', 'file', 'db4d3afc103f3f712659e89fd4e2bdb3', '28746', '1', null, '2017-07-12 14:33:52', 'admin');
INSERT INTO `s_resources` VALUES ('rEupX5', '33_10015_056fc72b53a7e35.jpg', '/file/2017-08-08', 'file', 'a2e897bb12d7ebba2509be6f5896cc2a', '94090', '1', null, '2017-08-08 16:14:19', 'noTTpW');
INSERT INTO `s_resources` VALUES ('RlVmDd', '15172503.jpg', '/file/2017-07-27', 'file', '6c36b1893eb5f1cb2d77e2dda9921ad6', '578526', '1', null, '2017-07-27 14:32:41', 'noTTpW');
INSERT INTO `s_resources` VALUES ('TKvi0V', '演示模板.g (1).json', '/file/2017-07-12', 'file', '9c1bac93e70c36418de7bc29737e6620', '22481', '1', null, '2017-07-12 14:33:19', 'admin');
INSERT INTO `s_resources` VALUES ('uKXdyn', '5.png', '/file/2017-08-09', 'file', 'f4b69af0b284e4c5ac05b6b9976843ca', '193183', '1', null, '2017-08-09 14:19:17', 'YThv5x');
INSERT INTO `s_resources` VALUES ('USnoEN', '048bv.jpg', '/file/2017-07-25', 'file', '9bcd982cb7ff39b857228cc9d2b448d3', '193929', '1', null, '2017-07-25 15:42:19', 'noTTpW');
INSERT INTO `s_resources` VALUES ('usx41e', '00034.JPG', '/file/2017-08-08', 'file', '39b0148e69cedc34218b67b8067658fc', '215067', '1', null, '2017-08-08 16:12:38', 'noTTpW');
INSERT INTO `s_resources` VALUES ('vTsAbh', 'e', '/file/2017-07-12', 'file', '7795ed31dbf4c1771856a91dcf4045f7', '14388', '1', null, '2017-07-12 15:39:34', 'admin');
INSERT INTO `s_resources` VALUES ('VYk8ZS', '2006110207161426174.jpg', '/file/2017-07-27', 'file', '294daf43305c5e77cdd8664d80d131bf', '215199', '1', null, '2017-07-27 14:32:55', 'noTTpW');
INSERT INTO `s_resources` VALUES ('WGoDBw', 'image.jpg', '/file/2017-08-11', 'file', '56220dfca4aedacd82db754d840b1a3c', '20596', '1', null, '2017-08-11 07:06:30', 't8081Q');
INSERT INTO `s_resources` VALUES ('WHmz9q', 'p_large_8I6H_2d1d000254482d0f.jpg', '/file/2017-08-08', 'file', 'c4dee1aefc7a83cab8625ce52844532d', '18265', '1', null, '2017-08-08 14:36:19', 'noTTpW');
INSERT INTO `s_resources` VALUES ('wXUrId', '00004.JPG', '/file/2017-08-08', 'file', 'eab3e43e7a56822e4acafa31f0da27a5', '68746', '1', null, '2017-08-08 15:07:12', 'noTTpW');
INSERT INTO `s_resources` VALUES ('xUCQhg', '00005.JPG', '/file/2017-08-08', 'file', '97c66f67bcec4b0cb0306c392b047451', '110053', '1', null, '2017-08-08 14:41:50', 'noTTpW');

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `u_id` varchar(32) NOT NULL COMMENT 'uid',
  `name` varchar(256) NOT NULL COMMENT '角色名称',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('22test', '22测试', '', '22测试备注内容');
INSERT INTO `s_role` VALUES ('333test', '333测试', '', '333测试');
INSERT INTO `s_role` VALUES ('admin', '管理员', '', '管理员权限账户');
INSERT INTO `s_role` VALUES ('ship-owner', '船主', '1', '测试2');
INSERT INTO `s_role` VALUES ('test', '测试用户组', '', '测试用 用户组');
INSERT INTO `s_role` VALUES ('user', '普通用户组', '', '普通用户组');

-- ----------------------------
-- Table structure for s_role_modules
-- ----------------------------
DROP TABLE IF EXISTS `s_role_modules`;
CREATE TABLE `s_role_modules` (
  `u_id` varchar(32) NOT NULL COMMENT 'uid',
  `module_id` varchar(256) NOT NULL COMMENT '模块id',
  `role_id` varchar(256) NOT NULL COMMENT '角色id',
  `actions` text COMMENT '可操作权限',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色模块绑定表';

-- ----------------------------
-- Records of s_role_modules
-- ----------------------------
INSERT INTO `s_role_modules` VALUES ('0ksWme', 'userInfo', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('1SYWPO', 'resources', 'user', '[\"R\",\"C\",\"U\"]');
INSERT INTO `s_role_modules` VALUES ('1znL6w', 'shop', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('2MVtpI', 'activity', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('2wx3Hm', 'radiofrequency-dist', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('2yjj1S', 'goodsclass', 'admin', '[\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('36FOvJ', 'module-meta', 'user', '[\"M\",\"R\",\"C\",\"U\"]');
INSERT INTO `s_role_modules` VALUES ('3t6hG5', 'datasource', 'user', '[\"M\",\"import\",\"export\",\"R\",\"C\",\"U\"]');
INSERT INTO `s_role_modules` VALUES ('40dW0W', 'datasource', 'admin', '[\"import\",\"export\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('4hVumN', 'dividend', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('4mJB08', 'role', '33老师', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('4RAgCV', 'config', 'user', '[\"M\",\"R\",\"C\",\"U\"]');
INSERT INTO `s_role_modules` VALUES ('4RwVBQ', 'devices', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('5fgx4w', 'device-ad', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('5VS8yB', 'device', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('5W2pg9', 'user', '33老师', '[\"M\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('8tKEIJ', 'sys', '22test', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('AAefSJ', 'sys-parent', 'admin', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('acxGGM', 'module', '333test', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('aWg4Dy', 'role', '22test', '[\"M\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('AXO3Cm', 'user', '333测试', '[\"M\",\"R\"]');
INSERT INTO `s_role_modules` VALUES ('ayaeZc', 'module', 'admin', '[\"M\",\"C\",\"R\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('b9X6Zh', 'module', 'user', '[\"M\",\"R\"]');
INSERT INTO `s_role_modules` VALUES ('ch8y8m', 'role', '333测试', '[\"M\",\"R\"]');
INSERT INTO `s_role_modules` VALUES ('CPNLJf', 'devicedist', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('cziuXz', 'database', 'admin', '[\"drop\",\"comment\",\"create\",\"alter\",\"R\",\"select\",\"insert\",\"update\",\"delete\"]');
INSERT INTO `s_role_modules` VALUES ('dLKLmu', 'radiofrequency', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('FFx5W8', 'role', 'user', '[\"M\",\"R\"]');
INSERT INTO `s_role_modules` VALUES ('Fn8dXj', 'marketing-manager', 'admin', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('fOWvzS', 'role', 'test', '[\"M\",\"C\",\"R\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('fYk6ER', 'code-generator', 'admin', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('G2f1KA', 'base', 'admin', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('H6y7Ff', 'module', '22test', '[\"M\",\"C\",\"R\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('heLKBN', 'datastatistics', 'admin', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('hh5wgv', 'orderaddress', 'admin', '[\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('J9P9iB', 'sys', '333测试', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('jZCjyL', 'classified', 'user', '[\"R\",\"C\",\"U\"]');
INSERT INTO `s_role_modules` VALUES ('jZXenx', 'generator', 'user', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('k5c4ib', 'system-monitor', 'user', '[\"M\",\"R\",\"C\",\"U\"]');
INSERT INTO `s_role_modules` VALUES ('mmgKmt', 'query-plan', 'user', '[\"R\",\"C\",\"U\"]');
INSERT INTO `s_role_modules` VALUES ('mTdlKg', 'ad-manager', 'admin', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('MTlTxk', 'goodsorderinfo', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('MV8c14', 'oauth2-manager', '22test', '[\"enable\",\"disable\",\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('NJDtpa', 'module', '333测试', '[\"R\"]');
INSERT INTO `s_role_modules` VALUES ('nqPlrX', 'user', 'admin', '[\"M\",\"C\",\"R\",\"U\",\"D\",\"enable\",\"disable\"]');
INSERT INTO `s_role_modules` VALUES ('nv493J', 'user', '22test', '[\"M\",\"C\",\"R\",\"U\",\"D\",\"enable\",\"disable\"]');
INSERT INTO `s_role_modules` VALUES ('nWigkx', 'form', 'user', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('O74Ua6', 'sys', 'user', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('OhbhFV', 'quartz', 'user', '[\"M\",\"R\",\"C\",\"U\"]');
INSERT INTO `s_role_modules` VALUES ('oSw2gs', 'api', '33老师', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('qoQ22Z', 'goodsinfospec', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('QSznQW', 'module', 'test', '[\"M\",\"C\",\"R\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('qyeUt9', 'video', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('qzHVSP', 'monitor-cache', 'user', '[\"M\",\"R\",\"C\",\"U\"]');
INSERT INTO `s_role_modules` VALUES ('r5bGfH', 'role', 'admin', '[\"M\",\"C\",\"R\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('RdUY8t', 'user', 'user', '[\"M\",\"R\"]');
INSERT INTO `s_role_modules` VALUES ('sMQEY7', 'api', '22test', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('T65cbd', 'goods', 'admin', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('thIg2X', 'module', '33老师', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('TiQAjD', 'database', 'user', '[\"M\",\"select\",\"insert\",\"update\"]');
INSERT INTO `s_role_modules` VALUES ('tIZEBQ', 'ide', 'user', '[\"meta\"]');
INSERT INTO `s_role_modules` VALUES ('TKE07R', 'area', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('U0fafF', 'goodsinfo', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('uH5w6g', 'sys', 'test', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('USw57A', 'sys', 'admin', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('vd6Ye4', 'sys', '333test', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('WPGVwb', 'teststatistics', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('wqyf3D', 'undefined', 'admin', '[\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\",\"on\"]');
INSERT INTO `s_role_modules` VALUES ('X39jFE', 'user', '333test', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('XndOg5', 'role', '333test', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('Xw4WzT', 'user', 'test', '[\"M\",\"C\",\"R\",\"U\",\"D\",\"enable\",\"disable\"]');
INSERT INTO `s_role_modules` VALUES ('XXkA5U', 'sys-parent', 'test', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('YnbwgC', 'generator', 'test', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('ytmmPc', 'salestatistics', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('zb7cEe', 'oauth2-manager', '33老师', '[\"enable\",\"disable\",\"M\"]');
INSERT INTO `s_role_modules` VALUES ('ZdvpUI', 'featurestatistics', 'admin', '[\"M\",\"R\",\"C\",\"U\",\"D\"]');
INSERT INTO `s_role_modules` VALUES ('ZgZoyG', 'oauth2-manager', 'user', '[\"M\",\"R\",\"C\",\"U\"]');
INSERT INTO `s_role_modules` VALUES ('Zl2MSP', 'order-manager', 'admin', '[\"M\"]');
INSERT INTO `s_role_modules` VALUES ('ZyxkII', 'video-manager', 'admin', '[\"M\"]');

-- ----------------------------
-- Table structure for s_script
-- ----------------------------
DROP TABLE IF EXISTS `s_script`;
CREATE TABLE `s_script` (
  `u_id` varchar(32) NOT NULL COMMENT 'uid',
  `name` varchar(256) NOT NULL COMMENT '脚本名称',
  `classified_id` varchar(1024) NOT NULL COMMENT '路径',
  `type` varchar(256) NOT NULL COMMENT '类型',
  `content` text NOT NULL COMMENT '内容',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` decimal(4,0) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='脚本';

-- ----------------------------
-- Records of s_script
-- ----------------------------

-- ----------------------------
-- Table structure for s_system
-- ----------------------------
DROP TABLE IF EXISTS `s_system`;
CREATE TABLE `s_system` (
  `name` varchar(128) NOT NULL COMMENT '系统名称',
  `major_version` decimal(32,0) NOT NULL COMMENT '主版本号',
  `minor_version` decimal(32,0) NOT NULL COMMENT '次版本号',
  `revision_version` decimal(32,0) NOT NULL COMMENT '修订版',
  `snapshot` decimal(1,0) NOT NULL COMMENT '是否快照版',
  `comment` varchar(2000) DEFAULT NULL COMMENT '系统说明',
  `website` varchar(2000) DEFAULT NULL COMMENT '系统网址',
  `framework_version` text NOT NULL COMMENT '框架版本'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统信息';

-- ----------------------------
-- Records of s_system
-- ----------------------------
INSERT INTO `s_system` VALUES ('base-program', '2', '2', '0', '0', '测试', null, '{\"@type\":\"com.zxwl.web.starter.SystemVersion$FrameworkVersion\",\"comment\":\"\",\"majorVersion\":2,\"minorVersion\":2,\"name\":\"hsweb framework\",\"revisionVersion\":1,\"snapshot\":false,\"website\":\"http://www.hsweb.me\"}');

-- ----------------------------
-- Table structure for s_template
-- ----------------------------
DROP TABLE IF EXISTS `s_template`;
CREATE TABLE `s_template` (
  `u_id` varchar(32) NOT NULL COMMENT 'uid',
  `name` varchar(256) NOT NULL COMMENT '名称',
  `template` text COMMENT '模板',
  `classified_id` varchar(32) DEFAULT NULL COMMENT '分类',
  `type` varchar(64) DEFAULT NULL COMMENT '类型',
  `script` text COMMENT '脚本',
  `css` text COMMENT '样式',
  `css_links` text COMMENT '样式链接',
  `script_links` text COMMENT '脚本链接',
  `version` decimal(32,0) DEFAULT NULL COMMENT '版本',
  `revision` decimal(32,0) DEFAULT NULL COMMENT '修订版',
  `release` decimal(32,0) DEFAULT NULL COMMENT '当前发布版本',
  `using` decimal(4,0) DEFAULT NULL COMMENT '是否使用中',
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板';

-- ----------------------------
-- Records of s_template
-- ----------------------------

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `u_id` varchar(32) NOT NULL COMMENT 'id',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `email` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `status` decimal(4,0) DEFAULT NULL COMMENT '状态',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('111111', 'xianghugui', 'c522ce325171ece85a1ead27cc443839', 'xianghugui', 'xianghugui@gmail.com', '18850581087', '1', '2017-06-30 09:23:23', '2017-06-30 09:23:23');
INSERT INTO `s_user` VALUES ('4zMhla', 'david', '45841765f20f2a9a2334b791f4ce53d2', '管理员', '123@qq.com', '18675951388', '1', '2017-06-15 08:38:09', '2017-06-15 17:48:34');
INSERT INTO `s_user` VALUES ('admin', 'admin', '23ec59e119da971084cbd0ba72d230a0', '超级管理员', 'admin@qqc.om', '138000000000', '1', '2017-06-14 17:40:23', '2017-06-15 13:35:08');
INSERT INTO `s_user` VALUES ('bGtaVN', 'sdfsdfdsf', '167ed72a4ef558f976ef568e404f4afb', '', '', '', '1', '2017-06-15 14:32:09', '2017-06-15 14:32:09');
INSERT INTO `s_user` VALUES ('CrLV63', 'ff', '275f9cb6ab386fb237653bb1a896f436', '', '', '', '1', '2017-06-15 14:31:57', '2017-06-15 14:31:57');
INSERT INTO `s_user` VALUES ('dI5mXs', 'xhg', '49ef565bc8a7cddfccf9608f51898359', '船主人', 'qq@qq.com', '123123123', '1', '2017-06-15 12:50:57', '2017-07-13 16:34:52');
INSERT INTO `s_user` VALUES ('HEtYza', 'sfsdfsdf', '25db7ee7f9eab26da194dd149b0f646d', '', '', '', '1', '2017-06-15 14:32:03', '2017-06-15 14:32:03');
INSERT INTO `s_user` VALUES ('i8Xc2l', '18750222148', 'c522ce325171ece85a1ead27cc443839', '', '', '18750222148', '1', '2017-07-20 10:24:05', '2017-07-20 10:24:05');
INSERT INTO `s_user` VALUES ('IzbaXD', 'test1', '49ef565bc8a7cddfccf9608f51898359', 'sdf', '11@qq', '21321', '-1', '2017-06-15 14:31:26', '2017-06-15 14:31:26');
INSERT INTO `s_user` VALUES ('Nj5G2w', 'cecece', 'c522ce325171ece85a1ead27cc443839', '', '', '', '1', '2017-07-17 15:42:17', '2017-07-17 15:42:17');
INSERT INTO `s_user` VALUES ('noTTpW', 'wuei', '49ef565bc8a7cddfccf9608f51898359', '', '', '', '1', '2017-06-27 17:52:07', '2017-07-13 14:14:09');
INSERT INTO `s_user` VALUES ('oSnHIR', 'dsfsdfsdfdsf', '1c736d8c26a39db57d079808216361c3', '', '', '', '1', '2017-06-15 14:31:53', '2017-06-29 15:36:47');
INSERT INTO `s_user` VALUES ('RNbIxh', 'sdfsdf', 'd2336cf61720903c23835b1b57498249', '', '', '', '-1', '2017-06-15 14:31:47', '2017-06-15 17:47:59');
INSERT INTO `s_user` VALUES ('RtLs8E', 'zjf', 'c522ce325171ece85a1ead27cc443839', '', '', '', '1', '2017-07-24 16:52:27', '2017-07-24 17:05:25');
INSERT INTO `s_user` VALUES ('t8081Q', '666666', '1c916b00520225432f228a28d87b1996', '666666', '', '666666', '1', '2017-08-03 16:57:28', '2017-08-03 16:57:28');
INSERT INTO `s_user` VALUES ('U2ksfR', '17605080105', '8723953dfd5c11c37c5baab57ded9888', '', '', '17605080105', '1', '2017-08-09 15:27:07', '2017-08-09 15:27:07');
INSERT INTO `s_user` VALUES ('VEI4wN', 'sdf', 'd2336cf61720903c23835b1b57498249', 'sdf', '', '', '-1', '2017-06-15 14:31:37', '2017-06-15 14:31:37');
INSERT INTO `s_user` VALUES ('X8jygJ', 'wang', '49ef565bc8a7cddfccf9608f51898359', 'wang', '', '', '1', '2017-07-13 15:08:34', '2017-07-13 15:08:34');
INSERT INTO `s_user` VALUES ('ybmat0', 'apitest', 'c522ce325171ece85a1ead27cc443839', '', '', '12345678911', '1', '2017-08-07 15:35:06', '2017-08-07 15:35:06');
INSERT INTO `s_user` VALUES ('YThv5x', 'yll', '49ef565bc8a7cddfccf9608f51898359', 'Sendya', '18x@loacg.com', '18659290614', '1', '2017-06-15 09:46:08', '2017-07-13 10:52:46');
INSERT INTO `s_user` VALUES ('zqxLNk', 'tttttt', '49ef565bc8a7cddfccf9608f51898359', '', '', '', '1', '2017-07-17 15:37:32', '2017-07-17 15:37:32');

-- ----------------------------
-- Table structure for s_user_profile
-- ----------------------------
DROP TABLE IF EXISTS `s_user_profile`;
CREATE TABLE `s_user_profile` (
  `u_id` varchar(32) NOT NULL COMMENT 'uid',
  `content` text NOT NULL COMMENT '配置内容',
  `type` varchar(512) DEFAULT NULL COMMENT '类型',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户配置表';

-- ----------------------------
-- Records of s_user_profile
-- ----------------------------
INSERT INTO `s_user_profile` VALUES ('9a0ebeb1ec112f33f8b7ba195458c0c7', '[{\"id\":\"parent\",\"name\":\"zxwl\",\"icon\":\"icon-application\",\"type\":\"dir\",\"config\":[{\"header\":\"column\",\"field\":\"column\",\"width\":\"50px\",\"headerAlign\":\"center\",\"align\":\"center\",\"_level\":0,\"visible\":true,\"allowResize\":true,\"allowMove\":true,\"allowSort\":false,\"allowDrag\":false,\"readOnly\":false,\"autoEscape\":false,\"enabled\":true,\"showCellTip\":true,\"valueFromSelect\":true,\"vtype\":\"\",\"inited\":true,\"_gridUID\":\"mini-52\",\"_rowIdField\":\"_id\",\"_index\":0,\"summaryValue\":\"\",\"renderer\":\"if(e.value){\\n\\te.value=e.value.toLowerCase();\\n  row.column=e.value;\\n}\\n\\nreturn e.value;\"},{\"header\":\"property\",\"field\":\"property\",\"width\":\"50px\",\"headerAlign\":\"center\",\"align\":\"center\",\"_level\":0,\"visible\":true,\"allowResize\":true,\"allowMove\":true,\"allowSort\":false,\"allowDrag\":false,\"readOnly\":false,\"autoEscape\":false,\"enabled\":true,\"showCellTip\":true,\"valueFromSelect\":true,\"vtype\":\"\",\"inited\":true,\"_gridUID\":\"mini-52\",\"_rowIdField\":\"_id\",\"_index\":1,\"summaryValue\":\"\",\"renderer\":\"\\nif(!e.value||e.value==\'\'){\\n\\tif(row.column&&row.column!=\'\'){\\n    \\t\\te.value=row.column.toLowerCase().replace(/\\\\_(\\\\w)/g, function(x){return x.slice(1).toUpperCase();});\\n      \\t\\trow.property=e.value;\\n    }\\n}\\nreturn e.value;\"},{\"header\":\"comment\",\"field\":\"comment\",\"width\":\"50px\"},{\"header\":\"dataType\",\"field\":\"dataType\",\"width\":\"50px\"},{\"header\":\"jdbcType\",\"field\":\"jdbcType\",\"width\":\"50px\",\"headerAlign\":\"center\",\"align\":\"center\",\"_level\":0,\"visible\":true,\"allowResize\":true,\"allowMove\":true,\"allowSort\":false,\"allowDrag\":false,\"readOnly\":false,\"autoEscape\":false,\"enabled\":true,\"showCellTip\":true,\"valueFromSelect\":true,\"vtype\":\"\",\"inited\":true,\"_gridUID\":\"mini-52\",\"_rowIdField\":\"_id\",\"_index\":3,\"summaryValue\":\"\",\"properties\":\"{\\n\\teditor:{type:\\\"combobox\\\",textField:\\\"id\\\",allowInput:true,data:\\n    [\\n    {id:\\\"INTEGER\\\"}\\n    ,{id:\\\"BIGINT\\\"}\\n    ,{id:\\\"DOUBLE\\\"}\\n    ,{id:\\\"NUMERIC\\\"}\\n    ,{id:\\\"DECIMAL\\\"}\\n    ,{id:\\\"CHAR\\\"}\\n    ,{id:\\\"VARCHAR\\\"}\\n    ,{id:\\\"DATE\\\"}\\n    ,{id:\\\"TIMESTAMP\\\"}\\n    ,{id:\\\"CLOB\\\"}\\n    ]\\n    }\\n}\",\"renderer\":\"if(!e.value&&row.dataType){\\n\\tvar dataTypeLow=row.dataType.toLowerCase();\\n    if(dataTypeLow.indexOf(\\\"varchar\\\")!=-1){\\n    \\te.value=\\\"VARCHAR\\\";\\n    }else if(dataTypeLow.indexOf(\\\"number\\\")!=-1){\\n    \\te.value=\\\"NUMERIC\\\";\\n    }else if(dataTypeLow.indexOf(\\\"int\\\")!=-1){\\n    \\te.value=\\\"INTEGER\\\";\\n    }else if(dataTypeLow.indexOf(\\\"clob\\\")!=-1){\\n    \\te.value=\\\"CLOB\\\";\\n    }else if(dataTypeLow.indexOf(\\\"date\\\")!=-1||dataTypeLow.indexOf(\\\"timestamp\\\")!=-1){\\n    \\te.value=\\\"TIMESTAMP\\\";\\n    }\\n  row.jdbcType=e.value;\\n}\\nreturn e.value;\"},{\"header\":\"javaType\",\"field\":\"javaType\",\"width\":\"50px\",\"headerAlign\":\"center\",\"align\":\"center\",\"_level\":0,\"visible\":true,\"allowResize\":true,\"allowMove\":true,\"allowSort\":false,\"allowDrag\":false,\"readOnly\":false,\"autoEscape\":false,\"enabled\":true,\"showCellTip\":true,\"valueFromSelect\":true,\"vtype\":\"\",\"inited\":true,\"_gridUID\":\"mini-52\",\"_rowIdField\":\"_id\",\"_index\":2,\"summaryValue\":\"\",\"properties\":\"{\\n\\teditor:{type:\\\"combobox\\\",textField:\\\"id\\\",allowInput:true,data:\\n    [\\n    {id:\\\"String\\\"}\\n    ,{id:\\\"int\\\"}\\n    ,{id:\\\"long\\\"}\\n    ,{id:\\\"double\\\"}\\n    ,{id:\\\"java.util.Date\\\"}\\n    ,{id:\\\"boolean\\\"}\\n    ,{id:\\\"short\\\"}\\n    ,{id:\\\"byte\\\"}\\n    ,{id:\\\"Inetger\\\"}\\n    ,{id:\\\"Long\\\"}\\n     ,{id:\\\"Double\\\"}\\n    ]\\n    }\\n}\",\"renderer\":\"if(!e.value&&row.dataType){\\n\\tvar dataTypeLow=row.dataType.toLowerCase();\\n    if(dataTypeLow.indexOf(\\\"varchar\\\")!=-1||dataTypeLow.indexOf(\\\"clob\\\")!=-1){\\n    \\te.value=\\\"String\\\";\\n    }else if(dataTypeLow.indexOf(\\\"number\\\")!=-1){\\n       if(dataTypeLow.indexOf(\\\",\\\")!=-1)\\n    \\te.value=\\\"double\\\";\\n      else\\n        e.value=\\\"long\\\";\\n    }else if(dataTypeLow.indexOf(\\\"int\\\")!=-1){\\n    \\te.value=\\\"int\\\";\\n    }else if(dataTypeLow.indexOf(\\\"date\\\")!=-1||dataTypeLow.indexOf(\\\"timestamp\\\")!=-1){\\n    \\te.value=\\\"java.util.Date\\\";\\n    }\\n  row.javaType=e.value;\\n}\\nreturn e.value;\"},{\"header\":\"不能为空\",\"field\":\"notNull\",\"width\":\"50px\",\"renderer\":\"if(!e.value){\\n  if(e.record.property==\'id\')\\n\\t\\te.value=\\\"true\\\";\\n  else e.value=\\\"false\\\";\\n  \\t\\te.record[\'notNull\']=e.value;\\n}\\nreturn e.value;\"},{\"header\":\"是否表格列\",\"field\":\"isTable\",\"width\":\"50px\",\"renderer\":\"if(!e.value){\\n\\t\\te.value=\\\"true\\\";\\n  \\t\\te.record.isTable=\\\"true\\\";\\n}\\nreturn e.value;\"},{\"header\":\"是否查询条件\",\"field\":\"condition\",\"width\":\"50px\",\"renderer\":\"if(!e.value){\\n  e.value=\\\"false\\\";\\n  e.record.condition=\\\"false\\\";\\n}\\nreturn e.value;\"}],\"children\":[{\"name\":\"zxwl-framework\",\"type\":\"dir\",\"fileName\":\"zxwl-framework\",\"children\":[{\"name\":\"ext-platform\",\"type\":\"dir\",\"fileName\":\"ext-platform\",\"replaceMod\":\"ignore\",\"children\":[{\"name\":\"src/main\",\"type\":\"dir\",\"fileName\":\"src/main\",\"children\":[{\"name\":\"java\",\"type\":\"dir\",\"fileName\":\"java\",\"children\":[{\"name\":\"${packageName}\",\"type\":\"dir\",\"fileName\":\"${packageName?replace(\'.\',\'/\')}\",\"children\":[{\"name\":\"controller\",\"type\":\"dir\",\"fileName\":\"controller\",\"children\":[{\"name\":\"Controller.ftl\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}Controller.java\",\"template\":\"package ${packageName}.controller;\\n\\nimport com.zxwl.web.core.logger.annotation.AccessLogger;\\nimport com.zxwl.web.core.authorize.annotation.Authorize;\\nimport com.zxwl.web.bean.common.QueryParam;\\nimport ${packageName}.bean.${beanName};\\nimport com.zxwl.web.controller.GenericController;\\nimport com.zxwl.web.core.message.ResponseMessage;\\nimport org.springframework.web.bind.annotation.RestController;\\nimport ${packageName}.service.${beanName}Service;\\n\\nimport org.springframework.web.bind.annotation.RequestMapping;\\n\\nimport javax.annotation.Resource;\\n\\n/**\\n * ${moduleComment!\'\'}控制器\\n * Created by generator\\n */\\n@RestController\\n@RequestMapping(value = \\\"/${requestMapping!module}\\\")\\n@AccessLogger(\\\"${moduleComment!module}\\\")\\n@Authorize(module = \\\"${module!\'\'}\\\")\\npublic class ${beanName}Controller extends GenericController<${beanName}, String> {\\n\\n    @Resource\\n    private  ${beanName}Service ${beanName?uncap_first}Service;\\n\\n    @Override\\n    public  ${beanName}Service getService() {\\n        return this.${beanName?uncap_first}Service;\\n    }\\n}\\n\",\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"},{\"name\":\"bean\",\"type\":\"dir\",\"fileName\":\"bean\",\"children\":[{\"name\":\"Bean\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}.java\",\"template\":\"package ${packageName}.bean;\\nimport com.zxwl.web.bean.po.GenericPo;\\n/**\\n* ${moduleComment!\'\'}\\n* Created by generator ${.now!\'\'}\\n*/\\npublic class ${beanName} extends GenericPo<String>{\\n<#list fields as field>\\n    <#if field.property!=\'id\'>\\n  \\t\\t//${field.comment!field.property}\\n        private ${field.javaType} ${field.property};\\n    </#if>\\n</#list>\\n\\n<#list fields as field>\\n    <#if field.property!=\'id\'>\\n        /**\\n        * 获取 ${field.comment!field.property}\\n        * @return ${field.javaType} ${field.comment!field.property}\\n        */\\n        public ${field.javaType} ${utils.getGetter(field.property,field.javaType)}(){\\n\\t\\t\\treturn this.${field.property};\\n        }\\n\\n        /**\\n        * 设置 ${field.comment!field.property}\\n        */\\n        public void ${utils.getSetter(field.property)}(${field.javaType} ${field.property}){\\n        \\tthis.${field.property}=${field.property};\\n        }\\n    </#if>\\n</#list>\\n      \\n      public interface Property extends GenericPo.Property{\\n    \\t\\t<#list fields as field>\\n            <#if field.property!=\'id\'>\\n                //${field.comment!field.property}\\n                 String ${field.property}=\\\"${field.property}\\\";\\n            </#if>\\n        </#list>\\n    \\t}\\n}\",\"replaceMod\":\"all\"}],\"replaceMod\":\"ignore\"},{\"name\":\"service\",\"type\":\"dir\",\"fileName\":\"service\",\"children\":[{\"name\":\"Service\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}Service.java\",\"template\":\"package ${packageName}.service;\\n\\nimport ${packageName}.bean.${beanName};\\nimport com.zxwl.web.service.GenericService;\\n\\n/**\\n * ${moduleComment!module} 服务类接口\\n * Created by generator\\n */\\npublic interface ${beanName}Service extends GenericService<${beanName}, String> {\\n\\n}\\n\",\"replaceMod\":\"ignore\"},{\"name\":\"impl\",\"type\":\"dir\",\"fileName\":\"impl\",\"children\":[{\"name\":\"ServiceImpl\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}ServiceImpl.java\",\"template\":\"package ${packageName}.service.impl;\\n\\nimport com.zxwl.web.bean.common.QueryParam;\\nimport ${packageName}.bean.${beanName};\\nimport ${packageName}.dao.${beanName}Mapper;\\nimport com.zxwl.web.service.impl.AbstractServiceImpl;\\nimport ${packageName}.service.${beanName}Service;\\nimport org.springframework.stereotype.Service;\\n\\nimport javax.annotation.Resource;\\n\\n/**\\n * ${moduleComment!module} 服务类实现\\n * Created by generator\\n */\\n@Service(\\\"${beanName?uncap_first}Service\\\")\\npublic class ${beanName}ServiceImpl extends AbstractServiceImpl<${beanName}, String> implements ${beanName}Service {\\n\\n    @Resource\\n    protected ${beanName}Mapper ${beanName?uncap_first}Mapper;\\n\\n    @Override\\n    protected ${beanName}Mapper getMapper() {\\n        return this.${beanName?uncap_first}Mapper;\\n    }\\n}\\n\",\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"},{\"name\":\"dao\",\"type\":\"dir\",\"fileName\":\"dao\",\"children\":[{\"name\":\"Mapper\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}Mapper.java\",\"template\":\"package ${packageName}.dao;\\n\\nimport com.zxwl.web.dao.GenericMapper;\\nimport ${packageName}.bean.${beanName};\\n\\n/**\\n* MyBatis ${moduleComment!module} 数据映射接口\\n* Created by generator \\n*/\\npublic interface ${beanName}Mapper extends GenericMapper<${beanName},String> {\\n\\n}\\n\",\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"},{\"name\":\"resources\",\"type\":\"dir\",\"fileName\":\"resources\",\"children\":[{\"name\":\"scripts/initialize\",\"type\":\"dir\",\"fileName\":\"scripts/initialize/\",\"children\":[{\"name\":\"initialize.groovy\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"initialize.groovy\",\"replaceMod\":\"append\",\"template\":\"//${moduleComment!\'\'}\\ndatabase.createOrAlter(\\\"${utils.toLowerCase(tableName)}\\\")\\n .addColumn().name(\\\"u_id\\\").alias(\\\"id\\\").comment(\\\"ID\\\").jdbcType(java.sql.JDBCType.VARCHAR).length(32).primaryKey().commit()\\n<#list  fields as field >\\n  <#if field.property!=\'id\'>\\n.addColumn().${utils.createSqlColumnBuilder(field)}.commit()\\n  </#if>\\n</#list>\\n .comment(\\\"${moduleComment!\'\'}\\\").commit();\\n\\ndef ${module}_module= [u_id: \'${module}\', name: \'${moduleComment}\', uri: \'admin/${module}/list.html\', icon: \'\', parent_id: \'-1\', remark: \'\', status: 1, optional: \'[{\\\"id\\\":\\\"M\\\",\\\"text\\\":\\\"菜单可见\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"import\\\",\\\"text\\\":\\\"导入excel\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"export\\\",\\\"text\\\":\\\"导出excel\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"R\\\",\\\"text\\\":\\\"查询\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"C\\\",\\\"text\\\":\\\"新增\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"U\\\",\\\"text\\\":\\\"修改\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"D\\\",\\\"text\\\":\\\"删除\\\",\\\"checked\\\":false}]\', sort_index: 1];\\ndatabase.getTable(\\\"s_modules\\\").createInsert().value(${module}_module).exec();\\n\"}],\"replaceMod\":\"ignore\"},{\"name\":\"${packageName}\",\"type\":\"dir\",\"fileName\":\"${packageName?replace(\'.\',\'/\')}\",\"children\":[{\"name\":\"dao\",\"type\":\"dir\",\"fileName\":\"dao\",\"children\":[{\"name\":\"Mapper\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}Mapper.xml\",\"replaceMod\":\"ignore\",\"template\":\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\" ?>\\n<!DOCTYPE mapper\\n        PUBLIC \\\"-//mybatis.org//DTD Mapper 3.0//EN\\\"\\n        \\\"http://www.mybatis.org/dtd/mybatis-3-mapper.dtd\\\">\\n<mapper namespace=\\\"${packageName}.dao.${beanName}Mapper\\\">\\n    <resultMap id=\\\"${beanName}ResultMap\\\" type=\\\"${packageName}.bean.${beanName}\\\">\\n        <id property=\\\"id\\\" column=\\\"u_id\\\" javaType=\\\"string\\\" jdbcType=\\\"VARCHAR\\\"/>\\n        <#list  fields as field >\\n        <#if field.property!=\'id\'>\\n            <result property=\\\"${field.property}\\\" column=\\\"${field.column}\\\" javaType=\\\"${field.javaType}\\\" jdbcType=\\\"${field.jdbcType}\\\"/>\\n        </#if>\\n    </#list>\\n    </resultMap>\\n\\n    <!--用于动态生成sql所需的配置-->\\n    <sql id=\\\"config\\\">\\n        <bind name=\\\"resultMapId\\\" value=\\\"\'${beanName}ResultMap\'\\\"/>\\n        <bind name=\\\"tableName\\\" value=\\\"\'${tableName}\'\\\"/>\\n    </sql>\\n    <insert id=\\\"insert\\\" parameterType=\\\"com.zxwl.web.bean.common.InsertParam\\\" >\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildInsertSql\\\"/>\\n    </insert>\\n\\n    <delete id=\\\"delete\\\" parameterType=\\\"com.zxwl.web.bean.common.DeleteParam\\\">\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildDeleteSql\\\"/>\\n    </delete>\\n\\n    <update id=\\\"update\\\" parameterType=\\\"com.zxwl.web.bean.common.UpdateParam\\\">\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildUpdateSql\\\"/>\\n    </update>\\n\\n    <select id=\\\"selectByPk\\\" parameterType=\\\"string\\\" resultMap=\\\"${beanName}ResultMap\\\">\\n        select * from ${tableName} where u_id=${r\'#{id}\'}\\n    </select>\\n\\n    <select id=\\\"select\\\" parameterType=\\\"com.zxwl.web.bean.common.QueryParam\\\" resultMap=\\\"${beanName}ResultMap\\\">\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildSelectSql\\\"/>\\n    </select>\\n\\n    <select id=\\\"total\\\" parameterType=\\\"com.zxwl.web.bean.common.QueryParam\\\" resultType=\\\"int\\\">\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildTotalSql\\\"/>\\n    </select>\\n</mapper>\\n\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}]}],\"replaceMod\":\"ignore\"}],\"vars\":[{\"name\":\"module\",\"value\":\"GoodsInfo\",\"comment\":\"模块名\"},{\"name\":\"beanName\",\"value\":\"GoodsInfo\",\"comment\":\"bean名\"},{\"name\":\"moduleComment\",\"value\":\"商品信息\",\"comment\":\"bean描述\"},{\"name\":\"tableName\",\"value\":\"t_goods_info\",\"comment\":\"数据库表名\"},{\"name\":\"packageName\",\"value\":\"com.zxwl.platform\",\"comment\":\"包名\"},{\"name\":\"dbType\",\"value\":\"mysql\",\"comment\":\"数据库类型\"},{\"name\":\"sourceDir\",\"value\":\"G:\\\\tmp\\\\\",\"comment\":\"开发环境源码目录\"}]}]', 'code-generator', 'X8jygJ');
INSERT INTO `s_user_profile` VALUES ('d4550fe0cdf8d6bd4444eefcf336cdfe', '[{\"id\":\"parent\",\"name\":\"zxwl\",\"icon\":\"icon-application\",\"type\":\"dir\",\"config\":[{\"header\":\"column\",\"field\":\"column\",\"width\":\"50px\",\"headerAlign\":\"center\",\"align\":\"center\",\"_level\":0,\"visible\":true,\"allowResize\":true,\"allowMove\":true,\"allowSort\":false,\"allowDrag\":false,\"readOnly\":false,\"autoEscape\":false,\"enabled\":true,\"showCellTip\":true,\"valueFromSelect\":true,\"vtype\":\"\",\"inited\":true,\"_gridUID\":\"mini-52\",\"_rowIdField\":\"_id\",\"_index\":0,\"summaryValue\":\"\",\"renderer\":\"if(e.value){\\n\\te.value=e.value.toLowerCase();\\n  row.column=e.value;\\n}\\n\\nreturn e.value;\"},{\"header\":\"property\",\"field\":\"property\",\"width\":\"50px\",\"headerAlign\":\"center\",\"align\":\"center\",\"_level\":0,\"visible\":true,\"allowResize\":true,\"allowMove\":true,\"allowSort\":false,\"allowDrag\":false,\"readOnly\":false,\"autoEscape\":false,\"enabled\":true,\"showCellTip\":true,\"valueFromSelect\":true,\"vtype\":\"\",\"inited\":true,\"_gridUID\":\"mini-52\",\"_rowIdField\":\"_id\",\"_index\":1,\"summaryValue\":\"\",\"renderer\":\"\\nif(!e.value||e.value==\'\'){\\n\\tif(row.column&&row.column!=\'\'){\\n    \\t\\te.value=row.column.toLowerCase().replace(/\\\\_(\\\\w)/g, function(x){return x.slice(1).toUpperCase();});\\n      \\t\\trow.property=e.value;\\n    }\\n}\\nreturn e.value;\"},{\"header\":\"comment\",\"field\":\"comment\",\"width\":\"50px\"},{\"header\":\"dataType\",\"field\":\"dataType\",\"width\":\"50px\"},{\"header\":\"jdbcType\",\"field\":\"jdbcType\",\"width\":\"50px\",\"headerAlign\":\"center\",\"align\":\"center\",\"_level\":0,\"visible\":true,\"allowResize\":true,\"allowMove\":true,\"allowSort\":false,\"allowDrag\":false,\"readOnly\":false,\"autoEscape\":false,\"enabled\":true,\"showCellTip\":true,\"valueFromSelect\":true,\"vtype\":\"\",\"inited\":true,\"_gridUID\":\"mini-52\",\"_rowIdField\":\"_id\",\"_index\":3,\"summaryValue\":\"\",\"properties\":\"{\\n\\teditor:{type:\\\"combobox\\\",textField:\\\"id\\\",allowInput:true,data:\\n    [\\n    {id:\\\"INTEGER\\\"}\\n    ,{id:\\\"BIGINT\\\"}\\n    ,{id:\\\"DOUBLE\\\"}\\n    ,{id:\\\"NUMERIC\\\"}\\n    ,{id:\\\"DECIMAL\\\"}\\n    ,{id:\\\"CHAR\\\"}\\n    ,{id:\\\"VARCHAR\\\"}\\n    ,{id:\\\"DATE\\\"}\\n    ,{id:\\\"TIMESTAMP\\\"}\\n    ,{id:\\\"CLOB\\\"}\\n    ]\\n    }\\n}\",\"renderer\":\"if(!e.value&&row.dataType){\\n\\tvar dataTypeLow=row.dataType.toLowerCase();\\n    if(dataTypeLow.indexOf(\\\"varchar\\\")!=-1){\\n    \\te.value=\\\"VARCHAR\\\";\\n    }else if(dataTypeLow.indexOf(\\\"number\\\")!=-1){\\n    \\te.value=\\\"NUMERIC\\\";\\n    }else if(dataTypeLow.indexOf(\\\"int\\\")!=-1){\\n    \\te.value=\\\"INTEGER\\\";\\n    }else if(dataTypeLow.indexOf(\\\"clob\\\")!=-1){\\n    \\te.value=\\\"CLOB\\\";\\n    }else if(dataTypeLow.indexOf(\\\"date\\\")!=-1||dataTypeLow.indexOf(\\\"timestamp\\\")!=-1){\\n    \\te.value=\\\"TIMESTAMP\\\";\\n    }\\n  row.jdbcType=e.value;\\n}\\nreturn e.value;\"},{\"header\":\"javaType\",\"field\":\"javaType\",\"width\":\"50px\",\"headerAlign\":\"center\",\"align\":\"center\",\"_level\":0,\"visible\":true,\"allowResize\":true,\"allowMove\":true,\"allowSort\":false,\"allowDrag\":false,\"readOnly\":false,\"autoEscape\":false,\"enabled\":true,\"showCellTip\":true,\"valueFromSelect\":true,\"vtype\":\"\",\"inited\":true,\"_gridUID\":\"mini-52\",\"_rowIdField\":\"_id\",\"_index\":2,\"summaryValue\":\"\",\"properties\":\"{\\n\\teditor:{type:\\\"combobox\\\",textField:\\\"id\\\",allowInput:true,data:\\n    [\\n    {id:\\\"String\\\"}\\n    ,{id:\\\"int\\\"}\\n    ,{id:\\\"long\\\"}\\n    ,{id:\\\"double\\\"}\\n    ,{id:\\\"java.util.Date\\\"}\\n    ,{id:\\\"boolean\\\"}\\n    ,{id:\\\"short\\\"}\\n    ,{id:\\\"byte\\\"}\\n    ,{id:\\\"Inetger\\\"}\\n    ,{id:\\\"Long\\\"}\\n     ,{id:\\\"Double\\\"}\\n    ]\\n    }\\n}\",\"renderer\":\"if(!e.value&&row.dataType){\\n\\tvar dataTypeLow=row.dataType.toLowerCase();\\n    if(dataTypeLow.indexOf(\\\"varchar\\\")!=-1||dataTypeLow.indexOf(\\\"clob\\\")!=-1){\\n    \\te.value=\\\"String\\\";\\n    }else if(dataTypeLow.indexOf(\\\"number\\\")!=-1){\\n       if(dataTypeLow.indexOf(\\\",\\\")!=-1)\\n    \\te.value=\\\"double\\\";\\n      else\\n        e.value=\\\"long\\\";\\n    }else if(dataTypeLow.indexOf(\\\"int\\\")!=-1){\\n    \\te.value=\\\"int\\\";\\n    }else if(dataTypeLow.indexOf(\\\"date\\\")!=-1||dataTypeLow.indexOf(\\\"timestamp\\\")!=-1){\\n    \\te.value=\\\"java.util.Date\\\";\\n    }\\n  row.javaType=e.value;\\n}\\nreturn e.value;\"},{\"header\":\"不能为空\",\"field\":\"notNull\",\"width\":\"50px\",\"renderer\":\"if(!e.value){\\n  if(e.record.property==\'id\')\\n\\t\\te.value=\\\"true\\\";\\n  else e.value=\\\"false\\\";\\n  \\t\\te.record[\'notNull\']=e.value;\\n}\\nreturn e.value;\"},{\"header\":\"是否表格列\",\"field\":\"isTable\",\"width\":\"50px\",\"renderer\":\"if(!e.value){\\n\\t\\te.value=\\\"true\\\";\\n  \\t\\te.record.isTable=\\\"true\\\";\\n}\\nreturn e.value;\"},{\"header\":\"是否查询条件\",\"field\":\"condition\",\"width\":\"50px\",\"renderer\":\"if(!e.value){\\n  e.value=\\\"false\\\";\\n  e.record.condition=\\\"false\\\";\\n}\\nreturn e.value;\"}],\"children\":[{\"name\":\"zxwl-framework\",\"type\":\"dir\",\"fileName\":\"zxwl-framework\",\"children\":[{\"name\":\"ext-platform\",\"type\":\"dir\",\"fileName\":\"ext-platform\",\"replaceMod\":\"ignore\",\"children\":[{\"name\":\"src/main\",\"type\":\"dir\",\"fileName\":\"src/main\",\"children\":[{\"name\":\"java\",\"type\":\"dir\",\"fileName\":\"java\",\"children\":[{\"name\":\"${packageName}\",\"type\":\"dir\",\"fileName\":\"${packageName?replace(\'.\',\'/\')}\",\"children\":[{\"name\":\"controller\",\"type\":\"dir\",\"fileName\":\"controller\",\"children\":[{\"name\":\"Controller.ftl\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}Controller.java\",\"template\":\"package ${packageName}.controller;\\n\\nimport com.zxwl.web.core.logger.annotation.AccessLogger;\\nimport com.zxwl.web.core.authorize.annotation.Authorize;\\nimport com.zxwl.web.bean.common.QueryParam;\\nimport ${packageName}.bean.${beanName};\\nimport com.zxwl.web.controller.GenericController;\\nimport com.zxwl.web.core.message.ResponseMessage;\\nimport org.springframework.web.bind.annotation.RestController;\\nimport ${packageName}.service.${beanName}Service;\\n\\nimport org.springframework.web.bind.annotation.RequestMapping;\\n\\nimport javax.annotation.Resource;\\n\\n/**\\n * ${moduleComment!\'\'}控制器\\n * Created by generator\\n */\\n@RestController\\n@RequestMapping(value = \\\"/${requestMapping!module}\\\")\\n@AccessLogger(\\\"${moduleComment!module}\\\")\\n@Authorize(module = \\\"${module!\'\'}\\\")\\npublic class ${beanName}Controller extends GenericController<${beanName}, String> {\\n\\n    @Resource\\n    private  ${beanName}Service ${beanName?uncap_first}Service;\\n\\n    @Override\\n    public  ${beanName}Service getService() {\\n        return this.${beanName?uncap_first}Service;\\n    }\\n}\\n\",\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"},{\"name\":\"bean\",\"type\":\"dir\",\"fileName\":\"bean\",\"children\":[{\"name\":\"Bean\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}.java\",\"template\":\"package ${packageName}.bean;\\nimport com.zxwl.web.bean.po.GenericPo;\\n/**\\n* ${moduleComment!\'\'}\\n* Created by generator ${.now!\'\'}\\n*/\\npublic class ${beanName} extends GenericPo<String>{\\n<#list fields as field>\\n    <#if field.property!=\'id\'>\\n  \\t\\t//${field.comment!field.property}\\n        private ${field.javaType} ${field.property};\\n    </#if>\\n</#list>\\n\\n<#list fields as field>\\n    <#if field.property!=\'id\'>\\n        /**\\n        * 获取 ${field.comment!field.property}\\n        * @return ${field.javaType} ${field.comment!field.property}\\n        */\\n        public ${field.javaType} ${utils.getGetter(field.property,field.javaType)}(){\\n\\t\\t\\treturn this.${field.property};\\n        }\\n\\n        /**\\n        * 设置 ${field.comment!field.property}\\n        */\\n        public void ${utils.getSetter(field.property)}(${field.javaType} ${field.property}){\\n        \\tthis.${field.property}=${field.property};\\n        }\\n    </#if>\\n</#list>\\n      \\n      public interface Property extends GenericPo.Property{\\n    \\t\\t<#list fields as field>\\n            <#if field.property!=\'id\'>\\n                //${field.comment!field.property}\\n                 String ${field.property}=\\\"${field.property}\\\";\\n            </#if>\\n        </#list>\\n    \\t}\\n}\",\"replaceMod\":\"all\"}],\"replaceMod\":\"ignore\"},{\"name\":\"service\",\"type\":\"dir\",\"fileName\":\"service\",\"children\":[{\"name\":\"Service\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}Service.java\",\"template\":\"package ${packageName}.service;\\n\\nimport ${packageName}.bean.${beanName};\\nimport com.zxwl.web.service.GenericService;\\n\\n/**\\n * ${moduleComment!module} 服务类接口\\n * Created by generator\\n */\\npublic interface ${beanName}Service extends GenericService<${beanName}, String> {\\n\\n}\\n\",\"replaceMod\":\"ignore\"},{\"name\":\"impl\",\"type\":\"dir\",\"fileName\":\"impl\",\"children\":[{\"name\":\"ServiceImpl\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}ServiceImpl.java\",\"template\":\"package ${packageName}.service.impl;\\n\\nimport com.zxwl.web.bean.common.QueryParam;\\nimport ${packageName}.bean.${beanName};\\nimport ${packageName}.dao.${beanName}Mapper;\\nimport com.zxwl.web.service.impl.AbstractServiceImpl;\\nimport ${packageName}.service.${beanName}Service;\\nimport org.springframework.stereotype.Service;\\n\\nimport javax.annotation.Resource;\\n\\n/**\\n * ${moduleComment!module} 服务类实现\\n * Created by generator\\n */\\n@Service(\\\"${beanName?uncap_first}Service\\\")\\npublic class ${beanName}ServiceImpl extends AbstractServiceImpl<${beanName}, String> implements ${beanName}Service {\\n\\n    @Resource\\n    protected ${beanName}Mapper ${beanName?uncap_first}Mapper;\\n\\n    @Override\\n    protected ${beanName}Mapper getMapper() {\\n        return this.${beanName?uncap_first}Mapper;\\n    }\\n  \\n    @Override\\n    public String insert(${beanName} data) {\\n        return super.insert(data);\\n    }\\n  \\n    @Override\\n    public String update(${beanName} data) {\\n        return super.update(data);\\n    }\\n  \\n    @Override\\n    public String update(List<${beanName}> data) {\\n        return super.update(data);\\n    }\\n}\\n\",\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"},{\"name\":\"dao\",\"type\":\"dir\",\"fileName\":\"dao\",\"children\":[{\"name\":\"Mapper\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}Mapper.java\",\"template\":\"package ${packageName}.dao;\\n\\nimport com.zxwl.web.dao.GenericMapper;\\nimport ${packageName}.bean.${beanName};\\n\\n/**\\n* MyBatis ${moduleComment!module} 数据映射接口\\n* Created by generator \\n*/\\npublic interface ${beanName}Mapper extends GenericMapper<${beanName},String> {\\n\\n}\\n\",\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"},{\"name\":\"resources\",\"type\":\"dir\",\"fileName\":\"resources\",\"children\":[{\"name\":\"scripts/initialize\",\"type\":\"dir\",\"fileName\":\"scripts/initialize/\",\"children\":[{\"name\":\"initialize.groovy\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"initialize.groovy\",\"replaceMod\":\"append\",\"template\":\"//${moduleComment!\'\'}\\ndatabase.createOrAlter(\\\"${utils.toLowerCase(tableName)}\\\")\\n .addColumn().name(\\\"u_id\\\").alias(\\\"id\\\").comment(\\\"ID\\\").jdbcType(java.sql.JDBCType.VARCHAR).length(32).primaryKey().commit()\\n<#list  fields as field >\\n  <#if field.property!=\'id\'>\\n.addColumn().${utils.createSqlColumnBuilder(field)}.commit()\\n  </#if>\\n</#list>\\n .comment(\\\"${moduleComment!\'\'}\\\").commit();\\n\\ndef ${module}_module= [u_id: \'${module}\', name: \'${moduleComment}\', uri: \'admin/${module}/list.html\', icon: \'\', parent_id: \'-1\', remark: \'\', status: 1, optional: \'[{\\\"id\\\":\\\"M\\\",\\\"text\\\":\\\"菜单可见\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"import\\\",\\\"text\\\":\\\"导入excel\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"export\\\",\\\"text\\\":\\\"导出excel\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"R\\\",\\\"text\\\":\\\"查询\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"C\\\",\\\"text\\\":\\\"新增\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"U\\\",\\\"text\\\":\\\"修改\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"D\\\",\\\"text\\\":\\\"删除\\\",\\\"checked\\\":false}]\', sort_index: 1];\\ndatabase.getTable(\\\"s_modules\\\").createInsert().value(${module}_module).exec();\\n\"}],\"replaceMod\":\"ignore\"},{\"name\":\"${packageName}\",\"type\":\"dir\",\"fileName\":\"${packageName?replace(\'.\',\'/\')}\",\"children\":[{\"name\":\"dao\",\"type\":\"dir\",\"fileName\":\"dao\",\"children\":[{\"name\":\"Mapper\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}Mapper.xml\",\"replaceMod\":\"ignore\",\"template\":\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\" ?>\\n<!DOCTYPE mapper\\n        PUBLIC \\\"-//mybatis.org//DTD Mapper 3.0//EN\\\"\\n        \\\"http://www.mybatis.org/dtd/mybatis-3-mapper.dtd\\\">\\n<mapper namespace=\\\"${packageName}.dao.${beanName}Mapper\\\">\\n    <resultMap id=\\\"${beanName}ResultMap\\\" type=\\\"${packageName}.bean.${beanName}\\\">\\n        <id property=\\\"id\\\" column=\\\"u_id\\\" javaType=\\\"string\\\" jdbcType=\\\"VARCHAR\\\"/>\\n        <#list  fields as field >\\n        <#if field.property!=\'id\'>\\n            <result property=\\\"${field.property}\\\" column=\\\"${field.column}\\\" javaType=\\\"${field.javaType}\\\" jdbcType=\\\"${field.jdbcType}\\\"/>\\n        </#if>\\n    </#list>\\n    </resultMap>\\n\\n    <!--用于动态生成sql所需的配置-->\\n    <sql id=\\\"config\\\">\\n        <bind name=\\\"resultMapId\\\" value=\\\"\'${beanName}ResultMap\'\\\"/>\\n        <bind name=\\\"tableName\\\" value=\\\"\'${tableName}\'\\\"/>\\n    </sql>\\n    <insert id=\\\"insert\\\" parameterType=\\\"com.zxwl.web.bean.common.InsertParam\\\" >\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildInsertSql\\\"/>\\n    </insert>\\n\\n    <delete id=\\\"delete\\\" parameterType=\\\"com.zxwl.web.bean.common.DeleteParam\\\">\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildDeleteSql\\\"/>\\n    </delete>\\n\\n    <update id=\\\"update\\\" parameterType=\\\"com.zxwl.web.bean.common.UpdateParam\\\">\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildUpdateSql\\\"/>\\n    </update>\\n\\n    <select id=\\\"selectByPk\\\" parameterType=\\\"string\\\" resultMap=\\\"${beanName}ResultMap\\\">\\n        select * from ${tableName} where u_id=${r\'#{id}\'}\\n    </select>\\n\\n    <select id=\\\"select\\\" parameterType=\\\"com.zxwl.web.bean.common.QueryParam\\\" resultMap=\\\"${beanName}ResultMap\\\">\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildSelectSql\\\"/>\\n    </select>\\n\\n    <select id=\\\"total\\\" parameterType=\\\"com.zxwl.web.bean.common.QueryParam\\\" resultType=\\\"int\\\">\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildTotalSql\\\"/>\\n    </select>\\n</mapper>\\n\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}]}],\"replaceMod\":\"ignore\"}],\"vars\":[{\"name\":\"module\",\"value\":\"Shop\",\"comment\":\"模块名\"},{\"name\":\"beanName\",\"value\":\"Shop\",\"comment\":\"bean名\"},{\"name\":\"moduleComment\",\"value\":\"店铺信息\",\"comment\":\"bean描述\"},{\"name\":\"tableName\",\"value\":\"t_user_info\",\"comment\":\"数据库表名\"},{\"name\":\"packageName\",\"value\":\"com.zxwl.platform\",\"comment\":\"包名\"},{\"name\":\"dbType\",\"value\":\"mysql\",\"comment\":\"数据库类型\"},{\"name\":\"sourceDir\",\"value\":\"G:\\\\tmp\\\\\",\"comment\":\"开发环境源码目录\"}]}]', 'code-generator', 'YThv5x');
INSERT INTO `s_user_profile` VALUES ('e9eb4491a2e636472b8260ed0e96d381', '[{\"id\":\"parent\",\"name\":\"zxwl\",\"icon\":\"icon-application\",\"type\":\"dir\",\"config\":[{\"header\":\"column\",\"field\":\"column\",\"width\":\"50px\",\"headerAlign\":\"center\",\"align\":\"center\",\"_level\":0,\"visible\":true,\"allowResize\":true,\"allowMove\":true,\"allowSort\":false,\"allowDrag\":false,\"readOnly\":false,\"autoEscape\":false,\"enabled\":true,\"showCellTip\":true,\"valueFromSelect\":true,\"vtype\":\"\",\"inited\":true,\"_gridUID\":\"mini-52\",\"_rowIdField\":\"_id\",\"_index\":0,\"summaryValue\":\"\",\"renderer\":\"if(e.value){\\n\\te.value=e.value.toLowerCase();\\n  row.column=e.value;\\n}\\n\\nreturn e.value;\"},{\"header\":\"property\",\"field\":\"property\",\"width\":\"50px\",\"headerAlign\":\"center\",\"align\":\"center\",\"_level\":0,\"visible\":true,\"allowResize\":true,\"allowMove\":true,\"allowSort\":false,\"allowDrag\":false,\"readOnly\":false,\"autoEscape\":false,\"enabled\":true,\"showCellTip\":true,\"valueFromSelect\":true,\"vtype\":\"\",\"inited\":true,\"_gridUID\":\"mini-52\",\"_rowIdField\":\"_id\",\"_index\":1,\"summaryValue\":\"\",\"renderer\":\"\\nif(!e.value||e.value==\'\'){\\n\\tif(row.column&&row.column!=\'\'){\\n    \\t\\te.value=row.column.toLowerCase().replace(/\\\\_(\\\\w)/g, function(x){return x.slice(1).toUpperCase();});\\n      \\t\\trow.property=e.value;\\n    }\\n}\\nreturn e.value;\"},{\"header\":\"comment\",\"field\":\"comment\",\"width\":\"50px\"},{\"header\":\"dataType\",\"field\":\"dataType\",\"width\":\"50px\"},{\"header\":\"jdbcType\",\"field\":\"jdbcType\",\"width\":\"50px\",\"headerAlign\":\"center\",\"align\":\"center\",\"_level\":0,\"visible\":true,\"allowResize\":true,\"allowMove\":true,\"allowSort\":false,\"allowDrag\":false,\"readOnly\":false,\"autoEscape\":false,\"enabled\":true,\"showCellTip\":true,\"valueFromSelect\":true,\"vtype\":\"\",\"inited\":true,\"_gridUID\":\"mini-52\",\"_rowIdField\":\"_id\",\"_index\":3,\"summaryValue\":\"\",\"properties\":\"{\\n\\teditor:{type:\\\"combobox\\\",textField:\\\"id\\\",allowInput:true,data:\\n    [\\n    {id:\\\"INTEGER\\\"}\\n    ,{id:\\\"BIGINT\\\"}\\n    ,{id:\\\"DOUBLE\\\"}\\n    ,{id:\\\"NUMERIC\\\"}\\n    ,{id:\\\"DECIMAL\\\"}\\n    ,{id:\\\"CHAR\\\"}\\n    ,{id:\\\"VARCHAR\\\"}\\n    ,{id:\\\"DATE\\\"}\\n    ,{id:\\\"TIMESTAMP\\\"}\\n    ,{id:\\\"CLOB\\\"}\\n    ]\\n    }\\n}\",\"renderer\":\"if(!e.value&&row.dataType){\\n\\tvar dataTypeLow=row.dataType.toLowerCase();\\n    if(dataTypeLow.indexOf(\\\"varchar\\\")!=-1){\\n    \\te.value=\\\"VARCHAR\\\";\\n    }else if(dataTypeLow.indexOf(\\\"number\\\")!=-1){\\n    \\te.value=\\\"NUMERIC\\\";\\n    }else if(dataTypeLow.indexOf(\\\"int\\\")!=-1){\\n    \\te.value=\\\"INTEGER\\\";\\n    }else if(dataTypeLow.indexOf(\\\"clob\\\")!=-1){\\n    \\te.value=\\\"CLOB\\\";\\n    }else if(dataTypeLow.indexOf(\\\"date\\\")!=-1||dataTypeLow.indexOf(\\\"timestamp\\\")!=-1){\\n    \\te.value=\\\"TIMESTAMP\\\";\\n    }\\n  row.jdbcType=e.value;\\n}\\nreturn e.value;\"},{\"header\":\"javaType\",\"field\":\"javaType\",\"width\":\"50px\",\"headerAlign\":\"center\",\"align\":\"center\",\"_level\":0,\"visible\":true,\"allowResize\":true,\"allowMove\":true,\"allowSort\":false,\"allowDrag\":false,\"readOnly\":false,\"autoEscape\":false,\"enabled\":true,\"showCellTip\":true,\"valueFromSelect\":true,\"vtype\":\"\",\"inited\":true,\"_gridUID\":\"mini-52\",\"_rowIdField\":\"_id\",\"_index\":2,\"summaryValue\":\"\",\"properties\":\"{\\n\\teditor:{type:\\\"combobox\\\",textField:\\\"id\\\",allowInput:true,data:\\n    [\\n    {id:\\\"String\\\"}\\n    ,{id:\\\"int\\\"}\\n    ,{id:\\\"long\\\"}\\n    ,{id:\\\"double\\\"}\\n    ,{id:\\\"java.util.Date\\\"}\\n    ,{id:\\\"boolean\\\"}\\n    ,{id:\\\"short\\\"}\\n    ,{id:\\\"byte\\\"}\\n    ,{id:\\\"Inetger\\\"}\\n    ,{id:\\\"Long\\\"}\\n     ,{id:\\\"Double\\\"}\\n    ]\\n    }\\n}\",\"renderer\":\"if(!e.value&&row.dataType){\\n\\tvar dataTypeLow=row.dataType.toLowerCase();\\n    if(dataTypeLow.indexOf(\\\"varchar\\\")!=-1||dataTypeLow.indexOf(\\\"clob\\\")!=-1){\\n    \\te.value=\\\"String\\\";\\n    }else if(dataTypeLow.indexOf(\\\"number\\\")!=-1){\\n       if(dataTypeLow.indexOf(\\\",\\\")!=-1)\\n    \\te.value=\\\"double\\\";\\n      else\\n        e.value=\\\"long\\\";\\n    }else if(dataTypeLow.indexOf(\\\"int\\\")!=-1){\\n    \\te.value=\\\"int\\\";\\n    }else if(dataTypeLow.indexOf(\\\"date\\\")!=-1||dataTypeLow.indexOf(\\\"timestamp\\\")!=-1){\\n    \\te.value=\\\"java.util.Date\\\";\\n    }\\n  row.javaType=e.value;\\n}\\nreturn e.value;\"},{\"header\":\"不能为空\",\"field\":\"notNull\",\"width\":\"50px\",\"renderer\":\"if(!e.value){\\n  if(e.record.property==\'id\')\\n\\t\\te.value=\\\"true\\\";\\n  else e.value=\\\"false\\\";\\n  \\t\\te.record[\'notNull\']=e.value;\\n}\\nreturn e.value;\"},{\"header\":\"是否表格列\",\"field\":\"isTable\",\"width\":\"50px\",\"renderer\":\"if(!e.value){\\n\\t\\te.value=\\\"true\\\";\\n  \\t\\te.record.isTable=\\\"true\\\";\\n}\\nreturn e.value;\"},{\"header\":\"是否查询条件\",\"field\":\"condition\",\"width\":\"50px\",\"renderer\":\"if(!e.value){\\n  e.value=\\\"false\\\";\\n  e.record.condition=\\\"false\\\";\\n}\\nreturn e.value;\"}],\"children\":[{\"name\":\"zxwl-framework\",\"type\":\"dir\",\"fileName\":\"zxwl-framework\",\"children\":[{\"name\":\"ext-platform\",\"type\":\"dir\",\"fileName\":\"ext-platform\",\"replaceMod\":\"ignore\",\"children\":[{\"name\":\"src/main\",\"type\":\"dir\",\"fileName\":\"src/main\",\"children\":[{\"name\":\"java\",\"type\":\"dir\",\"fileName\":\"java\",\"children\":[{\"name\":\"${packageName}\",\"type\":\"dir\",\"fileName\":\"${packageName?replace(\'.\',\'/\')}\",\"children\":[{\"name\":\"controller\",\"type\":\"dir\",\"fileName\":\"controller\",\"children\":[{\"name\":\"Controller.ftl\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}Controller.java\",\"template\":\"package ${packageName}.controller;\\n\\nimport com.zxwl.web.core.logger.annotation.AccessLogger;\\nimport com.zxwl.web.core.authorize.annotation.Authorize;\\nimport com.zxwl.web.bean.common.QueryParam;\\nimport ${packageName}.bean.${beanName};\\nimport com.zxwl.web.controller.GenericController;\\nimport com.zxwl.web.core.message.ResponseMessage;\\nimport org.springframework.web.bind.annotation.RestController;\\nimport ${packageName}.service.${beanName}Service;\\n\\nimport org.springframework.web.bind.annotation.RequestMapping;\\n\\nimport javax.annotation.Resource;\\n\\n/**\\n * ${moduleComment!\'\'}控制器\\n * Created by generator\\n */\\n@RestController\\n@RequestMapping(value = \\\"/${requestMapping!module}\\\")\\n@AccessLogger(\\\"${moduleComment!module}\\\")\\n@Authorize(module = \\\"${module!\'\'}\\\")\\npublic class ${beanName}Controller extends GenericController<${beanName}, String> {\\n\\n    @Resource\\n    private  ${beanName}Service ${beanName?uncap_first}Service;\\n\\n    @Override\\n    public  ${beanName}Service getService() {\\n        return this.${beanName?uncap_first}Service;\\n    }\\n}\\n\",\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"},{\"name\":\"bean\",\"type\":\"dir\",\"fileName\":\"bean\",\"children\":[{\"name\":\"Bean\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}.java\",\"template\":\"package ${packageName}.bean;\\nimport com.zxwl.web.bean.po.GenericPo;\\n/**\\n* ${moduleComment!\'\'}\\n* Created by generator ${.now!\'\'}\\n*/\\npublic class ${beanName} extends GenericPo<String>{\\n<#list fields as field>\\n    <#if field.property!=\'id\'>\\n  \\t\\t//${field.comment!field.property}\\n        private ${field.javaType} ${field.property};\\n    </#if>\\n</#list>\\n\\n<#list fields as field>\\n    <#if field.property!=\'id\'>\\n        /**\\n        * 获取 ${field.comment!field.property}\\n        * @return ${field.javaType} ${field.comment!field.property}\\n        */\\n        public ${field.javaType} ${utils.getGetter(field.property,field.javaType)}(){\\n\\t\\t\\treturn this.${field.property};\\n        }\\n\\n        /**\\n        * 设置 ${field.comment!field.property}\\n        */\\n        public void ${utils.getSetter(field.property)}(${field.javaType} ${field.property}){\\n        \\tthis.${field.property}=${field.property};\\n        }\\n    </#if>\\n</#list>\\n      \\n      public interface Property extends GenericPo.Property{\\n    \\t\\t<#list fields as field>\\n            <#if field.property!=\'id\'>\\n                //${field.comment!field.property}\\n                 String ${field.property}=\\\"${field.property}\\\";\\n            </#if>\\n        </#list>\\n    \\t}\\n}\",\"replaceMod\":\"all\"}],\"replaceMod\":\"ignore\"},{\"name\":\"service\",\"type\":\"dir\",\"fileName\":\"service\",\"children\":[{\"name\":\"Service\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}Service.java\",\"template\":\"package ${packageName}.service;\\n\\nimport ${packageName}.bean.${beanName};\\nimport com.zxwl.web.service.GenericService;\\n\\n/**\\n * ${moduleComment!module} 服务类接口\\n * Created by generator\\n */\\npublic interface ${beanName}Service extends GenericService<${beanName}, String> {\\n\\n}\\n\",\"replaceMod\":\"ignore\"},{\"name\":\"impl\",\"type\":\"dir\",\"fileName\":\"impl\",\"children\":[{\"name\":\"ServiceImpl\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}ServiceImpl.java\",\"template\":\"package ${packageName}.service.impl;\\n\\nimport com.zxwl.web.bean.common.QueryParam;\\nimport ${packageName}.bean.${beanName};\\nimport ${packageName}.dao.${beanName}Mapper;\\nimport com.zxwl.web.service.impl.AbstractServiceImpl;\\nimport ${packageName}.service.${beanName}Service;\\nimport org.springframework.stereotype.Service;\\n\\nimport javax.annotation.Resource;\\n\\n/**\\n * ${moduleComment!module} 服务类实现\\n * Created by generator\\n */\\n@Service(\\\"${beanName?uncap_first}Service\\\")\\npublic class ${beanName}ServiceImpl extends AbstractServiceImpl<${beanName}, String> implements ${beanName}Service {\\n\\n    @Resource\\n    protected ${beanName}Mapper ${beanName?uncap_first}Mapper;\\n\\n    @Override\\n    protected ${beanName}Mapper getMapper() {\\n        return this.${beanName?uncap_first}Mapper;\\n    }\\n  \\n    @Override\\n    public String insert(${beanName} data) {\\n        return super.insert(data);\\n    }\\n  \\n    @Override\\n    public String update(${beanName} data) {\\n        return super.update(data);\\n    }\\n  \\n    @Override\\n    public String update(List<${beanName}> data) {\\n        return super.update(data);\\n    }\\n}\\n\",\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"},{\"name\":\"dao\",\"type\":\"dir\",\"fileName\":\"dao\",\"children\":[{\"name\":\"Mapper\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}Mapper.java\",\"template\":\"package ${packageName}.dao;\\n\\nimport com.zxwl.web.dao.GenericMapper;\\nimport ${packageName}.bean.${beanName};\\n\\n/**\\n* MyBatis ${moduleComment!module} 数据映射接口\\n* Created by generator \\n*/\\npublic interface ${beanName}Mapper extends GenericMapper<${beanName},String> {\\n\\n}\\n\",\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"},{\"name\":\"resources\",\"type\":\"dir\",\"fileName\":\"resources\",\"children\":[{\"name\":\"scripts/initialize\",\"type\":\"dir\",\"fileName\":\"scripts/initialize/\",\"children\":[{\"name\":\"initialize.groovy\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"initialize.groovy\",\"replaceMod\":\"append\",\"template\":\"//${moduleComment!\'\'}\\ndatabase.createOrAlter(\\\"${utils.toLowerCase(tableName)}\\\")\\n .addColumn().name(\\\"u_id\\\").alias(\\\"id\\\").comment(\\\"ID\\\").jdbcType(java.sql.JDBCType.VARCHAR).length(32).primaryKey().commit()\\n<#list  fields as field >\\n  <#if field.property!=\'id\'>\\n.addColumn().${utils.createSqlColumnBuilder(field)}.commit()\\n  </#if>\\n</#list>\\n .comment(\\\"${moduleComment!\'\'}\\\").commit();\\n\\ndef ${module}_module= [u_id: \'${module}\', name: \'${moduleComment}\', uri: \'admin/${module}/list.html\', icon: \'\', parent_id: \'-1\', remark: \'\', status: 1, optional: \'[{\\\"id\\\":\\\"M\\\",\\\"text\\\":\\\"菜单可见\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"import\\\",\\\"text\\\":\\\"导入excel\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"export\\\",\\\"text\\\":\\\"导出excel\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"R\\\",\\\"text\\\":\\\"查询\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"C\\\",\\\"text\\\":\\\"新增\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"U\\\",\\\"text\\\":\\\"修改\\\",\\\"checked\\\":true},{\\\"id\\\":\\\"D\\\",\\\"text\\\":\\\"删除\\\",\\\"checked\\\":false}]\', sort_index: 1];\\ndatabase.getTable(\\\"s_modules\\\").createInsert().value(${module}_module).exec();\\n\"}],\"replaceMod\":\"ignore\"},{\"name\":\"${packageName}\",\"type\":\"dir\",\"fileName\":\"${packageName?replace(\'.\',\'/\')}\",\"children\":[{\"name\":\"dao\",\"type\":\"dir\",\"fileName\":\"dao\",\"children\":[{\"name\":\"Mapper\",\"type\":\"template\",\"icon\":\"icon-application\",\"fileName\":\"${beanName}Mapper.xml\",\"replaceMod\":\"ignore\",\"template\":\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\" ?>\\n<!DOCTYPE mapper\\n        PUBLIC \\\"-//mybatis.org//DTD Mapper 3.0//EN\\\"\\n        \\\"http://www.mybatis.org/dtd/mybatis-3-mapper.dtd\\\">\\n<mapper namespace=\\\"${packageName}.dao.${beanName}Mapper\\\">\\n    <resultMap id=\\\"${beanName}ResultMap\\\" type=\\\"${packageName}.bean.${beanName}\\\">\\n        <id property=\\\"id\\\" column=\\\"u_id\\\" javaType=\\\"string\\\" jdbcType=\\\"VARCHAR\\\"/>\\n        <#list  fields as field >\\n        <#if field.property!=\'id\'>\\n            <result property=\\\"${field.property}\\\" column=\\\"${field.column}\\\" javaType=\\\"${field.javaType}\\\" jdbcType=\\\"${field.jdbcType}\\\"/>\\n        </#if>\\n    </#list>\\n    </resultMap>\\n\\n    <!--用于动态生成sql所需的配置-->\\n    <sql id=\\\"config\\\">\\n        <bind name=\\\"resultMapId\\\" value=\\\"\'${beanName}ResultMap\'\\\"/>\\n        <bind name=\\\"tableName\\\" value=\\\"\'${tableName}\'\\\"/>\\n    </sql>\\n    <insert id=\\\"insert\\\" parameterType=\\\"com.zxwl.web.bean.common.InsertParam\\\" >\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildInsertSql\\\"/>\\n    </insert>\\n\\n    <delete id=\\\"delete\\\" parameterType=\\\"com.zxwl.web.bean.common.DeleteParam\\\">\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildDeleteSql\\\"/>\\n    </delete>\\n\\n    <update id=\\\"update\\\" parameterType=\\\"com.zxwl.web.bean.common.UpdateParam\\\">\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildUpdateSql\\\"/>\\n    </update>\\n\\n    <select id=\\\"selectByPk\\\" parameterType=\\\"string\\\" resultMap=\\\"${beanName}ResultMap\\\">\\n        select * from ${tableName} where u_id=${r\'#{id}\'}\\n    </select>\\n\\n    <select id=\\\"select\\\" parameterType=\\\"com.zxwl.web.bean.common.QueryParam\\\" resultMap=\\\"${beanName}ResultMap\\\">\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildSelectSql\\\"/>\\n    </select>\\n\\n    <select id=\\\"total\\\" parameterType=\\\"com.zxwl.web.bean.common.QueryParam\\\" resultType=\\\"int\\\">\\n        <include refid=\\\"config\\\"/>\\n        <include refid=\\\"BasicMapper.buildTotalSql\\\"/>\\n    </select>\\n</mapper>\\n\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}],\"replaceMod\":\"ignore\"}]}],\"replaceMod\":\"ignore\"}],\"vars\":[{\"name\":\"module\",\"value\":\"Shop\",\"comment\":\"模块名\"},{\"name\":\"beanName\",\"value\":\"Shop\",\"comment\":\"bean名\"},{\"name\":\"moduleComment\",\"value\":\"店铺信息\",\"comment\":\"bean描述\"},{\"name\":\"tableName\",\"value\":\"t_user_info\",\"comment\":\"数据库表名\"},{\"name\":\"packageName\",\"value\":\"com.zxwl.platform\",\"comment\":\"包名\"},{\"name\":\"dbType\",\"value\":\"mysql\",\"comment\":\"数据库类型\"},{\"name\":\"sourceDir\",\"value\":\"G:\\\\tmp\\\\\",\"comment\":\"开发环境源码目录\"}]}]', 'code-generator', 'admin');

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `u_id` varchar(32) NOT NULL COMMENT 'ID',
  `user_id` varchar(256) NOT NULL COMMENT '用户id',
  `role_id` varchar(256) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('9Xra9it1', 'dI5mXs', 'ship-owner');
INSERT INTO `s_user_role` VALUES ('cQnzTTeN', 'YThv5x', 'admin');
INSERT INTO `s_user_role` VALUES ('CsIw2Zus', 'VEI4wN', 'admin');
INSERT INTO `s_user_role` VALUES ('EmEYJ0HC', 'RtLs8E', 'admin');
INSERT INTO `s_user_role` VALUES ('FhIBhMLI', 'RNbIxh', 'test');
INSERT INTO `s_user_role` VALUES ('IFYa0jYK', 'noTTpW', 'admin');
INSERT INTO `s_user_role` VALUES ('jHIw5FYb', 'Nj5G2w', 'admin');
INSERT INTO `s_user_role` VALUES ('Jw7P07Rt', '111111', 'admin');
INSERT INTO `s_user_role` VALUES ('KC3qnUWc', 'i8Xc2l', 'admin');
INSERT INTO `s_user_role` VALUES ('mSDtrZo7', 'IzbaXD', 'admin');
INSERT INTO `s_user_role` VALUES ('QvDiQ5v9', 'Y6X2Ab', 'admin');
INSERT INTO `s_user_role` VALUES ('syLz5irQ', 'X8jygJ', 'admin');
INSERT INTO `s_user_role` VALUES ('whuGODCR', 't8081Q', 'admin');
INSERT INTO `s_user_role` VALUES ('YM3y4HPt', '4zMhla', 'admin');

-- ----------------------------
-- Table structure for t_ad_device
-- ----------------------------
DROP TABLE IF EXISTS `t_ad_device`;
CREATE TABLE `t_ad_device` (
  `u_id` varchar(32) NOT NULL,
  `device_id` varchar(32) NOT NULL,
  `ad_id` varchar(32) NOT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备 广告中间表';

-- ----------------------------
-- Records of t_ad_device
-- ----------------------------

-- ----------------------------
-- Table structure for t_app_version
-- ----------------------------
DROP TABLE IF EXISTS `t_app_version`;
CREATE TABLE `t_app_version` (
  `u_id` varchar(32) NOT NULL,
  `release_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `version` float DEFAULT NULL,
  `describe` varchar(500) DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_app_version
-- ----------------------------

-- ----------------------------
-- Table structure for t_area
-- ----------------------------
DROP TABLE IF EXISTS `t_area`;
CREATE TABLE `t_area` (
  `u_id` varchar(32) NOT NULL COMMENT '主键 id',
  `area_name` varchar(16) DEFAULT NULL COMMENT '区域名称',
  `parent_id` varchar(32) DEFAULT NULL,
  `level` int(11) DEFAULT '0',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`),
  KEY `idx_id` (`u_id`,`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域表';

-- ----------------------------
-- Records of t_area
-- ----------------------------
INSERT INTO `t_area` VALUES ('00e6383b9f520db9f227d4948a5d6b4f', '湖里区', '2768c7f1a9f6b6d027716bb22c72422d', '1', null, null, null);
INSERT INTO `t_area` VALUES ('1', '七匹狼', '-1', '0', null, null, null);
INSERT INTO `t_area` VALUES ('2768c7f1a9f6b6d027716bb22c72422d', '厦门市', '30f3ba82ccc84288846d63c02ce3dd22', '1', null, null, null);
INSERT INTO `t_area` VALUES ('30f3ba82ccc84288846d63c02ce3dd22', '福建省', '1', null, null, null, null);
INSERT INTO `t_area` VALUES ('a4713ba91b3cd32298299eb3ab2588c5', '集美区', '2768c7f1a9f6b6d027716bb22c72422d', '1', null, null, null);
INSERT INTO `t_area` VALUES ('f21b84df5e1f354abd997bdc56e9799e', '思明区', '2768c7f1a9f6b6d027716bb22c72422d', '1', null, null, null);

-- ----------------------------
-- Table structure for t_attr
-- ----------------------------
DROP TABLE IF EXISTS `t_attr`;
CREATE TABLE `t_attr` (
  `u_id` varchar(32) NOT NULL,
  `attr_key` varchar(32) DEFAULT NULL,
  `attr_value` int(11) DEFAULT NULL,
  `attr_type` int(11) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_attr
-- ----------------------------

-- ----------------------------
-- Table structure for t_deal_pay
-- ----------------------------
DROP TABLE IF EXISTS `t_deal_pay`;
CREATE TABLE `t_deal_pay` (
  `u_id` varchar(32) NOT NULL COMMENT '主键id',
  `order_id` varchar(32) NOT NULL COMMENT '订单id',
  `creator_id` varchar(32) DEFAULT NULL COMMENT '哪个用户的交易记录',
  `deal_time` datetime DEFAULT NULL COMMENT '交易时间',
  `deal_type` int(11) DEFAULT NULL COMMENT '交易类型 1 支出，2 充值',
  `deal_money` decimal(10,0) DEFAULT NULL COMMENT '交易金额',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_deal_pay
-- ----------------------------
INSERT INTO `t_deal_pay` VALUES ('', '', null, '2017-08-03 03:39:16', null, null, '0000-00-00 00:00:00', null);
INSERT INTO `t_deal_pay` VALUES ('1111', '3', '111111', '2017-08-03 03:39:16', '2', '2232', '2017-08-03 03:39:16', null);
INSERT INTO `t_deal_pay` VALUES ('dsfs', '2', '111111', '2017-08-03 03:39:16', '1', '34', '2017-08-03 03:39:16', null);
INSERT INTO `t_deal_pay` VALUES ('sdfw', '5', '111111', '2017-08-03 03:39:16', '2', '134', '2017-08-03 03:39:16', null);
INSERT INTO `t_deal_pay` VALUES ('sdsc', '4', '111111', '2017-08-03 03:39:16', '1', '3241', '2017-08-03 03:39:16', null);
INSERT INTO `t_deal_pay` VALUES ('ww', '1', '111111', '2017-08-03 03:39:16', '2', '222', '2017-08-03 03:39:16', null);
INSERT INTO `t_deal_pay` VALUES ('xcsd', '6', '111111', null, '1', '3432', '2017-08-03 03:39:16', null);

-- ----------------------------
-- Table structure for t_deivce
-- ----------------------------
DROP TABLE IF EXISTS `t_deivce`;
CREATE TABLE `t_deivce` (
  `u_id` varchar(32) NOT NULL COMMENT '设备主键 id',
  `title` varchar(64) DEFAULT NULL COMMENT '设备标题',
  `seq_code` varchar(128) DEFAULT NULL COMMENT '序列号',
  `code` varchar(10) DEFAULT NULL COMMENT '设备编码',
  `factory_time` datetime DEFAULT NULL COMMENT '出厂日期',
  `production_time` datetime DEFAULT NULL COMMENT '生产日期',
  `usage_time` datetime DEFAULT NULL COMMENT '投入使用时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  `last_change_user` varchar(32) DEFAULT NULL COMMENT '最后一次更变的用户id',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备信息';

-- ----------------------------
-- Records of t_deivce
-- ----------------------------
INSERT INTO `t_deivce` VALUES ('126488cdf4b813b11f6917ebddc88e5a', '魔镜', '87654426627ds', 'a3452dfds', '2017-06-27 00:00:00', '2017-06-26 00:00:00', '2017-07-04 00:00:00', '新款', null, null, null);
INSERT INTO `t_deivce` VALUES ('1b4f978fa4b3a1ca1a07c045324184e6', 'jingzi', 'ssddwwqw22', '21212dssa', '2017-07-06 00:00:00', '2017-06-27 00:00:00', '2017-07-17 00:00:00', '', null, null, null);
INSERT INTO `t_deivce` VALUES ('1e7626b5093768abc5b4ddcea5d2b347', '设备6', '00006', '66666', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '6', null, null, null);
INSERT INTO `t_deivce` VALUES ('33540d51ce16c68b79c704197be0404f', '设备7', '00007', '77777', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '7', null, null, null);
INSERT INTO `t_deivce` VALUES ('537fe539c042f78acd696b026dc6ae50', '设备5', '00005', '55555', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '5', null, null, null);
INSERT INTO `t_deivce` VALUES ('5666e68aed9c428a4b8690541c71946f', '设备2', '00002', '22222', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '2', null, null, null);
INSERT INTO `t_deivce` VALUES ('6980d2f55c121e17bfc35bd8f5c6dca6', '设备3', '00003', '33333', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '3', null, null, null);
INSERT INTO `t_deivce` VALUES ('87734cd5374a4bff28898608b72f625a', 'testeq', '111sssss', 'wqqwqwq', '2017-07-19 00:00:00', '2017-07-18 00:00:00', '2017-07-21 00:00:00', '', null, null, null);
INSERT INTO `t_deivce` VALUES ('95ba66d25c5bb493f70317df3de8a9ca', '设备8', '00008', '88888', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '8', null, null, null);
INSERT INTO `t_deivce` VALUES ('bf404d9c60d19cad638cafd5c4b1f1ce', '设备4', '00004', '44444', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '4', null, null, null);
INSERT INTO `t_deivce` VALUES ('e6ac3af01f863467281308844bcec495', '设备1', '00001', '11111', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '2017-07-31 00:00:00', '1', null, null, null);
INSERT INTO `t_deivce` VALUES ('fc7c900245c76fc87373f57ab934f00e', 'jingzi', '123sde43', '123wesd3', '2017-07-19 00:00:00', '2017-07-18 00:00:00', '2017-07-20 00:00:00', 'diaodiaodiao', null, null, null);

-- ----------------------------
-- Table structure for t_device_ad
-- ----------------------------
DROP TABLE IF EXISTS `t_device_ad`;
CREATE TABLE `t_device_ad` (
  `u_id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '发布用户id',
  `upload_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间',
  `effective_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '生效时间',
  `device_id` varchar(32) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `resource_id` varchar(32) DEFAULT NULL COMMENT '与 t_metadata_rel 的 record_id 关联',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备广告';

-- ----------------------------
-- Records of t_device_ad
-- ----------------------------
INSERT INTO `t_device_ad` VALUES ('1faf41rqfqwrwa', 'noTTpW', '2017-08-07 16:29:15', '2017-08-07 16:29:17', '126488cdf4b813b11f6917ebddc88e5a', '2017-08-08 16:29:42', 'I32OzH');
INSERT INTO `t_device_ad` VALUES ('899ede81a53e31bfc085c50dc3b6b845', 'noTTpW', '2017-08-09 06:22:37', '2017-08-11 12:00:00', null, '2017-08-24 12:00:00', 'MpYtTs');
INSERT INTO `t_device_ad` VALUES ('f532dc91971dbab728c3e2c5f75adb87', 'noTTpW', '2017-08-08 16:14:43', '2017-08-15 12:00:00', null, '2017-08-24 12:00:00', 'rEupX5');

-- ----------------------------
-- Table structure for t_device_fid
-- ----------------------------
DROP TABLE IF EXISTS `t_device_fid`;
CREATE TABLE `t_device_fid` (
  `u_id` varchar(32) NOT NULL,
  `dev_num` varchar(128) DEFAULT NULL COMMENT '设备序号',
  `dev_code` varchar(10) DEFAULT NULL COMMENT '设备编码',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='射频信息表';

-- ----------------------------
-- Records of t_device_fid
-- ----------------------------
INSERT INTO `t_device_fid` VALUES ('2', 'sttsg3547', 'xxzzsww112', '2017-07-26 08:29:44', '2017-07-26 08:29:44', null);
INSERT INTO `t_device_fid` VALUES ('3', 'sttsg3545', 'xxzzsww111', '2017-07-26 09:23:11', '2017-07-26 09:23:11', null);
INSERT INTO `t_device_fid` VALUES ('57e27f03e5a8828472a5c360b785e8ea', 'sttsg3546', 'xxzzsww111', '2017-07-18 03:35:47', '2017-07-18 03:35:47', null);
INSERT INTO `t_device_fid` VALUES ('bb24b24f24292c9dca330626e6900ef7', 'dsadsada', 'sdwwq', null, null, null);
INSERT INTO `t_device_fid` VALUES ('f04cfba15e407d1224c9d73eae63fdee', 'sdasdsaaa', 'sdwwqewqwe', null, null, null);

-- ----------------------------
-- Table structure for t_device_use_history
-- ----------------------------
DROP TABLE IF EXISTS `t_device_use_history`;
CREATE TABLE `t_device_use_history` (
  `u_id` varchar(32) DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `device_id` varchar(32) DEFAULT NULL,
  `use_type` int(11) DEFAULT NULL,
  `expense` int(11) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备使用历史记录表';

-- ----------------------------
-- Records of t_device_use_history
-- ----------------------------

-- ----------------------------
-- Table structure for t_fid_goodsspec
-- ----------------------------
DROP TABLE IF EXISTS `t_fid_goodsspec`;
CREATE TABLE `t_fid_goodsspec` (
  `u_id` varchar(32) NOT NULL,
  `fid_id` varchar(32) DEFAULT NULL COMMENT '射频id',
  `shop_id` varchar(32) DEFAULT NULL,
  `spec_id` varchar(32) DEFAULT NULL COMMENT '商品规格id',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='射频-商品规格表';

-- ----------------------------
-- Records of t_fid_goodsspec
-- ----------------------------
INSERT INTO `t_fid_goodsspec` VALUES ('28d15e4a1146c190b3676bfefc50844a', '3', 'a8b74c8e08aa59582c5f676cf0f7a119', '2', '2017-07-27 08:27:31', '2017-07-27 08:27:31', null);
INSERT INTO `t_fid_goodsspec` VALUES ('a0e7c2105ee11b89b53bee44fbe1bc82', 'bb24b24f24292c9dca330626e6900ef7', 'f445cf34a5f0984159e386385f9f1f42', '1', '2017-08-03 03:48:13', '2017-08-03 03:48:13', null);
INSERT INTO `t_fid_goodsspec` VALUES ('d7344a56b7074cab493ac5d7002cadd1', '2', 'a8b74c8e08aa59582c5f676cf0f7a119', '', '2017-07-27 08:53:36', '2017-07-27 08:53:36', null);
INSERT INTO `t_fid_goodsspec` VALUES ('dbc76f9911742e210e8d918c346dc28b', '57e27f03e5a8828472a5c360b785e8ea', 'a8b74c8e08aa59582c5f676cf0f7a119', '5', '2017-07-27 08:28:20', '2017-07-27 08:28:20', null);
INSERT INTO `t_fid_goodsspec` VALUES ('e626cf4338e98873426b8eba3a4f94e1', 'f04cfba15e407d1224c9d73eae63fdee', 'f445cf34a5f0984159e386385f9f1f42', null, null, null, null);

-- ----------------------------
-- Table structure for t_goods_average_level
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_average_level`;
CREATE TABLE `t_goods_average_level` (
  `u_id` varchar(32) NOT NULL,
  `comment_count` int(11) DEFAULT NULL COMMENT '评价总数量',
  `average_level` int(11) DEFAULT NULL COMMENT '平均星级',
  `goods_id` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评价统计表';

-- ----------------------------
-- Records of t_goods_average_level
-- ----------------------------

-- ----------------------------
-- Table structure for t_goods_brokerage
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_brokerage`;
CREATE TABLE `t_goods_brokerage` (
  `u_id` varchar(32) NOT NULL,
  `percentage_id` varchar(32) DEFAULT NULL COMMENT '导购设置id',
  `buyer_id` varchar(32) DEFAULT NULL COMMENT '购买用户id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '分佣用户id',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单id',
  `dividend` decimal(10,0) DEFAULT NULL COMMENT '获得佣金',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`),
  KEY `ix_idx` (`percentage_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='导购收益表';

-- ----------------------------
-- Records of t_goods_brokerage
-- ----------------------------
INSERT INTO `t_goods_brokerage` VALUES ('111111', '111111', '222222', 'YThv5x', '33333', '32', '2017-08-03 07:58:51', '2017-08-03 07:58:51', null);
INSERT INTO `t_goods_brokerage` VALUES ('12123e3', '444444', 'dsd233', 'YThv5x', '2323ewd', '1000', '2017-08-03 07:58:48', '2017-08-03 07:58:48', null);
INSERT INTO `t_goods_brokerage` VALUES ('1231243', '333333', 'csd4324', '111111', '5df920a25759dd8f4b4f9ff52f09b92b', '30', '2017-08-03 08:06:41', '2017-08-03 08:06:41', null);
INSERT INTO `t_goods_brokerage` VALUES ('221s12', '222222', '12dq321', 'YThv5x', '12dqw3e1', '200', '2017-08-03 07:58:47', '2017-08-03 07:58:47', null);
INSERT INTO `t_goods_brokerage` VALUES ('222222', '111111', '333333', '111111', '444444', '45', '2017-08-03 07:58:25', '2017-08-03 07:58:25', null);
INSERT INTO `t_goods_brokerage` VALUES ('2312ss', '222222', '4234223', '222222', 'ref342342', '166', '2017-08-03 07:58:29', '2017-08-03 07:58:29', null);
INSERT INTO `t_goods_brokerage` VALUES ('232312', '111111', '444444', '111111', 'dfd5783b038b9310b5fcfbe19d9df52e', '100', '2017-08-03 08:06:40', '2017-08-03 08:06:40', null);
INSERT INTO `t_goods_brokerage` VALUES ('4523567836', 'ef569bca96f1057e640ff638ab0d8fa8', '12332', 'YThv5x', '1', '50', '2017-08-03 07:58:46', '2017-08-03 07:58:46', '12332');
INSERT INTO `t_goods_brokerage` VALUES ('45434', '222222', '555555', '333333', '555435', '12', '2017-08-03 07:58:45', '2017-08-03 07:58:45', null);
INSERT INTO `t_goods_brokerage` VALUES ('ascxzs', '3', 'xsdaw', 'zxas', 'zxczxcd', '2', '2017-08-03 07:58:45', null, null);
INSERT INTO `t_goods_brokerage` VALUES ('sas', '1', 'swddx', 'wxsx', 'weedfcx', '33', '2017-08-03 07:58:45', '2017-08-03 07:58:45', null);
INSERT INTO `t_goods_brokerage` VALUES ('sdsfas', '4', 'wadw', 'xdasx', 'xzczx', '32', '2017-08-04 07:10:29', '2017-08-04 07:10:29', null);

-- ----------------------------
-- Table structure for t_goods_class
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_class`;
CREATE TABLE `t_goods_class` (
  `u_id` varchar(32) NOT NULL,
  `class_code` varchar(16) DEFAULT NULL COMMENT '类别编码',
  `class_name` varchar(20) DEFAULT NULL COMMENT '类别名称',
  `parent_code` varchar(16) DEFAULT NULL COMMENT '父级id',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类别表';

-- ----------------------------
-- Records of t_goods_class
-- ----------------------------
INSERT INTO `t_goods_class` VALUES ('1558ebac5136d732d0c76992ef5adc2f', '00000004', '职业装', '00000001', '2017-07-18 07:33:18', '2017-07-18 07:33:18', 'YThv5x');
INSERT INTO `t_goods_class` VALUES ('27bad499ddf2cca211c92e2ec722794c', '00000003', '连衣裙', '00000001', '2017-08-01 10:00:13', '2017-08-01 10:00:13', 'YThv5x');
INSERT INTO `t_goods_class` VALUES ('360dbc24de61fdf76af7e9525048b4c4', '00000006', '外套', '00000002', '2017-08-01 17:58:04', '2017-08-01 17:58:04', 'YThv5x');
INSERT INTO `t_goods_class` VALUES ('61fb49370a3f59d6444fa1b3be58001f', '00000005', '阔腿裤', '00000001', '2017-07-18 07:33:19', '2017-07-18 07:33:19', 'YThv5x');
INSERT INTO `t_goods_class` VALUES ('8526a2615dd2c765acb98d789a6f5636', '00000009', '运动服', '00000002', '2017-08-02 17:03:37', '2017-08-02 17:03:37', 'YThv5x');
INSERT INTO `t_goods_class` VALUES ('9442e508ea80a1cafd4bd70991c51bd3', '00000001', '女装', '', '2017-07-18 11:47:34', '2017-07-18 11:47:34', 'YThv5x');
INSERT INTO `t_goods_class` VALUES ('9cb1d9f6513315a7df64175918cf6abb', '00000008', '西装', '00000002', '2017-08-02 16:42:06', '2017-08-02 16:42:06', 'YThv5x');
INSERT INTO `t_goods_class` VALUES ('a4407f28a60a86b2e3ccf5e1cd1a8094', '00000010', '皮外套', '00000006', '2017-08-07 16:17:02', '2017-08-07 16:17:02', 'YThv5x');
INSERT INTO `t_goods_class` VALUES ('a5350f81f71bf700e57576413bbfd7cf', '00000011', '防雨套', '00000006', '2017-08-07 16:17:13', '2017-08-07 16:17:13', 'YThv5x');
INSERT INTO `t_goods_class` VALUES ('e3f3192ae7f1ff221cd80941053dc4ae', '00000008', '衬衫', '00000002', '2017-08-01 17:45:57', '2017-08-01 17:45:57', 'YThv5x');
INSERT INTO `t_goods_class` VALUES ('f8d2a3bb2908dd129b229f23609c2411', '00000002', '男装', '', '2017-08-02 16:59:43', '2017-08-02 16:59:43', 'YThv5x');

-- ----------------------------
-- Table structure for t_goods_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_comment`;
CREATE TABLE `t_goods_comment` (
  `u_id` varchar(32) NOT NULL,
  `comment_level` int(11) DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `goods_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  `last_change_user` varchar(32) DEFAULT NULL,
  `record_id` varchar(32) DEFAULT NULL COMMENT '商品评论图片关联ID，关联t_metadata_rel 表record_id',
  `goodsspc_info` varchar(100) DEFAULT NULL COMMENT '用户评论商品规格信息',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='商品评价表';

-- ----------------------------
-- Records of t_goods_comment
-- ----------------------------
INSERT INTO `t_goods_comment` VALUES ('1', '5', '好棒好棒好棒，这件衣服真棒', '1', '222222', '2017-07-26 09:40:29', '2017-07-26 09:40:31', 'YThv5x', '1', 'xl 红色');
INSERT INTO `t_goods_comment` VALUES ('2', '4', '这件衣服会起毛球', '2', '111111', '2017-07-26 09:41:00', '2017-07-26 09:41:02', 'YThv5x', '2', 'xl 黑色');
INSERT INTO `t_goods_comment` VALUES ('3', '5', '真棒！喜欢这件衣服', '1', '444444', '2017-07-26 09:41:27', '2017-07-26 09:41:28', 'YThv5x', '3', 'xxl白色');
INSERT INTO `t_goods_comment` VALUES ('4', '5', '超级棒', '1', '333333', '2017-07-26 09:43:04', '2017-07-26 09:43:06', 'Y6X2Ab', '4', 's 红色');
INSERT INTO `t_goods_comment` VALUES ('5', '4', 'dfsdf', '1', '555555', '2017-07-26 09:43:04', '2017-07-26 09:43:04', 'YThv5x', '5', 'm 红色');

-- ----------------------------
-- Table structure for t_goods_info
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_info`;
CREATE TABLE `t_goods_info` (
  `u_id` varchar(32) NOT NULL,
  `class_code` varchar(16) DEFAULT NULL COMMENT '类别编码',
  `title` varchar(200) DEFAULT NULL COMMENT '标题/名称',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `describe` text COMMENT '详情',
  `img_id` varchar(32) DEFAULT NULL COMMENT '商品图片ID，与 t_metadata_rel 表 record_id 对应',
  `creator_id` varchar(32) DEFAULT NULL COMMENT '所属用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  `last_change_user` varchar(32) DEFAULT NULL COMMENT '上次修改用户id',
  `quality` int(10) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Records of t_goods_info
-- ----------------------------
INSERT INTO `t_goods_info` VALUES ('432216b7ce3cf2f60531e230acc4f7e9', '00000003', '连衣裙', '49.55', '哈哈哈', 'c137272df31aa9399e9e7d9e978306c5', 'YThv5x', '2017-08-11 17:25:18', '2017-08-11 17:25:18', 'YThv5x', '0');
INSERT INTO `t_goods_info` VALUES ('aabf249adcabb387ad419bfae9084b03', '00000003', '连衣裙2', '99.55', '哈哈哈啊', '5030fb0506be7d55e840913eab271c5b', 'YThv5x', '2017-08-11 17:26:12', '2017-08-11 17:26:12', 'YThv5x', '0');

-- ----------------------------
-- Table structure for t_goods_info_spec
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_info_spec`;
CREATE TABLE `t_goods_info_spec` (
  `u_id` varchar(32) NOT NULL,
  `size` varchar(4) DEFAULT NULL,
  `color` varchar(6) DEFAULT NULL,
  `goods_id` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL,
  `quality` int(10) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格表';

-- ----------------------------
-- Records of t_goods_info_spec
-- ----------------------------
INSERT INTO `t_goods_info_spec` VALUES ('1', 'M', '白色', '1', '2017-08-09 09:55:10', '2017-08-09 09:55:10', 'YThv5x', '12');
INSERT INTO `t_goods_info_spec` VALUES ('2', 'L', '红色', '2', '2017-08-09 09:55:10', '2017-08-09 09:55:10', 'YThv5x', '1124');
INSERT INTO `t_goods_info_spec` VALUES ('3', 'L', '白色', '1', '2017-08-09 09:55:10', '2017-08-09 09:55:10', 'YThv5x', '232');
INSERT INTO `t_goods_info_spec` VALUES ('4', 'XL', '藏青色', '1', '2017-08-09 09:55:10', '2017-08-09 09:55:10', 'YThv5x', '23');
INSERT INTO `t_goods_info_spec` VALUES ('5', 'XXL', '白色', '1', '2017-08-09 09:55:10', '2017-08-09 09:55:10', 'YThv5x', '324');

-- ----------------------------
-- Table structure for t_goods_order
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_order`;
CREATE TABLE `t_goods_order` (
  `u_id` varchar(32) NOT NULL,
  `submit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `order_status` int(11) DEFAULT NULL COMMENT '1 未支付 2 待发货 3 待收货 4 待评价 5 退款    99 关闭',
  `remarks` varchar(500) DEFAULT NULL,
  `shop_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `pay_type` int(11) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单';

-- ----------------------------
-- Records of t_goods_order
-- ----------------------------
INSERT INTO `t_goods_order` VALUES ('1', '2017-08-11 06:42:17', '3', '1', 'f445cf34a5f0984159e386385f9f1f42', 'RtLs8E', '1', '99.99', null, null, null);
INSERT INTO `t_goods_order` VALUES ('2', '2017-08-11 06:42:18', '3', '1', 'f445cf34a5f0984159e386385f9f1f42', 'RtLs8E', '1', '99.99', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '');
INSERT INTO `t_goods_order` VALUES ('5df920a25759dd8f4b4f9ff52f09b92b', '2017-08-11 06:42:19', '2', null, 'f445cf34a5f0984159e386385f9f1f42', 'RtLs8E', '0', '99.99', null, null, null);
INSERT INTO `t_goods_order` VALUES ('9bdee23a2f6c8fbc11f706ac9911c85b', '2017-08-11 08:31:28', '5', null, 'f445cf34a5f0984159e386385f9f1f42', 'RtLs8E', '0', '99.99', null, null, null);
INSERT INTO `t_goods_order` VALUES ('a5f65889e6b91baac818a1456ae200fb', '2017-08-11 06:42:21', '1', null, 'f445cf34a5f0984159e386385f9f1f42', 'RtLs8E', '0', '99.99', null, null, null);
INSERT INTO `t_goods_order` VALUES ('dfd5783b038b9310b5fcfbe19d9df52e', '2017-08-11 06:42:23', '1', null, 'f445cf34a5f0984159e386385f9f1f42', 'RtLs8E', '0', '99.99', null, null, null);

-- ----------------------------
-- Table structure for t_goods_order_info
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_order_info`;
CREATE TABLE `t_goods_order_info` (
  `u_id` varchar(32) NOT NULL,
  `goods_id` varchar(32) DEFAULT NULL,
  `goods_name` varchar(200) DEFAULT NULL,
  `goods_spec` varchar(32) DEFAULT NULL COMMENT '商品规格(颜色 。大小XXL等） 与 t_goods_info_spec u_id 关联',
  `acount` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `order_id` varchar(32) DEFAULT NULL,
  `order_express_no` varchar(16) DEFAULT NULL COMMENT '快递单号',
  `order_express_company` varchar(10) DEFAULT NULL COMMENT '快递公司',
  `link_name` varchar(20) DEFAULT NULL,
  `link_tel` varchar(32) DEFAULT NULL,
  `link_address` varchar(200) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_delivery` datetime DEFAULT NULL COMMENT '发货时间',
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情';

-- ----------------------------
-- Records of t_goods_order_info
-- ----------------------------
INSERT INTO `t_goods_order_info` VALUES ('1', '1', '连衣裙', '1', '1', '99.99', '1', '1233123', 'SF', null, null, null, '2017-07-31 07:26:03', '2017-07-31 07:26:03', null, null);
INSERT INTO `t_goods_order_info` VALUES ('2', '1', '连衣裙', '1', '10', '99.99', '2', '', '', null, null, null, '2017-08-10 01:18:51', '2017-08-10 01:18:51', null, '');
INSERT INTO `t_goods_order_info` VALUES ('5da118775896042e9a53f2c14b2aca87', '1', '连衣裙', '1', '1', '99.99', '9bdee23a2f6c8fbc11f706ac9911c85b', null, null, '庄五', '123332111122', '厦门市集美区', null, null, null, null);
INSERT INTO `t_goods_order_info` VALUES ('613402c51619508c7d482fe0230850ed', '1', '连衣裙', '1', '1', '99.99', '5df920a25759dd8f4b4f9ff52f09b92b', null, null, '庄五', '123332111122', '厦门市集美区', null, null, null, null);
INSERT INTO `t_goods_order_info` VALUES ('71626aadbc66da77a7582eabda869e13', '1', '连衣裙', '1', '1', '99.99', 'dfd5783b038b9310b5fcfbe19d9df52e', null, null, '庄五', '123332111122', '厦门市集美区', null, null, null, null);
INSERT INTO `t_goods_order_info` VALUES ('ba1dd39e0a41be696f2f5ee9139088d7', '1', '连衣裙', '1', '1', '99.99', 'a5f65889e6b91baac818a1456ae200fb', null, null, '庄五', '123332111122', '厦门市集美区', null, null, null, null);

-- ----------------------------
-- Table structure for t_goods_percentage
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_percentage`;
CREATE TABLE `t_goods_percentage` (
  `u_id` varchar(32) NOT NULL,
  `goods_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `percentage` float(10,0) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='导购设置表';

-- ----------------------------
-- Records of t_goods_percentage
-- ----------------------------
INSERT INTO `t_goods_percentage` VALUES ('', null, null, null, null, null, null);
INSERT INTO `t_goods_percentage` VALUES ('1', '1', 'noTTpW', '3', '2017-08-03 16:43:51', '2017-08-03 16:43:51', 'noTTpW');
INSERT INTO `t_goods_percentage` VALUES ('3', '3', null, '3', '2017-08-03 16:43:51', null, 'dssds');
INSERT INTO `t_goods_percentage` VALUES ('7', null, null, null, '2017-08-03 16:43:51', null, 'sd');
INSERT INTO `t_goods_percentage` VALUES ('9', '2', 'noTTpW', '3', '2017-08-03 16:43:01', '2017-08-03 16:43:01', 'noTTpW');

-- ----------------------------
-- Table structure for t_lastest_device_useinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_lastest_device_useinfo`;
CREATE TABLE `t_lastest_device_useinfo` (
  `u_id` varchar(32) NOT NULL,
  `start_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `device_id` varchar(32) DEFAULT NULL,
  `use_type` int(11) DEFAULT NULL,
  `use_pay` int(11) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_lastest_device_useinfo
-- ----------------------------

-- ----------------------------
-- Table structure for t_location_new
-- ----------------------------
DROP TABLE IF EXISTS `t_location_new`;
CREATE TABLE `t_location_new` (
  `u_id` varchar(32) NOT NULL,
  `upload_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `x_coordinate` decimal(10,0) DEFAULT NULL,
  `y_coordinate` decimal(10,0) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户最新位置表';

-- ----------------------------
-- Records of t_location_new
-- ----------------------------

-- ----------------------------
-- Table structure for t_location_old
-- ----------------------------
DROP TABLE IF EXISTS `t_location_old`;
CREATE TABLE `t_location_old` (
  `u_id` varchar(32) NOT NULL,
  `upload_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `x_coordinate` decimal(10,0) DEFAULT NULL,
  `y_coordinate` decimal(10,0) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户历史位置表';

-- ----------------------------
-- Records of t_location_old
-- ----------------------------

-- ----------------------------
-- Table structure for t_metadata_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_metadata_rel`;
CREATE TABLE `t_metadata_rel` (
  `u_id` varchar(32) NOT NULL COMMENT '主键 id',
  `data_id` varchar(32) NOT NULL COMMENT '图片Id，与 s_resource 表 u_id 关联',
  `record_id` varchar(32) DEFAULT NULL COMMENT '与 视频表 | 店铺图片 等 主键关联',
  `data_type` tinyint(4) DEFAULT NULL COMMENT '图片类型： 1 设备广告，2 产品，3 评价图，4 视频图片,5店铺图片',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型： 0 图片，1 视频',
  PRIMARY KEY (`u_id`),
  KEY `idx_id` (`u_id`,`data_id`,`data_type`) USING BTREE,
  KEY `data_id` (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体（资源）信息中间表';

-- ----------------------------
-- Records of t_metadata_rel
-- ----------------------------
INSERT INTO `t_metadata_rel` VALUES ('00ec4a78f93cd57436d162d867e668b5', 'N83Wpt', 'd36ba3ade96147a5424a6428174f558a', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('10', '10', '10', null, null);
INSERT INTO `t_metadata_rel` VALUES ('11', '11', '10', null, null);
INSERT INTO `t_metadata_rel` VALUES ('11213131313', 'PKz776', '9090', '4', '0');
INSERT INTO `t_metadata_rel` VALUES ('12', '12', '12', null, null);
INSERT INTO `t_metadata_rel` VALUES ('121212', '121212', '1', null, '0');
INSERT INTO `t_metadata_rel` VALUES ('13', '13', '13', null, null);
INSERT INTO `t_metadata_rel` VALUES ('131313', '131313', '2', null, null);
INSERT INTO `t_metadata_rel` VALUES ('14', '14', '14', null, null);
INSERT INTO `t_metadata_rel` VALUES ('141414', '141414', '3', null, '0');
INSERT INTO `t_metadata_rel` VALUES ('15', '15', '10', null, null);
INSERT INTO `t_metadata_rel` VALUES ('151515', '15', '4', null, null);
INSERT INTO `t_metadata_rel` VALUES ('157c2450f7a75071f9298dca08234694', 'uKXdyn', 'e784ecbbe8f5d10710a9917abdb45652', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('161616', '161616', '5', null, null);
INSERT INTO `t_metadata_rel` VALUES ('1912eb27de2ee6aed8d1d36e0d4c5ca5', '1EANY6', 'febfa7979c13c8ed0ffbd12c7db7d7e3', '5', '0');
INSERT INTO `t_metadata_rel` VALUES ('196f2d0a472761edb344e8ef5c340afc', '1Twgre', '9bbcee86238eddc948bea45855f82888', '1', '0');
INSERT INTO `t_metadata_rel` VALUES ('2240f02d08e388a95c145dfb6306a786', 'N83Wpt', '64f0d3787bf6e0964fd42ac0cdbc8852', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('26f391be162e4a1e1f10ec6a56bf81e9', '1vCGx7', 'e784ecbbe8f5d10710a9917abdb45652', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('2c9a681a0e3a55cd470bad1cfee71ead', '08VEhR', '64f0d3787bf6e0964fd42ac0cdbc8852', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('2eed1dce22d7871a88b4255f71e11883', '222222', 'febfa7979c13c8ed0ffbd12c7db7d7e3', '5', '0');
INSERT INTO `t_metadata_rel` VALUES ('2w2w', 'eeeeeee', '5', null, null);
INSERT INTO `t_metadata_rel` VALUES ('30f122b1e8e753917c2671ad32ec2d6c', 'PKz776', 'c137272df31aa9399e9e7d9e978306c5', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('31864eae4e97efd7d8ec8a1a984466d6', 'N83Wpt', '5adf7333f6fbf67fc6edd19b6544fefd', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('31df7fcd88af98478b18d10901358a85', 'uKXdyn', '5030fb0506be7d55e840913eab271c5b', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('333333', '333333', '187a67fd4f47ca56b103e866afb7fdeb', '5', '0');
INSERT INTO `t_metadata_rel` VALUES ('42521ed9e8a14181b37fe88f275ead2a', 'uKXdyn', '64f0d3787bf6e0964fd42ac0cdbc8852', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('52a94b5d45d4f4a3c8fc1d63009fff2f', '1vCGx7', '64f0d3787bf6e0964fd42ac0cdbc8852', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('539076b46fb898dd7e5334c0f59d30a6', 'usx41e', '660d548f84c0d819caf197e2c8ec698a', '1', '0');
INSERT INTO `t_metadata_rel` VALUES ('541d982703311f71fef2f6c91a097752', '08VEhR', '5adf7333f6fbf67fc6edd19b6544fefd', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('59fd23771777d9b705222ac047fe9eae', 'N83Wpt', 'c137272df31aa9399e9e7d9e978306c5', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('5fbd96a1ac36dcc66ea29a4135e43c79', '666666', '187a67fd4f47ca56b103e866afb7fdeb', '5', '1');
INSERT INTO `t_metadata_rel` VALUES ('6459421b9f79e59895e52b02131fab9f', '1vCGx7', '5030fb0506be7d55e840913eab271c5b', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('66796579b06925545e88e9ba8b6a6674', '6Jm9tY', 'febfa7979c13c8ed0ffbd12c7db7d7e3', '5', '0');
INSERT INTO `t_metadata_rel` VALUES ('69d00b7705ce1c17fb2b88f6f2ddfa1b', 'HdmWvc', '1a85bb7caf5aa67d9389374faab218dd', '0', '0');
INSERT INTO `t_metadata_rel` VALUES ('6ffa65fcd50f92648a96426acc8475e8', '1vCGx7', 'd36ba3ade96147a5424a6428174f558a', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('75094be825b9dd922f2e94bd25c11e72', 'xUCQhg', 'c6d5f9965f5abf05636d8725ebaa5f86', '1', '0');
INSERT INTO `t_metadata_rel` VALUES ('79e28baf11621aa91e29493da94dde53', 'MpYtTs', '899ede81a53e31bfc085c50dc3b6b845', '1', '0');
INSERT INTO `t_metadata_rel` VALUES ('81a7dc7a97b7a83763b8b58c571914fe', 'PKz776', '64f0d3787bf6e0964fd42ac0cdbc8852', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('825ed737400a67a74ba44713fb2c64f6', 'rEupX5', 'f532dc91971dbab728c3e2c5f75adb87', '1', '0');
INSERT INTO `t_metadata_rel` VALUES ('9090', '9090', '9090', null, '1');
INSERT INTO `t_metadata_rel` VALUES ('92a844adf7ab987f90ea7f12351610e7', '70', 'f445cf34a5f0984159e386385f9f1f42', '5', '0');
INSERT INTO `t_metadata_rel` VALUES ('a440773ff59ed1b16407d72df2cdf5d7', '777777', '187a67fd4f47ca56b103e866afb7fdeb', '5', '0');
INSERT INTO `t_metadata_rel` VALUES ('a939d4efffad8a1e0960989973de153a', '08VEhR', '5030fb0506be7d55e840913eab271c5b', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('adaeqfew', '10001', '333333', '4', null);
INSERT INTO `t_metadata_rel` VALUES ('adawdadae', '10002', '333333', '4', null);
INSERT INTO `t_metadata_rel` VALUES ('adfadfas', '7pseHA', '111111', null, '1');
INSERT INTO `t_metadata_rel` VALUES ('b148104b5055a1fdc4eb78ffb25e1d3c', 'WGoDBw', '0e43e718ca609676485232e83779ac7a', '0', '0');
INSERT INTO `t_metadata_rel` VALUES ('bb6223ab03de28e052241171b7637715', '80', 'f445cf34a5f0984159e386385f9f1f42', '5', '0');
INSERT INTO `t_metadata_rel` VALUES ('c2c6e81d79617abb6d7923b593638844', 'uKXdyn', 'd36ba3ade96147a5424a6428174f558a', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('c7ed76f5b988043545f13e66b0e91f76', 'HdmWvc', 'a3bcd9fb31e16a4fd585f614dcd8a486', '0', '0');
INSERT INTO `t_metadata_rel` VALUES ('dssdfsdsf', '10000', '333333', '4', null);
INSERT INTO `t_metadata_rel` VALUES ('e27e55cc58d188f85070d03b471b5f5d', '888888', 'a8b74c8e08aa59582c5f676cf0f7a119', '5', '0');
INSERT INTO `t_metadata_rel` VALUES ('ed3fe40e628bf14843913f0554987e68', '90', 'a8b74c8e08aa59582c5f676cf0f7a119', '5', '0');
INSERT INTO `t_metadata_rel` VALUES ('eeb0ddf1042986522c763ab6383aeb32', 'PKz776', '5adf7333f6fbf67fc6edd19b6544fefd', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('f6ef74b75045ef8046e349b6cee651f9', '08VEhR', 'd36ba3ade96147a5424a6428174f558a', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('fd0a2560a73d93ea1befc9cd88dfa180', 'PKz776', 'd36ba3ade96147a5424a6428174f558a', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('fed17165e13bf03a352a61097369b055', '08VEhR', 'e784ecbbe8f5d10710a9917abdb45652', '2', '0');
INSERT INTO `t_metadata_rel` VALUES ('rqwer131434tqwdfa1', 'I32OzH', '1faf41rqfqwrwa', '1', '0');
INSERT INTO `t_metadata_rel` VALUES ('sdwqqwdqewqweqq2eqweddwqdq3', '1vCGx7', '111111', '4', '0');
INSERT INTO `t_metadata_rel` VALUES ('t_mate_r12312', '999999', '222222', '4', '1');
INSERT INTO `t_metadata_rel` VALUES ('zcxzcz', '100', '111', null, null);
INSERT INTO `t_metadata_rel` VALUES ('zxczxcsads31', '9autw9', '333333', '0', '1');

-- ----------------------------
-- Table structure for t_mirror_weightplate
-- ----------------------------
DROP TABLE IF EXISTS `t_mirror_weightplate`;
CREATE TABLE `t_mirror_weightplate` (
  `u_id` varchar(32) NOT NULL,
  `mirror_id` varchar(64) DEFAULT NULL,
  `weight_plate_id` varchar(128) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_mirror_weightplate
-- ----------------------------

-- ----------------------------
-- Table structure for t_order_address
-- ----------------------------
DROP TABLE IF EXISTS `t_order_address`;
CREATE TABLE `t_order_address` (
  `u_id` varchar(32) NOT NULL,
  `link_name` varchar(20) DEFAULT NULL,
  `link_tel` varchar(32) DEFAULT NULL,
  `link_province` varchar(20) DEFAULT NULL,
  `link_city` varchar(20) DEFAULT NULL,
  `link_area` varchar(50) DEFAULT NULL,
  `link_address` varchar(200) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址表';

-- ----------------------------
-- Records of t_order_address
-- ----------------------------
INSERT INTO `t_order_address` VALUES ('1', '庄大', '12332112311', null, null, null, '福建省厦门市思明区', 'RtLs8E', '2017-08-01 11:51:20', '2017-08-01 11:51:28');
INSERT INTO `t_order_address` VALUES ('2', '庄二', '12332112311', null, null, null, '福建省厦门市湖里区', 'RtLs8E', '2017-08-01 11:51:20', '2017-08-01 11:51:28');
INSERT INTO `t_order_address` VALUES ('3154ba4e32f836b876df29cae01cf3f9', '个6v一份6', '8505', null, null, null, '内蒙古 呼和浩特市 新城区I谢婷婷凸显虚脱', 'i8Xc2l', null, null);
INSERT INTO `t_order_address` VALUES ('31f45d84d65117acfdc5b426e767c6db', '庄五', '123332111122', null, null, null, '厦门市集美区', 'RtLs8E', '2017-08-01 06:33:52', '2017-08-01 06:33:52');
INSERT INTO `t_order_address` VALUES ('47f4a2bbf4ab995faaa75cb394a9b6c1', '庄三', '123332111122', null, null, null, '厦门市集美区', 'RtLs8E', null, null);
INSERT INTO `t_order_address` VALUES ('55cacd7d117c2c82ea680556f0c2414b', '驾校驾校大会计家', '946769', '北京市', '北京市', '东城区', 'v的红酒在你这', '111111', null, null);
INSERT INTO `t_order_address` VALUES ('59dbb89aa2c267961d51b6ddc19f9578', '壮大', '12345678911', '福建省', '厦门市', '思明区', '望海路59号', 'RtLs8E', null, null);
INSERT INTO `t_order_address` VALUES ('642af282cbd4a6c7f87ef0020c009535', '湖人即将', '98669595', null, null, null, '北京市 北京市 东城区活动好好的', 'i8Xc2l', null, null);
INSERT INTO `t_order_address` VALUES ('8bf91ee426f93f95de65093f2a954a58', 'Hxd', '18695696529', '福建省', '厦门市', '思明区', '2wwws', 't8081Q', '2017-08-04 10:04:45', '2017-08-04 10:04:45');
INSERT INTO `t_order_address` VALUES ('991518ac01bdb2dffc76e902e7c06a49', '壮大', '12345678911', null, null, null, '厦门市', 'RtLs8E', '2017-08-03 07:11:29', '2017-08-03 07:11:29');
INSERT INTO `t_order_address` VALUES ('d4270284ee05193d10d21b84fb51bcd8', '庄四', '123332111122', null, null, null, '厦门市集美区', 'RtLs8E', null, null);
INSERT INTO `t_order_address` VALUES ('e7b4cfb2cbfc1ea789433a4b8261a08f', '孔i按时2', '15846735488', '湖北省', '武汉市', '江岸区', '电脑待机i大家都难道你\n', 'i8Xc2l', null, null);
INSERT INTO `t_order_address` VALUES ('f7161aff336ea5b40cff59abefc474a9', '庄四', '11111111112', null, null, null, '厦门市湖里区', 'RtLs8E', '2017-08-02 07:29:33', '2017-08-02 07:29:33');

-- ----------------------------
-- Table structure for t_process_order
-- ----------------------------
DROP TABLE IF EXISTS `t_process_order`;
CREATE TABLE `t_process_order` (
  `u_id` varchar(32) NOT NULL,
  `process_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '处理时间',
  `process_content` varchar(500) DEFAULT NULL COMMENT '处理内容',
  `process_user_id` varchar(32) DEFAULT NULL COMMENT '处理人id',
  `order_id` varchar(32) DEFAULT NULL COMMENT '所属订单id',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单处理表\r\n处理内容如果店家没有可以去添加，直接默认状态改变记录';

-- ----------------------------
-- Records of t_process_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_shop
-- ----------------------------
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `u_id` varchar(32) NOT NULL DEFAULT '' COMMENT '店铺主键 id',
  `shop_name` varchar(64) DEFAULT NULL COMMENT '店铺名称',
  `principal` varchar(20) DEFAULT NULL COMMENT '负责人名称',
  `principal_tel` varchar(36) DEFAULT NULL COMMENT '负责人电话',
  `legal_name` varchar(20) DEFAULT NULL COMMENT '法人',
  `business_url` varchar(32) DEFAULT NULL COMMENT '营业执照 url ,与 t_metadata_rel 关系表对应',
  `address` varchar(100) DEFAULT NULL COMMENT '店铺详细地址',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域编码',
  `brand_id` varchar(32) DEFAULT NULL COMMENT '品牌编码',
  `logo` varchar(32) DEFAULT NULL COMMENT '店铺 LOGO，与 t_metadata_rel 关系表 u_id 关联取得 LOGO 图片',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `creator_id` varchar(32) DEFAULT NULL COMMENT '所属用户 id，创建者 id（该店铺归属于哪个用户，与 s_user 的 u_id 关联）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  `last_change_user` varchar(32) DEFAULT NULL COMMENT '最后一次更变的用户id',
  PRIMARY KEY (`u_id`),
  KEY `idx_id` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商店表';

-- ----------------------------
-- Records of t_shop
-- ----------------------------
INSERT INTO `t_shop` VALUES ('21', '优衣库（思明）', '初二', '1234578900', '赵四', 'RlVmDd', '思明区', 'f21b84df5e1f354abd997bdc56e9799e', null, 'FXpCgQ', null, 'noTTpW', '2017-07-27 14:33:11', '2017-07-27 14:33:11', 'noTTpW');
INSERT INTO `t_shop` VALUES ('f445cf34a5f0984159e386385f9f1f42', '优衣库（湖里）', '初一', '1234567890', '赵四', '7pseHA', '湖里区', '00e6383b9f520db9f227d4948a5d6b4f', null, '1EANY6', null, null, '2017-07-25 15:51:37', '2017-07-25 15:51:37', 'noTTpW');

-- ----------------------------
-- Table structure for t_shop_decoration
-- ----------------------------
DROP TABLE IF EXISTS `t_shop_decoration`;
CREATE TABLE `t_shop_decoration` (
  `u_id` varchar(32) NOT NULL COMMENT '店铺装修主键 id',
  `shop_id` varchar(32) DEFAULT NULL COMMENT '店铺id',
  `content` text COMMENT '店铺图文信息内容',
  `img1` varchar(32) DEFAULT NULL,
  `img2` varchar(32) DEFAULT NULL,
  `img3` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`),
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商店装修表  万物皆可varchar';

-- ----------------------------
-- Records of t_shop_decoration
-- ----------------------------
INSERT INTO `t_shop_decoration` VALUES ('febfa7979c13c8ed0ffbd12c7db7d7e3', 'f445cf34a5f0984159e386385f9f1f42', '<p>湖里<br/></p>', 'USnoEN', 'mLjMpR', 'Db4Iod');

-- ----------------------------
-- Table structure for t_shop_device
-- ----------------------------
DROP TABLE IF EXISTS `t_shop_device`;
CREATE TABLE `t_shop_device` (
  `u_id` varchar(32) NOT NULL,
  `shop_id` varchar(32) NOT NULL COMMENT '店铺Id',
  `device_id` varchar(32) NOT NULL COMMENT '设备id',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '匹配时间',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺 设备关系表';

-- ----------------------------
-- Records of t_shop_device
-- ----------------------------
INSERT INTO `t_shop_device` VALUES ('qieuq131g', 'a8b74c8e08aa59582c5f676cf0f7a119', 'fc7c900245c76fc87373f57ab934f00e', '2017-08-10 14:35:51');

-- ----------------------------
-- Table structure for t_shop_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_shop_goods`;
CREATE TABLE `t_shop_goods` (
  `u_id` varchar(32) NOT NULL,
  `shop_id` varchar(32) NOT NULL,
  `goods_id` varchar(32) NOT NULL,
  PRIMARY KEY (`u_id`),
  KEY `id_idx` (`u_id`),
  KEY `shopId_idx` (`shop_id`,`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品-店铺 关联表';

-- ----------------------------
-- Records of t_shop_goods
-- ----------------------------
INSERT INTO `t_shop_goods` VALUES ('3a891134801cdc3a16f9df9d2f2a1572', '21', '432216b7ce3cf2f60531e230acc4f7e9');
INSERT INTO `t_shop_goods` VALUES ('128e53107905fb5c4ee8e4a9147ed721', '21', 'aabf249adcabb387ad419bfae9084b03');
INSERT INTO `t_shop_goods` VALUES ('65c8af6baeec4d3c2c7d300695376594', 'f445cf34a5f0984159e386385f9f1f42', '432216b7ce3cf2f60531e230acc4f7e9');
INSERT INTO `t_shop_goods` VALUES ('9b0af123c88b19d78e9b326b5058992c', 'f445cf34a5f0984159e386385f9f1f42', 'aabf249adcabb387ad419bfae9084b03');

-- ----------------------------
-- Table structure for t_shop_user
-- ----------------------------
DROP TABLE IF EXISTS `t_shop_user`;
CREATE TABLE `t_shop_user` (
  `u_id` varchar(32) NOT NULL COMMENT '主键 id',
  `user_id` varchar(32) NOT NULL,
  `shop_id` varchar(32) NOT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商店用户关系表';

-- ----------------------------
-- Records of t_shop_user
-- ----------------------------
INSERT INTO `t_shop_user` VALUES ('1', 'RtLs8E', 'a8b74c8e08aa59582c5f676cf0f7a119');

-- ----------------------------
-- Table structure for t_sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_notice`;
CREATE TABLE `t_sys_notice` (
  `u_id` varchar(32) NOT NULL,
  `notice_title` varchar(100) DEFAULT NULL COMMENT '标题',
  `notice_time` datetime DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间',
  `user_id` varchar(32) DEFAULT NULL COMMENT '发布用户id',
  `notice_text` blob COMMENT '内容',
  `notice_type` int(11) DEFAULT NULL COMMENT '公告类型（1平台2店铺3代理商4品牌商',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统公告表';

-- ----------------------------
-- Records of t_sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_suggest
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_suggest`;
CREATE TABLE `t_sys_suggest` (
  `u_id` varchar(32) NOT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `notes` varchar(200) DEFAULT NULL COMMENT '反馈内容',
  `create_user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='意见反馈';

-- ----------------------------
-- Records of t_sys_suggest
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_account
-- ----------------------------
DROP TABLE IF EXISTS `t_user_account`;
CREATE TABLE `t_user_account` (
  `u_id` varchar(32) NOT NULL,
  `balance` decimal(10,2) DEFAULT NULL COMMENT '钱包余额',
  `user_id` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账号余额';

-- ----------------------------
-- Records of t_user_account
-- ----------------------------
INSERT INTO `t_user_account` VALUES ('xcsfd', '10000.00', '111111', '2017-07-27 02:00:51', '2017-07-27 02:00:51');

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `u_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL COMMENT '与用户表关联',
  `payment_password` varchar(32) DEFAULT NULL COMMENT '支付密码（MD5(明文密码) 加密） ',
  `name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别 0 女 1 男 2 其他',
  `height` decimal(10,0) DEFAULT NULL COMMENT '身高（cm)',
  `weight` decimal(10,0) DEFAULT NULL COMMENT '体重（kg)',
  `telephone` varchar(32) DEFAULT NULL,
  `avatar_id` varchar(32) DEFAULT NULL COMMENT '头像 跟 t_metadata_rel.record_id 关联',
  `default_address` varchar(32) DEFAULT NULL COMMENT '默认地址Id',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`u_id`),
  KEY `ix_idx` (`u_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户扩展信息表';

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('12332', '333333', null, 'xiaohua', '24', '0', '140', '40', null, '3', null, '2017-08-09 23:12:18', '2017-08-09 23:12:18');
INSERT INTO `t_user_info` VALUES ('23333', '555555', null, 'zhuangda', '20', '0', '145', '59', null, '10', '991518ac01bdb2dffc76e902e7c06a49', '2017-08-09 23:12:18', '2017-08-09 23:12:18');
INSERT INTO `t_user_info` VALUES ('23444', '222222', null, 'xiaoli', '34', '1', '150', '60', null, '2', null, '2017-08-11 04:02:18', '2017-08-11 04:02:18');
INSERT INTO `t_user_info` VALUES ('23456', '444444', null, 'xiaoming', '30', '1', '160', '78', null, '4', null, '2017-08-09 23:14:10', '2017-08-09 23:14:10');
INSERT INTO `t_user_info` VALUES ('55555', '111111', null, 'xianghugui', '24', '0', '180', '60', null, '1', '55cacd7d117c2c82ea680556f0c2414b', '2017-08-11 08:00:56', '2017-08-11 08:00:56');
INSERT INTO `t_user_info` VALUES ('a088f8de14ae58f327a02827cd7a6427', 'U2ksfR', null, 'sdsf', '14', '1', '175', '100', '17605080105', null, null, '2017-08-11 04:02:18', '2017-08-11 04:02:18');
INSERT INTO `t_user_info` VALUES ('gVsYSc', 'ybmat0', null, 'sdfs', '26', '1', '190', '90', '12345678911', null, null, '2017-08-11 04:02:18', '2017-08-11 04:02:18');
INSERT INTO `t_user_info` VALUES ('RtLs8e', 'RtLs8E', null, 'zjf', '24', '0', '100', '150', null, '1a85bb7caf5aa67d9389374faab218dd', null, '2017-08-11 06:38:16', '2017-08-11 06:38:16');
INSERT INTO `t_user_info` VALUES ('xcasd', 't8081Q', null, 'Eeee', '24', '0', '188', '65', null, '0e43e718ca609676485232e83779ac7a', '8bf91ee426f93f95de65093f2a954a58', '2017-08-11 07:06:31', '2017-08-11 07:06:31');
INSERT INTO `t_user_info` VALUES ('YThv5x', 'YThv5x', null, 'sendya', '24', '0', '178', '55', null, 'null', null, '2017-08-09 23:14:10', '2017-08-09 23:14:10');

-- ----------------------------
-- Table structure for t_user_privacy
-- ----------------------------
DROP TABLE IF EXISTS `t_user_privacy`;
CREATE TABLE `t_user_privacy` (
  `u_id` varchar(32) NOT NULL,
  `video_access` int(11) DEFAULT NULL COMMENT '试衣视频公开设置',
  `show_access` int(11) DEFAULT NULL COMMENT '亮衣圈显示设置',
  `user_id` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户隐私设置表（视频公开状态1向所有人公开2仅向好友公开3不公开\r\n亮衣圈状态1展示所有人动态2仅展示好友动态）';

-- ----------------------------
-- Records of t_user_privacy
-- ----------------------------

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video` (
  `u_id` varchar(32) NOT NULL,
  `video_url` varchar(200) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `device_id` varchar(32) DEFAULT NULL,
  `device_owner_id` varchar(32) DEFAULT NULL,
  `status` int(4) DEFAULT NULL COMMENT '0 表示禁用，1 解禁，9 删除（后端）  前端视频状态（2 表示不公开, 3表示公开）',
  `like_num` int(11) DEFAULT NULL COMMENT '视频点赞数',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频储存表';

-- ----------------------------
-- Records of t_video
-- ----------------------------
INSERT INTO `t_video` VALUES ('111111', null, '2017-08-11 02:56:04', null, null, '3', '100');
INSERT INTO `t_video` VALUES ('222222', null, '2017-08-11 02:56:04', null, null, '3', '232');
INSERT INTO `t_video` VALUES ('333333', null, '2017-08-11 02:56:04', '126488cdf4b813b11f6917ebddc88e5a', null, '3', '2322');
INSERT INTO `t_video` VALUES ('9090', null, '2017-08-11 03:08:54', null, null, '3', '2332');

-- ----------------------------
-- Table structure for t_video_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_video_goods`;
CREATE TABLE `t_video_goods` (
  `u_id` varchar(32) NOT NULL,
  `goodsspec_id` varchar(32) DEFAULT NULL COMMENT '商品规格id',
  `video_id` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频商品关联表';

-- ----------------------------
-- Records of t_video_goods
-- ----------------------------
INSERT INTO `t_video_goods` VALUES ('1111', '1', '333333', '2017-08-03 03:48:11', '2017-08-03 03:48:11', null);
INSERT INTO `t_video_goods` VALUES ('dfdcsd', '3', '9090', '2017-08-11 05:00:48', '2017-08-11 05:00:48', null);
INSERT INTO `t_video_goods` VALUES ('dfsd', '3', '222222', '2017-08-11 05:00:48', '2017-08-11 05:00:48', null);
INSERT INTO `t_video_goods` VALUES ('sdf', '3', '111111', '2017-08-03 03:48:11', '2017-08-03 03:48:11', null);

-- ----------------------------
-- Table structure for t_video_goods_fit
-- ----------------------------
DROP TABLE IF EXISTS `t_video_goods_fit`;
CREATE TABLE `t_video_goods_fit` (
  `u_id` varchar(32) NOT NULL,
  `video_good_id` varchar(32) DEFAULT NULL COMMENT '视频商品归属id',
  `is_fit` int(11) DEFAULT NULL COMMENT '是否相符',
  `report_user_id` varchar(32) DEFAULT NULL COMMENT '举荐人',
  `report_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '举荐时间',
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频商品符合表';

-- ----------------------------
-- Records of t_video_goods_fit
-- ----------------------------

-- ----------------------------
-- Table structure for t_video_post
-- ----------------------------
DROP TABLE IF EXISTS `t_video_post`;
CREATE TABLE `t_video_post` (
  `u_id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `video_id` varchar(32) DEFAULT NULL,
  `post_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间',
  `like_num` int(11) DEFAULT NULL COMMENT '点赞数量',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频发布表';

-- ----------------------------
-- Records of t_video_post
-- ----------------------------
INSERT INTO `t_video_post` VALUES ('121qw', '111111', '333333', '2017-08-01 10:04:00', '2323231');
INSERT INTO `t_video_post` VALUES ('12312', '111111', '111111', '2017-08-04 06:09:09', '13234242');
INSERT INTO `t_video_post` VALUES ('123121', '222222', '222222', '2017-08-10 08:53:42', '2243');
INSERT INTO `t_video_post` VALUES ('sdsfds', '333333', '9090', '2017-08-04 06:09:09', '2243');

-- ----------------------------
-- Table structure for t_video_user
-- ----------------------------
DROP TABLE IF EXISTS `t_video_user`;
CREATE TABLE `t_video_user` (
  `u_id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `video_id` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL,
  `shop_id` varchar(32) DEFAULT NULL,
  `goods_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频归属表';

-- ----------------------------
-- Records of t_video_user
-- ----------------------------
INSERT INTO `t_video_user` VALUES ('222222', '111111', '333333', '2017-08-10 02:22:25', '2017-08-10 05:58:54', null, '21', '1');
INSERT INTO `t_video_user` VALUES ('444444', '111111', '222222', '2017-08-04 05:58:54', '2017-08-04 05:58:54', null, '22', '2');
INSERT INTO `t_video_user` VALUES ('sdsfds', '333333', '9090', '2017-08-04 06:29:35', '2017-08-04 06:29:35', null, '21', '1');
INSERT INTO `t_video_user` VALUES ('wdqdasdas', '111111', '111111', '2017-08-04 06:29:35', '2017-08-04 06:29:35', null, '23', '3');

-- ----------------------------
-- Table structure for t_weight_plate
-- ----------------------------
DROP TABLE IF EXISTS `t_weight_plate`;
CREATE TABLE `t_weight_plate` (
  `u_id` varchar(32) NOT NULL,
  `title` varchar(64) DEFAULT NULL COMMENT '设备标题',
  `seq_code` varchar(128) DEFAULT NULL COMMENT '设备序列号',
  `code` varchar(10) DEFAULT NULL COMMENT '设备编码',
  `factory_date` datetime DEFAULT NULL COMMENT '出厂日期',
  `production_time` datetime DEFAULT NULL COMMENT '生产时间',
  `usage_time` datetime DEFAULT NULL COMMENT '投入市场时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '其他备注',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_change_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='体重盘';

-- ----------------------------
-- Records of t_weight_plate
-- ----------------------------
