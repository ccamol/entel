package com.esa.ponline.portalbolsas.web.servlets;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.esa.billing.orderingprod.t.validarplanmmautogestion.BILTValidarPlanMMAutogestionPortType;
import com.esa.billing.orderingprod.t.validarplanmmautogestion.ValidarPlanMMAutogestionFaultMessage;
import com.esa.billing.orderingprod.t.validarplanmmautogestion.request.ValidarPlanMMAutogestionReq;
import com.esa.billing.orderingprod.t.validarplanmmautogestion.response.ValidarPlanMMAutogestionRes;
import com.esa.ponline.portalbolsas.bean.BolsaComprada;
import com.esa.ponline.portalbolsas.bean.BolsaComprar;
import com.esa.ponline.portalbolsas.bean.DetalleBolsaCliente;
import com.esa.ponline.portalbolsas.bean.MontoRecargaBean;
import com.esa.ponline.portalbolsas.bean.Movil;
import com.esa.ponline.portalbolsas.configuration.parameters.helper.ParametrosHelper;
import com.esa.ponline.portalbolsas.configuration.properties.LoadProperty;
import com.esa.ponline.portalbolsas.constants.Constants;
import com.esa.ponline.portalbolsas.exceptions.ws.EntelServicesLocatorException;
import com.esa.ponline.portalbolsas.sec.encrypt.Hex;
import com.esa.ponline.portalbolsas.util.Config;
import com.esa.ponline.portalbolsas.util.TimeWatch;
import com.esa.ponline.portalbolsas.ws.EntelServices;
import com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.delegate.DelegateBolsa;

/**
 * @author ccastro
 *
 */

public class Init extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(Init.class);
    
    protected String sinTraficoMsg;
    
    protected String sinSaldoSuficienteMsg;

	protected String recFailMsg;
    
    protected String recSucMsg;
    
    protected ArrayList<DetalleBolsaCliente> detalleBolsasCliente;
    
    protected ArrayList<BolsaComprada> bolsasActivasCliente;
    
    private String tieneBolsasActivas;

	private String msisdn;
    
    private String ip;
    
    private String schar;
    
    private DelegateBolsa delegateBolsa = new DelegateBolsa();
    
    static Cipher cipher;
    
    private static final String urlWebpay = Constants.URL_WEBPAY.getStringValue();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession(false);
        String idSession = "";
        String msisdnSession = ""; 
        String msisdnDesencriptado = "";
        
        if(session == null){
        	LOGGER.info("Usuario no posee sesion al inicio del portal");
    		if(!obtenerParametrosURL(req, resp)){
    			return;
    		}
            idSession = "msisdnSession: " + this.msisdn;
            session = req.getSession();
            session.setAttribute("idSession", idSession);
            MDC.put("idSession", idSession);
            
    		// posee sesion
        } else {
        	// si posee sesion, pero no posee msisdnParam
        	String msisdnParam = req.getParameter("msisdn");
        	
        	if(msisdnParam==null){
            	msisdnSession = (String) session.getAttribute("msisdn");
        		LOGGER.info("msisdnSession: " + msisdnSession);
        		validarPlanMMAutogestion(msisdnSession, resp, req);
        	} else {
        		// posee msisdnSession y msisnParam - se compara msisdnParam y msisdnSession
            	msisdnSession = (String) session.getAttribute("msisdn");
        		
        		try {
					msisdnDesencriptado = obtenerParamDesencriptado(msisdnParam);
	        		LOGGER.info("msisdnSession: " + msisdnSession);
	        		LOGGER.info("msisdnParam: " + msisdnParam);
	        		LOGGER.info("msisdnDesencriptado: " + msisdnDesencriptado);
					
	        		if(!msisdnSession.equalsIgnoreCase(msisdnDesencriptado)){
		        		if(validarPlanMMAutogestion(msisdnDesencriptado, resp, req)){
		        			//TODO corregir log
		        			LOGGER.info("validando PortalCompraBolsas para una session diferente");
		        			LOGGER.info("el usuario ES planMMAutogestion");
		        			
		        	        idSession = "msisdnSession: " + msisdnDesencriptado;
//		        	        session = req.getSession();
		        	        session.setAttribute("idSession", idSession);
		        	        session.setAttribute("msisdnDesencriptado", msisdnDesencriptado);
		        	        MDC.put("idSession", idSession);
		        		} else {
		        			// TODO corregir log
		        			LOGGER.info("la linea ingresada MSISDN ->" + msisdnDesencriptado + "<- NO ES [MM Autogestion] para una session diferente");
		        			
		        			session.invalidate();
		    				resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		    				resp.setHeader("Location", Config.getAppProperty("redirectMEntel"));
		    				return;
		        		}
	        		}
				} catch (Exception e) {
					LOGGER.error("Excepcion lanzada debido a: " + e.getMessage());
					e.printStackTrace();
				}
        	}
        	
        	msisdnSession = (String) session.getAttribute("msisdn");
        	msisdnDesencriptado = (String) session.getAttribute("msisdnDesencriptado");
        	if (msisdnDesencriptado!=null){
        	    setMsisdn(msisdnDesencriptado);
        	}
        	LOGGER.info("msisdn: " + this.msisdn);
        	LOGGER.info("msisdnSession: " + msisdnSession);        	
        }
        
        List<MontoRecargaBean> montosWebpay = ParametrosHelper.getMontosRecargaTarjetaCredito();
        
        ArrayList<BolsaComprar> bolsasActivas = null;
