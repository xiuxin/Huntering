#如果复制到mysql中执行时 加上
#DELIMITER ;;

drop table if exists `account`;;
##account
create table `account`(
  `id`         bigint not null auto_increment,
  `email`  varchar(100),
  `password`  varchar(100),
  `salt`       varchar(10),
  `create_date` timestamp default 0,
  `deleted`   bool,
  constraint `pk_account` primary key(`id`),
  constraint `unique_account_email` unique(`email`)
) charset=utf8 ENGINE=InnoDB;;
alter table `account` auto_increment=1000;;

