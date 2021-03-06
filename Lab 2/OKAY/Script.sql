--<ScriptOptions statementTerminator="!"/>

CREATE SCHEMA SOHAM140911090!

CREATE TABLE SOHAM140911090.STUDENT (
		REGNO NUMERIC(5 , 0) NOT NULL,
		NAME VARCHAR(5),
		DEPARTMENT VARCHAR(5)
	)
	DATA CAPTURE NONE!

CREATE TABLE SOHAM140911090.COURSE (
		REGNO NUMERIC NOT NULL,
		DEPT CHAR(5),
		SEM NUMERIC(5 , 0),
		COURSEID NUMERIC NOT NULL
	)
	DATA CAPTURE NONE!

ALTER TABLE SOHAM140911090.STUDENT ADD CONSTRAINT STUDENT_PK PRIMARY KEY
	(REGNO)!

ALTER TABLE SOHAM140911090.COURSE ADD CONSTRAINT COURSE_PK PRIMARY KEY
	(REGNO,
	 COURSEID)!

ALTER TABLE SOHAM140911090.COURSE ADD CONSTRAINT COURSE_STUDENT_FK FOREIGN KEY
	(REGNO)
	REFERENCES SOHAM140911090.STUDENT
	(REGNO)
	ON DELETE CASCADE!

