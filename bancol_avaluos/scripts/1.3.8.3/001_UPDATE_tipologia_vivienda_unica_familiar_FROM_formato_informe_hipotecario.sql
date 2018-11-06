update avaluos.formato_informe_hipotecario 
   set tipologia_vivienda_unica_familiar = 'Casa - Continua' 
 where tipologia_vivienda_unica_familiar = 'Adosada';

 
update avaluos.formato_informe_hipotecario 
   set tipologia_vivienda_unica_familiar = 'Casa -  Aislada' 
 where tipologia_vivienda_unica_familiar = 'Aislada';