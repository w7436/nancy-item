drop database if exists book;
create database book character set utf8mb4;

use book;

drop table if exists user;
create table user(
    id int primary key auto_increment,
    username varchar(20) not null unique comment '用户账号',
    password varchar(20) not null comment '密码',
    nickname varchar(20) comment '用户昵称',
    email varchar(50) comment '邮箱',
    create_time timestamp default NOW() comment '创建时间'
) comment '用户表';

drop table if exists dictionary;
create table dictionary(
    id int primary key auto_increment,
    dictionary_key varchar(20) not null unique comment '键',
    dictionary_value varchar(20) not null comment '值',
    dictionary_desc varchar(20) comment '备注',
    create_time timestamp default NOW() comment '创建时间'
) comment '数据字典';

drop table if exists dictionary_tag;
create table dictionary_tag(
    id int primary key auto_increment,
    dictionary_tag_key varchar(20) not null comment '键',
    dictionary_tag_value varchar(20) not null comment '值',
    dictionary_tag_desc varchar(20) comment '备注',
    dictionary_id int comment '数据字典id',
    create_time timestamp default NOW() comment '创建时间',
    foreign key (dictionary_id) references dictionary(id)
) comment '数据字典标签';

drop table if exists classes;
create table classes(
    id int primary key auto_increment,
    classes_name varchar(20) not null comment '班级名称',
    classes_graduate_year varchar(20) comment '毕业年份，数据字典000001',
    classes_major varchar(20) comment '专业，数据字典000002',
    classes_desc varchar(50) comment '备注',
    create_time timestamp default NOW() comment '创建时间'
) comment '班级表';

drop table if exists student;
create table student(
    id int primary key auto_increment,
    student_name varchar(20) not null comment '姓名',
    student_no varchar(20) comment '学号',
    id_card varchar(20) comment '身份证号',
    student_email varchar(50) comment '邮箱',
    classes_id int comment '班级id',
    create_time timestamp default NOW() comment '创建时间',
    foreign key (classes_id) references classes(id)
) comment '学生表';

drop table if exists book;
create table book(
   id int primary key auto_increment,
   book_name varchar(50) not null comment '图书名称',
   author varchar(20) comment '作者',
   price decimal(10,2) comment '价格',
   create_time timestamp default NOW() comment '创建时间'
) comment '图书信息';

drop table if exists borrow_record;
create table borrow_record(
   id int primary key auto_increment,
   book_id int comment '图书id',
   student_id int comment '学生id',
   start_time timestamp not null comment '借阅日期',
   end_time timestamp null default null comment '归还日期',
   create_time timestamp default NOW() comment '创建时间',
   foreign key (book_id) references book(id),
   foreign key (student_id) references student(id)
) comment '图书借阅信息';




-- 初始化数据
-- mysql中没有==，是用=号代替==。为了区分=和==，赋值时使用:=
set @username:='abc';
set @password:='123';
set @nickname:='风一样的男子😱';
set @email:='123@qq.com';

set @dictionary_classes_graduate_year='000001';
set @dictionary_classes_major='000002';

set @classes_name:='幼儿园😂';
set @student_name:='小小的梦想🐷';

insert into user(username, nickname, password, email) values (@username, @nickname, @password, @email);
insert into user(username, nickname, password, email) values (concat(@username, '1'), concat(@nickname, '1'), @password, @email);
insert into user(username, nickname, password, email) values (concat(@username, '2'), concat(@nickname, '2'), @password, @email);
insert into user(username, nickname, password, email) values (concat(@username, '3'), concat(@nickname, '3'), @password, @email);
insert into user(username, nickname, password, email) values (concat(@username, '4'), concat(@nickname, '4'), @password, @email);
insert into user(username, nickname, password, email) values (concat(@username, '5'), concat(@nickname, '5'), @password, @email);

## 数据字典：学生毕业年份
insert into dictionary(dictionary_key, dictionary_value, dictionary_desc)values (@dictionary_classes_graduate_year, '毕业年份', '学生毕业的年份');

insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('001', '2020届', 1);
insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('002', '2021届', 1);
insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('003', '2022届', 1);
insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('004', '2023届', 1);

## 数据字典：学生专业
insert into dictionary(dictionary_key, dictionary_value, dictionary_desc)values (@dictionary_classes_major, '专业', '学生的专业');
insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('001', '中文系', 2);
insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('002', '英语系', 2);
insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('003', '计算机科学与技术', 2);

insert into classes(classes_name, classes_graduate_year, classes_major, classes_desc) values (concat(@classes_name, '大班'), '000001001', '000002003', '已经4岁，即将成为大人的大班同学，不再是3岁小孩子了呀');
insert into classes(classes_name, classes_graduate_year, classes_major, classes_desc) values (concat(@classes_name, '中班'), '000001002', '000002003', '虽然还是3岁的小孩子，但也开始思考人生了呢');
insert into classes(classes_name, classes_graduate_year, classes_major, classes_desc) values (concat(@classes_name, '小班'), '000001003', '000002003', '挂着鼻涕的跟屁虫');

insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'A1'), 's00001', '222222222222222222', @email, 1);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'A2'), 's00002', '222222222222222223', @email, 1);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'A3'), 's00003', '222222222222222224', @email, 1);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'A4'), 's00004', '222222222222222225', @email, 1);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'A5'), 's00005', '222222222222222226', @email, 1);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'B1'), 's00006', '222222222222222227', @email, 2);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'B2'), 's00007', '222222222222222228', @email, 2);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'B3'), 's00008', '222222222222222229', @email, 2);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'B4'), 's00009', '222222222222222230', @email, 2);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'B5'), 's00010', '222222222222222231', @email, 2);

insert into book(book_name, author, price, create_time) values ('高等数学', '马拉司机', 115.2, now());
insert into book(book_name, author, price, create_time) values ('诗和远方', '老湿', 61.5, now());
insert into book(book_name, author, price, create_time) values ('柴米油盐', '家庭妇男', 33, now());
insert into book(book_name, author, price, create_time) values ('▄︻┻┳═一∵∴∷∶∵(∵_,∵)>>>>散弹发射！！', '蚷神', 33, now());

insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (1, 1, '2020-01-01 14:20:00', '2020-02-01 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (1, 2, '2020-03-01 14:20:00', '2020-03-22 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (1, 3, '2020-05-01 14:20:00', '2020-05-12 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (2, 5, '2020-01-01 14:20:00', '2020-02-03 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (2, 7, '2020-02-04 14:20:00', '2020-03-03 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (2, 9, '2020-04-01 14:20:00', '2020-06-01 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (3, 3, '2020-02-01 14:20:00', '2020-02-21 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (3, 6, '2020-04-01 14:20:00', '2020-05-01 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (3, 8, '2020-06-01 14:20:00', '2020-06-06 14:20:00', now());


select * from borrow_record where start_time >= '2020-05-01 14:20:00' and end_time <= '2020-06-06 14:20:00';