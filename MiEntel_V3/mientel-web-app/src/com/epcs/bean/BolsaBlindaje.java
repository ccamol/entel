package com.epcs.bean;

import java.util.Date;

public class BolsaBlindaje {



/** Creates a new instance of Bolsa 
 * @return */
public void Bolsa() {
}

private String descripcion;
private String idBolsa;
private String observaciones;
private String precio;
private String unidades;
private String promocional;
private String tipo;
private String tipoVigencia;
private String valorVigencia;
private String estado;
private String cartaServicio;

//nuevos datos para saldo bolsas
protected Date fechaContratacion;
protected Date fechaExpiracion;
private String promocion;
private String fechaFinal;



private int     activarNumMovil;
private String  activarActivaDesactiva;
private int     activarCodigoRespuesta;
private String  activarCodigoRespuestaStr;
private String  activarMensajeRespuesta;
private String[] descripcionAdicional;

private String numRecargasPrevias;
private String estadoListaNegra;
private String numBolsasContratadas;

private boolean esBlackberry;


public String getDescripcion() {
    return descripcion;
}


public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
}

public String getIdBolsa() {
    return idBolsa;
}

public void setIdBolsa(String idBolsa) {
    this.idBolsa = idBolsa;
}

public String getObservaciones() {
    return observaciones;
}

public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
}

public String getPrecio() {
    return precio;
}

public void setPrecio(String precio) {
    this.precio = precio;
}

public String getUnidades() {
    return unidades;
}

public void setUnidades(String unidades) {
    this.unidades = unidades;
}

public String getPromocional() {
    return promocional;
}

public void setPromocional(String promocional) {
    this.promocional = promocional;
}

public String getTipo() {
    return tipo;
}

public void setTipo(String tipo) {
    this.tipo = tipo;
}

public String getTipoVigencia() {
    return tipoVigencia;
}

public void setTipoVigencia(String tipoVigencia) {
    this.tipoVigencia = tipoVigencia;
}

public String getValorVigencia() {
    return valorVigencia;
}

public void setValorVigencia(String valorVigencia) {
    this.valorVigencia = valorVigencia;
}


public void setEstado(String estado)
{
this.estado = estado;
}


public String getEstado()
{
return estado;
}


public void setCartaServicio(String cartaServicio)
{
this.cartaServicio = cartaServicio;
}


public String getCartaServicio()
{
return cartaServicio;
}

public String getPromocion() {
return promocion;
}

public void setPromocion(String promocion) {
this.promocion = promocion;
}

public void setActivarNumMovil(int activarNumMovil)
{
this.activarNumMovil = activarNumMovil;
}


public int getActivarNumMovil()
{
return activarNumMovil;
}


public void setActivarActivaDesactiva(String activarActivaDesactiva)
{
this.activarActivaDesactiva = activarActivaDesactiva;
}


public String getActivarActivaDesactiva()
{
return activarActivaDesactiva;
}


public void setActivarCodigoRespuesta(int activarCodigoRespuesta)
{
this.activarCodigoRespuesta = activarCodigoRespuesta;
}


public int getActivarCodigoRespuesta()
{
return activarCodigoRespuesta;
}


public void setActivarMensajeRespuesta(String activarMensajeRespuesta)
{
this.activarMensajeRespuesta = activarMensajeRespuesta;
}


public String getActivarMensajeRespuesta()
{
return activarMensajeRespuesta;
}

public String getActivarCodigoRespuestaStr() {
    return activarCodigoRespuestaStr;
}

public void setActivarCodigoRespuestaStr(String activarCodigoRespuestaStr) {
    this.activarCodigoRespuestaStr = activarCodigoRespuestaStr;
}
 

public String getFechaFinal() {
    return fechaFinal;
}

public void setFechaFinal(String fechaFinal) {
    this.fechaFinal = fechaFinal;
}

/**
 * @return the numRecargasPrevias
 */
public String getNumRecargasPrevias() {
    return numRecargasPrevias;
}

/**
 * @param numRecargasPrevias the numRecargasPrevias to set
 */
public void setNumRecargasPrevias(String numRecargasPrevias) {
    this.numRecargasPrevias = numRecargasPrevias;
}

/**
 * @return the estadoListaNegra
 */
public String getEstadoListaNegra() {
    return estadoListaNegra;
}

/**
 * @param estadoListaNegra the estadoListaNegra to set
 */
public void setEstadoListaNegra(String estadoListaNegra) {
    this.estadoListaNegra = estadoListaNegra;
}

/**
 * @return the numBolsasContratadas
 */
public String getNumBolsasContratadas() {
    return numBolsasContratadas;
}

/**
 * @param numBolsasContratadas the numBolsasContratadas to set
 */
public void setNumBolsasContratadas(String numBolsasContratadas) {
    this.numBolsasContratadas = numBolsasContratadas;
}

/**
 * @return the descripcionAdicional
 */
public String[] getDescripcionAdicional() {
    return descripcionAdicional;
}

/**
 * @param descripcionAdicional the descripcionAdicional to set
 */
public void setDescripcionAdicional(String[] descripcionAdicional) {
    this.descripcionAdicional = descripcionAdicional;
}

/**
 * @return the esBlackberry
 */
public boolean isEsBlackberry() {
    return esBlackberry;
}

/**
 * @param esBlackberry the esBlackberry to set
 */
public void setEsBlackberry(boolean esBlackberry) {
    this.esBlackberry = esBlackberry;
}

}