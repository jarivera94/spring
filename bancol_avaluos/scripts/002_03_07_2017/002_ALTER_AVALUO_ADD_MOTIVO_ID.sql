-- agregar campo de motivo a avaluo
ALTER TABLE avaluos.avaluo ADD COLUMN motivo_id integer null;


ALTER TABLE avaluos.avaluo
  ADD CONSTRAINT foreing_key_motivo_avaluo FOREIGN KEY (motivo_id)
      REFERENCES avaluos.motivo (codigo) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;
