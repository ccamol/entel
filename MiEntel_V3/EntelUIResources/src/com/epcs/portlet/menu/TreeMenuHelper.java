package com.epcs.portlet.menu;

import javax.servlet.http.HttpServletRequest;
import com.bea.netuix.servlets.controls.page.BookPresentationContext;

public class TreeMenuHelper {
	public static BookPresentationContext getBookTree(HttpServletRequest req, int index) {
		
		if (index <= 1) return (BookPresentationContext.getBookPresentationContext(req));
		
		BookPresentationContext book = BookPresentationContext.getBookPresentationContext(req);

		while (index > 1) {
			if (!book.isDesktopBook()) //Si no es el book base
				book = book.getParentBookPresentationContext();
			index--;
		}
			
		return book;
	}
}
