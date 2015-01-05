#如果复制到mysql中执行时 加上
#DELIMITER ;;

drop table if exists `account`;;
##account
create table `account`(
  `id`         bigint not null auto_increment,
  `password`  varchar(100),
  `salt`       varchar(10),
  `create_date` timestamp default 0,
  `deleted`   bool,
  `active`   bool,
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
  constraint `pk_email` primary key(`id`),
  constraint `unique_email` unique(`email`),
  index `idx_email_main` (`main`)
) charset=utf8 ENGINE=InnoDB;;
alter table `email` auto_increment=1000;;