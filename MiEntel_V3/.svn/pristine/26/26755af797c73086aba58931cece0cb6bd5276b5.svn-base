/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.seguridad.bean;

import java.io.Serializable;

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
public class RutBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8257595174812357052L;

    /**
     * Seccion numerica del RUT
     */
    private long numero;

    /**
     * Digito verificador del RUT
     */
    private char digito;

    public RutBean() {
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
    public RutBean(String rutCompleto) {
        super();
       
        if (rutCompleto == null || rutCompleto.trim().equals("")) {
            throw new IllegalArgumentException(
                    "rutCompleto cannot be null or empty");
        }

        if (rutCompleto.length() < 2) {
            throw new IllegalArgumentException(
                    "rutCompleto length must be greater than 2: "
                            + rutCompleto.length());
        }

        String number = rutCompleto.substring(0, rutCompleto.length() - 1);
        char dv = rutCompleto.charAt(rutCompleto.length() - 1);
        if (dv=='K'){
            dv = 'k';
        }
		
        // This may throw NumberFormatException
        long numero = Long.parseLong(number);
        if (numero <= 0) {
            throw new NumberFormatException("rut debe ser positivo: " + numero);
        }

        if (!validate(numero, dv)) {
            throw new IllegalArgumentException(rutCompleto + " is an invalid RUT");
        }

        this.digito = dv;
        this.numero = numero;

    }

    public RutBean(long numero, char digito) {

        super();

        this.numero = numero;
        this.digito = digito;
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
        this.numero = numero;
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
        this.digito = digito;
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
    public static RutBean parseRut(String rutCompleto) {
        return new RutBean(rutCompleto);
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
    private static boolean validate(long numero, char dv) {
        return (dv == calculateDV(numero));
    }

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
        RutBean other = (RutBean) obj;
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
        return String.valueOf(this.numero) + String.valueOf(this.digito);
    }
    
}
