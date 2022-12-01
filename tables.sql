/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.30 : Database - db_project
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_project` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `db_project`;

/*Table structure for table `t_address` */

DROP TABLE IF EXISTS `t_address`;

CREATE TABLE `t_address` (
  `aid` int NOT NULL AUTO_INCREMENT COMMENT '收货地址id',
  `uid` int DEFAULT NULL COMMENT '归属的用户id',
  `name` varchar(20) DEFAULT NULL COMMENT '收货人姓名',
  `state_name` varchar(15) DEFAULT NULL COMMENT 'State-名称',
  `city_name` varchar(15) DEFAULT NULL COMMENT 'City-名称',
  `zip` char(6) DEFAULT NULL COMMENT '邮政编码',
  `address` varchar(50) DEFAULT NULL COMMENT '详细地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `tag` varchar(50) DEFAULT NULL COMMENT '标签',
  `is_default` int DEFAULT NULL COMMENT '是否默认：0-不默认，1-默认',
  `created_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`aid`),
  KEY `uid` (`uid`),
  CONSTRAINT `t_address_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `t_customer` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_address` */

insert  into `t_address`(`aid`,`uid`,`name`,`state_name`,`city_name`,`zip`,`address`,`phone`,`tag`,`is_default`,`created_user`,`created_time`,`modified_user`,`modified_time`) values (3,7,'XINYU RAN','Pennsylvania','Pittsburgh','15217','5890 Hobart St.','7029723461','',1,'zzz','2022-10-30 19:44:12','zzz','2022-11-11 19:53:45'),(10,8,'XINYU RAN','Pennsylvania','Pittsburgh','15217','5890 Hobart St.','7029723461','home',0,'rxy','2022-11-11 21:26:53','rxy','2022-11-11 21:26:53'),(11,8,'Smith','Idaho','Hahahaha','54879','5890 Hobart St.','7029723461','company',1,'rxy','2022-11-11 21:38:17','rxy','2022-11-11 21:39:00');

/*Table structure for table `t_cart` */

DROP TABLE IF EXISTS `t_cart`;

