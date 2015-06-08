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
  `module` varchar(100) NOT NULL COMMENT '附件所属模块',
  `path` varchar(200) NOT NULL COMMENT '附件路径',
  `name` varchar(200) NOT NULL COMMENT '附件名称',
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '附件上传时间',
  `type` varchar(100) NOT NULL COMMENT '附件类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

/*Data for the table `t_attachment` */

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同表';

/*Data for the table `t_contract` */

/*Table structure for table `t_contract_data` */

DROP TABLE IF EXISTS `t_contract_data`;

CREATE TABLE `t_contract_data` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `contract_id` varchar(40) NOT NULL COMMENT '合同表id',
  `attachment_id` varchar(40) NOT NULL COMMENT '附件表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同附件表';

/*Data for the table `t_contract_data` */

/*Table structure for table `t_custom` */

DROP TABLE IF EXISTS `t_custom`;

CREATE TABLE `t_custom` (
  `id` varchar(40) NOT NULL COMMENT '主键',
  `custom_name` varchar(100) NOT NULL COMMENT '客户名称',
  `industry` varchar(200) DEFAULT NULL COMMENT '所属行业',
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
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(40) DEFAULT NULL COMMENT '上次更新人',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';

/*Data for the table `t_custom` */

/*Table structure for table `t_custom_communication` */

DROP TABLE IF EXISTS `t_custom_communication`;

CREATE TABLE `t_custom_communication` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `custom_id` varchar(40) NOT NULL COMMENT '客户id',
  `commu_date` varchar(100) NOT NULL COMMENT '沟通时间',
  `content` varchar(500) NOT NULL COMMENT '沟通内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户沟通记录表';

/*Data for the table `t_custom_communication` */

/*Table structure for table `t_custom_data` */

DROP TABLE IF EXISTS `t_custom_data`;

CREATE TABLE `t_custom_data` (
  `id` varchar(40) NOT NULL COMMENT '资料id',
  `custom_id` varchar(40) NOT NULL COMMENT '客户id',
  `attachment_id` varchar(40) NOT NULL COMMENT '附件id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户资料表';

/*Data for the table `t_custom_data` */

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
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发票表';

/*Data for the table `t_invoice` */

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
  `company_desc` blob COMMENT '公司介绍',
  `create_user` varchar(40) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(40) DEFAULT NULL COMMENT '上次更新人',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位表';

/*Data for the table `t_job` */

/*Table structure for table `t_pub` */

DROP TABLE IF EXISTS `t_pub`;

CREATE TABLE `t_pub` (
  `id` varchar(40) NOT NULL COMMENT '公共id',
  `content` varchar(500) NOT NULL COMMENT '公告内容',
  `create_user` varchar(40) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `state` varchar(40) NOT NULL COMMENT '公告状态',
  `last_update_user` varchar(40) DEFAULT NULL COMMENT '更新人',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告管理表';

/*Data for the table `t_pub` */

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
  `current_wage` varchar(50) DEFAULT NULL COMMENT '目前年薪',
  `work_state` varchar(50) DEFAULT NULL COMMENT '工作状态',
  `desc` blob COMMENT '个人简介',
  `head_image` varchar(100) DEFAULT NULL COMMENT '头像路径',
  `create_user` varchar(40) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次更新时间',
  `last_update_user` varchar(40) DEFAULT NULL COMMENT '上次更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='简历表';

/*Data for the table `t_resume` */

/*Table structure for table `t_resume_date` */

DROP TABLE IF EXISTS `t_resume_date`;

CREATE TABLE `t_resume_date` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `resume_id` varchar(40) NOT NULL COMMENT '简历id',
  `attachment_id` varchar(40) NOT NULL COMMENT '附件id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='简历附件';

/*Data for the table `t_resume_date` */

/*Table structure for table `t_resume_edu` */

DROP TABLE IF EXISTS `t_resume_edu`;

CREATE TABLE `t_resume_edu` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `resume_id` varchar(40) NOT NULL COMMENT '简历id',
  `content` blob NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教育培训经历';

/*Data for the table `t_resume_edu` */

/*Table structure for table `t_resume_language` */

DROP TABLE IF EXISTS `t_resume_language`;

CREATE TABLE `t_resume_language` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `resume_id` varchar(40) NOT NULL COMMENT '简历id',
  `content` blob NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='语言能力';

/*Data for the table `t_resume_language` */

/*Table structure for table `t_resume_workhistory` */

DROP TABLE IF EXISTS `t_resume_workhistory`;

CREATE TABLE `t_resume_workhistory` (
  `id` varchar(40) NOT NULL COMMENT '记录id',
  `resume_id` varchar(40) NOT NULL COMMENT '简历id',
  `content` blob NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作经历';

/*Data for the table `t_resume_workhistory` */

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
