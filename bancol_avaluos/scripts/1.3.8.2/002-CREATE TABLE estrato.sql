CREATE TABLE avaluos.estrato
(
   id serial, 
   nombre character varying(255), 
   CONSTRAINT pk_estrato PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE avaluos.estrato
  OWNER TO hibernate_login;