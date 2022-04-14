/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.62 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `user` (
	`id` int ,
	`username` char ,
	`password` char ,
	`name` char ,
	`email` char ,
	`type` char ,
	`date` date ,
	`status` int 
); 
insert into `user` (`id`, `username`, `password`, `name`, `email`, `type`, `date`, `status`) values('3','10001','123456','张三','123456@qq.com','admin','2022-04-14','0');
insert into `user` (`id`, `username`, `password`, `name`, `email`, `type`, `date`, `status`) values('4','10002','123456','李四','123456@qq.com','user','2022-04-14','0');
insert into `user` (`id`, `username`, `password`, `name`, `email`, `type`, `date`, `status`) values('7','10004','123456','测试2','123456@qq.com','user','2022-04-14','0');
insert into `user` (`id`, `username`, `password`, `name`, `email`, `type`, `date`, `status`) values('2','admin','admin','管理员','123456@qq.com','admin','2022-04-14','0');
