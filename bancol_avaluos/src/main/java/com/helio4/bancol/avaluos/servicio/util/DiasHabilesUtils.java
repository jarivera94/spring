package com.helio4.bancol.avaluos.servicio.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DiasHabilesUtils {
	
	/**
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param horasLaborales, numero de horas que se trabajan en un dia.
	 * 
	 * @return días hábiles en milesegundos 
	 * */
	public static Long getDiasHabilesMS(Date fechaInicial, Date fechaFinal, int horasLaborales) {
		Calendar fechaInicialCalendar = Calendar.getInstance();
		fechaInicialCalendar.setTime(fechaInicial);
		
		Calendar fechaFinalCalendar = Calendar.getInstance();
		fechaFinalCalendar.setTime(fechaFinal);		
		int diasHabiles = DiasHabilesUtils.getDiasHabiles(fechaInicialCalendar, fechaFinalCalendar);
		return (long) (diasHabiles*horasLaborales*60*60*1000);
	}
	
	/**
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param horasLaborales, numero de horas que se trabajan en un dia.
	 * 
	 * @return días hábiles en milesegundos 
	 * */
	public static Long getDiasHabilesMS(Calendar fechaInicial, Calendar fechaFinal, int horasLaborales) {
		int diasHabiles = DiasHabilesUtils.getDiasHabiles(fechaInicial, fechaFinal);
		return (long) (diasHabiles*horasLaborales*60*60*1000);
	}
	/**
	 * Obtiene los días hábiles entre dos fechas.
	 * 
	 * @param fechaInicial 
	 * @param fechaFinal
	 * @return numero de dias.
	 * **/
    public static  int getDiasHabiles(Calendar fechaInicial, Calendar fechaFinal) {
        int diffDays= 0;
        List<Date> diasfestivos = DiasHabilesUtils.getDiasFestivos(fechaInicial.getTime(), fechaFinal.getTime());
        //mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias
        while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {
            //si el dia de la semana de la fecha minima es diferente de sabado o domingo
            if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY 
             && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && !DiasHabilesUtils.esFestivo(diasfestivos, fechaInicial.getTime()) ) {
                //se aumentan los dias de diferencia entre min y max
                diffDays++;
            }
            //se suma 1 dia para hacer la validacion del siguiente dia.
            fechaInicial.add(Calendar.DATE, 1);
        }
        return diffDays;    
    }
    
	/**
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param horasLaborales, numero de horas que se trabajan en un dia.
	 * 
	 * @return días NO hábiles en milesegundos 
	 * */
	public static Long getDiasNoHabilesMS(Calendar fechaInicial, Calendar fechaFinal, int horasLaborales) {
		int diasNoHabiles = DiasHabilesUtils.getDiasNoHabiles(fechaInicial, fechaFinal);
		return (long) (diasNoHabiles*horasLaborales*60*60*1000);
	}
	
	/**
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param horasLaborales, numero de horas que se trabajan en un dia.
	 * 
	 * @return días NO hábiles en milesegundos 
	 * */
	public static Long getDiasNoHabilesMS(Date fechaInicial,Date fechaFinal, int horasLaborales) {
		Calendar fechaInicialCalendar = Calendar.getInstance();
		fechaInicialCalendar.setTime(fechaInicial);
		
		Calendar fechaFinalCalendar = Calendar.getInstance();
		fechaFinalCalendar.setTime(fechaFinal);
		
		int diasNoHabiles = DiasHabilesUtils.getDiasNoHabiles(fechaInicialCalendar, fechaFinalCalendar);
		return (long) (diasNoHabiles*horasLaborales*60*60*1000);
	}
	
	/**
	 * Obtiene los días hábiles entre dos fechas.
	 * 
	 * @param fechaInicial 
	 * @param fechaFinal
	 * @return numero de dias.
	 * **/
    public static  int getDiasNoHabiles(Calendar fechaInicial, Calendar fechaFinal) {
        int diffDays= 0;
        List<Date> diasfestivos = DiasHabilesUtils.getDiasFestivos(fechaInicial.getTime(), fechaFinal.getTime());
        //mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias
        while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {
            //si el dia de la semana de la fecha minima es diferente de sabado o domingo
            if (fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY 
             && fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && DiasHabilesUtils.esFestivo(diasfestivos, fechaInicial.getTime()) ) {
                //se aumentan los dias de diferencia entre min y max
                diffDays++;
            }
            //se suma 1 dia para hacer la validacion del siguiente dia.
            fechaInicial.add(Calendar.DATE, 1);
        }
        return diffDays;    
    }
    
    /**
     * Verifica si una fecha es festiva.
     * @param festivos, 
     * @param fecha
     * @return boolean, estado de la verificación
     * */
	public static boolean esFestivo(List<Date> festivos,Date fecha) {
		return festivos.contains(fecha);
	}
	/**
	 * Obtiene una lista de dias festivos entre dos fechas.
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return Lista de fechas festivas.
	 * */
	public static List<Date> getDiasFestivos(Date fechaInicial, Date fechaFinal){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		
		Integer ad = new Integer(format.format(fechaInicial).substring(6));
		Integer ah = new Integer(format.format(fechaFinal).substring(6));
		
		String[] fijos = {"01/01","01/05","20/07","07/08","08/12","25/12"};
		String[] primerLunes = {"06/01","19/03","29/06","15/08","12/10","01/11","11/11"};
		List<Date> festivos = new ArrayList<Date>();
		//dia en milisegundos
		long dia = 1000*60*60*24;
		
		try {
			for(int i=ad; i<=ah; i++){
				Date dobleFestivo = null;
				for(int j=0; j<fijos.length; j++){
					Date festivo = format.parse(fijos[j]+"/"+i);
					festivos.add(festivo);
				}
				
				for(int j=0; j<primerLunes.length; j++){
					String texto = primerLunes[j]+"/"+i;
					
					Date festivo = format.parse(texto);
					if(j==2){
						dobleFestivo = festivo;
					}
					
					long diaSemana = diaSemana(festivo);
					if(diaSemana==1){
						festivo.setTime(festivo.getTime()+1*dia);
						festivos.add(festivo);
					}else if(diaSemana>2){
						long t = (9-diaSemana)*dia;
						festivo.setTime(festivo.getTime()+t);
						festivos.add(festivo);
					}else{					
						festivos.add(festivo);
					}
				}
				
				Date pascua = getPascua(i);
				
				festivos.add(new Date(pascua.getTime()-( 3*dia)));
				festivos.add(new Date(pascua.getTime()-( 2*dia)));
				festivos.add(new Date(pascua.getTime()+(43*dia)));
				festivos.add(new Date(pascua.getTime()+(64*dia)));
				
				if(!((new Date(pascua.getTime()+(71*dia))).equals(dobleFestivo))){
					festivos.add(new Date(pascua.getTime()+(71*dia)));
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return festivos;
	}
	
	public static Date getPascua(int anno){
		//( entre los años 1900 y 2099)
		int M=24;
	    int N=5;	    
	    int a,b,c,d,e,dia,mes;
	    //Cálculo de residuos
	    a = anno % 19;
	    b = anno % 4;
	    c = anno % 7;
	    d = (19*a + M) % 30;
	    e = (2*b+4*c+6*d + N) % 7;
	    // Decidir entre los 2 casos:
	    if (d+e < 10)  {
	        dia = d+e+22;
	        mes = Calendar.MARCH;
	    }else{
	        dia = d+e-9;
	        mes = Calendar.APRIL;
	    }
	    // Excepciones especiales
	    if (dia == 26  && mes == Calendar.APRIL)
	        dia = 19;
	    if (dia == 25 && mes == Calendar.APRIL && d==28 && e == 6 && a >10)
	        dia = 18;
	 
	    Calendar fechaPascua = Calendar.getInstance();
	    fechaPascua.clear();
	    fechaPascua.set(anno, mes, dia, 0, 0, 0);	    
		return fechaPascua.getTime();
	}
	
	public static long diaSemana(Date fecha){
		Date date = new Date();		
		long dia = 1000*60*60*24;
		long fraccion = (fecha.getTime())/dia;
		
		if(((fecha.getTime())%dia)>0){
			date.setTime((dia*fraccion)/*+1000*60*60*5*/);
		}	
		return (((((date.getTime())/dia)+4)%7)+1);
	}
	
	
}