//        ArrayList<DetalleBolsaCliente> detalleBolsasCliente = null;
        Movil movilCliente = null;
        
//        String msisdnListarBolsas = msisdn.substring(3, 11);
        
        try {
            detalleBolsasCliente = delegateBolsa.listarBolsas(this.msisdn);
            bolsasActivasCliente = delegateBolsa.listarBolsasActivas(this.msisdn);
            movilCliente = delegateBolsa.desplegarInfoCliente(this.msisdn);
            //TODO codigo que aplica
            if(detalleBolsasCliente.size()==0){
                redirigirEntelMobile(resp);
                session.invalidate();
                return;
            }
            if(movilCliente==null){
                redirigirEntelMobile(resp);
                session.invalidate();
                return;
            }
            if(bolsasActivasCliente.size()>0){
            	setTieneBolsasActivas("bolsasActivas");
//            	session.setAttribute("bolsasActivasCliente", bolsasActivasCliente);
                req.setAttribute("bolsasActivas", getTieneBolsasActivas());            	
                req.setAttribute("bolsasActivasCliente", bolsasActivasCliente);
            } else {
            	setTieneBolsasActivas("");
            }         
            //TODO a modo prueba se establecer saldo 0
//            movilCliente.setSaldo("3000");
        } catch (Exception e) {
            LOGGER.error("Excepcion lanzado debido a: " + e.getMessage());
            e.printStackTrace();
        }
        
