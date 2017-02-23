--<ScriptOptions statementTerminator="!"/>

CREATE SCHEMA Schema!

CREATE TABLE Schema.TestTable (
		Number NUMERIC NOT NULL
	)
	DATA CAPTURE NONE!

ALTER TABLE Schema.TestTable ADD CONSTRAINT TestTable_PK PRIMARY KEY
	(Number)!

