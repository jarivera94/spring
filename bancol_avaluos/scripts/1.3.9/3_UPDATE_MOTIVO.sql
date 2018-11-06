update avaluos.motivo set grupo_motivo = 'Garantías' where codigo in (11361,11360,11347,11342);
update avaluos.motivo set grupo_motivo = 'Otro' where codigo in (11357);
update avaluos.motivo set grupo_motivo = 'Hipotecario' where codigo in (11351,11332,11401,11403,11346,11345,11343,11344);
update avaluos.motivo set grupo_motivo = 'Dación en Pago' where codigo in (11349, 11359);
update avaluos.motivo set grupo_motivo = 'Constructor' where codigo in (11350,11354,11352,11353,11355,11356);
update avaluos.motivo set grupo_motivo = 'Comercial' where codigo in (11335,11337,11338,11362,11364);
update avaluos.motivo set grupo_motivo = 'Comercial' where codigo in (11336,11348,11402);
update avaluos.motivo set grupo_motivo = 'Remates' where codigo in (11358);
update avaluos.motivo set grupo_motivo = 'Leasing' where codigo in (11412);
update avaluos.motivo set grupo_motivo = 'NIIF' where codigo in (11365,11363);
update avaluos.motivo set empresa = 'Bancol' where codigo in (11412,11358, 11361,  11360,  11347,  11342,  11357,  11351,  11332,  11401,  11403,  11346,  11345,  11344,  11359,  11349,  11350,  11354,  11352,  11353,  11355,  11356,  11335,  11336,  11348,  11402);
update avaluos.motivo set nombre ='Avalúo inmueble NIIF', abreviatura = 'AV.NIIF', grupo_motivo = 'NIIF', prefijo ='Avalúo inmueble NIFF' where codigo = 13363;
update avaluos.motivo set nombre ='Avalúo comercial MYE',  abreviatura = 'AV.COM.MYE', grupo_motivo = 'Comercial', prefijo ='Avalúo comercial MYE' where codigo = 13365;
update avaluos.motivo set nombre ='Avalúo MYE NIIF',  abreviatura = 'AV.NYN NIIF', grupo_motivo = 'NIIF', prefijo ='Avalúo MYE NIIF' where codigo = 13364;
update avaluos.motivo set abreviatura = 'CH.COM' where codigo = 11337;
update avaluos.motivo set abreviatura = 'CH.COM' where codigo = 11338;
update avaluos.motivo set abreviatura = 'CH' where codigo = 11343;
update avaluos.motivo set abreviatura = 'CH' where codigo = 11345;