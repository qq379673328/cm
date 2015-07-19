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

insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('07dc1213-f6d8-4494-90ef-d9fae90e30ac',NULL,'2015-07-19\\07dc1213-f6d8-4494-90ef-d9fae90e30ac.docx','系统问题20150716.docx','2015-07-19 17:48:39','docx','0',681204);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('206494eb-22a4-4e98-bbfc-fd35e751902d',NULL,'2015-07-19\\206494eb-22a4-4e98-bbfc-fd35e751902d.txt','宽带密码.txt','2015-07-19 17:42:01','txt','0',74);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('34187bbb-7adc-44fa-bb3d-9feed7364c43',NULL,'2015-07-19\\34187bbb-7adc-44fa-bb3d-9feed7364c43.bat','启动数据库.bat','2015-07-19 17:54:12','bat','0',81);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('3b45cb0d-5f70-4639-add4-fbf2038cbb9f',NULL,'2015-07-19\\3b45cb0d-5f70-4639-add4-fbf2038cbb9f.txt','宽带密码.txt','2015-07-19 17:49:42','txt','0',74);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('9afc3d62-130f-448b-b8b6-45990dd2e463',NULL,'2015-07-19\\9afc3d62-130f-448b-b8b6-45990dd2e463.bat','数据库控制台.bat','2015-07-19 17:46:22','bat','0',43);
insert  into `t_attachment`(`id`,`module`,`path`,`name`,`upload_time`,`type`,`is_use`,`size`) values ('e603d212-7aed-4cc5-93e8-7605301d14ff',NULL,'2015-07-19\\e603d212-7aed-4cc5-93e8-7605301d14ff.txt','宽带密码.txt','2015-07-19 17:46:36','txt','0',74);

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同表';

/*Data for the table `t_contract` */

