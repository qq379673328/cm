SET GLOBAL log_bin_trust_function_creators = 1;

/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.24-enterprise-commercial-advanced-log : Database - cm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cm`;

/*Table structure for table `t_attachment` */

DROP TABLE IF EXISTS `t_attachment`;

CREATE TABLE `t_attachment` (
  `id` varchar(40) NOT NULL COMMENT '附件id',
  `module` varchar(100) DEFAULT NULL COMMENT '附件所属模块',
  `path` varchar(200) NOT NULL COMMENT '附件路径',
  `name` varchar(200) NOT NULL COMMENT '附件名称',
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '附件上传时间',
  `type` varchar(100) NOT NULL COMMENT '附件类型',
  `is_use` varchar(10) NOT NULL COMMENT '是否有效：1-有效，0-无效',
  `size` double DEFAULT NULL COMMENT '附件大小',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

/*Data for the table `t_attachment` */

insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('05b8031b-1e31-44b5-b8b9-6673922584fd',NULL,'2015-08-18\\05b8031b-1e31-44b5-b8b9-6673922584fd.jpg','u0.jpg','2015-08-18 00:00:49','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('07dc1213-f6d8-4494-90ef-d9fae90e30ac',NULL,'2015-07-19\\07dc1213-f6d8-4494-90ef-d9fae90e30ac.docx','系统问题20150716.docx','2015-07-19 17:48:39','docx','0',681204);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('0ee064c1-c1f4-41d6-9d93-8394a8d9efba',NULL,'2015-08-17\\0ee064c1-c1f4-41d6-9d93-8394a8d9efba.jpg','u0.jpg','2015-08-17 21:41:10','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('0fbaeac6-f4c6-4dc1-a688-71d0f48495fe',NULL,'2015-08-18\\0fbaeac6-f4c6-4dc1-a688-71d0f48495fe.jpg','u0.jpg','2015-08-18 00:04:20','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('11250da2-12e2-48a1-90d1-2e007cf67094',NULL,'2015-08-17\\11250da2-12e2-48a1-90d1-2e007cf67094.jpg','u0.jpg','2015-08-17 22:02:32','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('13209dbe-3671-42b9-bd92-e66df98aa279',NULL,'2015-08-17\\13209dbe-3671-42b9-bd92-e66df98aa279.jpg','u0.jpg','2015-08-17 23:53:36','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('137703cc-cc59-4514-be58-3f0176b10f79',NULL,'2015-08-17\\137703cc-cc59-4514-be58-3f0176b10f79.jpg','u0.jpg','2015-08-17 22:08:47','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('17d0a37c-5cfd-4b60-afaf-c48ca8f465a9',NULL,'2015-08-17\\17d0a37c-5cfd-4b60-afaf-c48ca8f465a9.jpg','u0.jpg','2015-08-17 23:10:32','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('1b305eae-8476-4c73-82fb-f972169ab069',NULL,'2015-08-17\\1b305eae-8476-4c73-82fb-f972169ab069.jpg','u0.jpg','2015-08-17 23:12:48','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('1c67422e-f5a0-4879-807f-96ead88b2900',NULL,'2015-08-17\\1c67422e-f5a0-4879-807f-96ead88b2900.jpg','u0.jpg','2015-08-17 22:31:08','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('1caa596b-c594-4c95-9c7e-e927db3a00ec',NULL,'2015-08-17\\1caa596b-c594-4c95-9c7e-e927db3a00ec.jpg','u0.jpg','2015-08-17 22:44:20','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('206494eb-22a4-4e98-bbfc-fd35e751902d',NULL,'2015-07-19\\206494eb-22a4-4e98-bbfc-fd35e751902d.txt','宽带密码.txt','2015-07-19 17:42:01','txt','0',74);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('20bd7034-0b5b-442c-83d2-acd6287e6396',NULL,'2015-08-17\\20bd7034-0b5b-442c-83d2-acd6287e6396.jpg','u0.jpg','2015-08-17 23:05:17','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('261d827b-64f6-4fc4-9f9f-38dc5771b23b',NULL,'2015-08-17\\261d827b-64f6-4fc4-9f9f-38dc5771b23b.jpg','u0.jpg','2015-08-17 22:46:00','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('28fbbb22-3f5c-486c-9203-f8645a9ad4d3',NULL,'2015-08-17\\28fbbb22-3f5c-486c-9203-f8645a9ad4d3.jpg','u0.jpg','2015-08-17 21:30:47','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('2be69ee1-0acd-4a19-acf0-d93ae273adaf',NULL,'2015-08-17\\2be69ee1-0acd-4a19-acf0-d93ae273adaf.jpg','u0.jpg','2015-08-17 22:51:13','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('2c2c72ca-49df-41b9-90d9-f3993cd5623e',NULL,'2015-08-17\\2c2c72ca-49df-41b9-90d9-f3993cd5623e.jpg','u0.jpg','2015-08-17 22:18:09','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('34187bbb-7adc-44fa-bb3d-9feed7364c43',NULL,'2015-07-19\\34187bbb-7adc-44fa-bb3d-9feed7364c43.bat','启动数据库.bat','2015-07-19 17:54:12','bat','0',81);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('3ac67eca-775b-4549-8bc7-524cf24bdfba',NULL,'2015-08-17\\3ac67eca-775b-4549-8bc7-524cf24bdfba.jpg','u0.jpg','2015-08-17 22:23:13','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('3b45cb0d-5f70-4639-add4-fbf2038cbb9f',NULL,'2015-07-19\\3b45cb0d-5f70-4639-add4-fbf2038cbb9f.txt','宽带密码.txt','2015-07-19 17:49:42','txt','0',74);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('3c5352d5-097c-434e-a671-838a3b7a423b',NULL,'2015-08-17\\3c5352d5-097c-434e-a671-838a3b7a423b.jpg','u0.jpg','2015-08-17 22:26:34','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('3d518668-1d68-4538-89cf-faf09ffaf95c',NULL,'2015-08-17\\3d518668-1d68-4538-89cf-faf09ffaf95c.jpg','u0.jpg','2015-08-17 22:46:50','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('3de17d98-a7d3-4e88-8418-95c62892f1df',NULL,'2015-08-17\\3de17d98-a7d3-4e88-8418-95c62892f1df.jpg','u0.jpg','2015-08-17 22:28:21','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('4655a12f-9cf4-43a6-883b-acf5ebdc295d',NULL,'2015-08-18\\4655a12f-9cf4-43a6-883b-acf5ebdc295d.jpg','u0.jpg','2015-08-18 00:00:30','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('51d835bc-ffc3-4dcc-be1d-3bf0198959c1',NULL,'2015-08-17\\51d835bc-ffc3-4dcc-be1d-3bf0198959c1.jpg','u0.jpg','2015-08-17 22:53:08','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('52d99715-d9be-4ff4-b5c2-139dc35cdd9a',NULL,'2015-08-18\\52d99715-d9be-4ff4-b5c2-139dc35cdd9a.jpg','u0.jpg','2015-08-18 00:03:51','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('578556ba-32cd-4586-8cf3-d9c16a893122',NULL,'2015-08-17\\578556ba-32cd-4586-8cf3-d9c16a893122.jpg','u0.jpg','2015-08-17 22:54:20','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('5996ef74-ffe1-4974-9a8b-7fe08b120f91',NULL,'2015-08-17\\5996ef74-ffe1-4974-9a8b-7fe08b120f91.jpg','u0.jpg','2015-08-17 23:05:00','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('5c1581cd-05dc-4dbb-acea-ea3ecdb7f0af',NULL,'2015-08-17\\5c1581cd-05dc-4dbb-acea-ea3ecdb7f0af.jpg','u0.jpg','2015-08-17 22:56:02','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('60aa1a98-7da7-4841-880b-20c90d073331',NULL,'2015-08-18\\60aa1a98-7da7-4841-880b-20c90d073331.jpg','u0.jpg','2015-08-18 00:04:02','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('623c1f9c-8603-4699-82f6-2aa2009b1642',NULL,'2015-08-17\\623c1f9c-8603-4699-82f6-2aa2009b1642.jpg','u0.jpg','2015-08-17 22:58:37','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('639ce2e4-94b1-4359-8b46-2c47fb821769',NULL,'2015-08-17\\639ce2e4-94b1-4359-8b46-2c47fb821769.jpg','u0.jpg','2015-08-17 21:40:25','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('6e045ff8-16bd-40e1-abc7-9215358a4df4',NULL,'2015-08-17\\6e045ff8-16bd-40e1-abc7-9215358a4df4.jpg','u0.jpg','2015-08-17 23:07:50','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('7234948b-a2b7-4f6e-af81-004c810a0043',NULL,'2015-08-17\\7234948b-a2b7-4f6e-af81-004c810a0043.jpg','u0.jpg','2015-08-17 21:32:04','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('76734f79-bfa1-4620-a630-f4933ed4009a',NULL,'2015-08-18\\76734f79-bfa1-4620-a630-f4933ed4009a.jpg','u0.jpg','2015-08-18 00:01:09','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('79da6e94-b4e2-4d37-bcb7-ec5743bfc488',NULL,'2015-08-18\\79da6e94-b4e2-4d37-bcb7-ec5743bfc488.jpg','u0.jpg','2015-08-18 01:26:33','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('7b766aab-246b-475c-b39f-1e76c02a7cb8',NULL,'2015-08-17\\7b766aab-246b-475c-b39f-1e76c02a7cb8.jpg','u0.jpg','2015-08-17 22:20:00','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('7c2b37e7-47f6-4535-b68c-327e7e8072da',NULL,'2015-08-17\\7c2b37e7-47f6-4535-b68c-327e7e8072da.jpg','u0.jpg','2015-08-17 22:21:04','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('7c7d55e2-8207-4004-95d6-2332cb7506b7',NULL,'2015-08-18\\7c7d55e2-8207-4004-95d6-2332cb7506b7.jpg','u0.jpg','2015-08-18 00:30:39','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('7cb61fac-0ba2-4e72-8185-f8938d59fcfc',NULL,'2015-08-17\\7cb61fac-0ba2-4e72-8185-f8938d59fcfc.jpg','u0.jpg','2015-08-17 21:27:02','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('7ddc0c37-3d16-42da-b058-5252444ad065',NULL,'2015-08-17\\7ddc0c37-3d16-42da-b058-5252444ad065.jpg','u0.jpg','2015-08-17 23:16:01','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('84ea5aa1-866d-49f2-ad9d-af71faf8f070',NULL,'2015-08-17\\84ea5aa1-866d-49f2-ad9d-af71faf8f070.jpg','u0.jpg','2015-08-17 22:03:30','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('85373a73-c7af-4dbd-b3f7-c0f7ea77701c',NULL,'2015-08-17\\85373a73-c7af-4dbd-b3f7-c0f7ea77701c.jpg','u0.jpg','2015-08-17 22:18:42','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('8ea79e12-45d4-4c27-87c0-681839e0f91d',NULL,'2015-08-17\\8ea79e12-45d4-4c27-87c0-681839e0f91d.jpg','u0.jpg','2015-08-17 22:26:00','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('8ec7ef6e-67dd-4b48-b812-deb2761e4438',NULL,'2015-08-17\\8ec7ef6e-67dd-4b48-b812-deb2761e4438.jpg','u0.jpg','2015-08-17 22:27:54','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('94849374-8f16-45b9-af6f-9fe755c120a5',NULL,'2015-08-17\\94849374-8f16-45b9-af6f-9fe755c120a5.jpg','u0.jpg','2015-08-17 23:13:18','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('999f391a-a40f-4b99-ae9f-df7ae1492006',NULL,'2015-08-18\\999f391a-a40f-4b99-ae9f-df7ae1492006.jpg','u0.jpg','2015-08-18 00:51:43','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('9afc3d62-130f-448b-b8b6-45990dd2e463',NULL,'2015-07-19\\9afc3d62-130f-448b-b8b6-45990dd2e463.bat','数据库控制台.bat','2015-07-19 17:46:22','bat','0',43);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('9b8b1fa1-97a9-4430-832e-222ae80f3560',NULL,'2015-08-17\\9b8b1fa1-97a9-4430-832e-222ae80f3560.jpg','u0.jpg','2015-08-17 22:10:44','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('9e6332cc-1c20-4416-9efb-b462034912bb',NULL,'2015-08-17\\9e6332cc-1c20-4416-9efb-b462034912bb.jpg','u0.jpg','2015-08-17 22:09:00','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('a424ffdd-3a83-407f-8855-a9aae9a07c47',NULL,'2015-08-17\\a424ffdd-3a83-407f-8855-a9aae9a07c47.jpg','u0.jpg','2015-08-17 23:55:43','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('a78841fa-49c6-488c-8b7f-d0866171d906',NULL,'2015-08-17\\a78841fa-49c6-488c-8b7f-d0866171d906.jpg','u0.jpg','2015-08-17 23:18:36','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('ac18f6ba-d127-4da0-9a55-cbff1d44d9d5',NULL,'2015-08-17\\ac18f6ba-d127-4da0-9a55-cbff1d44d9d5.jpg','u0.jpg','2015-08-17 21:32:27','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('ad9384cc-a018-4268-b4a3-c6c8c53f76c8',NULL,'2015-08-17\\ad9384cc-a018-4268-b4a3-c6c8c53f76c8.jpg','u0.jpg','2015-08-17 23:03:01','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('af694c33-1c13-4bf1-aa27-00552b909ff7',NULL,'2015-08-17\\af694c33-1c13-4bf1-aa27-00552b909ff7.jpg','u0.jpg','2015-08-17 22:39:59','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('b5b77a08-479c-4def-9acb-71a16165bfa9',NULL,'2015-08-17\\b5b77a08-479c-4def-9acb-71a16165bfa9.jpg','u0.jpg','2015-08-17 23:54:10','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('b5f2031a-4e58-478e-a268-1be9f491378c',NULL,'2015-08-17\\b5f2031a-4e58-478e-a268-1be9f491378c.jpg','u0.jpg','2015-08-17 23:50:26','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('b77961ff-add5-42ba-bde2-b09c93cbac30',NULL,'2015-08-17\\b77961ff-add5-42ba-bde2-b09c93cbac30.jpg','u0.jpg','2015-08-17 22:00:45','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('bb29d0db-090c-4cb7-a582-7f4cda017a4b',NULL,'2015-08-17\\bb29d0db-090c-4cb7-a582-7f4cda017a4b.jpg','u0.jpg','2015-08-17 23:09:54','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('bfebc208-b5a0-4256-890d-c3739a8f3892',NULL,'2015-08-17\\bfebc208-b5a0-4256-890d-c3739a8f3892.jpg','u0.jpg','2015-08-17 22:10:57','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('c2d21b49-e475-493e-b6ec-7adb0139f78b',NULL,'2015-08-17\\c2d21b49-e475-493e-b6ec-7adb0139f78b.jpg','u0.jpg','2015-08-17 22:14:24','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('c80b03a7-8f5d-4e16-a2c4-1093822d2225',NULL,'2015-08-16\\c80b03a7-8f5d-4e16-a2c4-1093822d2225.txt','2015奥斯卡提名等电影.txt','2015-08-16 16:14:09','txt','0',30);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('c880b9cf-233c-4557-8c8b-d827cb5a8e95',NULL,'2015-08-18\\c880b9cf-233c-4557-8c8b-d827cb5a8e95.jpg','u0.jpg','2015-08-18 00:51:59','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('c999d0b8-28f1-411b-b928-1826b52b1d22',NULL,'2015-08-17\\c999d0b8-28f1-411b-b928-1826b52b1d22.jpg','u0.jpg','2015-08-17 22:17:03','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('caa76892-4ac7-484c-a383-6ff30d2b838e',NULL,'2015-08-17\\caa76892-4ac7-484c-a383-6ff30d2b838e.jpg','u0.jpg','2015-08-17 22:58:16','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('caec8088-1c7a-4778-86a8-5791029f9264',NULL,'2015-09-06\\caec8088-1c7a-4778-86a8-5791029f9264.jpg','P1408300305445.jpg','2015-09-06 21:31:27','jpg','0',80901);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('cecc1cb5-f344-459a-bc99-5d1df9a7bec5',NULL,'2015-08-17\\cecc1cb5-f344-459a-bc99-5d1df9a7bec5.jpg','u0.jpg','2015-08-17 22:26:45','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('d28d851a-2dee-409c-85cc-334f1db536cb',NULL,'2015-08-18\\d28d851a-2dee-409c-85cc-334f1db536cb.jpg','u0.jpg','2015-08-18 00:01:54','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('da6d44c6-c57a-4419-9521-869354a1ba5f',NULL,'2015-08-17\\da6d44c6-c57a-4419-9521-869354a1ba5f.jpg','u0.jpg','2015-08-17 23:15:38','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('db5550c4-9818-4013-82f5-51affdf2135e',NULL,'2015-08-17\\db5550c4-9818-4013-82f5-51affdf2135e.jpg','u0.jpg','2015-08-17 22:23:58','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('db82575c-7189-405c-97ad-5a9364f6eead',NULL,'2015-08-18\\db82575c-7189-405c-97ad-5a9364f6eead.jpg','u0.jpg','2015-08-18 00:31:48','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('e603d212-7aed-4cc5-93e8-7605301d14ff',NULL,'2015-07-19\\e603d212-7aed-4cc5-93e8-7605301d14ff.txt','宽带密码.txt','2015-07-19 17:46:36','txt','0',74);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('e64c2615-75c1-4e28-b4b7-c4f83e35f482',NULL,'2015-08-17\\e64c2615-75c1-4e28-b4b7-c4f83e35f482.jpg','u0.jpg','2015-08-17 23:52:22','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('ea4b5ba5-5d72-46e4-8cbf-61c34f8473cf',NULL,'2015-08-17\\ea4b5ba5-5d72-46e4-8cbf-61c34f8473cf.jpg','u0.jpg','2015-08-17 22:07:39','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('ee3a1211-a5b2-4494-afcb-bcc8324a069f',NULL,'2015-08-17\\ee3a1211-a5b2-4494-afcb-bcc8324a069f.jpg','u0.jpg','2015-08-17 22:41:22','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('eea5e870-bd20-42d4-8640-44634cd7267d',NULL,'2015-08-18\\eea5e870-bd20-42d4-8640-44634cd7267d.jpg','u0.jpg','2015-08-18 00:02:52','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('ef620602-ff22-4ca1-855c-7c0d361cc8ab',NULL,'2015-08-17\\ef620602-ff22-4ca1-855c-7c0d361cc8ab.jpg','u0.jpg','2015-08-17 22:57:24','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('f2d286b0-1d69-4c4e-b4bd-55e619e6b95d',NULL,'2015-08-17\\f2d286b0-1d69-4c4e-b4bd-55e619e6b95d.jpg','u0.jpg','2015-08-17 22:22:07','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('f9f97d95-37b6-4a9f-bf30-a00a67d5bfca',NULL,'2015-08-17\\f9f97d95-37b6-4a9f-bf30-a00a67d5bfca.jpg','u0.jpg','2015-08-17 22:16:51','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('fd1a2255-e291-46bb-a616-9dc33dba6a3b',NULL,'2015-08-17\\fd1a2255-e291-46bb-a616-9dc33dba6a3b.jpg','u0.jpg','2015-08-17 22:30:43','jpg','0',57643);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('feff591c-d2b0-49c5-8d6c-3e04e527e128',NULL,'2015-08-17\\feff591c-d2b0-49c5-8d6c-3e04e527e128.jpg','u0.jpg','2015-08-17 23:18:25','jpg','0',57643);

/*Table structure for table `t_code` */

DROP TABLE IF EXISTS `t_code`;

CREATE TABLE `t_code` (
  `id` varchar(40) NOT NULL,
  `codetype` varchar(100) NOT NULL COMMENT '编码类型',
  `codevalue` varchar(100) NOT NULL COMMENT '编码值',
  `rank` int(10) NOT NULL COMMENT '顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='码表';

/*Data for the table `t_code` */

insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('028defc6-171c-4ebe-97dd-3fa49e143847','yearpay','150-200万',8);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('04e5d158-3498-450e-bce7-6e70f0130e45','yearpay','10万以下',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('0e9a77f9-b56e-4563-8b0b-99be2f7dfed2','edu','博士以上',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('1','customIndustry','房地产开发/建筑与工程',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('18c309a3-637d-4864-af4d-a6f3abf080f5','incomestate','已到账',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('2','customIndustry','金融业(投资/保险/证券/银行/基金)',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('20d9c1b9-403f-4eaf-9ecc-5dd168fac6f7','yearpay','20-30万',4);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('24618aff-12e7-4b52-bfe0-c8f325cd0711','invoicepro','咨询服务费',6);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('26ef360c-bce7-460d-90b6-98af84c5bd31','edu','大专以上',4);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('3','customSource','广告呼入',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('32912aa8-ff3a-475a-8a7c-627d28616f90','invoicepro','咨询费',5);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('3374e835-6bfc-4269-bcc5-3ab696885062','invoicetype','普通发票',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('38844cd8-e893-4b7c-a4d2-2109a1730797','invoicepro','服务费',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('4','customSource','主动BD',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('4593fc0e-a112-4eca-a625-199ca40e2a4e','jobState','关闭',4);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('4a0e8a81-d476-4e62-9647-69e56d0f37a8','department','行政部',5);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('4f23c01a-2259-4b79-b09f-7ba1218fecc8','invoicepro','猎头费',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('5','customSource','机构合作',3);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('51f25a3b-a354-49a0-b6d5-ead8f671884a','userduty','财务出纳',6);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('539022dd-4552-4920-9e14-5bdced32fad4','yearpay','30-50万',5);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('53b51938-d54c-4f1d-9acb-772165f73430','zone','上海',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('594699f8-9bca-408d-b826-c611836b898a','contractState','终止',3);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('5a6f422f-a32f-4491-b364-83972bb62915','jobState','暂停',3);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('6','customSource','人脉推荐',4);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('662c6daf-aa4a-4af1-aa9e-08df2355f142','invoicestate','已作废',3);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('6c2c511f-c044-4a2a-890d-7af1b7cde637','department','人力资源部',3);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('71b62108-db11-4042-a37f-ef4c382eec5f','yearpay','50-100万',6);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('74a4cdc3-a8f5-491b-8539-8a000e71355e','department','市场部',6);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('7891f2c1-6a94-44a0-beb8-2a9b3d73141c','userduty','合作人',3);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('7ed6d10a-17fa-4680-b6db-7f3d6232b268','userduty','资深顾问',5);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('809dd2f2-634d-497c-aaf4-e5a95c80c71f','jobState','运作',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('82ea6256-8371-4e7f-a634-72df06ea322f','zone','北京',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('8569b661-d2cd-4556-bd74-9f22e1bd33fe','zone','深圳',4);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('8a81732b-6fed-4b6c-ab2f-db82f04740cf','department','管理中心',7);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('8c48aa35-42dd-4401-8e7c-4d6890819137','duty','财务/审计/统计类',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('8ede6248-57e1-44ec-a2c3-ae8f847502fa','incomestate','未到账',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('8f9aba77-3155-4af0-824a-c4b1d3a5d071','userduty','工程师',7);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('931e9555-246e-4b75-a2c5-110f37ebb2b2','department','技术部',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('98193f01-8bd5-4564-bea5-66ff5e0bc3b9','contractState','运作',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('9983a3c8-76c5-497f-b162-e799472221f4','department','财务部',4);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('9e5e2e5c-2010-41ac-97ab-303406d37e6f','yearpay','10-15万',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('9ee4e559-1559-4614-ae5c-ec99eb6edeaf','zone','广州',3);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('a03dca81-d987-4b0a-ac90-9a0b4fea262b','userduty','助理顾问',8);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('aff36e09-9415-4217-8da1-2f578d4c09f0','userduty','人力资源',4);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('b406a3ff-d7f3-4d05-bce8-f758ff3a8d17','contractState','暂停',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('b6e1d9e2-7520-4baf-b44a-e351b7190de0','invoicestate','已开出',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('b74f0a15-dd7a-4bf7-89ed-5ca57dce0b14','yearpay','100-150万',7);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('b933db8c-2d34-40ba-be3c-a6576f52cb7c','yearpay','200万以上',9);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('c1c1e947-bcac-4cc3-8b5d-ab302e58ccb0','userduty','猎头助理',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('c39d61da-ebcb-4a33-bc3b-85a0b6ef0f6d','yearpay','15-20万',3);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('cf6b8d6d-3aba-41db-84b5-564bba3d4954','userduty','猎头顾问',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('d0d12059-9dff-4260-b62e-01f86c924f86','invoicetype','专用发票',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('d111ff2c-d065-468b-a72e-740dedd41105','edu','本科以上',3);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('d53530bc-416c-4855-bf3e-9762ca476378','invoicepro','其他',7);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('d60e4f10-7ecf-4971-a06c-6b7d08cdeeed','edu','硕士以上',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('d8d25d1e-6319-49ba-9d13-30921e64a96e','invoicepro','招聘服务费',4);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('df78fd01-3942-4969-8bec-e6c1b0b6859d','invoicestate','申请中',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('e064a133-72f9-48fc-84c5-eab47a75d608','duty','人力资源类',3);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('e0c30aab-eb4d-4c90-a001-2f2f92ade642','customState','潜在客户',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('e1ea3d45-c668-4ffa-ba8e-dbcf50e4bb4c','jobState','结束',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('e2fe4ef3-315c-42ec-ab75-82baa48c47d9','customState','签约运作',2);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('e5b00027-951c-4160-ac2b-71b11f1c6877','invoicepro','招聘费',3);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('ec9bece3-5be0-4aba-b737-e819ff326330','department','猎头部',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('f04a33f1-9c69-409e-9f1d-bbd73481bc3a','duty','经营管理类',1);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('f6356db4-05f3-4a92-85b1-a8b3596d72f7','customState','签约终止',4);
insert  into `t_code`(`id`,`codetype`,`codevalue`,`rank`) values ('fab80ee0-425e-4629-ad39-d861eeebd77b','customState','签约暂停',3);

/*Table structure for table `t_codetype` */

DROP TABLE IF EXISTS `t_codetype`;

CREATE TABLE `t_codetype` (
  `id` varchar(40) NOT NULL,
  `codetype` varchar(100) NOT NULL COMMENT '编码类型',
  `codetypedesc` varchar(100) NOT NULL COMMENT '类型描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='编码类别表';

/*Data for the table `t_codetype` */

insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('1','customIndustry','行业');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('10','userduty','担任职务');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('11','incomestate','回款状态');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('12','invoicetype','发票类型');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('13','invoicestate','发票状态');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('14','yearpay','年薪');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('15','invoicepro','发票属性');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('2','customSource','客户来源');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('3','customState','客户状态');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('4','contractState','合同状态');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('5','jobState','职位状态');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('6','zone','省市');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('7','duty','职业');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('8','edu','学历');
insert  into `t_codetype`(`id`,`codetype`,`codetypedesc`) values ('9','department','部门');

/*Table structure for table `t_contract` */

DROP TABLE IF EXISTS `t_contract`;

CREATE TABLE `t_contract` (
  `id` varchar(40) NOT NULL COMMENT '合同id',
  `custom_id` varchar(40) NOT NULL COMMENT '客户id',
  `no` varchar(100) NOT NULL COMMENT '合同编号',
  `state` varchar(50) NOT NULL COMMENT '合同状态',
  `in_date` date NOT NULL COMMENT '签约日期',
  `in_percentage` varchar(100) DEFAULT NULL COMMENT '签约比例',
  `payway` varchar(100) DEFAULT NULL COMMENT '支付方式',
  `first_pay` varchar(50) DEFAULT NULL COMMENT '首付款',
  `use_limit` varchar(50) DEFAULT NULL COMMENT '使用期限',
  `other_require` varchar(500) DEFAULT NULL COMMENT '其他要求',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(40) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(40) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_CONTRACT_NO` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同表';