CREATE TABLE `t_cart` (
  `cid` int NOT NULL AUTO_INCREMENT COMMENT '购物车数据id',
  `uid` int NOT NULL COMMENT '用户id',
  `pid` int NOT NULL COMMENT '商品id',
  `price` bigint DEFAULT NULL COMMENT '加入时商品单价',
  `num` int DEFAULT NULL COMMENT '商品数量',
  `created_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_cart` */

insert  into `t_cart`(`cid`,`uid`,`pid`,`price`,`num`,`created_user`,`created_time`,`modified_user`,`modified_time`) values (5,8,10000007,120,20,'rxy','2022-10-23 03:52:21','rxy','2022-11-11 21:20:53'),(6,8,10000004,150,3,'rxy','2022-10-23 03:52:27','rxy','2022-10-23 04:47:13'),(11,7,10000007,120,6,'zzz','2022-10-25 23:29:04','zzz','2022-11-07 04:04:39'),(12,8,10000003,35,5,'rxy','2022-11-02 02:20:56','rxy','2022-11-02 02:20:56'),(13,8,10000008,200,3,'rxy','2022-11-02 02:25:28','rxy','2022-11-02 02:25:28'),(14,7,10000006,32,5,'zzz','2022-11-07 00:35:43','zzz','2022-11-07 01:00:46'),(15,7,10000002,80,4,'zzz','2022-11-07 02:55:28','zzz','2022-11-07 02:55:28'),(16,8,10000006,32,5,'rxy','2022-11-11 21:21:04','rxy','2022-11-11 21:21:04');

/*Table structure for table `t_customer` */

DROP TABLE IF EXISTS `t_customer`;

CREATE TABLE `t_customer` (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `username` varchar(20) NOT NULL COMMENT 'username',
  `password` char(32) NOT NULL COMMENT 'password',
  `salt` char(36) DEFAULT NULL COMMENT 'salt used to encrypt',
  `phone` varchar(20) DEFAULT NULL COMMENT 'phone number',
  `email` varchar(30) DEFAULT NULL COMMENT 'email',
  `gender` int DEFAULT NULL COMMENT '0-female 0-male',
  `avatar` varchar(50) DEFAULT NULL COMMENT '头像',
  `is_delete` int DEFAULT NULL COMMENT 'delete or not：0-not delete，1-deleted',
  `created_user` varchar(20) DEFAULT NULL COMMENT 'log-created_user',
  `created_time` datetime DEFAULT NULL COMMENT 'log-created_time',
  `modified_user` varchar(20) DEFAULT NULL COMMENT 'log-last modified user',
  `modified_time` datetime DEFAULT NULL COMMENT 'log-last modified time',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_customer` */

insert  into `t_customer`(`uid`,`username`,`password`,`salt`,`phone`,`email`,`gender`,`avatar`,`is_delete`,`created_user`,`created_time`,`modified_user`,`modified_time`) values (1,'IU','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'Eason','F9D2096F7D9C28AF2B0F927915FE0F46','5F1BC836-5431-459F-A900-4B98A5E8BAA8',NULL,NULL,NULL,NULL,0,'Eason','2022-10-11 00:54:35','Eason','2022-10-11 00:54:35'),(3,'Chris','12DA14A8F08F1E5BA5FBBF903AD50FA2','FE66C14B-1824-4894-B97C-FFAD40656BEE',NULL,'chris@gmail.com',NULL,NULL,0,'Chris','2022-10-11 00:57:23','Eason','2022-10-11 01:36:37'),(4,'comz','9DCB0D3DD3A2690C25C7AD24F96C4C04','EC74134E-A411-44B9-B168-EC9B815024AC',NULL,'comz@gmail.com',NULL,NULL,0,'comz','2022-10-11 01:00:08','comz','2022-10-11 01:00:08'),(5,'ran','ADE4EE1CA793B6CEE3818E4FBC679316','A6EB7A03-CA82-469A-A612-E9A3EE0BC28A',NULL,'ran@gmail.com',NULL,NULL,0,'ran','2022-10-11 01:50:53','ran','2022-10-11 01:50:53'),(6,'ccc','4EAC5C13C8E4C99C3EEAC48831A04814','79BF1080-1412-4CD4-8012-5999BDC6113C','158999999','45645@gmail.com',NULL,NULL,0,'ccc','2022-10-11 01:54:59','ChangedName','2022-10-12 01:03:46'),(7,'zzz','7389AF5F36E8EEF830DA45799672291C','599DAA3F-C85B-4897-80C7-89B6422AA026','1815000176','xir16@pitt.edu',NULL,'/upload/E6891338-7A21-435D-9335-E45F3983D583.jpg',0,'zzz','2022-10-12 00:21:46','zzz','2022-10-25 23:29:53'),(8,'rxy','852C9F070344B5CEDC95B32C2AFC0D92','5371FD66-F450-455E-8380-BA4E94598699',NULL,'xir16@pitt.edu',NULL,NULL,0,'rxy','2022-10-23 03:51:56','rxy','2022-10-23 03:51:56'),(9,'sss','16A70200FDABCB330CDC50BCD8A89563','EC39F651-4D25-4ED6-8DF2-33485F60376F',NULL,'xir16@pitt.edu',NULL,'/upload/874F0C8B-FD2D-4956-9AE8-D94141A8C52C.jpg',0,'sss','2022-10-25 23:30:22','sss','2022-10-25 23:37:56'),(10,'qqq','F88C5970B9A1CA80F5ED2EDA019AF193','CFA292F8-EA6A-42E7-A08B-50B80EE1B084',NULL,'1219584836@qq.com',NULL,'/upload/C94C3D1D-B299-4EEA-AA81-D6789D012A6C.jpg',0,'qqq','2022-10-25 23:38:15','qqq','2022-10-25 23:38:38');

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `oid` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `uid` int NOT NULL COMMENT '用户id',
  `recv_name` varchar(20) NOT NULL COMMENT '收货人姓名',
  `recv_phone` varchar(20) DEFAULT NULL COMMENT '收货人电话',
  `recv_state` varchar(15) DEFAULT NULL COMMENT '收货人所在州',
  `recv_city` varchar(15) DEFAULT NULL COMMENT '收货人所在城市',
  `recv_address` varchar(50) DEFAULT NULL COMMENT '收货详细地址',
  `total_price` bigint DEFAULT NULL COMMENT '总价',
  `status` int DEFAULT NULL COMMENT '状态：0-未支付，1-已支付，2-已取消，3-已关闭，4-已完成',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `created_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_order` */

insert  into `t_order`(`oid`,`uid`,`recv_name`,`recv_phone`,`recv_state`,`recv_city`,`recv_address`,`total_price`,`status`,`order_time`,`pay_time`,`created_user`,`created_time`,`modified_user`,`modified_time`) values (1,7,'LeeSin','7021475489',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_order_item` */

DROP TABLE IF EXISTS `t_order_item`;

CREATE TABLE `t_order_item` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单中的商品记录的id',
  `oid` int NOT NULL COMMENT '所归属的订单的id',
  `pid` int NOT NULL COMMENT '商品的id',
  `title` varchar(100) NOT NULL COMMENT '商品标题',
  `image` varchar(500) DEFAULT NULL COMMENT '商品图片',
  `price` bigint DEFAULT NULL COMMENT '商品价格',
  `num` int DEFAULT NULL COMMENT '购买数量',
  `created_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_order_item` */

insert  into `t_order_item`(`id`,`oid`,`pid`,`title`,`image`,`price`,`num`,`created_user`,`created_time`,`modified_user`,`modified_time`) values (1,1,10000003,'测试插入10000003好商品是否成功',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `id` int NOT NULL COMMENT '商品id',
  `category_id` int DEFAULT NULL COMMENT '分类id',
  `item_type` varchar(100) DEFAULT NULL COMMENT '商品系列',
  `title` varchar(100) DEFAULT NULL COMMENT '商品标题',
  `sell_point` varchar(150) DEFAULT NULL COMMENT '商品卖点',
  `price` bigint DEFAULT NULL COMMENT '商品单价',
  `num` int DEFAULT NULL COMMENT '库存数量',
  `image` varchar(500) DEFAULT NULL COMMENT '图片路径',
  `status` int DEFAULT '1' COMMENT '商品状态  1：上架   2：下架   3：删除',
  `priority` int DEFAULT NULL COMMENT '显示优先级',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `created_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `modified_user` varchar(50) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_product` */

insert  into `t_product`(`id`,`category_id`,`item_type`,`title`,`sell_point`,`price`,`num`,`image`,`status`,`priority`,`created_time`,`modified_time`,`created_user`,`modified_user`) values (10000001,1,'Gamepad','Gamepad','Classic Review! Great Value Specials',69,9999,'/images/product/electric/product-01',1,1,'2022-10-25 15:08:55','2012-10-25 15:08:55','admin','admin'),(10000002,2,'Keyboard','Keyboard','Classic Review! Great Value Specials',80,9999,'/images/product/electric/product-02',1,2,'2022-10-25 15:08:55','2012-10-25 15:08:55','admin','admin'),(10000003,3,'Monitor','Monitor','Classic Review! Great Value Specials',35,7850,'/images/product/electric/product-03',1,3,'2022-10-25 15:08:55','2012-10-25 15:08:55','admin','admin'),(10000004,4,'Audio','Audio','Classic Review! Great Value Specials',150,8888,'/images/product/electric/product-04',1,4,'2022-10-25 15:08:55','2012-10-25 15:08:55','admin','admin'),(10000005,4,'Bluetooth wireless audio','Bluetooth wireless audio','Classic Review! Great Value Specials',140,9999,'/images/product/electric/product-05',1,5,'2022-10-25 15:08:55','2012-10-25 15:08:55','admin','admin'),(10000006,5,'Mouse','Mouse','Classic Review! Great Value Specials',32,9999,'/images/product/electric/product-06',1,6,'2022-10-25 15:08:55','2012-10-25 15:08:55','admin','admin'),(10000007,6,'Headphones','Headphones','Classic Review! Great Value Specials',120,9999,'/images/product/electric/product-07',1,7,'2022-10-25 15:08:55','2012-10-25 15:08:55','admin','admin'),(10000008,1,'3DGamepad','3DGamepad','Classic Review! Great Value Specials',200,9999,'/images/product/electric/product-08',1,8,'2022-10-25 15:08:55','2012-10-25 15:08:55','admin','admin');

/*Table structure for table `t_state` */

DROP TABLE IF EXISTS `t_state`;

CREATE TABLE `t_state` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sta_name` varchar(50) NOT NULL COMMENT 'states names',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_state` */

insert  into `t_state`(`id`,`sta_name`) values (1,'Alabama'),(2,'Alaska'),(3,'Arizona'),(4,'Arkansas'),(5,'California'),(6,'Colorado'),(7,'Connecticut'),(8,'Delaware'),(9,'District of Columbia'),(10,'Florida '),(11,'Georgia '),(12,'Hawaii  '),(13,'Idaho  '),(14,'Illinois  '),(15,'Indiana '),(16,'Iowa  '),(17,'Kansas  '),(18,'Kentucky  '),(19,'Louisiana '),(20,'Maine '),(21,'Maryland  '),(22,'Massachusetts '),(23,'Michigan '),(24,'Minnesota '),(25,'Mississippi '),(26,'Missouri '),(27,'Montana '),(28,'Nebraska  '),(29,'Nevada '),(30,'New Hampshire '),(31,'New Jeresy '),(32,'New Mexico '),(33,'New York '),(34,'North Carolina  '),(35,'North Dakota '),(36,'Ohio  '),(37,'Oklahoma '),(38,'Oregon '),(39,'Pennsylvania '),(40,'Rhode Island '),(41,'SouthCarolina  '),(42,'South Dakota '),(43,'Tennessee '),(44,'Texas '),(45,'Utah '),(46,'Vermont '),(47,'Virginia '),(48,'Washington  '),(49,'West Virginia '),(50,'Wisconsin'),(51,'Wyoming');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
