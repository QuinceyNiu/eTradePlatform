/*
 Navicat Premium Data Transfer

 Source Server         : MrTree
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : db_project

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 01/12/2022 10:59:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `aid` int NOT NULL AUTO_INCREMENT,
  `uid` int DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `state_name` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `city_name` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `zip` char(6) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `tag` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_default` int DEFAULT NULL,
  `created_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`aid`),
  KEY `uid` (`uid`),
  CONSTRAINT `t_address_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `t_customer` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for t_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `pid` int NOT NULL,
  `price` bigint DEFAULT NULL,
  `num` int DEFAULT NULL,
  `created_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `cart_uid` (`uid`),
  KEY `cart_pid` (`pid`),
  CONSTRAINT `cart_pid` FOREIGN KEY (`pid`) REFERENCES `t_product` (`id`),
  CONSTRAINT `cart_uid` FOREIGN KEY (`uid`) REFERENCES `t_customer` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `username` varchar(20) NOT NULL COMMENT 'username',
  `password` char(32) NOT NULL COMMENT 'password',
  `salt` char(36) DEFAULT NULL COMMENT 'salt used to encrypt',
  `phone` varchar(20) DEFAULT NULL COMMENT 'phone number',
  `email` varchar(30) DEFAULT NULL COMMENT 'email',
  `gender` int DEFAULT NULL COMMENT '0-female 0-male',
  `avatar` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'logo',
  `is_delete` int DEFAULT NULL COMMENT 'delete or not：0-not delete，1-deleted',
  `created_user` varchar(20) DEFAULT NULL COMMENT 'log-created_user',
  `created_time` datetime DEFAULT NULL COMMENT 'log-created_time',
  `modified_user` varchar(20) DEFAULT NULL COMMENT 'log-last modified user',
  `modified_time` datetime DEFAULT NULL COMMENT 'log-last modified time',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `oid` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `recv_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `recv_phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `recv_state` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `recv_city` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `recv_address` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `total_price` bigint DEFAULT NULL,
  `status` int DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `created_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `order_uid` (`uid`),
  CONSTRAINT `order_uid` FOREIGN KEY (`uid`) REFERENCES `t_customer` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `oid` int NOT NULL,
  `pid` int NOT NULL COMMENT '\n',
  `title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `image` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `price` bigint DEFAULT NULL,
  `num` int DEFAULT NULL,
  `created_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_oid` (`oid`),
  KEY `item_pid` (`pid`),
  CONSTRAINT `item_oid` FOREIGN KEY (`oid`) REFERENCES `t_order` (`oid`),
  CONSTRAINT `item_pid` FOREIGN KEY (`pid`) REFERENCES `t_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` int NOT NULL,
  `category_id` int DEFAULT NULL,
  `item_type` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `sell_point` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `num` int DEFAULT NULL,
  `image` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `status` int DEFAULT '1',
  `priority` int DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `created_user` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `modified_user` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for t_state
-- ----------------------------
DROP TABLE IF EXISTS `t_state`;
CREATE TABLE `t_state` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sta_name` varchar(50) NOT NULL COMMENT 'states names',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb3;

SET FOREIGN_KEY_CHECKS = 1;