/*Data for the table `t_contract` */

insert  into `t_contract`(`id`,`custom_id`,`no`,`state`,`in_date`,`in_percentage`,`payway`,`first_pay`,`use_limit`,`other_require`,`create_time`,`create_user`,`update_time`,`update_user`) values ('3468be67-f492-44de-8d44-6b33c52f8e78','2bd09171-6547-4e30-b16d-d6bc7f13bfea','20150001','运作','2015-08-15','12','现今','20','20','无','2015-08-16 14:25:36','1','2015-08-16 14:31:59','1');
insert  into `t_contract`(`id`,`custom_id`,`no`,`state`,`in_date`,`in_percentage`,`payway`,`first_pay`,`use_limit`,`other_require`,`create_time`,`create_user`,`update_time`,`update_user`) values ('e27177aa-f703-4da2-b750-4ac10ea37e3a','63966dad-e3e5-4815-b8be-b39d2ecdcae9','20150002','运作','2015-08-16','12','123','123','123','123','2015-08-16 14:31:12','1',NULL,NULL);

/*Table structure for table `t_contract_data` */

DROP TABLE IF EXISTS `t_contract_data`;

CREATE TABLE `t_contract_data` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `contract_id` varchar(40) NOT NULL COMMENT '合同表id',
  `attachment_id` varchar(40) NOT NULL COMMENT '附件表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同附件表';

