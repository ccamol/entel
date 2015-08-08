/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.esa.ponline.portalbolsas.configuration.properties.LoadProperty;

//import com.bea.content.Node;
//import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 *
 */
public class DateHelper {

    /**
     * Formato de fecha completo, con separador de guiones: dd-MM-yyyy
     */
    public static final String FORMAT_ddMMyyyy_HYPHEN = "dd-MM-yyyy";

    /**
     * Formato de fecha completo, con separador de slash: dd/MM/yyyy
     */
    public static final String FORMAT_ddMMyyyy_SLASH = "dd/MM/yyyy";
    
    /**
     * Formato de fecha date + time, date con separador de slash, espacio  y formato time con
     * separador ':' : dd/MM/yyyy HH:mm:ss
     */
    public static final String FORMAT_ddMMyyyy_HHmmss_SLASH = "dd/MM/yyyy HH:mm:ss";

    /**
     * Formato de fecha date + time, date con separador de slash, espacio  y formato time con
     * separador ':' : dd/MM/yyyy HH:mm
     */
    public static final String FORMAT_ddMMyyyy_HHmm_SLASH = "dd/MM/yyyy HH:mm";


    /**
     * Formato de fecha completo, sin separador: ddMMyyyy
     */
    public static final String FORMAT_ddMMyyyy = "ddMMyyyy";

    /**
     * Formato de fecha completo, con separador de guiones: yyyy-MM-dd
     */
    public static final String FORMAT_yyyyMMdd_HYPHEN = "yyyy-MM-dd";

    /**
     * Formato de fecha completo, con separador de slash: yyyy/MM/dd
     */
    public static final String FORMAT_yyyyMMdd_SLASH = "yyyy/MM/dd";

    /**
     * Formato de fecha completo, sin separador: yyyyMMdd
     */
    public static final String FORMAT_yyyyMMdd = "yyyyMMdd";

    /**
     * Formato de fecha date + time, sin separador
     */
    public static final String FORMAT_yyyyMMddhhmmss = "yyyyMMddhhmmss";

    /**
     * Formato de fecha date + time, separadas con espacio y formato time con
     * separador ':'
     */
    public static final String FORMAT_yyyyMMdd_HHmmss = "yyyyMMdd HH:mm:ss";


    /**
     * Formato de fecha completo, con separador de slash: MM/yyyy
     */
    public static final String FORMAT_MMyyyy_SLASH = "MM/yyyy";
    
    /**
     * Formato de fecha date + time, date con separador de slash, espacio  y formato time con
     * separador ':' : dd-MM-yyyy HH:mm:ss
     */
    public static final String FORMAT_ddMMyyyy_HHmmss_HYPHEN = "dd-MM-yyyy HH:mm:ss";
    
    
    /**
     * Formato de fecha date + time, date con separador de slash, espacio  y formato time con
     * separador ':' : yyyy-MM-dd HH:mm:ss
     */
    public static final String FORMAT_yyyyMMdd_HHmmss_HYPHEN = "yyyy-MM-dd HH:mm:ss";

    
    /**
     * Formato de fecha completo, dias y mes en letras dd MMMM
     */
    public static final String FORMAT_DD_MMMM="dd MMMM";
    
    
    /**
     * Formato de fecha completo, dias , horas en letras , horas y minutos dd MMMM HH:mm
     */
    public static final String FORMAT_DD_MMMM_HHmm="dd MMMM HH:mm";
    
    /**
     * Formato de fecha date + time 24 horas , sin separador
     */
    public static final String FORMAT_yyyyMMddhhmmss24HORAS = "yyyyMMddHHmmss";
    
    
    /**
     * Formato de fecha dia ano en mes letras y en ano. dd-MMM-yyyy
     */
    public static final String FORMAT_ddMMMyyyy_HHmmss_HYPHEN = "dd-MMM-yyyy";
    

