/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.portalbolsas.constants;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public enum Constants {
	
	INICIO("inicio"), 
	LOGIN_OK("loginOk"),
	MEDIUM_RES("webmobileMedium"),
	ERROR_LOGIN("errorLogin"),
	ERROR_PASSWD("errorPasswd"),
	ERROR_RESOLUTION("resolucion_no_encontrada"),
	ERROR("error"), 
	ERROR_GRAL("errorGeneral"),
	ERROR_SESION("error_sesion"), 
	STRING_VACIO(""),
	MSISDN("msisdn"),
	MERCADO("mercado"),
	CLIENTE("cliente"),
	CLIENTE_REGISTRO("cliente_registro"),
	CLIENTE_REGISTRO_SIGNINLOGIN("cliente_registro_signin"),
	RUT("rut"),
	USUARIO("usuario"),
	TEMPLATE("template"),
	PASSWORD("password"),
	GET_PASS("getpass"),
	GET_PASS_OK("getpassok"),
	GET_PASS_NOK("getpassnok"),
	WEB_MOBILE("webmobile"),
	APP_MOBILE("appmobile"),
	BACK_SLASH("\\"),
	CERRAR_SESION("cerrarSesion"),
	BIENVENIDA("bienvenida"),
	ID_SESION("idSesion"),
	OK("OK"),
	NOK("NOK"),
	MINUTOS("minutos"),
	SMS("sms"),
	BAM("bam"),
	BRRY("brry"),
	WAP("wap"),
	NET_MOVIL("netmovil"),
	URL_OBTIENE_CLAVE("url.solicitudClave.mientel"),
	ESTADO_TOKEN_OK("estadoTokenOK"),
	ESTADO_TOKEN_NOK("estadoTokenNOK"),
	MZZO_KEY_I("mzzoKeyI"),
	MZZO_KEY_II("mzzoKeyII"),
	MEGAS(" MB"),
	URL_WEBPAY("http://m.entel.cl/movil/appmanager/movil/sitio?_nfpb=true&_pageLabel=P69800258881375760051009&recarga=WebPay&msisdn="),
	
	/**
	 * Formatos
	 */
	SEPARACION_FECHA("separacionFecha"),
	DOLLAR_SIGN("formatPrecio"),
	
	/**
	 * Facturacion Electronica
	 */
	CODIGO_RESPUESTA_OK("servicios.codigoRespuesta.exito"),
	CODIGO_YA_EXISTE("facturacionElectronica.existe"),
	ID_SISTEMA_FACTURACION_ELECTRONICA("facturacionElectronica.idsistema"),
	TIPO_INSCRIPCION("facturacionElectronica.tipoOperacion.inscripcion"),
	TIPO_ACTUALIZACION("facturacionElectronica.tipoOperacion.actualizacion"),
	TIPO_OPERACION("facturacionElectronica.tipoOperacion.inscripcion"),

	
	
	/**
	 * Mapping
	 */
	TRAFICO("trafico"),
	
	
	/**
	 * PLANES MULTIMEDIA EXCEDIDOS
	 */
	TRAFICOEXCEDIDO_APPNAME("PORTALES"),
	TRAFICOEXCEDIDO_USERNAME("PORTALES"),
	
	PLANESMULTIMEDIA_EXCEDIDO_CODIGO_OK("0000"),
	PLANESMULTIMEDIA_EXCEDIDO_PLANEXCEDIDO("1"),
	
	TOTAL_MULTI_TRAFFICO("totaltrafico"),
	PORCENTAJE_CONSUMIDO("porcentajeconsumido"),
	
	/**
	 * PARAMETRO XMLPLANES
	 */
	XMLPLANES_CODIGO_OK("0000"),
	XMLPLANES_PLANEXCEDIDO("S"),
	
	/**
	 * XMLPLANES
	 */
//	XMLPLANES_CODIGO_OK"xmlplanes.codigo.ok"),
//	XMLPLANES_PLANEXCEDIDO("xmlplanes.codigo.planexcedido"),
	TASACION_SMSTD("SMS Todo Destino"),
	TASACION_SMSMMS("SMS-MMS NET"),
	TASACION_MTTD("MTTD"),
	TASACION_MBADI("MBADI"),
	TASACION_SMSADI("SMS ADI"),
	TASACION_MMSADI("MMS ADI"),
	XMLPLANES_PLANMULTIMEDIA("0"),
	XMLPLANES_TIPOPLAN13("13"),
	XMLPLANES_TIPOPLAN14("14"),
	XMLPLANES_TIPOPLAN13_CONTENIDO("tipoplanmultimedia13"),
	XMLPLANES_TIPOPLAN14_CONTENIDO("tipoplanmultimedia14"),
	
	/**
	 * Constante para calculo de minutos de plan
	 */
	TASA_PLAN__EN_SEGUNDOS("S"),
	TASACION_PLANES_SEGUNDOS("60"),
	CARGO_FIJO_MOVIL_PLAN_FAMILIA("planes.cargo.fijo.familia"),
	MINUTOS_ILIMITADOS("1666666666.65"),
	
	/**
	 * Constantes para tipos de Plan
	 */
	TIPO_PLAN_NORMAL("1"),
	TIPO_PLAN_RED("2"),
	TIPO_PLAN_GLOBAL("3"),
	TIPO_PLAN_JOVEN("4"),
	TIPO_PLAN_FULL("5"),
	TIPO_PLAN_UNICA("6"),
	TIPO_PLAN_REDFIJA("7"),
	TIPO_PLAN_FAMILIA("8"),
	TIPO_PLAN_DATOS("9"),
	
	/**
	 * 
	 */
	LONGITUD_CONPREFIJO_MSISDN_ENTEL("11"),
	LONGITUD_SINPREFIJO_MSISDN_ENTEL("8"),
	PREFIJO_ENTEL("569"),	
	
	/**
	 * Constantes para Consulta de Trafico
	 */
	SERVICIO_TRAFICO_VOZ("TRAF"),
	SERVICIO_TRAFICO_SMS("SMS"),
	SERVICIO_TRAFICO_ISHOP("ISHOP"),
	SERVICIO_TRAFICO_BANDA_ANCHA_MOVIL("BAM"),
	SERVICIO_TRAFICO_BANDA_ANCHA_MOVIL_GPRS("TRAFICO GPRS"),
	SERVICIO_TRAFICO_BLACKBERRY("BBRY"),
	SERVICIO_TRAFICO_WAP("WAP"),
	SERVICIO_TRAFICO_NO_DISPONIBLE("Trafico No Disponible."),
	MENU("menu"), 
	COOKIE_TIME("cookieTime"), 
	APP_TEMPLATE("app_template");
 
    private String constanteAppMovil;
 
    Constants(String constante) {
        this.constanteAppMovil = constante;
    }
 
    public String getStringValue() {
        return constanteAppMovil;
    }
    
	public static final String[] SERVICIOS_TRAFICO_ENTEL_PCS = 
		{ 
		SERVICIO_TRAFICO_VOZ.getStringValue(), 
		SERVICIO_TRAFICO_SMS.getStringValue(),
		SERVICIO_TRAFICO_BANDA_ANCHA_MOVIL.getStringValue(), 
		SERVICIO_TRAFICO_BLACKBERRY.getStringValue(), 
		SERVICIO_TRAFICO_WAP.getStringValue(), 
		SERVICIO_TRAFICO_ISHOP.getStringValue()
		};

}