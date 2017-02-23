--<ScriptOptions statementTerminator="!"/>

CREATE SCHEMA Schema2!

CREATE TABLE Schema2.TestTable (
		Number NUMERIC NOT NULL
	)
	DATA CAPTURE NONE!

ALTER TABLE Schema2.TestTable ADD CONSTRAINT TestTable_PK PRIMARY KEY
	(Number)!

