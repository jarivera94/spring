package com.helio4.bancol.avaluos.servicio.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    /**
     * <p>Formato de fecha y hora: yyyy-MM-dd HH:mm:ss.</p>
     * <p>Ejemplo: 2012-01-01 15:00:35.</p>
     */
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * <p>Formato de solo fecha: yyyy-MM-dd.</p>
     */
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * <p>Formato de solo fecha: yyyyMMdd.</p>
     */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    /**
     * <p>Formato de solo fecha: dd/MM/yyyy.</p>
     */
    public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    /**
     * <p>Formato de solo fecha: ddMMyyyy.</p>
     */
    public static final String DATE_FORMAT_DDMMYYYY = "ddMMyyyy";
    /**
     * <p>Formato de fecha: AAMMDD.</p>
     */
    public static final String DATE_FORMAT_AAMMDD = "yyMMdd";
    /**
     * <p>Formato de solo horas y minutos es formato de horas 0-23: HH:mm.</p>
     */
    public static final String TIME_FORMAT_HH_MM = "HH:mm";
    
    /**
     * <p>Formato de fecha: MM/yyyy.</p>
     */
    public static final String DATE_FORMAT_MMYYYY = "MM/yyyy";
    /**
     * <p>Formato de Hora: hh:mm a (Hora:minutos am/pm).</p>
     * <p>Ejemplo: 15:00 am.</p>
     */
    public static final String DATE_FORMAT_HHMMAA = "hh:mm a";

    /**
     * <p>
     * Procesa la fecha actual y la retorna en el formato indicado por el
     * parametro
     * <code>formato</code>.
     * </p>
     * <p>Si ocurre un error al convertir la fecha, se retorna nulo.</p>
     *
     * @param fecha fecha a convertir.
     * @param formato formato en el que se va a retornar la fecha. Ejemplo:
     * dd/MM/yyyy.
     * @return objeto de la clase <code>Date</code> con la fecha ingresada.
     */
    public static Date getFecha(String fecha, String formato) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat(formato);
        Date date;

        try {
            date = formatoDelTexto.parse(fecha);
        } catch (ParseException ex) {
            date = null;
        }

        return date;
    }

    /**
     * Interpreta la fecha dado un texto y un formato.
     * 
     * @param text Texto que define una fecha.
     * @param regex Formato en el que se debe leer el texto.
     * @return La fecha en {@link GregorianCalendar}.
     * @throws ParseException Cuando el texto de la fecha no cumple el formato
     */
    public static GregorianCalendar parseDate(String text, String regex) throws ParseException {
        if (text == null)
            return null;
        DateFormat df = new SimpleDateFormat(regex);
        Date date = df.parse(text);
        GregorianCalendar cal = new GregorianCalendar();//Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    /**
     * <p>
     * Procesa la fecha actual y la retorna en el formato indicado por el
     * parametro
     * <code>formato</code>.
     * </p>
     *
     * @param formato formato en el que se va a retornar la fecha. Ejemplo:
     * dd/MM/yyyy.
     * @return objeto de la clase <code>String</code> con la fecha actual
     * formateda.
     */
    public static String getFechaActual(String formato) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        String fecha = dateFormat.format(date);

        return fecha;
    }

    /**
     * <p>Transforma una fecha recibida al formato solicitado.</p>
     *
     * @param fecha objeto de la clase <code>java.util.Fecha</code> con el fecha
     * a formatear.
     * @param formato formato en el que se va a retornar la fecha. Ejemplo:
     * dd/MM/yyyy.
     * @return objeto de la clase <code>String</code> con la fecha actual
     * formateda.
     */
    public static String getFechaFormateada(Date fecha, String formato) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        String fechaStr = dateFormat.format(fecha);

        return fechaStr;
    }

    /**
     * <p>Transforma una fecha recibida al formato solicitado.</p>
     *
     * @param fecha objeto de la clase <code>java.util.Fecha</code> con el fecha
     * a formatear.
     * @param formato formato en el que se va a retornar la fecha. Ejemplo:
     * dd/MM/yyyy.
     * @return objeto de la clase <code>String</code> con la fecha actual
     * formateda.
     */
    public static Date getFechaFormateada(String formato, Date fecha) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        String fechaStr = dateFormat.format(fecha);

        return getFecha(fechaStr, formato);
    }

    /**
     * <p>Cambia de formato una fecha indicada.</p>
     * <p>
     * Si se presenta un error en el cambio de formato se retorna la fecha con
     * el formato original.
     * </p>
     *
     * @param fecha fecha a reformatear.
     * @param formatoActual formato de la fecha a cambiar.
     * @param nuevoFormato nuevo formato de fecha a usar.
     * @return objeto de la clase <code>String</code> con la fecha reformateda.
     */
    public static String getConvertFechaYYYYMMDD(String fecha, String formatoActual,
            String nuevoFormato) {
        String nuevaFecha;

        try {
            nuevaFecha = new SimpleDateFormat(nuevoFormato).format((new SimpleDateFormat(formatoActual)).parse(fecha));
        } catch (ParseException ex) {
            nuevaFecha = fecha;
        }

        return nuevaFecha;
    }
}
