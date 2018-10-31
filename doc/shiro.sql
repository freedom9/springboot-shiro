/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-10-31 10:31:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `salt` varchar(30) DEFAULT NULL,
  `perms` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
--原始密码123
INSERT INTO `user` VALUES ('1', '小白', 'b76484c24e095b6d915ec2ad3d50b2dd', '5bCP55m9', 'user:add,user:update');
INSERT INTO `user` VALUES ('2', 'aaa', 'e97ae203e788e10b215184a0ea10ebf7', 'YWFh', 'user:add');
INSERT INTO `user` VALUES ('3', 'bbb', 'a6fae6fc2eec0645004ba1ee3659520c', 'YmJi', 'user:update');
INSERT INTO `user` VALUES ('4', 'ccc', 'febff1ca405b1e0420f5af3caf6463e7', 'Y2Nj', 'user:add,user:update');
