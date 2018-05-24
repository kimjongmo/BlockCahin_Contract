SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS Contract_employment;
DROP TABLE IF EXISTS Job_kinds;
DROP TABLE IF EXISTS Member;




/* Create Tables */

CREATE TABLE Contract_employment
(
	category_num int NOT NULL,
	employer_business varchar(20),
	employer_rep varchar(20),
	employer_address varchar(100),
	employee_name varchar(20),
	employee_rrn int,
	employee_address varchar(100),
	employee_phone varchar(13),
	workplace varchar(100),
	contract_term varchar(50),
	salary int,
	working_hour varchar(20),
	PRIMARY KEY (category_num)
);


CREATE TABLE Job_kinds
(
	jk_num int NOT NULL,
	job_name varchar(20),
	PRIMARY KEY (jk_num)
);


CREATE TABLE Member
(
	mem_id varchar(20) NOT NULL,
	mem_pw varchar(64) NOT NULL,
	mem_name varchar(10) NOT NULL,
	mem_address varchar(100) NOT NULL,
	mem_phone varchar(13) NOT NULL,
	mem_email varchar(50) NOT NULL,
	PRIMARY KEY (mem_id)
);



