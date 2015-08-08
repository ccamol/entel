package com.epcs.recursoti.configuracion.backingfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bea.netuix.servlets.controls.BackingContext;
import com.bea.netuix.servlets.controls.application.backing.DesktopBackingContext;
import com.bea.netuix.servlets.controls.content.backing.AbstractJspBacking;
import com.bea.netuix.servlets.controls.page.BookBackingContext;
import com.bea.netuix.servlets.controls.page.PageBackingContext;

public class TitleBacking extends AbstractJspBacking {

	private static final long serialVersionUID = 1L;
	private static final String SEPARADOR = " - ";
	//private static final String ROOT = "Mi Entel";
	
	private StringBuffer title;
	private String root;

	@Override
	public boolean preRender(HttpServletRequest request, HttpServletResponse response) {
		
		title = new StringBuffer("");
		
		DesktopBackingContext dbc = DesktopBackingContext.getDesktopBackingContext(request);
		
		if (dbc != null) {
			try{
				root = dbc.getTitle();
				
				PageBackingContext pbc = dbc.getDisplayedPageBackingContext();
				
				if (pbc != null) {
					String tt = getTitleTail(pbc);
					dbc.setTitle(tt);
				} else {
					//System.out.println("Page fuera de contexto.");
				}
				
			} catch (Exception e) {
				
			}
		}
		return super.preRender(request, response);
	}

	private String getTitleTail(BackingContext context) {
		
		if (context == null) {
			title.insert(0, root + SEPARADOR);
			return title.toString();
		}
		
		if ("PageBackingContext".equals(context.getClass().getSimpleName())) {
			
			PageBackingContext page = (PageBackingContext) context;
			title.append(page.getTitle());
			getTitleTail(page.getParentBackingContext());
			
		} 
		
		else if ("BookBackingContext".equals(context.getClass().getSimpleName())) {
			
			BookBackingContext book = (BookBackingContext) context;
			title.insert(0, book.getTitle() + SEPARADOR);
			getTitleTail(book.getParentBackingContext());
		}
		
		return title.toString();
	}
}
