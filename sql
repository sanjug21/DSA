create table empinfo (fname varchar(20),lname varchar(15),mname varchar(10),emid int,age int,city varchar(20));
alter table empinfo drop column mname;
alter table empinfo add state varchar(20);
insert into empinfo values('Pushpendra','Singh',101,23,'mathura','up');
insert into empinfo values('Yeshvendra','Singh',102,20,'mathura','up');
insert into empinfo values('Sagar','Rajput',103,20,'Agra','up');
insert into empinfo values('Rajesh','Kumar',104,21,'Aligarh','up');
insert into empinfo values('Manish','Singh',105,21,'Agra','up');
select fname,lname from empinfo;
select * from empinfo where state!='up' ;
select fname,lname from empinfo where lname like '%gh';
select * from empinfo where city='mathura' and age<=21;
select * from empinfo where city like '____' or city like 'A%';
desc empinfo;
select distinct age from empinfo;
select *from empinfo;
select * from empinfo order by age DESC;


// ASSIGNMENT

create table college(cName varchar(10),state varchar(10),enrollment int);
insert into college values('Stanford','CA',15000);
insert into college values('Berkeley ','CA',36000);
insert into college values('MIT','MA',10000);
insert into college values('Cornell','NY',21000);
insert into college values('Harvard','MA',50040);

create table student(sID int, sName varchar(10), GPA number(2,1), sizeHS int, DoB date);
insert into student values(123, 'Amy' ,3.9 ,1000,'26-JUN-96');
insert into student values(234, 'Bob' ,3.6 ,1500,'7-Apr-95');
insert into student values(345, 'Craig', 3.5, 500,'4-Feb-95');
insert into student values(456, 'Doris', 3.9, 100,'24-Jul-97');
insert into student values(567, 'Edward' ,2.9 ,2000,'21-Dec-96');
insert into student values(678, 'Fay', 3.8, 200,'27-Aug-96');
insert into student values(789, 'Gary', 3.4, 800,'8-Oct-96');
insert into student values(987, 'Helen', 3.7, 800,'27-Mar-97');
insert into student values(876, 'Irene', 3.9, 400,'7-Mar-96');
insert into student values(765, 'Jay' ,2.9 ,1500,'8-Aug-98');
insert into student values(654, 'Amy' ,3.9 ,1000,'26-May-96');
insert into student values(543, 'Craig' ,3.4 ,2000,'27-Aug-98');

create table apply(sID int,cName varchar(10),major varchar(20),decision char(1));
insert into apply values(123, 'Stanford', 'CS', 'Y');
insert into apply values(123, 'Stanford' ,'EE' ,'N');
insert into apply values(123 ,'Berkeley' ,'CS' ,'Y');
insert into apply values(123 ,'Cornell' ,'EE' ,'Y');
insert into apply values(234, 'Berkeley', 'biology', 'N');
insert into apply values(345 ,'MIT' ,'bioengineering', 'Y');
insert into apply values(345, 'Cornell', 'bioengineering', 'N');
insert into apply values(345, 'Cornell' ,'CS', 'Y');
insert into apply values(345, 'Cornell' ,'EE' ,'N');
insert into apply values(678, 'Stanford', 'history', 'Y');
insert into apply values(987, 'Stanford' ,'CS' ,'Y');
insert into apply values(987, 'Berkeley' ,'CS' ,'Y');
insert into apply values(876, 'Stanford' ,'CS' ,'N');
insert into apply values(876, 'MIT' ,'biology' ,'Y');
insert into apply values(876, 'MIT' ,'marine' ,'biology' ,'N');
insert into apply values(765 ,'Stanford', 'history' ,'Y');
insert into apply values(765 ,'Cornell' ,'history', 'N');
insert into apply values(765 ,'Cornell' ,'psychology', 'Y');
insert into apply values(543 ,'MIT' ,'CS' ,'N');


// Assignment 2
create table College (cName varchar(10), state varchar(10), enrollment int);
create table Student (sID int, sName varchar(10), GPA number(2,1),sizeHS int);
create table Apply (sID int, cName varchar(10), major varchar(20));
alter table College add primary key(cName);
alter table Student add primary key(sID);
alter table apply add primary key(sid,cname,major);
alter table apply add foreign key(sid) references student;
alter table apply modify(major varchar(25));
alter table apply add (decision varchar(3) not null);
alter table apply modify(decision char(1));
alter table apply drop foreign key;
alter table student drop column sizehs;
alter table college drop primary key;
alter table apply add constraint abc unique(cname,major);

alter table apply add foreign key(cname) references college on delete cascade;
alter table apply add foreign key(sid) references student(sid) on delete set null;
alter table college rename column enrollment to enroll;
desc apply;
desc student;
desc college;


// Assignment 3
create table College(collegeName varchar2(10) primary key, state varchar2(10), enrollment int);
create table Student(sID int primary key, sName varchar2(10), GPA real, sizeHS int);
create table Apply(sID int, cName varchar2(10), major varchar2(20), decision char(1), primary key(sID, major, cName), constraint sID_fk Foreign key(sID) references Student, constraint cName_fk Foreign key(cName) references College);

