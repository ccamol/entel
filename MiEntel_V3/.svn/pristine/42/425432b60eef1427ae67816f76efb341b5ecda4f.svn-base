package com.epcs.billing.balance.util;

import com.epcs.recursoti.configuracion.MiEntelProperties;

public class ArcFour {
	
	public static String charset = MiEntelProperties.getProperty("parametros.factura.charset");
	
    private int pseudoStream[] = new int[256];
    private int a =0;
    private int b=0;

    public String encriptar(String plain, String key){
        ksa(key);
        String epa = "";
        for(int n = 0;n<plain.length();n++){
             epa+=ToHex((plain.charAt(n))^prng());
        }
        pseudoStream = new int[256];
        a = 0;
        b = 0;
        return epa;
    }

    /**
     *
     * @param plain
     * @param key
     * @return
     */
    public String desencriptar(String plain, String key) {
        ksa(key);
        String epa = "";
        for(int n = 0;(n+2)<=plain.length();n+=2){
            epa+=(char)((toDec(plain.substring(n,n+2)))^prng());
        }
        pseudoStream = new int[256];
        a = 0;
        b = 0;
        return epa;
    }
    /**
     * @param a
     * @return
     */
     private int toDec(String a){
         int tmp = 0;
         for(int n = a.length()-1,c=0;n>-1;n--,c++)
             tmp+=(charset.indexOf(String.valueOf(a.charAt(n))))*(Math.pow(16,c));
         return tmp;
     }

    private String ToHex(int a){
        String tmp="";
        do{
            tmp =charset.charAt(a%16)+tmp;
        }while((a>>=4)>0);
        return (tmp.length()>1)?tmp:"0"+tmp;
    }


    /**
     * @return
     */
     private int prng(){
         a = (++a)%256;
         b = (b+pseudoStream[a])%256;
         swap(pseudoStream,a,b);
         return pseudoStream[(pseudoStream[a]+pseudoStream[b])%256];
     }

    /**
     * @param key
     */
    private void ksa(String key){
        for (int n =0;n<pseudoStream.length;n++){
            pseudoStream[n] = n;
        }
        int j = 0;
        for (int n = 0;n<pseudoStream.length;n++){
            j = (j+pseudoStream[n]+((int)key.charAt(n%key.length())))%256;
            swap(pseudoStream,n,j);
        }
    }
    /**
     *
     * @param a
     * @param b
     * @param c
     */
    private void swap(int a[],int b,int c){
        int tmp = a[b];
        a[b] = a[c];
        a[c] = tmp;
    }
}
