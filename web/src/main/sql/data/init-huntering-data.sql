#如果复制到mysql中执行时 加上
#DELIMITER ;;

delete from `account` where id>=1 and id<=1000;;
/*默认admin/123456*/
insert into `account`
(`id`, `password`, `salt`, `create_date`, `deleted`, `active`)
  values
  (1, '7ec5c233d59e08bfe045292809ffac35', 'yDd1956wn1', sysdate(), 0, 1);;

  
delete from `email` where id>=1 and id<=1000;;
insert into `email`
(`id`, `email`, `account_id`, `main`)
  values
  (1, 'qiuchen_yao@126.com', 1, 1);;
  
delete from `auth_code` where id>=1 and id<=1000;;
insert into `auth_code` (`id`, `code`, `used`) values (1, 'authcode1', 0);;
insert into `auth_code` (`id`, `code`, `used`) values (2, 'authcode2', 0);;
insert into `auth_code` (`id`, `code`, `used`) values (3, 'authcode3', 0);;