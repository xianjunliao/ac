/*
Navicat MySQL Data Transfer

Source Server         : lxj
Source Server Version : 50528
Source Host           : 112.126.85.175:3306
Source Database       : economize

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-01-20 21:54:54
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ac_temp`
-- ----------------------------
DROP TABLE IF EXISTS `ac_temp`;
CREATE TABLE `ac_temp` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tx_name` varchar(50) NOT NULL COMMENT '交易名称',
  `tx_type` int(1) unsigned zerofill NOT NULL COMMENT '0消费 1收入',
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_temp
-- ----------------------------
