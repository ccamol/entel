package com.epcs.nba;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epcs.bean.MsisdnAsociadoBean;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;

public class NBAController2  extends HttpServlet{

	private static final long serialVersionUID = 8190170610121552038L;
	private static final Logger LOGGER = Logger.getLogger(NBAController2.class);
	private List<MsisdnAsociadoBean> numerosAsociados;
	public NBAController2(){}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
        try {
			NBAUtils nbaUtils=new NBAUtils();
			CuentaDelegate cuentaDelegate = new CuentaDelegate();
			int estado=Integer.parseInt(request.getParameter("estado"));
            String flagBam=request.getParameter("flagBam");
			String subMercado=request.getParameter("subMercado");
			String movil=request.getParameter("movil");
			numerosAsociados = cuentaDelegate.getListaMsisdnAsociados(movil,subMercado,flagBam);
			nbaUtils.actualizarMinimizarBanner(numerosAsociados, estado);
		} catch (Exception e) {
			 LOGGER.error("Exception caught: " + e.getMessage());
		}		
	}
	
	public void setNumerosAsociados(List<MsisdnAsociadoBean> numerosAsociados) {
		this.numerosAsociados = numerosAsociados;
	}

	public List<MsisdnAsociadoBean> getNumerosAsociados() {
		return numerosAsociados;
	}
}
