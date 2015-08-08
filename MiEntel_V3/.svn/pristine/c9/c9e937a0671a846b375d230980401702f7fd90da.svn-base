/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.jsf.tags;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentELTag;

public class IteratorTag extends UIComponentELTag {
	private String var;
	private String value;
	private String rowIndexVar;

	public void setVar(String var) {
		this.var = var;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setRowIndexVar(String rowIndexVar) {
		this.rowIndexVar = rowIndexVar;
	}

	protected void setProperties(UIComponent component) {
		super.setProperties(component);

		IteratorComponent iteratorComponent = (IteratorComponent) component;

		if (var != null) {
			iteratorComponent.setVar(var);
		}
		if (rowIndexVar != null) {
			iteratorComponent.setRowIndexVar(rowIndexVar);
		}

		Application application = FacesContext.getCurrentInstance()
				.getApplication();
		iteratorComponent.setValueExpression("value", 
				application.getExpressionFactory().createValueExpression(FacesContext.getCurrentInstance().getELContext(), value, Object.class));
				
	}

	public String getComponentType() {
		return IteratorComponent.COMPONENT_TYPE;
	}

	public String getRendererType() {
		return null;
	}
}
