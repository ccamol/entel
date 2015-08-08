/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.orden.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import org.apache.log4j.Logger;

import com.bea.netuix.servlets.controls.html.Li;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.administracion.suscripciones.delegate.SuscripcionesDelegate;
import com.epcs.bean.BolsaBean;
import com.epcs.bean.BolsaPPBean;
import com.epcs.bean.CodeDescBean;
import com.epcs.bean.GrupoBolsaBean;
import com.epcs.bean.ResumenPlan;
import com.epcs.bean.RutBean;
import com.epcs.bean.TransaccionGTMBean;
import com.epcs.bean.ZonaPerfilBean;
import com.epcs.cliente.orden.delegate.BolsaDelegate;
import com.epcs.cliente.perfil.delegate.PlanDelegate;
import com.epcs.inteligencianegocio.satisfaccioncliente.util.EstadisticasHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.vtasymktg.fidelizacion.delegate.BeneficiosDelegate;

/**
 * Meneja listado de bolsas disponibles, y compra de bolsas para mercado pp
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class MisBolsasPPController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = Logger
            .getLogger(MisBolsasPPController.class);
    
    private ResumenPlan resumenPlan;
    
    private PlanDelegate planDelegate;
    private BolsaDelegate bolsaDelegate;
    private SuscripcionesDelegate suscripcionesDelegate;
        
    private List<BolsaBean> bolsasDispRegalo;
    private List<GrupoBolsaBean> bolsasDispPPScobRegalo;
    private List<BolsaPPBean> bolsasActualesPP;
    
    private boolean existenBolsasDispReg = false;
    private boolean existenBolsasContratadas = false;
    
	// Validacion prestaluka
    private BeneficiosDelegate beneficiosDelegate;
    private ZonaPerfilBean zonaPerfilBean;
    private static final String CODSBAD_PRESTALUKA = MiEntelProperties.getProperty("zonaEntel.prestaLukaService.codsbad");
    private String mensajeErrorPrestaLuka;
    private boolean validoPrestaLuka = true;
    private List<GrupoBolsaBean> bolsasMasVendidas;    
    String tiposBolsasMasVendidas = ParametrosHelper.getTipoDeBolsasMasVendidas() ;
    
    private String pruebas;
    
    private TransaccionGTMBean transGTM;
    
    
    
    public boolean isValidoPrestaLuka() {
		return validoPrestaLuka;
	}

	public String getMensajeErrorPrestaLuka() {
		return mensajeErrorPrestaLuka;
	}
	
	public BeneficiosDelegate getBeneficiosDelegate() {
		return beneficiosDelegate;
	}

	public void setBeneficiosDelegate(BeneficiosDelegate beneficiosDelegate) {
		this.beneficiosDelegate = beneficiosDelegate;
	}
	//Validacion Usuario MP1 y Bolsas Plus
	private static final String CATEGORIA_CLIENTE_PP = MiEntelProperties.getProperty("parametros.categoriausuario.diferenciacioncanalespp.altovalor");
    private boolean validoBolsasPlus= false;
    private List<GrupoBolsaBean> bolsasDispPPScobAltoValor;

    
    public boolean isValidoBolsasPlus(){
    	return validoBolsasPlus;
    }
    
    
    /**
     * Inicializa las listas de bolsas disponibles y activas para PP 
     * @param phase
     */
    public void init(final PhaseEvent phase) {
        try {

            final ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            final String msisdn = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroPcsSeleccionado");

            final String mercado = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "mercado");
            
            String flagBam = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"flagBam");
            String rutSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuarioSeleccionado");

            resumenPlan = obtenerPlanResumen(msisdn);

            // Consultar Bolsa disponibles para comprar.
            //bolsasDispRegalo = consultarBolsasDisponibleRegalo(msisdn, mercado);            
            bolsasDispPPScobRegalo  = buildGruposBolsa(suscripcionesDelegate.obtenerBolsasDisponiblesScob(msisdn));
            
           existenBolsasDispReg = (bolsasDispPPScobRegalo != null && !bolsasDispPPScobRegalo
                    .isEmpty());
             //Consultar Bolsas PP plus Disponibles
           
    
        bolsasDispPPScobAltoValor =getBolsasDisponiblesPPAltoValor(bolsasDispPPScobRegalo);
            //bolsasActualesPP = bolsaDelegate.obtenerBolsasOneShot(msisdn);
            bolsasActualesPP = bolsaDelegate.obtenerBolsasScob(msisdn);
            existenBolsasContratadas = (bolsasActualesPP != null && !bolsasActualesPP
                    .isEmpty());
            
            // Solo para mercados PP (voz) --> mas adelante para CC
            
            if(MiEntelBusinessHelper.isMercadoPrepago(mercado)){            	
            
              RutBean rutBean = new RutBean(rutSeleccionado);
               zonaPerfilBean = beneficiosDelegate.getZonaPerfil(msisdn, rutBean.getNumero()+"0"+rutBean.getDigito());               
                
               String[] codsBad = CODSBAD_PRESTALUKA.split(",");
        		for(String cod : codsBad){
        			if(zonaPerfilBean.getStatusRespuesta().equals(cod)){
        				validoPrestaLuka = false;
        				break;
        			}
        		}
               
                if(!isValidoPrestaLuka()){                 	                    
            		mensajeErrorPrestaLuka = MiEntelProperties.getServiceMessages().getErrorMessage("bolsasComprar.prestalukaNoValido");                  	              
                }
            }
            
            
            EstadisticasHelper.agregarMarcaEstadistica(EstadisticasHelper.TRANSACCION_OK,EstadisticasHelper.GRUPO_BOLSAS,EstadisticasHelper.BOLSAS_PANTALLA_COMPRAR);

        } catch (DAOException ex) {
            LOGGER.error("DAOException al inespeado consultar bolsas", ex);
            JSFMessagesHelper.addServiceErrorMessage("serviceDisabled");
            EstadisticasHelper.agregarMarcaEstadistica(EstadisticasHelper.TRANSACCION_NO_OK,EstadisticasHelper.GRUPO_BOLSAS,EstadisticasHelper.BOLSAS_PANTALLA_COMPRAR);
        } catch (ServiceException e) {
            LOGGER.info("ServiceException de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("clienteOrden", e
                    .getCodigoRespuesta());
            EstadisticasHelper.agregarMarcaEstadistica(EstadisticasHelper.TRANSACCION_NO_OK,EstadisticasHelper.GRUPO_BOLSAS,EstadisticasHelper.BOLSAS_PANTALLA_COMPRAR);
        } catch (Exception e) {
            LOGGER.error("Exception inespeado consultar bolsas", e);
            JSFMessagesHelper.addServiceErrorMessage("serviceDisabled");
            EstadisticasHelper.agregarMarcaEstadistica(EstadisticasHelper.TRANSACCION_NO_OK,EstadisticasHelper.GRUPO_BOLSAS,EstadisticasHelper.BOLSAS_PANTALLA_COMPRAR);
        }
    }
    
    /**
     * Validacion para bolsas plus
     * @throws Exception 
     */
	private String[] getPreferencesBolsasPPAltoValor() {
		FacesContext context = FacesContext.getCurrentInstance();
		PortletRequest req = (PortletRequest) context.getExternalContext().getRequest();
		String unidadBolsasPlus = req.getPreferences().getValue("BolsasPPAltoValor", null);
		return unidadBolsasPlus != null ? unidadBolsasPlus.split(",") : null;
	}

    
  
	private List<GrupoBolsaBean> getBolsasDisponiblesPPAltoValor(List<GrupoBolsaBean> bolsasDispPPScobRegalo) {
		List<BolsaPPBean> listadoBolsasPlus = new ArrayList<BolsaPPBean>();
		List<GrupoBolsaBean> grupoListadoBolsasPlus = new ArrayList<GrupoBolsaBean>();
		GrupoBolsaBean grupoBolsasPlus = new GrupoBolsaBean();
		
		String categoriaUserPP = "";

		try {
			ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
			categoriaUserPP = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "categoriaCliente");

			if (categoriaUserPP != null && categoriaUserPP.equals(CATEGORIA_CLIENTE_PP)) {
				String[] unidadBolsasPlus = getPreferencesBolsasPPAltoValor();

				if (bolsasDispPPScobRegalo != null && unidadBolsasPlus != null) {

					for (String idBolsasPlus : unidadBolsasPlus) {

						Iterator<GrupoBolsaBean> itGrupoBolsasBean = bolsasDispPPScobRegalo.iterator();

						while (itGrupoBolsasBean.hasNext()) {

							GrupoBolsaBean itGrupoBolsaBeanTemp = itGrupoBolsasBean.next();
							Iterator<BolsaPPBean> itBolsaPPBean = itGrupoBolsaBeanTemp.getBolsasPPDisponibles().iterator();

							while (itBolsaPPBean.hasNext()) {

								BolsaPPBean bolsaPPBean = itBolsaPPBean.next();

								if (bolsaPPBean.getTipoBolsa().equalsIgnoreCase(idBolsasPlus)) {

									bolsaPPBean.setTipoBolsaPlus(itGrupoBolsaBeanTemp.getTipoBolsaSinAcento());
									listadoBolsasPlus.add(bolsaPPBean);
									itBolsaPPBean.remove();
								}
							}

						}

					}
					if (listadoBolsasPlus != null&& !listadoBolsasPlus.isEmpty()) {
						validoBolsasPlus = true;
						grupoBolsasPlus.setBolsasPPDisponiblesAltoValor(listadoBolsasPlus);
						grupoListadoBolsasPlus.add(grupoBolsasPlus);

					}

				}
			}

		} catch (Exception e) {
			LOGGER
					.error("Error Inesperado al cargar la categoria del usuario pp");
		}
		return grupoListadoBolsasPlus != null && !grupoListadoBolsasPlus.isEmpty() ? grupoListadoBolsasPlus: null;

	}
   
    
    
    
    
    /**
     * 
     * @param msisdn
     */
    private List<BolsaBean> consultarBolsasDisponibleRegalo(final String msisdn,
            final String mercado) {
        List<BolsaBean> bolsasDispoRegaladoTemp = new ArrayList<BolsaBean>();
        try {

            final String flagMercado = MiEntelProperties
                    .getProperty("parametros.flagmercado.".concat(mercado));
            LOGGER.info("flagMercado: " + flagMercado);
            bolsasDispoRegaladoTemp = this.ordenarBolsasDestacadas(suscripcionesDelegate
                    .consultarBolsasDisponiblesRegalo(msisdn, flagMercado));

        } catch (DAOException ex) {
            LOGGER.error("Error al inespeado consultar bolsas Regaladas", ex);
            JSFMessagesHelper.addServiceErrorMessage("serviceDisabled");
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("clienteOrden", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Error al inespeado consultar bolsas Regaladas", e);
            JSFMessagesHelper.addServiceErrorMessage("serviceDisabled");
        }
        return bolsasDispoRegaladoTemp;
    }
   
    /**
     * Obtener el saldo del equipo prepago. 
     * @param msisdn
     * @return ResumenPlan,   null si se presenta una excepcion 
     */
    private ResumenPlan obtenerPlanResumen(final String msisdn) {
        ResumenPlan resumenPlan = null;
        try {
            resumenPlan = planDelegate.getPlanResumenPP(msisdn);
        } catch (Exception e) {
            LOGGER.error("No se pudo obtener resumen plan :"+msisdn,e);
        }
        return resumenPlan;
    }
    
    /**
     * Action para comprar una bolsa pp
     * @return "" postback
     */
    public String comprarBolsaPP() {
        try {
            final ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            final String msisdn = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroPcsSeleccionado");

            final String descuentoStr = MiEntelProperties
                    .getProperty("parametros.comprarbolsa.descuento");
//            String msj = "";
            
            final String cartaServicio = JsfUtil.getRequestParameter("cartaServicio");
            final String nombreBolsa = JsfUtil.getRequestParameter("nombreBolsa");
            final String tipoBolsa = JsfUtil.getRequestParameter("tipoBolsa");
            final double valorBolsa = Double.parseDouble(JsfUtil.getRequestParameter("valorBolsa"));
            final double descuento = Double.parseDouble(descuentoStr);
            //Obtener informacion de saldo.            
            resumenPlan = (resumenPlan == null) ? this.obtenerPlanResumen(msisdn) : resumenPlan;
            
            if (resumenPlan != null) {
                // Se valida el saldo.
                if ((resumenPlan.getSaldo() + descuento) <= valorBolsa) {
                    JSFMessagesHelper.addServiceErrorMessage("saldoinsuficientepp");
                }
                else {
                    //bolsaDelegate.comprarBolsasOneShotPP(msisdn,
                    //cartaServicio, valorBolsa);
                	
                	bolsaDelegate.comprarBolsasScobPP(msisdn,cartaServicio);
                	
//                    msj = MiEntelProperties.getServiceMessages().getRb()
//                            .getString("success.clienteOrden.bolsacomprada");
//                    msj = msj.replace("{0}", nombreBolsa);
                    String[] args = {nombreBolsa};
                    JSFMessagesHelper.addSuccessMessage("clienteOrden", "bolsacomprada", args);
                    EstadisticasHelper.agregarMarcaEstadistica(EstadisticasHelper.TRANSACCION_OK,EstadisticasHelper.GRUPO_BOLSAS,EstadisticasHelper.BOLSAS_CONFIRMAR_COMPRAR);
                    
                    // Carga de datos para el marcado GTM
                    transGTM = new TransaccionGTMBean();
                    transGTM.setIdTransaccion(msisdn.substring(msisdn.length() - 4));
                    transGTM.setSkuID(cartaServicio);
                    transGTM.setNombre(nombreBolsa);
                    transGTM.setNuevoValor(String.valueOf(valorBolsa));
                    transGTM.setCostoOperacional(0);
                    transGTM.setNumeroPlanes(1);
                    transGTM.setNumeroOperaciones(1);                 
                    transGTM.setTipoProducto(tipoBolsa);
                    transGTM.setValorTransaccion(valorBolsa + transGTM.getCostoOperacional());
                    
                }
            }else{
                JSFMessagesHelper.addServiceErrorMessage("noinfosaldo");
            }

        } catch (DAOException e) {
            LOGGER.error("Exception inesperado al comprar bolsa pp", e);
            JSFMessagesHelper.addServiceErrorMessage("nocomprabolsa");
            EstadisticasHelper.agregarMarcaEstadistica(EstadisticasHelper.TRANSACCION_NO_OK,EstadisticasHelper.GRUPO_BOLSAS,EstadisticasHelper.BOLSAS_CONFIRMAR_COMPRAR);
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("clienteOrden", e
                    .getCodigoRespuesta());
            EstadisticasHelper.agregarMarcaEstadistica(EstadisticasHelper.TRANSACCION_NO_OK,EstadisticasHelper.GRUPO_BOLSAS,EstadisticasHelper.BOLSAS_CONFIRMAR_COMPRAR);
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al comprar bolsa pp", e);
            JSFMessagesHelper.addServiceErrorMessage("nocomprabolsa");
            EstadisticasHelper.agregarMarcaEstadistica(EstadisticasHelper.TRANSACCION_NO_OK,EstadisticasHelper.GRUPO_BOLSAS,EstadisticasHelper.BOLSAS_CONFIRMAR_COMPRAR);
        }
        return "";
    }
    
	/**
	 * Ordena las bolsas PP colocando de primero las destacadas, las cuales
	 * se especifican en el preference "destacadas" del portlet bolsasPP
	 * @param bolsas Listado de bolsas a ordenar
	 * @return Bolsas ordenadas, colocando de primero las destacadas
	 */
    private List<BolsaBean> ordenarBolsasDestacadas(List<BolsaBean> bolsas) {
		PortletPreferences prefs = JSFPortletHelper.getPreferencesObject();
		String prefDestacadas = JSFPortletHelper.getPreference(prefs, "destacadas", "");
		List<BolsaBean> destacadas = new ArrayList<BolsaBean>();
		List<BolsaBean> noDestacadas = new ArrayList<BolsaBean>();
		List<BolsaBean> ordenadas = new ArrayList<BolsaBean>();
		
		for (BolsaBean bolsa : bolsas) {
			if (prefDestacadas.contains(bolsa.getSnCodigo())) {
				destacadas.add(bolsa);
			} else {
				noDestacadas.add(bolsa);
			}
		}
    
		ordenadas.addAll(destacadas);
		ordenadas.addAll(noDestacadas);
		
		return ordenadas;
	}
    
    
    /**
     * Agrupa las bolsas por tipo.
     * 
     * @param listBolsas
     * @return List<GrupoBolsaBean>
     */
    private List<GrupoBolsaBean> buildGruposBolsa(List<BolsaPPBean> listBolsas) {
        List<GrupoBolsaBean> listGrupoBolsa = new ArrayList<GrupoBolsaBean>();
        // Tipos de Bolsa.
        List<CodeDescBean> tiposBolsas = ParametrosHelper.getListTiposBolsaPP();

        GrupoBolsaBean grupoBolsa;
        List<BolsaPPBean> listTemp;
        for (CodeDescBean codeDescBean : tiposBolsas) {
            grupoBolsa = new GrupoBolsaBean();
            grupoBolsa.setTipoBolsa(codeDescBean.getDescripcion());
            grupoBolsa.setTipoBolsaSinAcento(Utils.removerAcentos(codeDescBean.getDescripcion()));
            grupoBolsa.setCodBolsa(codeDescBean.getCodigo());
            listTemp = new ArrayList<BolsaPPBean>();
            Iterator<BolsaPPBean> itetator = listBolsas.iterator();
            while (itetator.hasNext()) {
            	BolsaPPBean bolsa = itetator.next();
            	if(codeDescBean.getCodigo().contains(tiposBolsasMasVendidas)){
            		 if (codeDescBean.getCodigo().contains(bolsa.getOrden())) {
                         listTemp.add(bolsa);                       
                     }
            	}else if (codeDescBean.getCodigo().contains(bolsa.getUnidad())) {
                    listTemp.add(bolsa);  
                    itetator.remove();
                }
            }
            grupoBolsa.setBolsasPPDisponibles(listTemp);
            // Si el grupo tiene bolsas, se agregan para ser visualizadas.
            if (!listTemp.isEmpty()) {
                listGrupoBolsa.add(grupoBolsa);
            }
        }
        return listGrupoBolsa;
    }
    
    
    /**
     * @param planDelegate the planDelegate to set
     */
    public void setPlanDelegate(final PlanDelegate planDelegate) {
        this.planDelegate = planDelegate;
    }

    /**
     * @return the resumenPlan
     */
    public ResumenPlan getResumenPlan() {
        return resumenPlan;
    }

    /**
     * @param resumenPlan the resumenPlan to set
     */
    public void setResumenPlan(final ResumenPlan resumenPlan) {
        this.resumenPlan = resumenPlan;
    }

    /**
     * @return the bolsasDispRegalo
     */
    public List<BolsaBean> getBolsasDispRegalo() {
        return bolsasDispRegalo;
    }

    /**
     * @return the bolsasActualesPP
     */
    public List<BolsaPPBean> getBolsasActualesPP() {
        return bolsasActualesPP;
    }

    /**
     * @return the existenBolsasDispReg
     */
    public boolean isExistenBolsasDispReg() {
        return existenBolsasDispReg;
    }

    /**
     * @return the existenBolsasContratadas
     */
    public boolean isExistenBolsasContratadas() {
        return existenBolsasContratadas;
    }

    /**
     * @param bolsaDelegate the bolsaDelegate to set
     */
    public void setBolsaDelegate(final BolsaDelegate bolsaDelegate) {
        this.bolsaDelegate = bolsaDelegate;
    }

    /**
     * @param suscripcionesDelegate the suscripcionesDelegate to set
     */
    public void setSuscripcionesDelegate(final SuscripcionesDelegate suscripcionesDelegate) {
        this.suscripcionesDelegate = suscripcionesDelegate;
    }

    /**
     * @return the suscripcionesDelegate
     */
    public SuscripcionesDelegate getSuscripcionesDelegate() {
        return suscripcionesDelegate;
    }

    /**
     * @return the planDelegate
     */
    public PlanDelegate getPlanDelegate() {
        return planDelegate;
    }

    /**
     * @return the bolsaDelegate
     */
    public BolsaDelegate getBolsaDelegate() {
        return bolsaDelegate;
    }

    /**
     * @param bolsasDispRegalo the bolsasDispRegalo to set
     */
    public void setBolsasDispRegalo(final List<BolsaBean> bolsasDispRegalo) {
        this.bolsasDispRegalo = bolsasDispRegalo;
    }

    /**
     * @param bolsasActualesPP the bolsasActualesPP to set
     */
    public void setBolsasActualesPP(final List<BolsaPPBean> bolsasActualesPP) {
        this.bolsasActualesPP = bolsasActualesPP;
    }

    /**
     * @param existenBolsasDispReg the existenBolsasDispReg to set
     */
    public void setExistenBolsasDispReg(final boolean existenBolsasDispReg) {
        this.existenBolsasDispReg = existenBolsasDispReg;
    }

    /**
     * @param existenBolsasContratadas the existenBolsasContratadas to set
     */
    public void setExistenBolsasContratadas(final boolean existenBolsasContratadas) {
        this.existenBolsasContratadas = existenBolsasContratadas;
    }

	/**
	 * @return the bolsasDispPPScobRegalo
	 */
	public List<GrupoBolsaBean> getBolsasDispPPScobRegalo() {
		return bolsasDispPPScobRegalo;
	}

	/**
	 * @param bolsasDispPPScobRegalo the bolsasDispPPScobRegalo to set
	 */
	public void setBolsasDispPPScobRegalo(
			List<GrupoBolsaBean> bolsasDispPPScobRegalo) {
		this.bolsasDispPPScobRegalo = bolsasDispPPScobRegalo;
	}

	public List<GrupoBolsaBean> getBolsasMasVendidas() {
		return bolsasMasVendidas;
	}

	public void setBolsasMasVendidas(List<GrupoBolsaBean> bolsasMasVendidas) {
		this.bolsasMasVendidas = bolsasMasVendidas;
	}

	/**
	 * @return the tiposBolsasMasVendidas
	 */
	public String getTiposBolsasMasVendidas() {
		return tiposBolsasMasVendidas;
	}

	/**
	 * @param tiposBolsasMasVendidas the tiposBolsasMasVendidas to set
	 */
	public void setTiposBolsasMasVendidas(String tiposBolsasMasVendidas) {
		this.tiposBolsasMasVendidas = tiposBolsasMasVendidas;
	}

	public TransaccionGTMBean getTransGTM() {
		return transGTM;
	}
	public List<GrupoBolsaBean> getBolsasDispPPScobAltoValor(){
		return bolsasDispPPScobAltoValor;
	}
	
	public void setBolsasDispPPScobAltoValor(List<GrupoBolsaBean> bolsasDispPPScobAltoValor){
		this.bolsasDispPPScobAltoValor = bolsasDispPPScobAltoValor;
	}
	

	

}