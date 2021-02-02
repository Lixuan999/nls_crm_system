/*
Navicat MySQL Data Transfer

Source Server         : 88
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : ckf_crm

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-06-20 01:18:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
                            `business_id` int(11) NOT NULL AUTO_INCREMENT,
                            `b_name` varchar(255) COLLATE utf8_bin NOT NULL,
                            `head` varchar(255) COLLATE utf8_bin NOT NULL,
                            `phone` varchar(255) COLLATE utf8_bin NOT NULL,
                            `description` varchar(255) COLLATE utf8_bin NOT NULL,
                            `create_time` datetime NOT NULL,
                            `update_time` datetime NOT NULL,
                            `is_del` int(11) NOT NULL,
                            PRIMARY KEY (`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES ('1', '制作微信小程序', '陈某', '13018596325', '争取一个月内做好', '2020-05-04 11:28:10', '2020-06-08 00:03:50', '0');
INSERT INTO `business` VALUES ('2', '制作ORM员工系统', '陈庄', '13348596325', '争取一个月内做好', '2020-05-04 11:28:10', '2020-05-04 11:28:10', '0');
INSERT INTO `business` VALUES ('3', '制作OA系统', '张伐', '13018599825', '争取一个月内做好', '2020-05-04 11:28:10', '2020-05-04 11:28:10', '0');
INSERT INTO `business` VALUES ('4', '制作微信小游戏', '陈飞', '13078596325', '争取一个月内做好', '2020-05-04 11:28:10', '2020-05-04 11:28:10', '0');
INSERT INTO `business` VALUES ('5', '制作美团外卖小程序', '陈成功', '13218596325', '争取一个月内做好', '2020-05-04 11:28:11', '2020-05-04 14:27:50', '0');
INSERT INTO `business` VALUES ('6', '制作微信小程序', '陈某', '13018596325', '争取一个月内做好', '2020-06-10 00:29:17', '2020-06-10 00:29:17', '0');
INSERT INTO `business` VALUES ('7', '制作ORM员工系统', '陈庄', '13348596325', '争取一个月内做好', '2020-06-10 00:29:17', '2020-06-10 00:29:17', '0');
INSERT INTO `business` VALUES ('8', '制作OA系统', '张伐', '13018599825', '争取一个月内做好', '2020-06-10 00:29:17', '2020-06-10 00:29:17', '0');
INSERT INTO `business` VALUES ('9', '制作微信小游戏', '陈飞', '13078596325', '争取一个月内做好', '2020-06-10 00:29:17', '2020-06-10 00:29:17', '0');
INSERT INTO `business` VALUES ('10', '制作美团外卖小程序', '陈成功', '13218596325', '争取一个月内做好', '2020-06-10 00:29:18', '2020-06-10 00:29:18', '0');

-- ----------------------------
-- Table structure for consult
-- ----------------------------
DROP TABLE IF EXISTS `consult`;
CREATE TABLE `consult` (
                           `consult_id` int(11) NOT NULL AUTO_INCREMENT,
                           `c_consult_content` varchar(200) COLLATE utf8_bin NOT NULL,
                           `c_name` varchar(20) COLLATE utf8_bin NOT NULL,
                           `c_sex` varchar(20) COLLATE utf8_bin NOT NULL,
                           `c_phone` varchar(20) COLLATE utf8_bin NOT NULL,
                           `create_time` datetime NOT NULL,
                           `update_time` datetime NOT NULL,
                           `is_del` int(11) NOT NULL,
                           PRIMARY KEY (`consult_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of consult
-- ----------------------------
INSERT INTO `consult` VALUES ('1', '可以加盟吗？', 'ckf', '男', '13018596132', '2020-06-01 21:54:47', '2020-06-01 21:54:47', '0');
INSERT INTO `consult` VALUES ('2', '连锁的吗？', 'chen', '男', '1818547032', '2020-06-01 21:54:47', '2020-06-01 21:54:47', '0');
INSERT INTO `consult` VALUES ('3', '可以加盟吗？', 'makie', '女', '15018567332', '2020-06-01 21:54:47', '2020-06-01 21:54:47', '0');
INSERT INTO `consult` VALUES ('4', '哪里的呢？', 'bog', '女', '12015676132', '2020-06-01 21:54:48', '2020-06-01 21:54:48', '0');
INSERT INTO `consult` VALUES ('5', '....', 'hellme', '男', '13018592342', '2020-06-01 21:54:48', '2020-06-01 21:54:48', '0');
INSERT INTO `consult` VALUES ('6', '可以加盟吗？', 'ckf', '男', '13018596132', '2020-06-10 00:46:31', '2020-06-10 00:46:31', '0');
INSERT INTO `consult` VALUES ('7', '连锁的吗？', 'chen', '男', '1818547032', '2020-06-10 00:46:31', '2020-06-10 00:46:31', '0');
INSERT INTO `consult` VALUES ('8', '可以加盟吗？', 'makie', '女', '15018567332', '2020-06-10 00:46:32', '2020-06-10 00:46:32', '0');
INSERT INTO `consult` VALUES ('9', '哪里的呢？', 'bog', '女', '12015676132', '2020-06-10 00:46:32', '2020-06-10 00:46:32', '0');
INSERT INTO `consult` VALUES ('10', '....', 'hellme', '男', '13018592342', '2020-06-10 00:46:32', '2020-06-10 00:46:32', '0');
INSERT INTO `consult` VALUES ('11', '我要加盟可以吗？', '陈克丰', '女', '15121212412', '2020-06-11 23:21:29', '2020-06-11 23:21:29', '0');
INSERT INTO `consult` VALUES ('12', '我要加盟可以吗？', '陈克丰', '女', '15121212412', '2020-06-11 23:21:46', '2020-06-11 23:21:46', '0');
INSERT INTO `consult` VALUES ('13', '。。。', '陈克丰', '男', '15121212412', '2020-06-11 23:24:00', '2020-06-11 23:24:00', '0');
INSERT INTO `consult` VALUES ('14', '。。', '陈克丰', '男', '15121212412', '2020-06-11 23:28:59', '2020-06-11 23:28:59', '0');
INSERT INTO `consult` VALUES ('15', '。。。', '陈克丰', '男', '15121212412', '2020-06-11 23:30:32', '2020-06-11 23:30:32', '0');
INSERT INTO `consult` VALUES ('16', '。。。', '陈克丰', '男', '13018567958', '2020-06-11 23:42:16', '2020-06-11 23:42:16', '0');

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
                           `contact_id` int(11) NOT NULL AUTO_INCREMENT,
                           `way` varchar(255) COLLATE utf8_bin NOT NULL,
                           `content` varchar(255) COLLATE utf8_bin NOT NULL,
                           `create_time` datetime NOT NULL,
                           `update_time` datetime NOT NULL,
                           `is_del` int(11) NOT NULL,
                           PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of contact
-- ----------------------------
INSERT INTO `contact` VALUES ('1', '微信', '有空吗？', '2020-05-04 11:28:03', '2020-06-07 21:00:41', '0');
INSERT INTO `contact` VALUES ('2', 'QQ', '有空吗？', '2020-05-04 11:28:03', '2020-06-07 21:02:13', '0');
INSERT INTO `contact` VALUES ('3', '电话', '有空吗？', '2020-05-04 11:28:03', '2020-05-04 11:28:03', '0');
INSERT INTO `contact` VALUES ('4', '微信', '有空吗？', '2020-05-04 11:28:03', '2020-05-04 11:28:03', '0');
INSERT INTO `contact` VALUES ('5', '电话', '有空吗？', '2020-05-04 11:28:03', '2020-05-04 11:28:03', '0');
INSERT INTO `contact` VALUES ('6', '微信', '有空吗？', '2020-06-10 00:09:24', '2020-06-10 00:09:24', '0');
INSERT INTO `contact` VALUES ('7', 'QQ', '有空吗？', '2020-06-10 00:09:24', '2020-06-10 00:09:24', '0');
INSERT INTO `contact` VALUES ('8', '电话', '有空吗？', '2020-06-10 00:09:24', '2020-06-10 00:09:24', '0');
INSERT INTO `contact` VALUES ('9', '微信', '有空吗？', '2020-06-10 00:09:24', '2020-06-10 00:09:24', '0');
INSERT INTO `contact` VALUES ('10', '电话', '有空吗？', '2020-06-10 00:09:24', '2020-06-10 00:09:24', '0');

-- ----------------------------
-- Table structure for con_emp_cus
-- ----------------------------
DROP TABLE IF EXISTS `con_emp_cus`;
CREATE TABLE `con_emp_cus` (
                               `con_id` int(11) NOT NULL,
                               `emp_id` int(11) NOT NULL,
                               `cus_id` int(11) NOT NULL,
                               `create_time` datetime NOT NULL,
                               `update_time` datetime NOT NULL,
                               `is_del` int(11) NOT NULL,
                               PRIMARY KEY (`con_id`,`emp_id`,`cus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of con_emp_cus
-- ----------------------------
INSERT INTO `con_emp_cus` VALUES ('1', '6', '2', '2020-05-04 11:28:06', '2020-06-07 21:00:41', '0');
INSERT INTO `con_emp_cus` VALUES ('2', '6', '2', '2020-05-04 11:28:06', '2020-06-07 21:02:13', '0');
INSERT INTO `con_emp_cus` VALUES ('3', '6', '4', '2020-05-04 11:28:06', '2020-05-04 11:28:06', '0');
INSERT INTO `con_emp_cus` VALUES ('4', '6', '2', '2020-05-04 11:28:06', '2020-05-04 11:28:06', '0');
INSERT INTO `con_emp_cus` VALUES ('5', '6', '2', '2020-05-04 11:28:06', '2020-05-04 11:28:06', '0');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
                            `customer_id` int(11) NOT NULL AUTO_INCREMENT,
                            `customer_name` varchar(255) COLLATE utf8_bin NOT NULL,
                            `sex` varchar(50) COLLATE utf8_bin NOT NULL,
                            `phone` varchar(255) COLLATE utf8_bin NOT NULL,
                            `company` varchar(255) COLLATE utf8_bin NOT NULL,
                            `address` varchar(255) COLLATE utf8_bin NOT NULL,
                            `is_orders` int(11) NOT NULL,
                            `create_time` datetime NOT NULL,
                            `update_time` datetime NOT NULL,
                            `is_del` int(11) NOT NULL,
                            PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '陈小姐', '男', '13018596135', '广东科技公司', '广州天河区', '1', '2020-05-04 11:27:57', '2020-06-11 13:43:46', '0');
INSERT INTO `customer` VALUES ('2', '陈先生', '男', '13018596135', '广东科技公司', '广州天河区', '1', '2020-05-04 11:27:57', '2020-06-07 20:57:23', '0');
INSERT INTO `customer` VALUES ('3', '陈某', '男', '13018596135', '广东厚朴公司', '武汉市', '0', '2020-05-04 11:27:57', '2020-05-04 11:27:57', '0');
INSERT INTO `customer` VALUES ('4', '陈经理', '女', '13018596135', '广东科技公司', '广州天河区', '0', '2020-05-04 11:27:58', '2020-05-04 11:27:58', '0');
INSERT INTO `customer` VALUES ('5', '小飞', '男', '13018596135', '广东腾讯公司', '广州黄埔区', '1', '2020-05-04 11:27:58', '2020-05-05 01:33:39', '0');
INSERT INTO `customer` VALUES ('6', '陈克丰', '男', '12332566988', '保密', '广东湛江', '1', '2020-05-05 01:34:46', '2020-05-05 10:51:22', '0');
INSERT INTO `customer` VALUES ('7', '陈克彬', '男', '11121213145', '牛逼it互联网公司', '广东湛江', '1', '2020-05-05 12:39:27', '2020-05-05 12:40:03', '0');
INSERT INTO `customer` VALUES ('8', '陈克丰', '男', '12335569885', '抖音传媒', '广东湛江', '1', '2020-06-07 20:57:57', '2020-06-08 11:24:07', '0');
INSERT INTO `customer` VALUES ('9', '陈小姐', '女', '13018596135', '广东科技公司', '广州天河区', '1', '2020-06-10 00:02:56', '2020-06-10 00:02:56', '0');
INSERT INTO `customer` VALUES ('10', '陈先生', '男', '13018596135', '广东科技公司', '广州天河区', '1', '2020-06-10 00:02:57', '2020-06-10 00:02:57', '0');

-- ----------------------------
-- Table structure for cus_emp
-- ----------------------------
DROP TABLE IF EXISTS `cus_emp`;
CREATE TABLE `cus_emp` (
                           `cus_id` int(11) NOT NULL,
                           `emp_id` int(11) NOT NULL,
                           `create_time` datetime NOT NULL,
                           `update_time` datetime NOT NULL,
                           `is_del` int(11) NOT NULL,
                           PRIMARY KEY (`cus_id`,`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cus_emp
-- ----------------------------
INSERT INTO `cus_emp` VALUES ('1', '4', '2020-05-04 11:28:00', '2020-06-11 13:43:46', '0');
INSERT INTO `cus_emp` VALUES ('2', '5', '2020-05-04 11:28:00', '2020-06-07 20:57:23', '0');
INSERT INTO `cus_emp` VALUES ('3', '6', '2020-05-04 11:28:00', '2020-05-04 11:28:00', '0');
INSERT INTO `cus_emp` VALUES ('4', '5', '2020-05-04 11:28:01', '2020-05-04 11:28:01', '0');
INSERT INTO `cus_emp` VALUES ('5', '5', '2020-05-04 11:28:01', '2020-05-05 01:33:40', '0');
INSERT INTO `cus_emp` VALUES ('6', '2', '2020-05-05 01:34:46', '2020-05-05 10:51:22', '0');
INSERT INTO `cus_emp` VALUES ('7', '2', '2020-05-05 12:39:27', '2020-05-05 12:40:03', '0');
INSERT INTO `cus_emp` VALUES ('8', '4', '2020-06-07 20:57:57', '2020-06-08 11:24:07', '0');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
                              `department_id` int(11) NOT NULL AUTO_INCREMENT,
                              `d_name` varchar(255) COLLATE utf8_bin NOT NULL,
                              `d_manager` varchar(255) COLLATE utf8_bin NOT NULL,
                              `d_population` int(11) NOT NULL,
                              `create_time` datetime NOT NULL,
                              `update_time` datetime NOT NULL,
                              `is_del` int(11) NOT NULL,
                              PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '技术部', '陈成功', '50', '2020-06-09 23:42:19', '2020-06-09 23:42:19', '0');
INSERT INTO `department` VALUES ('2', '销售部', '陈轧辊', '100', '2020-06-09 23:42:19', '2020-06-09 23:42:19', '0');
INSERT INTO `department` VALUES ('3', '咨询部', '陈小姐', '20', '2020-06-09 23:42:19', '2020-06-09 23:42:19', '0');
INSERT INTO `department` VALUES ('4', '广告部', '陈小姐', '20', '2020-06-09 23:42:19', '2020-06-11 16:50:43', '0');
INSERT INTO `department` VALUES ('5', '行政部', '陈小姐', '20', '2020-06-09 23:42:19', '2020-06-09 23:42:19', '0');
INSERT INTO `department` VALUES ('6', '技术部', '陈成功', '50', '2020-06-09 23:42:36', '2020-06-09 23:42:36', '0');
INSERT INTO `department` VALUES ('7', '销售部', '陈轧辊', '100', '2020-06-09 23:42:36', '2020-06-09 23:42:36', '0');
INSERT INTO `department` VALUES ('8', '咨询部', '陈小姐', '20', '2020-06-09 23:42:36', '2020-06-09 23:42:36', '0');
INSERT INTO `department` VALUES ('9', '广告部', '陈小姐', '20', '2020-06-09 23:42:36', '2020-06-09 23:42:36', '0');
INSERT INTO `department` VALUES ('10', '行政部', '陈小姐', '20', '2020-06-09 23:42:37', '2020-06-09 23:42:37', '0');

-- ----------------------------
-- Table structure for dept_role
-- ----------------------------
DROP TABLE IF EXISTS `dept_role`;
CREATE TABLE `dept_role` (
                             `dep_id` int(11) NOT NULL,
                             `role_id` int(11) NOT NULL,
                             `create_time` datetime NOT NULL,
                             `update_time` datetime NOT NULL,
                             `is_del` int(11) NOT NULL,
                             PRIMARY KEY (`dep_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of dept_role
-- ----------------------------
INSERT INTO `dept_role` VALUES ('1', '1', '2020-05-04 11:27:47', '2020-06-07 20:50:57', '0');
INSERT INTO `dept_role` VALUES ('2', '2', '2020-05-04 11:27:47', '2020-05-04 11:27:47', '0');
INSERT INTO `dept_role` VALUES ('3', '3', '2020-05-04 11:27:47', '2020-05-04 11:27:47', '0');
INSERT INTO `dept_role` VALUES ('4', '4', '2020-05-04 11:27:47', '2020-06-11 16:50:43', '0');
INSERT INTO `dept_role` VALUES ('5', '5', '2020-05-04 11:27:47', '2020-06-06 18:01:51', '0');
INSERT INTO `dept_role` VALUES ('6', '5', '2020-06-07 20:50:24', '2020-06-07 20:53:06', '0');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
                            `employee_id` int(11) NOT NULL AUTO_INCREMENT,
                            `emp_name` varchar(50) NOT NULL,
                            `e_pwd` varchar(255) NOT NULL,
                            `salt` varchar(255) DEFAULT NULL,
                            `age` int(11) DEFAULT NULL,
                            `sex` varchar(3) DEFAULT NULL,
                            `phone` varchar(20) DEFAULT NULL,
                            `address` varchar(255) DEFAULT NULL,
                            `create_time` datetime NOT NULL,
                            `update_time` datetime NOT NULL,
                            `is_del` int(11) NOT NULL,
                            PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'admin', 'eb6f99c4600b32aea7b4d5778b4a56a9', 'df3f84aa7b71', '21', '女', '13157896468', '广东省湛江市', '2020-06-10 15:01:16', '2020-06-11 10:37:06', '0');
INSERT INTO `employee` VALUES ('2', 'ckf', 'cd11ee7be2b237afe697574241a347da', '5010597ef27b', '18', '男', '14317896465', '广东省广州市', '2020-06-10 15:01:16', '2020-06-10 15:01:16', '0');
INSERT INTO `employee` VALUES ('3', '陈某', 'f0b32997f97e2c2141b5476944bd488e', '2ddfd3a74e16', '25', '男', '13787896467', '广东省深圳市', '2020-06-10 15:01:16', '2020-06-10 15:01:16', '0');
INSERT INTO `employee` VALUES ('4', 'makie', '1122', '111111', '25', '男', '13787896467', '广东省深圳市', '2020-06-10 15:01:16', '2020-06-10 15:01:16', '0');
INSERT INTO `employee` VALUES ('5', 'aa', '1122', '111111', '25', '男', '13787896467', '广东省深圳市', '2020-06-10 15:01:16', '2020-06-11 17:07:04', '0');
INSERT INTO `employee` VALUES ('6', 'bb', '1122', '111111', '25', '男', '13787896467', '广东省深圳市', '2020-06-10 15:01:17', '2020-06-10 15:01:17', '0');
INSERT INTO `employee` VALUES ('7', 'cc', '1122', '111111', '25', '女', '13787896467', '广东省深圳市', '2020-06-10 15:01:17', '2020-06-11 14:44:21', '0');
INSERT INTO `employee` VALUES ('8', 'ff', '94227c86c48319f07c7bed20aca3be3c', 'b92c18e337fe', '18', '男', '13018596132', '未知', '2020-06-10 15:03:29', '2020-06-11 14:34:31', '0');
INSERT INTO `employee` VALUES ('9', 'gg', '9fd771746c95187039975678f8b48593', 'ed52c43cea53', '18', '男', '12333665555', '广东湛江', '2020-06-10 15:12:12', '2020-06-11 11:49:12', '0');
INSERT INTO `employee` VALUES ('10', 'fdf', '1d8f5160e8880d6118f397b2384148a1', '33d6aeda3af1', '12', '男', '13325655544', '未知', '2020-06-10 15:27:01', '2020-06-11 11:44:20', '0');
INSERT INTO `employee` VALUES ('11', 'ggg', '27831c46461ba979f6d449d66151286e', '88e7106246aa', null, null, '13325655544', null, '2020-06-10 15:31:09', '2020-06-10 15:31:21', '0');
INSERT INTO `employee` VALUES ('12', 'hh1', '337987ce61debf51e32a36dcb04a2c2e', 'f03a34329915', null, null, '13365236598', null, '2020-06-10 15:45:56', '2020-06-10 15:46:19', '0');
INSERT INTO `employee` VALUES ('13', 'hh', 'e7d92a93058ad35666613e371c76e8f5', '6fad26ec249f', null, null, '13365236598', null, '2020-06-10 15:48:05', '2020-06-10 16:12:17', '0');
INSERT INTO `employee` VALUES ('14', 'tt', '463b1c217fabfc5ec293bff81de5c168', '704b936e9cde', null, null, '13365236598', null, '2020-06-10 17:06:52', '2020-06-10 17:06:52', '0');
INSERT INTO `employee` VALUES ('15', 'wwww', '9621f30cef17b29bc781b75c3460c86c', '1de912747983', null, null, '13365236598', null, '2020-06-10 17:07:11', '2020-06-10 17:07:11', '0');
INSERT INTO `employee` VALUES ('16', 'bbb', '1a1523724dd83fda1e5f6b1a4d534633', 'b7d70c25496b', null, null, '13365236598', null, '2020-06-10 17:08:32', '2020-06-10 17:08:32', '0');
INSERT INTO `employee` VALUES ('17', 'oo', 'cbcc7769575702cf0fa0da0e6c257d83', '603069adf99a', null, null, '13365236598', null, '2020-06-10 17:11:47', '2020-06-10 17:12:01', '0');
INSERT INTO `employee` VALUES ('18', 'pp', '8b54c582eb00e7a5405b7f57c05dc6ed', '2d7856c36429', null, null, '13365236598', null, '2020-06-10 17:12:27', '2020-06-10 17:12:27', '0');
INSERT INTO `employee` VALUES ('19', 'll', 'b68aad38367f15ef4cec60ad20e34566', 'd422eb554c75', null, null, '13365236598', null, '2020-06-10 17:14:49', '2020-06-10 17:14:56', '0');
INSERT INTO `employee` VALUES ('20', 'mm', '05db7bf5a3af4187a166ed70d342fe5c', 'fb15b29391b0', null, null, '13365236598', null, '2020-06-10 23:28:39', '2020-06-10 23:28:39', '0');
INSERT INTO `employee` VALUES ('21', 'nn', '71c62ca4e04abbbeb981c1bab841fd84', 'b48c46fba13a', null, null, '12132323232', null, '2020-06-10 23:30:11', '2020-06-10 23:30:11', '0');

-- ----------------------------
-- Table structure for emp_role
-- ----------------------------
DROP TABLE IF EXISTS `emp_role`;
CREATE TABLE `emp_role` (
                            `emp_id` int(11) NOT NULL,
                            `role_id` int(11) NOT NULL,
                            `create_time` datetime NOT NULL,
                            `update_time` datetime NOT NULL,
                            `is_del` int(11) NOT NULL,
                            PRIMARY KEY (`emp_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of emp_role
-- ----------------------------
INSERT INTO `emp_role` VALUES ('1', '1', '2020-05-04 11:27:05', '2020-06-11 10:37:06', '0');
INSERT INTO `emp_role` VALUES ('2', '2', '2020-05-04 11:27:05', '2020-06-06 21:28:15', '0');
INSERT INTO `emp_role` VALUES ('3', '3', '2020-05-04 11:27:05', '2020-06-07 18:05:26', '0');
INSERT INTO `emp_role` VALUES ('4', '3', '2020-05-04 11:27:06', '2020-06-07 22:08:06', '0');
INSERT INTO `emp_role` VALUES ('5', '3', '2020-05-04 11:27:06', '2020-06-11 17:07:04', '0');
INSERT INTO `emp_role` VALUES ('6', '4', '2020-06-11 14:45:44', '2020-06-11 14:45:47', '0');
INSERT INTO `emp_role` VALUES ('7', '4', '2020-06-11 14:46:13', '2020-06-11 14:46:14', '0');
INSERT INTO `emp_role` VALUES ('8', '4', '2020-06-07 17:06:46', '2020-06-11 14:34:31', '0');
INSERT INTO `emp_role` VALUES ('9', '4', '2020-06-07 17:12:37', '2020-06-11 11:49:12', '0');
INSERT INTO `emp_role` VALUES ('10', '4', '2020-06-07 17:14:59', '2020-06-11 11:44:20', '0');
INSERT INTO `emp_role` VALUES ('11', '5', '2020-06-07 17:20:29', '2020-06-07 17:20:29', '0');
INSERT INTO `emp_role` VALUES ('12', '5', '2020-06-07 17:40:16', '2020-06-07 17:40:16', '0');
INSERT INTO `emp_role` VALUES ('13', '4', '2020-06-07 17:50:55', '2020-06-07 17:50:55', '0');
INSERT INTO `emp_role` VALUES ('14', '5', '2020-06-07 18:11:40', '2020-06-07 18:11:40', '0');

-- ----------------------------
-- Table structure for gambit
-- ----------------------------
DROP TABLE IF EXISTS `gambit`;
CREATE TABLE `gambit` (
                          `gambit_id` int(11) NOT NULL AUTO_INCREMENT,
                          `g_headline` varchar(50) COLLATE utf8_bin NOT NULL,
                          `g_auditor` varchar(50) COLLATE utf8_bin NOT NULL,
                          `g_audi_time` varchar(50) COLLATE utf8_bin NOT NULL,
                          `g_founder` varchar(50) COLLATE utf8_bin NOT NULL,
                          `g_check_state` varchar(20) COLLATE utf8_bin NOT NULL,
                          `create_time` datetime NOT NULL,
                          `update_time` datetime NOT NULL,
                          `is_del` tinyint(4) DEFAULT NULL,
                          PRIMARY KEY (`gambit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gambit
-- ----------------------------
INSERT INTO `gambit` VALUES ('1', '家中大佬', 'ckf', '2020-01-18', 'ckf', '未审核', '2020-06-08 15:23:39', '2020-06-08 15:23:39', '0');
INSERT INTO `gambit` VALUES ('2', '爱情的火花', '作者', '2020-01-18', 'mikel', '未审核', '2020-05-04 18:17:59', '2020-06-08 12:39:01', '1');
INSERT INTO `gambit` VALUES ('3', 'bbbqq', '200', '2020-01-18', 'aa', '未审核', '2020-06-08 13:21:40', '2020-06-08 13:21:40', '0');
INSERT INTO `gambit` VALUES ('4', '黑客如何入侵', '作者', '2020-01-18', '陈某', '未审核', '2020-06-08 13:20:29', '2020-06-08 13:20:29', '0');
INSERT INTO `gambit` VALUES ('5', '黑教师级电脑', '2020-01-18', '2020-01-18', '陈欧', '未审核', '2020-05-04 18:17:59', '2020-06-07 17:26:48', '0');
INSERT INTO `gambit` VALUES ('6', 'a', 'a', '2020-05-05', 'd', '未审核', '2020-05-04 22:42:42', '2020-06-08 12:52:15', '0');
INSERT INTO `gambit` VALUES ('7', '发的', '2020-06-02', '2020-06-02', '陈丰富', '未审核', '2020-06-08 13:21:30', '2020-06-08 13:21:30', '0');
INSERT INTO `gambit` VALUES ('8', 'bb', '2020-06-11', '2020-06-11', 'dd', '未审核', '2020-06-06 17:47:16', '2020-06-07 11:17:36', '0');
INSERT INTO `gambit` VALUES ('9', '解决bug', '陈某', '2020-06-07', '陈现结', '未审核', '2020-06-07 11:22:44', '2020-06-07 11:22:44', '0');
INSERT INTO `gambit` VALUES ('10', 'ee', 'ee', '2020-06-07', 'ee', '未审核', '2020-06-07 12:50:11', '2020-06-07 12:50:11', '0');
INSERT INTO `gambit` VALUES ('11', 'ff', 'ff', '2020-06-07', 'ff', '未审核', '2020-06-07 17:07:53', '2020-06-07 17:07:53', '0');
INSERT INTO `gambit` VALUES ('12', 'aa', 'aa', '2020-06-15', 'aa', '未审核', '2020-06-07 17:10:39', '2020-06-07 17:10:39', '0');
INSERT INTO `gambit` VALUES ('13', 'aa', 'aa', '2020-06-01', 'aa', '未审核', '2020-06-07 17:19:01', '2020-06-07 17:19:01', '0');
INSERT INTO `gambit` VALUES ('14', 'aa', 'aa', '2020-06-07', 'aa', '未审核', '2020-06-07 17:24:40', '2020-06-07 17:24:40', '0');
INSERT INTO `gambit` VALUES ('15', 'aa', 'aa', '2020-06-07', 'aa', '未审核', '2020-06-07 17:26:40', '2020-06-07 17:26:40', '0');
INSERT INTO `gambit` VALUES ('16', 'aa', 'aa', '2020-06-07', 'aa', '未审核', '2020-06-07 17:27:01', '2020-06-09 22:19:38', '1');
INSERT INTO `gambit` VALUES ('17', 'aa', 'aa', '2020-06-07', 'fdf', '未审核', '2020-06-07 17:28:00', '2020-06-07 17:28:00', '0');
INSERT INTO `gambit` VALUES ('18', '啊啊', '啊啊', '2020-06-07', 'aa', '未审核', '2020-06-07 18:02:29', '2020-06-07 18:02:29', '0');
INSERT INTO `gambit` VALUES ('19', 'aa', 'aa', '2020-06-07', 'aa', '未审核', '2020-06-07 18:08:40', '2020-06-07 18:08:40', '0');
INSERT INTO `gambit` VALUES ('20', 'aa', 'aa', '2020-06-07', 'aa', '未审核', '2020-06-07 18:09:37', '2020-06-07 18:09:37', '0');
INSERT INTO `gambit` VALUES ('21', 'aa', 'aa', '2020-06-07', 'aa', '未审核', '2020-06-07 18:09:43', '2020-06-07 18:09:43', '0');
INSERT INTO `gambit` VALUES ('22', 'aa', 'aa', '2020-06-07', 'aa', '未审核', '2020-06-07 18:10:27', '2020-06-07 18:10:27', '0');
INSERT INTO `gambit` VALUES ('23', 'aa', 'aa', '2020-06-07', 'aa', '未审核', '2020-06-07 18:11:03', '2020-06-07 18:11:03', '0');
INSERT INTO `gambit` VALUES ('24', 'aa', 'aa', '2020-06-07', 'aa', '未审核', '2020-06-07 18:12:43', '2020-06-07 18:12:43', '0');
INSERT INTO `gambit` VALUES ('25', 'ggg', 'gg', '2020-06-07', 'ff', '未审核', '2020-06-07 18:14:19', '2020-06-20 01:15:10', '0');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
                          `orders_id` int(11) NOT NULL AUTO_INCREMENT,
                          `total_price` double NOT NULL,
                          `create_time` datetime NOT NULL,
                          `update_time` datetime NOT NULL,
                          `is_del` int(11) NOT NULL,
                          PRIMARY KEY (`orders_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '50000', '2020-05-04 11:28:14', '2020-06-09 23:19:41', '0');
INSERT INTO `orders` VALUES ('2', '70000', '2020-05-04 11:28:14', '2020-05-04 11:28:14', '0');
INSERT INTO `orders` VALUES ('3', '90000', '2020-05-04 11:28:14', '2020-05-04 11:28:14', '0');
INSERT INTO `orders` VALUES ('4', '30000', '2020-05-04 11:28:15', '2020-05-04 11:28:15', '0');
INSERT INTO `orders` VALUES ('5', '20000', '2020-05-04 11:28:15', '2020-05-04 11:28:15', '0');
INSERT INTO `orders` VALUES ('6', '2000000', '2020-05-05 01:36:14', '2020-05-05 01:36:14', '0');
INSERT INTO `orders` VALUES ('7', '50000', '2020-06-10 00:37:02', '2020-06-10 00:37:02', '0');
INSERT INTO `orders` VALUES ('8', '70000', '2020-06-10 00:37:02', '2020-06-10 00:37:02', '0');
INSERT INTO `orders` VALUES ('9', '90000', '2020-06-10 00:37:03', '2020-06-10 00:37:03', '0');
INSERT INTO `orders` VALUES ('10', '30000', '2020-06-10 00:37:03', '2020-06-10 00:37:03', '0');
INSERT INTO `orders` VALUES ('11', '20000', '2020-06-10 00:37:03', '2020-06-10 00:37:03', '0');

-- ----------------------------
-- Table structure for ord_bus_cus
-- ----------------------------
DROP TABLE IF EXISTS `ord_bus_cus`;
CREATE TABLE `ord_bus_cus` (
                               `ord_id` int(11) NOT NULL,
                               `bus_id` int(11) NOT NULL,
                               `cus_id` int(11) NOT NULL,
                               `create_time` datetime NOT NULL,
                               `update_time` datetime NOT NULL,
                               `is_del` int(11) NOT NULL,
                               PRIMARY KEY (`ord_id`,`bus_id`,`cus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ord_bus_cus
-- ----------------------------
INSERT INTO `ord_bus_cus` VALUES ('1', '2', '2', '2020-05-04 11:28:19', '2020-06-09 23:19:41', '0');
INSERT INTO `ord_bus_cus` VALUES ('2', '3', '3', '2020-05-04 11:28:19', '2020-05-04 11:28:19', '0');
INSERT INTO `ord_bus_cus` VALUES ('3', '4', '4', '2020-05-04 11:28:19', '2020-05-04 11:28:19', '0');
INSERT INTO `ord_bus_cus` VALUES ('4', '1', '1', '2020-05-04 11:28:19', '2020-05-04 11:28:19', '0');
INSERT INTO `ord_bus_cus` VALUES ('5', '5', '5', '2020-05-04 11:28:20', '2020-05-04 11:28:20', '0');
INSERT INTO `ord_bus_cus` VALUES ('6', '2', '2', '2020-05-05 01:36:14', '2020-05-05 01:36:14', '0');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
                              `permission_id` int(11) NOT NULL AUTO_INCREMENT,
                              `perm_name` varchar(255) COLLATE utf8_bin NOT NULL,
                              `permission` varchar(255) COLLATE utf8_bin NOT NULL,
                              `url` varchar(255) COLLATE utf8_bin NOT NULL,
                              `create_time` datetime NOT NULL,
                              `update_time` datetime NOT NULL,
                              `is_del` int(11) NOT NULL,
                              PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '员工查询', 'emp:list', '/emp/employee', '2020-05-04 15:33:48', '2020-06-06 21:32:41', '0');
INSERT INTO `permission` VALUES ('2', '员工添加', 'emp:add', '/emp/empAdd', '2020-05-04 15:33:48', '2020-05-04 15:33:48', '0');
INSERT INTO `permission` VALUES ('3', '员工修改', 'emp:update', '/emp/empUpdate', '2020-05-04 15:33:48', '2020-06-07 18:25:12', '0');
INSERT INTO `permission` VALUES ('4', '员工删除', 'emp:delete', '/emp/empDelete', '2020-05-04 15:33:48', '2020-05-04 15:33:48', '0');
INSERT INTO `permission` VALUES ('5', '角色查询', 'rol:list', '/rol/role', '2020-05-04 15:33:48', '2020-05-04 15:33:48', '0');
INSERT INTO `permission` VALUES ('6', '角色添加', 'rol:add', '/rol/rolAdd', '2020-05-04 15:33:48', '2020-05-04 15:33:48', '0');
INSERT INTO `permission` VALUES ('7', '角色修改', 'rol:update', '/rol/rolUpdate', '2020-05-04 15:33:48', '2020-05-04 16:03:10', '0');
INSERT INTO `permission` VALUES ('8', '角色删除', 'rol:delete', '/rol/rolDelete', '2020-05-04 15:33:48', '2020-05-04 16:00:26', '0');
INSERT INTO `permission` VALUES ('9', '权限查询', 'per:list', '/per/permission', '2020-05-04 15:33:48', '2020-05-04 15:33:48', '0');
INSERT INTO `permission` VALUES ('10', '权限添加', 'per:add', '/per/perAdd', '2020-05-04 15:33:48', '2020-06-07 18:26:26', '0');
INSERT INTO `permission` VALUES ('11', '权限修改', 'per:update', '/per/perUpdate', '2020-05-04 15:33:48', '2020-05-04 15:33:48', '0');
INSERT INTO `permission` VALUES ('12', '权限删除', 'per:delete', '/per/perDelete', '2020-05-04 15:33:48', '2020-05-04 15:33:48', '0');
INSERT INTO `permission` VALUES ('13', '部门查询', 'dep:list', '/dep/department', '2020-05-04 15:33:48', '2020-05-04 15:33:48', '0');
INSERT INTO `permission` VALUES ('14', '部门添加', 'dep:add', '/dep/depAdd', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('15', '部门修改', 'dep:update', '/dep/depUpdate', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('16', '部门删除', 'dep:delete', '/dep/depDelete', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('17', '客户查询', 'cus:list', '/cus/customer', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('18', '客户添加', 'cus:add', '/cus/cusAdd', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('19', '客户修改', 'cus:update', '/cus/cusUpdate', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('20', '客户删除', 'cus:delete', '/cus/cusDelete', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('21', '客户跟踪查询', 'cont:list', '/cont/contact', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('22', '客户跟踪添加', 'cont:add', '/cont/contAdd', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('23', '客户跟踪修改', 'cont:update', '/cont/contUpdate', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('24', '客户跟踪删除', 'cont:delete', '/cont/contDelete', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('25', '业务查询', 'bus:list', '/bus/business', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('26', '业务添加', 'bus:add', '/bus/busAdd', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('27', '业务修改', 'bus:update', '/bus/busUpdate', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('28', '业务删除', 'bus:delete', '/bus/busDelete', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('29', '订单查询', 'ord:list', '/ord/orders', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('30', '订单添加', 'ord:add', '/ordAdd', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('31', '订单修改', 'ord:update', '/ord/ordUpdate', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('32', '订单删除', 'ord:delete', '/ord/ordDelete', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('33', '文章查询', 'gam:list', '/gam/gambit', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('34', '文章添加', 'gam:add', '/gam/contAdd', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('35', '文章修改', 'gam:update', '/gam/contUpdate', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('36', '文章删除', 'gam:delete', '/gam/contDelete', '2020-05-04 15:33:49', '2020-05-04 15:33:49', '0');
INSERT INTO `permission` VALUES ('37', '咨询查询', 'con:list', '/con/consult', '2020-05-04 15:33:50', '2020-05-04 15:33:50', '0');
INSERT INTO `permission` VALUES ('38', '咨询添加', 'con:add', '/con/conAdd', '2020-05-04 15:33:50', '2020-05-04 15:33:50', '0');
INSERT INTO `permission` VALUES ('39', '咨询修改', 'con:update', 'con/conUpdate', '2020-05-04 15:33:50', '2020-05-04 15:33:50', '0');
INSERT INTO `permission` VALUES ('40', '咨询删除', 'ord:delete', '/con/conDelete', '2020-05-04 15:33:50', '2020-05-04 15:33:50', '0');
INSERT INTO `permission` VALUES ('41', '员工删除查询', 'empdel:list', '/emp/employeeDelList', '2020-05-04 15:33:50', '2020-05-04 15:33:50', '0');
INSERT INTO `permission` VALUES ('42', '正式客户查询', 'offc:list', '/cos/offCustomerList', '2020-05-04 15:33:50', '2020-05-04 15:33:50', '0');
INSERT INTO `permission` VALUES ('43', '多级分类', 'cat:list', '/catList', '2020-05-04 15:33:50', '2020-05-04 15:33:50', '0');
INSERT INTO `permission` VALUES ('44', '管理员查询', 'adm:list', '/emp/adminList', '2020-05-04 15:33:50', '2020-05-04 15:33:50', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `rol_id` int(11) NOT NULL AUTO_INCREMENT,
                        `r_name` varchar(50) COLLATE utf8_bin NOT NULL,
                        `create_time` datetime NOT NULL,
                        `update_time` datetime NOT NULL,
                        `is_del` int(11) NOT NULL,
                        PRIMARY KEY (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '2020-06-11 14:47:41', '2020-06-11 14:47:41', '0');
INSERT INTO `role` VALUES ('2', '董事长', '2020-06-11 14:47:41', '2020-06-11 14:47:41', '0');
INSERT INTO `role` VALUES ('3', '总经理', '2020-06-11 14:47:41', '2020-06-11 14:47:41', '0');
INSERT INTO `role` VALUES ('4', '员工', '2020-06-11 14:47:41', '2020-06-11 14:47:41', '0');
INSERT INTO `role` VALUES ('5', '销售员', '2020-06-11 14:47:41', '2020-06-11 14:47:41', '0');

-- ----------------------------
-- Table structure for role_perm
-- ----------------------------
DROP TABLE IF EXISTS `role_perm`;
CREATE TABLE `role_perm` (
                             `role_id` int(11) NOT NULL,
                             `perm_id` int(11) NOT NULL,
                             `create_time` datetime NOT NULL,
                             `update_time` datetime NOT NULL,
                             `is_del` int(11) NOT NULL,
                             PRIMARY KEY (`role_id`,`perm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role_perm
-- ----------------------------
INSERT INTO `role_perm` VALUES ('1', '1', '2020-05-04 11:27:43', '2020-05-04 11:27:43', '0');
INSERT INTO `role_perm` VALUES ('1', '2', '2020-05-04 11:27:43', '2020-05-04 11:27:43', '0');
INSERT INTO `role_perm` VALUES ('1', '3', '2020-05-04 11:27:43', '2020-05-04 11:27:43', '0');
INSERT INTO `role_perm` VALUES ('1', '4', '2020-05-04 11:27:43', '2020-05-04 11:27:43', '0');
INSERT INTO `role_perm` VALUES ('1', '5', '2020-05-04 11:27:43', '2020-05-04 11:27:43', '0');
INSERT INTO `role_perm` VALUES ('1', '6', '2020-05-04 11:27:43', '2020-05-04 11:27:43', '0');
INSERT INTO `role_perm` VALUES ('1', '7', '2020-05-04 11:27:44', '2020-05-04 11:27:44', '0');
INSERT INTO `role_perm` VALUES ('1', '8', '2020-05-04 11:39:16', '2020-05-04 11:39:19', '0');
INSERT INTO `role_perm` VALUES ('1', '9', '2020-05-04 11:39:31', '2020-05-04 11:39:33', '0');
INSERT INTO `role_perm` VALUES ('1', '10', '2020-05-04 11:39:45', '2020-05-04 11:39:49', '0');
INSERT INTO `role_perm` VALUES ('1', '11', '2020-05-04 11:41:37', '2020-05-04 11:41:37', '0');
INSERT INTO `role_perm` VALUES ('1', '12', '2020-05-04 11:41:37', '2020-05-04 11:41:37', '0');
INSERT INTO `role_perm` VALUES ('1', '13', '2020-05-04 11:41:37', '2020-05-04 11:41:37', '0');
INSERT INTO `role_perm` VALUES ('1', '14', '2020-05-04 11:41:38', '2020-05-04 11:41:38', '0');
INSERT INTO `role_perm` VALUES ('1', '15', '2020-05-04 11:41:38', '2020-05-04 11:41:38', '0');
INSERT INTO `role_perm` VALUES ('1', '16', '2020-05-04 11:41:38', '2020-05-04 11:41:38', '0');
INSERT INTO `role_perm` VALUES ('1', '17', '2020-05-04 11:41:38', '2020-05-04 11:41:38', '0');
INSERT INTO `role_perm` VALUES ('1', '18', '2020-05-04 11:41:38', '2020-05-04 11:41:38', '0');
INSERT INTO `role_perm` VALUES ('1', '19', '2020-05-04 11:41:38', '2020-05-04 11:41:38', '0');
INSERT INTO `role_perm` VALUES ('1', '20', '2020-05-04 11:41:38', '2020-05-04 11:41:38', '0');
INSERT INTO `role_perm` VALUES ('1', '21', '2020-05-04 11:42:38', '2020-05-04 11:42:38', '0');
INSERT INTO `role_perm` VALUES ('1', '22', '2020-05-04 11:42:38', '2020-05-04 11:42:38', '0');
INSERT INTO `role_perm` VALUES ('1', '23', '2020-05-04 11:42:38', '2020-05-04 11:42:38', '0');
INSERT INTO `role_perm` VALUES ('1', '24', '2020-05-04 11:42:39', '2020-05-04 11:42:39', '0');
INSERT INTO `role_perm` VALUES ('1', '25', '2020-05-04 11:42:39', '2020-05-04 11:42:39', '0');
INSERT INTO `role_perm` VALUES ('1', '26', '2020-05-04 11:42:39', '2020-05-04 11:42:39', '0');
INSERT INTO `role_perm` VALUES ('1', '27', '2020-05-04 11:42:39', '2020-05-04 11:42:39', '0');
INSERT INTO `role_perm` VALUES ('1', '28', '2020-05-04 11:42:39', '2020-05-04 11:42:39', '0');
INSERT INTO `role_perm` VALUES ('1', '29', '2020-05-04 11:42:39', '2020-05-04 11:42:39', '0');
INSERT INTO `role_perm` VALUES ('1', '30', '2020-05-04 11:42:39', '2020-05-04 11:42:39', '0');
INSERT INTO `role_perm` VALUES ('1', '31', '2020-05-04 12:23:50', '2020-05-04 12:23:50', '0');
INSERT INTO `role_perm` VALUES ('1', '32', '2020-05-04 12:23:50', '2020-05-04 12:23:50', '0');
INSERT INTO `role_perm` VALUES ('1', '33', '2020-05-04 12:23:50', '2020-05-04 12:23:50', '0');
INSERT INTO `role_perm` VALUES ('1', '34', '2020-05-04 12:23:50', '2020-05-04 12:23:50', '0');
INSERT INTO `role_perm` VALUES ('1', '35', '2020-05-04 12:23:50', '2020-05-04 12:23:50', '0');
INSERT INTO `role_perm` VALUES ('1', '36', '2020-05-04 12:23:50', '2020-05-04 12:23:50', '0');
INSERT INTO `role_perm` VALUES ('1', '37', '2020-05-04 12:23:50', '2020-05-04 12:23:50', '0');
INSERT INTO `role_perm` VALUES ('1', '38', '2020-05-04 12:23:50', '2020-05-04 12:23:50', '0');
INSERT INTO `role_perm` VALUES ('1', '39', '2020-05-04 12:23:50', '2020-05-04 12:23:50', '0');
INSERT INTO `role_perm` VALUES ('1', '40', '2020-05-04 12:23:50', '2020-05-04 12:23:50', '0');
INSERT INTO `role_perm` VALUES ('1', '41', '2020-05-04 12:24:27', '2020-05-04 12:24:27', '0');
INSERT INTO `role_perm` VALUES ('1', '42', '2020-05-04 12:24:27', '2020-05-04 12:24:27', '0');
INSERT INTO `role_perm` VALUES ('1', '43', '2020-05-04 12:24:27', '2020-05-04 12:24:27', '0');
INSERT INTO `role_perm` VALUES ('1', '44', '2020-05-04 12:24:27', '2020-05-04 12:24:27', '0');
INSERT INTO `role_perm` VALUES ('1', '45', '2020-05-04 12:24:27', '2020-05-04 12:24:27', '0');
INSERT INTO `role_perm` VALUES ('6', '33', '2020-05-04 14:34:24', '2020-06-07 13:57:55', '0');
INSERT INTO `role_perm` VALUES ('7', '17', '2020-06-07 18:17:17', '2020-06-07 18:20:42', '0');
