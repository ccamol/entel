/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import com.epcs.cliente.perfil.types.PlanActualPPType;
import com.epcs.cliente.perfil.types.ResPlanResumenCCType;
import com.epcs.cliente.perfil.types.ResPlanResumenPPType;
import com.esa.billing.billingcons.t.conssaldobonoextra.types.ConsultarSaldoResponseType;
import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.vo.menu.cc.CuentaControladaVO;
import com.esa.ponline.appmobile.vo.menu.prepaid.PrepaidVO;
import com.esa.ponline.appmobile.web.delegate.DelegateMenu;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class MenuAction extends ActionSupport {

    private static final long serialVersionUID = 6097669556004957102L;

    private static final Logger LOGGER = Logger.getLogger(MenuAction.class);

    private DelegateMenu delegate = new DelegateMenu();

    private LoginVO datosClientes;

    private PlanActualPPType planActualPP;

    private PrepaidVO prepago;

    private ArrayList<String> glosaDescPlan;

    private CuentaControladaVO cc;

    private String restriccionZona;
    
    public String execute() {
        try {
            datosClientes = (LoginVO) getSession().get("cliente");
            if (datosClientes == null) {
                datosClientes = (LoginVO) getSession().get("cliente_registro");
            }

            // forza error
            // String cdf = null;
            // cdf.length();
            //Config.cacheRestriccionZona.put(datosClientes.getMsisdn(), true);

            if (Config.cacheRestriccionZona.get(datosClientes.getMsisdn())){
        	//Config.cacheRestriccionZona.put(datosClientes.getMsisdn(), false);
        	restriccionZona="restringido";
            }

            if (datosClientes.getMercado().equalsIgnoreCase("pp")) {
                obtieneSaldoPP(datosClientes.getMsisdn());
                return datosClientes.getResultNav();
            } else {
                obtieneSaldoCC(datosClientes.getMsisdn());
                return datosClientes.getResultNav();
            }
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un problema en MenuAction "
                    + e.getMessage());
            e.printStackTrace();
            return "error_general";
        }
    }

    public void obtieneSaldoPP(String msisdn) {
        prepago = new PrepaidVO();
        detalleResumenPP(msisdn);
    }

    public void obtieneSaldoCC(String msisdn) {
        cc = new CuentaControladaVO();
        detalleResumenCC(msisdn);
    }

    public String consultaPlanPrepago(String msisdn) {
        prepago = new PrepaidVO();
        detalleResumenPP(msisdn);
        consultaPlanActualPP(msisdn);

        return "planActualPP";
    }

    protected void detalleResumenCC(String msisdn) {
        ResPlanResumenCCType detalleCC = new ResPlanResumenCCType();
        try {
            detalleCC = delegate.consultarPlanResumenCC(msisdn);
        } catch (Exception e) {
            LOGGER.error("Problemas al consultar resumen para mercado cc asociada al msisdn ["
                    + msisdn + "] " + e.getMessage());
            e.printStackTrace();
        }

        if (detalleCC != null) {
            cc.setNombrePlan(detalleCC.getNombrePlan());
            cc.setSaldo(Config.getAppProperty(Constants.DOLLAR_SIGN
                    .getStringValue()) + detalleCC.getSaldo());
            cc.setFechaExpiracion(detalleCC.getFechaExpiracion());
        } else {
            cc.setNombrePlan("");
            cc.setSaldo("");
            cc.setFechaExpiracion("");
        }
    }

    protected void detalleResumenPP(String msisdn) {
        List<ResPlanResumenPPType> detalleResumenPP =
                new ArrayList<ResPlanResumenPPType>();
        List<PlanActualPPType> detallePlanActualPP =
                new ArrayList<PlanActualPPType>();
        
        //TODO CC3 saldo secundario
        ConsultarSaldoResponseType consultaSaldoSecundario = 
                new ConsultarSaldoResponseType();
        
        String descripcionPlan = null;

        try {
            detalleResumenPP = delegate.consultarPlanResumenPP(msisdn);
            detallePlanActualPP = delegate.consultarPlanActualPP(msisdn);
            
            //TODO CC3 saldoSecundario
//            consultaSaldoSecundario = delegate.consultarSaldoSecundario(msisdn);
        } catch (Exception e) {
            LOGGER.error("Problemas al consultar resumen para mercado pp asociada al msisdn ["
                    + msisdn + "] " + e.getMessage());
            e.printStackTrace();
        }
        if (detallePlanActualPP != null
                && detallePlanActualPP.iterator().hasNext()) {
            descripcionPlan = detallePlanActualPP.get(0).getDescripcionPlan();
        } else {
            descripcionPlan = "";
        }
        if (detalleResumenPP != null && detalleResumenPP.iterator().hasNext()) {
            for (int i = 0; i < detalleResumenPP.size(); i++) {
                prepago.setFechaExpiracion(detalleResumenPP.get(i)
                        .getFechaExpiracion());
                prepago.setNombrePlan(detalleResumenPP.get(i).getNombrePlan());
                prepago.setFechaExpSaldoReservado(detalleResumenPP.get(i)
                        .getFechaExpiracionSaldoReservado());
                prepago.setSaldo(Config.getAppProperty(Constants.DOLLAR_SIGN
                        .getStringValue()) + detalleResumenPP.get(i).getSaldo());
                prepago.setSaldoReservado(Config
                        .getAppProperty(Constants.DOLLAR_SIGN.getStringValue())
                        + detalleResumenPP.get(i).getSaldoReservado());
                prepago.setDescripcionPlan(construirGlosaPlan(descripcionPlan));
                
//                //TODO CC3 saldoSecundario
//                // BigDecimal
//                prepago.setSaldoSecundario(Config
//                        .getAppProperty(Constants.DOLLAR_SIGN.getStringValue()) +
//                        String.valueOf(consultaSaldoSecundario.getSaldosecundario().intValue()));
//                // XMLGregorianCalendar
//                prepago.setVigenciaSecundario(consultaSaldoSecundario.getVigenciasecundario().toXMLFormat());
                
                setGlosaDescPlan(construirGlosaPlan(descripcionPlan));
            }
        }
    }

    protected void consultaPlanActualPP(String msisdn) {
        List<PlanActualPPType> listadoPlanActualPP =
                new ArrayList<PlanActualPPType>();

        try {
            listadoPlanActualPP = delegate.consultarPlanActualPP(msisdn);
        } catch (Exception e) {
            LOGGER.error("Problemas al consultar plan actual para mercado pp asociada al msisdn ["
                    + msisdn + "] " + e.getMessage());
            e.printStackTrace();
        }

        if (listadoPlanActualPP != null
                && listadoPlanActualPP.iterator().hasNext()) {
            for (int i = 0; i < listadoPlanActualPP.size(); i++) {

                prepago.setDescripcionPlan(construirGlosaPlan(listadoPlanActualPP
                        .get(i).getDescripcionPlan()));
                setGlosaDescPlan(construirGlosaPlan(listadoPlanActualPP.get(i)
                        .getDescripcionPlan()));
                prepago.setNombrePlan(listadoPlanActualPP.get(i)
                        .getNombrePlan());
                prepago.setTipoPlan((listadoPlanActualPP.get(i).getTipoPlan()
                        .equalsIgnoreCase("pp") ? "Prepago"
                        : listadoPlanActualPP.get(i).getTipoPlan()));
            }
        }
    }

    /**
     * Metodo que recibe la descripcion del plan actual del usuario para
     * construir de forma correcta la visualizacion de la glosa.
     * 
     * @param descripcionPlan
     * @return
     */
    public static ArrayList<String> construirGlosaPlan(String descripcionPlan) {

        ArrayList<String> list = new ArrayList<String>();

        Pattern pattern = Pattern.compile("\\.\\s[A-Z]");
        Matcher matcher = pattern.matcher(descripcionPlan);

        boolean found = false;
        int ini = 0;
        int fin = 0;
        while (matcher.find()) {
            fin = matcher.start();
            list.add(descripcionPlan.substring(ini, fin+1));
            ini = matcher.end() - 1;
            found = true;
        }

        // if(found){
        // list.add(descripcionPlan.substring(ini, descripcionPlan.length() - 1
        // ));
        // }

        if (found) {
            list.add(descripcionPlan.substring(ini, descripcionPlan.length()));
        }

        if (!found) {
            list.add("-");
        }

        return list;
    }

    public Map<String, Object> getSession() {
	HttpServletRequest req = ServletActionContext.getRequest();
    	SessionMap<String, Object> attributes = new SessionMap<String, Object>(req);
	return attributes;
    }

    public LoginVO getDatosClientes() {
        return datosClientes;
    }

    public void setDatosClientes(LoginVO datosClientes) {
        this.datosClientes = datosClientes;
    }

    public PlanActualPPType getPlanActualPP() {
        return planActualPP;
    }

    public void setPlanActualPP(PlanActualPPType planActualPP) {
        this.planActualPP = planActualPP;
    }

    public PrepaidVO getPrepago() {
        return prepago;
    }

    public void setPrepago(PrepaidVO prepago) {
        this.prepago = prepago;
    }

    public CuentaControladaVO getCc() {
        return cc;
    }

    public void setCc(CuentaControladaVO cc) {
        this.cc = cc;
    }

    public ArrayList<String> getGlosaDescPlan() {
        return glosaDescPlan;
    }

    public void setGlosaDescPlan(ArrayList<String> arrayList) {
        this.glosaDescPlan = arrayList;
    }

    public String getRestriccionZona() {
        return restriccionZona;
    }

    public void setRestriccionZona(String restriccionZona) {
        this.restriccionZona = restriccionZona;
    }
    
    
}
