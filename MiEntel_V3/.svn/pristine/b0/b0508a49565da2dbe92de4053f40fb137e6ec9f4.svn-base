/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.administracion.suscripciones.util;

import com.bea.content.Node;
import com.epcs.bean.BolsaDisponibleBAMCCPP;
import com.epcs.bean.PlanBAMBean;
import com.epcs.bean.ResumenBolsasActivasBAMCCPP;
import com.epcs.bean.ResumenBolsasDisponiblesBAMCCPP;
import com.epcs.bean.ResumenTraficoBAMCCBean;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class BolsasBAMCCPPHelper {
    
    private static final int GRUPO_A = Integer.parseInt(MiEntelProperties.getProperty("bolsasBAMCCPP.grupoA"));
    private static final int GRUPO_B = Integer.parseInt(MiEntelProperties.getProperty("bolsasBAMCCPP.grupoB"));
    private static final int GRUPO_C = Integer.parseInt(MiEntelProperties.getProperty("bolsasBAMCCPP.grupoC"));
       
   
    /**
     * Verifica si se puede realizar la compra de una bolsa por las dos modalidades
     * disponibles (SALDO, FACTURA)
     * 
     * @param aaa
     * @param planActual
     * @param traficoBAM
     * @param bolsasDisponibles
     * @param bolsasActivas
     */    
    public static void verificaTipoCompra(String aaa,
            ResumenBolsasDisponiblesBAMCCPP bolsasDisponibles,
            ResumenBolsasActivasBAMCCPP bolsasActivas, PlanBAMBean planActual,
            ResumenTraficoBAMCCBean traficoBAM) {
        
        boolean bolsaActiva = bolsasActivas.getBolsas().size() > 0
                && !bolsasActivas.getBolsas().get(0).isConsumida();
        boolean cuotaTraficoValida = validarCuotaConsumo(planActual
                .getUmbralFairUseMb(), traficoBAM.getSaldoNavegacion());
        double saldoRecarga = Double.parseDouble(bolsasDisponibles.getSaldoPlan());
        int grupoMovil = bolsasDisponibles.getGrupo();
        final double montoMaxBolsaGrupoMovil = obtenerMontoGrupo(grupoMovil); 
        double creditoDisponible = bolsasDisponibles.getLimiteCreditoTotal() - bolsasDisponibles.getLimiteCreditoAcumulado();   
        
        for (BolsaDisponibleBAMCCPP bolsa : bolsasDisponibles.getBolsas()) {
            
            if (bolsaActiva) { // Validacion bolsa activa
                desactivarBolsa(bolsa, "mensajeBolsaActiva");
            } else if (!cuotaTraficoValida) { // Validacion cuota de trafico
                desactivarBolsa(bolsa, "mensajeCuotaConsumo");
            } else if (bolsa.isFlagHabilitada()) { // Validaciones individuales contra Saldo y Factura                
                
                /**
                 * Validacion Compra Contra Saldo
                 */
                // Precio de Bolsa                
                if (saldoRecarga < bolsa.getPrecio()) {
                    desactivarBolsaSaldo(bolsa, "mensajeSaldoInsuficiente");
                }
                
                /**
                 * Validacion Compra Contra Factura
                 */                
                // Validacion de AAA
                if (MiEntelBusinessHelper.isAAAControlParcial(aaa) && MiEntelBusinessHelper.isAAAConsulta(aaa)) {
                    desactivarBolsaFactura(bolsa, "mensajeAAAInvalido");
                } else if (creditoDisponible >= 0) {
                    // Valida el monto maximo de la bolsa a comprar por grupo
                    if (montoMaxBolsaGrupoMovil < bolsa.getPrecio()) {
                        desactivarBolsaFactura(bolsa, "mensajeMontoMaxBolsaGrupoExcedido");
                    }                    
                } else {
                    desactivarBolsaFactura(bolsa, "mensajeCreditoExcedido");
                }
                
                /**
                 * Valida el caso que el usuario no cumpla con las condiciones para comprar 
                 * bolsa contra Saldo o Factura
                 */        
                if (!bolsa.isFlagHabilitada()) {
                    if (cuotaTraficoValida) {
                        desactivarBolsaSaldo(bolsa, "mensajeSaldoInsuficienteDesactivarBolsa");
                    } else {
                        desactivarBolsa(bolsa, "mensajeNoCumpleCondicionesSaldoFactura");
                    }
                }
            }                
        }   
    }    
    
    /**
     * Obtiene el monto maximo permitido para un grupo de movil determinado
     * 
     * @param grupoMovil
     * @return Monto maximo, en caso de no ser un grupo con restriccion de monto
     *         se devuelve 0
     */
    private static double obtenerMontoGrupo(int grupoMovil) {
        double montoGrupo = 0.0;
        try {
            if (grupoMovil == GRUPO_A || grupoMovil == GRUPO_B || grupoMovil == GRUPO_C) {
                montoGrupo = Double.parseDouble(MiEntelProperties.getProperty("bolsasBAMCCPP.grupo." + grupoMovil));
            } else {
                montoGrupo = Double.parseDouble(MiEntelProperties.getProperty("bolsasBAMCCPP.grupo.grupoSinRiesgo"));
            }            
        } catch (Exception e) {
            return 0.0;
        }
        return montoGrupo;
    }    
    
    /**
     * Obtiene el nodo del Content Management asociado a un mensaje descriptivo
     * de la causa para desactivar una bolsa
     * @param value
     * @return
     */
    private static String obtenerMensaje(String value) {
        try {
            Node n = JSFPortletHelper.getContentNode("idContenido", value);
            return n.getProperty("html").getValue().getStringValue();
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Verifica si se tiene la cuota de trafico necesaria para comprar una bolsa
     * @param umbralFairUseMB
     * @param cuotaUtilizada
     * @return
     */
    public static boolean validarCuotaConsumo(String umbralFairUseMB,
            String cuotaUtilizada) {
        int porcentajeMinimo = 5;
        int porcentajeCuota = 0;

        if (umbralFairUseMB != null && !umbralFairUseMB.equals("")) {
            porcentajeCuota = obtenerPorcentajeCuota(new Integer(
                    umbralFairUseMB), new Integer(cuotaUtilizada));
        }
        
        return porcentajeCuota <= porcentajeMinimo;
    }

    /**
     * Obtiene el porcentaje utilizado de cuota de trafico
     * @param umbralFairUse
     * @param cuotaUtilizada
     * @return
     */
    private static int obtenerPorcentajeCuota(int umbralFairUse,
            int cuotaUtilizada) {
        return (cuotaUtilizada * 100) / umbralFairUse;
    }
    
    /**
     * Deshabilita una bolsa para Compra por Saldo
     * @param bolsa
     * @param motivo
     */
    private static void desactivarBolsaSaldo(BolsaDisponibleBAMCCPP bolsa, String motivo) {
        bolsa.setFlagCargaSaldo(false);
        bolsa.setDescripcionCargaSaldo(BolsasBAMCCPPHelper.obtenerMensaje(motivo));
    }
    
    /**
     * Deshabilita una bolsa para Compra por Factura
     * @param bolsa
     * @param motivo
     */    
    private static void desactivarBolsaFactura(BolsaDisponibleBAMCCPP bolsa, String motivo) {
        bolsa.setFlagCargaFactura(false);
        bolsa.setDescripcionCargaFactura(BolsasBAMCCPPHelper.obtenerMensaje(motivo));
    }    
    
    /**
     * Desactiva una bolsa para todas las opciones de compra (SALDO, FACTURA)
     * @param bolsa
     * @param motivo
     */
    private static void desactivarBolsa(BolsaDisponibleBAMCCPP bolsa, String motivo) {
        bolsa.setFlagCargaSaldo(false);
        bolsa.setDescripcionCargaSaldo(BolsasBAMCCPPHelper.obtenerMensaje(motivo));
        bolsa.setFlagCargaFactura(false);
        bolsa.setDescripcionCargaFactura(BolsasBAMCCPPHelper.obtenerMensaje(motivo));
    }
}
