/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.MissingResourceException;

import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ContratoBean;
import com.epcs.bean.ListaContratosBean;
import com.epcs.bean.MiEntelBean;
import com.epcs.bean.PerfilUsuarioBean;
import com.epcs.bean.RutBean;
import com.epcs.cliente.perfil.delegate.ContratosDelegate;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;

import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.converter.RutConverter;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author dvelez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ContratosController implements Serializable {

	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger
    .getLogger(ContratosController.class);
    public static final String CODIGO_RESPUESTA_NOINFO = "0001";
    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties.getProperty("servicios.codigoRespuesta.exito");
    public static final String SIN_INFORMACION = MiEntelProperties.getProperty("contratos.mensaje.sinInformacion");
    
    private ContratosDelegate contratosDelegate = new ContratosDelegate();
    private CuentaDelegate cuentaDelegate = new CuentaDelegate();
    
    private int cantidadRegistrosPorPagina = 20;
    private String codigoFirmados = "31,32";
    private boolean error = false;
	private String numeroPcs = "";
	private String rutUsuarioSeleccionado = "";
	private String rutUsuarioSeleccionadoFormated = "";
    private String rut = "";
    private String rutFormated = "";
    private String errorMessage = "";
    private int totalPaginas = 0;
    private String AAA;
    
    private int numeroRegistros;
    private ArrayList<ContratoBean> listaContratosTabla;
    private ArrayList<ContratoBean> listaContratos;
    
    private MiEntelBean miEntelBean;
    private String mensajeNoDisponible = "";
    private String mensajePermisos = "";
    
    public ContratosController(){
    	try{
	        ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
	        RutConverter rutconv = new RutConverter();
	        
	        numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,"numeroPcsSeleccionado");
	        rutUsuarioSeleccionado = ProfileWrapperHelper.getPropertyAsString(profile,"rutUsuarioSeleccionado");	        
	        RutBean rutUsuarioSeleccionadoBean = (RutBean)rutconv.getAsObject(null, null, rutUsuarioSeleccionado);
	        RutBean.rutToMayus(rutUsuarioSeleccionadoBean);
	        rutUsuarioSeleccionadoFormated = rutconv.getAsString(null, null, rutUsuarioSeleccionadoBean);
	        
	        rut = ProfileWrapperHelper.getPropertyAsString(profile,"rutTitular");	        
	        RutBean rutBean = (RutBean)rutconv.getAsObject(null, null, rut);	        
	        RutBean.rutToMayus(rutBean);
	        rutFormated = rutconv.getAsString(null, null, rutBean);
	        
	        
	        setAAA(ProfileWrapperHelper.getPropertyAsString(profile,"aaa"));
	        
	    	try{
	    		String regPorPagina = MiEntelProperties.getProperty("contratos.lista.registrosPorPagina");
	    		cantidadRegistrosPorPagina = Integer.parseInt(regPorPagina);
	    	}catch (NumberFormatException nfe) {
				LOGGER.info("Problema al obtener el numero de registros por pagina, default a 20");
				cantidadRegistrosPorPagina = 20;
			}catch (MissingResourceException e) {
				LOGGER.info("Problema al obtener el numero de registros por pagina, default a 20");
				cantidadRegistrosPorPagina = 20;
			}
			
	    	if(cantidadRegistrosPorPagina == 0){
	    		cantidadRegistrosPorPagina = 20;
	    	}
	    	
	    	try{
		    	codigoFirmados = MiEntelProperties.getProperty("contratos.lista.registroFirmados");
		    	if(codigoFirmados == null || "".equalsIgnoreCase(codigoFirmados)){
		    		codigoFirmados = "31,32";
		            LOGGER.info("CodigoFirmados default a 31,32");
		    	}
	    	}catch (MissingResourceException e) {
				LOGGER.info("Problema al obtener el codigo de registros firmados, default a 31,32");
				codigoFirmados = "31,32";
			}
	    	
	    	miEntelBean = new MiEntelBean();	    	
	    	mensajeNoDisponible = MiEntelProperties.getServiceMessages().getErrorMessage("noDisponible");	    	
	    	mensajePermisos = MiEntelProperties.getProperty("miEntel.aaa.security.defaultMessage");
	    	
        }catch (Exception e) {
            LOGGER.info("Exception al inicializar los datos para el portlet, rut: "+rut+", numeroPCS: "+numeroPcs, e);
        }
    }
    
    public void init(PhaseEvent event) {
    	loadData();
    }
    
    public void loadData(){
        try{				
        	if(miEntelBean.isAAATitular(getAAA())){
				ListaContratosBean listaContratosBean;		
				String flagRutTitular="0";				
				if(rutUsuarioSeleccionadoFormated.equals(rutFormated)){
					flagRutTitular="1";
				}else{
					flagRutTitular="0";			
				}
				
				listaContratosBean = contratosDelegate.obtenerListaContratosFirmadosConMovil(rutFormated, 
						String.valueOf(0), 
						String.valueOf(cantidadRegistrosPorPagina), 
                        codigoFirmados,
                        flagRutTitular,
                        numeroPcs);
				
				numeroRegistros = listaContratosBean.getNumeroTotalRegistros();
				
				totalPaginas = (int) Math.ceil((float)numeroRegistros/(float)cantidadRegistrosPorPagina);
        	}else{
        		numeroRegistros=0;
				totalPaginas = 1;
			}
			
			if(totalPaginas == 0){
				totalPaginas = 1;
			}
			
        }catch (Exception e) {
            LOGGER.error("Exception al inicializar los datos para el portlet, rut: "+rut+", numeroPCS: "+numeroPcs, e);
            error = true;
            setErrorMessage(mensajeNoDisponible);
        }
        
    }
    
    public void initPageData(PhaseEvent ev){
    	loadPageData();
    }
    
    public void loadPageData(){    	
    	String paginaActual = JsfUtil.getRequestParameter("paginaActual");
    	if (paginaActual==null || paginaActual.trim().equals("")) paginaActual = "1";
    	
    	int numeroPagina = 1;
    	try{
    	    numeroPagina = Integer.parseInt(paginaActual);
    	}catch (NumberFormatException nfe) {
			LOGGER.info("El numero de pagina solicitada es incorrecto, default a 1");
			numeroPagina = 1;
		}
    	
    	try {    			
    		if(miEntelBean.isAAATitular(getAAA())){
    			
    			ListaContratosBean listaContratosBean;
    			
    			String flagRutTitular="0";				
				if(rutUsuarioSeleccionadoFormated.equals(rutFormated)){
					flagRutTitular="1";
				}else{
					flagRutTitular="0";			
				}
				
    			listaContratosBean = contratosDelegate.obtenerListaContratosFirmadosConMovil(rutFormated, 
    					                                        String.valueOf(numeroPagina), 
    					                                        String.valueOf(cantidadRegistrosPorPagina), 
    					                                        codigoFirmados,
    					                                        flagRutTitular,
    					                                        numeroPcs);
    			
    			numeroRegistros = listaContratosBean.getNumeroTotalRegistros();
    			
    			if(listaContratosBean!=null){
    				if(listaContratosBean.getCodigo().equals(CODIGO_RESPUESTA_OK)){		    			
		    			
		    			ArrayList<ContratoBean> contratosTemp = listaContratosBean.getDocumentos(); 
		    			listaContratos = new ArrayList<ContratoBean>();		    			
		    			
		    					    				
		    			for(ContratoBean contrato: contratosTemp){
		    				if(contrato.getUrl() != null && !contrato.getUrl().equalsIgnoreCase("")){			    					
	    						if(contrato.getMovil().equals("")){
		    						contrato.setMovil(SIN_INFORMACION);
		    						listaContratos.add(contrato);		    						
		    					}else{
		    						listaContratos.add(contrato);		    						
		    					}			    						    					
		    				}
		    			}	    			
		    			
		    			totalPaginas = (int) Math.ceil((float)numeroRegistros/(float)cantidadRegistrosPorPagina);
		    			if(totalPaginas == 0){
		    				totalPaginas = 1;
		    			}
		    			
		    			if(numeroRegistros <= 0){
		    				error = true;
		    				setErrorMessage(MiEntelProperties.getServiceMessages().getErrorMessage("miscontratos","noInfo"));
		    			}
    				}else{
    					error = true;
	    				setErrorMessage(MiEntelProperties.getServiceMessages().getErrorMessage("miscontratos",listaContratosBean.getCodigo()));
    				}
    			}else{
    				error = true;
    				setErrorMessage(mensajeNoDisponible);
    			}
    		}else{
    			error = true;
    			setErrorMessage(mensajePermisos);
    		}			
		} catch (DAOException daoe) {
			LOGGER.error("DAOException al consultar los contratos", daoe);
			error = true;
			setErrorMessage(mensajeNoDisponible);
		} catch (ServiceException sxe) {
			error = true;
			LOGGER.info("Service exception al consultar los contratos firmados codigo: "+sxe.getCodigoRespuesta()+" mensaje: "+sxe.getDescripcionRespuesta());
			if(CODIGO_RESPUESTA_NOINFO.equalsIgnoreCase(sxe.getCodigoRespuesta())){
				setErrorMessage("No existe informaci&oacute;n");
			}else{
				setErrorMessage(mensajeNoDisponible);
			}
		}catch (Exception e) {
            LOGGER.error("Exception al consultar los contratos firmados, rut: "+rut, e);
            error = true;
            setErrorMessage(mensajeNoDisponible);
        }
    }

    
	/**
	 * @return the numeroRegistros
	 */
	public int getNumeroRegistros() {
		return numeroRegistros;
	}

	/**
	 * @param numeroRegistros the numeroRegistros to set
	 */
	public void setNumeroRegistros(int numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}

	/**
	 * @return the listaContratos
	 */
	public ArrayList<ContratoBean> getListaContratos() {
		return listaContratos;
	}

	/**
	 * @param listaContratos the listaContratos to set
	 */
	public void setListaContratos(ArrayList<ContratoBean> listaContratos) {
		this.listaContratos = listaContratos;
	}

	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setAAA(String AAA) {
		this.AAA = AAA;
	}

	public String getAAA() {
		return AAA;
	}
}
