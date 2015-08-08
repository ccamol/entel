package com.epcs.nba;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.event.PhaseEvent;
import javax.portlet.PortletPreferences;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.administracion.suscripciones.dao.SuscripcionesBolsaDAO;
import com.epcs.administracion.suscripciones.delegate.BolsasBAMCCPPDelegate;
import com.epcs.administracion.suscripciones.delegate.SuscripcionesDelegate;
import com.epcs.bean.BolsaActivaBAMCCPP;
import com.epcs.bean.BolsaBean;
import com.epcs.bean.BolsaPPBean;
import com.epcs.bean.BolsasActualesDisponiblesBean;
import com.epcs.bean.MsisdnAsociadoBean;
import com.epcs.bean.PlanBAMBean;
import com.epcs.bean.PlanBean;
import com.epcs.bean.ResumenBolsasActivasBAMCCPP;
import com.epcs.bean.ResumenPlan;
import com.epcs.billing.prodfactura.dao.FacturacionElectronicaDAO;
import com.epcs.billing.prodfactura.delegate.FacturacionElectronicaDelegate;
import com.epcs.billing.registrouso.dao.TraficoBamDAO;
import com.epcs.billing.registrouso.delegate.TraficoBamCCDelegate;
import com.epcs.cliente.orden.dao.BolsaDAO;
import com.epcs.cliente.orden.delegate.BolsaDelegate;
import com.epcs.cliente.perfil.bean.Oferta;
import com.epcs.cliente.perfil.dao.PlanDAO;
import com.epcs.cliente.perfil.delegate.OfertaBlindajeDelegate;
import com.epcs.cliente.perfil.delegate.PlanDelegate;
import com.epcs.cliente.perfil.util.PlanHelper;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.inteligencianegocio.satisfaccioncliente.util.EstadisticasHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.provision.suscripcion.bolsaspp.types.ListarBolsasActivasResponseType.Mensaje.Movil;

public class NBAController  implements Serializable {
	
