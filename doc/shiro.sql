/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-10-30 16:52:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `perms` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '小白', '123', 'user:add,user:update');
INSERT INTO `user` VALUES ('2', 'aaa', '123', 'user:add');
INSERT INTO `user` VALUES ('3', 'bbb', '123', 'user:update');
INSERT INTO `user` VALUES ('4', 'ccc', '123', 'user:add,user:update');
