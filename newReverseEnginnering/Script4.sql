--<ScriptOptions statementTerminator="!"/>

CREATE TABLE EMPLOYEE090.EMPLOYEE (
		EMPLOYEE_NAME VARCHAR(20) NOT NULL,
		STREET CHAR(20),
		CITY VARCHAR(20)
	)
	DATA CAPTURE NONE 
	IN USERSPACE1!

ALTER TABLE EMPLOYEE090.EMPLOYEE ADD CONSTRAINT EMPLOYEE_PK PRIMARY KEY
	(EMPLOYEE_NAME)!
