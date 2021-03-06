--<ScriptOptions statementTerminator="!"/>

CREATE SCHEMA STUDENTENROLLMENT090!

CREATE TABLE STUDENTENROLLMENT090.Department (
		deptid NUMERIC(2 , 0) NOT NULL,
		dname VARCHAR(50),
		location VARCHAR(50)
	)
	DATA CAPTURE NONE!

CREATE TABLE STUDENTENROLLMENT090.Student (
		snum NUMERIC(11 , 0) NOT NULL,
		sname VARCHAR(50) NOT NULL,
		deptid NUMERIC(2 , 0) NOT NULL,
		slevel NUMERIC(2 , 0) NOT NULL,
		age NUMERIC(2 , 0) NOT NULL
	)
	DATA CAPTURE NONE!

CREATE TABLE STUDENTENROLLMENT090.Faculty (
		fid NUMERIC(2 , 0) NOT NULL,
		fname VARCHAR(50) NOT NULL,
		deptid NUMERIC(2 , 0) NOT NULL
	)
	DATA CAPTURE NONE!

CREATE TABLE STUDENTENROLLMENT090.Class (
		cname VARCHAR(50) NOT NULL,
		time VARCHAR(50) NOT NULL,
		room NUMERIC(4 , 0) NOT NULL,
		fid NUMERIC(2 , 0) NOT NULL
	)
	DATA CAPTURE NONE!

CREATE TABLE STUDENTENROLLMENT090.Enrolled (
		snum NUMERIC(11 , 0) NOT NULL,
		cname VARCHAR(50) NOT NULL,
		grade CHAR(5)
	)
	DATA CAPTURE NONE!

ALTER TABLE STUDENTENROLLMENT090.Department ADD CONSTRAINT Department_PK PRIMARY KEY
	(deptid)!

ALTER TABLE STUDENTENROLLMENT090.Student ADD CONSTRAINT Student_PK PRIMARY KEY
	(deptid,
	 snum)!

ALTER TABLE STUDENTENROLLMENT090.Faculty ADD CONSTRAINT Faculty_PK PRIMARY KEY
	(deptid,
	 fid)!

ALTER TABLE STUDENTENROLLMENT090.Class ADD CONSTRAINT Class_PK PRIMARY KEY
	(fid,
	 cname)!

ALTER TABLE STUDENTENROLLMENT090.Enrolled ADD CONSTRAINT Enrolled_PK PRIMARY KEY
	(snum,
	 cname)!

ALTER TABLE STUDENTENROLLMENT090.Student ADD CONSTRAINT Student_Department_FK FOREIGN KEY
	(deptid)
	REFERENCES STUDENTENROLLMENT090.Department
	(deptid)
	ON DELETE CASCADE!

ALTER TABLE STUDENTENROLLMENT090.Faculty ADD CONSTRAINT Faculty_Department_FK FOREIGN KEY
	(deptid)
	REFERENCES STUDENTENROLLMENT090.Department
	(deptid)
	ON DELETE CASCADE!

ALTER TABLE STUDENTENROLLMENT090.Class ADD CONSTRAINT Class_Faculty_FK FOREIGN KEY
	(fid)
	REFERENCES STUDENTENROLLMENT090.Faculty
	(deptid,
	 fid)
	ON DELETE CASCADE!

ALTER TABLE STUDENTENROLLMENT090.Enrolled ADD CONSTRAINT Enrolled_Student_FK FOREIGN KEY
	(snum)
	REFERENCES STUDENTENROLLMENT090.Student
	(deptid,
	 snum)
	ON DELETE CASCADE!

ALTER TABLE STUDENTENROLLMENT090.Enrolled ADD CONSTRAINT Enrolled_Class_FK FOREIGN KEY
	(cname)
	REFERENCES STUDENTENROLLMENT090.Class
	(fid,
	 cname)
	ON DELETE CASCADE!

