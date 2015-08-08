/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao.factory;

import com.esa.ponline.appmobile.ws.dao.IAccountDAO;
import com.esa.ponline.appmobile.ws.dao.IBundleDAO;
import com.esa.ponline.appmobile.ws.dao.ILoginDAO;
import com.esa.ponline.appmobile.ws.dao.IMenuDAO;
import com.esa.ponline.appmobile.ws.dao.IPlanDAO;
import com.esa.ponline.appmobile.ws.dao.IRegisterDAO;
import com.esa.ponline.appmobile.ws.dao.ITrafficDAO;
import com.esa.ponline.appmobile.ws.dao.imp.AccountImp;
import com.esa.ponline.appmobile.ws.dao.imp.BundleImp;
import com.esa.ponline.appmobile.ws.dao.imp.LoginImp;
import com.esa.ponline.appmobile.ws.dao.imp.MenuImp;
import com.esa.ponline.appmobile.ws.dao.imp.PlanImp;
import com.esa.ponline.appmobile.ws.dao.imp.RegisterImp;
import com.esa.ponline.appmobile.ws.dao.imp.TrafficImp;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class FactoryWSDAO {
	
	private static ILoginDAO login;
	private static ITrafficDAO trafico;
	private static IMenuDAO menu;
	private static IRegisterDAO registro;
	private static IBundleDAO bolsas;
	private static IAccountDAO cuenta;
	private static IPlanDAO plan;
	
	public static IPlanDAO getPlanDAO(){
		if(plan == null){
			plan = new PlanImp();
			return plan;
		}else{
			return plan;
		}
	}
	
	public static IRegisterDAO getRegistroDAO(){
		if(registro == null){
			registro = new RegisterImp();
			return registro;
		}else{
			return registro;
		}
	}
	
	public static IAccountDAO getCuentaDAO(){
		if(cuenta == null){
			cuenta = new AccountImp();
			return cuenta;
		}else{
			return cuenta;
		}
	}
	
	public static IMenuDAO getMenuDAO(){
		if(menu == null){
			menu = new MenuImp();
			return menu;
		}else{
			return menu;
		}
	}
	
	public static ILoginDAO getLoginDAO(){
		if(login == null){
			login = new LoginImp();
			return login;
		}else{
			return login;
		}
	}

	public static ITrafficDAO getTraficoDAO(){
		if(trafico == null){
			trafico = new TrafficImp();
			return trafico;
		}else{
			return trafico;
		}
	}
	
	public static IBundleDAO getBolsasDAO(){
		if(bolsas == null){
			bolsas = new BundleImp();
			return bolsas;
		}else{
			return bolsas;
		}
	}
}
