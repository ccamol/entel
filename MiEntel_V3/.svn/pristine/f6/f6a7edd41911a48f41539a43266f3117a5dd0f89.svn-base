package com.epcs.nba;

import java.util.List;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MsisdnAsociadoBean;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;
import com.epcs.cliente.perfil.delegate.OfertaBlindajeDelegate;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

public class NBAUtils {
	
	private List<MsisdnAsociadoBean> numerosAsociados;
	
	private static final String ID_CONTENIDO_PLANES_BAM = MiEntelProperties
			.getProperty("parametros.ofertas.idbscs.planesBAM");

	public NBAUtils(){}
	
	/**
	 * metodo que retorna los numeros asociados al movil del usuario en sesion
	 * @return List<MsisdnAsociadoBean>
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<MsisdnAsociadoBean> getMovilesAsociados() throws Exception{
		
		ProfileWrapper profileWrapper = ProfileWrapperHelper
		        .getProfile(JSFPortletHelper.getRequest());
		numerosAsociados = (List<MsisdnAsociadoBean>) JSFPortletHelper.getSession().getAttribute("numerosAsociados");   
	        if (numerosAsociados == null) {
	
	            String movilUsuario = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcs");
	            String aaa = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
	            String flagBam = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"flagBam");
	            String subMercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"subMercado");
	            
	            if(MiEntelBusinessHelper.getAAATitular().equals(aaa)){
		            CuentaDelegate cuentaDelegate = new CuentaDelegate();
		            numerosAsociados = cuentaDelegate.getListaMsisdnAsociados(movilUsuario,subMercado,flagBam);
	            }
	        }   
		    return numerosAsociados;
	}
	
	/**
	 * metodo que retorna los numeros asociados al movil del usuario en sesion sin tener en cuenta el AAA
	 * @return List<MsisdnAsociadoBean>
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<MsisdnAsociadoBean> getMovilesAsociadosSinAAA() throws Exception{
		
		ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
		numerosAsociados = (List<MsisdnAsociadoBean>) JSFPortletHelper.getSession().getAttribute("numerosAsociados");   
	        if (numerosAsociados == null) {
	            String movilUsuario = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcs");
	            String flagBam = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"flagBam");
	            String subMercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"subMercado");
	            CuentaDelegate cuentaDelegate = new CuentaDelegate();
	            numerosAsociados = cuentaDelegate.getListaMsisdnAsociados(movilUsuario,subMercado,flagBam);
	        }   
		    return numerosAsociados;
	}
	public void actualizarMinimizarBanner(List<MsisdnAsociadoBean> lista,int rechazo) throws Exception{
		String movil;
		OfertaBlindajeDelegate ofertaBlindajeDelegate = new OfertaBlindajeDelegate();
		for(int i=0;i<lista.size();i++){
			movil=((MsisdnAsociadoBean)lista.get(0)).getNumeroPcs();
			ofertaBlindajeDelegate.actualizarMinimizarBanner(movil,rechazo);
		}
	}
	/**
	 * metodo que reemplaza &_acute; por tilde
	 * @param texto
	 * @return
	 */
	public String agregarTilde(String texto){
		
		texto = texto.replace("&aacute;", "\u00e1");
		texto = texto.replace("&eacute;", "\u00e9");
		texto = texto.replace("&iacute;", "\u00ed");
		texto = texto.replace("&oacute;", "\u00f3");
		texto = texto.replace("&uacute;", "\u00fa");
		texto = texto.replace("&ntilde;", "\u00f1");
		texto = texto.replace("&uuml;", "\u00fa");

		texto = texto.replace("&Aacute;", "\u00c1");
		texto = texto.replace("&Eacute;", "\u00c9");
		texto = texto.replace("&Iacute;", "\u00cd");
		texto = texto.replace("&Oacute;", "\u00d3");
		texto = texto.replace("&Uacute;", "\u00da");
		texto = texto.replace("&Ntilde;", "\u00d1");
		texto = texto.replace("&Uuml;", "\u00dc");
		return texto;
	}
	
	public boolean isOfertaBAM(String codiPlantilla){
		boolean isOfertaBam = false;
		try{
			Node plantillasBAM = JSFPortletHelper.getContentNode("idContenido", ID_CONTENIDO_PLANES_BAM);
	    	String codPlanilla = plantillasBAM.getProperty("html").getValue().getStringValue();
	    	isOfertaBam = codPlanilla.indexOf(codiPlantilla) != -1;
		}catch(Exception ex){
			
		}
		return isOfertaBam;
	}
	
}
