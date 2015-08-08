/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.helper;

import org.apache.log4j.Logger;

import com.esa.ponline.appmobile.exceptions.ServiceException;
import com.esa.ponline.appmobile.exceptions.WSDAOException;
import com.esa.ponline.appmobile.vo.Bundle.AvailableBundleBAMCCPP;
import com.esa.ponline.appmobile.vo.Bundle.AvailableBundleBAMSSCC;
import com.esa.ponline.appmobile.vo.Bundle.BundleCCPPVO;
import com.esa.ponline.appmobile.vo.Bundle.BundleSSCCVO;
import com.esa.ponline.appmobile.web.delegate.DelegateBundle;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * version 1.0.0
 * Sep 3, 2014
 */

public class BundleHelper {
	
	private static final Logger LOGGER = Logger.getLogger(BundleHelper.class);
	
	private DelegateBundle delegateBundle = new DelegateBundle();
	
	private BundleCCPPVO bolsaPP = new BundleCCPPVO();
	
	private BundleSSCCVO bolsaSSCC = new BundleSSCCVO();

	public BundleCCPPVO getBundleDescriptionInfoPP(String codeBundle, String msisdn) {
		AvailableBundleBAMCCPP response = null;
		try {
			response = delegateBundle.getAllAvailablePPBundles(msisdn);
			if(!response.getBolsas().isEmpty() && response.getBolsas().size() > 0){
				for (int i = 0; i < response.getBolsas().size(); i++) {
					if(codeBundle.equals(response.getBolsas().get(i).getCodigo())){
						bolsaPP = new BundleCCPPVO();
						bolsaPP.setCodigo(response.getBolsas().get(i).getCodigo());
						bolsaPP.setNombre(response.getBolsas().get(i).getNombre());
						
						// Carta Bolsa Alo Peru
						if(codeBundle.equalsIgnoreCase("CARTASERV0320")){
							bolsaPP.setCantInicial(response.getBolsas().get(i).getDescripcionComercial());
						}else{
//							if(codeBundle.equalsIgnoreCase("CARTASERV0337")){
//								bolsaPP.setCantInicial("ilimitado");
//							}else{
								bolsaPP.setCantInicial(getCantIniBolsa(response.getBolsas().get(i).getDescripcionComercial(), 
										response.getBolsas().get(i).getDescripcion(), response.getBolsas().get(i).getUnidad()));
//							}
						}
						bolsaPP.setPrecio(response.getBolsas().get(i).getPrecio());
						bolsaPP.setCuotaInicialVoucher(response.getBolsas().get(i).getCuotaInicialVoucher());
						break;
					}
				}
			}else{
				LOGGER.error("No es posible obtener bolsas");
			}
		} catch (ServiceException e) {
			LOGGER.error("Problema en servicio " + e.getMessage());
			e.printStackTrace();
		} catch (WSDAOException e) {
			LOGGER.error("Problema en servicio " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Excepcion lanzada " + e.getMessage());
			e.printStackTrace();
		}
		
		//Valido que la bolsa tenga datos consistentes
		if (bolsaPP.getNombre()==null){
		    LOGGER.error("No es posible obtener bolsa con codigo "+codeBundle+" al movil " + msisdn);
		    //Cargamos datos de prueba
		    bolsaPP.setCodigo("");
		    bolsaPP.setNombre("bolsa");		
		}
		
		return bolsaPP;
	}
	
	
	public BundleSSCCVO getBundleDescriptionInfoSSCC(String codeBundle, String msisdn) {
		AvailableBundleBAMSSCC response = null;
		try {
			response = delegateBundle.getAllAvailableSSCCBundles(msisdn);
			if(!response.getBolsasDisponibles().isEmpty() && response.getBolsasDisponibles().size() > 0){
				for (int i = 0; i < response.getBolsasDisponibles().size(); i++) {
					if(codeBundle.equals(response.getBolsasDisponibles().get(i).getSnCodigo())){
						bolsaSSCC = new BundleSSCCVO();
						bolsaSSCC.setSnCodigo(response.getBolsasDisponibles().get(i).getSnCodigo());
						bolsaSSCC.setNombreBolsa(response.getBolsasDisponibles().get(i).getNombreBolsa());
						

						bolsaSSCC.setCantidad(response.getBolsasDisponibles().get(i).getCantidad());
						
						bolsaSSCC.setNombreUserAction("SSCC");
						bolsaSSCC.setCosto(response.getBolsasDisponibles().get(i).getCosto());
						break;
					}
				}
			}else{
				LOGGER.error("No es posible obtener bolsas");
			}
		} catch (ServiceException e) {
			LOGGER.error("Problema en servicio " + e.getMessage());
			e.printStackTrace();
		} catch (WSDAOException e) {
			LOGGER.error("Problema en servicio " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Excepcion lanzada " + e.getMessage());
			e.printStackTrace();
		}
		
		//Valido que la bolsa tenga datos consistentes
		if (bolsaSSCC.getNombreBolsa()==null){
		    LOGGER.error("No es posible obtener bolsa con codigo "+codeBundle+" al movil " + msisdn);
		    //Cargamos datos de prueba
		    bolsaSSCC.setSnCodigo("");
		    bolsaSSCC.setNombreBolsa("bolsa");		
		}
		
		return bolsaSSCC;
	}
	
	public BundleSSCCVO getActualBundleDescriptionInfoSSCC(String codeBundle, String msisdn) {
		AvailableBundleBAMSSCC response = null;
		try {
//			response = delegateBundle.getAllAvailableSSCCBundles(msisdn);
			response = delegateBundle.getSSCCBundles(msisdn);
			if(!response.getBolsas().isEmpty() && response.getBolsas().size() > 0){
				for (int i = 0; i < response.getBolsas().size(); i++) {
					if(codeBundle.equals(response.getBolsas().get(i).getSnCodigo())){
						bolsaSSCC = new BundleSSCCVO();
						bolsaSSCC.setSnCodigo(response.getBolsas().get(i).getSnCodigo());
						bolsaSSCC.setNombreBolsa(response.getBolsas().get(i).getNombreBolsa());
						
						// Carta Bolsa Alo Peru
						if(codeBundle.equalsIgnoreCase("CARTASERV0320")){
							bolsaSSCC.setCantidad(response.getBolsas().get(i).getCantidad());
						}else{
							// en analisis para setiar datos
//							bolsaSSCC.setCantInicial(getCantIniBolsa(response.getBolsasDisponibles().get(i).getDescripcionComercial(), 
//										response.getBolsasDisponibles().get(i).getDescripcion(), response.getBolsasDisponibles().get(i).getUnidad()));
							bolsaSSCC.setCantidad(response.getBolsas().get(i).getCantidad());
						}
						bolsaSSCC.setNombreUserAction("SSCC");
						bolsaSSCC.setCosto(response.getBolsas().get(i).getCosto());
						break;
					}
				}
			}else{
				LOGGER.error("No es posible obtener bolsas");
			}
		} catch (ServiceException e) {
			LOGGER.error("Problema en servicio " + e.getMessage());
			e.printStackTrace();
		} catch (WSDAOException e) {
			LOGGER.error("Problema en servicio " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Excepcion lanzada " + e.getMessage());
			e.printStackTrace();
		}
		
		//Valido que la bolsa tenga datos consistentes
		if (bolsaSSCC.getNombreBolsa()==null){
		    LOGGER.error("No es posible obtener bolsa con codigo "+codeBundle+" al movil " + msisdn);
		    //Cargamos datos de prueba
		    bolsaSSCC.setSnCodigo("");
		    bolsaSSCC.setNombreBolsa("bolsa");		
		}
		
		return bolsaSSCC;
	}
	
	private String getCantIniBolsa(String descripcionComercial, String descripcion, String unidad) {
		String inicial = null;
		if(!unidad.equalsIgnoreCase("MIXTA")){
			if(descripcionComercial.contains("ilimitado")){
				inicial = "ilimitado";
			}else{
				String cadena[] = descripcionComercial.split(" ", 2);
				inicial = cadena[0];
			}
		}else{
			inicial = descripcion;
		}
		return inicial;
	}



}
