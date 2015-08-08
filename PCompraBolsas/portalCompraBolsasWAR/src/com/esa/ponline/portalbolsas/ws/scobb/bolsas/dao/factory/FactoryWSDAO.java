package com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.factory;

import com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.IBolsaDAO;
import com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.imp.BolsaImp;



/**
 * @author ccastro
 *
 */

public class FactoryWSDAO {

    private static IBolsaDAO bolsas;
    
    public static IBolsaDAO getBolsasDAO(){
        if(bolsas == null){
            bolsas = new BolsaImp();
            return bolsas;
        }else{
            return bolsas;
        }
    }
    
    
}