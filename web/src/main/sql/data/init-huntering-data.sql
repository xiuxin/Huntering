#如果复制到mysql中执行时 加上
#DELIMITER ;;

delete from `account` where id>=1 and id<=1000;;
/*默认admin/123456*/
insert into `account`
(`id`, `password`, `salt`, `deleted`, `active`)
  values
  (1, '7ec5c233d59e08bfe045292809ffac35', 'iY71e4d123', 0, 1);;
  
delete from `email` where id>=1 and id<=1000;;
insert into `email`
(`id`, `email`, `account_id`, `main`, `active`)
  values
  (1, 'qiuchen_yao@126.com', 1, 1, 1);;
  
delete from `invitation_code` where id>=1 and id<=1000;;
insert into `invitation_code` (`id`, `code`, `used`) values (1, 'invcode1', 0);;
insert into `invitation_code` (`id`, `code`, `used`) values (2, 'invcode2', 0);;
insert into `invitation_code` (`id`, `code`, `used`) values (3, 'invcode3', 0);;
delete from `apply_invitation_code` where id>=1 and id<=1000;;
insert into `apply_invitation_code` (`id`, `email`) values (1, 'testtest@126.com');;

delete from `people` where id>=1 and id<=1000;;
/*默认admin/123456*/
insert into `people`
(`id`, `account_id`, `fullName`, `self`,`deleted`,`email`)
values (1, 1, '张三', 1, 0, 'qiuchen_yao@126.com');;
insert into `people`
(`id`, `account_id`, `fullName`, `self`,`deleted`,`email`)
values (2, 1, '李四', 0, 0, 'bell_qiu@outlook.com');;
  
delete from `people_education` where id>=1 and id<=1000;;
insert into `people_education`
(`id`, `people_id`, `degree`, `desb`, `deleted`)
  values
  (1, 1, 1, '账户人', 0);;
insert into `people_education`
(`id`, `people_id`, `degree`, `desb`, `deleted`)
  values
  (2, 2, 1, 'Candidate', 0);;
  
  
delete from `company` where id>=1 and id<=1000;;
insert into `company`
(`id`, `name`, `typo`, `location`, `deleted`)
  values
  (1, 'HP', 1, '中国芯科技园', 0);;
insert into `company`
(`id`, `name`, `typo`, `location`, `deleted`)
  values
  (2, 'SAP', 1, '晨晖路100号', 0);;
  
delete from `people_company` where id>=1 and id<=1000;;
insert into `people_company`
(`id`, `people_id`, `company_id`, `startdate`, `enddate`, `title`, `deleted`)
  values
  (1, 1, 1, '2011-12-28', '2014-06-30', '扫地神僧', 0);;
insert into `people_company`
(`id`, `people_id`, `company_id`, `startdate`, `enddate`, `title`, `deleted`)
  values
  (2, 2, 2, '2011-12-28', '2014-06-30', '扫地神僧', 0);;
  
delete from `job` where id>=1 and id<=1000;;
insert into `job`
(`id`, `company_id`, `title`, `desb`, `requirement`, `status`, `onboard_date`)
  values
  (1, 1, 'Java开发工程师', '开发Huntering网站', '1. spring 2. mysql 3. tomcat', 'ACTIVE', '2015-10-01');;
  
delete from `activity` where id>=1 and id<=1000;;
insert into `activity`
(`id`, `job_id`, `people_id`, `feedback`, `desb`, `pass`, `status`)
  values
  (1, 1, 1, 'No feedback now', 'AAA面试BB公司', 0, 'ACTIVE');;
insert into `activity`
(`id`, `job_id`, `people_id`, `feedback`, `desb`, `pass`, `status`)
  values
  (2, 1, 2, 'No feedback now', 'BBB面试BB公司', 0, 'ACTIVE');;

delete from `account_activity_conn` where id>=1 and id<=1000;;
insert into `account_activity_conn` (`id`, `account_id`, `activity_id`) values (1, 1, 1);;
insert into `account_activity_conn` (`id`, `account_id`, `activity_id`) values (2, 1, 2);;
  
delete from `activity_type` where id>=1 and id<=1000;;
insert into `activity_type`(`id`, `type_name`, `desb`) values (1, '电话面试', '电话面试');;
insert into `activity_type`(`id`, `type_name`, `desb`) values (2, '公司直接面试', 'Face to face面试');;
insert into `activity_type`(`id`, `type_name`, `desb`) values (3, '做题目', '做题目');;

