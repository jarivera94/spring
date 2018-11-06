delete
from avaluos.usuario_tipo_avaluo uta
where (uta.tipo_documento_identificacion
    || '_'
    || uta.numero_documento
    || '_'
    || uta.tipo_avaluo) in (select u.tipo_documento_identificacion
                                || '_'
                                || u.numero_documento
                                || '_'
                                ||  uta.tipo_avaluo
				from avaluos.usuario u,
				     avaluos.usuario_tipo_avaluo uta
				where u.tipo_documento_identificacion = uta.tipo_documento_identificacion
				  and u.numero_documento = uta.numero_documento
				  and uta.tipo_avaluo = 1 -- tipo de avaluo hipotecario
				  and u.rol_id in (10,9)); -- roles de abogado y coordinador de abogado
