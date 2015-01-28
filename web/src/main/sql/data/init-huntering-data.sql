#如果复制到mysql中执行时 加上
#DELIMITER ;;

delete from `account` where id>=1 and id<=1000;;
/*默认admin/123456*/
insert into `account`
(`id`, `password`, `salt`, `deleted`, `active`)
  values
  (1, '7ec5c233d59e08bfe045292809ffac35', 'iY71e4d123', 0, 1);;
  
delete from `people` where id>=1 and id<=1000;;
/*默认admin/123456*/
insert into `people`
(`id`, `account_id`, `fullName`, `self`,`deleted`)
  values
  (1, 1, '苍井空', 1, 0);;
  
delete from `people_education` where id>=1 and id<=1000;;
insert into `people_education`
(`id`, `people_id`, `degree`, `desb`, `deleted`)
  values
  (1, 1, 1, '给校长当小三', 0);;
  
delete from `email` where id>=1 and id<=1000;;
insert into `email`
(`id`, `email`, `account_id`, `main`, `active`)
  values
  (1, 'qiuchen_yao@126.com', 1, 1, 1);;
  
delete from `invitation_code` where id>=1 and id<=1000;;
insert into `invitation_code` (`id`, `code`, `used`) values (1, 'invcode1', 0);;
insert into `invitation_code` (`id`, `code`, `used`) values (2, 'invcode2', 0);;
insert into `invitation_code` (`id`, `code`, `used`) values (3, 'invcode3', 0);;