	/**
	 * Declaracion de atributos
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(NBAController.class);
	
	private OfertaBlindajeDelegate ofertaBlindajeDelegate;
	private FacturacionElectronicaDelegate facturacionElectronicaDelegate;
	private BolsasBAMCCPPDelegate bolsasBAMCCPPDelegate;
	private ResumenBolsasActivasBAMCCPP bolsasActivas;
	private PlanBAMBean planActualBAMSSCC;
	private TraficoBamCCDelegate miTraficoBAMCCDelegate;
	
	private List<NBA> ofertas;
	private List<Oferta> ofertasBlindaje;
	private List<MsisdnAsociadoBean> numerosAsociados;
	private NBA ofertaNBA;
	
	private String mensaje;
	private String aaa;
	private String movil;
	private String mercado;
	private String flagBam;
	private String subMercado;
	private String plan;
	private String estiloComun;
	private String rutTitular;
	private String nroCuenta;
	private String ocultarPuntos;
	private String ocultarControl="";
	private String inicioFont1;
	private String finFont1;
	private String minimizarBanner;
	private String minimizar;
	private String respuestaJson;
	private String tempIdImg;
	
	private int time;
	
	private int ocultarOfertas=0;
	private int rechazo=0;
	
	private boolean ocultar=false;
	
	private boolean isBAM = false;
	
	private String urlCargarOferta = "";
	private static final String URL_CARGAR_OFERTA_DISPONIBLE = MiEntelProperties
			.getProperty("parametros.blindaje.urlCargarOferta");
	
    /**
     * Metodo initNBA que inicializa los atributos para utilizar en portlet NBA
     * @param event
     */
	public void initNBA(PhaseEvent event){
		
		ProfileWrapper profileWrapper = ProfileWrapperHelper
        .getProfile(JSFPortletHelper.getRequest());
		aaa="0";

		try {
			//nivel 2 - se obtiene aaa y movil del Usuario
			PlanHelper helper = new PlanHelper();
			aaa = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "aaa");
			this.setRutTitular(ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutTitular"));
			this.setMovil(ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs"));
			this.setNroCuenta(ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroCuenta"));
			this.setMercado(ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado"));
			ProfileWrapperHelper.setProperty(profileWrapper, "minimizarBanner", "si");
			setFlagBam(ProfileWrapperHelper.getPropertyAsString(profileWrapper,"flagBam"));
            setSubMercado(ProfileWrapperHelper.getPropertyAsString(profileWrapper,"subMercado"));
			//nivel 2 - si aaa es menor a 2 no se muestra banner
			if(aaa!=null){
			if(Integer.parseInt(aaa)<2){
				setOcultar(false);
			}else{
				ofertaBlindajeDelegate =new OfertaBlindajeDelegate();
				estiloComun="";
				ofertas = new ArrayList<NBA>();
				//nivel 3 - se obtienen moviles asociados
				NBAUtils nbaUtils=new NBAUtils();
				numerosAsociados=nbaUtils.getMovilesAsociados();
				//nivel 3 - hay numeros asociados se continua..				
				if(numerosAsociados!=null){
					for(int i=0;i<numerosAsociados.size();i++){
						//nivel 3 - se obtiene el (i) numero asociado para procesarlo
						String movilAsociado=((MsisdnAsociadoBean)numerosAsociados.get(i)).getNumeroPcs();
						String mercadoFor=((MsisdnAsociadoBean)numerosAsociados.get(i)).getMercado();
						String flagEstadoMovil =((MsisdnAsociadoBean)numerosAsociados.get(i)).getFlagEstadoMsisdn();
						//nivel 4 - se recuperan todas las ofertas para el movil (i) asociado
						if(("A").equals(flagEstadoMovil)){
							setOfertasBlindaje(ofertaBlindajeDelegate.ConsultarOfertaNBA(movilAsociado));
						
							for(int j=0;j<ofertasBlindaje.size();j++){
								Oferta oferta=((Oferta)ofertasBlindaje.get(j));
								oferta.setMovil(movilAsociado);
								this.validacionesComerciales(oferta,mercadoFor);
							}
						
							int minBanner=ofertaBlindajeDelegate.minimizarBanner(movilAsociado);
						
							ocultarOfertas+=minBanner;
						
							int maximo=ofertaBlindajeDelegate.obtenerMaximoMinimizarBanner(movil);
							if(maximo>rechazo){
								rechazo=maximo;
							}
						}
					}
					ofertas = PlanHelper.sortOfertasNBAMain(ofertas);
				}else{
					//nivel 4 - se recuperan todas las ofertas para el movil (i) asociado
					setOfertasBlindaje(ofertaBlindajeDelegate.ConsultarOfertaNBA(movil));
					
					for(int j=0;j<ofertasBlindaje.size();j++){
						Oferta oferta=((Oferta)ofertasBlindaje.get(j));
						oferta.setMovil(movil);
						this.validacionesComerciales(oferta,this.mercado);
					}
					int minBanner=ofertaBlindajeDelegate.minimizarBanner(movil);
					
					ocultarOfertas+=minBanner;
					
					int maximo=ofertaBlindajeDelegate.obtenerMaximoMinimizarBanner(movil);
					if(maximo>rechazo){
						rechazo=maximo;
					}
				}
				
					if(ofertas.size()==0){
						setOcultar(false);
						
					}else{
						//modificar ofertas par presentacion en "header"
						if(ofertas.size()==1){
							ocultarControl="hidden";
							this.setTime(0);
						}else{
							this.setTime(6);
						}
						if(ofertas.size()<9){
							ocultarPuntos="hidden";
							((NBA)ofertas.get(0)).setSeleccionado("enlace_seleccionado");
							((NBA)ofertas.get(0)).setEstilo(estiloComun+"text-align: center;");
							for(int i=0;i<ofertas.size();i++){
								((NBA)ofertas.get(i)).setEstilo(estiloComun+"text-align: center;");
							}
						}else{
							ocultarPuntos="";
							((NBA)ofertas.get(0)).setSeleccionado("enlace_seleccionado");
							((NBA)ofertas.get(0)).setEstilo(estiloComun+"width: 35px;padding-left: 6px;");
							((NBA)ofertas.get(0)).setPrimero(true);
							
							for(int i=1;i<ofertas.size()-1;i++){
								((NBA)ofertas.get(i)).setEstilo(estiloComun+"text-align: center;");
								if(i>5){((NBA)ofertas.get(i)).setOculto("none");}
							}
							((NBA)ofertas.get(ofertas.size()-1)).setEstilo("text-align: center;right: -20px;");
							
						}
						
						//SLAC_RECHAZO
						//SI NO HAY NINGUNA OFERTA CON SLEC_RECHAZO=0 
						//SE AUMENTA EL SLEC_RECHAZO+1 HASTA 4
						//SI SLECT>=4 TODAS LAS OFERTAS QUEDAN CON SLEC_RECHAZO IGUAL A 0
						if(rechazo>=4){
							rechazo=0;
						}else{
							rechazo++;
						}
						
						setOcultar(true);
						if(ocultarOfertas==0){
							setMinimizarBanner("none");
							nbaUtils.actualizarMinimizarBanner(numerosAsociados, rechazo);
							
						}else{
							setMinimizarBanner("");
						}
					}
				/*else{
					setOcultar(false);
				}*/
					
			}
			}else{
				LOGGER.error("no se pudo rescatar aaa");
			}

		} catch (Exception e) {
			LOGGER.error("Exception inesperado rescatar aaa", e);
		}
		
	}
	/**
	 * Metodo que realiza las validaciones comerciales para ver si una oferta es mostrada o no
	 * @param oferta
	 * @throws DAOException
	 * @throws ServiceException
	 */
	private void validacionesComerciales(Oferta oferta,String mercado) throws DAOException, ServiceException {
		String grupo=oferta.getGrupoOferta();
		BolsaDelegate delegateBolsas=new BolsaDelegate();
		delegateBolsas.setBolsaDAO(new BolsaDAO());
		List<BolsaPPBean> listaBolsaPPBean;
		String movilAsociado=oferta.getMovil();
		String codigoOferta=oferta.getOfertaId();
		String codBscsOferta = oferta.getCodBscsOferta();
		String codiPlantilla = oferta.getCodPlantilla();
		
		//GRUPO 3 retiradas del portal Mi Entel por peticion del usuario
//		if(grupo.equalsIgnoreCase("G3")){
//			this.incluiOferta(grupo,movilAsociado,"","",codigoOferta,codBscsOferta,"");
//		}
//		//GRUPO 5
//		if(grupo.equalsIgnoreCase("G5")){
//			this.incluiOferta(grupo,movilAsociado,"","",codigoOferta,codBscsOferta,"");
//		}
		if(grupo.equalsIgnoreCase("G1") ||  grupo.equalsIgnoreCase("G4")){
			//PARTE AGREGADA EL 25-08-2013
			if(flagBam.equals("1")){
				//es BAM
				boolean logCajaFacturacion = false;
				miTraficoBAMCCDelegate = new TraficoBamCCDelegate();
				miTraficoBAMCCDelegate.setTraficoBamDAO(new TraficoBamDAO());
				planActualBAMSSCC = miTraficoBAMCCDelegate.obtenerPlanActualBAMSSCC(movilAsociado, mercado, aaa, logCajaFacturacion);
		        if(!planActualBAMSSCC.getCodbscs2().equalsIgnoreCase(oferta.getCodBscsOferta())){
		        	if(grupo.equalsIgnoreCase("G1")){
		        		String datosG1[]=ofertaBlindajeDelegate.ConsultarOfertaPlanNBA(oferta.getCodBscsOferta());
		        		this.incluiOferta(grupo,movilAsociado,datosG1[0],datosG1[1],codigoOferta,codBscsOferta,"");
					}
		        	if(grupo.equalsIgnoreCase("G4")){
						String datosG4[]=ofertaBlindajeDelegate.ConsultarOfertaPlanNBA(oferta.getCodBscsOferta());
						this.incluiOferta(grupo,movilAsociado,datosG4[0],datosG4[1],codigoOferta,codBscsOferta,"");
					}
		        }
		        
			}else{
				LOGGER.info("Validaciones comerciales G4 VOZ");
				PlanDelegate planDelegate=new PlanDelegate();
				planDelegate.setPlanDAO(new PlanDAO());
				PlanBean planActual = planDelegate.getPlanActualSSCC(movilAsociado, mercado, aaa);
				if(!planActual.getCodbscs2().equalsIgnoreCase(oferta.getCodBscsOferta())){
					if(grupo.equalsIgnoreCase("G1")){
						String datosG1[]=ofertaBlindajeDelegate.ConsultarOfertaPlanNBA(oferta.getCodBscsOferta());
						this.incluiOferta(grupo,movilAsociado,datosG1[0],datosG1[1],codigoOferta,codBscsOferta,"");
					}
					if(grupo.equalsIgnoreCase("G4")){
						if (this.numeroTieneMismoPlan(movilAsociado, codigoOferta, "G4")) {
							String datosG4[]=ofertaBlindajeDelegate.ConsultarOfertaPlanNBA(oferta.getCodBscsOferta());
							this.incluiOferta(grupo,movilAsociado,datosG4[0],datosG4[1],codigoOferta,codBscsOferta,"");
						}
					}
				}
			}
			
		}
		if(grupo.equalsIgnoreCase("G6")){
			//nivel 5 - validaciones otros grupos
			if(flagBam.equals("1")){
				//es BAM
				bolsasActivas = bolsasBAMCCPPDelegate.listarBolsasActivas(movilAsociado);
				List<BolsaActivaBAMCCPP> bolsasActivasBam = bolsasActivas.getBolsas();
				if(bolsasActivasBam.size()==0){
					String datosG6[]=ofertaBlindajeDelegate.ConsultarOfertaBolsaNBA(oferta.getCodBscsOferta());
					if(datosG6[2].equalsIgnoreCase("INTERNET MOVIL")){
						datosG6[2]="IM";
					}
					this.incluiOferta( grupo,movilAsociado,datosG6[0],datosG6[1],codigoOferta,codBscsOferta,datosG6[2]);
				}else{
					//hacer validaciones 
					int contOfertas=0;
					for(int i=0;i<bolsasActivasBam.size();i++){
						if(((BolsaActivaBAMCCPP)bolsasActivasBam.get(i)).getCodigo().equalsIgnoreCase(oferta.getCodBscsOferta())){
							contOfertas++;
						}
					}
					if(contOfertas==0){
						String datosG6[]=ofertaBlindajeDelegate.ConsultarOfertaBolsaNBA(oferta.getCodBscsOferta());
						if(datosG6[2].equalsIgnoreCase("INTERNET MOVIL")){
							datosG6[2]="IM";
						}
						this.incluiOferta( grupo,movilAsociado,datosG6[0],datosG6[1],codigoOferta,codBscsOferta,datosG6[2]);
						
					}
				}
			}else{
				LOGGER.info("Validaciones comerciales G6 VOZ");
				//CC SS
				//listaBolsaPPBean=delegateBolsas.obtenerBolsasScob(movilAsociado);
				
				SuscripcionesDelegate suscripcionesDelegate = new SuscripcionesDelegate();
				suscripcionesDelegate.setSuscripcionesBolsaDAO(new SuscripcionesBolsaDAO());
				BolsasActualesDisponiblesBean listaBolsaPPBean2 = suscripcionesDelegate.consultarBolsasActualesDisponibles(movilAsociado);				

				
				//if(listaBolsaPPBean.size()==0){
				if(listaBolsaPPBean2.getBolsasActuales() == null || listaBolsaPPBean2.getBolsasActuales().size()==0 ){
					if (this.numeroTieneMismoPlan(movilAsociado, codigoOferta, "G6") && this.numeroTieneSaldoSuficiente(movilAsociado, oferta.getCodBscsOferta())) {
						String datosG6[]=ofertaBlindajeDelegate.ConsultarOfertaBolsaNBA(oferta.getCodBscsOferta());
						if(datosG6[2].equalsIgnoreCase("INTERNET MOVIL")){
							datosG6[2]="IM";
						}
						this.incluiOferta( grupo,movilAsociado,datosG6[0],datosG6[1],codigoOferta,codBscsOferta,datosG6[2]);
					}
				}else{
					//hacer validaciones 
					int contOfertas=0;
					/*
					for(int i=0;i<listaBolsaPPBean.size();i++){
						if(((BolsaPPBean)listaBolsaPPBean.get(i)).getCodBolsa().equalsIgnoreCase(oferta.getCodBscsOferta())){
							contOfertas++;
						}
					}
					*/
					for(int i=0;i<listaBolsaPPBean2.getBolsasActuales().size();i++){
						if(((BolsaBean)listaBolsaPPBean2.getBolsasActuales().get(i)).getSnCodigo().equalsIgnoreCase(oferta.getCodBscsOferta())){
							contOfertas++;
						}
					}
					
					if(contOfertas==0){
						if (this.numeroTieneMismoPlan(movilAsociado, codigoOferta, "G6") && this.numeroTieneSaldoSuficiente(movilAsociado, oferta.getCodBscsOferta())) {
							String datosG6[]=ofertaBlindajeDelegate.ConsultarOfertaBolsaNBA(oferta.getCodBscsOferta());
							if(datosG6[2].equalsIgnoreCase("INTERNET MOVIL")){
								datosG6[2]="IM";
							}
							this.incluiOferta( grupo,movilAsociado,datosG6[0],datosG6[1],codigoOferta,codBscsOferta,datosG6[2]);
						}
					}
				}
			}
		}
		if(grupo.equalsIgnoreCase("G7")){
			//nivel 5 - validaciones grupo 7
			facturacionElectronicaDelegate =new FacturacionElectronicaDelegate();
			facturacionElectronicaDelegate.setFacturacionElectronicaDAO(new FacturacionElectronicaDAO());
			
	        	if(!facturacionElectronicaDelegate.getFacturacionElectronicaEstado(this.rutTitular, this.nroCuenta)){
	        		
		        	this.incluiOferta(grupo,movilAsociado,"","",codigoOferta,codBscsOferta,"");
	        	}
		}
		if(grupo.equalsIgnoreCase("G10")){
			//nivel 5 - validaciones grupo 10
			String datosG10[]=ofertaBlindajeDelegate.ConsultarOfertaPlanNBA(oferta.getCodBscsOferta());
			this.incluiOferta(grupo,movilAsociado,datosG10[0],datosG10[1],codigoOferta,codBscsOferta,codiPlantilla);
		}
	}
	
	/**
	 * Metodo que incluye oferta en lista de ofertas
	 * @param url
	 * @param img
	 */
	public void incluiOferta(String grupo,String movilAsociado,String nombreOferta,String valor,String codigoOferta, String codBscsOferta, String codiPlantilla){
		NBAUtils util=new NBAUtils();
		String url="";
		Boolean isAsociado = false;
		
		url=urlBlindaje(codigoOferta,movilAsociado);
		
		ofertaNBA=new NBA();
		
		ofertaNBA.setGrupo(grupo);

		if(movilAsociado.equals(movil)){
    		movilAsociado="-1";
    	}else{
    		isAsociado=true;
    	}
		
		ofertaNBA.setMovil(movilAsociado);
		
		if(ofertaNBA.getGrupo().equalsIgnoreCase("G10") && codiPlantilla.equalsIgnoreCase("2")){
				this.ofertaNBA.setOfertaBAM(true);
		}
		//actualmente estos grupos de ofertas son las que presentan imagenes aleatorias
		if(ofertaNBA.getGrupo().equalsIgnoreCase("G4")|| ofertaNBA.getGrupo().equalsIgnoreCase("G6")|| ofertaNBA.getGrupo().equalsIgnoreCase("G10")){
			this.ofertaNBA.setImg(this.generaImagen(this.ofertaNBA.getGrupo(),this.ofertaNBA.isOfertaBAM(),isAsociado,codiPlantilla));
		}
		if(ofertaNBA.getGrupo().equalsIgnoreCase("G6")){
			this.ofertaNBA.setTipoBolsa(codiPlantilla);
		}
		this.ofertaNBA.setIdImg(this.tempIdImg);
		if(nombreOferta!=null){
			ofertaNBA.setNombreOferta(util.agregarTilde(nombreOferta));
		}
		if(valor!=null){
		ofertaNBA.setValor(valor);
		}
		ofertaNBA.setCodigoOferta(codigoOferta);
		ofertaNBA.setUrl(url);
		ofertas.add(ofertaNBA);
		this.tempIdImg = "";

	}
		
	
    /**
     * Punto de entrada para todas las peticiones ajax
     * @param phase
     */
    public void initProcesarAjax(final PhaseEvent phase){
        String accion = JsfUtil.getRequestParameter("accion");
        if(accion.equals("rechazarOferta")){
            this.rechazarOfertaBlindajeNBASS();            
        }
    }
    /**
     * <p>metodo que genera imagen de manera aleatoria retornando la ruta de esta en el repositorio Weblogic para una oferta especifica.</p>
     * <p><b>NOTA:</b> la imagen cargada por contenido debe tener la sgte estructura:</p>
     * <p>"GX" + "_aux"(si lo tiene)+"_numero de imagen", ejemplo:</p>
     * <blockquote><pre>
     * G10_BAM_3, G4_1, G6_SMS_3.
     * </pre></blockquote>
     * @param grupo, isBam, isAsociado, tipoBolsa
     * @return String
     * @author Santoya
     * @since 06 Ago 2014
     */
    public String generaImagen(String grupo, Boolean isBam, Boolean isAsociado, String tipoBolsa){
    	Node nodoImagen = null;
    	String imgPath = null;
    	Integer  idImagen = null;
    	Random random = new Random();
    	PortletPreferences preferences = JSFPortletHelper.getPreferencesObject();
    	try {
    		//para G10 actualmente se usan las mismas imagenes VOZ/BAM por definiciones de negocio, si algun dia son diferentes no sera necesario modificar 
    		if (grupo.equalsIgnoreCase("G10")) {
    			if(isBam){
    				idImagen = random.nextInt(Integer.parseInt(JSFPortletHelper.getPreference(preferences,grupo+"_BAM", ""))) + 1;
        			nodoImagen = JSFPortletHelper.getContentNode("description",grupo+"_BAM_"+idImagen);
    			}else{
    				idImagen = random.nextInt(Integer.parseInt(JSFPortletHelper.getPreference(preferences,grupo, ""))) + 1;
        			nodoImagen = JSFPortletHelper.getContentNode("description",grupo+"_"+idImagen);	
    			}
    		}	
    		if(grupo.equalsIgnoreCase("G6") && !tipoBolsa.equalsIgnoreCase("")){
    			idImagen = random.nextInt(Integer.parseInt(JSFPortletHelper.getPreference(preferences,grupo+"_"+tipoBolsa, ""))) + 1;
				nodoImagen = JSFPortletHelper.getContentNode("description",grupo+"_"+tipoBolsa+"_"+idImagen);
    		}
    		if(grupo.equalsIgnoreCase("G4")){
    			//por definiciones de negocio la campaña G4 para los moviles asociados el conjunto de imagenes son totalmente distintas
    			//para identificarlas se le agrega "A" al nombre
    			if(isAsociado) {
    				idImagen = random.nextInt(Integer.parseInt(JSFPortletHelper.getPreference(preferences,grupo+"_A",""))) + 1;
    				nodoImagen = JSFPortletHelper.getContentNode("description",grupo+"_A"+idImagen);
    			}else{
    				idImagen = random.nextInt(Integer.parseInt(JSFPortletHelper.getPreference(preferences,grupo, ""))) + 1;
    				nodoImagen = JSFPortletHelper.getContentNode("description",grupo+"_"+idImagen);
    			}
    		}
    		this.tempIdImg= idImagen.toString();
    		imgPath = "/ShowProperty?nodeId="+nodoImagen.getId();
    	} catch (Exception e) {
    		LOGGER.error(e);
    	}
    	return imgPath;
    }
    
    
    
    private void rechazarOfertaBlindajeNBASS() {
    	ofertaBlindajeDelegate =new OfertaBlindajeDelegate();
    	try{
        String codOferta = JsfUtil.getRequestParameter("codOferta");
    	
        ofertaBlindajeDelegate.rechazaOfertaBlindajeNBA(Long.valueOf(codOferta));
        
    	
    } catch (Exception e) {
        LOGGER.error("Exception inesperado al rechazar la oferta de contratacion de la bolsa", e);
        respuestaJson = JsonHelper
                .toJsonServiceErrorMessage("norechazabolsa");
        EstadisticasHelper.agregarMarcaEstadistica(EstadisticasHelper.TRANSACCION_NO_OK, EstadisticasHelper.GRUPO_BOLSAS, EstadisticasHelper.BOLSAS_CONFIRMAR_CONTRATAR_SSCC);            
    }
  }
	/**
	 * METODOS GETTER AND SETTER
	 * 
	 */

	public void setOfertaNBA(NBA oferta) {
		this.ofertaNBA = oferta;
	}

	public NBA getOfertaNBA() {
		return ofertaNBA;
	}

	public void setOcultarPuntos(String ocultarPuntos) {
		this.ocultarPuntos = ocultarPuntos;
	}

	public String getOcultarPuntos() {
		return ocultarPuntos;
	}

	public void setOcultarControl(String ocultarControl) {
		this.ocultarControl = ocultarControl;
	}

	public String getOcultarControl() {
		return ocultarControl;
	}
	public void setRutTitular(String rutTitular) {
		this.rutTitular = rutTitular;
	}
	public String getRutTitular() {
		return rutTitular;
	}
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	public String getNroCuenta() {
		return nroCuenta;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getMensaje() {
		return mensaje;
	}
	public List<NBA> getOfertas() {
		return this.ofertas;
	}
	public void setOfertas(List<NBA> ofertas) {
		this.ofertas = ofertas;
	}
	
	public void setOcultar(boolean ocultar) {
		this.ocultar = ocultar;
	}
	public boolean isOcultar() {
		return ocultar;
	}
	
	public FacturacionElectronicaDelegate getFacturacionElectronicaDelegate() {
		return facturacionElectronicaDelegate;
	}

	public void setFacturacionElectronicaDelegate(
				 FacturacionElectronicaDelegate facturacionElectronicaDelegate) {
		this.facturacionElectronicaDelegate = facturacionElectronicaDelegate;
	}
	public OfertaBlindajeDelegate getOfertaBlindajeDelegate() {
		return ofertaBlindajeDelegate;
	}

	public void setOfertaBlindajeDelegate(
			OfertaBlindajeDelegate ofertaBlindajeDelegate) {
		this.ofertaBlindajeDelegate = ofertaBlindajeDelegate;
	}
	public void setOfertasBlindaje(List<Oferta> ofertasBlindaje) {
		this.ofertasBlindaje = ofertasBlindaje;
	}
	public List<Oferta> getOfertasBlindaje() {
		return ofertasBlindaje;
	}
	public void setInicioFont1(String inicioFont1) {
		this.inicioFont1 = inicioFont1;
	}
	public String getInicioFont1() {
		return inicioFont1;
	}
	public void setFinFont1(String finFont1) {
		this.finFont1 = finFont1;
	}
	public String getFinFont1() {
		return finFont1;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getPlan() {
		return plan;
	}
	public void setMercado(String mercado) {
		this.mercado = mercado;
	}
	public String getMercado() {
		return mercado;
	}
	public void setMovil(String movil) {
		this.movil = movil;
	}
	public String getMovil() {
		return movil;
	}
	public void setOcultarOfertas(int ocultarOfertas) {
		this.ocultarOfertas = ocultarOfertas;
	}
	public int getOcultarOfertas() {
		return ocultarOfertas;
	}
	public void setMinimizarBanner(String minimizarBanner) {
		this.minimizarBanner = minimizarBanner;
	}
	public String getMinimizarBanner() {
		return minimizarBanner;
	}
	public void setMinimizar(String minimizar) {
		this.minimizar = minimizar;
	}
	public String getMinimizar() {
		return minimizar;
	}
	public void setFlagBam(String flagBam) {
		this.flagBam = flagBam;
	}
	public String getFlagBam() {
		return flagBam;
	}
	public void setSubMercado(String subMercado) {
		this.subMercado = subMercado;
	}
	public String getSubMercado() {
		return subMercado;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getTime() {
		return time;
	}
	public boolean isBAM() {
		return isBAM;
	}
	public void setBAM(boolean isBAM) {
		this.isBAM = isBAM;
	}
	public String urlBlindaje(String idOferta,String numeroPcsSeleccionado) {
		String url = "";
		try {

			LOGGER.info("ARMAR URL DE LA OFERTA");
						
			String idp = "";
			
			SeguridadDelegate seguridadDelegate = new SeguridadDelegate();

			idp = seguridadDelegate.consultarIDP(numeroPcsSeleccionado);
			
            LOGGER.info("numeroPcsSeleccionado :::: " + numeroPcsSeleccionado );
            LOGGER.info("idp :::: " + idp );
          
            url = URL_CARGAR_OFERTA_DISPONIBLE + idp + "&idOferta="+idOferta;
			
			LOGGER.info("URL :::: " + urlCargarOferta );
			
			
		}  catch (DAOException e) {
            LOGGER.error("DAOException al buscar informacion de la oferta Blidaje.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoOfertas");
        }catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar informacion de la oferta Blidaje. msisdn: "+numeroPcsSeleccionado);
            JSFMessagesHelper.addServiceErrorMessage("noinfoOfertas");
        }catch (Exception e) {
            LOGGER.error("Exception al buscar oferta blidaje disponible.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoOfertas");
        }
        
		return url;
	}
	
	public boolean isTieneOfertasPorMovil(String movil) {
		try {			
			ofertaBlindajeDelegate = (OfertaBlindajeDelegate) JSFPortletHelper
					.getFacesBeanInstance("ofertaBlindajeDelegate");
			return ofertaBlindajeDelegate.tieneOfertasPorMovil(movil);
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getUrlCargarOferta() {
		return urlCargarOferta;
	}

	public void setUrlCargarOferta(String urlCargarOferta) {
		this.urlCargarOferta = urlCargarOferta;
	}
	public void setBolsasBAMCCPPDelegate(BolsasBAMCCPPDelegate bolsasBAMCCPPDelegate) {
		this.bolsasBAMCCPPDelegate = bolsasBAMCCPPDelegate;
	}
	public BolsasBAMCCPPDelegate getBolsasBAMCCPPDelegate() {
		return bolsasBAMCCPPDelegate;
	}
	public void setBolsasActivas(ResumenBolsasActivasBAMCCPP bolsasActivas) {
		this.bolsasActivas = bolsasActivas;
	}
	public ResumenBolsasActivasBAMCCPP getBolsasActivas() {
		return bolsasActivas;
	}
	public void setMiTraficoBAMCCDelegate(TraficoBamCCDelegate miTraficoBAMCCDelegate) {
		this.miTraficoBAMCCDelegate = miTraficoBAMCCDelegate;
	}
	public TraficoBamCCDelegate getMiTraficoBAMCCDelegate() {
		return miTraficoBAMCCDelegate;
	}
	public void setPlanActualBAMSSCC(PlanBAMBean planActualBAMSSCC) {
		this.planActualBAMSSCC = planActualBAMSSCC;
	}
	public PlanBAMBean getPlanActualBAMSSCC() {
		return planActualBAMSSCC;
	}
	public String getRespuestaJson() {
		return respuestaJson;
	}
	public void setRespuestaJson(String respuestaJson) {
		this.respuestaJson = respuestaJson;
	}
	
	public String getTempIdImg() {
		return tempIdImg;
	}
	public void setTempIdImg(String tempIdImg) {
		this.tempIdImg = tempIdImg;
	}
	public boolean numeroTieneMismoPlan(String movil, String codigoOferta, String tipoOferta) {
		
		LOGGER.info("Validando cambio de plan... movil: " + movil + ", mercado: " + mercado + ", aaa: " + aaa + ", codigoOferta: " + codigoOferta + ", tipoOferta: " + tipoOferta);
		
		boolean mismoPlan = false;
		
		try {			

			// Obtiene plan original, cargado en la baseIT
			String planOriginalNumero = ofertaBlindajeDelegate.obtenerPlanOriginalNumero(codigoOferta, tipoOferta);						
			LOGGER.info("Plan original numero: " + planOriginalNumero);
			
			// Obtiene plan actual
			PlanDelegate planDelegate=new PlanDelegate();
			planDelegate.setPlanDAO(new PlanDAO());
			PlanBean planActual = planDelegate.getPlanActualSSCC(movil, mercado, aaa);			
			String planActualNumero = planActual.getCodbscs2();
			LOGGER.info("Codigo plan actual: " + planActualNumero);
			
			// verifica cambio
			if (planOriginalNumero.equals(planActualNumero)) {				
				LOGGER.info("Plan del numero NO ha sido cambiado");
				mismoPlan = true;
			} else {
				LOGGER.info("Plan del numero fue cambiado");
			}
						
		} catch (Exception e) {
			LOGGER.error("Excepcion al validar cambio de plan...",e);
		}
				
		return mismoPlan;
	}	
	
	public boolean numeroTieneSaldoSuficiente(String movil, String codigoOferta) {
		
		LOGGER.info("Validando saldo disponible para Bolsa... movil: " + movil + ", mercado: " + mercado + ", aaa: " + aaa + ", codigoOferta: " + codigoOferta);
		
		boolean tieneSaldo = false;
		
		if (this.getMercado().equalsIgnoreCase("CC")) {
			try {			
	
				// Obtiene saldo actual
				ResumenPlan resumenPlan = new ResumenPlan();			
				PlanDelegate planDelegate=new PlanDelegate();
				planDelegate.setPlanDAO(new PlanDAO());
				resumenPlan = planDelegate.getPlanResumenCC(movil, aaa);
				Double saldoActual = resumenPlan.getSaldo();
				LOGGER.info("SaldoActual movil " + movil + ": " + saldoActual.toString());
				
				// Obtiene valor de la bolsa
				String datosBolsa[]=ofertaBlindajeDelegate.ConsultarOfertaBolsaNBA(codigoOferta);
				
				if(datosBolsa[1]!=null){
					LOGGER.info("Valor de la bolsa ofertada " + codigoOferta + ": " + datosBolsa[1]);
					Double valorBolsa = Double.parseDouble(datosBolsa[1]);
		            if(saldoActual <= 7 || saldoActual > valorBolsa){
		            	LOGGER.info("Movil tiene saldo suficiente para la bolsa");
		            	tieneSaldo = true;
		            } else {
		            	LOGGER.info("Movil NO tiene saldo suficiente para la bolsa");
		            }
				} else {
					LOGGER.error("Valor de bolsa nulo, es posible que no exista en la BD");
				}
			
				
			} catch (Exception e) {
				LOGGER.error("Excepcion al validar saldo del numero...",e);
			}
		} else {
			LOGGER.info("Numero no es CC, se omite validacion");
			tieneSaldo = true;
		}
				
		return tieneSaldo;
	}	
}
