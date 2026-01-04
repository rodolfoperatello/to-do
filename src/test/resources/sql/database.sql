-- desativa FKs temporariamente
SET REFERENTIAL_INTEGRITY FALSE;

-- limpa tabelas (a ordem não importa)
DELETE FROM TASK;

-- reinicia ID auto-increment manualmente
ALTER TABLE TASK ALTER COLUMN ID RESTART WITH 1;

-- reativa FKs
SET REFERENTIAL_INTEGRITY TRUE;
