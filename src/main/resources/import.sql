
insert into course (id , name) values ( 1 , 'course 1');
insert into course (id , name) values ( 2 , 'course 2');
insert into course (id , name) values ( 3 , 'course 3');
insert into course (id , name) values ( 4 , 'course 4');
insert into course (id , name) values ( 5 , 'course 5');
insert into course (id , name) values ( 6 , 'course 6');

insert into student (id , name) values ( 1 , 'student 1');
insert into student (id , name) values ( 2 , 'student 2');
insert into student (id , name) values ( 3 , 'student 3');
insert into student (id , name) values ( 4 , 'student 4');
insert into student (id , name) values ( 5 , 'student 5');
insert into student (id , name) values ( 6 , 'student 6');

insert into course_regestertion (course_id , student_id) values ( 1 , 1);
insert into course_regestertion (course_id , student_id) values ( 1 , 2);
insert into course_regestertion (course_id , student_id) values ( 1 , 3);

insert into course_regestertion (course_id , student_id) values ( 2 , 1);
insert into course_regestertion (course_id , student_id) values ( 2 , 2);
insert into course_regestertion (course_id , student_id) values ( 2 , 3);

insert into course_regestertion (course_id , student_id) values ( 3 , 1);
insert into course_regestertion (course_id , student_id) values ( 3 , 2);
insert into course_regestertion (course_id , student_id) values ( 3 , 3);

insert into course_regestertion (course_id , student_id) values ( 4 , 1);
insert into course_regestertion (course_id , student_id) values ( 5 , 1);

insert into user_auth(id  , user_name , password ,role , is_active) values ( 1 , 'test' , '$2a$10$TlgG76flvMCelK0Mc98itu4kH4O/aBNyeYRlFW1z/UkBtl3nGDuQq' , 'USER' , 1);
insert into user_auth(id  , user_name , password ,role , is_active) values ( 2 , 'user' , '$2a$10$Fffx83Ij10um8e8l8K2MTufsuvMc0tk7RwybSbD5qAklZmaqZgJMC' , 'USER' , 1);
insert into user_auth(id  , user_name , password ,role , is_active) values ( 3 , 'admin' , '$2a$10$At/BcGrj2W.sRKIHvD8SpeuzZyyXJigij3H5vwtVqnJLRV/2.DntS' , 'ROLE_ADMIN' , 1);