    /**
     * Parseador de fechas.<br>
     * Los formatos mas utilizados pueden ser encontrados como constantes de
     * esta clase.
     *
     * @param date
     *            String con la fecha a parsear
     * @param format
     *            formato de fecha en que esta el String <code>date</code>
     * @return {@link Date} con la fecha parseada. <code>null</code> si la fecha
     *         no esta en el formato correcto o no es valida
     */
    public static Date parseDate(String date, String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Entrega el nombre del mes de una fecha entregada como String.
     *
     * @param date
     *            String fecha de quien se desea nombre del mes
     * @param format
     *            String formato en que esta la fecha <code>date</code>
     * @return String nombre del mes o null si <code>date</code> no se ajusta al
     *         formato de <code>format</code>
     */
    public static String getMonthName(String date, String format) {

        Date parsedDate = parseDate(date, format);
        if (parsedDate == null) {
            return null;
        }
        return getMonthName(parsedDate);

    }

    /**
     * Entrega el nombre del mes de una fecha
     *
     * @param date
     *            fecha de quien se desea el nombre del mes
     * @return String nombre del mes.
     */
    public static String getMonthName(Date date) {
        return format(date, "MMM");
    }

    /**
     * Entrega un String con la fecha <code>date</code> en el formato indicado
     * en <code>format</code>
     *
     * @param date
     *            {@link Date} fecha a formatear
     * @param format
     *            String formato de la fecha
     * @return String con fecha <code>date</code> en formato <code>format</code>
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, new Locale("es","CL"));
        return sdf.format(date);
    }

    /**
     * Entrega un objeto Date restando o sumandole la cantidad de dias especificado
     * @param fecha {@link Date} fecha a la que se le sumaran o restaran los dias
     * @param dias Dias a sumar o restar
     * @return Objeto {@link Date}
     */
    public static Date addDays(Date fecha, int dias) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(fecha.getTime());

        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    /**
     * Entrega un objeto Date al cual se le restara el n de meses indicado.
     * @param fecha {@link Date} fecha a la que se le restara los meses.
     * @param mes Numero de meses a restar, ejemplo -2 para restar 2 meses.
     * @return
     */
    public static Date fechaRestaMes(Date fecha,int mes){
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(fecha.getTime());
        calendar.add(Calendar.MONTH, mes);

        return new Date(calendar.getTimeInMillis());
    }


    /**
     * Entrega el ano y el mes para luego calcular el ultimo dia del mes.
     * Se crea un GregorianCalendar con el dia 1 del mes y se utiliza la funcion getActualMaximum.
     * @param ano
     * @param mes
     * @return ultimo dia de un mes.
     */
    public static int ultimoDiaMes(int ano, int mes){
    	Calendar calendar = GregorianCalendar.getInstance();
    	calendar.set(ano,(mes-1), 1);
        return  calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
    }
    
    /**
     * Entrega el nombre del mes dado el mes en entero.         
     * @param mes
     * @return nombre mes (Enero,Febrero..).
     */
    public static String nombreMes(int mes){
    	String nombresMes="";    	 
    	 try{
    		 nombresMes = LoadProperty
    	        .getProperty("parametro.mes."+mes); 		            	   
		   }catch(Exception e){
			   return  nombresMes;      	
	      }		  
        return  nombresMes;
    }

    /**
     * Entrega un String con la fecha del primer dia del proximo mes en el formato indicado
     * en <code>formato</code>
     *
     * @param formato
     *            String formato de la fecha
     * @return String con fecha del primer dia del proximo mes en formato <code>formato</code>
     */
    public static String primerDiaProximoMes(String formato){
    	Calendar calendar = GregorianCalendar.getInstance();
    	calendar.setLenient(true);
    	calendar.add(Calendar.MONTH, 1);
    	calendar.set(Calendar.DAY_OF_MONTH, 1);
    	return format(new Date(calendar.getTimeInMillis()), formato);
    }
    
    /**
     * Entrega un String con la fecha del dia de manana en el formato indicado
     * en <code>formato</code>
     *
     * @param formato
     *            String formato de la fecha
     * @return String con fecha del primer dia del proximo mes en formato <code>formato</code>
     */
    public static String manana(String formato){
    	Calendar calendar = GregorianCalendar.getInstance();
    	calendar.setLenient(true);
    	calendar.add(Calendar.DAY_OF_MONTH, 1);
    	return format(new Date(calendar.getTimeInMillis()), formato);
    }
    
    /**
     * Compara dos fechas y determina si corresponden al mismo dia del anio.
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2){
    	Calendar cal1 = Calendar.getInstance();
    	Calendar cal2 = Calendar.getInstance();
    	cal1.setTime(date1);
    	cal2.setTime(date2);
    	boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
    	                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    	return sameDay;
    }
    

}
