/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : economize

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-01-17 23:33:19
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
  `ac_name` varchar(50) NOT NULL COMMENT '记账名称',
  `ac_type` int(1) NOT NULL DEFAULT '0' COMMENT '记账类型（0消费 1收入）',
  `ac_amount` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '记账金额',
  `ac_remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `ac_create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_books
-- ----------------------------
INSERT INTO ac_books VALUES ('1', '1', '1', '2017-01-17', '2017-01', '3', '早餐', '0', '5.00', null, '2017-01-17 23:15:37');