/*Data for the table `t_contract_data` */

insert  into `t_contract_data`(`id`,`contract_id`,`attachment_id`) values ('085655a9-b1a3-4c17-9774-f201c5b074f9','5c7e6f7e-4bf0-4bea-988f-5d4683718a6b','3b45cb0d-5f70-4639-add4-fbf2038cbb9f');
insert  into `t_contract_data`(`id`,`contract_id`,`attachment_id`) values ('0c942adc-9f3c-46b8-81d1-7b08a5301793','fbcdce3d-8722-4f2d-8abb-0c1b2412f6f3','f19e6706-d0e4-48df-b6b0-a0e196d1b0c2');

/*Table structure for table `t_custom` */

DROP TABLE IF EXISTS `t_custom`;

CREATE TABLE `t_custom` (
  `id` varchar(40) NOT NULL COMMENT '主键',
  `custom_name` varchar(100) NOT NULL COMMENT '客户名称',
  `industry` varchar(500) DEFAULT NULL COMMENT '所属行业',
  `team` varchar(40) NOT NULL COMMENT '执行团队',
  `source` varchar(100) DEFAULT NULL COMMENT '客户来源',
  `state` varchar(40) NOT NULL COMMENT '客户状态',
  `contact_name` varchar(100) NOT NULL COMMENT '客户联系人名',
  `contact_duty` varchar(100) DEFAULT NULL COMMENT '联系人担任职务',
  `contact_landline` varchar(100) DEFAULT NULL COMMENT '联系人客户座机号码',
  `contact_cellphone` varchar(100) DEFAULT NULL COMMENT '联系人手机号码',
  `contact_fax` varchar(100) DEFAULT NULL COMMENT '联系人传真',
  `contact_email` varchar(100) DEFAULT NULL COMMENT '联系人电子邮件',
  `contact_qq` varchar(100) DEFAULT NULL COMMENT '联系人qq',
  `contact_weixin` varchar(100) DEFAULT NULL COMMENT '联系人微信',
  `contact_address` varchar(200) DEFAULT NULL COMMENT '联系人公司地址',
  `contact_website` varchar(100) DEFAULT NULL COMMENT '联系人网址',
  `company_profile` varchar(500) DEFAULT NULL COMMENT '公司简介',
  `create_user` varchar(40) NOT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_user` varchar(40) DEFAULT NULL COMMENT '上次更新人',
  `last_update_time` timestamp NULL DEFAULT NULL COMMENT '上次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';

/*Data for the table `t_custom` */

insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('0c3162f7-069f-46b6-b54c-621de7801623','123','金融业(投资/保险/证券/银行/基金)','2,6,3,5,1,4','广告呼入','签约暂停','123','123','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'iuhhn','1','2015-06-11 23:59:46','1','2015-06-15 22:14:27');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('1','测试客户','金融业(投资/保险/证券/银行/基金)','4','主动BD','签约终止','1','1','1','1','1','1','1','1','1','1','sdfsdf','2','2015-06-15 21:30:02','1','2015-06-22 10:57:47');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('1437d08e-90e4-48f7-8685-79f0a6396491','大名科技','金融业(投资/保险/证券/银行/基金)','1','广告呼入','潜在客户','郑浩','asd','12','123','31','test@test.com','123','123','123','123','123123','1','2015-07-01 22:23:10','1','2015-08-18 01:33:53');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('2bd09171-6547-4e30-b16d-d6bc7f13bfea','渤海证券','金融业(投资/保险/证券/银行/基金)','1','人脉推荐','签约运作','张力','经理','0371-0909876','13459888765','0371-0909876','123@test.com','332244422','332244422','北京市海淀区','www.baidu.com','新成立公司','1','2015-07-02 02:05:34','1','2015-07-19 17:48:41');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('38a0eacd-6b79-4011-bda9-29aae5a97357','sfdfsdf','金融业(投资/保险/证券/银行/基金)','3,4','主动BD','潜在客户','sdf','asdf','asdf','123123','123',NULL,'123','123','123','123','sdf123123','3','2015-06-15 21:30:03','1','2015-06-15 22:58:58');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('4c95efa9-4798-487a-ae36-44e5a3d5fa16','123','金融业(投资/保险/证券/银行/基金)','5,3,2,1,6,4','广告呼入','签约暂停','123','123','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'4','2015-06-15 21:30:03','1','2015-06-15 21:03:54');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('63966dad-e3e5-4815-b8be-b39d2ecdcae9','客户A','金融业(投资/保险/证券/银行/基金)','1','广告呼入','签约运作','123','123','','13458876123','sdf','sdf','sdf','dsf','sdf','sdf','sdfsd','1','2015-06-18 20:10:49','1','2015-08-16 16:14:22');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('6a56e4fa-1244-439c-8414-11ad4d946d25','测试客户','房地产开发/建筑与工程','3,6,4','机构合作','签约暂停','张三','测试人员','1234123123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'12312312','1','2015-06-14 10:53:50','1','2015-06-24 23:24:11');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('762dcab8-07e1-4c8e-9945-ebdb941734a0','史蒂芬森地方','金融业(投资/保险/证券/银行/基金)','5,4','人脉推荐','签约暂停','123','123','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2','2015-06-15 21:30:04','1','2015-06-15 21:04:39');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('abc6c0cb-1c1a-49fd-9567-e5ac38c00297','什么玩意啊','金融业(投资/保险/证券/银行/基金)','2,3,4','机构合作','潜在客户','12','123','12','123','123','123@11.com','123','123','123','123','wqeqwe','2','2015-06-15 21:30:05','1','2015-06-22 10:57:59');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('bfa0022c-18c7-4538-b7e8-71f30d1751d9','12','金融业(投资/保险/证券/银行/基金)','123','广告呼入','签约暂停','1231','123','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3','2015-06-15 21:30:05','1','2015-06-14 12:43:42');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('c9973244-d12d-4582-9604-d2596090fafe','123飒飒东风','金融业(投资/保险/证券/银行/基金)','123','机构合作','签约客户','123','123','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'4','2015-06-15 21:30:06','1','2015-06-15 21:04:03');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('dbefd624-731b-4d83-a693-3dc31042031b','大客户','金融业(投资/保险/证券/银行/基金)','1','人脉推荐','签约暂停','sadf','sdf','sdf','123','123','test@test.com','123','123','123','123','12312','2','2015-06-15 21:30:09','1','2015-08-18 01:34:20');

/*Table structure for table `t_custom_communication` */

DROP TABLE IF EXISTS `t_custom_communication`;

CREATE TABLE `t_custom_communication` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `custom_id` varchar(40) NOT NULL COMMENT '客户id',
  `commu_date` varchar(100) DEFAULT NULL COMMENT '沟通时间',
  `content` varchar(500) NOT NULL COMMENT '沟通内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(40) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户沟通记录表';

/*Data for the table `t_custom_communication` */

insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('0c5ebd97-109c-456c-8d2a-a185e9307278','dbefd624-731b-4d83-a693-3dc31042031b',NULL,'sfsd','2015-06-15 23:17:49','');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('11b63280-5e0b-45ab-be4b-04dcb2c99662','1437d08e-90e4-48f7-8685-79f0a6396491',NULL,'123','2015-07-01 23:06:27','1');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('2bcc58f9-1dd1-4d97-bd71-7d6c0ae6fa4c','6a56e4fa-1244-439c-8414-11ad4d946d25',NULL,'sdfsdf','2015-06-14 12:16:48','');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('34a45f26-59f4-4ec0-81a0-e52c867904d1','6a56e4fa-1244-439c-8414-11ad4d946d25',NULL,'saf','2015-06-14 12:17:03','');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('36ed73b0-cebf-4b8d-b3e2-0a0f34fa3884','2bd09171-6547-4e30-b16d-d6bc7f13bfea',NULL,'暂无啊','2015-07-11 10:24:00','1');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('3d35582e-80ae-4087-a2ed-9b1bbce698dd','1437d08e-90e4-48f7-8685-79f0a6396491',NULL,'123','2015-07-01 23:14:42','1');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('3d971635-cffb-44d7-84d0-92743651cb26','1437d08e-90e4-48f7-8685-79f0a6396491',NULL,'123123','2015-07-01 23:09:50','1');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('4038b564-3b02-434f-96aa-e2b80c2b16aa','38a0eacd-6b79-4011-bda9-29aae5a97357',NULL,'123123','2015-06-15 22:58:58','');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('4b653c99-fc9e-4614-9bab-cd4fad41d2be','dbefd624-731b-4d83-a693-3dc31042031b',NULL,'手动阀','2015-06-15 21:04:08','');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('53cfeed9-0155-44f1-a045-d82b96541d73','1437d08e-90e4-48f7-8685-79f0a6396491',NULL,'123','2015-07-01 23:06:24','1');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('5f3b5a9c-17c2-471d-9262-9c6e47852e04','1',NULL,'sdf','2015-06-22 10:57:47','');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('76eccff2-d8bc-48c0-97da-8c00b6c5d43b','2bd09171-6547-4e30-b16d-d6bc7f13bfea',NULL,'adasdas','2015-08-18 01:56:45','1');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('92e65acb-47d0-4c7e-94cc-23bf5a766b9b','1437d08e-90e4-48f7-8685-79f0a6396491',NULL,'sdfasdfasdfasdf','2015-07-01 23:04:48','1');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('947ab42f-5017-4faa-8531-0e47c20987a4','63966dad-e3e5-4815-b8be-b39d2ecdcae9',NULL,'e1312','2015-06-21 21:43:29','');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('97409fd3-ab40-42f2-96b0-2c6713681333','38a0eacd-6b79-4011-bda9-29aae5a97357',NULL,'asdfsdaf','2015-06-15 21:02:54','');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('a1a08296-6e58-4fd7-8a03-aec52ed397c5','1437d08e-90e4-48f7-8685-79f0a6396491',NULL,'11','2015-07-01 23:17:21','1');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('a9f6ec98-8aa5-4769-a6d0-950b43d335a9','2bd09171-6547-4e30-b16d-d6bc7f13bfea',NULL,'','2015-08-18 01:54:53','1');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('c9f020fc-122d-4162-a07d-df63d44b7f00','1437d08e-90e4-48f7-8685-79f0a6396491',NULL,'sdfasdfsdf','2015-07-01 23:05:05','1');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('cac34cae-834e-4422-abc6-c0a21df454aa','2bd09171-6547-4e30-b16d-d6bc7f13bfea',NULL,'sdfsdfsd','2015-07-11 13:09:55','1');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('d6c5527d-49a8-4f9e-859a-66eadcbd4461','2bd09171-6547-4e30-b16d-d6bc7f13bfea',NULL,'12312','2015-07-12 10:33:42','1');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('dcc0b112-57c6-4804-a1fa-773187c3499a','4c95efa9-4798-487a-ae36-44e5a3d5fa16',NULL,'士大夫','2015-06-15 21:03:54','');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('ebe90e8b-4df2-4a36-88c4-ba762afcb755','6a56e4fa-1244-439c-8414-11ad4d946d25',NULL,'123123123','2015-06-14 12:00:49','');

/*Table structure for table `t_custom_data` */

DROP TABLE IF EXISTS `t_custom_data`;

CREATE TABLE `t_custom_data` (
  `id` varchar(40) NOT NULL COMMENT '资料id',
  `custom_id` varchar(40) NOT NULL COMMENT '客户id',
  `attachment_id` varchar(40) NOT NULL COMMENT '附件id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户资料表';

/*Data for the table `t_custom_data` */

insert  into `t_custom_data`(`id`,`custom_id`,`attachment_id`) values ('0c86ed27-cbf1-401e-878e-afac2c9ce8db','63966dad-e3e5-4815-b8be-b39d2ecdcae9','c80b03a7-8f5d-4e16-a2c4-1093822d2225');
insert  into `t_custom_data`(`id`,`custom_id`,`attachment_id`) values ('3ed6a8f1-fbf4-4350-badf-401cf78c9da9','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','dc1f892c-55a0-4c6e-898f-fef79e52e6d3');
insert  into `t_custom_data`(`id`,`custom_id`,`attachment_id`) values ('4d748857-9654-4276-adfd-f6f17b5e6ff1','2bd09171-6547-4e30-b16d-d6bc7f13bfea','07dc1213-f6d8-4494-90ef-d9fae90e30ac');
insert  into `t_custom_data`(`id`,`custom_id`,`attachment_id`) values ('8adb610c-7f36-4835-a089-cd725e5e1b3a','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','da5d1bb6-d166-4225-a008-abc5dfea65e0');

/*Table structure for table `t_custom_resume` */

DROP TABLE IF EXISTS `t_custom_resume`;

CREATE TABLE `t_custom_resume` (
  `id` varchar(40) NOT NULL COMMENT '主键',
  `custom_id` varchar(40) NOT NULL COMMENT '客户id',
  `resume_id` varchar(40) NOT NULL COMMENT '简历id',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `createuser` varchar(40) NOT NULL COMMENT '创建人',
  `state` varchar(40) DEFAULT NULL COMMENT '推荐状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='简历推荐表';

/*Data for the table `t_custom_resume` */

insert  into `t_custom_resume`(`id`,`custom_id`,`resume_id`,`createtime`,`createuser`,`state`) values ('050d9f60-632e-439c-bcc6-fd5237f7fd46','0c3162f7-069f-46b6-b54c-621de7801623','040311ba-6023-4a06-ab51-64efbdc50672','2015-07-04 17:00:02','1','未推荐');
insert  into `t_custom_resume`(`id`,`custom_id`,`resume_id`,`createtime`,`createuser`,`state`) values ('2e14b4e7-ea44-4441-a572-00043ff25d48','38a0eacd-6b79-4011-bda9-29aae5a97357','040311ba-6023-4a06-ab51-64efbdc50672','2015-07-04 16:59:12','1','未推荐');
insert  into `t_custom_resume`(`id`,`custom_id`,`resume_id`,`createtime`,`createuser`,`state`) values ('2f3cdad7-dc0a-4179-8db5-f9691ab809d3','2bd09171-6547-4e30-b16d-d6bc7f13bfea','040311ba-6023-4a06-ab51-64efbdc50672','2015-07-04 17:00:01','1','未推荐');
insert  into `t_custom_resume`(`id`,`custom_id`,`resume_id`,`createtime`,`createuser`,`state`) values ('4b842a13-d554-4e3a-b4a8-da8d67b4ddb8','dbefd624-731b-4d83-a693-3dc31042031b','040311ba-6023-4a06-ab51-64efbdc50672','2015-07-04 16:59:16','1','未推荐');
insert  into `t_custom_resume`(`id`,`custom_id`,`resume_id`,`createtime`,`createuser`,`state`) values ('77c348bc-86d4-4b81-9cb7-8090f3f264d4','6a56e4fa-1244-439c-8414-11ad4d946d25','040311ba-6023-4a06-ab51-64efbdc50672','2015-07-04 16:59:11','1','未推荐');
insert  into `t_custom_resume`(`id`,`custom_id`,`resume_id`,`createtime`,`createuser`,`state`) values ('7b9af4ce-d966-4c7f-a65a-606b26f5d4f9','1437d08e-90e4-48f7-8685-79f0a6396491','040311ba-6023-4a06-ab51-64efbdc50672','2015-07-04 17:00:07','1','未推荐');
insert  into `t_custom_resume`(`id`,`custom_id`,`resume_id`,`createtime`,`createuser`,`state`) values ('c04a2872-7b02-4aa4-8c98-5a96a4998f64','63966dad-e3e5-4815-b8be-b39d2ecdcae9','040311ba-6023-4a06-ab51-64efbdc50672','2015-07-04 17:00:07','1','未推荐');

/*Table structure for table `t_custom_team` */

DROP TABLE IF EXISTS `t_custom_team`;

CREATE TABLE `t_custom_team` (
  `id` varchar(40) NOT NULL COMMENT 'id',
  `user_id` varchar(40) NOT NULL COMMENT '团队id',
  `custom_id` varchar(40) NOT NULL COMMENT '客户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户执行团队';

/*Data for the table `t_custom_team` */

insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('059b9265-f1b8-4bea-b2c0-531e431c74e6','2','0c3162f7-069f-46b6-b54c-621de7801623');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('1','1','38a0eacd-6b79-4011-bda9-29aae5a97357');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('136d0d0e-2e57-49d1-8cb8-9b81d48c78ad','3','38a0eacd-6b79-4011-bda9-29aae5a97357');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('2741c3cf-94c9-4c2c-8bc5-8f4277ab1571','3','0c3162f7-069f-46b6-b54c-621de7801623');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('383d5263-2ece-450b-9df0-5f84b298af7f','3','abc6c0cb-1c1a-49fd-9567-e5ac38c00297');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('3dacf5b5-7c16-4be8-97f5-fb99f597d8ab','4','abc6c0cb-1c1a-49fd-9567-e5ac38c00297');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('58f6ff9b-8d59-4a66-905f-f26c5a5abde4','4','1');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('734335ff-b1c1-40a6-b1e5-700de5f03cb6','3','6a56e4fa-1244-439c-8414-11ad4d946d25');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('82b03cee-ae8c-4b63-8f84-e9937679bd86','4','38a0eacd-6b79-4011-bda9-29aae5a97357');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('83fa750f-77ca-47bc-882c-206dee1221e0','6','0c3162f7-069f-46b6-b54c-621de7801623');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('895925ce-ee48-476e-9e26-37eeae16b226','1','2bd09171-6547-4e30-b16d-d6bc7f13bfea');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('9737abb4-2f42-4e22-ba6d-c8cf577761aa','1','63966dad-e3e5-4815-b8be-b39d2ecdcae9');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('99a53aaf-3a2b-44c1-b3c3-7d6dacb64911','1','dbefd624-731b-4d83-a693-3dc31042031b');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('9ceaeb62-8250-42b0-9c4e-a4032b18f84d','2','abc6c0cb-1c1a-49fd-9567-e5ac38c00297');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('aa730fd8-65c3-40c2-a6be-171f77ca5c33','1','1437d08e-90e4-48f7-8685-79f0a6396491');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('b5ca8fe6-642c-45fa-ac3f-05b0a6c3f26f','5','0c3162f7-069f-46b6-b54c-621de7801623');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('c0da6887-423d-4c79-a869-678f76e90916','4','6a56e4fa-1244-439c-8414-11ad4d946d25');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('dc8ac145-4c91-47e7-9eac-227acb1a012a','4','0c3162f7-069f-46b6-b54c-621de7801623');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('e5c5b979-d905-47bc-bb5b-1265fccaaff6','1','0c3162f7-069f-46b6-b54c-621de7801623');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('fc5b5f35-2d07-44d9-bd0b-3b2da4e22982','6','6a56e4fa-1244-439c-8414-11ad4d946d25');

/*Table structure for table `t_invoice` */

DROP TABLE IF EXISTS `t_invoice`;

CREATE TABLE `t_invoice` (
  `id` varchar(40) NOT NULL COMMENT '发票id',
  `apply_user` varchar(40) NOT NULL COMMENT '申请人',
  `apply_time` date NOT NULL COMMENT '申请时间',
  `custom_contract` varchar(100) NOT NULL COMMENT '客户合同编号',
  `in_user` varchar(100) DEFAULT NULL COMMENT '入职人选',
  `type` varchar(50) NOT NULL COMMENT '发票类型',
  `property` varchar(100) DEFAULT NULL COMMENT '发票属性',
  `total` float NOT NULL COMMENT '发票金额',
  `state` varchar(100) NOT NULL COMMENT '发票状态',
  `check_user` varchar(100) DEFAULT NULL COMMENT '开票人',
  `income_state` varchar(50) DEFAULT NULL COMMENT '回款状态',
  `comment` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_user` varchar(40) NOT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `custom_id` varchar(40) DEFAULT NULL COMMENT '客户id',
  `contract_no` varchar(100) DEFAULT NULL COMMENT '合同编号',
  `resume_id` varchar(40) DEFAULT NULL COMMENT '简历id',
  `resume_desc` varchar(100) DEFAULT NULL COMMENT '入职人选描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发票表';

/*Data for the table `t_invoice` */

insert  into `t_invoice`(`id`,`apply_user`,`apply_time`,`custom_contract`,`in_user`,`type`,`property`,`total`,`state`,`check_user`,`income_state`,`comment`,`create_user`,`create_time`,`custom_id`,`contract_no`,`resume_id`,`resume_desc`) values ('2626bea0-1e89-4e5c-a7dd-414ca90ce06a','6bdb5752-2310-4680-b77e-3d314dead3ef','2015-08-06','渤海证券(合同编号：20150001)',NULL,'专用发票','其它',123,'已开出','6bdb5752-2310-4680-b77e-3d314dead3ef','已到账','1231','1','2015-08-16 18:07:58','2bd09171-6547-4e30-b16d-d6bc7f13bfea','20150001','040311ba-6023-4a06-ab51-64efbdc50672','郑亮 - 证券交易师');
insert  into `t_invoice`(`id`,`apply_user`,`apply_time`,`custom_contract`,`in_user`,`type`,`property`,`total`,`state`,`check_user`,`income_state`,`comment`,`create_user`,`create_time`,`custom_id`,`contract_no`,`resume_id`,`resume_desc`) values ('5bbb76d2-8b35-4638-a940-2ade74b1e120','2be301f4-a9d6-4acd-9866-ee92788dc908','2015-08-13','渤海证券(合同编号：20150001)',NULL,'普通发票','服务费',123,'已开出','6bdb5752-2310-4680-b77e-3d314dead3ef','未到账','12','6bdb5752-2310-4680-b77e-3d314dead3ef','2015-08-16 18:45:08','2bd09171-6547-4e30-b16d-d6bc7f13bfea','20150001','040311ba-6023-4a06-ab51-64efbdc50672','郑亮 - 证券交易师');

/*Table structure for table `t_job` */

DROP TABLE IF EXISTS `t_job`;

CREATE TABLE `t_job` (
  `id` varchar(40) NOT NULL COMMENT '职位id',
  `custom_id` varchar(40) NOT NULL COMMENT '客户id',
  `contract_id` varchar(40) NOT NULL COMMENT '合同id',
  `name` varchar(100) NOT NULL COMMENT '职位名称',
  `pay_min` varchar(100) DEFAULT NULL COMMENT '薪酬待遇-从',
  `pay_max` varchar(100) DEFAULT NULL COMMENT '薪酬待遇-至',
  `state` varchar(50) NOT NULL COMMENT '职位状态',
  `team` varchar(40) NOT NULL COMMENT '执行团队',
  `workplace` varchar(200) DEFAULT NULL COMMENT '工作地点',
  `job_type` varchar(100) DEFAULT NULL COMMENT '职位类别',
  `industry` varchar(200) DEFAULT NULL COMMENT '所属行业',
  `work_year` varchar(50) DEFAULT NULL COMMENT '工作年限',
  `require_people` int(11) DEFAULT NULL COMMENT '需求人数',
  `department` varchar(100) DEFAULT NULL COMMENT '所属部门',
  `report_obj` varchar(100) DEFAULT NULL COMMENT '汇报对象',
  `age_min` int(11) DEFAULT NULL COMMENT '年龄要求-从',
  `age_max` int(11) DEFAULT NULL COMMENT '年龄要求-至',
  `sex_limit` varchar(10) DEFAULT NULL COMMENT '性别要求',
  `edu_limit` varchar(50) DEFAULT NULL COMMENT '学历要求',
  `language_limit` varchar(100) DEFAULT NULL COMMENT '语言要求',
  `job_desc` varchar(500) DEFAULT NULL COMMENT '职位描述',
  `company_desc` varchar(500) DEFAULT NULL COMMENT '公司介绍',
  `create_user` varchar(40) NOT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_user` varchar(40) DEFAULT NULL COMMENT '上次更新人',
  `last_update_time` timestamp NULL DEFAULT NULL COMMENT '上次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位表';

/*Data for the table `t_job` */

insert  into `t_job`(`id`,`custom_id`,`contract_id`,`name`,`pay_min`,`pay_max`,`state`,`team`,`workplace`,`job_type`,`industry`,`work_year`,`require_people`,`department`,`report_obj`,`age_min`,`age_max`,`sex_limit`,`edu_limit`,`language_limit`,`job_desc`,`company_desc`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('2e92ce3b-3a05-41c2-a58b-0061de11a92c','63966dad-e3e5-4815-b8be-b39d2ecdcae9','19a34bdb-7386-4a03-8fd3-069e819467d8','职位','1','2','结束','a52649ff-85aa-453b-9ad0-ee8a094ced69','1','1','1','1',1,'1','1',1,1,'不限','硕士以上','1','1','123123','1','2015-06-18 21:12:41','1','2015-08-16 16:11:45');
insert  into `t_job`(`id`,`custom_id`,`contract_id`,`name`,`pay_min`,`pay_max`,`state`,`team`,`workplace`,`job_type`,`industry`,`work_year`,`require_people`,`department`,`report_obj`,`age_min`,`age_max`,`sex_limit`,`edu_limit`,`language_limit`,`job_desc`,`company_desc`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('32013d37-a495-4741-a52e-c9cd50032356','6a56e4fa-1244-439c-8414-11ad4d946d25','c4af03c8-8575-4553-a4c3-4e66c1aeacd2','测试职位','11','12','运作','2,3','测试','测试','测试','12',1,'1','1',1,1,'不限','硕士以上',NULL,'1','测试','1','2015-06-18 21:59:58','1','2015-06-28 10:26:07');
insert  into `t_job`(`id`,`custom_id`,`contract_id`,`name`,`pay_min`,`pay_max`,`state`,`team`,`workplace`,`job_type`,`industry`,`work_year`,`require_people`,`department`,`report_obj`,`age_min`,`age_max`,`sex_limit`,`edu_limit`,`language_limit`,`job_desc`,`company_desc`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('421038fd-c415-49d5-83c8-a4ac90749c5a','2bd09171-6547-4e30-b16d-d6bc7f13bfea','372a6e8b-d644-4642-9610-edf0294f86a3','证券交易师','1','50','运作','1','北京','业务员','证券','3',2,'市场部','无',25,40,'男','本科以上','无','市场业务员','大力发展中','1','2015-07-12 12:08:41','1','2015-07-19 17:52:01');
insert  into `t_job`(`id`,`custom_id`,`contract_id`,`name`,`pay_min`,`pay_max`,`state`,`team`,`workplace`,`job_type`,`industry`,`work_year`,`require_people`,`department`,`report_obj`,`age_min`,`age_max`,`sex_limit`,`edu_limit`,`language_limit`,`job_desc`,`company_desc`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('6c3add2f-01bc-4879-b720-81526ad0a5ef','63966dad-e3e5-4815-b8be-b39d2ecdcae9','19a34bdb-7386-4a03-8fd3-069e819467d8','测试1','1','1','关闭','a52649ff-85aa-453b-9ad0-ee8a094ced69','1','1','1','1',1,'1','1',1,1,'不限','硕士以上','1','1','dsdfsdf','1','2015-06-18 21:16:56','1','2015-08-16 16:12:07');
insert  into `t_job`(`id`,`custom_id`,`contract_id`,`name`,`pay_min`,`pay_max`,`state`,`team`,`workplace`,`job_type`,`industry`,`work_year`,`require_people`,`department`,`report_obj`,`age_min`,`age_max`,`sex_limit`,`edu_limit`,`language_limit`,`job_desc`,`company_desc`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('79694b87-fadb-4f6d-9bea-54a00bcfd9bf','dbefd624-731b-4d83-a693-3dc31042031b','d9e6b2d1-785c-4dd3-8ce9-1971f66e8e53','管理人员','10','20','运作','5,6','北京望京','管理','互联网','3',3,'一部','老被',22,40,'不限','博士以上','英语','无特殊要求','好东西啊','1','2015-06-22 11:00:34','1','2015-06-22 11:49:52');
insert  into `t_job`(`id`,`custom_id`,`contract_id`,`name`,`pay_min`,`pay_max`,`state`,`team`,`workplace`,`job_type`,`industry`,`work_year`,`require_people`,`department`,`report_obj`,`age_min`,`age_max`,`sex_limit`,`edu_limit`,`language_limit`,`job_desc`,`company_desc`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('828272d7-3ece-4de1-b159-99290d12dcc5','2bd09171-6547-4e30-b16d-d6bc7f13bfea','5c7e6f7e-4bf0-4bea-988f-5d4683718a6b','财务','1','2','暂停','2be301f4-a9d6-4acd-9866-ee92788dc908','1','1','1','1',1,'1','1',1,1,'不限','本科以上','1','1','11','1','2015-07-18 18:46:06','1','2015-08-16 16:11:28');

/*Table structure for table `t_job_comm_file` */

DROP TABLE IF EXISTS `t_job_comm_file`;

CREATE TABLE `t_job_comm_file` (
  `id` varchar(40) NOT NULL,
  `attachment_id` varchar(40) NOT NULL COMMENT '附件id',
  `job_id` varchar(40) NOT NULL COMMENT '职位id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位沟通附件表';

/*Data for the table `t_job_comm_file` */

/*Table structure for table `t_job_communication` */

DROP TABLE IF EXISTS `t_job_communication`;

CREATE TABLE `t_job_communication` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `job_id` varchar(40) NOT NULL COMMENT '职位id',
  `commu_date` varchar(100) DEFAULT NULL COMMENT '沟通时间',
  `content` varchar(500) DEFAULT NULL COMMENT '沟通内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(40) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='团队职位沟通记录表';

/*Data for the table `t_job_communication` */

insert  into `t_job_communication`(`id`,`job_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('09a04f1c-1e18-475f-82db-8c961a2f310b','828272d7-3ece-4de1-b159-99290d12dcc5',NULL,'123','2015-07-19 15:45:16','1');
insert  into `t_job_communication`(`id`,`job_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('19ceee4e-2e53-4ad3-b388-851a95326ed3','6c3add2f-01bc-4879-b720-81526ad0a5ef',NULL,'132123','2015-08-18 01:49:04','1');
insert  into `t_job_communication`(`id`,`job_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('5f3071ca-3e12-426c-8b4d-fbef8c92b836','2e92ce3b-3a05-41c2-a58b-0061de11a92c',NULL,'客户反应还可以','2015-07-18 11:53:50','1');
insert  into `t_job_communication`(`id`,`job_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('af0904d0-9868-4540-bbea-c64336a492f2','421038fd-c415-49d5-83c8-a4ac90749c5a',NULL,'123','2015-07-19 17:22:38','1');
insert  into `t_job_communication`(`id`,`job_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('ed3a73e7-4b65-4448-a2df-65cd060b4cc5','6c3add2f-01bc-4879-b720-81526ad0a5ef',NULL,'sdfsdfsdfsd','2015-08-18 01:49:14','1');

/*Table structure for table `t_job_team` */

DROP TABLE IF EXISTS `t_job_team`;

CREATE TABLE `t_job_team` (
  `id` varchar(40) NOT NULL COMMENT 'id',
  `user_id` varchar(40) NOT NULL COMMENT '团队id',
  `job_id` varchar(40) NOT NULL COMMENT '职位id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位执行团队';

/*Data for the table `t_job_team` */

insert  into `t_job_team`(`id`,`user_id`,`job_id`) values ('92065ddc-d367-4a04-b191-ab47d95ee5a8','a52649ff-85aa-453b-9ad0-ee8a094ced69','2e92ce3b-3a05-41c2-a58b-0061de11a92c');
insert  into `t_job_team`(`id`,`user_id`,`job_id`) values ('97d664ba-aeee-4fc7-8a16-bacd0e25d8c8','a52649ff-85aa-453b-9ad0-ee8a094ced69','6c3add2f-01bc-4879-b720-81526ad0a5ef');
insert  into `t_job_team`(`id`,`user_id`,`job_id`) values ('c831346b-1f7d-4fec-b4ca-4be489769f9b','1','421038fd-c415-49d5-83c8-a4ac90749c5a');
insert  into `t_job_team`(`id`,`user_id`,`job_id`) values ('edc9f43c-97aa-4580-9a5e-856e2a648d79','2be301f4-a9d6-4acd-9866-ee92788dc908','828272d7-3ece-4de1-b159-99290d12dcc5');

/*Table structure for table `t_pub` */

DROP TABLE IF EXISTS `t_pub`;

CREATE TABLE `t_pub` (
  `id` varchar(40) NOT NULL COMMENT '公共id',
  `content` varchar(500) NOT NULL COMMENT '公告内容',
  `create_user` varchar(40) NOT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `state` varchar(40) NOT NULL COMMENT '公告状态',
  `last_update_user` varchar(40) DEFAULT NULL COMMENT '更新人',
  `last_update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `pub_time` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告管理表';

/*Data for the table `t_pub` */

insert  into `t_pub`(`id`,`content`,`create_user`,`create_time`,`state`,`last_update_user`,`last_update_time`,`pub_time`) values ('626d99ec-c68b-42fc-8c15-bac6f9c06568','管理系统正式上线，请大家踊跃测试并反馈问题','1','2015-07-19 17:55:57','已发布','1','2015-07-19 17:55:57','2015-07-19 17:56:09');

/*Table structure for table `t_resume` */

DROP TABLE IF EXISTS `t_resume`;

CREATE TABLE `t_resume` (
  `id` varchar(40) NOT NULL COMMENT '简历id',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `address` varchar(200) DEFAULT NULL COMMENT '居住地址',
  `sex` varchar(10) NOT NULL COMMENT '性别',
  `education` varchar(100) DEFAULT NULL COMMENT '最高学历',
  `birthcay` date DEFAULT NULL COMMENT '出生日期',
  `marrage` varchar(20) DEFAULT NULL COMMENT '婚姻状况',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `work_state` varchar(50) DEFAULT NULL COMMENT '工作状态',
  `resumt_desc` varchar(500) DEFAULT NULL COMMENT '个人简介',
  `head_image` varchar(100) DEFAULT NULL COMMENT '头像路径',
  `create_user` varchar(40) NOT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` timestamp NULL DEFAULT NULL COMMENT '上次更新时间',
  `last_update_user` varchar(40) DEFAULT NULL COMMENT '上次更新人',
  `year_pay` varchar(40) DEFAULT NULL COMMENT '目前年薪',
  `city` varchar(100) DEFAULT NULL COMMENT '所在城市',
  `industry` varchar(200) DEFAULT NULL COMMENT '行业',
  `duty` varchar(100) DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='简历表';

/*Data for the table `t_resume` */

insert  into `t_resume`(`id`,`name`,`address`,`sex`,`education`,`birthcay`,`marrage`,`phone`,`email`,`work_state`,`resumt_desc`,`head_image`,`create_user`,`create_time`,`last_update_time`,`last_update_user`,`year_pay`,`city`,`industry`,`duty`) values ('040311ba-6023-4a06-ab51-64efbdc50672','郑亮','北京市','男','博士以上','1988-07-15','未婚','1123123123123','test@test.com','在职','急需工作一份',NULL,'1','2015-07-04 16:12:46','2015-07-19 17:52:55','1','22','海淀区','金融业(投资/保险/证券/银行/基金)','财务/审计/统计类');
insert  into `t_resume`(`id`,`name`,`address`,`sex`,`education`,`birthcay`,`marrage`,`phone`,`email`,`work_state`,`resumt_desc`,`head_image`,`create_user`,`create_time`,`last_update_time`,`last_update_user`,`year_pay`,`city`,`industry`,`duty`) values ('34022055-ab45-4c7f-bbbc-28ca5b6b95c5','李丁腌','西四环大陆','男','硕士以上','1977-07-08','已婚','13262323112312','test@test.com','在职','暂无个人简介',NULL,'1','2015-07-04 16:09:23','2015-08-16 14:53:15','1','12',NULL,'房地产开发/建筑与工程','财务/审计/统计类');
insert  into `t_resume`(`id`,`name`,`address`,`sex`,`education`,`birthcay`,`marrage`,`phone`,`email`,`work_state`,`resumt_desc`,`head_image`,`create_user`,`create_time`,`last_update_time`,`last_update_user`,`year_pay`,`city`,`industry`,`duty`) values ('6b9b0c9c-958c-4668-91d1-43e1cc188cbc','张晓军','123','男','本科以上','1950-07-10','未婚','13603988712','123@test.com','离职','123123','caec8088-1c7a-4778-86a8-5791029f9264','1','2015-07-02 01:24:23','2015-09-06 21:29:21','1','20','北京','房地产开发/建筑与工程','人力资源类');
insert  into `t_resume`(`id`,`name`,`address`,`sex`,`education`,`birthcay`,`marrage`,`phone`,`email`,`work_state`,`resumt_desc`,`head_image`,`create_user`,`create_time`,`last_update_time`,`last_update_user`,`year_pay`,`city`,`industry`,`duty`) values ('c180d5a2-3c2e-4d77-8aa9-a80fdec62f94','张天宇','北京三里屯','男','博士以上','2015-08-12','已婚','14117123123123','henan@test.com','在职','无',NULL,'1','2015-08-15 12:05:01','2015-08-15 12:05:01','1','12000','北京','房地产开发/建筑与工程','财务/审计/统计类');
insert  into `t_resume`(`id`,`name`,`address`,`sex`,`education`,`birthcay`,`marrage`,`phone`,`email`,`work_state`,`resumt_desc`,`head_image`,`create_user`,`create_time`,`last_update_time`,`last_update_user`,`year_pay`,`city`,`industry`,`duty`) values ('cb873a3e-c5d4-43a9-92f0-113a6afa89f4','周炳','北京市河西','男','硕士以上','2015-08-05','已婚','11111111111','test@test.com','在职','12312','0fbaeac6-f4c6-4dc1-a688-71d0f48495fe','1','2015-08-16 19:35:55','2015-08-16 19:35:55','1','12',NULL,'房地产开发/建筑与工程','经营管理类');
insert  into `t_resume`(`id`,`name`,`address`,`sex`,`education`,`birthcay`,`marrage`,`phone`,`email`,`work_state`,`resumt_desc`,`head_image`,`create_user`,`create_time`,`last_update_time`,`last_update_user`,`year_pay`,`city`,`industry`,`duty`) values ('f862d649-dc68-4d9f-92a6-4f6fa1a487e5','张浏三','小滨海','女','博士以上','1997-08-23','未婚','13601832211','tese@tset.com','离职','1',NULL,'6bdb5752-2310-4680-b77e-3d314dead3ef','2015-08-16 16:49:33','2015-08-18 01:33:15','1','20','襄樊','房地产开发/建筑与工程','经营管理类');

/*Table structure for table `t_resume_communication` */

DROP TABLE IF EXISTS `t_resume_communication`;

CREATE TABLE `t_resume_communication` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `resume_id` varchar(40) NOT NULL COMMENT '客户id',
  `commu_date` varchar(100) DEFAULT NULL COMMENT '沟通时间',
  `content` varchar(500) NOT NULL COMMENT '沟通内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(100) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='简历沟通记录表';

/*Data for the table `t_resume_communication` */

insert  into `t_resume_communication`(`id`,`resume_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('2bdae843-e4eb-42f3-b278-8a7edbcd430d','0e2842f8-abf1-4bd1-876a-450fa26dfcc7',NULL,'123123','2015-06-22 19:56:17','1');
insert  into `t_resume_communication`(`id`,`resume_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('3afc5953-eda7-4b6e-865a-c5ebeba280f8','f862d649-dc68-4d9f-92a6-4f6fa1a487e5',NULL,'啊实打上的','2015-08-16 16:57:19','6bdb5752-2310-4680-b77e-3d314dead3ef');
insert  into `t_resume_communication`(`id`,`resume_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('a55a13fb-2fdc-4763-8f80-fde885650ad4','6b9b0c9c-958c-4668-91d1-43e1cc188cbc',NULL,'213123','2015-07-02 01:38:44','1');
insert  into `t_resume_communication`(`id`,`resume_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('e8d40f1e-9c6e-4be5-b378-66c265a120ec','040311ba-6023-4a06-ab51-64efbdc50672',NULL,'此人是可造之才','2015-07-19 17:54:37','1');

/*Table structure for table `t_resume_date` */

DROP TABLE IF EXISTS `t_resume_date`;

CREATE TABLE `t_resume_date` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `resume_id` varchar(40) NOT NULL COMMENT '简历id',
  `attachment_id` varchar(40) NOT NULL COMMENT '附件id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='简历附件';

/*Data for the table `t_resume_date` */

insert  into `t_resume_date`(`id`,`resume_id`,`attachment_id`) values ('2524cef2-0a33-4f62-b08e-eba47866446b','040311ba-6023-4a06-ab51-64efbdc50672','34187bbb-7adc-44fa-bb3d-9feed7364c43');
insert  into `t_resume_date`(`id`,`resume_id`,`attachment_id`) values ('5b8a6860-a79e-4cde-8415-64cf898be5e8','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','b94c4d10-fa14-405b-8c05-8714d291cae8');
insert  into `t_resume_date`(`id`,`resume_id`,`attachment_id`) values ('5f51fba2-0c8b-45bc-a51e-d218f4aada4d','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','c42a8736-e2df-4563-be03-563221ea84ea');
insert  into `t_resume_date`(`id`,`resume_id`,`attachment_id`) values ('85e3c304-6e48-43e0-8531-3a670758db18','cb873a3e-c5d4-43a9-92f0-113a6afa89f4','639ce2e4-94b1-4359-8b46-2c47fb821769');
insert  into `t_resume_date`(`id`,`resume_id`,`attachment_id`) values ('d854cdaf-5585-431a-b4a0-6716ff3156c7','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','b6fab14c-f30c-424b-824a-771fdc1fa136');

/*Table structure for table `t_resume_edu` */

DROP TABLE IF EXISTS `t_resume_edu`;

CREATE TABLE `t_resume_edu` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `resume_id` varchar(40) NOT NULL COMMENT '简历id',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `time_begin` varchar(100) DEFAULT NULL COMMENT '开始日期',
  `time_end` varchar(100) DEFAULT NULL COMMENT '结束日期',
  `org` varchar(100) DEFAULT NULL COMMENT '培训机构',
  `course` varchar(500) DEFAULT NULL COMMENT '培训课程',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_user` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教育培训经历';

/*Data for the table `t_resume_edu` */

insert  into `t_resume_edu`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`org`,`course`,`create_time`,`create_user`) values ('0cf87495-1fad-4aa9-ae07-5089c377d923','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','123123','2015-06-12','2015-06-11','3123','123',NULL,NULL);
insert  into `t_resume_edu`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`org`,`course`,`create_time`,`create_user`) values ('0d2472dd-08be-4160-9a8a-c41eb59d8a11','040311ba-6023-4a06-ab51-64efbdc50672','各种销售技巧','2015-07-07','2015-07-04','科技之上培训部','销售','2015-07-19 17:53:50','1');
insert  into `t_resume_edu`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`org`,`course`,`create_time`,`create_user`) values ('2fe172d7-153e-4347-8352-29c953398502','cb873a3e-c5d4-43a9-92f0-113a6afa89f4','本科','2011-07','2015-07','清华大学','新闻传播','2015-08-16 19:43:38','1');
insert  into `t_resume_edu`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`org`,`course`,`create_time`,`create_user`) values ('351d6342-941c-489a-83f3-9e9a9d86bcf1','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','32','2015-06-04','2015-06-02','1','2',NULL,NULL);
insert  into `t_resume_edu`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`org`,`course`,`create_time`,`create_user`) values ('4b523738-715f-4933-8507-ac802cf28647','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','12312312','2015-06-13','2015-06-03','123','123',NULL,NULL);
insert  into `t_resume_edu`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`org`,`course`,`create_time`,`create_user`) values ('6584013c-dbb5-4855-95c6-8db48d8a0c03','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','123123','2015-07-16','2015-07-24','123','12312',NULL,NULL);
insert  into `t_resume_edu`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`org`,`course`,`create_time`,`create_user`) values ('697716e0-2ef7-492c-b059-7dd92ea71d6a','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','大专','2015-08','2007-08','小学校','科技','2015-09-06 21:29:42','1');
insert  into `t_resume_edu`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`org`,`course`,`create_time`,`create_user`) values ('7d1f6112-d8f3-41b7-a08d-06128b068f4c','040311ba-6023-4a06-ab51-64efbdc50672','123','2015-07','2015-07','123','123','2015-08-16 13:04:11','1');
insert  into `t_resume_edu`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`org`,`course`,`create_time`,`create_user`) values ('a36c4713-962b-4631-ba7f-f88e741fdb3a','38b8c57e-276d-4ba3-b02f-1a3ad09488c6','第三方','2015-06-11','2015-06-03','是否','大份',NULL,NULL);
insert  into `t_resume_edu`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`org`,`course`,`create_time`,`create_user`) values ('f855739e-9cbd-466c-92df-79075d89d87a','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','qweqweqw','2015-06-05','2015-06-10','qwe','qwe',NULL,NULL);

/*Table structure for table `t_resume_job` */

DROP TABLE IF EXISTS `t_resume_job`;

CREATE TABLE `t_resume_job` (
  `id` varchar(40) NOT NULL COMMENT 'id',
  `resume_id` varchar(40) NOT NULL COMMENT '简历id',
  `job_id` varchar(40) NOT NULL COMMENT '职位id',
  `verify_state` varchar(40) DEFAULT '审核中' COMMENT '审核状态',
  `recom_state` varchar(40) DEFAULT '待处理' COMMENT '推荐状态',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(40) NOT NULL COMMENT '创建人',
  `recom_time` timestamp NULL DEFAULT NULL COMMENT '推荐时间',
  `verify_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `recom_user` varchar(40) DEFAULT NULL COMMENT '推荐人',
  `verify_user` varchar(40) DEFAULT NULL COMMENT '审核人',
  `mianshi_count` varchar(10) DEFAULT '0' COMMENT '面试次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='简历职位关联表';

/*Data for the table `t_resume_job` */

insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`,`mianshi_count`) values ('1cbe5813-90f6-476b-a8cd-181447769a20','040311ba-6023-4a06-ab51-64efbdc50672','421038fd-c415-49d5-83c8-a4ac90749c5a','终试','已推荐',NULL,'1','2015-08-16 13:55:56','2015-08-16 14:59:23','1','1','4');
insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`,`mianshi_count`) values ('32d5a45a-fb3e-486e-a911-eef2bba8fa62','040311ba-6023-4a06-ab51-64efbdc50672','79694b87-fadb-4f6d-9bea-54a00bcfd9bf','审核中','已推荐',NULL,'1','2015-08-16 14:52:45',NULL,'1',NULL,'0');
insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`,`mianshi_count`) values ('509dd581-d38b-4655-9d48-f15dbaf9a92e','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','6c3add2f-01bc-4879-b720-81526ad0a5ef','审核中','已推荐',NULL,'6bdb5752-2310-4680-b77e-3d314dead3ef','2015-08-18 01:49:31',NULL,'6bdb5752-2310-4680-b77e-3d314dead3ef',NULL,'0');
insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`,`mianshi_count`) values ('7e30acc6-73ef-4b26-bd74-8cc40fae9cbb','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','79694b87-fadb-4f6d-9bea-54a00bcfd9bf','审核中','已推荐',NULL,'1','2015-07-19 11:53:52',NULL,'1',NULL,'0');
insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`,`mianshi_count`) values ('7f7cc177-ca3f-4958-a6b7-737d58c46dc2','cb873a3e-c5d4-43a9-92f0-113a6afa89f4','828272d7-3ece-4de1-b159-99290d12dcc5','审核中','待处理',NULL,'a52649ff-85aa-453b-9ad0-ee8a094ced69','2015-08-17 20:52:49',NULL,'a52649ff-85aa-453b-9ad0-ee8a094ced69',NULL,'0');
insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`,`mianshi_count`) values ('849c3068-4742-4b4d-8808-58bec110906a','34022055-ab45-4c7f-bbbc-28ca5b6b95c5','79694b87-fadb-4f6d-9bea-54a00bcfd9bf','审核中','已推荐',NULL,'1','2015-07-19 11:53:46',NULL,'1',NULL,'0');
insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`,`mianshi_count`) values ('85874758-fb90-4399-939f-6fd79efd9aa7','c180d5a2-3c2e-4d77-8aa9-a80fdec62f94','79694b87-fadb-4f6d-9bea-54a00bcfd9bf','审核中','已推荐',NULL,'1','2015-08-16 14:53:03',NULL,'1',NULL,'0');
insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`,`mianshi_count`) values ('c2ac0aa9-0c59-4497-9d6d-588addd39bde','f862d649-dc68-4d9f-92a6-4f6fa1a487e5','828272d7-3ece-4de1-b159-99290d12dcc5','入职','已推荐',NULL,'6bdb5752-2310-4680-b77e-3d314dead3ef','2015-08-16 16:53:31','2015-08-16 18:40:37','6bdb5752-2310-4680-b77e-3d314dead3ef','1','0');
insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`,`mianshi_count`) values ('cd6565f8-835c-4e9d-a113-65ae6f7795cb','040311ba-6023-4a06-ab51-64efbdc50672','828272d7-3ece-4de1-b159-99290d12dcc5','入职','已推荐',NULL,'1','2015-07-19 16:05:13','2015-08-16 18:40:32','1','1','0');
insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`,`mianshi_count`) values ('f82eb635-2d13-413f-9486-b265cb80e235','040311ba-6023-4a06-ab51-64efbdc50672','32013d37-a495-4741-a52e-c9cd50032356','审核中','已推荐',NULL,'1','2015-08-16 14:52:31',NULL,'1',NULL,'0');

/*Table structure for table `t_resume_job_comm` */

DROP TABLE IF EXISTS `t_resume_job_comm`;

CREATE TABLE `t_resume_job_comm` (
  `id` varchar(40) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(40) NOT NULL,
  `content` varchar(500) NOT NULL COMMENT '沟通内容',
  `resumejob_id` varchar(40) NOT NULL COMMENT '推荐简历对照id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推荐简历的沟通记录';

/*Data for the table `t_resume_job_comm` */

insert  into `t_resume_job_comm`(`id`,`create_date`,`create_user`,`content`,`resumejob_id`) values ('12483962-7eaf-4ac4-8278-df10d234b0f2','2015-07-19 16:45:07','1','ewqeqweqweqweqweqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq','1cbe5813-90f6-476b-a8cd-181447769a20');
insert  into `t_resume_job_comm`(`id`,`create_date`,`create_user`,`content`,`resumejob_id`) values ('17f3438f-d9b1-4f29-96ab-f7e9f6a81a1f','2015-08-18 01:44:59','1','asdasd','32d5a45a-fb3e-486e-a911-eef2bba8fa62');
insert  into `t_resume_job_comm`(`id`,`create_date`,`create_user`,`content`,`resumejob_id`) values ('37a03b6c-3dee-46e0-897b-2bdd088309fe','2015-07-19 16:06:33','1','还可以','cd6565f8-835c-4e9d-a113-65ae6f7795cb');
insert  into `t_resume_job_comm`(`id`,`create_date`,`create_user`,`content`,`resumejob_id`) values ('5d25bf8b-8693-4bad-afbc-07a600a0808e','2015-07-19 16:06:45','1','确定录用了，评价不错还','cd6565f8-835c-4e9d-a113-65ae6f7795cb');
insert  into `t_resume_job_comm`(`id`,`create_date`,`create_user`,`content`,`resumejob_id`) values ('6fb5a9bd-2573-404d-8fa7-a5f66c225f18','2015-08-18 01:44:39','1','213123','7e30acc6-73ef-4b26-bd74-8cc40fae9cbb');
insert  into `t_resume_job_comm`(`id`,`create_date`,`create_user`,`content`,`resumejob_id`) values ('d6f8af1e-3af0-4730-b35a-5880e82906fd','2015-08-18 01:44:57','1','dasdasdasd','32d5a45a-fb3e-486e-a911-eef2bba8fa62');
insert  into `t_resume_job_comm`(`id`,`create_date`,`create_user`,`content`,`resumejob_id`) values ('ec714b88-3210-483d-9efe-8a63fd9e84c8','2015-08-18 01:45:00','1','asdas','32d5a45a-fb3e-486e-a911-eef2bba8fa62');

/*Table structure for table `t_resume_language` */

DROP TABLE IF EXISTS `t_resume_language`;

CREATE TABLE `t_resume_language` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `resume_id` varchar(40) NOT NULL COMMENT '简历id',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `lan_type` varchar(100) DEFAULT NULL COMMENT '语种',
  `read_ab` varchar(100) DEFAULT NULL COMMENT '读写能力',
  `listen_ab` varchar(100) DEFAULT NULL COMMENT '听说能力',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_user` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='语言能力';

/*Data for the table `t_resume_language` */

insert  into `t_resume_language`(`id`,`resume_id`,`content`,`lan_type`,`read_ab`,`listen_ab`,`create_time`,`create_user`) values ('02e48948-eb5e-4264-ab8d-068e452ccc37','040311ba-6023-4a06-ab51-64efbdc50672','eqweqw','英语','良好','熟练','2015-08-16 12:55:36','1');
insert  into `t_resume_language`(`id`,`resume_id`,`content`,`lan_type`,`read_ab`,`listen_ab`,`create_time`,`create_user`) values ('1835508c-3aac-4688-9856-2bddc5ea9311','040311ba-6023-4a06-ab51-64efbdc50672','sdsdf','其它','熟练','熟练',NULL,NULL);
insert  into `t_resume_language`(`id`,`resume_id`,`content`,`lan_type`,`read_ab`,`listen_ab`,`create_time`,`create_user`) values ('55e699f9-64d4-4e95-8ed4-f6d9fcc1de95','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','123123','英语','良好','良好',NULL,NULL);
insert  into `t_resume_language`(`id`,`resume_id`,`content`,`lan_type`,`read_ab`,`listen_ab`,`create_time`,`create_user`) values ('c0c91eae-18f8-43a2-8ce2-731e2f7cb9b9','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','123123','英语','良好','熟练',NULL,NULL);
insert  into `t_resume_language`(`id`,`resume_id`,`content`,`lan_type`,`read_ab`,`listen_ab`,`create_time`,`create_user`) values ('d2f10b35-4fb9-4755-a28a-ad636d4884cb','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','23','其它','良好','精通',NULL,NULL);
insert  into `t_resume_language`(`id`,`resume_id`,`content`,`lan_type`,`read_ab`,`listen_ab`,`create_time`,`create_user`) values ('db23b0d2-952f-44e7-917e-b6b47d7cd7a7','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','qweqw','英语','良好','良好',NULL,NULL);
insert  into `t_resume_language`(`id`,`resume_id`,`content`,`lan_type`,`read_ab`,`listen_ab`,`create_time`,`create_user`) values ('f00f7a92-96d2-4ce3-b45b-94faff271bdc','040311ba-6023-4a06-ab51-64efbdc50672','确实不错','英语','良好','良好','2015-07-19 17:54:04','1');

/*Table structure for table `t_resume_target` */

DROP TABLE IF EXISTS `t_resume_target`;

CREATE TABLE `t_resume_target` (
  `id` varchar(40) NOT NULL COMMENT '主键',
  `resume_id` varchar(40) NOT NULL COMMENT '简历id',
  `target_type` varchar(100) DEFAULT NULL COMMENT '期望工作性质',
  `target_place` varchar(100) DEFAULT NULL COMMENT '期望工作地点',
  `target_job` varchar(100) DEFAULT NULL COMMENT '期望从事职业',
  `target_industry` varchar(100) DEFAULT NULL COMMENT '期望从事行业',
  `target_pay` varchar(100) DEFAULT NULL COMMENT '期望月薪',
  `work_state` varchar(300) DEFAULT NULL COMMENT '工作状态',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_user` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_resume_target` */

insert  into `t_resume_target`(`id`,`resume_id`,`target_type`,`target_place`,`target_job`,`target_industry`,`target_pay`,`work_state`,`create_time`,`create_user`) values ('1452550d-0558-4cd5-80e4-490bdd625f18','34022055-ab45-4c7f-bbbc-28ca5b6b95c5','全职,兼职,','望京','不限','不限','6001-8000元/月','我对现有工作还算满意，如有更好的工作机会，我也可以考虑。（到岗时间另议）',NULL,NULL);
insert  into `t_resume_target`(`id`,`resume_id`,`target_type`,`target_place`,`target_job`,`target_industry`,`target_pay`,`work_state`,`create_time`,`create_user`) values ('a3581e2b-e7ab-47df-b61e-421b0f76e96b','040311ba-6023-4a06-ab51-64efbdc50672','全职,','海淀区','不限','不限','2001-4000元/月','我对现有工作还算满意，如有更好的工作机会，我也可以考虑。（到岗时间另议）',NULL,NULL);
insert  into `t_resume_target`(`id`,`resume_id`,`target_type`,`target_place`,`target_job`,`target_industry`,`target_pay`,`work_state`,`create_time`,`create_user`) values ('d3984a6e-1acb-4959-9e86-a998dde66d96','c180d5a2-3c2e-4d77-8aa9-a80fdec62f94','全职,兼职,','北京','不限','不限','25000元/月以上','我目前处于离职状态，可立即上岗','2015-08-15 12:05:24','1');
insert  into `t_resume_target`(`id`,`resume_id`,`target_type`,`target_place`,`target_job`,`target_industry`,`target_pay`,`work_state`,`create_time`,`create_user`) values ('db2529f3-7559-4a15-803d-b3f7a511fbaa','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','兼职,实习,','123','123','123','1000-2000元/月','我目前处于离职状态，可立即上岗',NULL,NULL);

/*Table structure for table `t_resume_workhistory` */

DROP TABLE IF EXISTS `t_resume_workhistory`;

CREATE TABLE `t_resume_workhistory` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `resume_id` varchar(40) NOT NULL COMMENT '简历id',
  `content` varchar(500) NOT NULL COMMENT '内容',
  `time_begin` varchar(100) DEFAULT NULL COMMENT '开始日期',
  `time_end` varchar(100) DEFAULT NULL COMMENT '结束日期',
  `company` varchar(100) DEFAULT NULL COMMENT '公司名',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_user` varchar(40) DEFAULT NULL,
  `zhiwei` varchar(100) DEFAULT NULL COMMENT '职位',
  `hangye` varchar(500) DEFAULT NULL COMMENT '行业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作经历';

/*Data for the table `t_resume_workhistory` */

insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`,`zhiwei`,`hangye`) values ('11555052-c7b2-4b3b-9070-d071c845ee5f','34022055-ab45-4c7f-bbbc-28ca5b6b95c5','工作一年了','2015-07-10','2015-07-15','科技公司',NULL,NULL,'',NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`,`zhiwei`,`hangye`) values ('1f14a877-f123-4a27-a110-c0c9403bbc6a','040311ba-6023-4a06-ab51-64efbdc50672','无','2005-07','2005-11','小凌浪','2015-08-16 13:53:52','1','业务员','金融业(投资/保险/证券/银行/基金)');
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`,`zhiwei`,`hangye`) values ('2626139d-2654-4eca-a752-7c37c2de7a75','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','无','2015-08','2015-08','大明科技','2015-09-06 21:30:18','1','经理','房地产开发/建筑与工程');
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`,`zhiwei`,`hangye`) values ('2993fd67-8f55-4cd2-ad63-4bfe90796a25','040311ba-6023-4a06-ab51-64efbdc50672','无','2007-03','2007-07','大媒传奇','2015-08-16 13:53:22','1','总经理','房地产开发/建筑与工程');
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`,`zhiwei`,`hangye`) values ('3573d20e-db59-4a46-9376-9e3cc9767b9b','cb873a3e-c5d4-43a9-92f0-113a6afa89f4','第一，基本情况概述\n\n　　首先要概述工作内容、工作的主客观条件、有利和不利因素以及工作环境等。虽然这些与业绩的取得没有必然的联系，但是，显然如果你处于不利的工作环境，工作条件也甚是恶劣的话，无疑能够使你所取得的业绩大放光彩。比如说，你的工作是处理客户投诉，这一工作的环境显然不是十分优越的，它要求员工能够忍受客户的抱怨，甚至是谩骂、侮辱。环境本身的特殊性实际上是业绩的一部分，因此应该把工作环境陈述出来，以使自己的业绩有所依托。当然，在陈述不利条件和环境时，千万不要理直气壮，不管怎么说，这是工作性质所决定的，接受了这份工作就必须接受它所带来的特殊的工作环境。','2007-07','2015-07','大宝科技','2015-08-16 19:46:23','1','销售','房地产开发/建筑与工程');
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`,`zhiwei`,`hangye`) values ('41dda10a-6ef5-4b74-8cfd-bc08671c1d13','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','123','2015-06-03','2015-06-02',NULL,NULL,NULL,'',NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`,`zhiwei`,`hangye`) values ('52f96538-a45f-48e4-910a-88b8619ec8b5','040311ba-6023-4a06-ab51-64efbdc50672','无','2010-03','2015-07','环球科技','2015-08-16 13:52:26','1','营销总监','金融业(投资/保险/证券/银行/基金)');
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`,`zhiwei`,`hangye`) values ('7753c50b-747a-4c85-97a6-d1803f6c0ffa','c180d5a2-3c2e-4d77-8aa9-a80fdec62f94','123','2015-08-28','2015-08-20','123','2015-08-15 12:09:30','1',NULL,NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`,`zhiwei`,`hangye`) values ('a208dbdb-208f-4516-9240-a85d6231ee24','38b8c57e-276d-4ba3-b02f-1a3ad09488c6','胜多负少','2015-06-09','2015-06-25','史蒂芬森地方',NULL,NULL,'',NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`,`zhiwei`,`hangye`) values ('b62d0b4b-d5f7-4a14-a688-3ce6c51e15af','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','123123','2015-06-17','2015-06-11',NULL,NULL,NULL,'',NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`,`zhiwei`,`hangye`) values ('c08d0910-f197-4cd2-be05-bc3c04fb64b6','c180d5a2-3c2e-4d77-8aa9-a80fdec62f94','12312','2015-08-20','2015-08-05','123','2015-08-15 12:10:07','1',NULL,NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`,`zhiwei`,`hangye`) values ('cc1ef021-c054-4280-8c5e-9f45b50eb2a4','34022055-ab45-4c7f-bbbc-28ca5b6b95c5','工作一年了','2015-07-10','2015-07-15','科技公司',NULL,NULL,'',NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`,`zhiwei`,`hangye`) values ('ecbdb477-c26f-450a-a43e-3ba7a1efca50','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','无','2008-08','2015-08','卢安群实业','2015-09-06 21:30:47','1','财务',NULL);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(40) NOT NULL COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `sex` varchar(10) NOT NULL COMMENT '性别',
  `duty` varchar(40) NOT NULL COMMENT '职务',
  `entry_date` date DEFAULT NULL COMMENT '入职日期',
  `state` varchar(20) DEFAULT NULL COMMENT '状态-在职、离职、试用期',
  `id_card` varchar(40) DEFAULT NULL COMMENT '身份证号',
  `np_place` varchar(200) DEFAULT NULL COMMENT '籍贯',
  `phone` varchar(100) DEFAULT NULL COMMENT '电话',
  `edu_school` varchar(200) DEFAULT NULL COMMENT '毕业院校',
  `edu_date` date DEFAULT NULL COMMENT '毕业时间',
  `department` varchar(100) DEFAULT NULL COMMENT '所属部门',
  `team` varchar(40) DEFAULT NULL COMMENT '隶属团队',
  `user_type` varchar(40) NOT NULL COMMENT '用户类型-管理员、顾问、助理',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `msn` varchar(100) DEFAULT NULL COMMENT 'qq或者微信',
  `education` varchar(100) DEFAULT NULL COMMENT '学历',
  `professional` varchar(200) DEFAULT NULL COMMENT '专业',
  `positive_date` date DEFAULT NULL COMMENT '转正日期',
  `leave_date` date DEFAULT NULL COMMENT '离职日期',
  `skills` varchar(200) DEFAULT NULL COMMENT '擅长专业',
  `user_create` varchar(40) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(40) DEFAULT NULL COMMENT '更新人',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次更新时间',
  `is_disabled` varchar(1) NOT NULL DEFAULT '1' COMMENT '是否可用:1-可用，0-不可用',
  `icon` varchar(100) DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_USER_NAME` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`,`name`,`sex`,`duty`,`entry_date`,`state`,`id_card`,`np_place`,`phone`,`edu_school`,`edu_date`,`department`,`team`,`user_type`,`email`,`msn`,`education`,`professional`,`positive_date`,`leave_date`,`skills`,`user_create`,`create_time`,`last_update_user`,`last_update_time`,`is_disabled`,`icon`) values ('1','cm_lihua','e31773dab88ebdbe2db9c6b056d00438','李华','1','合作人','2015-06-02','在职','410182198811113311','','12312','',NULL,'管理中心','','管理员','','','','',NULL,NULL,'','','2015-08-18 01:26:43','1','2015-08-16 15:17:28','1','79da6e94-b4e2-4d37-bcb7-ec5743bfc488');
insert  into `t_user`(`id`,`username`,`password`,`name`,`sex`,`duty`,`entry_date`,`state`,`id_card`,`np_place`,`phone`,`edu_school`,`edu_date`,`department`,`team`,`user_type`,`email`,`msn`,`education`,`professional`,`positive_date`,`leave_date`,`skills`,`user_create`,`create_time`,`last_update_user`,`last_update_time`,`is_disabled`,`icon`) values ('2be301f4-a9d6-4acd-9866-ee92788dc908','xiaobingbing','1eaf60bd1d9d4ffdc335a1207b66e052','小兵','1','资深顾问','2015-07-09','试用期',NULL,NULL,'123123123123',NULL,NULL,'猎头部','6bdb5752-2310-4680-b77e-3d314dead3ef','助理',NULL,NULL,NULL,NULL,NULL,NULL,'房地产开发/建筑与工程','1','2015-08-15 11:50:52','1','2015-08-16 15:20:26','1',NULL);
insert  into `t_user`(`id`,`username`,`password`,`name`,`sex`,`duty`,`entry_date`,`state`,`id_card`,`np_place`,`phone`,`edu_school`,`edu_date`,`department`,`team`,`user_type`,`email`,`msn`,`education`,`professional`,`positive_date`,`leave_date`,`skills`,`user_create`,`create_time`,`last_update_user`,`last_update_time`,`is_disabled`,`icon`) values ('6bdb5752-2310-4680-b77e-3d314dead3ef','test_guwen','1eaf60bd1d9d4ffdc335a1207b66e052','测试顾问','2','猎头顾问','2008-07-11','在职',NULL,NULL,'11111',NULL,NULL,NULL,'1','顾问',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','2015-08-16 16:36:03','1','2015-08-16 15:24:52','1',NULL);
insert  into `t_user`(`id`,`username`,`password`,`name`,`sex`,`duty`,`entry_date`,`state`,`id_card`,`np_place`,`phone`,`edu_school`,`edu_date`,`department`,`team`,`user_type`,`email`,`msn`,`education`,`professional`,`positive_date`,`leave_date`,`skills`,`user_create`,`create_time`,`last_update_user`,`last_update_time`,`is_disabled`,`icon`) values ('a52649ff-85aa-453b-9ad0-ee8a094ced69','test_zhuli','1eaf60bd1d9d4ffdc335a1207b66e052','测试助理','1','猎头顾问','2013-07-19','离职',NULL,NULL,'111',NULL,NULL,NULL,'6bdb5752-2310-4680-b77e-3d314dead3ef','助理',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','2015-07-25 17:15:05','1','2015-08-16 15:25:24','1',NULL);

/* Function  structure for function  `func_get_split_string` */

/*!50003 DROP FUNCTION IF EXISTS `func_get_split_string` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `func_get_split_string`(  
f_string varchar(1000),f_delimiter varchar(5),f_order int) RETURNS varchar(255) CHARSET utf8
BEGIN  
  declare result varchar(255) default '';  
  set result = reverse(substring_index(reverse(substring_index(f_string,f_delimiter,f_order)),f_delimiter,1));  
  return result;  
END */$$
DELIMITER ;

/* Function  structure for function  `func_get_split_string_total` */

/*!50003 DROP FUNCTION IF EXISTS `func_get_split_string_total` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `func_get_split_string_total`(  
f_string VARCHAR(1000),f_delimiter VARCHAR(5)  
) RETURNS int(11)
BEGIN  
  DECLARE returnInt INT(11);  
  IF LENGTH(f_delimiter)=2  THEN  
     RETURN 1+(LENGTH(f_string) - LENGTH(REPLACE(f_string,f_delimiter,'')))/2;  
  ELSE      
     RETURN 1+(LENGTH(f_string) - LENGTH(REPLACE(f_string,f_delimiter,'')));  
  END IF;  
END */$$
DELIMITER ;

/* Function  structure for function  `getDictName` */

/*!50003 DROP FUNCTION IF EXISTS `getDictName` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `getDictName`(v_str VARCHAR(100)) RETURNS varchar(100) CHARSET utf8
BEGIN  
            
          DECLARE i INT(4);  
          DECLARE dictCode VARCHAR(100);  
          DECLARE dictName VARCHAR(100);  
          DECLARE returnStr VARCHAR(100);   
          
          SET i = 1;  
          SET returnStr = '';        
            
          IF(v_str IS NULL OR LENGTH(v_str)=0) THEN  
               RETURN returnStr;  
          ELSE  
            
          WHILE i<=func_get_split_string_total(v_str,',') 
          DO  
               SET dictCode = func_get_split_string(v_str,',',i);  
            
               SELECT NAME INTO dictName FROM t_user WHERE id = dictCode;   
            
               SET returnStr = CONCAT(returnStr,',',dictName);  -- 这里要用中文的逗号，否则导出EXCEL的时候会串行，因为程序中是以逗号分隔的  
          SET i = i+1;  
          END WHILE;  
             
          SET returnStr = SUBSTRING(returnStr,2);            
          RETURN returnStr;  
             
          END IF;  
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
