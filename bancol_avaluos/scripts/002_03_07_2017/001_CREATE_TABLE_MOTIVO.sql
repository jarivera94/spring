Create Table avaluos.motivo (
    codigo integer primary key,
    nombre  character varying(75),
    abreviatura character varying(25),
    prefijo character varying(75),
    estado boolean,
    entidad_id integer
)
WITH (
  OIDS=FALSE
);

ALTER TABLE avaluos.motivo
  ADD CONSTRAINT foreing_key_motivo_entidad FOREIGN KEY (entidad_id)
      REFERENCES avaluos.entidad (entidad_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE avaluos.motivo
  OWNER TO hibernate_login;