select student.sid,sname,gpa,sizehs,major,cname,decision from student Inner JOIN apply on Student.sid=apply.sid;
select student.sid,sname,gpa,major,cname from student Inner JOIN apply on Student.sid=apply.sid;
select apply.* from apply Join college on apply.cname=college.collegeName where state='CA';
select student.sid,sname,gpa,apply.cname from student join apply on student.sid=apply.sid where gpa>3.7 AND cname='Stanford';
select student.sid,sname,gpa,sizehs from student join apply on Student.sid=apply.sid where major='CS' AND decision='N';
select student.*,apply.* from student join apply on student.sid=apply.sid join college on apply.cname=college.collegename where college.state='NY';
SELECT Student.* FROM Student LEFT JOIN Apply ON Student.sID =Apply.sID WHERE Apply.sID IS NULL;
select college.* from college left join apply on college.collegename=apply.cname where apply.cname is null;
select sid from apply group by sid having count(sid)=1;
select distinct sname, gpa from student  join apply on student.sid=apply.sid  join college on college.collegename=apply.cname where enrollment<=25000;
select s1.sid,s2.sid from student s1,student s2 where s1.gpa=s2.gpa AND s1.sid<s2.sid; 

//Assignment 4
create table College(cName varchar2(10) primary key, state varchar2(10), enrollment int);
create table Student(sID int primary key, sName varchar2(10), GPA real, sizeHS int);
create table Apply(sID int, cName varchar2(10), major varchar2(20), decision char(1), primary key(sID, major, cName), constraint sID_fk Foreign key(sID) references Student, constraint cName_fk Foreign key(cName) references College);

select count(sname) from student;
select avg(gpa) as AverageGPA from student;
select MIN(gpa)as Min_GPA,MAX(gpa) as Max_GPA from student;
select count(*)  from student where gpa>=3.7;
select max(gpa) as MAx_gpa,min(gpa) as min_gpa,avg(gpa) as average_gpa,sum(gpa) as total_of_gpa from student;
select count(distinct cname) from apply;
select count(distinct major) from apply;
select count(*) as Total_applications from apply;
select avg(distinct gpa) from Student;
select count(*) from apply where decision='Y';
select count(*) from student where gpa>3.4 AND sizehs>1000;
select count(*) from apply where major='marine biology';
select decision,count(*) from apply group by decision;
select major, count(sID) as No_of_applications from Apply group by major;
select cName, count(*) as Applications_Received from Apply group by cName;
select cName,major, count(*) as Applications_Received from Apply group by cName,major;
select cName,major from Apply group by cName,major having count(*)>=2;
select cName,count(*) as Applications_Received  from Apply group by cName having count(*)>=3;
select state,count(*) from college group by state having count(*)>1;
select sname,count(*) from student group by sname having count(*)>1;



//assignment 5

select student.sid,student.sname from student join apply on student.sid=apply.sid where major='CS';
select sid,sname from student where sizehs= (select sizehs from student where sname='Jay');
select sid,sname from student where sizehs= (select sizehs from student where sname='Jay') AND sname!='Jay';
select sid,sname,gpa from student where gpa!= (select gpa from student where sname='Irene');
select distinct (apply.cname) from apply join student on apply.sid=student.sid where sname like 'J%';
select apply.major from apply join student on apply.sid=student.sid where sname='Irene';
select distinct (sid),major from apply  where major in (select major from apply join student on apply.sid=student.sid where sname='Irene') ;
select distinct (sid),major from apply  where major in (select major from apply join student on apply.sid=student.sid where sname='Irene') and sid!=(select sid from student where sname='Irene');
select count(distinct (cname)) from apply  where sid=(select sid from student where sname='Jay');
SELECT A1.Sid FROM Apply A1, Apply A2 WHERE A1.Cname = A2.Cname AND A2.Sid = 'Jay' GROUP BY A1.Sid HAVING COUNT(DISTINCT (A1.Cname)) >= COUNT(DISTINCT (A2.Cname));
select distinct(sid) from apply where major='CS' AND sid NOT IN (select sid from apply where major='EE');
select cname from college c1 where exists (select * from college c2 where c1.state=c2.state and c1.cname!=c2.cname );
select cname from college where enrollment=(select max(enrollment)from college);
select sname from student where gpa=(select min(gpa)from student);
select major from apply where rownum=1 group by major order by count(*) desc ;

// assignment 6

alter table student add  DoB date;
update student set dob='24-Jul-97' where sid=456;
select round(avg(gpa),2) as "Average Gpa" from student;
select sname,extract(year from dob) from student where sizehs<1000;
select sname,extract(year from sysdate)-extract(year from dob) as "age" from student;
select upper(student.sname),lower(apply.cname) from student join apply on student.sid=apply.sid;
select substr(sname,4,1) from student;
select sid,sname from student where length(sname)>3;
select ceil(avg(gpa)),floor(avg(gpa)),trunc(avg(gpa),1) from student;
select sid from student where mod(sid,2)=0;
select sqrt(900),sqrt(24) from dual;
select('Peter Piper picked a peck of pickled peppers. A peck of pickled peppers Peter Piper picked. If Peter Piper picked a peck of pickled peppers, Where the peck of pickled peppers Peter Piper picked?','pick',1,6) from dual;


// aasignment 7

create view WeakStudent as select * from Student where GPA<3.7;
create view cView(collegeName,state,seats) as select * from College;
create view CSaccept as select sID,cName from Apply where major='CS' and decision='Y';
create view CSberkeley as select sID,sName,GPA,sizeHS from Student natural join CSaccept where cName='Berkeley' and sizeHS>500;
select * from CSberkeley natural join Student where GPA>3.8;
drop view CSaccept;
select sName from CSberkeley;
update WeakStudent set GPA=(GPA+0.8) where sizeHS> 1000;
create view AppCount(sID,NoOfApp) as select sID,count(major) from Student natural join Apply group by sID;
