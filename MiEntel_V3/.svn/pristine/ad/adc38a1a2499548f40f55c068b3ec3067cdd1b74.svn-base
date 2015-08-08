/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.jsf.utils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import com.epcs.bean.CodeDescBean;
import com.epcs.bean.MontoRecargaBean;
import com.epcs.bean.MsisdnAsociadoBean;
import com.epcs.bean.MultitiendaBean;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.Utils;

/**
 * Metodos de Utilidad para el entorno JSF 
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class JsfUtil {

    /**
     * Obtener un parametro del request
     * @param key
     * @return
     */
    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    /**
     * Obtener un objeto del request aplicando un converter
     * @param requestParameterName
     * @param converter
     * @param component
     * @return
     */
    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    /**
     * Transforma a Strig un objeto aplicando un converter
     * 
     * @param object
     * @param converterId
     * @return
     */
    public static String getAsConvertedString(Object object, String converterId) {

        FacesContext fc = FacesContext.getCurrentInstance();
        Converter converter = fc.getApplication().createConverter(converterId);
        return converter.getAsString(FacesContext.getCurrentInstance(), null,
                object);
    }

    /** 
     * @param entities list de String con los valores del select.
     * @param selectOne boolean que indica si se agreaga una opcion vacia al inicio.
     * @return Array de tipo SelectItem
     * @throws No dispara ninguna excepcion.
     */
    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    /**
     * Crea un array de SelectItem
     * 
     * @param entities
     *            List de clases que hereden de CodeDescBean
     * @param selectOne
     *            boolean que indica si se agreaga una opcion vacia al inicio.
     * @return Array de tipo SelectItem
     * @throws No
     *             dispara ninguna excepcion.
     */
    public static SelectItem[] getSelectItemsCodeDesc(
            List<? extends CodeDescBean> entities, boolean selectOne) {
    	return getSelectItemsCodeDesc(entities, selectOne, "---");
    }

    public static SelectItem[] getSelectItemsCodeDesc(
            List<? extends CodeDescBean> entities, boolean selectOne, String firstOptionText) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", firstOptionText);
            i++;
        }
        for (CodeDescBean bean : entities) {
            items[i++] = new SelectItem(bean.getCodigo(), bean.getDescripcion());
        }
        return items;
    }

    /** 
     * Crea un array de SelectItem
     * @param entities List 
     * @param selectOne boolean que indica si se agreaga una opcion vacia al inicio.
     * @return Array de tipo SelectItem
     * @throws No dispara ninguna excepcion.
     */
    public static SelectItem[] getSelectItemsMultitiendaBean(List<? extends MultitiendaBean> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "Seleccione");
            i++;
        }
        for (MultitiendaBean bean : entities) {
            items[i++] = new SelectItem(bean.getNombreProperty(), bean.getNombre());
        }
        return items;
    }      

    /** 
     * Crea un array de SelectItem
     * @param entities List 
     * @param selectOne boolean que indica si se agreaga una opcion vacia al inicio.
     * @return Array de tipo SelectItem
     * @throws No dispara ninguna excepcion.
     */
    public static SelectItem[] getSelectItemsMontosRecargaTarjetaCredito() {

        List<MontoRecargaBean> entities = ParametrosHelper.getMontosRecargaTarjetaCredito();
        
        int size = entities.size() + 1;
        SelectItem[] items = new SelectItem[size];
        items[0] = new SelectItem("0", "Selecciona monto");
        
        int i = 1;

        for (MontoRecargaBean bean : entities) {
            String value = String.valueOf(bean.getMonto().longValue());
            String label = "$ " + Utils.formatMoneyPuntos(bean.getMonto()) + " - "
                    + bean.getVigencia() + " dias";
            items[i++] = new SelectItem(value, label);
        }
        return items;
    }

    /**
     * Crea un array de SelectItem
     * @param entities List 
     * @param selectOne boolean que indica si se agreaga una opcion vacia al inicio.
     * @return Array de tipo SelectItem
     * @throws No dispara ninguna excepcion.
     */
    public static SelectItem[] getSelectItemsMotivoFormularioContacto() {

        List<CodeDescBean> entities = ParametrosHelper.getMotivosFormularioContacto();

        int size = entities.size();
        SelectItem[] items = new SelectItem[size];
        
        int i = 0;

        for (CodeDescBean bean : entities) {
            String value = bean.getCodigo();
            String label = bean.getDescripcion();
            items[i++] = new SelectItem(value, label);
        }

        return items;
    }

    /** 
     * Crea un array de SelectItem
     * @param entities List 
     * @param selectOne boolean que indica si se agreaga una opcion vacia al inicio.
     * @return Array de tipo SelectItem
     * @throws No dispara ninguna excepcion.
     */
    public static SelectItem[] getSelectItemsTiposFormularioContacto() {

        List<CodeDescBean> entities = ParametrosHelper.getTiposFormularioContacto();

        int size = entities.size();
        SelectItem[] items = new SelectItem[size];

        int i = 0;

        for (CodeDescBean bean : entities) {
            String value = bean.getCodigo();
            String label = bean.getDescripcion();
            items[i++] = new SelectItem(value, label);
        }

        return items;
    }

    /**
     * 
     * @param msisdnAsociados
     * @param selectOne
     * @return
     */
	public static SelectItem[] getSelectItemsMsisdnAsociados(
			List<MsisdnAsociadoBean> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (MsisdnAsociadoBean bean : entities) {
            items[i++] = new SelectItem(bean.getNumeroPcs(), bean.getDescripcionTipoMsisdn());
        }        
        return items;
	}

    /**
     * 
     * @return array {@link SelectItem} con las opciones validas para los dias
     *         de anticipacion de aviso de factura electronica
     */
    public static SelectItem[] getSelectItemsDiasAnticipacion() {
        List<CodeDescBean> diasAnticipacionList = ParametrosHelper
                .getDiasAnticipacionFacturaElecList();
        return getSelectItemsCodeDesc(diasAnticipacionList, true);
    }

	 /**
     * Agrear un parametro al request
     * @param name
     * @param value
     * @deprecated A partir de 05-05-2011, use JSFPortletHelper.getRequest.addAttribute() 
     */
    public static void setRequestParameter(String name, String value) {
        FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().put(name, value);
    }
    
    
    
    /** 
     * Crea un array de SelectItem
     * @param entities List
     * @return Array de tipo SelectItem
     * @throws No dispara ninguna excepcion.
     */
    public static List<CodeDescBean> getSelectItemsTipoReclamos(String tipo) {
        List<CodeDescBean> items = ParametrosHelper.getTiposReclamoList(tipo); 
        return items;
    }
    
    
	public static SelectItem[] getSelectItemsMsisdnAsociadosReclamos(
			List<MsisdnAsociadoBean> entities, boolean selectOne) {

		int size = selectOne ? entities.size() + 1 : entities.size();
		SelectItem[] items = new SelectItem[size];			
		
		try {
			int i = 0;
			if (selectOne) {
				items[0] = new SelectItem("", "---");
				i++;
			}
			String tipo;
			for (MsisdnAsociadoBean bean : entities) {
				tipo = "0";
				if (bean != null) {
					if (bean.getMercado() != null
							&& bean.getSubMercado() != null) {
						if (MiEntelBusinessHelper.isMercadoPrepago(bean
								.getMercado())
								|| bean
										.getSubMercado()
										.equals(
												MiEntelProperties
														.getProperty("miEntel.subMercadoEMP"))
								|| bean
										.getSubMercado()
										.equals(
												MiEntelProperties
														.getProperty("miEntel.subMercadoSGO"))) {
							tipo = "1";

						}

						items[i++] = new SelectItem(bean.getNumeroPcs() + "|"
								+ tipo, bean.getDescripcionTipoMsisdn());						
					}
				}
			}
			
		} catch (Exception e) {

		}
		return items;
	}
    
  
}
