CREATE TABLE employee (
  employeeid bigint(20) NOT NULL AUTO_INCREMENT,
  employeename varchar(255) DEFAULT NULL,
  PRIMARY KEY (employeeid)
);

insert into employee (employeeid, employeename) values('1','Rockey');
insert into employee (employeeid, employeename) values('2','Jose');