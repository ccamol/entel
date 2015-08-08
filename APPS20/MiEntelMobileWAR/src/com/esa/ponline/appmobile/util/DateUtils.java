package com.esa.ponline.appmobile.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.esa.ponline.appmobile.constants.Constants;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class DateUtils {
	private static final Logger LOGGER = Logger.getLogger(DateUtils.class);
	
    //public static final String FORMAT_ddMMyyyy_HYPHEN = "dd-MM-yyyy";
    //public static final String FORMAT_AlDiaMes = "'al' dd 'de' MMMM";
    //public static final String FORMAT_AlasHora = "'a las' HH:mm 'hrs.'";
    //public static final String FORMAT_AlDiaMesHora = "'al' dd 'de' MMMM HH:mm";
    //public static final String FORMAT_MesYear = "MMMM yyyy";
    //public static final String FORMAT_DiaMesYearGion = "dd-MM-yy";
    //public static final String FORMAT_DiaMesYearBE = "dd/MM/yyyy";
    //public static final String FORMAT_DiaMesYearHoraBE = "dd/MM/yyyy HH:mm:ss";
	
	public static final String DIAMESYEARBE = "dd/MM/yyyy";
	public static final String ALDIAMES = "'al' dd 'de' MMMM";
	public static final String DIAMESYEARHORABE = "dd/MM/yyyy HH:mm:ss";
	public static final String ALASHORA = "'a las' HH:mm 'hrs.'";
	public static final String ALDIAMESHORA = "'al' dd 'de' MMMM HH:mm";
    public static final String FORMAT_yyyyMMdd_HYPHEN = "yyyy-MM-dd";
	
	public static String formatFecha(String txtFecha) throws Exception {
		
		SimpleDateFormat formatFecha = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat formatFecha2 = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
		 java.util.Date fecha  = formatFecha.parse(txtFecha);
		 return formatFecha2.format(fecha);		 
		 
	}

	// 07/07/2015 01:25:00
    public static String formatFechaMMAG(String txtFecha) throws Exception {
        
//        SimpleDateFormat formatFecha = new SimpleDateFormat("ddMMyyyy HHmmss");
        SimpleDateFormat formatFecha2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        java.util.Date fecha  = formatFecha2.parse(txtFecha);
        return formatFecha2.format(fecha);      
         
    }	
	
	public static String formatFechaString(String fecha,String formatoOriginal,String formato){
    	try {
            DateFormat formatter = new SimpleDateFormat(formatoOriginal, new Locale("es","CL"));
            Date date = (Date)formatter.parse(fecha);
            SimpleDateFormat sdf = new SimpleDateFormat(formato, new Locale("es","CL"));
            return sdf.format(date);
        } catch (Exception e) {
        	LOGGER.error("Error formateando la fecha: " + e.getMessage());
        	return "";   
        }
    }
	
	public static String formatoSeparacionFecha(String fecha) {
		if(!fecha.equals("")){
		String fechaYear = fecha.substring(0, 4);
		String fechaMonth = fecha.substring(4, 6);
		String fechaDay = fecha.substring(6, 8);
		String fechaFormateada = 
				fechaDay+Config.getAppProperty(Constants.SEPARACION_FECHA.getStringValue())+
				fechaMonth+Config.getAppProperty(Constants.SEPARACION_FECHA.getStringValue())+
				fechaYear;
		return fechaFormateada;
		}else{
			return "";
		}
	}
	
    public static String obtenerFecha(){
		Calendar fechaActual = Calendar.getInstance();
		SimpleDateFormat formatFecha = new SimpleDateFormat("yyyyMMdd");
		return formatFecha.format(fechaActual.getTime());
	}
    
	public static String obtenerFechaActual() throws Exception {
		Calendar fechaActual = Calendar.getInstance();
		SimpleDateFormat formatFecha = new SimpleDateFormat("yyyyMMdd");
		return formatFecha.format(fechaActual.getTime());
	}
	
	public static String obtenerHoraFechaActual() throws Exception {
		Calendar fechaActual = Calendar.getInstance();
		SimpleDateFormat formatFecha = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatFecha.format(fechaActual.getTime());
	}
	
    public static Date parseDate(String date, String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

	public static String formatoSeparacionFechaReservado(String fechaSaldoReservado) {
		if(!fechaSaldoReservado.equals("")){
			String firstWord = null;
			if(fechaSaldoReservado.contains(" ")){
			   firstWord = fechaSaldoReservado.substring(0, fechaSaldoReservado.indexOf(" ")); 
			}
			
		String[] fechaYearMonthDay = firstWord.split("-");
		String fechaYear = fechaYearMonthDay[0];
		String fechaMonth = fechaYearMonthDay[1];
		String fechaDay = fechaYearMonthDay[2];
		
//		String fechaYear = fechaSaldoReservado.substring(0, 4);
//		String fechaMonth = fechaSaldoReservado.substring(4, 6);
//		String fechaDay = fechaSaldoReservado.substring(6, 8);
		String fechaFormateada = 
				fechaDay + Config.getAppProperty(Constants.SEPARACION_FECHA.getStringValue())+
				fechaMonth + Config.getAppProperty(Constants.SEPARACION_FECHA.getStringValue())+
				fechaYear;
		return fechaFormateada;
		}else{
			return "";
		}
	}
}