delete from `people_role` where id>=1 and id<=1000;;
insert into `people_role`(`id`, `name`, `desb`) values (1, '面试候选人', '面试候选人');;
insert into `people_role`(`id`, `name`, `desb`) values (2, '面试官', '面试官');;
insert into `people_role`(`id`, `name`, `desb`) values (3, '面试经理', '面试经理');;

delete from `activity_round` where id>=1 and id<=1000;;
insert into `activity_round`
(`id`, `start_date`, `end_date`, `address`, `round`, `pass`, `activity_id`, `activity_type_id`)
  values
  (1, '2015-10-01 13:00:00', '2015-10-01 16:00:00', '上海市金科路000号 ABC公司', 1, 0, 1, 1);;
insert into `activity_round`
(`id`, `start_date`, `end_date`, `address`, `round`, `pass`, `activity_id`, `activity_type_id`)
  values
  (2, '2015-10-05 13:00:00', '2015-10-05 16:00:00', '上海市金科路000号 ABC公司', 2, 0, 1, 2);;
insert into `activity_round`
(`id`, `start_date`, `end_date`, `address`, `round`, `pass`, `activity_id`, `activity_type_id`)
  values
  (3, '2015-10-01 13:00:00', '2015-10-01 16:00:00', '上海市金科路000号 ABC公司', 1, 0, 2, 2);;
insert into `activity_round`
(`id`, `start_date`, `end_date`, `address`, `round`, `pass`, `activity_id`, `activity_type_id`)
  values
  (4, '2015-10-01 13:00:00', '2015-10-01 16:00:00', '上海市金科路000号 ABC公司', 2, 0, 2, 2);;
  
delete from `activity_people_conn` where id>=1 and id<=1000;;
insert into `activity_people_conn`
(`id`, `activity_round_id`, `people_id`, `people_role_id`)
  values
  (1, 1, 1, 'INTERVIEE');;
insert into `activity_people_conn`
(`id`, `activity_round_id`, `people_id`, `people_role_id`)
  values
  (2, 2, 2, 'INTERVIER');;
insert into `activity_people_conn`
(`id`, `activity_round_id`, `people_id`, `people_role_id`)
  values
  (3, 3, 2, 'INTERVIEE');;
insert into `activity_people_conn`
(`id`, `activity_round_id`, `people_id`, `people_role_id`)
  values
  (4, 4, 1, 'INTERVIER');;
  
delete from `feedback` where id>=1 and id<=1000;;
insert into `feedback`
(`id`, `activity_round_id`, `detail`, `behavihor`, `profession`, `language`, `innovation`, `communication`, `execution`, `teamwork`, `management`, `desb`, `result`)
  values
  (1, 1, 'Feed back detail is ok', 1, 2, 3, 4, 5, 4, 3, 2, '可以录用', 1);;
insert into `feedback`
(`id`, `activity_round_id`, `detail`, `behavihor`, `profession`, `language`, `innovation`, `communication`, `execution`, `teamwork`, `management`, `desb`, `result`)
  values
  (2, 2, 'Feed back detail is ok', 1, 2, 3, 4, 5, 4, 3, 2, '可以录用', 1);;
insert into `feedback`
(`id`, `activity_round_id`, `detail`, `behavihor`, `profession`, `language`, `innovation`, `communication`, `execution`, `teamwork`, `management`, `desb`, `result`)
  values
  (3, 3, 'Feed back detail is ok', 1, 2, 3, 4, 5, 4, 3, 2, '可以录用', 1);;
insert into `feedback`
(`id`, `activity_round_id`, `detail`, `behavihor`, `profession`, `language`, `innovation`, `communication`, `execution`, `teamwork`, `management`, `desb`, `result`)
  values
  (4, 4, 'Feed back detail is ok', 1, 2, 3, 4, 5, 4, 3, 2, '可以录用', 1);;


delete from `message` where id>=1 and id<=1000;;
insert into `message`
(`id`, `account_id`, `people_id`, `activity_id`, `message_type`)
  values
  (1, 1, 1, null, 'RESUME');;
insert into `message`
(`id`, `account_id`, `people_id`, `activity_id`, `message_type`)
  values
  (2, 1, null, 1, 'ACTIVITY');;
insert into `message`
(`id`, `account_id`, `people_id`, `activity_id`, `message_type`)
  values
  (3, 1, null, 2, 'ACTIVITY');;

