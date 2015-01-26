#如果复制到mysql中执行时 加上
#DELIMITER ;;

drop table if exists `account`;;
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

drop table if exists `email`;;
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

drop table if exists `invitation_code`;;
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
alter table `email` auto_increment=1000;;

drop table if exists `people`;;
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
  `desb`  		varchar(100),
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