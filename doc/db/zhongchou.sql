/*
Navicat MySQL Data Transfer

Source Server         : localhost_root
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zhongchou

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-11-06 16:45:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `profit_distribution`
-- ----------------------------
DROP TABLE IF EXISTS `profit_distribution`;
CREATE TABLE `profit_distribution` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL COMMENT '项目id',
  `name` varchar(200) NOT NULL COMMENT '项目名字',
  `join_date` varchar(200) NOT NULL COMMENT '加入日期',
  `join_days` varchar(200) NOT NULL COMMENT '加入总天数',
  `predict_distribution` varchar(200) NOT NULL COMMENT '预计分配',
  PRIMARY KEY (`id`),
  UNIQUE KEY `project_id` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of profit_distribution
-- ----------------------------

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL COMMENT '项目id',
  `name` varchar(200) NOT NULL COMMENT '项目名字',
  `introduce_text` varchar(200) NOT NULL COMMENT '介绍文字',
  `introduce_pic` varchar(200) NOT NULL COMMENT '介绍图片url',
  `predict_profit` varchar(200) NOT NULL COMMENT '预计利润',
  `actual_profit` varchar(200) NOT NULL COMMENT '实际利润',
  PRIMARY KEY (`id`),
  UNIQUE KEY `project_id` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------

-- ----------------------------
-- Table structure for `project_order`
-- ----------------------------
DROP TABLE IF EXISTS `project_order`;
CREATE TABLE `project_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL COMMENT '项目id',
  `name` varchar(200) NOT NULL COMMENT '项目名字',
  `order_date` varchar(200) NOT NULL COMMENT '预订日期',
  `telephone` varchar(200) NOT NULL COMMENT '电话',
  `order_content` varchar(200) NOT NULL COMMENT '预订内容',
  `order_type` varchar(200) NOT NULL COMMENT '预订类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `project_id` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_order
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户账号',
  `pwd` varchar(200) NOT NULL COMMENT '密码',
  `name` varchar(200) NOT NULL COMMENT '姓名',
  `payment` varchar(200) NOT NULL COMMENT '交款',
  `ID_number` varchar(200) NOT NULL COMMENT '身份证号',
  `handl_person` varchar(200) NOT NULL COMMENT '经手人',
  `account_bank` varchar(200) NOT NULL COMMENT '开户银行',
  `bank_account` varchar(200) NOT NULL COMMENT '银行账号',
  `sex` varchar(200) NOT NULL COMMENT '性别',
  `type` varchar(200) NOT NULL COMMENT '会员类型',
  `address` varchar(200) NOT NULL COMMENT '家庭住址',
  `birthday` varchar(200) NOT NULL COMMENT '生日',
  `telephone` varchar(200) NOT NULL COMMENT '电话',
  `referrer_number` varchar(200) NOT NULL COMMENT '推荐人编号',
  `add_time` varchar(200) NOT NULL COMMENT '登记时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '123', 'hjd', '99.99', '411526199111086433', '杨午圣', '光大银行', '6214921400279135', '1', '1', '河南省信阳市', '1991-11-08', '15271840991', '0000000001', '2015-11-06 09:24:59');
INSERT INTO `user` VALUES ('2', '2', '456', 'yws', '99.99', '411526199111086433', '杨午圣', '光大银行', '6214921400279135', '1', '1', '河南省信阳市', '1991-11-08', '15271840991', '0000000001', '2015-11-06 09:24:59');
