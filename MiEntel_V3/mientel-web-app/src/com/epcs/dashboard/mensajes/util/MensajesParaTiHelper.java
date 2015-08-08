/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.util;

import com.bea.content.Node;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.error.ServiceMessages;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class MensajesParaTiHelper {

    /**
     * ID de Servicios VOZ
     */
    private static final String ID_ESTADO_CUENTA = MiEntelProperties
            .getProperty("mensajesParaTi.servicios.estadoCuenta.id");
    private static final String ID_FACTURA_ELECTRONICA = MiEntelProperties
            .getProperty("mensajesParaTi.servicios.facturacionElectronica.id");
    private static final String ID_BOLSAS_PLANES = MiEntelProperties
            .getProperty("mensajesParaTi.servicios.bolsasPlanesComplementarios.id");
    private static final String ID_SERVICIO_TECNICO = MiEntelProperties
            .getProperty("mensajesParaTi.servicios.servicioTecnico.id");
    private static final String ID_EQUIPOS_ARRIENDO = MiEntelProperties
            .getProperty("mensajesParaTi.servicios.equiposArriendo.id");
    private static final String ID_INTERNET_MOVIL = MiEntelProperties
            .getProperty("mensajesParaTi.servicios.internetMovilIlimitado.id");
    private static final String ID_DETALLE_LLAMADOS = MiEntelProperties
            .getProperty("mensajesParaTi.servicios.detalleLlamados.id");
    private static final String ID_BLOQUEO_ROBO_EXTRAVIO = MiEntelProperties
            .getProperty("mensajesParaTi.servicios.bloqueoRoboExtravio.id");
    private static final String ID_BIENVENIDA = MiEntelProperties
    		.getProperty("mensajesParaTi.servicios.bienvenida.id");
    private static final String ID_FACTURACION_EMPRESAS = MiEntelProperties
    		.getProperty("mensajesParaTi.servicios.facturacionEmpresas.id");
    private static final String ID_RECARGA_WEBPAY = MiEntelProperties
			.getProperty("mensajesParaTi.servicios.bienvenida.id");
	private static final String ID_MIGRACION_PREPAGO_PLAN = MiEntelProperties
			.getProperty("mensajesParaTi.servicios.facturacionEmpresas.id");
	private static final String ID_PAGO_AUTOMATICO = MiEntelProperties
			.getProperty("mensajesParaTi.servicios.pagoAutomatico.id");    
	private static final String ID_TARIFA_DATOSEXCEDIDOS = MiEntelProperties
			.getProperty("mensajesParaTi.servicios.traficoDatosExcedidos.id");   
    /**
     * idContenido mensajes de servicios VOZ
     */
    // Estado de Cuenta
    private static final String MENS_CUENTA_PAGADA = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.estadoCuenta.pagada");
    private static final String MENS_CUENTA_VENCIDA = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.estadoCuenta.vencida");
    private static final String MENS_CUENTA_EMITIDA = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.estadoCuenta.emitida");
    // Factura Electronica
    private static final String MENS_FACTURA_NO_INSCRITA = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.facturacionElectronica.noInscrita");
    // Bolsas y Planes Complementarios
    private static final String MENS_OPTIMIZAR_BOLSA = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.bolsasPlanesComplementarios.optimizar");
    // Bloqueo por Robo / Extravio
    private static final String MENS_MOVIL_BLOQUEADO = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.bloqueoRoboExtravio.bloqueado");
    // Internet Movil Ilimitado
    private static final String MENS_INTERNET_MOVIL_ACTIVO = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.internetMovilIlimitado.activo");
    private static final String MENS_INTERNET_MOVIL_NO_ACTIVO = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.internetMovilIlimitado.noActivo");
    // Equipos en Arriendo
    private static final String MENS_CON_EQUIPO_ARRIENDO = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.equiposArriendo.conArriendo");
    private static final String MENS_SIN_EQUIPO_ARRIENDO = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.equiposArriendo.sinArriendo");
    // Servicio Tecnico
    private static final String MENS_EQUIPO_REPARADO = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.servicioTecnico.reparado");
    private static final String MENS_EQUIPO_NO_REPARADO = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.servicioTecnico.noReparado");
    private static final String MENS_PRESUPUESTO_NO_PUBLICADO = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.servicioTecnico.presupuestoNoPublicado");
    // Detalle de Llamados
    private static final String MENS_DETALLE_DISPONIBLE = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.detalleLlamados.detalleDisponible");
    // Bienvenida Mientel v3
    private static final String MENS_BIENVENIDA = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.bienvenida.msjInfo");
    // Cambios Facturacion Empresas
    private static final String MENS_FACT_EMPRESAS = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.facturacionEmpresas.msjInfo");
    // Bienvenida Mientel v3
    private static final String MENS_RECARGA_WEBPAY = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.recargaWebPay.msjInfo");
    // Cambios Facturacion Empresas
    private static final String MENS_MIGRACION_PREPAGO_PLAN = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.migracionPrepagoPlan.msjInfo");
    
    
    // Bloqueo Hurto
    private static final String MENS_MOVIL_BLOQUEADO_HURTO = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.bloqueoRoboExtravio.hurto");    
    // Bloqueo por  Extravio
    private static final String MENS_MOVIL_BLOQUEADO_EXTRAVIO = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.bloqueoRoboExtravio.extravio");    
    
    // Bloqueo por robo
    private static final String MENS_MOVIL_BLOQUEADO_ROBO = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.bloqueoRoboExtravio.robo");      
    /**
     * Codigos de Estado Servicio Tecnico
     */
    private static final String ESTADO_EQUIPO_RECEPCIONADO = MiEntelProperties
            .getProperty("mensajesParaTi.servicios.servicioTecnico.estados.equipoRecepcionado");
    private static final String ESTADO_PRESUPUESTO_PENDIENTE = MiEntelProperties
            .getProperty("mensajesParaTi.servicios.servicioTecnico.estados.presupuestoPendiente");
    
    //Comunicacion tarifa diaria.
    private static final String MENS_COMUNICACION_TARIFA_DIARIA = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.tarifaDiaria.msjInfo");
    
    //Pago Automatico Tarjeta (PAT)
	private static final String MENS_PAGO_AUTOMATICO = MiEntelProperties
			.getProperty("mensajesParaTi.mensajes.pagoAutomatico.noActivo");    
	//Tasacion Datos Excedidos
	private static final String MENS_DATOS_EXCEDIDOS = MiEntelProperties
			.getProperty("mensajesParaTi.servicios.TDE.idContenido");    
    // Mensaje por defecto
    private static final String NO_HAY_MENSAJES = MiEntelProperties
            .getProperty("mensajesParaTi.mensajes.noHayMensajes");    
    
    // Mensaje alto valor.
    private static final String MENS_PP_ALTO_VALOR = MiEntelProperties
            .getProperty("mensajesParaTi.servicios.altoValor.msjInfo");

    /**
     * @return the idEstadoCuenta
     */
    public static String getIdEstadoCuenta() {
        return ID_ESTADO_CUENTA;
    }

    /**
     * @return the idFacturaElectronica
     */
    public static String getIdFacturaElectronica() {
        return ID_FACTURA_ELECTRONICA;
    }

    /**
     * @return the idBolsasPlanes
     */
    public static String getIdBolsasPlanes() {
        return ID_BOLSAS_PLANES;
    }

    /**
     * @return the idServicioTecnico
     */
    public static String getIdServicioTecnico() {
        return ID_SERVICIO_TECNICO;
    }

    /**
     * @return the idEquiposArriendo
     */
    public static String getIdEquiposArriendo() {
        return ID_EQUIPOS_ARRIENDO;
    }

    /**
     * @return the idInternetMovil
     */
    public static String getIdInternetMovil() {
        return ID_INTERNET_MOVIL;
    }

    /**
     * @return the idDetalleLlamados
     */
    public static String getIdDetalleLlamados() {
        return ID_DETALLE_LLAMADOS;
    }

    /**
     * @return the idBloqueoRoboExtravio
     */
    public static String getIdBloqueoRoboExtravio() {
        return ID_BLOQUEO_ROBO_EXTRAVIO;
    }

    /**
	 * @return the idBienvenida
	 */
	public static String getIdBienvenida() {
		return ID_BIENVENIDA;
	}

	/**
	 * @return the idFacturacionEmpresas
	 */
	public static String getIdFacturacionEmpresas() {
		return ID_FACTURACION_EMPRESAS;
	}

	/**
	 * @return the idRecargaWebpay
	 */
	public static String getIdRecargaWebpay() {
		return ID_RECARGA_WEBPAY;
	}

	/**
	 * @return the idMigracionPrepagoPlan
	 */
	public static String getIdMigracionPrepagoPlan() {
		return ID_MIGRACION_PREPAGO_PLAN;
	}
	
	/**
	 * @return the idPagoAutomatico
	 */	
	public static String getIdPagoAutomatico() {
		return ID_PAGO_AUTOMATICO;
	}

	/**
	 * @return the idPagoAutomatico
	 */	
	public static String getIdTarifaDatosExcedidos() {
		return ID_TARIFA_DATOSEXCEDIDOS;
	}

	/**
     * @return the mensCuentaPagada
     */
    public static String getMensCuentaPagada() {
        return MENS_CUENTA_PAGADA;
    }
    
    /**
     * @return the mensCuentaVencida
     */
    public static String getMensCuentaVencida() {
        return MENS_CUENTA_VENCIDA;
    }

    /**
     * @return the mensCuentaEmitida
     */
    public static String getMensCuentaEmitida() {
        return MENS_CUENTA_EMITIDA;
    }

    /**
     * @return the mensFacturaNoInscrita
     */
    public static String getMensFacturaNoInscrita() {
        return MENS_FACTURA_NO_INSCRITA;
    }

    /**
     * @return the mensOptimizarBolsa
     */
    public static String getMensOptimizarBolsa() {
        return MENS_OPTIMIZAR_BOLSA;
    }

    /**
     * @return the mensMovilBloqueado
     */
    public static String getMensMovilBloqueado() {
        return MENS_MOVIL_BLOQUEADO;
    }

    /**
     * @return the mensInternetMovilActivo
     */
    public static String getMensInternetMovilActivo() {
        return MENS_INTERNET_MOVIL_ACTIVO;
    }

    /**
     * @return the mensInternetMovilNoActivo
     */
    public static String getMensInternetMovilNoActivo() {
        return MENS_INTERNET_MOVIL_NO_ACTIVO;
    }

    /**
     * @return the mensConEquipoArriendo
     */
    public static String getMensConEquipoArriendo() {
        return MENS_CON_EQUIPO_ARRIENDO;
    }

    /**
     * @return the mensSinEquipoArriendo
     */
    public static String getMensSinEquipoArriendo() {
        return MENS_SIN_EQUIPO_ARRIENDO;
    }

    /**
     * @return the mensEquipoReparado
     */
    public static String getMensEquipoReparado() {
        return MENS_EQUIPO_REPARADO;
    }

    /**
     * @return the mensEquipoNoReparado
     */
    public static String getMensEquipoNoReparado() {
        return MENS_EQUIPO_NO_REPARADO;
    }

    /**
     * @return the mensPresupuestoNoPublicado
     */
    public static String getMensPresupuestoNoPublicado() {
        return MENS_PRESUPUESTO_NO_PUBLICADO;
    }

    /**
     * @return the estadoEquipoRecepcionado
     */
    public static String getEstadoEquipoRecepcionado() {
        return ESTADO_EQUIPO_RECEPCIONADO;
    }

    /**
     * @return the estadoPresupuestoPendiente
     */
    public static String getEstadoPresupuestoPendiente() {
        return ESTADO_PRESUPUESTO_PENDIENTE;
    }

    /**
     * @return the mensDetalleNoRevisado
     */
    public static String getMensDetalleDisponible() {
        return MENS_DETALLE_DISPONIBLE;
    }
    
    /**
	 * @return the mensBienvenida
	 */
	public static String getMensBienvenida() {
		return MENS_BIENVENIDA;
	}

	/**
	 * @return the mensFactEmpresas
	 */
	public static String getMensFactEmpresas() {
		return MENS_FACT_EMPRESAS;
	}

	/**
	 * @return the mensRecargaWebpay
	 */
	public static String getMensRecargaWebpay() {
		return MENS_RECARGA_WEBPAY;
	}

	/**
	 * @return the mensMigracionPrepagoPlan
	 */
	public static String getMensMigracionPrepagoPlan() {
		return MENS_MIGRACION_PREPAGO_PLAN;
	}
	
	
    /**
     * @return the mensMovilBloqueado
     */
    public static String getMensMovilBloqueadRobo() {
        return MENS_MOVIL_BLOQUEADO_ROBO;
    }
    
    /**
     * @return the mensMovilBloqueado
     */
    public static String getMensMovilBloqueadoExtravio() {
        return MENS_MOVIL_BLOQUEADO_EXTRAVIO;
    }
    
    /**
     * @return the mensMovilBloqueado
     */
    public static String getMensMovilBloqueadoHurto() {
        return MENS_MOVIL_BLOQUEADO_HURTO;
    }

	/**
     * @return the noHayMensajes
     */
    public static String getNoHayMensajes() {
        return NO_HAY_MENSAJES;
    }

    /**
	 * @return the mensComunicacionTarifaDiaria
	 */
	public static String getMensComunicacionTarifaDiaria() {
		return MENS_COMUNICACION_TARIFA_DIARIA;
	}
    /**
	 * @return the mensComunicacionTarifaDiaria
	 */	
	public static String getMensPagoAutomatico() {
		return MENS_PAGO_AUTOMATICO;
	}	
	
    /**
	 * @return the mensDatosExcedidos
	 */		
	public static String getMensDatosExcedidos() {		
		return MENS_DATOS_EXCEDIDOS;
	}
	
	 /**
	 * @return the mensAltoValor
	 */		
	public static String getMensPPAltoValor() {		
		return MENS_PP_ALTO_VALOR;
	}
	
	
	/**
     * Obtiene un nodo de contenido de acuerdo al valor de su propiedad
     * idContenido
     * @param value
     * @return
     * @throws Exception
     */
    public static Node obtenerMensajeCM(String value) throws Exception {
        return JSFPortletHelper.getContentNode("idContenido", value);        
    }
    
    /**
     * Obtiene un nodo hijo de un nodo de mensajes de servicio
     * @param mensajesServicio
     * @param value
     * @return
     * @throws Exception
     */
    public static Node obtenerMensajeParaTi(Node mensajesServicio, String value) throws Exception {
        return JSFPortletHelper.getContentChildNode(mensajesServicio, "idContenido", value);
    }

    /**
     * Devuelve el mensaje de error (Tipo BAD) para un servicio
     * 
     * @param servicio
     * @return
     */
    public static String obtenerMensajeTipoBad(String servicio) {
        ServiceMessages mensajes = MiEntelProperties.getServiceMessages();
        return mensajes.getErrorMessage("mensajesParaTi", servicio);
    }    
    
    /**
     * Devuelve el idContenido asociado a un servicio de mensajes para ti, teniendo
     * en cuenta el mercado y el producto (VOZ o BAM)
     * @param idServicio
     * @param flagBAM
     * @param mercado
     * @return
     */
    public static String obtenerIdContenidoMensaje(String idServicio, String flagBAM, String mercado) {
        String nombreIdServicio = MiEntelProperties.getProperty("mensajesParaTi.servicios." + idServicio + ".idContenido");
        String producto = flagBAM.equals("1") ? "bam" : "voz";
        return nombreIdServicio + "_" + mercado + "_" + producto;
    }

}
