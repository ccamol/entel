package com.esa.ponline.appmobile.util;

import com.esa.ponline.appmobile.vo.RutVO;

public class Rut {

    static public String format(String rutParam){
    	int cont=0;
    	String rut = formatTrim(rutParam);        
        String format = "-"+rut.substring(rut.length()-1);
        
        for(int i = rut.length()-2;i>=0;i--){
            format = rut.substring(i, i+1)+format;
            cont++;
            if(cont == 3 && i != 0){
                format = "."+format;
                cont = 0;
            }
        }
        
        return format;
    }
    
    static public String formatTrim(final String rut){
        return rut.replace(".", "").replace("-", "");
    }

    static public boolean isValid(String rutParam){
    	String rut=formatTrim(rutParam);
		long num = RutVO.parseRut(rutParam).getNumero();

		if (num != 0) {
        int dvR,dvT,suma=0;
        int[] serie = {2,3,4,5,6,7};
        
        dvR = Integer.valueOf(rut.substring(rut.length()-1));
        for(int i = rut.length()-2;i>=0; i--){
            suma +=  Integer.valueOf(rut.substring(i, i+1))*serie[(rut.length()-2-i)%6];
        }
        dvT=11-suma%11;
        
        if(dvT == dvR){
            return true;
        }else{
            return false;
        }
    }
		return false;
	}
}