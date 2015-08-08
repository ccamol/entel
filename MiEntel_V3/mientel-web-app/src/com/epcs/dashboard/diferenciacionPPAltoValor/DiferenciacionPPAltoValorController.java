package com.epcs.dashboard.diferenciacionPPAltoValor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.portlet.PortletRequest;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;

import com.epcs.administracion.suscripciones.delegate.SuscripcionesDelegate;
import com.epcs.bean.BolsaPPBean;
import com.epcs.bean.CodeDescBean;
import com.epcs.bean.GrupoBolsaBean;
import com.epcs.bean.PlanDisponibleBean;
import com.epcs.bean.PlanPPBean;
import com.epcs.cliente.perfil.controller.PlanController;
import com.epcs.cliente.perfil.dao.PlanDAO;
import com.epcs.cliente.perfil.delegate.PlanDelegate;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

public class DiferenciacionPPAltoValorController implements Serializable {
	 private static final Logger LOGGER = Logger.getLogger(DiferenciacionPPAltoValorController.class);
	
    private static final String CATEGORIA_CLIENTE_PP = MiEntelProperties.getProperty("parametros.categoriausuario.diferenciacioncanalespp.altovalor");
    private static final String BANNER_VISIBLE = MiEntelProperties.getProperty("parametros.banner.diferenciacioncanalespp.altovalor");
    private boolean validoCarruselPPAltoValor=true;
    private boolean validoBannerTarifasplus=false;
    private boolean validoBannerBolsasPlus=false;
    private List<String> itemCarruselPlus;
    private PlanDelegate planDelegate;
    private SuscripcionesDelegate suscripcionesDelegate;
    private PlanDisponibleBean planDisponible;
    private List<GrupoBolsaBean> bolsasDispPPScobRegalo;
    private String tiposBolsasMasVendidas = ParametrosHelper.getTipoDeBolsasMasVendidas();
    

    
    
    public void setValidoCarruselPPAltoValor(boolean  validoCarruselPPAltoValor){
    	this. validoCarruselPPAltoValor =  validoCarruselPPAltoValor;
    }
    
    
    public boolean getValidoCarruselPPAltoValor(){
    	return  validoCarruselPPAltoValor;
    }
    
    
    public void setValidoBannerTarifasplus(boolean validoBannerTarifasPlus){
    	this.validoBannerTarifasplus= validoBannerTarifasPlus ;
    }
    
    public boolean getValidoBannerTarifasPlus(){
    	return validoBannerTarifasplus;
    }
    
    public void setValidoBannerBolsasPlus( boolean validoBannerBolsasPlus){
    	this.validoBannerBolsasPlus= validoBannerBolsasPlus;
    }
    public boolean getValidoBannerBolsasPlus(){
    	return validoBannerBolsasPlus;
    }
    
    public List<String> getItemCarruselPlus(){
    	return itemCarruselPlus; 
    }
    
    public void setItemCarruselPlus(ArrayList<String> itemCarruselPlus){
    	this.itemCarruselPlus= itemCarruselPlus;
    }
    
	/**
     * @return the planDelegate
     */
    public PlanDelegate getPlanDelegate() {
        return planDelegate;
    }

    /**
     * Metodo para la injection del delegate
     * @param planDelegate
     */
    public void setPlanDelegate(PlanDelegate planDelegate) {
        this.planDelegate = planDelegate;
    }    

    
    /**
     * @return the suscripcionesDelegate
     */
    
    public SuscripcionesDelegate setSuscripcionesDelegate(){
    	return suscripcionesDelegate;
    }
    
    /**
     * @param suscripcionesDelegate
     */
    
    public void setSuscripcionesDelegate(final SuscripcionesDelegate suscripcionesDelegate){
    	this.suscripcionesDelegate = suscripcionesDelegate;
    	
    }
    
	/**
	 * 
	 * @return  planDisponibleBean
	 */
	public PlanDisponibleBean getPlanDisponible() {
		return this.planDisponible;
	}

