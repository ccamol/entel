package com.epcs.portlet.footer;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;
import com.bea.netuix.servlets.controls.application.backing.DesktopBackingContext;
import com.bea.netuix.servlets.controls.page.BookBackingContext;
import com.bea.netuix.servlets.controls.page.PageBackingContext;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;

public class FooterController {
	
	public void updatePortalTitle(PhaseEvent event) {
		
		StringBuffer title = new StringBuffer("Mi Entel - ");
		
		HttpServletRequest request = JSFPortletHelper.getRequest(FacesContext.getCurrentInstance());
		
		DesktopBackingContext dbc = DesktopBackingContext.getDesktopBackingContext(request);
		
		if (dbc != null) {
			try{
				PageBackingContext pbc = dbc.getDisplayedPageBackingContext();		
				BookBackingContext bbc = pbc.getParentBookBackingContext();
				title.append(bbc.getTitle()).append(" - ");
				title.append(pbc.getTitle());
				dbc.setTitle(title.toString());
			}catch (Exception e) {
				
			}
		} else {
			System.out.println("DBC es null!!!");
		}
	}
}
