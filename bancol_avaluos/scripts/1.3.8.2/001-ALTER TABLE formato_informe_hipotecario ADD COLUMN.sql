ALTER TABLE avaluos.formato_informe_hipotecario ADD COLUMN fecha_remodelacion timestamp with time zone;
COMMENT ON COLUMN avaluos.formato_informe_hipotecario.fecha_remodelacion IS 'Fecha de remodelación';

ALTER TABLE avaluos.formato_informe_hipotecario ADD COLUMN licencia_construccion character varying(255);
COMMENT ON COLUMN avaluos.formato_informe_hipotecario.licencia_construccion IS 'Licencia de Construcción';

ALTER TABLE avaluos.formato_informe_hipotecario ADD COLUMN tipologia_vivienda_unica_familiar character varying(255);
COMMENT ON COLUMN avaluos.formato_informe_hipotecario.tipologia_vivienda_unica_familiar IS 'Tipología de vivienda única familiar';