//        setSinSaldoSuficienteMsg("Saldo insuficiente");
        setSinSaldoSuficienteMsg(LoadProperty.getProperty("sinSaldo"));
        
        // Se establecen atributos y variables
        req.setAttribute("montosWebpay", montosWebpay);
        req.setAttribute("bolsas", bolsasActivas);
        req.setAttribute("movil", movilCliente);
        req.setAttribute("bolsasCliente", detalleBolsasCliente); 
        req.setAttribute("saldoInsuficiente", getSinSaldoSuficienteMsg());  
        
        session = req.getSession();
        session.setAttribute("msisdn", this.msisdn);
        session.setAttribute("montosWebpay", montosWebpay);
        session.setAttribute("bolsasCliente", detalleBolsasCliente);
        session.setAttribute("movil", movilCliente);
        session.setAttribute("saldo", movilCliente.getSaldo());
		session.setAttribute("urlWebpay", paramUrlWebpay());
        
        req.getRequestDispatcher("/jsp/bolsas-mm-2.jsp").forward(req, resp);
    }
    
    
    private String paramUrlWebpay(){
    	String url = "";
		String keyString = "ptraficoexcedido";
		SecretKey secretKey = new SecretKeySpec(keyString.getBytes(),"AES");
		url = urlWebpay+encrypt(this.msisdn, secretKey).toUpperCase();
		
    	return url;
    }

	private void redirigirEntelMobile(HttpServletResponse resp) {
        LOGGER.error("No se cumplen los requisitos, por ende se redirige a: " + Config.getAppProperty("redirectMEntel"));               
        resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        resp.setHeader("Location", Config.getAppProperty("redirectMEntel"));
        return;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
    }
    
	private boolean obtenerParametrosURL(HttpServletRequest req, HttpServletResponse resp) {
        String msisdn = "";
        String ip = "";
        String schar = "";
    	
    	try {
    		//Lineas que aplican para alerta cautiva
			msisdn = obtenerParamDesencriptado(req.getParameter("msisdn"));
			ip = obtenerParamDesencriptado(req.getParameter("ip"));
			schar = obtenerParamDesencriptado(req.getParameter("schar"));
			
			LOGGER.info("MSISDN PARAM DESCIFRADO: " + msisdn);
			LOGGER.info("IP PARAM DESCIFRADO: " + ip);
			LOGGER.info("SCHAR PARAM DESCIFRADO: " + schar);
			
	      if(!validarPlanMMAutogestion(!msisdn.equals("")  ? msisdn : "", resp, req)){
	      	return false;
	      } else {
	    	  setMsisdn(msisdn);
	    	  setIp(ip);
	    	  setSchar(schar);
	      }
		} catch (Exception e) {
			LOGGER.error("Error al desencriptar datos u obtener parametros nulos en URL");
			LOGGER.error("PARAMRTRO [MSISDN]: " + (!msisdn.equals("")  ? msisdn : "NULO O SIN VALOR"));
			LOGGER.error("PARAMETRO [IP]:     " + (!ip.equals("")  ? ip : "NULO O SIN VALOR"));
			LOGGER.error("PARAMETRO [SCHAR]:  " + (!schar.equals("")  ? schar : "NULO O SIN VALOR"));
			
//		    if(!validarPlanMMAutogestion(msisdn != null || !msisdn.equals("")  ? msisdn : "", resp))
//		    	return false;
	    	if (msisdn == null || msisdn.equals("")) {
	    		LOGGER.error("No se capturan parametros desde url - Usuario es redirigido a " + Config.getAppProperty("redirectMEntel"));
	            resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
//	            resp.setHeader("Location", "http://m.entel.cl");
	            resp.setHeader("Location", Config.getAppProperty("redirectMEntel"));
	            return false;
			}
	    	if (ip == null || ip.equals("")) {
	    		LOGGER.error("No se capturan parametros desde url - Usuario es redirigido a " + Config.getAppProperty("redirectMEntel"));	    		
	            resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	            resp.setHeader("Location", Config.getAppProperty("redirectMEntel"));
	            return false;
			}		    
	    	if (schar == null || schar.equals("")) {
	    		LOGGER.error("No se capturan parametros desde url - Usuario es redirigido a " + Config.getAppProperty("redirectMEntel"));	    		
	            resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	            resp.setHeader("Location", Config.getAppProperty("redirectMEntel"));
	            return false;
			}
    		LOGGER.error("Fallo de coneccion al servicio - Usuario es redirigido a " + Config.getAppProperty("redirectMEntel"));	    		
            resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            resp.setHeader("Location", Config.getAppProperty("redirectMEntel"));
			e.printStackTrace();
			
            return false;
		}
    	return true;
	}
	
	
    private String obtenerParamDesencriptado(String string) throws Exception {
		String keyString = "ptraficoexcedido";
		SecretKey secretKey = new SecretKeySpec(keyString.getBytes(),"AES");
		
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Error de cifrado debida a: " + e.getMessage());
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			LOGGER.error("Error de cifrado debida a: " + e.getMessage());
			e.printStackTrace();
		}
		
//		String plainText = "AES Symmetric Encryption Decryption";
//		LOGGER.info("Plain Text Before Encryption: " + plainText);
		
    	return decrypt(string, secretKey);
		
	}
    
    @SuppressWarnings("unused")
	private String obtenerParamEncriptado(String string) throws Exception {
		String keyString = "ptraficoexcedido";
		SecretKey secretKey = new SecretKeySpec(keyString.getBytes(),"AES");
		
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Error de cifrado debida a: " + e.getMessage());
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			LOGGER.error("Error de cifrado debida a: " + e.getMessage());
			e.printStackTrace();
		}
		
