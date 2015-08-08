/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.imp.helper;

import org.apache.log4j.Logger;

import com.esa.ponline.portalbolsas.bean.BolsaComprar;
import com.esa.ponline.portalbolsas.bean.BolsaDisponibleMM;
import com.esa.ponline.portalbolsas.exceptions.ws.ServiceException;
import com.esa.ponline.portalbolsas.exceptions.ws.WSDAOException;
import com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.delegate.DelegateBolsa;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * 
 * 
 */

public class BolsaHelper {
	
	private static final Logger LOGGER = Logger.getLogger(BolsaHelper.class);
	
	private DelegateBolsa delegateBolsa = new DelegateBolsa();

	public BolsaComprar getBolsaDescripcionDetalleMM(String codeBundle, String msisdn) {
	    BolsaComprar bolsaComprada = null;
	    BolsaDisponibleMM response = null;
		try {
			response = delegateBolsa.getBolsaDisponibleInfoMM(msisdn);
			if(!response.getBolsas().isEmpty() && response.getBolsas().size() > 0){
				for (int i = 0; i < response.getBolsas().size(); i++) {
					if(codeBundle.equals(response.getBolsas().get(i).getCodigo())){
					    bolsaComprada = new BolsaComprar();
					    bolsaComprada.setCodigo(response.getBolsas().get(i).getCodigo());
					    bolsaComprada.setNombre(response.getBolsas().get(i).getNombre());
						
						// Carta Bolsa Alo Peru
						if(codeBundle.equalsIgnoreCase("CARTASERV0320")){
						    bolsaComprada.setCantInicial(response.getBolsas().get(i).getDescripcionComercial());
						}else{
//							if(codeBundle.equalsIgnoreCase("CARTASERV0337")){
//								bolsaPP.setCantInicial("ilimitado");
//							}else{
						    bolsaComprada.setCantInicial(getCantIniBolsa(response.getBolsas().get(i).getDescripcionComercial(), 
										response.getBolsas().get(i).getDescripcion(), response.getBolsas().get(i).getUnidad()));
//							}
						}
						bolsaComprada.setPrecio(response.getBolsas().get(i).getPrecio());
						bolsaComprada.setPrecioInt(response.getBolsas().get(i).getPrecioInt());					
						bolsaComprada.setCuotaInicialVoucher(response.getBolsas().get(i).getCuotaInicialVoucher());
						bolsaComprada.setDescripcionComercial(response.getBolsas().get(i).getDescripcionComercial());
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
		if (bolsaComprada.getNombre()==null){
		    LOGGER.error("No es posible obtener bolsa con codigo " + codeBundle + " al movil " + msisdn);
		    //Cargamos datos de prueba
		    bolsaComprada.setCodigo("");
		    bolsaComprada.setNombre("bolsa");		
		}
		
		return bolsaComprada;
	}
	
	
//	public BundleSSCCVO getBundleDescriptionInfoSSCC(String codeBundle, String msisdn) {
//		AvailableBundleBAMSSCC response = null;
//		try {
//			response = delegateBundle.getAllAvailableSSCCBundles(msisdn);
//			if(!response.getBolsasDisponibles().isEmpty() && response.getBolsasDisponibles().size() > 0){
//				for (int i = 0; i < response.getBolsasDisponibles().size(); i++) {
//					if(codeBundle.equals(response.getBolsasDisponibles().get(i).getSnCodigo())){
//						bolsaSSCC = new BundleSSCCVO();
//						bolsaSSCC.setSnCodigo(response.getBolsasDisponibles().get(i).getSnCodigo());
//						bolsaSSCC.setNombreBolsa(response.getBolsasDisponibles().get(i).getNombreBolsa());
//						
//
//						bolsaSSCC.setCantidad(response.getBolsasDisponibles().get(i).getCantidad());
//						
//						bolsaSSCC.setNombreUserAction("SSCC");
//						bolsaSSCC.setCosto(response.getBolsasDisponibles().get(i).getCosto());
//						break;
//					}
//				}
//			}else{
//				LOGGER.error("No es posible obtener bolsas");
//			}
//		} catch (ServiceException e) {
//			LOGGER.error("Problema en servicio " + e.getMessage());
//			e.printStackTrace();
//		} catch (WSDAOException e) {
//			LOGGER.error("Problema en servicio " + e.getMessage());
//			e.printStackTrace();
//		} catch (Exception e) {
//			LOGGER.error("Excepcion lanzada " + e.getMessage());
//			e.printStackTrace();
//		}
//		
//		//Valido que la bolsa tenga datos consistentes
//		if (bolsaSSCC.getNombreBolsa()==null){
//		    LOGGER.error("No es posible obtener bolsa con codigo "+codeBundle+" al movil " + msisdn);
//		    //Cargamos datos de prueba
//		    bolsaSSCC.setSnCodigo("");
//		    bolsaSSCC.setNombreBolsa("bolsa");		
//		}
//		
//		return bolsaSSCC;
//	}
	
//	public BundleSSCCVO getActualBundleDescriptionInfoSSCC(String codeBundle, String msisdn) {
//		AvailableBundleBAMSSCC response = null;
//		try {
////			response = delegateBundle.getAllAvailableSSCCBundles(msisdn);
//			response = delegateBundle.getSSCCBundles(msisdn);
//			if(!response.getBolsas().isEmpty() && response.getBolsas().size() > 0){
//				for (int i = 0; i < response.getBolsas().size(); i++) {
//					if(codeBundle.equals(response.getBolsas().get(i).getSnCodigo())){
//						bolsaSSCC = new BundleSSCCVO();
//						bolsaSSCC.setSnCodigo(response.getBolsas().get(i).getSnCodigo());
//						bolsaSSCC.setNombreBolsa(response.getBolsas().get(i).getNombreBolsa());
//						
//						// Carta Bolsa Alo Peru
//						if(codeBundle.equalsIgnoreCase("CARTASERV0320")){
//							bolsaSSCC.setCantidad(response.getBolsas().get(i).getCantidad());
//						}else{
//							// en analisis para setiar datos
////							bolsaSSCC.setCantInicial(getCantIniBolsa(response.getBolsasDisponibles().get(i).getDescripcionComercial(), 
////										response.getBolsasDisponibles().get(i).getDescripcion(), response.getBolsasDisponibles().get(i).getUnidad()));
//							bolsaSSCC.setCantidad(response.getBolsas().get(i).getCantidad());
//						}
//						bolsaSSCC.setNombreUserAction("SSCC");
//						bolsaSSCC.setCosto(response.getBolsas().get(i).getCosto());
//						break;
//					}
//				}
//			}else{
//				LOGGER.error("No es posible obtener bolsas");
//			}
//		} catch (ServiceException e) {
//			LOGGER.error("Problema en servicio " + e.getMessage());
//			e.printStackTrace();
//		} catch (WSDAOException e) {
//			LOGGER.error("Problema en servicio " + e.getMessage());
//			e.printStackTrace();
//		} catch (Exception e) {
//			LOGGER.error("Excepcion lanzada " + e.getMessage());
//			e.printStackTrace();
//		}
//		
//		//Valido que la bolsa tenga datos consistentes
//		if (bolsaSSCC.getNombreBolsa()==null){
//		    LOGGER.error("No es posible obtener bolsa con codigo "+codeBundle+" al movil " + msisdn);
//		    //Cargamos datos de prueba
//		    bolsaSSCC.setSnCodigo("");
//		    bolsaSSCC.setNombreBolsa("bolsa");		
//		}
//		
//		return bolsaSSCC;
//	}
	
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
