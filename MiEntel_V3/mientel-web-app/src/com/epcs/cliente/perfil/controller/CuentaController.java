/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.controller;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.CiudadBean;
import com.epcs.bean.ComunaBean;
import com.epcs.bean.CuentaClienteBean;
import com.epcs.bean.DireccionBean;
import com.epcs.bean.FacturacionElectronicaBean;
import com.epcs.bean.RegionBean;
import com.epcs.bean.RutBean;
import com.epcs.bean.UsuarioBean;
import com.epcs.billing.prodfactura.delegate.FacturacionElectronicaDelegate;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;
import com.epcs.inscripcion.controller.InscripcionController;
import com.epcs.inscripcion.util.InscripcionHelper;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.jsf.converter.RutConverter;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.recursoti.parametros.delegate.ParametrosDelegate;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class CuentaController implements Serializable {

    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = Logger
            .getLogger(CuentaController.class);
    
    private CuentaDelegate cuentaDelegate;

    private ParametrosDelegate parametrosDelegate;

    private UsuarioBean usuarioBean;
    
    private SelectItem [] regionesList;
    
    private String mensajeError;
    
    private List<CiudadBean> itemsCiudades = null;
    
    private List<ComunaBean> itemsComunas = null;
    
    private SelectItem[] itemsComunasDirFactura = null;
    
    private CuentaClienteBean cuentaClienteBean = null;
    
    private boolean panelSSCC = false;
    
    private boolean panelAdmin = false;
    
    private boolean renderEmailFacturaElectronica;
    
    private boolean error = false;
    
	private String autorizacionVoluntariaContent;
	
	private String fechaActual;
	
	private boolean mostrarCheckBoletaElectronica;
	
	private boolean suscripcionBoletaElectronica;
	
	private boolean inscritoBoletaElectronica;
	
	private FacturacionElectronicaDelegate facturacionElectronicaDelegate;
    
    public void init(PhaseEvent event) {
    	loadData();
    }
    
    public void loadData(){
    	
    	String numeroPcs = "";
        String rut = "";
    	
    	try {
    		
            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,
                    "numeroPcs");
            rut = ProfileWrapperHelper.getPropertyAsString(profile,
                    "rutUsuario");

            loadUsuarioData(rut, numeroPcs);
            
            error = false;
            
        } catch (DAOException e) {
            LOGGER.error("DAOException al buscar datos de Usuario", e);
            error = true;
            JSFMessagesHelper.addServiceErrorMessage("obtenerDatosUsuario");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar datos de Usuario. msisdn: "+numeroPcs);
            error = true;
            JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", e
                    .getCodigoRespuesta(), new String[] { "Rut " + rut });
        } catch (Exception e) {
            LOGGER.error("Exception al buscar datos de usuario", e);
            error = true;
            JSFMessagesHelper.addServiceErrorMessage("obtenerDatosUsuario");
        }
       
    }
    
    public void initCuentaClienteBSCS(PhaseEvent event){    	
    	InscripcionController inscripcion = new InscripcionController();
    	inscripcion.prepareData();
    	
		String rut = "";
		if (inscripcion.getRutUsuario() != null) {
			rut = inscripcion.getRutUsuario();
		} else {
			ProfileWrapper profile = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());
			try {
				rut = ProfileWrapperHelper.getPropertyAsString(profile,
						"rutUsuarioSeleccionado");
			} catch (Exception e) { }
		}
   	
    	try {
             String idContenido = MiEntelProperties.getProperty("inscripcion.formRegistro.autorizacionVoluntaria.idContenido");
             Node mensajeServicio = JSFPortletHelper.getContentNode("idContenido", idContenido);
             autorizacionVoluntariaContent = mensajeServicio.getProperty("html").getValue().getStringValue();
         	
         	/*
         	 * Aplicamos el formato '#.###.###-#' al rutTitular y rutUsuario.
         	 * Ejemplo: 1.000.502-7
         	 */
             
         	RutConverter converter = new RutConverter();
         	String rutTitularFormatted = converter.getAsString(
         									null, null, RutBean.parseRut(rut));        	
         	String rutUsuarioFormatted = converter.getAsString(
         									null, null, RutBean.parseRut(rut));
         	
         	Formatter rutFmt = new Formatter();
         	rutFmt.format("%09d", Integer.parseInt(rut));

            cuentaClienteBean = obtenerCuentaCliente(rutFmt.toString()); 
         	
         	autorizacionVoluntariaContent = autorizacionVoluntariaContent
         			.replace("{nombreTitularCompleto}", cuentaClienteBean.getRazonSocial())
         			.replace("{rutTitular}", rutTitularFormatted)
         			.replace("{domicilioTitular}", cuentaClienteBean.getDireccion())
         			.replace("{comunaTitular}", "")
         			.replace("{cuenta}", "")
         			.replace("{fechaActual}", getFechaActual())
         			.replace("{nombreUsuarioCompleto}", "")
         			.replace("{rutUsuario}", rutUsuarioFormatted);
             
            autorizacionVoluntariaContent = autorizacionVoluntariaContent.replace("{email}", "");
            
        } catch (Exception e) {
            LOGGER.error("Exception al buscar datos de usuario", e);
        }
    	
    }
    
    public CuentaClienteBean obtenerCuentaCliente(String rut){
    	try{

    		List<CuentaClienteBean> cuentasClienteBean = cuentaDelegate.obtenerCuentaCliente(rut);    		
    		
    		if (cuentasClienteBean !=null){
    			CuentaClienteBean cuentaCliente = cuentasClienteBean.get(0);
    			return cuentaCliente;
    		}
    		
    	} catch (DAOException e) {
            LOGGER.error("DAOException al buscar datos de Usuario", e);
            JSFMessagesHelper.addServiceErrorMessage("obtenerDatosUsuario");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar datos de Usuario. msisdn: ");
            JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", e
                    .getCodigoRespuesta(), new String[] { "Rut " + rut });
        } catch (Exception e) {
            LOGGER.error("Exception al buscar datos de usuario", e);
            JSFMessagesHelper.addServiceErrorMessage("obtenerDatosUsuario");
        } 
        
        return null;
    }
    
	public void initCheckBoletaElectronica(PhaseEvent event) {
		try {
			ProfileWrapper profile = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());
			
			String mercado = ProfileWrapperHelper.getPropertyAsString(profile,
					"mercado");
			String aaa = ProfileWrapperHelper.getPropertyAsString(profile,
					"aaa");
			String rut = ProfileWrapperHelper.getPropertyAsString(profile,
					"rutUsuarioSeleccionado");
			String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(
					profile, "numeroCuenta");

			mostrarCheckBoletaElectronica = ((MiEntelBusinessHelper
					.isMercadoCuentaControlada(mercado) || MiEntelBusinessHelper
					.isMercadoSuscripcion(mercado)) && MiEntelBusinessHelper
					.isAAATitular(aaa));

			if (mostrarCheckBoletaElectronica) {
				/*
				 * Verifica si el usuario se encuentra inscrito al servicio de
				 * boleta electronica
				 */
				FacturacionElectronicaBean facturacionElectronica = facturacionElectronicaDelegate
						.getFacturacionElectronica(rut, numeroCuenta);
				inscritoBoletaElectronica = facturacionElectronica != null;
				suscripcionBoletaElectronica = facturacionElectronica != null;
			}
		} catch (ServiceException e) {
			LOGGER.error("ServiceException al buscar estado de boleta electronica", e);
			if (e.getCodigoRespuesta().equals("0006")) {
				inscritoBoletaElectronica = false;
			} else {
				mostrarCheckBoletaElectronica = false;
			}
		} catch (DAOException e) {
			LOGGER.error("DAOException al buscar estado de boleta electronica", e);
			mostrarCheckBoletaElectronica = false;
		} catch (Exception e) {
			LOGGER.error("Exception al buscar estado de boleta electronica", e);
			mostrarCheckBoletaElectronica = false;
		}
	}
    
    /**
     * @return the cuentaDelegate
     */
    public CuentaDelegate getCuentaDelegate() {
        return cuentaDelegate;
    }

    /**
     * @param cuentaDelegate
     *            the cuentaDelegate to set
     */
    public void setCuentaDelegate(CuentaDelegate cuentaDelegate) {
        this.cuentaDelegate = cuentaDelegate;
    }

    /**
     * @return the parametrosDelegate
     */
    public ParametrosDelegate getParametrosDelegate() {
        return parametrosDelegate;
    }

    /**
     * @param parametrosDelegate
     *            the parametrosDelegate to set
     */
    public void setParametrosDelegate(ParametrosDelegate parametrosDelegate) {
        this.parametrosDelegate = parametrosDelegate;
    }
    

    /**
     * Obtener el usuario en sesion o un usuario asociado a el usuario principal
     * 
     * @return UsuarioBean
     * @throws ServiceException
     * @throws DAOException
     */
    public UsuarioBean getUsuario() {        
        if (usuarioBean == null && !error) {
        	loadData();
        }
        return this.usuarioBean;
    }

    
    /**
     * Consulta la informacion de un usuario obteniendo los datos de consulta
     * del request y redireciona a la pantalla de modificacion de usuario.
     * 
     * @return String de navegacion a "informacion_usuario"
     * @throws ServiceException
     * @throws DAOException
     */
    public String mostrarDatosUsuario() {

        String nroPcs = JsfUtil.getRequestParameter("nroPcs");
        String rut = JsfUtil.getRequestParameter("rut");
        
        try {
            
            loadUsuarioData(rut, nroPcs);

        } catch (DAOException e) {
            LOGGER.error("DAOException al buscar datos de Usuario", e);
            JSFMessagesHelper.addServiceErrorMessage("obtenerDatosUsuario");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar datos de Usuario. msisdn: "+nroPcs);
            JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", e
                    .getCodigoRespuesta(), new String[] { "Rut " + rut });
        } catch (Exception e) {
            LOGGER.error("Exception al buscar datos de usuario", e);
            JSFMessagesHelper.addServiceErrorMessage("obtenerDatosUsuario");
        }

        return "informacion_usuario";
    }

    /**
     * Metodo utilitario para recuperar la informacion del usuario asociado al
     * rut y numeroPcs indicado en los parametros del mismo nombre.<br>
     * El usuario recuperado es asignado al atributo <code>usuarioBean</code> de
     * esta clase, ademas de setear los flags necesarios para el jsp de
     * presentacion de "Mis Datos".<br>
     * En caso de excepcion, este metodo las lanza, para que cada accion de
     * negocio las trate como corresponda.
     * 
     * 
     * @param rut
     * @param numeroPcs
     * 
     * @return {@link UsuarioBean} con el usuario encontrado, null en caso de no
     *         existir o producise un error.
     * @throws ServiceException
     *             si los servicios arrojan esa Excepcion
     * @throws DAOException
     *             si la clase DAO arroja esa Excepcion
     * @throws Exception
     *             en caso de un error inesperado
     */
    private void loadUsuarioData(String rut, String numeroPcs)
            throws ServiceException, DAOException, Exception {

        ProfileWrapper profile = ProfileWrapperHelper
                .getProfile(JSFPortletHelper.getRequest());

        String mercado = ProfileWrapperHelper.getPropertyAsString(profile,
                "mercado");

        this.usuarioBean = this.cuentaDelegate.obtenerUsuario(numeroPcs, rut);

        panelSSCC = ((MiEntelBusinessHelper.isMercadoCuentaControlada(mercado) || 
                MiEntelBusinessHelper.isMercadoSuscripcion(mercado)) && 
                MiEntelBusinessHelper.isAAATitular(usuarioBean.getAaa()));

        panelAdmin = this.getUserAdmin(this.usuarioBean.getAaa());
        
        suscripcionBoletaElectronica = false;

        if (MiEntelBusinessHelper.isAAATitular(this.usuarioBean.getAaa())) {

            DireccionBean direccionFactura = null;

            try {

                String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(
                        profile, "numeroCuenta");

                direccionFactura = this.cuentaDelegate.obtenerDireccionFactura(
                        rut, numeroCuenta);
            } catch (Exception ex) {
                // Si no hay informacion de factura se configuran valores por
                // defecto
                direccionFactura = new DireccionBean();
                direccionFactura.setComuna(new ComunaBean("", ""));
            }

            this.usuarioBean.setDireccionFactura(direccionFactura);

        }
        else {
            DireccionBean direccionFactura = new DireccionBean();
            direccionFactura.setComuna(new ComunaBean("", ""));
            this.usuarioBean.setDireccionFactura(direccionFactura);
        }

    }

    /**
     * Actualizar informacion del Usuario 
     * @return null postback
     */
    public String actualizarDatos() {
        try {
            
            this.cuentaDelegate.actualizarDatos(this.usuarioBean);

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            String aaa = ProfileWrapperHelper.getPropertyAsString(profile,
                    "aaa");
            String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(
                    profile, "numeroCuenta");
			String numeroPcs = ProfileWrapperHelper.getPropertyAsString(
					profile, "numeroPcsSeleccionado");
			String rut = ProfileWrapperHelper.getPropertyAsString(profile,
					"rutUsuarioSeleccionado");         

//            if (AAA_TITULAR.equals(aaa)
//                    && AAA_TITULAR.equals(this.usuarioBean.getAaa())) {
            if (MiEntelBusinessHelper.isAAATitular(this.usuarioBean.getAaa())
                    && MiEntelBusinessHelper.isAAATitular(aaa)) {
                LOGGER.info("Es titular -> Actualizar direccion postal");
                
                InetAddress ip = InetAddress.getLocalHost();

                try {
                    this.cuentaDelegate.actualizarDireccionPostal(numeroCuenta,
                            ip.getHostAddress(), this.usuarioBean
                                    .getDireccionFactura());
                } catch (Exception e) {
                    LOGGER.error(
                            "No se pudo Actualizar la direccion postal, msisdn : "
                                    + this.usuarioBean.getNumeroPCS(), e);
                }
            }
            
			// Inscribir a Boleta Electronica
            if (suscripcionBoletaElectronica) {
				try {
					if (!inscritoBoletaElectronica) {
						facturacionElectronicaDelegate.inscribirServicioFacturacionElectronica(rut,
										numeroCuenta, this.usuarioBean.getEmail(), numeroPcs);
					} else {
						facturacionElectronicaDelegate.actualizarServicioFacturacionElectronica(rut,
										numeroCuenta, this.usuarioBean.getEmail(), numeroPcs);
					}
				} catch (Exception e) {
					LOGGER.error("No se pudo suscribir el servicio de boleta electronica", e);
				}
			}
            
            //TODO cambiar a mensaje en properties
            JSFMessagesHelper.addSuccessMessage("Datos actualizados exitosamente");
            
        } catch (DAOException e) {
            LOGGER.error("DAOException al actualizar otros datos de Usuario", e);
            JSFMessagesHelper.addServiceErrorMessage("actualizarUsuario",
                    new String[] { "Rut "
                            + this.usuarioBean.getRut().getStringValue() });
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al actualizar otros datos de Usuario");
            JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", e
                    .getCodigoRespuesta(), new String[] { "Rut "
                    + this.usuarioBean.getRut().getStringValue() });
        } catch (Exception e) {
            LOGGER.error("Exception no esperada al actualizar otros datos de usuario");
            JSFMessagesHelper.addServiceErrorMessage("actualizarUsuario",
                    new String[] { "Rut "
                            + this.usuarioBean.getRut().getStringValue() });
        }

        return "informacion_usuario";
    }

    
    
     

    /**
     * Devuleve un a Regiones 
     * @return SelectItem[]
     * @throws DAOException
     */
    public SelectItem[] getRegionesList() throws DAOException {
        if (this.regionesList == null) {
            try {
                this.regionesList = JsfUtil.getSelectItemsCodeDesc(
                        this.parametrosDelegate.getRegionesList(), false);
            } catch (Exception e) {
                LOGGER.error("Error al cargar Regiones", e);
                regionesList = new SelectItem[0];
            }
        }
        return this.regionesList;
    }
    
    /**
     * 
     * @return
     * @throws DAOException
     */
    public List<CiudadBean> getCiudadesList() {
        try{
        if (RegionBean.isEmptyBean(this.usuarioBean.getDireccionContacto()
                .getRegion())) {

            itemsCiudades = Collections.emptyList();

        } else {
            
            if (itemsCiudades == null) {
    
                    itemsCiudades = this.parametrosDelegate
                            .getCiudadesList(this.usuarioBean
                                    .getDireccionContacto().getRegion());
            }
        }

        } catch (Exception e) {
            LOGGER.error("Error al cargar Ciudades", e);
            itemsCiudades = Collections.emptyList();
        }
        return itemsCiudades;
    }
    
    /**
     * 
     * @return
     * @throws DAOException
     */
    public List<ComunaBean> getComunasList() {
        try {
        if (RegionBean.isEmptyBean(this.usuarioBean.getDireccionContacto()
                .getRegion())
                && CiudadBean.isEmptyBean(this.usuarioBean
                        .getDireccionContacto().getCiudad())) {

            itemsComunas = Collections.emptyList();
            
        } else {

            if (itemsComunas == null) {

                
                    itemsComunas = this.parametrosDelegate
                            .getComunasList(this.usuarioBean
                                    .getDireccionContacto().getRegion(),
                                    this.usuarioBean.getDireccionContacto()
                                            .getCiudad());
               
            }
        }
        } catch (Exception e) {
            LOGGER.error("Error al cargar Ciudades", e);
            itemsComunas = Collections.emptyList();
        }

        return itemsComunas;
    }
    

    /**
     * 
     * @return
     * @throws DAOException
     */
    public SelectItem[] getNivelEstudiosList() throws DAOException {
        return JsfUtil.getSelectItemsCodeDesc(ParametrosHelper
                .getNivelEstudiosList(), false);
    }

    /**
     * 
     * @return
     * @throws DAOException
     */
    public SelectItem[] getAreaLaboralList() throws DAOException {
        return JsfUtil.getSelectItemsCodeDesc(ParametrosHelper
                .getAreaLaboralList(), false);
    }

    /**
     * 
     * @return
     * @throws DAOException
     */
    public SelectItem[] getSexoList() throws DAOException {
        return JsfUtil.getSelectItemsCodeDesc(ParametrosHelper.getSexoList(),
                false);
    }

    /**
     * 
     * @return
     * @throws DAOException
     */
    public SelectItem[] getActividadLaboralList() throws DAOException {
        return JsfUtil.getSelectItemsCodeDesc(ParametrosHelper
                .getActividadLaboralList(), false);
    }

    /**
     * 
     * @return
     * @throws DAOException
     */
    public SelectItem[] getRelacionesTitularList() throws DAOException {
        return JsfUtil.getSelectItemsCodeDesc(ParametrosHelper
                .getRelacionesTitularList(), false);
    }

    /**
     * 
     * @return
     * @throws DAOException
     */
    public SelectItem[] getEstadosCivilList() throws DAOException {
        return JsfUtil.getSelectItemsCodeDesc(ParametrosHelper
                .getEstadosCivilList(), false);
    }

    /**
     * 
     * @return
     * @throws DAOException
     */
    public SelectItem[] getHijosList() throws DAOException {
        return JsfUtil.getSelectItemsCodeDesc(ParametrosHelper.getHijosList(),
                false);
    }    
    
	/**
	 * Metodo que devuelve la lista de los prefijos para red fija
	 * 
	 * @return
	 */
	public List<SelectItem> getPrefijosTelefono() {
		return ParametrosHelper.getPrefijoTelefonoParametrosList();
	}

    /**
     * Mensajes de error de metodos get
     * @return
     */
    public String getMensajeError(){
        return this.mensajeError;
    }
    
    public CuentaClienteBean getCuentaClienteBean() {
		return cuentaClienteBean;
	}
    
	public void setCuentaClienteBean(CuentaClienteBean cuentaClienteBean) {
		this.cuentaClienteBean = cuentaClienteBean;
	}
    
    /**
     * 
     * @return
     * @throws DAOException
     */
    public SelectItem[] getComunasListDirFactura() {
        
        try {
            LOGGER.info("Cargando Comunas List Factura");
            if(itemsComunasDirFactura == null || itemsComunasDirFactura.length == 0){
                itemsComunasDirFactura = JsfUtil.getSelectItemsCodeDesc(this.parametrosDelegate
                    .getComunasList(new com.epcs.bean.RegionBean("TODAS",""), new com.epcs.bean.CiudadBean("TODAS","")),true, "Seleccione");
             
            }
        } catch (Exception e) {
            LOGGER.error("Error al cargar Ciudades", e);
            itemsComunasDirFactura = new  SelectItem[0];
        }
        return itemsComunasDirFactura;
    }
    
    public boolean getUserAdmin(int userCodigoAAA) {
        boolean isUserAdmin = false;
        try {

            isUserAdmin = MiEntelBusinessHelper.isAAATitular(userCodigoAAA);

        } catch (Exception e) {
            LOGGER.error("Exception inesperado listar mis usuarios", e);
          //TODO cambiar a mensaje en properties
            JSFMessagesHelper.addErrorMessage("Ha ocurrido un error inesperado. "
                    + " Disculpe las molestias");
        }

        return isUserAdmin;

    }

    /**
     * @return the panelSSCC
     */
    public boolean getPanelSSCC() {
        return panelSSCC;
    }

    /**
     * @param panelSSCC the panelSSCC to set
     */
    public void setPanelSSCC(boolean panelSSCC) {
        this.panelSSCC = panelSSCC;
    }

    /**
     * @return the panelAdmin
     */
    public boolean getPanelAdmin() {
        return panelAdmin;
    }

    /**
     * @param panelAdmin the panelAdmin to set
     */
    public void setPanelAdmin(boolean panelAdmin) {
        this.panelAdmin = panelAdmin;
    }
    
    /**
     * 
     * @return
     */
    public boolean getRenderEmailFacturaElectronica() {
    	if (this.usuarioBean.getEmailFacturaElectronica().trim().isEmpty()) {
    		renderEmailFacturaElectronica = false;
    	} else {
    		renderEmailFacturaElectronica = true;
    	}
		return renderEmailFacturaElectronica;
	}

	/**
     * Mensaje de usuario no autorizado
     * @return
     */
    public String getMensajeUsuarioNoAutorizado(){
        String mensaje = "";
        try{
         mensaje = MiEntelProperties
        .getProperty("solo.usuario.titular");
        }catch (Exception e) {
            LOGGER.error("Erro al obtener propiedad [solo.usuario.tituar]",e);
        }
        return mensaje;
    }
    
    /**
     * 
     * @return
     */
    public String getUrlFacturacionElectronica() {

        String pageLabel = "";

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String mercado = ProfileWrapperHelper.getPropertyAsString(profile,
                    "mercado");

            if (MiEntelProperties.getProperty(
                    "parametros.plan.suscripcion.sigla").equals(mercado)) {
                pageLabel = "ss_page_facturaElectronica_iter2";
            }
            else if (MiEntelProperties.getProperty(
                    "parametros.plan.cuentacontrolada.sigla").equals(mercado)) {
                pageLabel = "cc_page_facturaElectronica_iter2";
            }
        } catch (Exception e) {
        	LOGGER.error("Exception caught: "+e.getMessage(),e);
        }

        return JSFPortletHelper.createUrlPage(pageLabel);
    }
    
    
    /**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * dependiendo del mercado como clave asociada al valor.
     * @return
     */
    public String getPageLabelFacturaElectronica(){
    	try{
    	  ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
    		
    	  return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), ProfileWrapperHelper.getPropertyAsString(
                  profileWrapper, "mercado"), null);
		 
        }catch(Exception e){
        	LOGGER.error("No se ha podido obtener el pageLabel "+ e.getMessage());
        	return "";
        }
    }
    
    
    /**
     * Metodo utilitario para validar la visualizacion del boton cambiar usuario.
     * El boton es visible cuando el atributo de autoatencion es titular y el numeroPcs
     * de titular es distinto al mostrado en el form.
     * 
     * @return
     */
    public boolean isActivarCambiarUsuario(){
    	boolean resp = false;
    	String numeroPcs = "";
    	try{
      	  ProfileWrapper profileWrapper = ProfileWrapperHelper
              .getProfile(JSFPortletHelper.getRequest());
      	  
      	    numeroPcs = ProfileWrapperHelper.getPropertyAsString(
	                profileWrapper, "numeroPcs");
      	  if( MiEntelBusinessHelper.isAAATitular(ProfileWrapperHelper.getPropertyAsString(
                  profileWrapper, "aaa")) ){
      		  if(numeroPcs.equals(usuarioBean.getNumeroPCS())){
      			  resp = false;
      		  }else{
      			 resp = true;
      		  } 
      	  }
      	  
          }catch(Exception e){
          	LOGGER.error("No se ha podido obtener y validar los datos del perfil "+ e.getMessage(), e);
          }
          

    	
    	return resp;
    }
    
    /**
	 * Metodo utilitario para la obtencion de la URL y redireccionamiento al
	 * Formulario de Ingreso Registro Usuario desde el formulario Mis Datos.
	 * 
	 */
    public String redirectFormIngresoRut(){
    	InscripcionHelper insc = new InscripcionHelper();
    	try{
    		insc.redirectFormIngresoRut(
    				insc.getAccesoFormMisDatos(),usuarioBean.getNumeroPCS());
        }catch(Exception e){
        	LOGGER.error("Exception al intentar obtener y redireccionar a la URL del Formulario de Inscripcion"+ 
        			e.getMessage());
        }
        return "";
    }
  
    public String getFechaActualFormat() { 
    	Calendar c = Calendar.getInstance(); 
    	String fechaActualFormat = (c.getTimeInMillis() / 1000L)+"";
    	return fechaActualFormat;
	} 
    
	public String getFechaActual() {
        if( fechaActual == null ){
            fechaActual = DateHelper.format(new Date(), "dd MMMM yyyy");
        }
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

	public String getAutorizacionVoluntariaContent() {
		return autorizacionVoluntariaContent;
	}

	public void setAutorizacionVoluntariaContent(
			String autorizacionVoluntariaContent) {
		this.autorizacionVoluntariaContent = autorizacionVoluntariaContent;
	}

	public boolean isMostrarCheckBoletaElectronica() {
		return mostrarCheckBoletaElectronica;
	}

	public void setMostrarCheckBoletaElectronica(
			boolean mostrarCheckBoletaElectronica) {
		this.mostrarCheckBoletaElectronica = mostrarCheckBoletaElectronica;
	}

	public boolean isSuscripcionBoletaElectronica() {
		return suscripcionBoletaElectronica;
	}

	public void setSuscripcionBoletaElectronica(boolean suscripcionBoletaElectronica) {
		this.suscripcionBoletaElectronica = suscripcionBoletaElectronica;
	}

	public boolean isInscritoBoletaElectronica() {
		return inscritoBoletaElectronica;
	}

	public void setInscritoBoletaElectronica(boolean inscritoBoletaElectronica) {
		this.inscritoBoletaElectronica = inscritoBoletaElectronica;
	}

	public FacturacionElectronicaDelegate getFacturacionElectronicaDelegate() {
		return facturacionElectronicaDelegate;
	}

	public void setFacturacionElectronicaDelegate(
			FacturacionElectronicaDelegate facturacionElectronicaDelegate) {
		this.facturacionElectronicaDelegate = facturacionElectronicaDelegate;
	}
}