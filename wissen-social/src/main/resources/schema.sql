create database wiseconnect;
use wiseconnect;


create table employee(
emp_id varchar(10) primary key,
first_name varchar(50),
last_name varchar(50),
bio varchar(255),
current_position varchar(50),
qualification_degree varchar(20),
institute_name varchar(50),
contact_number_personal bigint,
contact_number_work bigint,
email_id varchar(50),
resume varchar(255),
bio_pic varchar(255),
manager_id varchar(10) ,
date_of_birth Date,
marital_status varchar(20),
gender varchar(10),
application_status integer,
foreign key (manager_id) references employee(emp_id)
);

create table user_credential(
user_credential_id integer primary key auto_increment,
password varchar(255),
role_id integer,
emp_id varchar(10),	
foreign key (emp_id) references employee(emp_id) );


create table role(
role_id integer primary key auto_increment,
role_name varchar(255) );

insert into role(role) values('superadmin');
insert into role(role) values('admin');
insert into role(role) values('employee');

create table address(
address_id integer primary key auto_increment,
emp_id varchar(10),
street varchar(255),
city varchar(50),
state varchar(50),
country varchar(50),
foreign key (emp_id) references employee(emp_id),
zipcode integer(7));

create table certification (
certification_id integer primary key auto_increment,
emp_id varchar(10),
certification_name varchar(255),
completion_year integer(10),
foreign key(emp_id) references employee(emp_id) );


create table all_skill
( all_skill_id integer primary key auto_increment,
  name varchar(100));

insert into all_skill(name) values( "Java" );
insert into all_skill(name) values( "JS");
insert into all_skill(name) values( "Machine Learning" );
insert into all_skill(name) values( "Python" );
insert into all_skill(name) values( "Angular" );
insert into all_skill(name) values( "React" );
insert into all_skill(name) values( "Networking" );

create table skill
( 
  skill_id integer primary key auto_increment,
  emp_id varchar(10),
  all_skill_id integer,
 foreign key (all_skill_id) references all_skill(all_skill_id),
foreign key (emp_id) references employee(emp_id)
);

create table admin(
admin_id integer primary key auto_increment,
id varchar(10) unique,
password varchar(255),
role_id integer,
foreign key(role_id) references role(role_id) );
