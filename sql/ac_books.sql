/*
Navicat MySQL Data Transfer

Source Server         : lxj
Source Server Version : 50528
Source Host           : 112.126.85.175:3306
Source Database       : economize

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-01-20 21:57:35
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ac_books`
-- ----------------------------
DROP TABLE IF EXISTS `ac_books`;
CREATE TABLE `ac_books` (
  `id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  `p_ac_id` int(10) DEFAULT NULL COMMENT '记账ID',
  `ac_date` date NOT NULL,
  `ac_month` varchar(12) DEFAULT NULL,
  `ac_week` int(3) DEFAULT NULL,
  `temp_tx_id` int(10) NOT NULL COMMENT '记账名称',
  `ac_amount` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '记账金额',
  `ac_remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `ac_create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_books
-- ----------------------------