	/**
	 * 
	 * @param planDisponibleBean
	 */
	public void setPlanDisponible(PlanDisponibleBean planDisponible) {
		this.planDisponible = planDisponible;
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
	
	
	
    private String getPreferencesBannerPPAltoValor(){		               	
    	FacesContext context = FacesContext.getCurrentInstance();		 
    	PortletRequest req = (PortletRequest) context.getExternalContext().getRequest();		 
    	String visible = req.getPreferences().getValue("visible",null);
  
		
		
		return visible != null ? visible: null;    
    }
	
	
	public void init(PhaseEvent event) {
		String bannerVisible = "";
		String categoriaUser = "";
		String numeroPcsSeleccionado = "";
		List<String> itemPlusTemp = new ArrayList<String>();
		int cantBolsasPlus = 0;
		int cantTarifasPlus = 0;

		try {
			ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
			categoriaUser = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "categoriaCliente");
			numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
			bannerVisible = getPreferencesBannerPPAltoValor();
			validoCarruselPPAltoValor = false;
			if (bannerVisible != null && bannerVisible.equals(BANNER_VISIBLE)) {
				if (categoriaUser != null && categoriaUser.equals(CATEGORIA_CLIENTE_PP)) {
					validoCarruselPPAltoValor = true;					
					planDisponible = planDelegate.getPlanesDisponiblesPP(numeroPcsSeleccionado);
					bolsasDispPPScobRegalo = buildGruposBolsa(suscripcionesDelegate.obtenerBolsasDisponiblesScob(numeroPcsSeleccionado));

					if (!planDisponible.getPlanesDisponiblesPP().isEmpty()&& planDisponible != null) {

						validoBannerTarifasplus = getTarifasDisponiblesPlus(planDisponible);

						if (validoBannerTarifasplus) {
							cantTarifasPlus = 1;
							itemPlusTemp.add(String.valueOf(cantTarifasPlus));

						}

					}
					if (bolsasDispPPScobRegalo != null) {

						validoBannerBolsasPlus = getBolsasDisponiblesPlus(bolsasDispPPScobRegalo);
						if (validoBannerBolsasPlus) {
							cantBolsasPlus = 1;

							itemPlusTemp.add(String.valueOf(cantBolsasPlus));

						}

					}

					if (validoBannerBolsasPlus || validoBannerTarifasplus) {
						//validoCarruselPPAltoValor = false;

						itemCarruselPlus = itemPlusTemp;

					}

				}

			}

		} catch (Exception e) {
			LOGGER.error("Error inesperado al cargarel banner");
		}
	}
	
	/**
	 * 
	 * @return Preferences Tarifas Plus
	 */
	private String[] getPreferencesTarifasPPAltoValor() {
		FacesContext context = FacesContext.getCurrentInstance();
		PortletRequest req = (PortletRequest) context.getExternalContext().getRequest();
		String idTarifasPlus = req.getPreferences().getValue("Idtarifasppaltovalor", null);

		return idTarifasPlus != null ? idTarifasPlus.split(",") : null;
	}
    
    /**
     * 
     * @param Listado de tarifas disponiblesplus
     * @return
     */
	

	private boolean getTarifasDisponiblesPlus(PlanDisponibleBean planDisponible) {
		boolean existenTarifasPlus = false;

		ArrayList<PlanPPBean> listadoTarifasPlus = new ArrayList<PlanPPBean>();

		try {

			String[] idTarifasAltovalorPP = getPreferencesTarifasPPAltoValor();

			if (planDisponible != null&& !planDisponible.getPlanesDisponiblesPP().isEmpty()&&  idTarifasAltovalorPP != null) {

				for (String idT : idTarifasAltovalorPP) {
					Iterator<PlanPPBean> it = planDisponible.getPlanesDisponiblesPP().iterator();
					while (it.hasNext()) {
						PlanPPBean planPPBean = it.next();
						if (planPPBean.getIdTarifa() == Integer.parseInt(idT)) {
							listadoTarifasPlus.add(planPPBean);
							it.remove();
						}
					}
				}
				if (listadoTarifasPlus != null && !listadoTarifasPlus.isEmpty()) {
					existenTarifasPlus = true;

				}
			}

		} catch (Exception e) {
			LOGGER.error("Error inesperado al cargar las tarifas especiales prepagoPlus",e);
		}

		return existenTarifasPlus;

	}
	
	public String getLinkTarifasPPaltovalor() {
		FacesContext context = FacesContext.getCurrentInstance();
		PortletRequest req = (PortletRequest) context.getExternalContext().getRequest();
		String linkTarifasPlus = req.getPreferences().getValue("linkTarifasPlus", null);

		return linkTarifasPlus != null ? linkTarifasPlus : null;
	}
/**
 * Agrupamiento de Bolsas
 */
private List<GrupoBolsaBean> buildGruposBolsa(List<BolsaPPBean> listBolsas) {
    List<GrupoBolsaBean> listGrupoBolsa = new ArrayList<GrupoBolsaBean>();
 
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
 * preferences para bolsas plus
 * 
 */
	private String[] getPreferencesBolsasPPAltoValor() {
		FacesContext context = FacesContext.getCurrentInstance();
		PortletRequest req = (PortletRequest) context.getExternalContext().getRequest();
		String unidadBolsasPlus = req.getPreferences().getValue("BolsasPPAltoValor", null);

		return unidadBolsasPlus != null ? unidadBolsasPlus.split(",") : null;
	}

/**
 * 
 * @param Listado de bolsas plus
 * @return
 */

	private boolean getBolsasDisponiblesPlus(List<GrupoBolsaBean> bolsasDispPPScobRegalo) {
		List<BolsaPPBean> listadoBolsasPlus = new ArrayList<BolsaPPBean>();
		boolean validoBolsasPlus = false;

		try {

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
				if (listadoBolsasPlus != null && !listadoBolsasPlus.isEmpty()) {
					validoBolsasPlus = true;
				}
			}

		} catch (Exception e) {
			LOGGER.error("Error Inesperado al cargar la categoria del usuario pp");
		}

		return validoBolsasPlus;
	}
	

		public String getLinkBolsasPPAltoValor(){
			FacesContext context = FacesContext.getCurrentInstance();
			PortletRequest req=(PortletRequest) context.getExternalContext().getRequest();
			String linkBolsasPlus= req.getPreferences().getValue("linkBolsasPlus", null);
			
			return linkBolsasPlus!=null ? linkBolsasPlus : null;
			
			
		}
	
	

}
