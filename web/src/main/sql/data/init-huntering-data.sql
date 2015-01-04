#如果复制到mysql中执行时 加上
#DELIMITER ;;

delete from `account` where id>=1 and id<=1000;;
/*默认admin/123456*/
insert into `account`
(`id`, `email`, `password`, `salt`, `create_date`, `deleted`)
  values
  (1, 'qiuchen_yao@126.com', '59f8688873c1d78d0dd5e7871c211a04', 'yDd1956wn1', sysdate(), 0);;
