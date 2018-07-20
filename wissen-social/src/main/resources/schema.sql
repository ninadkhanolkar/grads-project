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


insert into employee values('WT350','Guru','Murthy','Hello all','Architect','MTECH','Inst',9976543211,9987654321,'guru@wissen.com','resume','Pic',NULL,'1985-05-11','Married','Male',0);
insert into employee values('WT300','darshan','dalsaniya','Hello hii','trainee analyst','BTECH','msrit',9976543211,9987654321,'darshan@wissen.com','resume','Pic','WT350','1996-06-09','Unmarried','Male',0);
insert into employee values('WT301','saurabh','B','hii all','trainee analyst','BTECH','vit',9976543211,9987654321,'saurabh@wissen.com','resume','Pic','WT350','1996-06-01','Unmarried','Male',0);
insert into employee values('WT302','shubham','S','Hola all','trainee analyst','BTECH','vit',9976543211,9987654321,'shubham@wissen.com','resume','Pic','WT350','1996-06-07','Unmarried','Male',0);
insert into employee values('WT303','Ninand','Khanolkar','Hello','trainee analyst','BTECH','pesit',9976543211,9987654321,'ninand@wissen.com','resume','Pic','WT350','1996-06-28','Unmarried','Male',0);


create table user_credential(
user_credential_id integer primary key auto_increment,
password varchar(255),
role_id integer,
emp_id varchar(10),	
foreign key (emp_id) references employee(emp_id) );

insert into user_credential(password,role_id,emp_id) values(12345678,3,'WT350');
insert into user_credential(password,role_id,emp_id) values(12345678,3,'WT300');
insert into user_credential(password,role_id,emp_id) values(12345678,3,'WT301');
insert into user_credential(password,role_id,emp_id) values(12345678,3,'WT302');
insert into user_credential(password,role_id,emp_id) values(12345678,3,'WT303');

create table role(
role_id integer primary key auto_increment,
role varchar(255) );

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

insert into address(emp_id,street,city,state,country,zipcode) values('WT350','4th cross','bangalore','karnataka','india',560066);
insert into address(emp_id,street,city,state,country,zipcode) values('WT300','5th cross','bangalore','karnataka','india',560066);
insert into address(emp_id,street,city,state,country,zipcode) values('WT301','6th cross','bangalore','karnataka','india',560066);
insert into address(emp_id,street,city,state,country,zipcode) values('WT302','7th cross','bangalore','karnataka','india',560066);
insert into address(emp_id,street,city,state,country,zipcode) values('WT303','8th cross','bangalore','karnataka','india',560066);

create table certification (
certification_id integer primary key auto_increment,
emp_id varchar(10),
certification_name varchar(255),
completion_year integer(10),
foreign key(emp_id) references employee(emp_id) );

insert into certification(emp_id,certification_name,completion_year) values( 'WT350', 'DS' ,2000);  
insert into certification(emp_id,certification_name,completion_year) values( 'WT350', 'Algo' ,2000); 
insert into certification(emp_id,certification_name,completion_year) values( 'WT300', 'Java',2018);  
insert into certification(emp_id,certification_name,completion_year) values( 'WT300', 'Python' ,2017); 
insert into certification(emp_id,certification_name,completion_year) values( 'WT301', 'DS' ,2018);  
insert into certification(emp_id,certification_name,completion_year) values( 'WT301', 'Java',2017); 
insert into certification(emp_id,certification_name,completion_year) values( 'WT302', 'JS' ,2018);  
insert into certification(emp_id,certification_name,completion_year) values( 'WT302', 'Java' ,2017); 
insert into certification(emp_id,certification_name,completion_year) values( 'WT303', 'Java' ,2018);  
insert into certification(emp_id,certification_name,completion_year) values( 'WT303', 'Algo' ,2017); 

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

insert into skill(emp_id, all_skill_id) values( 'WT350' , 1 );
insert into skill(emp_id, all_skill_id) values( 'WT350' , 2 ); 
insert into skill(emp_id, all_skill_id) values( 'WT300' , 3  );
insert into skill(emp_id, all_skill_id) values( 'WT300' , 4 );
insert into skill(emp_id, all_skill_id) values( 'WT301' , 5 );
insert into skill(emp_id, all_skill_id) values( 'WT301' , 6 );
insert into skill(emp_id, all_skill_id) values( 'WT302' , 6   );
insert into skill(emp_id, all_skill_id) values( 'WT302' , 7 );
insert into skill(emp_id, all_skill_id) values( 'WT303' , 7 );
insert into skill(emp_id, all_skill_id) values( 'WT303' , 1 );

create table admin(
admin_id integer primary key auto_increment,
id varchar(10) unique,
password varchar(255),
role_id integer,
foreign key(role_id) references role(role_id) );

insert into admin(id,password,role_id) values( 'AD01' , 'admin', 1);
insert into admin(id,password,role_id) values( 'AD02' , 'admin', 2);
insert into admin(id,password,role_id) values( 'AD03' , 'admin', 2);
insert into admin(id,password,role_id) values( 'AD04' , 'admin', 2);
insert into admin(id,password,role_id) values( 'AD05' , 'admin', 2);

update employee set application_status = 1 where emp_id in('WT301','WT300');