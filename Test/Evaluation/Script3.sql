--<ScriptOptions statementTerminator="!"/>

CREATE TABLE Schema.ORDER090 (
		ID NUMERIC(5 , 0) NOT NULL,
		ORDER_DATE NUMERIC(5 , 0),
		ORDER_NUMBER NUMERIC(5 , 0),
		TOTAL_AMT NUMERIC(5 , 0),
		CUSTOMER_ID NUMERIC(5 , 0)
	)
	DATA CAPTURE NONE!

ALTER TABLE Schema.ORDER090 ADD CONSTRAINT ORDER090_PK PRIMARY KEY
	(ID)!

ALTER TABLE Schema.ORDER090 ADD CONSTRAINT ORDER090_CUSTOMER090_FK FOREIGN KEY
	(CUSTOMER_ID)
	REFERENCES Schema.CUSTOMER090
	(ID)
	ON DELETE CASCADE!

