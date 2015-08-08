/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Bean de resumen de facturaci&oacute;n de un cliente de Entel
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ResumenFacturacionBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Double totalPago;
    
    private Date fechaVencimiento;
    
    private Date fechaEmision;
    
    private Date fechaPeriodo;
    
    private String urlImagenFactura;

	private List<String> meses;

    private List<String> facturacionMeses;
    
    private String fechaEmisionFormated;
    
    private String fechaVencimientoFormated;
    
    private String totalPagoFormated;
    
    private String fechaPeriodoFormated;
    
    private String numIntervalosGrafico;
    
    private String folio;
    
    private String tipoDocumento;
    
    private String estadoDocumento;
    
    private String mesesString;
    
    private String montosString;    
    
    
    /**
     * @return the totalPago
     */
    public Double getTotalPago() {
        return totalPago;
    }

    /**
     * @param totalPago
     *            the totalPago to set
     */
    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }
    
    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento
     *            the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    
    /**
     * @return the fechaEmision
     */
    public Date getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision
     *            the fechaEmision to set
     */
    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    
    /**
     * @return the fechaPeriodo
     */
    public Date getFechaPeriodo() {
        return fechaPeriodo;
    }

    /**
     * @param fechaPeriodo
     *            the fechaPeriodo to set
     */
    public void setFechaPeriodo(Date fechaPeriodo) {
        this.fechaPeriodo = fechaPeriodo;
    }
    
    /**
     * 
     * @return
     */
 	public String getUrlImagenFactura() {
 		return urlImagenFactura;
 	}
 	
    
    /**
	 * 
	 * @param urlImagenFactura
	 */
	public void setUrlImagenFactura(String urlImagenFactura) {
		this.urlImagenFactura = urlImagenFactura;
	}
    
	
	/**
     * @return the meses
     */
    public List<String> getMeses() {
        return meses;
    }

    /**
     * @param meses
     *            the meses to set
     */
    public void setMeses(List<String> meses) {
        this.meses = meses;
    }

    /**
     * @return the facturacionMeses
     */
    public List<String> getFacturacionMeses() {
        return facturacionMeses;
    }

    /**
     * @param facturacionMeses
     *            the facturacionMeses to set
     */
    public void setFacturacionMeses(List<String> facturacionMeses) {
        this.facturacionMeses = facturacionMeses;
    }
	

    /**
     * 
     * @return
     */
    public String getFechaEmisionFormated() {
		return fechaEmisionFormated;
	}
    
    /**
     * @param fechaEmisionF the fechaEmisionF to set
     */
    public void setFechaEmisionFormated(String fechaEmisionFormated) {
        this.fechaEmisionFormated = fechaEmisionFormated;
    }

    /**
     * 
     * @return
     */
	public String getFechaVencimientoFormated() {
		return fechaVencimientoFormated;
	}
	
	
	/**
     * @param fechaVencimientoF the fechaVencimientoF to set
     */
    public void setFechaVencimientoFormated(String fechaVencimientoFormated) {
        this.fechaVencimientoFormated = fechaVencimientoFormated;
    }
    
    /**
     * @return the totalPagoF
     */
    public String getTotalPagoFormated() {
        return totalPagoFormated;
    }
    
    
    /**
     * @param totalPagoF the totalPagoF to set
     */
    public void setTotalPagoFormated(String totalPagoFormated) {
        this.totalPagoFormated = totalPagoFormated;
    }
	
	/**
	 * 
	 * @return
	 */
    public String getFechaPeriodoFormated() {
		return fechaPeriodoFormated;
	}
    
    
    /**
     * @param fechaPeriodoF the fechaPeriodoF to set
     */
    public void setFechaPeriodoFormated(String fechaPeriodoFormated) {
        this.fechaPeriodoFormated = fechaPeriodoFormated;
    }
    
    

    /**
     * 
     * @return
     */
    public String getMesesString() {
        StringBuffer buffer = new StringBuffer();

        if(meses == null || meses.isEmpty()) {
            return "";
        }
        
        for(String item : meses) {
            if (buffer.length() > 0) {
                buffer.append(",");
            }
            buffer.append("'" + item.substring(0, 3) + "'");
        }
        
        return buffer.toString();
    }
    
    public String getFacturacionMesesString() {

        StringBuffer buffer = new StringBuffer();

        if(facturacionMeses == null || facturacionMeses.isEmpty()) {
            return "";
        }
        
        for(String item : facturacionMeses) {
            if (buffer.length() == 0) {
                buffer.append(item);
            } else {
                buffer.append(",");
                buffer.append(item);
            }
        }
        
        return buffer.toString();
    }

  
//    private StringBuffer listToStringBuffer(List<String> list) {
//
//        StringBuffer buffer = new StringBuffer();
//
//        if(list == null || list.isEmpty()) {
//            return buffer;
//        }
//        
//        for(String item : list) {
//            if (buffer.length() == 0) {
//                buffer.append("'" + item + "'");
//            } else {
//                buffer.append(",");
//                buffer.append(item);
//            }
//        }
//        
//        return buffer;
//        
//    }
    
    /**
     * @return the numIntervalosGrafico
     */
    public String getNumIntervalosGrafico() {
        return numIntervalosGrafico;
    }
    
    /**
     * @param numIntervalosGrafico the numIntervalosGrafico to set
     */
    public void setNumIntervalosGrafico(String numIntervalosGrafico) {
        this.numIntervalosGrafico = numIntervalosGrafico;
    }

    /**
     * @return the folio
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return the tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

	public String getEstadoDocumento() {
		return estadoDocumento;
	}

	public void setEstadoDocumento(String estadoDocumento) {
		this.estadoDocumento = estadoDocumento;
	}

	/**
	 * @return the montosString
	 */
	public String getMontosString() {
		return montosString;
	}

	/**
	 * @param montosString the montosString to set
	 */
	public void setMontosString(String montosString) {
		this.montosString = montosString;
	}

	/**
	 * @param mesesString the mesesString to set
	 */
	public void setMesesString(String mesesString) {
		this.mesesString = mesesString;
	}
    
	
	
    
}