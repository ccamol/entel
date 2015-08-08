/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.jsf.tags;

/**
 * Componente jsf para iteraciones sobre Collections.
 * 
 * <it:iterator value="#{bean.list}" var="row" rowIndexVar="rowIndex">
 *    ...
 * </it:iterator>
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

import com.epcs.cliente.perfil.dao.EquipoDAO;
/**
 * 
 * <it:iterator value="#{bean.List}" var="row" rowIndexVar="rowIndex"> ...
 * </it:iterator>
 */

public class IteratorComponent extends UIComponentBase implements
		NamingContainer {
	private static final Logger LOGGER = Logger.getLogger(IteratorComponent.class);
	public final static String COMPONENT_TYPE = "com.epcs.recursoti.configuracion.jsf.tags.iterator";
	public final static String COMPONENT_FAMILY = "javax.faces.Data";

	private Object value = null;
	private String var = null;
	private String rowIndexVar = null;

	public void encodeChildren(FacesContext context) throws IOException {
		if (!isRendered()) {
			return;
		}

		Collection<?> dataModel = getDataModel();
		if (dataModel != null) {
			Map<String,Object> requestMap = context.getExternalContext().getRequestMap();
			int rowIndex = 0;
			for (Iterator<?> iter = dataModel.iterator(); iter.hasNext(); rowIndex++) {
				Object varObject = iter.next();
				if (var != null) {
					if (varObject != null) {
						requestMap.put(var, varObject);
					} else {
						requestMap.remove(var);
					}
				}
				if (rowIndexVar != null) {
					requestMap.put(rowIndexVar, Integer.valueOf(rowIndex));
				}
				renderRowChildren(context);
			}
			if (var != null) {
				requestMap.remove(var);
			}
			if (rowIndexVar != null) {
				requestMap.remove(rowIndexVar);
			}
		}
	}

	/**
	 * 
	 * Subclasses can decorate the children as they see fit.
	 */
	protected void renderRowChildren(FacesContext context) throws IOException {
		for (Iterator<?> iter = getChildren().iterator(); iter.hasNext();) {
			encodeRecursive(context, (UIComponent) iter.next());
		}
	}

	protected void encodeRecursive(FacesContext context, UIComponent component)
			throws IOException {
		if (component.isRendered()) {
			component.encodeBegin(context);
			if (component.getRendersChildren()) {
				component.encodeChildren(context);
			} else {
				for (Iterator<?> iter = component.getChildren().iterator(); iter
						.hasNext();) {
					encodeRecursive(context, (UIComponent) iter.next());
				}
			}
			component.encodeEnd(context);
		}
	}

	private Collection<?> getDataModel() {
		Collection<?> dataModel = null;
		Object val = getValue();
		if (val != null) {
			if (val instanceof Collection<?>) {
				dataModel = (Collection<?>) val;
			}else{
				//"Valor no es Collection");
			}
		}
		return dataModel;
	}

	public void encodeBegin(FacesContext context) throws IOException {
	}

	public void encodeEnd(FacesContext context) throws IOException {
		return;
	}

	public void decode(FacesContext context) {
		return;
	}

	public boolean getRendersChildren() {
		return true;
	}

	public void setValueExpression(String name, ValueExpression expression) {
		if ("var".equals(name) || "rowIndexVar".equals(name)) {
			LOGGER.error( new IllegalArgumentException());
		}
		super.setValueExpression(name, expression);
	}

	protected Object getFieldOrExpression(Object field, String expressionName) {
		Object retVal = null;
		if (field != null) {
			retVal = field;
		} else {
			ValueExpression expression = getValueExpression(expressionName); 
			if (expression != null) {
				retVal = expression.getValue(getFacesContext().getELContext());
				if(retVal instanceof Object[])
					retVal = new ArrayList<Object>(Arrays.asList((Object[])retVal));
			}
		}
		return retVal;
	}

	public Object getValue() {
		return getFieldOrExpression(value, "value");
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setRowIndexVar(String rowIndexVar) {
		this.rowIndexVar = rowIndexVar;
	}

	public Object saveState(FacesContext context) {
		Object values[] = new Object[4];
		values[0] = super.saveState(context);
		values[1] = value;
		values[2] = var;
		values[3] = rowIndexVar;
		return values;
	}

	public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;
		super.restoreState(context, values[0]);
		value = values[1];
		var = (String) values[2];
		rowIndexVar = (String) values[3];
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

}
