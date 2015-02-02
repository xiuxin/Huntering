#如果复制到mysql中执行时 加上
#DELIMITER ;;

drop table if exists `account`;;
drop table if exists `email`;;
drop table if exists `invitation_code`;;

drop table if exists `company`;;
drop table if exists `people`;;
drop table if exists `people_company`;;
drop table if exists `people_education`;;
drop table if exists `apply_invitation_code`;;
drop table if exists `job`;;
drop table if exists `activity`;;
drop table if exists `account_activity_conn`;
drop table if exists `activity_type`;;
drop table if exists `people_role`;;
drop table if exists `activity_round`;;
drop table if exists `activity_people_conn`;;

##account
create table `account`(
  `id`         bigint not null auto_increment,
  `password`  varchar(100),
  `salt`       varchar(10),
  `mdn`   varchar(20),
  `deleted`   bool,
  `active`   bool,
  `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint `pk_account` primary key(`id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `account` auto_increment=1000;;

##email
create table `email`(
  `id`         bigint not null auto_increment,
  `account_id` bigint,
  `email`  varchar(100),
  `main`   bool,
  `active`   bool,
  `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint `pk_email` primary key(`id`),
  constraint `unique_email` unique(`email`),
  index `idx_email_main` (`main`)
) charset=utf8 ENGINE=InnoDB;;
alter table `email` auto_increment=1000;;

##AUTH_CODE
create table `invitation_code`(
  `id`         bigint not null auto_increment,
  `code`  varchar(100),
  `used`   bool,
  `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint `pk_invitation_code` primary key(`id`),
  constraint `unique_invitation_code` unique(`code`),
  index `idx_invitation_code_used` (`used`)
) charset=utf8 ENGINE=InnoDB;;
alter table `invitation_code` auto_increment=1000;;

##apply_invitation_code
create table `apply_invitation_code`(
  `id`         bigint not null auto_increment,
  `email`  varchar(100),
  `applyDate` timestamp DEFAULT CURRENT_TIMESTAMP,
  constraint `pk_apply_invitation_code` primary key(`id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `apply_invitation_code` auto_increment=1000;;

##people
create table `people`(
  `id`         	bigint not null auto_increment,
  `account_id` 	bigint,
  `self` 		bool,
  `nickname`  	varchar(100),
  `fullname`  	varchar(100),
  `mdn`  		varchar(100),
  `country`  	varchar(100),
  `city`  		varchar(100),
  `district`  	varchar(100),
  `desb`  		varchar(255),
  `birthday` 	date,
  `age` 		tinyint,
  `gender` 		tinyint,
  `summary` 	varchar(255),
  `deleted`   bool,
  `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint `pk_people` primary key(`id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `people` auto_increment=1000;;
    
##people_education
create table `people_education`(
  `id`         	bigint not null auto_increment,
  `people_id` 	bigint,
  `college` 	varchar(255),
  `degree`  	tinyint,
  `desb`  		varchar(255),
  `startdate` 	date,
  `enddate` 	date,
  `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted`   bool,
  constraint `pk_people_education` primary key(`id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `people_education` auto_increment=1000;;

##company
create table `company`(
  `id`         	bigint not null auto_increment,
  `temp` 		bool,
  `name`  		varchar(255),
  `website`  	varchar(100),
  `industry`  	smallint,
  `typo`  		smallint,
  `size`  		smallint,
  `version`  	varchar(100),
  `location`  	varchar(255),
  `deleted`   	bool,
  `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint `pk_company` primary key(`id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `company` auto_increment=1000;;

##peoplecompany
create table `people_company`(
  `id`         	bigint not null auto_increment,
  `people_id` 	bigint not null ,
  `company_id`  bigint not null ,
  `detail`  	varchar(100),
  `title`  		varchar(100),
  `desb`  		varchar(255),
  `startdate` 	date,
  `enddate` 	date,
  `deleted`   	bool,
  `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint `pk_people_company` primary key(`id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `company` auto_increment=1000;;

##job
create table `job`(
  `id`         bigint not null auto_increment,
  `company_id`  bigint,
  `title`  		varchar(100),
  `desb`  		varchar(255),
  `requirement`   varchar(6000),
  `status`   varchar(10),
  `onboard_date`	timestamp,
  `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint `pk_job` primary key(`id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `job` auto_increment=1000;;

##activity
create table `activity`(
  `id`         bigint not null auto_increment,
  `job_id`  bigint,
  `people_id`  bigint,
  `feedback`  		varchar(255),
  `desb`  		varchar(255),
  `pass`   	bool,
  `status`   varchar(10),
  `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint `pk_activity` primary key(`id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `activity` auto_increment=1000;;

##account_activity_conn
create table `account_activity_conn`(
  `id`         bigint not null auto_increment,
  `account_id`  bigint,
  `activity_id`  bigint,
  constraint `pk_account_activity_conn` primary key(`id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `account_activity_conn` auto_increment=1000;;

##activity_type
create table `activity_type`(
  `id`         bigint not null auto_increment,
  `type_name`  varchar(50),
  `desb`  varchar(255),
  constraint `pk_activity_type` primary key(`id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `activity_type` auto_increment=1000;;

##people_role
create table `people_role`(
  `id`         bigint not null auto_increment,
  `name`  varchar(50),
  `desb`  varchar(255),
  constraint `pk_people_role` primary key(`id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `people_role` auto_increment=1000;;

##activity_round
create table `activity_round`(
  `id`         bigint not null auto_increment,
  `start_date`  timestamp,
  `end_date`  timestamp,
  `address`  		varchar(255),
  `round`  		smallint,
  `pass`   	bool,
  `activity_id`	bigint,
  `activity_type_id`	bigint,
  `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint `pk_activity_round` primary key(`id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `activity_round` auto_increment=1000;;

##activity_people_conn
create table `activity_people_conn`(
  `id`         bigint not null auto_increment,
  `activity_round_id`  bigint,
  `people_id`  bigint,
  `people_role_id`  bigint,
  constraint `pk_activity_people_conn` primary key(`id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `activity_people_conn` auto_increment=1000;;

