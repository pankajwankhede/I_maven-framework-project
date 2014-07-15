DROP TABLE employee;

CREATE TABLE employee (
  ID INT(10) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(100) NOT NULL,
  AGE INT(10) NOT NULL,
  PRIMARY KEY (ID)
);

DROP TABLE customer;

CREATE TABLE customer (
  CUST_ID INT(10) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(100) NOT NULL,
  AGE INT(10) NOT NULL,
  PRIMARY KEY (CUST_ID)
);

DROP TABLE address;
CREATE TABLE address (
  id INT(10) NOT NULL AUTO_INCREMENT,
  cust_id INT(10),
  street VARCHAR(255),
  city VARCHAR(255),
  PRIMARY KEY(id),
  CONSTRAINT address_customer_ref
  FOREIGN KEY (cust_id) REFERENCES customer (cust_id)
);

DROP TABLE contact;

CREATE TABLE contact (
  contact_id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  email varchar(45) NOT NULL,
  address varchar(45) NOT NULL,
  telephone varchar(45) NOT NULL,
  PRIMARY KEY (contact_id)
);


-- 存储过程

DELIMITER //
CREATE PROCEDURE read_customer ( 
  IN in_id INT, 
  OUT out_name VARCHAR(100), 
  OUT out_age INT)
BEGIN 
  SELECT NAME, age 
  INTO out_name, out_age 
  FROM customer WHERE CUST_ID = in_id;
END//

-- 存储过程

DELIMITER //
CREATE PROCEDURE read_all_customers()
BEGIN
SELECT a.CUST_ID, a.NAME, a.AGE FROM customer a;
END//

-- 函数

DELIMITER //
CREATE FUNCTION get_customer_name (in_id INT)
RETURNS VARCHAR(50) READS SQL DATA 
BEGIN
  declare out_name varchar(50);
  select name into out_name from customer where CUST_ID = in_id;
  RETURN out_name;
END//

