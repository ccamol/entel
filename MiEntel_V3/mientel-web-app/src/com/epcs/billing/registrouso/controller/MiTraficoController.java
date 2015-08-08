/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.registrouso.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.netuix.servlets.controls.page.BookPresentationContext;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.DetalleMiTraficoSSBean;
import com.epcs.bean.GrupoClienteBean;
import com.epcs.bean.PlanesFullBean;
import com.epcs.bean.PlanesMultimediaBean;
import com.epcs.bean.RutBean;
import com.epcs.bean.TasacionBean;
import com.epcs.billing.registrouso.delegate.TraficoDelegate;
import com.epcs.cliente.perfil.delegate.EquipoDelegate;
import com.epcs.cliente.perfil.delegate.PlanDelegate;
import com.epcs.cliente.perfil.util.PlanHelper;
import com.epcs.recursoti.configuracion.DateHelper;
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

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class MiTraficoController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = Logger
    .getLogger(MiTraficoController.class);
    
    private TraficoDelegate traficoDelegate;
    
    public static final String TIPO_PLAN_EXCEDIDO = MiEntelProperties.getProperty("servicios.codigoRespuesta.tipoPlan.excedido");
    
    private List<DetalleMiTraficoSSBean> listDetalleMiTraficoSSBean;
    private List<DetalleMiTraficoSSBean> listDetalleMiTraficoAnteriores = new LinkedList<DetalleMiTraficoSSBean>();
    private DetalleMiTraficoSSBean detalleMiTraficoPlanVigente;
    private HtmlPanelGroup panelTraficoAnterior = new HtmlPanelGroup();
    private HtmlPanelGroup panelTrafico = new HtmlPanelGroup();
    
    private String descripcionPlanTrafico;
    
    private boolean tienePlanAnteriores = false;
    private boolean periodoActual = true;
    private PlanesMultimediaBean planesMultimediaBean;
    private PlanDelegate planDelegate;
    private boolean errorPlanMMexcedido = true;  
    

    
    //BORRAR
    
    private EquipoDelegate equipoDelegate;
    
    
    public EquipoDelegate getEquipoDelegate() {
		return equipoDelegate;
	}

	public void setEquipoDelegate(EquipoDelegate equipoDelegate) {
		this.equipoDelegate = equipoDelegate;
	}
	// FIN BORRAR
	
    
    /**
     * 
     * @param event
     */
	public void init(final PhaseEvent event) {

		String numeroPcsSeleccionado = "";
		String mercado = "";
		String aaa = "";
		LOGGER.info("<<<<<< INIT MITRAFICOCONTROLLER >>>>>>");

		try {

			final ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());
			numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "numeroPcsSeleccionado");
			mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
					"mercado");
			aaa = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
			"aaa");
			
			//Obtiene Mi trafico
			this.searchMiTrafico(mercado, numeroPcsSeleccionado);
			
			//Obtiene descripcion del plan
			this.searchDescripcionPlan(numeroPcsSeleccionado, mercado, aaa);
			LOGGER.info("periodoActual >>>>> " + periodoActual );
		} catch (Exception e) {
			LOGGER.error("Exception no esperada al buscar datos de usuario", e);
			JSFMessagesHelper.addServiceErrorMessage("inesperado");
			panelTrafico.setRendered(false);
		}
	}

	/**
	 * 
	 * @param mercado
	 * @param numeroPcsSeleccionado
	 */
	private void searchMiTrafico(String mercado, String numeroPcsSeleccionado) {

		try {
			//Nuevo Estructura
			ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
			
			panelTraficoAnterior.setRendered(false);

			final String periodo = JSFPortletHelper.getRequest().getParameter(
					"periodo");
			
			if (periodo != null && periodo.equals("1")) {
				periodoActual = false;
			}

			/*
			 * Este metodo fue modificado para indicarle si debe registrar 
			 * flujo en el log de la cajaTrafico del Dashboad. Esto se hace
			 * pasandole <code>true</code> o <code>false</code>
			 */
			
			listDetalleMiTraficoSSBean = this.traficoDelegate.obtenerMiTrafico(
					numeroPcsSeleccionado, periodo == null ? "0" : periodo,
					mercado, false);
			
			System.out.println("Fuera for: " + listDetalleMiTraficoSSBean);
			
			if (listDetalleMiTraficoSSBean != null
					&& !listDetalleMiTraficoSSBean.isEmpty()) {

				for (int i = 0; i < listDetalleMiTraficoSSBean.size(); i++) {
					
					
					/*
					 * Nuevo Estructura Tarifaria
					 */
					String rutTitular = null;
		            String pageLabel = getPageLabelActual();
		             
		            System.out.println("PageLabel MiTraficoController: "+pageLabel);
		            if(mercado.equals("PP")){
		             	 rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuario");
		            }else{
		             	rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutTitular");
		            }
		             
		            rutTitular = rutTitular == null ? ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuarioSeleccionado") : rutTitular;
		             
		            String nroCuenta = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroCuenta");
		            
		            RutBean rt = new RutBean(rutTitular);
		            String rutSinDV = rutTitular != null ? rutTitular.substring(0, rutTitular.length()-1) : "";
		            String DV = String.valueOf(rt.getDigito());
		         	String codigoGrupo = null;
		         	String rutFormated = rutSinDV.concat(DV.toUpperCase());
		         	
		         	try {
		         		 //EquipoDelegate delegateEquipo = new EquipoDelegate();
		         		 System.out.println("MiTraficoController - rutFormated"+rutFormated+ " nroCuenta"+nroCuenta);
		         	//GrupoClienteBean grupoCliente = delegateEquipo
		 			//.obtenerGrupoCliente(rutFormated, nroCuenta);
		         	GrupoClienteBean grupoCliente = this.equipoDelegate.obtenerGrupoCliente(rutFormated, nroCuenta);
		         	//MODIFICARRRRRRRRRRRRRRRRRR
		         	//codigoGrupo = "13";
		         	codigoGrupo = grupoCliente.getCodigoGrupo();
		         	System.out.println("MiTraficoController - Grupo"+codigoGrupo);		
		         	if (ParametrosHelper.getExisteParametro("equipo.grupoCliente.empresa",codigoGrupo)
		         			&& ParametrosHelper.getExisteParametro("grupoCliente.excentoTrafico.empresa",codigoGrupo) && pageLabel != null
		         			&& pageLabel.contains("trafico")) {
		         		System.out.println("Existe");	
		         		//resumenTrafico.setMostrarSeccionExcedidoDash("1");
		         		listDetalleMiTraficoSSBean.get(i).setCargarCargoFijo("0");
		         		//cargarCargoFijo = "0";
		 			}else if(ParametrosHelper.getExisteParametro("equipo.grupoCliente.empresa",codigoGrupo) && pageLabel != null
		         			&& pageLabel.contains("trafico")){
		 				System.out.println("No Existe");
		 				System.out.println("No Existe");
		 				//resumenTrafico.setMostrarSeccionExcedidoDash("0");
		 				//cargarCargoFijo = "1";
		 				listDetalleMiTraficoSSBean.get(i).setCargarCargoFijo("1");
		 				System.out.println("else if listDetalleMiTraficoSSBean.set(i).CargarCargoFijo: " + listDetalleMiTraficoSSBean.get(i).getCargarCargoFijo());
		 			}else{
		 				//cargarCargoFijo = "0";
		 				System.out.println("Else - MiTraficoController");
		 				listDetalleMiTraficoSSBean.get(i).setCargarCargoFijo("0");
		 			}
		         	
		         	 } catch (Exception e) {
		         		 	System.out.println("Error al verificar el grupo del cliente");	
		 					LOGGER.error("Error al verificar el grupo del cliente.", e);
		 			 }
		             /*
					  * Fin Nuevo Estructura Tarifaria
				     */ 
		         	listDetalleMiTraficoSSBean.get(i).getPlanBean().setTipoPlan("17");
		         	System.out.println("MiTraficoController - Tipo Plan: " + listDetalleMiTraficoSSBean.get(i).getPlanBean().getTipoPlan());
		         	System.out.println("MiTraficoController - listDetalleMiTraficoSSBean.get(i).CargarCargoFijo: " + listDetalleMiTraficoSSBean.get(i).getCargarCargoFijo());
		         	System.out.println("MiTraficoController listDetalleMiTraficoSSBean.get(i).getPlanBean().getTotalMinutos: " + listDetalleMiTraficoSSBean.get(i).getPlanBean().getTotalMinutos());
		         	
		         	if (i == 0) {
						detalleMiTraficoPlanVigente = listDetalleMiTraficoSSBean
								.get(i);
						
						LOGGER.info("detalleMiTraficoPlanVigente.getTraficoVoz().getFechaFinal() :::: " + detalleMiTraficoPlanVigente.getTraficoVoz().getFechaFinal() );
						LOGGER.info("detalleMiTraficoPlanVigente.getTraficoVoz().getTotal() :::: " + detalleMiTraficoPlanVigente.getTraficoVoz().getTotal() );
						LOGGER.info("detalleMiTraficoPlanVigente.getTraficoVoz().getDetallePorHorario().size() :::: " + detalleMiTraficoPlanVigente.getTraficoVoz().getDetallePorHorario() );
						
					} else {
						tienePlanAnteriores = true;
						listDetalleMiTraficoAnteriores
								.add(listDetalleMiTraficoSSBean.get(i));
						panelTraficoAnterior.setRendered(true);
					}
		         	
				}

				if (MiEntelBusinessHelper.isMercadoSuscripcion(mercado)) {
					if (periodoActual) {
						if (listDetalleMiTraficoSSBean.get(0) != null) {
							try {
								PlanesFullBean planesFullBean;

								if (listDetalleMiTraficoSSBean.get(0)
										.getPlanBean() != null) {									
									 									
									planesFullBean = planDelegate
											.consultaXmlPlanesFull(
													listDetalleMiTraficoSSBean
															.get(0)
															.getPlanBean()
															.getCodbscs2(), "");
									if (planesFullBean != null) {
										if (planesFullBean.getPlanExcedido()
												.equals(TIPO_PLAN_EXCEDIDO)) {	
											
											this.planesMultimediaBean = this
													.consultarPlanMultimediaExcedido(numeroPcsSeleccionado,planesFullBean.getValorMBExcedido());
											if (planesMultimediaBean != null) {
												this.planesMultimediaBean
														.setCondProrrateo(planesFullBean
																.getCondProrrateo());
											}
										}
									}
								}

							} catch (Exception e) {
								LOGGER
										.error(
												"Error al buscar el codigo codbscs2 en la listDetalleMiTraficoSSBean ",
												e);
							}
						}
					}
				}
				
			} else {
				
				panelTrafico.setRendered(false);
				JSFMessagesHelper.addErrorCodeMessage("gestionRegistroUso",
						"0002");

			}

		} catch (DAOException e) {
			LOGGER.error("Error al buscar otros datos de Usuario", e);
			JSFMessagesHelper.addServiceErrorMessage("noDisponible");
			panelTrafico.setRendered(false);
		} catch (ServiceException e) {
			LOGGER.info("Error al buscar otros datos de Usuario");
			JSFMessagesHelper.addErrorCodeMessage("gestionRegistroUso", e
					.getCodigoRespuesta());
			panelTrafico.setRendered(false);
		} catch (Exception e) {
			LOGGER.error("Exception no esperada al buscar datos de usuario", e);
			JSFMessagesHelper.addServiceErrorMessage("inesperado");
			panelTrafico.setRendered(false);
		}

	}
	
	/**
	 * 
	 * @param numeroPcsSeleccionado
	 * @param mercado
	 * @param aaa
	 */
	private void searchDescripcionPlan(String numeroPcsSeleccionado, String mercado, String aaa) {

		try {
			descripcionPlanTrafico = PlanHelper
					.obtenerBreveDescripcionPlan(this.traficoDelegate
							.obtenerPlan(numeroPcsSeleccionado, mercado, aaa));
			
		} catch (DAOException e) {
			LOGGER.error("Error al buscar Descripcion del plan", e);
			JSFMessagesHelper.addServiceErrorMessage("noDisponible");
			panelTrafico.setRendered(false);
		} catch (ServiceException e) {
			LOGGER.info("Error al buscar Descripcion del plan");
			JSFMessagesHelper.addErrorCodeMessage("gestionRegistroUso", e
					.getCodigoRespuesta());
			panelTrafico.setRendered(false);
		} catch (Exception e) {
			LOGGER.error("Exception no esperada al buscar Descripcion del plan", e);
			JSFMessagesHelper.addServiceErrorMessage("inesperado");
			panelTrafico.setRendered(false);
		}

	}
	
	
	/**
	 * Metodo consulta la informacion del un plan multimedia excedido
     * @author Wilson Brochero Munoz
     * @param  msisdn   
     * @param  codbscs2 
     * @return PlanesMultimediaBean
     * @throws Exception
     * @throws ServiceException
     * @throws DAOException
     */
    private PlanesMultimediaBean consultarPlanMultimediaExcedido(String msisdn , String valorMBExcedido){    	
    	
		PlanesMultimediaBean planesMultimediaBean;
		planesMultimediaBean = new PlanesMultimediaBean();
		try {

			planesMultimediaBean = planDelegate
					.consultarPlanMultimediaExcedido(msisdn);
			
			if(planesMultimediaBean!=null){
				
				String velocidadPlan = planesMultimediaBean.getVelocidadPlan();
			    Double cuotaTraficoUtil = Double.valueOf(Utils.isEmptyString(planesMultimediaBean.getTotalTrafico() ) ? "0.0" : planesMultimediaBean.getTotalTrafico())/1024;			
			    Double valorTotalTrafico = Double.valueOf(Utils.isEmptyString(planesMultimediaBean.getCuotaTraficoUtil()) ? "0.0" : planesMultimediaBean.getCuotaTraficoUtil())/1024;
			    //Double valorMBExcedido = Double.valueOf(Utils.isEmptyString(planesMultimediaBean.getValorMBExcedido()) ? "0" : planesMultimediaBean.getValorMBExcedido());		
			    Double totalTrafico = Double.valueOf(Utils.isEmptyString(planesMultimediaBean.getTotalTrafico()) ? "0.0" : planesMultimediaBean.getTotalTrafico())/1024;					
			    Double valorTraficoExcedido = Double.valueOf(Utils.isEmptyString(planesMultimediaBean.getTraficoExcedido()) ? "0.0" : planesMultimediaBean.getTraficoExcedido())/1024;
			    							    
				Double promedioConsumo =0.0;
				Double promedioUtilImg =0.0;	
				
				if(valorTotalTrafico>=cuotaTraficoUtil){					
					promedioConsumo = Double.valueOf(cuotaTraficoUtil);
					promedioConsumo = (promedioConsumo / Double.valueOf(valorTotalTrafico)) * 100;
					promedioUtilImg = promedioConsumo - (promedioConsumo % 10);
				}else {
					promedioUtilImg=promedioConsumo=100.0;
					cuotaTraficoUtil = valorTotalTrafico;
				}
				
				if(valorTraficoExcedido==0){
					planesMultimediaBean.setTieneValorExcedido(false);
				 }

				try {
					Date fechaReferencia;
					fechaReferencia = DateHelper.parseDate(planesMultimediaBean
							.getFechaDiaMesFormat(),
							DateHelper.FORMAT_yyyyMMddhhmmss24HORAS);
					planesMultimediaBean
							.setFechaDiaMesFormat(DateHelper.format(
									fechaReferencia, DateHelper.FORMAT_DD_MMMM));
					planesMultimediaBean.setFechaDiaMesHoraFormat(DateHelper
							.format(fechaReferencia,
									DateHelper.FORMAT_DD_MMMM_HHmm));
				} catch (NumberFormatException nfe) {
					LOGGER.warn("Error formateando fecha Referencia :"
							+ planesMultimediaBean.getFechaDiaMesFormat()
							+ nfe.getMessage());
				}
				
				planesMultimediaBean.setVelocidadPlan(velocidadPlan);
				planesMultimediaBean.setCuotaTraficoUtil(Utils.formatUnDecimal(cuotaTraficoUtil));//Valor consumido en MB			
				planesMultimediaBean.setValorTotalTrafico(Utils.formatPuntos(String.valueOf(valorTotalTrafico), Locale.GERMAN));			
				planesMultimediaBean.setTraficoExcedido(Utils.formatUnDecimal(valorTraficoExcedido));
				planesMultimediaBean.setValorMBExcedido(Utils.formatPuntos(String.valueOf(valorMBExcedido), Locale.GERMAN));		
				planesMultimediaBean.setTotalTrafico(Utils.formatUnDecimal(totalTrafico));							
				planesMultimediaBean.setPromConsumo(Utils.formatUnDecimal(promedioConsumo));
				planesMultimediaBean.setPromConsumoSinDec(Utils.formatPuntos(String.valueOf(promedioUtilImg), Locale.GERMAN));
				
			}

		} catch (DAOException e) {
			LOGGER.error("Error al consultar la informacion del un plan multimedia excedido", e);
			planesMultimediaBean = null;
			this.errorPlanMMexcedido = false;
		} catch (ServiceException e) {
			LOGGER.error("ServiceException caught msisdn: "
					+ msisdn + " - " + e.getCodigoRespuesta() + " - "
					+ e.getDescripcionRespuesta());
			planesMultimediaBean = null;
			this.errorPlanMMexcedido = false;

		} catch (Exception e) {
			LOGGER.error("Exception caught: " + e.getMessage(), e);
			this.errorPlanMMexcedido = false;
		}

		return planesMultimediaBean;
    }
	
	
    /**
     * @return the traficoDelegate
     */
    public TraficoDelegate getTraficoDelegate() {
        return traficoDelegate;
    }

    /**
     * @param traficoDelegate the traficoDelegate to set
     */
    public void setTraficoDelegate(TraficoDelegate traficoDelegate) {
        this.traficoDelegate = traficoDelegate;
    }

    /**
     * @return the detalleMiTraficoSSBean
     */
    public List<DetalleMiTraficoSSBean> getListDetalleMiTraficoSSBean() {
        return listDetalleMiTraficoSSBean;
    }

    /**
     * @param detalleMiTraficoSSBean the detalleMiTraficoSSBean to set
     */
    public void setDetalleMiTraficoSSBean(
            List<DetalleMiTraficoSSBean> listDetalleMiTraficoSSBean) {
        this.listDetalleMiTraficoSSBean = listDetalleMiTraficoSSBean;
    }

    /**
     * @return the detalleMiTraficoPlanVigente
     */
    public DetalleMiTraficoSSBean getDetalleMiTraficoPlanVigente() {
        return detalleMiTraficoPlanVigente;
    }

    /**
     * @param detalleMiTraficoPlanVigente the detalleMiTraficoPlanVigente to set
     */
    public void setDetalleMiTraficoPlanVigente(
            DetalleMiTraficoSSBean detalleMiTraficoPlanVigente) {
        this.detalleMiTraficoPlanVigente = detalleMiTraficoPlanVigente;
    }

    /**
     * @return the listDetalleMiTraficoAnteriores
     */
    public List<DetalleMiTraficoSSBean> getListDetalleMiTraficoAnteriores() {
        return listDetalleMiTraficoAnteriores;
    }

    /**
     * @param listDetalleMiTraficoAnteriores the listDetalleMiTraficoAnteriores to set
     */
    public void setListDetalleMiTraficoAnteriores(
            List<DetalleMiTraficoSSBean> listDetalleMiTraficoAnteriores) {
        this.listDetalleMiTraficoAnteriores = listDetalleMiTraficoAnteriores;
    }

    /**
     * @return the panelTraficoAnterior
     */
    public HtmlPanelGroup getPanelTraficoAnterior() {
        return panelTraficoAnterior;
    }

    /**
     * @param panelTraficoAnterior the panelTraficoAnterior to set
     */
    public void setPanelTraficoAnterior(HtmlPanelGroup panelTraficoAnterior) {
        this.panelTraficoAnterior = panelTraficoAnterior;
    }

    /**
     * @return the panelTrafico
     */
    public HtmlPanelGroup getPanelTrafico() {
        return panelTrafico;
    }

    /**
     * @param panelTrafico the panelTrafico to set
     */
    public void setPanelTrafico(HtmlPanelGroup panelTrafico) {
        this.panelTrafico = panelTrafico;
    }

    /**
     * 
     * @return
     */
	public String getDescripcionPlanTrafico() {
		return descripcionPlanTrafico;
	}

	/**
	 * 
	 * @param descripcionPlanTrafico
	 */
	public void setDescripcionPlanTrafico(String descripcionPlanTrafico) {
		this.descripcionPlanTrafico = descripcionPlanTrafico;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isTienePlanAnteriores() {
		return tienePlanAnteriores;
	}

	/**
	 * 
	 * @param tienePlanAnteriores
	 */
	public void setTienePlanAnteriores(boolean tienePlanAnteriores) {
		this.tienePlanAnteriores = tienePlanAnteriores;
	}

	public boolean isPeriodoActual() {
		return periodoActual;
	}
	
	public String getCurrentPageLabel() {
		BookPresentationContext ctx = BookPresentationContext.getBookPresentationContext(JSFPortletHelper.getRequest());
		return ctx.getActivePage();
	}

	/**
	 * @return the planesMultimediaBean
	 */
	public PlanesMultimediaBean getPlanesMultimediaBean() {
		return planesMultimediaBean;
	}

	/**
	 * @param planesMultimediaBean the planesMultimediaBean to set
	 */
	public void setPlanesMultimediaBean(PlanesMultimediaBean planesMultimediaBean) {
		this.planesMultimediaBean = planesMultimediaBean;
	}
	
	 /**
	 * @return the planDelegate
	 */
	public PlanDelegate getPlanDelegate() {
		return planDelegate;
	}

	/**
	 * @param planDelegate the planDelegate to set
	 */
	public void setPlanDelegate(PlanDelegate planDelegate) {
		this.planDelegate = planDelegate;
	}

	/**
	 * @return the errorPlanMMexcedido
	 */
	public boolean isErrorPlanMMexcedido() {
		return errorPlanMMexcedido;
	}

	/**
	 * @param errorPlanMMexcedido the errorPlanMMexcedido to set
	 */
	public void setErrorPlanMMexcedido(boolean errorPlanMMexcedido) {
		this.errorPlanMMexcedido = errorPlanMMexcedido;
	}

	
	/*NUEVO RODRIGO DIAZ*/
	public String getPageLabelActual() {
		return JSFPortletHelper.getCurrentPageLabel();
	}   
	/*FIN NUEVO RODRIGO DIAZ*/
	
	
}