insert  into `t_contract`(`id`,`custom_id`,`no`,`state`,`in_date`,`in_percentage`,`payway`,`first_pay`,`use_limit`,`other_require`,`create_time`,`create_user`,`update_time`,`update_user`) values ('5c7e6f7e-4bf0-4bea-988f-5d4683718a6b','2bd09171-6547-4e30-b16d-d6bc7f13bfea','2015041','运作','2015-07-18','10%','现金','20','一年','无','2015-07-18 17:44:34','1','2015-07-19 17:49:44','1');

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
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('1437d08e-90e4-48f7-8685-79f0a6396491','adasdasd','金融业(投资/保险/证券/银行/基金)','1','广告呼入','潜在客户','asdasd','asd','12','123','31','123','123','123','123','123','123123','1','2015-07-01 22:23:10','1','2015-07-02 02:33:58');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('2bd09171-6547-4e30-b16d-d6bc7f13bfea','渤海证券','金融业(投资/保险/证券/银行/基金)','1','人脉推荐','签约运作','张力','经理','0371-0909876','13459888765','0371-0909876','123@test.com','332244422','332244422','北京市海淀区','www.baidu.com','新成立公司','1','2015-07-02 02:05:34','1','2015-07-19 17:48:41');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('38a0eacd-6b79-4011-bda9-29aae5a97357','sfdfsdf','金融业(投资/保险/证券/银行/基金)','3,4','主动BD','潜在客户','sdf','asdf','asdf','123123','123',NULL,'123','123','123','123','sdf123123','3','2015-06-15 21:30:03','1','2015-06-15 22:58:58');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('4c95efa9-4798-487a-ae36-44e5a3d5fa16','123','金融业(投资/保险/证券/银行/基金)','5,3,2,1,6,4','广告呼入','签约暂停','123','123','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'4','2015-06-15 21:30:03','1','2015-06-15 21:03:54');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('63966dad-e3e5-4815-b8be-b39d2ecdcae9','客户A','金融业(投资/保险/证券/银行/基金)','1','广告呼入','签约运作','123','123','','','sdf','sdf','sdf','dsf','sdf','sdf','sdfsd','1','2015-06-18 20:10:49','1','2015-07-02 02:33:48');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('6a56e4fa-1244-439c-8414-11ad4d946d25','测试客户','房地产开发/建筑与工程','3,6,4','机构合作','签约暂停','张三','测试人员','1234123123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'12312312','1','2015-06-14 10:53:50','1','2015-06-24 23:24:11');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('762dcab8-07e1-4c8e-9945-ebdb941734a0','史蒂芬森地方','金融业(投资/保险/证券/银行/基金)','5,4','人脉推荐','签约暂停','123','123','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2','2015-06-15 21:30:04','1','2015-06-15 21:04:39');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('abc6c0cb-1c1a-49fd-9567-e5ac38c00297','什么玩意啊','金融业(投资/保险/证券/银行/基金)','2,3,4','机构合作','潜在客户','12','123','12','123','123','123@11.com','123','123','123','123','wqeqwe','2','2015-06-15 21:30:05','1','2015-06-22 10:57:59');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('bfa0022c-18c7-4538-b7e8-71f30d1751d9','12','金融业(投资/保险/证券/银行/基金)','123','广告呼入','签约暂停','1231','123','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3','2015-06-15 21:30:05','1','2015-06-14 12:43:42');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('c9973244-d12d-4582-9604-d2596090fafe','123飒飒东风','金融业(投资/保险/证券/银行/基金)','123','机构合作','签约客户','123','123','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'4','2015-06-15 21:30:06','1','2015-06-15 21:04:03');
insert  into `t_custom`(`id`,`custom_name`,`industry`,`team`,`source`,`state`,`contact_name`,`contact_duty`,`contact_landline`,`contact_cellphone`,`contact_fax`,`contact_email`,`contact_qq`,`contact_weixin`,`contact_address`,`contact_website`,`company_profile`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('dbefd624-731b-4d83-a693-3dc31042031b','大客户','金融业(投资/保险/证券/银行/基金)','4,5,6,1','人脉推荐','签约暂停','sadf','sdf','sdf','123','123','d123','123','123','123','123','12312','2','2015-06-15 21:30:09','1','2015-06-15 23:17:49');

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
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('92e65acb-47d0-4c7e-94cc-23bf5a766b9b','1437d08e-90e4-48f7-8685-79f0a6396491',NULL,'sdfasdfasdfasdf','2015-07-01 23:04:48','1');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('947ab42f-5017-4faa-8531-0e47c20987a4','63966dad-e3e5-4815-b8be-b39d2ecdcae9',NULL,'e1312','2015-06-21 21:43:29','');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('97409fd3-ab40-42f2-96b0-2c6713681333','38a0eacd-6b79-4011-bda9-29aae5a97357',NULL,'asdfsdaf','2015-06-15 21:02:54','');
insert  into `t_custom_communication`(`id`,`custom_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('a1a08296-6e58-4fd7-8a03-aec52ed397c5','1437d08e-90e4-48f7-8685-79f0a6396491',NULL,'11','2015-07-01 23:17:21','1');
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
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('3455ae52-3172-4ac0-9f12-1bdbad624b1c','1','1437d08e-90e4-48f7-8685-79f0a6396491');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('383d5263-2ece-450b-9df0-5f84b298af7f','3','abc6c0cb-1c1a-49fd-9567-e5ac38c00297');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('3dacf5b5-7c16-4be8-97f5-fb99f597d8ab','4','abc6c0cb-1c1a-49fd-9567-e5ac38c00297');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('45e21f79-f7a0-4e6b-8be0-0ac1d392e47f','6','dbefd624-731b-4d83-a693-3dc31042031b');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('4bafcdcd-8c63-4a9e-b783-f149f81d5b88','4','dbefd624-731b-4d83-a693-3dc31042031b');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('58f6ff9b-8d59-4a66-905f-f26c5a5abde4','4','1');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('6e4752e5-1642-49cf-957a-f8e3b60a666d','5','dbefd624-731b-4d83-a693-3dc31042031b');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('72ac1b7c-622e-45bf-af0a-610fd1808006','1','63966dad-e3e5-4815-b8be-b39d2ecdcae9');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('734335ff-b1c1-40a6-b1e5-700de5f03cb6','3','6a56e4fa-1244-439c-8414-11ad4d946d25');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('82b03cee-ae8c-4b63-8f84-e9937679bd86','4','38a0eacd-6b79-4011-bda9-29aae5a97357');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('83fa750f-77ca-47bc-882c-206dee1221e0','6','0c3162f7-069f-46b6-b54c-621de7801623');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('895925ce-ee48-476e-9e26-37eeae16b226','1','2bd09171-6547-4e30-b16d-d6bc7f13bfea');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('9ceaeb62-8250-42b0-9c4e-a4032b18f84d','2','abc6c0cb-1c1a-49fd-9567-e5ac38c00297');
insert  into `t_custom_team`(`id`,`user_id`,`custom_id`) values ('ac208f55-6649-43a1-8487-d49accba9fd9','1','dbefd624-731b-4d83-a693-3dc31042031b');
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
  `check_user` varchar(100) NOT NULL COMMENT '开票人',
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

insert  into `t_invoice`(`id`,`apply_user`,`apply_time`,`custom_contract`,`in_user`,`type`,`property`,`total`,`state`,`check_user`,`income_state`,`comment`,`create_user`,`create_time`,`custom_id`,`contract_no`,`resume_id`,`resume_desc`) values ('69fbe1a2-a2e0-409e-b1cb-d5e374912e8a','1','2015-06-18','渤海证券 - 123443',NULL,'专用发票','服务费',12,'已开出','1','已到账','213','1','2015-06-24 22:46:51','2bd09171-6547-4e30-b16d-d6bc7f13bfea','123443','34022055-ab45-4c7f-bbbc-28ca5b6b95c5','李丁腌 - 管理人员');

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

insert  into `t_job`(`id`,`custom_id`,`contract_id`,`name`,`pay_min`,`pay_max`,`state`,`team`,`workplace`,`job_type`,`industry`,`work_year`,`require_people`,`department`,`report_obj`,`age_min`,`age_max`,`sex_limit`,`edu_limit`,`language_limit`,`job_desc`,`company_desc`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('2e92ce3b-3a05-41c2-a58b-0061de11a92c','63966dad-e3e5-4815-b8be-b39d2ecdcae9','19a34bdb-7386-4a03-8fd3-069e819467d8','职位','1','2','运作','3,4','1','1','1','1',1,'1','1',1,1,'不限','硕士以上','1','1','123123','1','2015-06-18 21:12:41','1','2015-07-18 12:10:32');
insert  into `t_job`(`id`,`custom_id`,`contract_id`,`name`,`pay_min`,`pay_max`,`state`,`team`,`workplace`,`job_type`,`industry`,`work_year`,`require_people`,`department`,`report_obj`,`age_min`,`age_max`,`sex_limit`,`edu_limit`,`language_limit`,`job_desc`,`company_desc`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('32013d37-a495-4741-a52e-c9cd50032356','6a56e4fa-1244-439c-8414-11ad4d946d25','c4af03c8-8575-4553-a4c3-4e66c1aeacd2','测试职位','11','12','运作','2,3','测试','测试','测试','12',1,'1','1',1,1,'不限','硕士以上',NULL,'1','测试','1','2015-06-18 21:59:58','1','2015-06-28 10:26:07');
insert  into `t_job`(`id`,`custom_id`,`contract_id`,`name`,`pay_min`,`pay_max`,`state`,`team`,`workplace`,`job_type`,`industry`,`work_year`,`require_people`,`department`,`report_obj`,`age_min`,`age_max`,`sex_limit`,`edu_limit`,`language_limit`,`job_desc`,`company_desc`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('421038fd-c415-49d5-83c8-a4ac90749c5a','2bd09171-6547-4e30-b16d-d6bc7f13bfea','372a6e8b-d644-4642-9610-edf0294f86a3','证券交易师','1','50','运作','1','北京','业务员','证券','3',2,'市场部','无',25,40,'男','本科以上','无','市场业务员','大力发展中','1','2015-07-12 12:08:41','1','2015-07-19 17:52:01');
insert  into `t_job`(`id`,`custom_id`,`contract_id`,`name`,`pay_min`,`pay_max`,`state`,`team`,`workplace`,`job_type`,`industry`,`work_year`,`require_people`,`department`,`report_obj`,`age_min`,`age_max`,`sex_limit`,`edu_limit`,`language_limit`,`job_desc`,`company_desc`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('6c3add2f-01bc-4879-b720-81526ad0a5ef','dbefd624-731b-4d83-a693-3dc31042031b','19a34bdb-7386-4a03-8fd3-069e819467d8','测试1','1','1','运作','3,4','1','1','1','1',1,'1','1',1,1,'不限','硕士以上','1','1','MQ==','1','2015-06-18 21:16:56','1','2015-06-28 10:26:20');
insert  into `t_job`(`id`,`custom_id`,`contract_id`,`name`,`pay_min`,`pay_max`,`state`,`team`,`workplace`,`job_type`,`industry`,`work_year`,`require_people`,`department`,`report_obj`,`age_min`,`age_max`,`sex_limit`,`edu_limit`,`language_limit`,`job_desc`,`company_desc`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('79694b87-fadb-4f6d-9bea-54a00bcfd9bf','dbefd624-731b-4d83-a693-3dc31042031b','d9e6b2d1-785c-4dd3-8ce9-1971f66e8e53','管理人员','10','20','运作','5,6','北京望京','管理','互联网','3',3,'一部','老被',22,40,'不限','博士以上','英语','无特殊要求','好东西啊','1','2015-06-22 11:00:34','1','2015-06-22 11:49:52');
insert  into `t_job`(`id`,`custom_id`,`contract_id`,`name`,`pay_min`,`pay_max`,`state`,`team`,`workplace`,`job_type`,`industry`,`work_year`,`require_people`,`department`,`report_obj`,`age_min`,`age_max`,`sex_limit`,`edu_limit`,`language_limit`,`job_desc`,`company_desc`,`create_user`,`create_time`,`last_update_user`,`last_update_time`) values ('828272d7-3ece-4de1-b159-99290d12dcc5','2bd09171-6547-4e30-b16d-d6bc7f13bfea','5c7e6f7e-4bf0-4bea-988f-5d4683718a6b','财务','1','2','运作','a52649ff-85aa-453b-9ad0-ee8a094ced69','1','1','1','1',1,'1','1',1,1,'不限','本科以上','1','1','11','1','2015-07-18 18:46:06','1','2015-07-19 16:07:38');

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
insert  into `t_job_communication`(`id`,`job_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('5f3071ca-3e12-426c-8b4d-fbef8c92b836','2e92ce3b-3a05-41c2-a58b-0061de11a92c',NULL,'客户反应还可以','2015-07-18 11:53:50','1');
insert  into `t_job_communication`(`id`,`job_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('af0904d0-9868-4540-bbea-c64336a492f2','421038fd-c415-49d5-83c8-a4ac90749c5a',NULL,'123','2015-07-19 17:22:38','1');
insert  into `t_job_communication`(`id`,`job_id`,`commu_date`,`content`,`create_time`,`create_user`) values ('ba12880c-ba3d-419b-b30b-aba7875fa4d0','421038fd-c415-49d5-83c8-a4ac90749c5a',NULL,NULL,'2015-07-19 17:23:55','1');

/*Table structure for table `t_job_team` */

DROP TABLE IF EXISTS `t_job_team`;

CREATE TABLE `t_job_team` (
  `id` varchar(40) NOT NULL COMMENT 'id',
  `user_id` varchar(40) NOT NULL COMMENT '团队id',
  `job_id` varchar(40) NOT NULL COMMENT '职位id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位执行团队';

/*Data for the table `t_job_team` */

insert  into `t_job_team`(`id`,`user_id`,`job_id`) values ('85813b66-4288-475d-8e57-5cd2f4c2516f','3','2e92ce3b-3a05-41c2-a58b-0061de11a92c');
insert  into `t_job_team`(`id`,`user_id`,`job_id`) values ('92d71205-f4d0-4fe8-a270-855ac8e7e9dd','a52649ff-85aa-453b-9ad0-ee8a094ced69','828272d7-3ece-4de1-b159-99290d12dcc5');
insert  into `t_job_team`(`id`,`user_id`,`job_id`) values ('c831346b-1f7d-4fec-b4ca-4be489769f9b','1','421038fd-c415-49d5-83c8-a4ac90749c5a');
insert  into `t_job_team`(`id`,`user_id`,`job_id`) values ('d5408e03-e698-4588-99a4-b2a8c18faa2b','4','2e92ce3b-3a05-41c2-a58b-0061de11a92c');

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
insert  into `t_resume`(`id`,`name`,`address`,`sex`,`education`,`birthcay`,`marrage`,`phone`,`email`,`work_state`,`resumt_desc`,`head_image`,`create_user`,`create_time`,`last_update_time`,`last_update_user`,`year_pay`,`city`,`industry`,`duty`) values ('34022055-ab45-4c7f-bbbc-28ca5b6b95c5','李丁腌','西四环大陆','男','硕士以上','1977-07-08','已婚','13262323112312','test@test.com','在职','暂无个人简介',NULL,'1','2015-07-04 16:09:23','2015-07-19 11:36:17','1','12',NULL,NULL,'财务/审计/统计类');
insert  into `t_resume`(`id`,`name`,`address`,`sex`,`education`,`birthcay`,`marrage`,`phone`,`email`,`work_state`,`resumt_desc`,`head_image`,`create_user`,`create_time`,`last_update_time`,`last_update_user`,`year_pay`,`city`,`industry`,`duty`) values ('6b9b0c9c-958c-4668-91d1-43e1cc188cbc','张晓军','123','男','本科以上','1950-07-10','未婚','12','123','离职','123123',NULL,'1','2015-07-02 01:24:23','2015-07-19 11:36:30','1','1',NULL,NULL,NULL);

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
insert  into `t_resume_edu`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`org`,`course`,`create_time`,`create_user`) values ('351d6342-941c-489a-83f3-9e9a9d86bcf1','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','32','2015-06-04','2015-06-02','1','2',NULL,NULL);
insert  into `t_resume_edu`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`org`,`course`,`create_time`,`create_user`) values ('4b523738-715f-4933-8507-ac802cf28647','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','12312312','2015-06-13','2015-06-03','123','123',NULL,NULL);
insert  into `t_resume_edu`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`org`,`course`,`create_time`,`create_user`) values ('6584013c-dbb5-4855-95c6-8db48d8a0c03','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','123123','2015-07-16','2015-07-24','123','12312',NULL,NULL);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='简历职位关联表';

/*Data for the table `t_resume_job` */

insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`) values ('1cbe5813-90f6-476b-a8cd-181447769a20','040311ba-6023-4a06-ab51-64efbdc50672','421038fd-c415-49d5-83c8-a4ac90749c5a','审核中','已推荐',NULL,'1','2015-07-19 11:53:41',NULL,'1',NULL);
insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`) values ('7e30acc6-73ef-4b26-bd74-8cc40fae9cbb','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','79694b87-fadb-4f6d-9bea-54a00bcfd9bf','审核中','已推荐',NULL,'1','2015-07-19 11:53:52',NULL,'1',NULL);
insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`) values ('849c3068-4742-4b4d-8808-58bec110906a','34022055-ab45-4c7f-bbbc-28ca5b6b95c5','79694b87-fadb-4f6d-9bea-54a00bcfd9bf','审核中','已推荐',NULL,'1','2015-07-19 11:53:46',NULL,'1',NULL);
insert  into `t_resume_job`(`id`,`resume_id`,`job_id`,`verify_state`,`recom_state`,`create_time`,`create_user`,`recom_time`,`verify_time`,`recom_user`,`verify_user`) values ('cd6565f8-835c-4e9d-a113-65ae6f7795cb','040311ba-6023-4a06-ab51-64efbdc50672','828272d7-3ece-4de1-b159-99290d12dcc5','复试','已推荐',NULL,'1','2015-07-19 16:05:13','2015-07-19 16:05:17','1','1');

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
insert  into `t_resume_job_comm`(`id`,`create_date`,`create_user`,`content`,`resumejob_id`) values ('37a03b6c-3dee-46e0-897b-2bdd088309fe','2015-07-19 16:06:33','1','还可以','cd6565f8-835c-4e9d-a113-65ae6f7795cb');
insert  into `t_resume_job_comm`(`id`,`create_date`,`create_user`,`content`,`resumejob_id`) values ('5d25bf8b-8693-4bad-afbc-07a600a0808e','2015-07-19 16:06:45','1','确定录用了，评价不错还','cd6565f8-835c-4e9d-a113-65ae6f7795cb');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作经历';

/*Data for the table `t_resume_workhistory` */

insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`) values ('11555052-c7b2-4b3b-9070-d071c845ee5f','34022055-ab45-4c7f-bbbc-28ca5b6b95c5','工作一年了','2015-07-10','2015-07-15','科技公司',NULL,NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`) values ('13e8e894-d2bb-4ba2-8128-03ef47291d45','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','123','2015-07-02','2015-07-15','123123',NULL,NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`) values ('41dda10a-6ef5-4b74-8cfd-bc08671c1d13','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','123','2015-06-03','2015-06-02',NULL,NULL,NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`) values ('7339322b-63f7-4fd5-9eb2-95bd66e291e1','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','123','2015-07-02','2015-07-08','12312',NULL,NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`) values ('91c69b2c-b1f9-40dd-bf28-a13fadea360f','040311ba-6023-4a06-ab51-64efbdc50672','不限','2015-07-03','2015-07-12','科技股份有限公司',NULL,NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`) values ('a208dbdb-208f-4516-9240-a85d6231ee24','38b8c57e-276d-4ba3-b02f-1a3ad09488c6','胜多负少','2015-06-09','2015-06-25','史蒂芬森地方',NULL,NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`) values ('a319800b-af7c-4856-8588-da5ce3500bd1','040311ba-6023-4a06-ab51-64efbdc50672','渤海证券','2015-07-07','2015-07-25','渤海证券','2015-07-19 17:53:19','1');
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`) values ('b62d0b4b-d5f7-4a14-a688-3ce6c51e15af','0e2842f8-abf1-4bd1-876a-450fa26dfcc7','123123','2015-06-17','2015-06-11',NULL,NULL,NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`) values ('bd5743f7-1516-47bb-bec7-305ad7e505ad','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','大范甘迪法国','2015-07-02','2015-07-01','士大夫但是',NULL,NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`) values ('bde46133-7ade-413a-9f37-321401309795','6b9b0c9c-958c-4668-91d1-43e1cc188cbc','12312','2015-07-08','2015-07-07','dsfsd',NULL,NULL);
insert  into `t_resume_workhistory`(`id`,`resume_id`,`content`,`time_begin`,`time_end`,`company`,`create_time`,`create_user`) values ('cc1ef021-c054-4280-8c5e-9f45b50eb2a4','34022055-ab45-4c7f-bbbc-28ca5b6b95c5','工作一年了','2015-07-10','2015-07-15','科技公司',NULL,NULL);

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_USER_NAME` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`,`name`,`sex`,`duty`,`entry_date`,`state`,`id_card`,`np_place`,`phone`,`edu_school`,`edu_date`,`department`,`team`,`user_type`,`email`,`msn`,`education`,`professional`,`positive_date`,`leave_date`,`skills`,`user_create`,`create_time`,`last_update_user`,`last_update_time`,`is_disabled`) values ('1','admin','1eaf60bd1d9d4ffdc335a1207b66e052','管理员','1','管理员','2015-06-02','在职','410182198811113311','','12312','',NULL,'管理员','','管理员','','','','',NULL,NULL,'','','2015-07-19 12:09:23','1','2015-07-19 14:44:38','1');
insert  into `t_user`(`id`,`username`,`password`,`name`,`sex`,`duty`,`entry_date`,`state`,`id_card`,`np_place`,`phone`,`edu_school`,`edu_date`,`department`,`team`,`user_type`,`email`,`msn`,`education`,`professional`,`positive_date`,`leave_date`,`skills`,`user_create`,`create_time`,`last_update_user`,`last_update_time`,`is_disabled`) values ('6bdb5752-2310-4680-b77e-3d314dead3ef','test_guwen','1eaf60bd1d9d4ffdc335a1207b66e052','测试顾问','2','顾问','2015-07-15','在职',NULL,NULL,'11111',NULL,NULL,NULL,'1','顾问',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','2015-07-19 15:09:59','1','2015-07-19 17:55:14','1');
insert  into `t_user`(`id`,`username`,`password`,`name`,`sex`,`duty`,`entry_date`,`state`,`id_card`,`np_place`,`phone`,`edu_school`,`edu_date`,`department`,`team`,`user_type`,`email`,`msn`,`education`,`professional`,`positive_date`,`leave_date`,`skills`,`user_create`,`create_time`,`last_update_user`,`last_update_time`,`is_disabled`) values ('a52649ff-85aa-453b-9ad0-ee8a094ced69','test_zhuli','1eaf60bd1d9d4ffdc335a1207b66e052','测试助理','1','助理','2015-07-14','在职',NULL,NULL,'111',NULL,NULL,NULL,'6bdb5752-2310-4680-b77e-3d314dead3ef','助理',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','2015-07-19 15:08:12','1','2015-07-19 15:10:57','1');

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
