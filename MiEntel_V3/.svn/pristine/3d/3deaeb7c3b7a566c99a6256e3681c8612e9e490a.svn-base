package com.epcs.portlet.footer.backing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bea.netuix.servlets.controls.application.backing.DesktopBackingContext;
import com.bea.netuix.servlets.controls.content.backing.AbstractJspBacking;
import com.bea.netuix.servlets.controls.page.BookBackingContext;
import com.bea.netuix.servlets.controls.page.PageBackingContext;
import com.bea.netuix.servlets.controls.page.PagePresentationContext;

public class FooterBacking extends AbstractJspBacking {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(HttpServletRequest request, HttpServletResponse response) {
		
		StringBuffer title = new StringBuffer("Mi Entel - ");
		
		DesktopBackingContext dbc = DesktopBackingContext.getDesktopBackingContext(request);
		
		if (dbc != null) {
			try{
				PageBackingContext pbc = dbc.getDisplayedPageBackingContext();
				PagePresentationContext pbc2 = PagePresentationContext.getPagePresentationContext(request);
				BookBackingContext bbc = pbc.getParentBookBackingContext();
				System.out.println("\n" + bbc.getTitle() + "\n" + pbc.getLabel() + "\n" + pbc2);
				title.append(bbc.getTitle()).append(" - ");
				title.append(pbc.getTitle());
				dbc.setTitle(title.toString());
			} catch (Exception e) {
				
			}
		}
		super.init(request, response);
	}
}
