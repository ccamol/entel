/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.appmobile.vo;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.esa.ponline.appmobile.util.Formato;


/**
 * Bean representante de un Rol Unico Tributario (RUT). Contiene los
 * constructores necesarios para la generacion de RUT validos a partir de un rut
 * sin digito verificador, validar RUT o solicitarlo en formato canonico para
 * presentacion.
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
@Deprecated
public class RutVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4865871925941462842L;

	private static final Logger LOGGER = Logger.getLogger(RutVO.class);

	/**
     * Seccion numerica del RUT
     */
    private static long numero;

    /**
     * Digito verificador del RUT
     */
    private static char digito;

    private static boolean rutValido;

    public RutVO() {
        super();
    }

    /**
     * Constructor para RUT preparado para rut de la forma <i>xxxxxxxxy</i>.
     * Donde <i>xxxxxxxx</i> es la parte numerica del RUT y <i>y</i> es el
     * digito verificador.
     * 
     * @param rutCompleto
     *            String RUT en la forma <i>xxxxxxxxy</i>
     * 
     * @throws IllegalArgumentExceptionsi
     *             se cumple una de las siguientes logicas: <br>
     *             <li>rutCompleto == null || rutCompleto.trim().equals("") <li>
     *             rutCompleto.length() < 2 <br>
     * @throws NumberFormatException
     *             Si <code>rutCompleto</code> en la parte numerica del RUT no
     *             es un numero valido
     */
    public RutVO(String rutCompleto) {
        super();
       
        if (rutCompleto == null || rutCompleto.trim().equals("")) {
        	LOGGER.info("rutCompleto cannot be null or empty");
        }

        if (rutCompleto.length() < 2) {
        	LOGGER.info("rutCompleto size debe ser mayor a 2: "+ rutCompleto.length());
        }else{
        String number = rutCompleto.substring(0, rutCompleto.length() - 1);
            try {
				if(Formato.esNumerico(number)){
        char dv = rutCompleto.charAt(rutCompleto.length() - 1);
        if (dv=='K'){
            dv = 'k';
        }
		
        long numero = Long.parseLong(number);
        if (numero <= 0) {
            	LOGGER.info("rut debe ser positivo: " + numero);
        }

            RutVO.digito = dv;
            RutVO.numero = numero;
		            setRutValido(true);
				}else{
					setRutValido(false);
        }
			} catch (Exception e) {
				LOGGER.info("Rut no es numerico");
			}
        }
    }
    
    /**
     * Metodo utilitario que recibe un objeto de la clase RutBean
     * y convierte su digito verificador en MAYUSCULAS.
     * 
     * @param rutBean
     *            objeto de la clase RutBean
     */
    public static void rutToMayus(RutVO rutBean) {
    	if (RutVO.digito == 'k'){
    		RutVO.digito = 'K';
        }
    	
    }

    public RutVO(long numero, char digito) {

        super();

        RutVO.numero = numero;
        RutVO.digito = digito;
    }

    /**
     * @return the rut
     */
    public long getNumero() {
        return numero;
    }

    /**
     * @param numero
     *            the rut to set
     */
    public void setNumero(long numero) {
        RutVO.numero = numero;
    }

    /**
     * @return the digito
     */
    public char getDigito() {
        return digito;
    }

    /**
     * @param digito
     *            the digito to set
     */
    public void setDigito(char digito) {
        RutVO.digito = digito;
    }

    /**
     * Parsing de un String con un RUT en la forma <i>xxxxxxxxy</i>. Donde
     * <i>xxxxxxxx</i> es la parte numerica e <i>y</i> es el digito verificador.<br>
     * Este metodo NO VALIDA el RUT (ver {@link #validate(long, char)} )
     * 
     * @param rutCompleto
     *            String RUT completo en la forma <i>xxxxxxxxy</i>
     * @return RutBean a partir de <code>rutCompleto</code>
     * @throws IllegalArgumentExceptionsi
     *             se cumple una de las siguientes logicas: <br>
     *             <li>rutCompleto == null || rutCompleto.trim().equals("") <li>
     *             rutCompleto.length() < 2 <br>
     * @throws NumberFormatException
     *             Si <code>rutCompleto</code> en la parte numerica del RUT no
     *             es un numero valido
     */
    public static RutVO parseRut(String rutCompleto) {
        return new RutVO(rutCompleto);
    }

    /**
     * Realiza el calculo del Digito verificador para el numero de rut indicado
     * en <code>numero</code>
     * 
     * @param numero
     *            numero de RUT para el que se desea calcular el digito
     * @return char con el digito verificador correspondiente a
     *         <code>numero</code>.<br>
     *         Para el caso de 'k' retorna la letra minuscula 'k'
     */
    public static char calculateDV(long numero) {

        int module = modulo11(numero);

        if (module == 11) {
            return '0';
        }
        else if (module == 10) {
            return 'k';
        }
        else {
            return String.valueOf(module).charAt(0);
        }

    }

    
    public static boolean validate() {
        return (digito == calculateDV(numero));
    }
    
    

    /**
     * Valida si <code>dv</code> corresponde como digito verificador de
     * <code>numero</code>
     * 
     * @param numero
     *            parte numerica del rut
     * @param dv
     *            digito verificador del rut
     * @return true si <code>dv</code> corresponde como digito verificador de
     *         <code>numero</code>, en caso contrario, false
     */
//    private static boolean validate(long numero, char dv) {
//        return (dv == calculateDV(numero));
//    }

    private static final int MODULO_11_START = 2;
    private static final int MODULO_11_END = 7;

    /**
     * Algoritmo de codigo de verificacion en Modulo 11. {@link http
     * ://es.wikipedia.org/wiki/C%C3%B3digo_de_control#M.C3.B3dulo_11s}
     * 
     * @param numero
     * @return
     */
    private static int modulo11(long numero) {

        int suma = 0;
        String stringNumero = String.valueOf(numero);
        int factor = MODULO_11_START;

        for (int i = stringNumero.length() - 1; i >= 0; i--) {

            int currentDigito = Integer.parseInt(String.valueOf(stringNumero
                    .charAt(i)));
            suma += currentDigito * factor;

            if (factor == MODULO_11_END) {
                factor = MODULO_11_START;
            }
            else {
                factor++;
            }
        }

        return 11 - (suma % 11);
    }
    

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getStringValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + digito;
        result = prime * result + (int) (numero ^ (numero >>> 32));
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings("static-access")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RutVO other = (RutVO) obj;
        if (digito != other.digito) {
            return false;
        }
        if (numero != other.numero) {
            return false;
        }
        return true;
    }

    /**
     * Representacion String del Rut.<br>
     * Es similar a llamar {@link #toString()} en esta clase
     * 
     * @return String con la representacion String del Rut de la forma
     *         {numero}{digito}
     */
    public String getStringValue() {
        return String.valueOf(RutVO.numero) + String.valueOf(RutVO.digito);
    }
    
	public boolean isRutValido() {
		return rutValido;
	}

	public void setRutValido(boolean rutValido) {
		RutVO.rutValido = rutValido;
	}
    
}
