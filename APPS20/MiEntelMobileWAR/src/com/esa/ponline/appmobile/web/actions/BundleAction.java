/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.epcs.cliente.orden.types.ResultadoContrataBolsaSSCCType;
import com.epcs.cliente.perfil.types.ResultadoConsultarUsuarioBuicType;
import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.exceptions.ServiceException;
import com.esa.ponline.appmobile.exceptions.WSDAOException;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.vo.Bundle.AvailableBundleBAMCCPP;
import com.esa.ponline.appmobile.vo.Bundle.AvailableBundleBAMSSCC;
import com.esa.ponline.appmobile.vo.Bundle.BundleCCPPVO;
import com.esa.ponline.appmobile.vo.Bundle.BundleSSCCVO;
import com.esa.ponline.appmobile.vo.Bundle.PurchasedPPBundle;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.web.delegate.DelegateBundle;
import com.esa.ponline.appmobile.web.delegate.cache.CacheCompraBolsa;
import com.esa.ponline.appmobile.web.helper.ActionHelper;
import com.esa.ponline.appmobile.web.helper.BundleHelper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online,
 *         EntelSA) version 1.0.0 Aug 12, 2014
 */

public class BundleAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 3430819699408192458L;

	private static final Logger LOGGER = Logger.getLogger(BundleAction.class);

	private DelegateBundle delegateBundle = new DelegateBundle();

	private ActionHelper actionHelper = new ActionHelper();

	private List<BundleCCPPVO> ppBundle = new ArrayList<BundleCCPPVO>();

	private List<BundleCCPPVO> voiceBundles = new ArrayList<BundleCCPPVO>();

	private List<BundleCCPPVO> dataBundles = new ArrayList<BundleCCPPVO>();

	private List<BundleCCPPVO> smsBundles = new ArrayList<BundleCCPPVO>();

	private List<BundleCCPPVO> mixedBundles = new ArrayList<BundleCCPPVO>();

	private List<BundleCCPPVO> plusBundles = new ArrayList<BundleCCPPVO>();

	private List<BundleCCPPVO> ppBolsas = new ArrayList<BundleCCPPVO>();

	private List<BundleSSCCVO> ssccBundle = new ArrayList<BundleSSCCVO>();

	//
	private List<BundleSSCCVO> voiceSSCCBundles = new ArrayList<BundleSSCCVO>();

	private List<BundleSSCCVO> dataSSCCBundles = new ArrayList<BundleSSCCVO>();

	private List<BundleSSCCVO> smsSSCCBundles = new ArrayList<BundleSSCCVO>();

	private List<BundleSSCCVO> mmsSSCCBundles = new ArrayList<BundleSSCCVO>();

	private List<BundleSSCCVO> mixedSSCCBundles = new ArrayList<BundleSSCCVO>();

	private List<BundleSSCCVO> extSSCCBundles = new ArrayList<BundleSSCCVO>();
	//

	private List<BundleSSCCVO> ssccBolsas = new ArrayList<BundleSSCCVO>();

	private Map<String, Integer> bundleMap = new HashMap<String, Integer>();

	private BundleHelper bHelper = new BundleHelper();

	private String bolsasPlus = new String();

	private String posee = new String();

	private String poseeBolsaSeleccionSSCC = new String();

	private String mostrarCondiciones = new String();

	private ArrayList<PurchasedPPBundle> bolsasActivas;

	private List<BundleSSCCVO> bolsasContradadas;

	private SessionMap<String, Object> session;

	private String userBundleChoice;

	private String publicidad;

	private LoginVO datosClientes;

	private String bolsasCliente;

	private BundleCCPPVO bundlePP;

	private BundleSSCCVO bundleSSCC;

	private String unidad;

	private String bType;

	
	static LoadingCache<String, Boolean> cacheCompraBolsa = CacheBuilder.newBuilder().
		maximumSize(100).expireAfterWrite(10, TimeUnit.SECONDS).build(new CacheCompraBolsa());
	
    @Override
    public String execute() {
        datosClientes = (LoginVO) getSession().get("cliente");
        posee = "notiene";
        getPurchasedOrActiveUserBundle(datosClientes);
        try {
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

    // TODO Config properties
    // Accion que lista las bolsas disponibles
    public String loadAvailableUserBundle() throws Exception {
        ActionHelper aHelper = new ActionHelper();
        try {
            datosClientes = (LoginVO) getSession().get("cliente");
            String categoriaCliente =
                    aHelper.obtienePerfil(datosClientes.getMsisdn())
                            .getResumenPerfilamiento().getCategoriaCliente();
            defineUserBundleType(datosClientes.getMsisdn());
            bType = (String) getSession().get("btype");
            if (bType.equalsIgnoreCase("pp")) {
                setAllAvailableUserPPPBundles(datosClientes.getMsisdn());
                if (categoriaCliente.equalsIgnoreCase("MP1")) {
                    LOGGER.info("MSIDN [" + datosClientes.getMsisdn()
                            + "es cliente PP PLUS");
                    setBolsasPlus("mostrar");
                } else {
                    setBolsasPlus("nomostrar");
                }
            } else {
                setAllAvailableSSCCBundles(datosClientes.getMsisdn());
            }
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }
    
    private String buyBundlePP(String msisdn, String codeBundle, String tipoCobro) {
        try {
            com.epcs.provision.suscripcion.bolsasppmovil.types.ComprarResponseType.Mensaje response =
                    delegateBundle.comprarBolsa(datosClientes.getMsisdn(),
                            codeBundle, tipoCobro);
            LOGGER.info("CODIGO RESPONSE: " + response.getCodigo());
            LOGGER.info("DESCRIPCION RESPONSE: " + response.getDescripcion());
            LOGGER.info("PUBLICIDAD RESPONSE: " + response.getPublicidad());
            datosClientes = (LoginVO) getSession().get("cliente");
            if (response.getCodigo().equals("200")) {
                bundlePP = new BundleCCPPVO();
                setBundlePP(new BundleCCPPVO());
                setBundlePP(bHelper.getBundleDescriptionInfoPP(codeBundle,
                        datosClientes.getMsisdn()));
                setPublicidad(response.getPublicidad());
                bundlePP.setNombreUserAction("ok");
                DelegateBundle.cacheAvailableBundleBAMCCPP.invalidate(msisdn);
                DelegateBundle.cacheBundleBalancePP.invalidate(msisdn);
            } else if (response.getDescripcion() != null) {
                bundlePP = new BundleCCPPVO();
                bundlePP.setDescripcion(response.getDescripcion());
                bundlePP.setNombreUserAction("nok");
            }
        } catch (WSDAOException error) {
            LOGGER.error("WSDAOException lanzada al realizar transaccion para comprar bolsa prepago: "
                    + error.getMessage());
            error.printStackTrace();
            return "error_general";
        } catch (ServiceException error) {
            LOGGER.error("ServiceException lanzada al realizar transaccion para comprar bolsa prepago: "
                    + error.getMessage());
            error.printStackTrace();
            return "error_general";
        } catch (Exception e) {
            LOGGER.error("ServiceException lanzada al realizar transaccion para comprar bolsa prepago: "
                    + e);
            e.printStackTrace();
            return "error_general";
        }
        try {
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

    private String hiredBundleSSCC(String msisdn, String codBolsa,
            double valorBolsa, String opcionActivacion) {
        try {
            LOGGER.info("Iniciando contratacion de bolsa para MSISDN ["
                    + msisdn + "]");
            LOGGER.info("Codigo bolsa a contratar [" + codBolsa + "]");
            datosClientes = (LoginVO) getSession().get("cliente");
            ResultadoContrataBolsaSSCCType response =
                    delegateBundle.contratarBolsaSSCC(msisdn, codBolsa,
                            valorBolsa, opcionActivacion);
            LOGGER.info("CODIGO RESPONSE: "
                    + response.getRespuesta().getCodigo());
            LOGGER.info("DESCRIPCION RESPONSE: "
                    + response.getRespuesta().getDescripcion());
            if (response.getRespuesta().getCodigo().equals("0000")) {
                bundleSSCC = new BundleSSCCVO();
                setBundleSSCC(new BundleSSCCVO());
                setBundleSSCC(bHelper.getActualBundleDescriptionInfoSSCC(
                        codBolsa, msisdn));
                bundleSSCC.setNombreUserAction("ok");
                DelegateBundle.cacheAvailableBundleBAMSSCC.invalidate(msisdn);
                DelegateBundle.cacheBundleBAMSSCC.invalidate(msisdn);
            } else {
                bundleSSCC = new BundleSSCCVO();
                bundleSSCC.setNombreUserAction("nok");
            }
        } catch (WSDAOException error) {
            LOGGER.error("WSDAOException lanzada al realizar transaccion para comprar bolsa suscripcion: "
                    + error.getMessage());
            error.printStackTrace();
            return "error_general";
        } catch (ServiceException error) {
            LOGGER.error("ServiceException lanzada al realizar transaccion para comprar bolsa suscripcion: "
                    + error.getMessage());
            error.printStackTrace();
            return "error_general";
        } catch (Exception e) {
            LOGGER.error("ServiceException lanzada al realizar transaccion para comprar bolsa suscripcion: "
                    + e);
            e.printStackTrace();
            return "error_general";
        }
        try {
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

	// TODO Config properties
	public String confirmBundle() {
		datosClientes = (LoginVO) getSession().get("cliente");
		
		HttpServletRequest req = ServletActionContext.getRequest();
		String codeBundle = req.getParameter("edoc");

		// TODO Config properties o rescatar valores segun corresponda
		String tipoCobro = "";
		double valorBolsa = 0.0;
		String tipoActivacion = "A";

	try {
	    if(!cacheCompraBolsa.get(datosClientes.getMsisdn()+" | "+codeBundle)){
	        LOGGER.info("Usuario realiza confirmacion de la bolsa");
	        LOGGER.info("Codigo o Carta Bolsa a Contratar o Comprar: ["
	                + codeBundle + "]");
	        
	        LOGGER.info("Iniciando compra de bolsa");
	        LOGGER.info("Compramos la bolsa y cacheamos");
	        
	        String respuesta="error_general";
                //Usuario tiene permiso para comprar
                if (datosClientes.getAaa()>=2){
	        if (datosClientes.getMercado().equalsIgnoreCase("pp")) {
	    		respuesta= buyBundlePP(datosClientes.getMsisdn(), codeBundle, tipoCobro);
        } else {
	    		respuesta= hiredBundleSSCC(datosClientes.getMsisdn(), codeBundle,
                    valorBolsa, tipoActivacion);
	        }
                }else{
                    if (datosClientes.getMercado().equalsIgnoreCase("pp")) {
                	bundlePP = new BundleCCPPVO();
	    		bundlePP.setNombreUserAction("nok");
        
                    } else {
                	bundleSSCC=new BundleSSCCVO();
                	bundleSSCC.setNombreUserAction("nok");                	
                    }
                    return datosClientes.getResultNav();
                }
	        // Codigo DEBUG
	        /*
	        bundleSSCC = new BundleSSCCVO();
                setBundleSSCC(new BundleSSCCVO());
                setBundleSSCC(bHelper.getActualBundleDescriptionInfoSSCC(codeBundle, datosClientes.getMsisdn()));
                bundleSSCC.setNombreUserAction("ok");
	        respuesta=datosClientes.getResultNav();
	        */
	        
		LOGGER.info("Bolsa comprada");
	        LOGGER.info("Cacheamos bolsa comprada");
	        cacheCompraBolsa.put(datosClientes.getMsisdn()+"|"+codeBundle, true);	    
	        return respuesta;
	    }else{		
	        bundleSSCC = new BundleSSCCVO();
                setBundleSSCC(new BundleSSCCVO());
                setBundleSSCC(bHelper.getActualBundleDescriptionInfoSSCC(codeBundle, datosClientes.getMsisdn()));
                bundleSSCC.setNombreUserAction("ok");
                LOGGER.info("Respuesta desde cache");
	        return datosClientes.getResultNav();
	    }
	} catch (ExecutionException e) {
	    LOGGER.error("Error al leer desde la cache");
	    return "error_general";
	}
    }

	// Metodos para mostrar detalle de bolsa seleccionada PP
	public String getDetailVoiceBundlePP() throws Exception {
		datosClientes = (LoginVO) getSession().get("cliente");
		LOGGER.info("Usuario PP ha seleccionado bolsa");
		HttpServletRequest req = ServletActionContext.getRequest();
		String codeBundle = req.getParameter("carta");
		LOGGER.info(codeBundle);

		if (codeBundle.equalsIgnoreCase("CARTASERV0320")) {
			setUnidad(new String("Descripci\u00f3n"));
		} else {
			setUnidad(new String("Minutos"));
		}

        setBundlePP(new BundleCCPPVO());
        setBundlePP(bHelper.getBundleDescriptionInfoPP(codeBundle,
                datosClientes.getMsisdn()));

        try {
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

	public String getDetailDataBundlePP() throws Exception {
		datosClientes = (LoginVO) getSession().get("cliente");
		LOGGER.info("Usuario PP ha seleccionado bolsa");
		HttpServletRequest req = ServletActionContext.getRequest();
		setUnidad(new String("Datos"));

        String codeBundle = req.getParameter("carta");
        LOGGER.info(codeBundle);
        setBundlePP(new BundleCCPPVO());
        setBundlePP(bHelper.getBundleDescriptionInfoPP(codeBundle,
                datosClientes.getMsisdn()));

        try {
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

	public String getDetailMixedBundlePP() throws Exception {
		datosClientes = (LoginVO) getSession().get("cliente");
		LOGGER.info("Usuario PP ha seleccionado bolsa");
		HttpServletRequest req = ServletActionContext.getRequest();
		setUnidad(new String("Combinada"));

        String codeBundle = req.getParameter("carta");
        LOGGER.info(codeBundle);
        setBundlePP(new BundleCCPPVO());
        setBundlePP(bHelper.getBundleDescriptionInfoPP(codeBundle,
                datosClientes.getMsisdn()));

        try {
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

	public String getDetailSMSBundlePP() throws Exception {
		datosClientes = (LoginVO) getSession().get("cliente");
		LOGGER.info("Usuario PP ha seleccionado bolsa");
		HttpServletRequest req = ServletActionContext.getRequest();
		setUnidad(new String("Mensajes"));

        String codeBundle = req.getParameter("carta");
        LOGGER.info(codeBundle);
        setBundlePP(new BundleCCPPVO());
        setBundlePP(bHelper.getBundleDescriptionInfoPP(codeBundle,
                datosClientes.getMsisdn()));

        try {
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

	public String getDetailPlusBundlePP() throws Exception {
		datosClientes = (LoginVO) getSession().get("cliente");
		LOGGER.info("Usuario PP ha seleccionado bolsa");
		HttpServletRequest req = ServletActionContext.getRequest();
		setUnidad(new String("Descripcion"));

        String codeBundle = req.getParameter("carta");
        LOGGER.info(codeBundle);
        setBundlePP(new BundleCCPPVO());
        setBundlePP(bHelper.getBundleDescriptionInfoPP(codeBundle,
                datosClientes.getMsisdn()));

        try {
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

	// fi Metodos para mostrar detalle de bolsa seleccionada PP

	// Metodos para mostrar detalle de bolsa seleccionada SSCC
	public String getDetailVoiceBundleSSCC() throws Exception {
		datosClientes = (LoginVO) getSession().get("cliente");
		LOGGER.info("Usuario SSCC ha seleccionado bolsa");
		HttpServletRequest req = ServletActionContext.getRequest();
		setUnidad(new String("Minutos"));

		String codeBundle = req.getParameter("edoc");
		LOGGER.info(codeBundle);

        setBundleSSCC(new BundleSSCCVO());
        setBundleSSCC(bHelper.getBundleDescriptionInfoSSCC(codeBundle,
                datosClientes.getMsisdn()));

        try {
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

	public String getDetailDataBundleSSCC() throws Exception {
		datosClientes = (LoginVO) getSession().get("cliente");
		LOGGER.info("Usuario SSCC ha seleccionado bolsa");
		HttpServletRequest req = ServletActionContext.getRequest();
		setUnidad(new String("Datos"));

		String codeBundle = req.getParameter("edoc");
		LOGGER.info(codeBundle);

        setBundleSSCC(new BundleSSCCVO());
        setBundleSSCC(bHelper.getBundleDescriptionInfoSSCC(codeBundle,
                datosClientes.getMsisdn()));

        try {
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

	public String getDetailMixedBundleSSCC() throws Exception {
		datosClientes = (LoginVO) getSession().get("cliente");
		LOGGER.info("Usuario SSCC ha seleccionado bolsa");
		HttpServletRequest req = ServletActionContext.getRequest();
		setUnidad(new String("Combinada"));

		String codeBundle = req.getParameter("edoc");
		LOGGER.info(codeBundle);

        setBundleSSCC(new BundleSSCCVO());
        setBundleSSCC(bHelper.getBundleDescriptionInfoSSCC(codeBundle,
                datosClientes.getMsisdn()));

        try {
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

	public String getDetailSmsBundleSSCC() throws Exception {
		datosClientes = (LoginVO) getSession().get("cliente");
		LOGGER.info("Usuario SSCC ha seleccionado bolsa");
		HttpServletRequest req = ServletActionContext.getRequest();
		setUnidad(new String("Mensajes"));

		String codeBundle = req.getParameter("edoc");
		LOGGER.info(codeBundle);

        setBundleSSCC(new BundleSSCCVO());
        setBundleSSCC(bHelper.getBundleDescriptionInfoSSCC(codeBundle,
                datosClientes.getMsisdn()));

        try {
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

	// fi Metodos para mostrar detalle de bolsa seleccionada SSCC

	private void getPurchasedOrActiveUserBundle(LoginVO in) {
		if (in.getMercado().equalsIgnoreCase("pp")) {
			if (ppUserHasBundles(in.getMsisdn())) {
				posee = "tienePP";
			}
		} else {
			if (ssccUserHasBundles(in.getMsisdn())) {
				posee = "tieneSSCC";
			}
		}
	}

    private boolean ssccUserHasBundles(String msisdn) {
        AvailableBundleBAMSSCC response = new AvailableBundleBAMSSCC();
        try {
            response = delegateBundle.getAllAvailableSSCCBundles(msisdn);
            if (response.getBolsasActuales().isEmpty()) {
                LOGGER.info("Usuario no posee bolsas contratadas");
                return false;
            } else {
                if (response.getBolsasActuales().size() > 0) {
                    setBolsasContradadas(response.getBolsasActuales());
                    return true;
                }
            }
        } catch (AppMobileException error) {
            LOGGER.error("Servicio retorna falso");
            return false;
        } catch (WSDAOException error) {
            LOGGER.error("WSDAOException lanzada al realizar transaccion consultas bolsas activas suscripcion: "
                    + error.getMessage());
            error.printStackTrace();
            return false;
        } catch (ServiceException error) {
            LOGGER.error("ServiceException lanzada al realizar transaccion consultas bolsas activas suscripcion: "
                    + error.getMessage());
            error.printStackTrace();
            return false;
        } catch (Exception e) {
            LOGGER.error("ServiceException lanzada al realizar transaccion consultas bolsas activas suscripcion: "
                    + e);
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private boolean ppUserHasBundles(String msisdn) {
        try {
            // TODO: No se puede devolver null en un servicio que se cachea....
            bolsasActivas = delegateBundle.obtenerSaldoBolsasPP(msisdn);
            if (bolsasActivas.isEmpty()) {
                LOGGER.info("MSISDN [" + msisdn + "] no posee bolsas activas");
                return false;
            } else if (!bolsasActivas.isEmpty() && bolsasActivas.size() > 0) {
                LOGGER.info("MSISDN [" + msisdn + "] posee bolsas activas");
                return true;
            }
        } catch (WSDAOException error) {
            LOGGER.error("WSDAOException lanzada al realizar transaccion para consulta bolsa activa prepago: "
                    + error.getMessage());
            error.printStackTrace();
            return false;
        } catch (ServiceException error) {
            LOGGER.error("ServiceException lanzada al realizar transaccion para consulta bolsa activa prepago: "
                    + error.getMessage());
            error.printStackTrace();
            return false;
        } catch (Exception e) {
            LOGGER.error("ServiceException lanzada al realizar transaccion para consulta bolsa activa prepago: "
                    + e);
            e.printStackTrace();
            return false;
        }
        return false;
    }

    

    // TODO Config properties time
    private void setAllAvailableSSCCBundles(String msisdn) {
        try {
            // ssccBolsas =
            // getAllAvailableUserSSCCBundles(msisdn).getBolsasDisponibles();
            ssccBolsas = getAllAvailableUserSSCCBundles(msisdn).getBolsas();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
        }
    }

    private AvailableBundleBAMSSCC
            getAllAvailableUserSSCCBundles(String msisdn) {
        AvailableBundleBAMSSCC response = null;
        try {
            // response = delegateBundle.getSSCCBundlesSinCache(msisdn);
            response = delegateBundle.getSSCCBundles(msisdn);

            LOGGER.info("Se realiza consulta de bolsas disponibles para msisdn ["
                    + msisdn + "]");
        } catch (WSDAOException error) {
            LOGGER.error("WSDAOException lanzada al realizar transaccion para listar bolsa suscripcion: "
                    + error.getMessage());
            error.printStackTrace();
        } catch (ServiceException error) {
            LOGGER.error("ServiceException lanzada al realizar transaccion para listar bolsa suscripcion: "
                    + error.getMessage());
            error.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("ServiceException lanzada al realizar transaccion para listar bolsa suscripcion: "
                    + e);
            e.printStackTrace();
        }
        return response;
    }

    // TODO Config properties time
    private void setAllAvailableUserPPPBundles(String msisdn) {
        try {
            ppBolsas = getAllAvailableUserPPPBundles(msisdn).getBolsas();
            LOGGER.info("Se realiza consulta de bolsas disponibles para msisdn ["
                    + msisdn + "]");
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
        }
    }

    private AvailableBundleBAMCCPP getAllAvailableUserPPPBundles(String msisdn) {
        AvailableBundleBAMCCPP response = null;
        try {
            response = delegateBundle.getAllAvailablePPBundles(msisdn);
            LOGGER.info("Se realiza consulta de bolsas disponibles para msisdn ["
                    + msisdn + "]");
        } catch (WSDAOException error) {
            LOGGER.error("WSDAOException lanzada al realizar transaccion para listar bolsa prepago: "
                    + error.getMessage());
            error.printStackTrace();
        } catch (ServiceException error) {
            LOGGER.error("ServiceException lanzada al realizar transaccion para listar bolsa prepago: "
                    + error.getMessage());
            error.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("ServiceException lanzada al realizar transaccion para listar bolsa prepago: "
                    + e);
            e.printStackTrace();
        }
        return response;
    }

    // Cargas bolsas mercado prepago
    // Accion del usuario que muestra bolsas de voz
    public String loadUserVoiceBundle() {
        LOGGER.info("Se realiza consulta para bolsas Voz");
        setUserBundleChoice("voz");
        datosClientes = (LoginVO) getSession().get("cliente");
        try {
            ppBolsas =
                    getAllAvailableUserPPPBundles(datosClientes.getMsisdn())
                            .getBolsas();
            setVoiceBundles(ppBolsas);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
        }
        try {
            if (getVoiceBundles().iterator().hasNext()) {
                setMostrarCondiciones("ok");
            } else {
                setMostrarCondiciones("nok");
            }

            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

    // Accion del usuario que muestra bolsas de datos
    public String loadUserDataBundle() {
        LOGGER.info("Se realiza consulta para bolsas Datos");
        setUserBundleChoice("datos");
        datosClientes = (LoginVO) getSession().get("cliente");
        try {
            ppBolsas =
                    getAllAvailableUserPPPBundles(datosClientes.getMsisdn())
                            .getBolsas();
            setDataBundles(ppBolsas);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
        }
        try {
            if (getDataBundles().iterator().hasNext()) {
                setMostrarCondiciones("ok");
            } else {
                setMostrarCondiciones("nok");
            }

            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

    // Accion del usuario que muestra bolsas de mixtas
    public String loadUserMixedBundle() {
        LOGGER.info("Se realiza consulta para bolsas Combinadas");
        setUserBundleChoice("mixed");
        datosClientes = (LoginVO) getSession().get("cliente");
        try {
            ppBolsas =
                    getAllAvailableUserPPPBundles(datosClientes.getMsisdn())
                            .getBolsas();
            setMixedBundles(ppBolsas);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
        }
        try {
            if (getMixedBundles().iterator().hasNext()) {
                setMostrarCondiciones("ok");
            } else {
                setMostrarCondiciones("nok");
            }

            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

    // Accion del usuario que muestra bolsas de mixtas
    public String loadUserSMSBundle() {
        LOGGER.info("Se realiza consulta para bolsas SMS");
        setUserBundleChoice("sms");
        datosClientes = (LoginVO) getSession().get("cliente");
        try {
            ppBolsas =
                    getAllAvailableUserPPPBundles(datosClientes.getMsisdn())
                            .getBolsas();
            setSmsBundles(ppBolsas);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
        }
        try {
            if (getSmsBundles().iterator().hasNext()) {
                setMostrarCondiciones("ok");
            } else {
                setMostrarCondiciones("nok");
            }
            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

    // Accion del usuario que muestra bolsas de plus
    public String loadUserPlusBundle() {
        LOGGER.info("Se realiza consulta para bolsas IM");
        setUserBundleChoice("plus");
        datosClientes = (LoginVO) getSession().get("cliente");
        try {
            ppBolsas =
                    getAllAvailableUserPPPBundles(datosClientes.getMsisdn())
                            .getBolsas();
            setPlusBundles(ppBolsas);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
        }
        try {
            if (getPlusBundles().iterator().hasNext()) {
                setMostrarCondiciones("ok");
            } else {
                setMostrarCondiciones("nok");
            }

            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

    // Cargas bolsas mercado SSCC
    // Accion del usuario que muestra bolsas de voz
    public String loadUserSSCCVoiceBundle() {
        LOGGER.info("Se realiza consulta para bolsas Voz");
        setUserBundleChoice("vozSSCC");
        datosClientes = (LoginVO) getSession().get("cliente");
        try {
            ssccBolsas =
                    getAllAvailableUserSSCCBundles(datosClientes.getMsisdn())
                            .getBolsasDisponibles();
            setVoiceSSCCBundles(ssccBolsas);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
        }
        try {
            if (getVoiceSSCCBundles().iterator().hasNext()) {
                setPoseeBolsaSeleccionSSCC("ok");
                setMostrarCondiciones("ok");
            } else {
                setPoseeBolsaSeleccionSSCC("nok");
                setMostrarCondiciones("nok");
            }

            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

    // Accion del usuario que muestra bolsas de datos
    public String loadUserSSCCDataBundle() {
        LOGGER.info("Se realiza consulta para bolsas Datos");
        setUserBundleChoice("datosSSCC");
        datosClientes = (LoginVO) getSession().get("cliente");
        try {
            ssccBolsas =
                    getAllAvailableUserSSCCBundles(datosClientes.getMsisdn())
                            .getBolsasDisponibles();
            setDataSSCCBundles(ssccBolsas);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
        }
        try {
            if (getDataSSCCBundles().iterator().hasNext()) {
                setPoseeBolsaSeleccionSSCC("ok");
                setMostrarCondiciones("ok");
            } else {
                setPoseeBolsaSeleccionSSCC("nok");
                setMostrarCondiciones("nok");
            }

            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

    // Accion del usuario que muestra bolsas de mixtas
    public String loadUserSSCCMixedBundle() {
        LOGGER.info("Se realiza consulta para bolsas Combinadas");
        setUserBundleChoice("comSSCC");
        datosClientes = (LoginVO) getSession().get("cliente");
        try {
            ssccBolsas =
                    getAllAvailableUserSSCCBundles(datosClientes.getMsisdn())
                            .getBolsasDisponibles();
            setMixedSSCCBundles(ssccBolsas);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
        }
        try {
            if (getMixedSSCCBundles().iterator().hasNext()) {
                setPoseeBolsaSeleccionSSCC("ok");
                setMostrarCondiciones("ok");
            } else {
                setPoseeBolsaSeleccionSSCC("nok");
                setMostrarCondiciones("nok");
            }

            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

    // Accion del usuario que muestra bolsas de mixtas
    public String loadUserSSCCSMSBundle() {
        LOGGER.info("Se realiza consulta para bolsas SMS");
        setUserBundleChoice("smsSSCC");
        datosClientes = (LoginVO) getSession().get("cliente");
        try {
            ssccBolsas =
                    getAllAvailableUserSSCCBundles(datosClientes.getMsisdn())
                            .getBolsasDisponibles();
            setSmsSSCCBundles(ssccBolsas);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
        }
        try {
            if (getSmsSSCCBundles().iterator().hasNext()) {
                setPoseeBolsaSeleccionSSCC("ok");
                setMostrarCondiciones("ok");
            } else {
                setPoseeBolsaSeleccionSSCC("nok");
                setMostrarCondiciones("nok");
            }

            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

    // Accion del usuario que muestra bolsas de mixtas
    public String loadUserSSCCExtBundle() {
        LOGGER.info("Se realiza consulta para bolsas EXTRAS");
        setUserBundleChoice("extSSCC");
        datosClientes = (LoginVO) getSession().get("cliente");
        try {
            ssccBolsas =
                    getAllAvailableUserSSCCBundles(datosClientes.getMsisdn())
                            .getBolsasDisponibles();
            setExtSSCCBundles(ssccBolsas);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
        }
        try {
            if (getExtSSCCBundles().iterator().hasNext()) {
                setPoseeBolsaSeleccionSSCC("ok");
                setMostrarCondiciones("ok");
            } else {
                setPoseeBolsaSeleccionSSCC("nok");
                setMostrarCondiciones("nok");
            }

            return datosClientes.getResultNav();
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en BundleAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

    private void setVoiceBundles(List<BundleCCPPVO> in) {
        LOGGER.info("Consiguien bolsas de voz disponibles");
        for (int i = 0; i < in.size(); i++) {
            if (in.get(i).getUnidad().equalsIgnoreCase("SEG")
                    && in.get(i).getTipoOferta().equalsIgnoreCase("normal")) {
                BundleCCPPVO bundle = new BundleCCPPVO();
                bundle.setCodigo(in.get(i).getCodigo());
                bundle.setNombre(in.get(i).getNombre());
                bundle.setDescripcion(in.get(i).getDescripcion());
                bundle.setDescripcionComercial(in.get(i)
                        .getDescripcionComercial());
                bundle.setPrecio(in.get(i).getPrecio());
                voiceBundles.add(bundle);
            }
        }
        LOGGER.info("Finaliza la consulta para bolsas de tipo VOZ");
    }

    private void setDataBundles(List<BundleCCPPVO> in) {
        LOGGER.info("Consiguien bolsas de datos disponibles");
        for (int i = 0; i < in.size(); i++) {
            if ((in.get(i).getUnidad().equalsIgnoreCase("KB"))
                    && in.get(i).getTipoOferta().equalsIgnoreCase("normal")) {
                BundleCCPPVO bundle = new BundleCCPPVO();
                bundle.setCodigo(in.get(i).getCodigo());
                bundle.setNombre(in.get(i).getNombre());
                bundle.setDescripcion(in.get(i).getDescripcion());
                bundle.setDescripcionComercial(in.get(i)
                        .getDescripcionComercial());
                bundle.setPrecio(in.get(i).getPrecio());
                dataBundles.add(bundle);
            }
        }
        LOGGER.info("Finaliza la consulta para bolsas de tipo DATOS");
    }

    private void setMixedBundles(List<BundleCCPPVO> in) {
        LOGGER.info("Consiguien bolsas de voz disponibles");
        for (int i = 0; i < in.size(); i++) {
            if (in.get(i).getUnidad().equalsIgnoreCase("MIXTA")
                    && in.get(i).getTipoOferta().equalsIgnoreCase("normal")) {
                BundleCCPPVO bundle = new BundleCCPPVO();
                bundle.setCodigo(in.get(i).getCodigo());
                bundle.setNombre(in.get(i).getNombre());
                bundle.setDescripcion(in.get(i).getDescripcion());
                bundle.setDescripcionComercial(in.get(i)
                        .getDescripcionComercial());
                bundle.setPrecio(in.get(i).getPrecio());
                mixedBundles.add(bundle);
            }
        }
        LOGGER.info("Finaliza la consulta para bolsas de tipo COMBINADAS");
    }

    private void setSmsBundles(List<BundleCCPPVO> in) {
        LOGGER.info("Consiguien bolsas de voz disponibles");
        for (int i = 0; i < in.size(); i++) {
            if (in.get(i).getUnidad().equalsIgnoreCase("SMS")
                    && in.get(i).getTipoOferta().equalsIgnoreCase("normal")) {
                BundleCCPPVO bundle = new BundleCCPPVO();
                bundle.setCodigo(in.get(i).getCodigo());
                bundle.setNombre(in.get(i).getNombre());
                bundle.setDescripcion(in.get(i).getDescripcion());
                bundle.setDescripcionComercial(in.get(i)
                        .getDescripcionComercial());
                bundle.setPrecio(in.get(i).getPrecio());
                smsBundles.add(bundle);
            }
        }
        LOGGER.info("Finaliza la consulta para bolsas de tipo SMS");
    }

    private void setPlusBundles(List<BundleCCPPVO> in) {
        LOGGER.info("Consiguien bolsas PLUS disponibles");
        for (int i = 0; i < in.size(); i++) {
            if (in.get(i).getTipoOferta().equalsIgnoreCase("MP1")) {
                BundleCCPPVO bundle = new BundleCCPPVO();
                bundle.setCodigo(in.get(i).getCodigo());
                bundle.setNombre(in.get(i).getNombre());
                bundle.setDescripcion(in.get(i).getDescripcion());
                bundle.setDescripcionComercial(in.get(i)
                        .getDescripcionComercial());
                bundle.setPrecio(in.get(i).getPrecio());
                plusBundles.add(bundle);
            }
        }
        LOGGER.info("Finaliza la consulta para bolsas de tipo PLUS");
    }

    public List<BundleCCPPVO> getPlusBundles() {
        return plusBundles;
    }

    private void defineUserBundleType(String msisdn) {
        ResultadoConsultarUsuarioBuicType respUser =
                actionHelper.obtenerValorTipoBolsa(msisdn);
        String mercado = respUser.getUsuarioBuic().getMercado();
        String categoriaCliente =
                respUser.getUsuarioBuic().getVarCuantitativa2();
        if (mercado.equalsIgnoreCase("pp")) {
            if (categoriaCliente.contains("plus")) {
                setBolsasCliente(categoriaCliente.substring(
                        categoriaCliente.lastIndexOf(" ") + 1).toLowerCase());
                session.put(
                        "btype",
                        categoriaCliente.substring(
                                categoriaCliente.lastIndexOf(" ") + 1)
                                .toLowerCase());
                LOGGER.info("Se establece bolsas para PP Plus");
            } else {
                setBolsasCliente("pp");
                session.put("btype", mercado.toLowerCase());
                LOGGER.info("Se establece bolsas para PP Normal");
            }
        } else {
            setBolsasCliente(mercado.toLowerCase());
            session.put("btype", mercado.toLowerCase());
            LOGGER.info("Se establece bolsas para " + mercado);
        }
    }

    // TODO validar bolsas ocultas
    public void setVoiceSSCCBundles(List<BundleSSCCVO> in) {
        LOGGER.info("Obteniendo bolsas de voz disponibles para sscc");
        boolean ocultas = false;
        for (int i = 0; i < in.size(); i++) {
            if (in.get(i).getTipoBolsa().equalsIgnoreCase("MIN")) {
                String[] bOcultasID =
                        Config.getAppProperty("bolsasOcultasID").split(",");
                for (int j = 0; j < bOcultasID.length; j++) {
                    if (in.get(i).getSnCodigo().equalsIgnoreCase(bOcultasID[j])) {
                        ocultas = true;
                        break;
                    } else {
                        ocultas = false;
                    }
                }
                if (ocultas != true) {
                    BundleSSCCVO bundle = new BundleSSCCVO();
                    bundle.setNombreBolsa(in.get(i).getNombreBolsa());
                    bundle.setCosto(in.get(i).getCosto());
                    bundle.setSnCodigo(in.get(i).getSnCodigo());
                    voiceSSCCBundles.add(bundle);
                }
            }
        }
        LOGGER.info("Finaliza la consulta para bolsas de tipo VOZ");
    }

    public List<BundleSSCCVO> getDataSSCCBundles() {
        return dataSSCCBundles;
    }

    public void setDataSSCCBundles(List<BundleSSCCVO> in) {
        LOGGER.info("Obteniendo bolsas de datos disponibles para sscc");
        for (int i = 0; i < in.size(); i++) {
            if (in.get(i).getTipoBolsa().equalsIgnoreCase("BAM")
                    || in.get(i).getTipoBolsa().equalsIgnoreCase("WAP")
                    || in.get(i).getTipoBolsa().equalsIgnoreCase("IM")
                    || in.get(i).getTipoBolsa().equalsIgnoreCase("IMPRO")) {
                BundleSSCCVO bundle = new BundleSSCCVO();
                bundle.setNombreBolsa(in.get(i).getNombreBolsa());
                bundle.setCosto(in.get(i).getCosto());
                bundle.setSnCodigo(in.get(i).getSnCodigo());
                dataSSCCBundles.add(bundle);
            }
        }
        LOGGER.info("Finaliza la consulta para bolsas de tipo DATOS");
    }

    public List<BundleSSCCVO> getSmsSSCCBundles() {
        return smsSSCCBundles;
    }

    public void setSmsSSCCBundles(List<BundleSSCCVO> in) {
        LOGGER.info("Obteniendo bolsas de mensajes disponibles para sscc");
        for (int i = 0; i < in.size(); i++) {
            if (in.get(i).getTipoBolsa().equalsIgnoreCase("SMS")
                    || in.get(i).getTipoBolsa().equalsIgnoreCase("MMS")) {
                BundleSSCCVO bundle = new BundleSSCCVO();
                bundle.setNombreBolsa(in.get(i).getNombreBolsa());
                bundle.setCosto(in.get(i).getCosto());
                bundle.setSnCodigo(in.get(i).getSnCodigo());
                smsSSCCBundles.add(bundle);
            }
        }
        LOGGER.info("Finaliza la consulta para bolsas de tipo SMS");
    }

    public List<BundleSSCCVO> getMixedSSCCBundles() {
        return mixedSSCCBundles;
    }

    public void setMixedSSCCBundles(List<BundleSSCCVO> in) {
        LOGGER.info("Obteniendo bolsas combinadas disponibles para sscc");
        for (int i = 0; i < in.size(); i++) {
            if (in.get(i).getTipoBolsa().equalsIgnoreCase("COM")
                    || in.get(i).getTipoBolsa().equalsIgnoreCase("PCK")) {
                BundleSSCCVO bundle = new BundleSSCCVO();
                bundle.setNombreBolsa(in.get(i).getNombreBolsa());
                bundle.setCosto(in.get(i).getCosto());
                bundle.setSnCodigo(in.get(i).getSnCodigo());
                mixedSSCCBundles.add(bundle);
            }
        }
        LOGGER.info("Finaliza la consulta para bolsas de tipo COMBINADAS");
    }

	public List<BundleSSCCVO> getExtSSCCBundles() {
		return extSSCCBundles;
	}

	public void setExtSSCCBundles(List<BundleSSCCVO> in) {
		LOGGER.info("Obteniendo bolsas extras disponibles para sscc");
		for (int i = 0; i < in.size(); i++) {
			if (in.get(i).getTipoBolsa().equalsIgnoreCase("EXT")) {
				BundleSSCCVO bundle = new BundleSSCCVO();
				bundle.setNombreBolsa(in.get(i).getNombreBolsa());
				bundle.setCosto(in.get(i).getCosto());
				bundle.setSnCodigo(in.get(i).getSnCodigo());
				extSSCCBundles.add(bundle);
			}
		}
		LOGGER.info("Finaliza la consulta para bolsas de tipo EXTRAS");
	}

	private Map<String, Object> getSession() {
		Map<String, Object> attibutes = ActionContext.getContext().getSession();
		return attibutes;
	}

	public LoginVO getDatosClientes() {
		return datosClientes;
	}

	public void setDatosClientes(LoginVO datosClientes) {
		this.datosClientes = datosClientes;
	}

	public String getBolsasCliente() {
		return bolsasCliente;
	}

	public void setBolsasCliente(String bolsasCliente) {
		this.bolsasCliente = bolsasCliente;
	}

	public List<BundleCCPPVO> getPpBundle() {
		Collections.sort(ppBundle);
		return ppBundle;
	}

	public void setPpBundle(List<BundleCCPPVO> ppBundle) {
		this.ppBundle = ppBundle;
	}

	public Map<String, Integer> getBundleMap() {
		return bundleMap;
	}

	public void setBundleMap(Map<String, Integer> bundleMap) {
		this.bundleMap = bundleMap;
	}

	public List<BundleCCPPVO> getVoiceBundles() {
		return voiceBundles;
	}

	public String getUserBundleChoice() {
		return userBundleChoice;
	}

	public void setUserBundleChoice(String userBundleChoice) {
		this.userBundleChoice = userBundleChoice;
	}

	public List<BundleCCPPVO> getDataBundles() {
		return dataBundles;
	}

	public String getPosee() {
		return posee;
	}

	public void setPosee(String posee) {
		this.posee = posee;
	}

	public List<BundleCCPPVO> getSmsBundles() {
		return smsBundles;
	}

	public List<BundleCCPPVO> getMixedBundles() {
		return mixedBundles;
	}

	public List<BundleSSCCVO> getSsccBundle() {
		Collections.sort(ssccBundle);
		return ssccBundle;
	}

	public void setSsccBundle(List<BundleSSCCVO> ssccBundle) {
		this.ssccBundle = ssccBundle;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		session = (SessionMap<String, Object>) map;
	}

	public List<BundleSSCCVO> getVoiceSSCCBundles() {
		return voiceSSCCBundles;
	}

	public List<BundleSSCCVO> getMmsSSCCBundles() {
		return mmsSSCCBundles;
	}

	public ArrayList<PurchasedPPBundle> getBolsasActivas() {
		return bolsasActivas;
	}

	public void setBolsasActivas(ArrayList<PurchasedPPBundle> bolsasActivas) {
		this.bolsasActivas = bolsasActivas;
	}

	public List<BundleSSCCVO> getBolsasContradadas() {
		return bolsasContradadas;
	}

	public void setBolsasContradadas(List<BundleSSCCVO> bolsasContradadas) {
		this.bolsasContradadas = bolsasContradadas;
	}

	public BundleCCPPVO getBundlePP() {
		return bundlePP;
	}

	public void setBundlePP(BundleCCPPVO bundlePP) {
		this.bundlePP = bundlePP;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getPublicidad() {
		return publicidad;
	}

	public void setPublicidad(String publicidad) {
		this.publicidad = publicidad;
	}

	public BundleSSCCVO getBundleSSCC() {
		return bundleSSCC;
	}

	public void setBundleSSCC(BundleSSCCVO bundleSSCC) {
		this.bundleSSCC = bundleSSCC;
	}

	public String getBolsasPlus() {
		return bolsasPlus;
	}

	public void setBolsasPlus(String bolsasPlus) {
		this.bolsasPlus = bolsasPlus;
	}

	public String getPoseeBolsaSeleccionSSCC() {
		return poseeBolsaSeleccionSSCC;
	}

	public void setPoseeBolsaSeleccionSSCC(String poseeBolsaSeleccionSSCC) {
		this.poseeBolsaSeleccionSSCC = poseeBolsaSeleccionSSCC;
	}

	public String getMostrarCondiciones() {
		return mostrarCondiciones;
	}

	public void setMostrarCondiciones(String mostrarCondiciones) {
		this.mostrarCondiciones = mostrarCondiciones;
	}

}