//		String plainText = "AES Symmetric Encryption Decryption";
//		LOGGER.info("Plain Text Before Encryption: " + plainText);
		
    	return encrypt(string, secretKey);
		
	}
    
    protected String idSessionLog(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(false);
        if(session==null){
    		LOGGER.error("Usuario intenta acceder a url sin session | Usuario es redirigido a " + Config.getAppProperty("redirectMEntel"));	    		
            resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            resp.setHeader("Location", Config.getAppProperty("redirectMEntel"));
        	return "NOK";
        }
        
        String idSession = (String) session.getAttribute("idSession"); 
    	MDC.put("idSession", idSession);
    	return "OK";
    }
   
	public static String encrypt(String plainText, SecretKey secretKey) {
		byte[] plainTextByte = plainText.getBytes();
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		} catch (InvalidKeyException e) {
			LOGGER.error("no es posible realizar llamado para cifrar: " + e.getMessage());
			e.printStackTrace();
		}
		
		byte[] encryptedByte = null;
		try {
			encryptedByte = cipher.doFinal(plainTextByte);
		} catch (IllegalBlockSizeException e) {
			LOGGER.error("no es posible realizar llamado para cifrar: " + e.getMessage());
			e.printStackTrace();
		} catch (BadPaddingException e) {
			LOGGER.error("no es posible realizar llamado para cifrar: " + e.getMessage());
			e.printStackTrace();
		}
		
		char[] encryptedHex = Hex.encodeHex(encryptedByte);
		String encryptedText = new String(encryptedHex);
		return encryptedText;
	}

    private String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
    	byte[] encryptedTextByte = Hex.decodeHex(encryptedText.toCharArray());
    	cipher.init(Cipher.DECRYPT_MODE, secretKey);
    	byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
    	String decryptedText = new String(decryptedByte);
    	return decryptedText;
    }
    
    
    private boolean validarPlanMMAutogestion(String msisdn, HttpServletResponse resp, HttpServletRequest req){
    	if (msisdn == null || msisdn.equals("")) {
            resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            resp.setHeader("Location", Config.getAppProperty("redirectMEntel"));
            return false;
		}
    	
		ValidarPlanMMAutogestionReq validarPlanMMReq = new ValidarPlanMMAutogestionReq();
		validarPlanMMReq.setMsisdn(msisdn);
		
		ValidarPlanMMAutogestionRes response = null;
		
		try {
			response = getPlanMMAutogestionConnection().validarPlanMMAutogestion(validarPlanMMReq);		
		} catch (ValidarPlanMMAutogestionFaultMessage e) {
			LOGGER.error("No es posible obtener respuesta para validar cliente MMAutogestion");
			e.printStackTrace();
		}
		
		String codigoRespuesta = String.valueOf(response.getResultado().getCodigoResp().intValue());
		LOGGER.info("codigoRespuesta: " + codigoRespuesta);
		
		//TODO a modo prueba para PMOVIL se establece en valor 1
		// linea para simular canal pmovil, solo aplica bloque if para true, sino es bloque real
		if(Boolean.parseBoolean(LoadProperty.getProperty("simulaCanal"))){
			if(LoadProperty.getProperty("respCodigoResp").equals(codigoRespuesta)){
				LOGGER.info("############### SIMULANDO CANAL PMOVIL #################");
				LOGGER.info("Se imprime variable [respCodigoResp] [TRUE] para simular canal pmovil");
				LOGGER.info("Cliente pertenece a Plan MM Autogestion");
				LOGGER.info("Servicio ValidarPlanMMAutogestionRespuetsa MERCADO: " + response.getMercadoPlan());
				LOGGER.info("Servicio ValidarPlanMMAutogestionRespuesta RESULTADO: " + response.getResultado().getMsgResp());
				LOGGER.info("Servicio ValidarPlanMMAutogestionRespuesta CODIGO: " + codigoRespuesta);
				
//		        HttpSession session = req.getSession(false);
//		        String idSession = "msisdnSession: " + msisdn;
//		        session = req.getSession();
//		        session.setAttribute("idSession", idSession);
//		        MDC.put("idSession", idSession);
				
				
				return true;
			} else {
				LOGGER.warn("Cliente NO pertenece a Plan MM Autogestion");
				LOGGER.warn("ValidarPlanMMAutogestionRes RESULTADO: " + response.getResultado().getMsgResp());
				LOGGER.warn("Servicio ValidarPlanMMAutogestionRespuesta CODIGO: " + codigoRespuesta);
				LOGGER.warn("El cliente es redirigido al sitio " + Config.getAppProperty("redirectMEntel"));
//				return codigoRespuesta;
				resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
				resp.setHeader("Location", Config.getAppProperty("redirectMEntel"));
				return false;
			}
		} else {
			// linea if que aplica para WEBSGOPP
			if(codigoRespuesta.equals("0")){
				LOGGER.info("Cliente pertenece a Plan MM Autogestion");
				LOGGER.info("Servicio ValidarPlanMMAutogestionRespuetsa MERCADO: " + response.getMercadoPlan());
				LOGGER.info("Servicio ValidarPlanMMAutogestionRespuesta RESULTADO: " + response.getResultado().getMsgResp());
				LOGGER.info("Servicio ValidarPlanMMAutogestionRespuesta CODIGO: " + codigoRespuesta);
				
//		        HttpSession session = req.getSession(false);
//		        String idSession = "msisdnSession: " + msisdn;
//		        session = req.getSession();
//		        session.setAttribute("idSession", idSession);
//		        MDC.put("idSession", idSession);
				
				
				return true;
			} else {
				LOGGER.warn("Cliente NO pertenece a Plan MM Autogestion");
				LOGGER.warn("ValidarPlanMMAutogestionRes RESULTADO: " + response.getResultado().getMsgResp());
				LOGGER.warn("Servicio ValidarPlanMMAutogestionRespuesta CODIGO: " + codigoRespuesta);
				LOGGER.warn("El cliente es redirigido al sitio " + Config.getAppProperty("redirectMEntel"));
//				return codigoRespuesta;
				resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
				resp.setHeader("Location", Config.getAppProperty("redirectMEntel"));
				return false;
			}
		}
    }
    

    /**
     * Gets the account service connection.
     *
     * @return the account service connection
     */
    private BILTValidarPlanMMAutogestionPortType getPlanMMAutogestionConnection() {
        TimeWatch watch = TimeWatch.start();
        BILTValidarPlanMMAutogestionPortType port = null;

        try {
            port = EntelServices.getMMAutoGestionServicePort().getBILTValidarPlanMMAutogestionBindingQSPort();
        } catch (EntelServicesLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + BILTValidarPlanMMAutogestionPortType.class, e);
            LOGGER.error("Tiempo: " + watch.time() + " | " + BILTValidarPlanMMAutogestionPortType.class);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("Error al inicializar el Port " + BILTValidarPlanMMAutogestionPortType.class, e);
            LOGGER.error("Tiempo: " + watch.time() + " | " + BILTValidarPlanMMAutogestionPortType.class);
            e.printStackTrace();
        }
        return port;
    }
    
    
    public String inicio() {
        LOGGER.info("Logger ok");
        
        return "Hola"; 
    }

	/**
	 * @return the sinTraficoMsg
	 */
	public String getSinTraficoMsg() {
		return sinTraficoMsg;
	}

	/**
	 * @param sinTraficoMsg the sinTraficoMsg to set
	 */
	public void setSinTraficoMsg(String sinTraficoMsg) {
		this.sinTraficoMsg = sinTraficoMsg;
	}

	/**
	 * @return the recFailMsg
	 */
	public String getRecFailMsg() {
		return recFailMsg;
	}

	/**
	 * @param recFailMsg the recFailMsg to set
	 */
	public void setRecFailMsg(String recFailMsg) {
		this.recFailMsg = recFailMsg;
	}

	/**
	 * @return the recSucMsg
	 */
	public String getRecSucMsg() {
		return recSucMsg;
	}

	/**
	 * @param recSucMsg the recSucMsg to set
	 */
	public void setRecSucMsg(String recSucMsg) {
		this.recSucMsg = recSucMsg;
	}

	/**
	 * @return the msisdn
	 */
	public String getMsisdn() {
		return msisdn;
	}

	/**
	 * @param msisdn the msisdn to set
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the schar
	 */
	public String getSchar() {
		return schar;
	}

	/**
	 * @param schar the schar to set
	 */
	public void setSchar(String schar) {
		this.schar = schar;
	}

	/**
	 * @return the sinSaldoSuficienteMsg
	 */
	public String getSinSaldoSuficienteMsg() {
		return sinSaldoSuficienteMsg;
	}

	/**
	 * @param sinSaldoSuficienteMsg the sinSaldoSuficienteMsg to set
	 */
	public void setSinSaldoSuficienteMsg(String sinSaldoSuficienteMsg) {
		this.sinSaldoSuficienteMsg = sinSaldoSuficienteMsg;
	}
	
    
    /**
	 * @return the detalleBolsasCliente
	 */
	public ArrayList<DetalleBolsaCliente> getDetalleBolsasCliente() {
		return detalleBolsasCliente;
	}


	/**
	 * @param detalleBolsasCliente the detalleBolsasCliente to set
	 */
	public void setDetalleBolsasCliente(
			ArrayList<DetalleBolsaCliente> detalleBolsasCliente) {
		this.detalleBolsasCliente = detalleBolsasCliente;
	}
	
	public String getTieneBolsasActivas() {
		return tieneBolsasActivas;
	}

    
	private void setTieneBolsasActivas(String mensaje) {
		this.tieneBolsasActivas=mensaje;
	}
	
}
