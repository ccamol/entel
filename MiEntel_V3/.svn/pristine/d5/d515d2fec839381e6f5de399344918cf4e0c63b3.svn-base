/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion;

import java.math.BigInteger;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public final class CifradoRSA {
    
    private static BigInteger n;
    private static BigInteger e;
    private static BigInteger d;
    
    
    private CifradoRSA(){
        
    }
    
    /**
     * @param llave un array de tipo BigInteger[] con el valor de la llave publica to set
     */
    public static void setLlavePublica(BigInteger[] llave){
        n = llave[0];
        e = llave[1];
    }
    
    /**
     * @param llave un array de tipo BigInteger[] con el valor de la llave privada to set
     */
    public static void setLlavePrivada(BigInteger[] llave){
        n = llave[0];
        d = llave[1];        
    }
    
    /**
     * @return x un array de tipo BigInteger[] con el valor de la llave publica
     */
    public static BigInteger[] getLlavePublica(){
        BigInteger[] x = new BigInteger[2];
        x[0] = n;
        x[1] = e;
        return x;
    }
    
    /**
     * @return x un array de tipo BigInteger[] con el valor de la llave privada
     */
    public static BigInteger[] getLlavePrivada(){
        BigInteger[] x = new BigInteger[2];
        x[0] = n;
        x[1] = d;
        return x;
    }
    
    /**
     * Encripta el texto usando la clave publica
     * @param mensaje Ristra que contiene el mensaje a encriptar
     * @return movilCifrado cadena que representa mensaje cifrado en texto plano.
     */
//    public BigInteger[] encripta(String mensaje)
    public static String encripta(String mensaje)
    {
        int i;
        byte[] temp = new byte[1];
        byte[] digitos = mensaje.getBytes();
        BigInteger[] bigdigitos = new BigInteger[digitos.length];
        
        for(i=0; i<bigdigitos.length;i++){
            temp[0] = digitos[i];
            bigdigitos[i] = new BigInteger(temp);
        }
        
        BigInteger[] encriptado = new BigInteger[bigdigitos.length];
        
        for(i=0; i<bigdigitos.length; i++)
            encriptado[i] = bigdigitos[i].modPow(e,n);
        
        String movilCifrado="";
        for(int h=0; h<encriptado.length; h++){
            movilCifrado=movilCifrado+encriptado[h].toString(16);
        }
        //return(encriptado);
        return movilCifrado;
    }
    /**
     * Desencripta el texto cifrado usando la clave privada
     * @param cifrado cadena que representa un Array de objetos BigInteger, el cual contiene el texto que va ser desencriptado
     * @return cadena que representa el mensaje desencriptado en texto plano
     */
    public static String desencripta(String cifrado) {
        BigInteger[] desencriptado = new BigInteger[(cifrado.length()/16)];
        BigInteger[] encriptado = new BigInteger[(cifrado.length()/16)];
        int k = 0;
        for(int j=0; j<cifrado.length(); j=j+16){
            encriptado[k]=new BigInteger(cifrado.substring(j,(j+16)),16);
            k++;
        }
        for(int i=0; i<desencriptado.length; i++)
            desencriptado[i] = encriptado[i].modPow(d,n);
        
        char[] charArray = new char[desencriptado.length];
        
        for(int i=0; i<charArray.length; i++)
            charArray[i] = (char) (desencriptado[i].intValue());
        
        return(new String(charArray));
    }

}
