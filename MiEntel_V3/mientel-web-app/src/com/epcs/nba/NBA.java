package com.epcs.nba;

import java.io.Serializable;

import com.epcs.recursoti.configuracion.Utils;

public class NBA implements Serializable {
	
	private static final long serialVersionUID = 1731613428044085415L;
	
	private String img;
	/**
	 * atributo necesario para definir color de los textos de acuerdo a la imagen obtenida
	 */
	private String idImg;
	private String url;
	private String oculto;
	private String estilo;
	private String seleccionado;
	private String nombreOferta;
	private String codigoOferta;
	private String movil;
	private String grupo;
	private String valor;
	private String valorFormateado;
	private String tipoBolsa;
	private boolean ofertaBAM;
	
	private boolean primero=false;
	
	public void setImg(String img) {
		this.img = img;
	}
	public String getImg() {
		return img;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setOculto(String oculto) {
		this.oculto = oculto;
	}
	public String getOculto() {
		return oculto;
	}
	
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	public String getEstilo() {
		return estilo;
	}
	public void setSeleccionado(String seleccionado) {
		this.seleccionado = seleccionado;
	}
	public String getSeleccionado() {
		return seleccionado;
	}
	public void setPrimero(boolean primero) {
		this.primero = primero;
	}
	public boolean isPrimero() {
		return primero;
	}
	public void setNombreOferta(String nombreOferta) {
		this.nombreOferta = nombreOferta;
	}
	public String getNombreOferta() {
		return nombreOferta;
	}
	public void setMovil(String movil) {
		this.movil = movil;
	}
	public String getMovil() {
		return movil;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	/**
	 * @param isOfertaBAM the isOfertaBAM to set
	 */
	public void setOfertaBAM(boolean isOfertaBAM) {
		this.ofertaBAM = isOfertaBAM;
	}
	/**
	 * @return the isOfertaBAM
	 */
	
	
	public boolean isOfertaBAM() {
		return ofertaBAM;
	}
	public String getTipoBolsa() {
		return tipoBolsa;
	}
	public void setTipoBolsa(String tipoBolsa) {
		this.tipoBolsa = tipoBolsa;
	}
	public String getValor() {
		return valor;
	}
	public void setCodigoOferta(String codigoOferta) {
		this.codigoOferta = codigoOferta;
	}
	public String getCodigoOferta() {
		return codigoOferta;
	}
	public String getValorFormateado() {
		String valorFormato="";
		if(null != valor && !" ".equalsIgnoreCase(valor)){
			valorFormato= Utils.formatMoneyPuntos(Double.parseDouble(valor));
		}
		return valorFormato;
		}
	/**
	 * id de la imagen obtenida de forma aleaotria
	 * @return
	 */
	public String getIdImg() {
		return idImg;
	}
	/**
	 * id de la imagen obtenida de forma aleaotria
	 * @return
	 */
	public void setIdImg(String idImg) {
		this.idImg = idImg;
	}
	
	
}
