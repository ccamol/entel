///* Propiedad de Entel PCS. Todos los derechos reservados */
//package com.esa.ponline.portalbolsas.bean;
//
//import java.io.Serializable;
//
//import com.epcs.cliente.perfil.util.PlanHelper;
//
///**
// * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
// *
// */
//
//public class TipoPlanBean implements Serializable {
//	
//    /**
//     * 
//     */
//    private static final long serialVersionUID = 1L;
//	
//	private String id;
//	
//	private String mercado;
//	
//	private String nombre;
//	
//	private String descripcion;
//	
//	
//
//	/**
//	 * 
//	 */
//	public TipoPlanBean() {
//	}
//
//	/**
//	 * 
//	 * @param id
//	 * @param mercado
//	 * @param nombre
//	 * @param descripcion
//	 */
//	public TipoPlanBean(String id, String mercado, String nombre,
//			String descripcion) {
//		super();
//		this.id = id;
//		this.mercado = mercado;
//		this.nombre = nombre;
//		this.descripcion = descripcion;
//	}
//
//	/**
//	 * 
//	 * @return id del plan
//	 */
//	public String getId() {
//		return id;
//	}
//
//	/**
//	 * 
//	 * @param id
//	 */
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	/**
//	 * 
//	 * @return sigla mercado del tipo de plan
//	 */
//	public String getMercado() {
//		return mercado;
//	}
//
//	/**
//	 * 
//	 * @param mercado
//	 */
//	public void setMercado(String mercado) {
//		this.mercado = mercado;
//	}
//
//	/**
//	 * 
//	 * @return nombre del plan 
//	 */
//	public String getNombre() {
//		return nombre;
//	}
//
//	/**
//	 * 
//	 * @param nombre
//	 */
//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}
//
//	/**
//	 * 
//	 * @return descripcion del plan
//	 */
//	public String getDescripcion() {
//		return descripcion;
//	}
//
//	/**
//	 * 
//	 * @param descripcion
//	 */
//	public void setDescripcion(String descripcion) {
//		this.descripcion = descripcion;
//	}
//	
//	
//	
//
//    /**
//     * 
//     * @return id plan tarifa plana ss
//     */
//	public int getPlanTarifaPlanaSs() {
//		return PlanHelper.getPlanTarifaPlanaSs();
//	}
//
//
//	/**
//	 * 
//	 * @return id plan red ss
//	 */
//	public int getPlanRedSs() {
//		return PlanHelper.getPlanRedSs();
//	}
//
//
//	/**
//	 * 
//	 * @return id tarifa unica frecuente
//	 */
//	public int getPlanTarifaUnicaFrec() {
//		return PlanHelper.getPlanTarifaUnicaFrec();
//	}
//
//
//	/**
//	 * 
//	 * @return id plan joven
//	 */
//	public int getPlanJoven() {
//		return PlanHelper.getPlanJoven();
//	}
//
//
//	/**
//	 * 
//	 * @return id plan full
//	 */
//	public int getPlanFull() {
//		return PlanHelper.getPlanFull();
//	}
//
//
//	/**
//	 * 
//	 * @return id plan tarifa unica
//	 */
//	public int getPlanTarifaUnica() {
//		return PlanHelper.getPlanTarifaUnica();
//	}
//
//
//	/**
//	 * 
//	 * @return id plan red fija 
//	 */
//	public int getPlanRedFija() {
//		return PlanHelper.getPlanRedFija();
//	}
//
//
//	/**
//	 * 
//	 * @return id plan familia
//	 */
//	public int getPlanFamilia() {
//		return PlanHelper.getPlanFamilia();
//	}
//
//
//	/**
//	 * 
//	 * @return id plan red empresa
//	 */
//	public int getPlanRedEmpresa() {
//		return PlanHelper.getPlanRedEmpresa();
//	}
//
//
//	/**
//	 * 
//	 * @return id plan multimedia iphone
//	 */
//	public int getPlanMultimediaIphone() {
//		return PlanHelper.getPlanMultimediaIphone();
//	}
//	
//	/**
//	 * 
//	 * @return id plan multimedia red
//	 */
//	public int getPlanMultimediaRed() {
//		return PlanHelper.getPlanMultimediaRed();
//	}
//
//
//	/**
//	 * 
//	 * @return id plan tarifa plana cc
//	 */
//	public int getPlanTarifaPlanaCc() {
//		return PlanHelper.getPlanTarifaPlanaCc();
//	}
//
//
//	/**
//	 * 
//	 * @return id plan red cc
//	 */
//	public int getPlanRedCc() {
//		return PlanHelper.getPlanRedCc();
//	}
//
//
//	/**
//	 * 
//	 * @return id plan multimedia cc
//	 */
//	public int getPlanMultimediaCc() {
//		return PlanHelper.getPlanMultimediaCc();
//	}
//	
//	
//	/**
//	 * 
//	 * @return id plan comunik2
//	 */
//	public int getPlanComunik2Id(){
//		return PlanHelper.getPlanComunik2Id();
//	}
//
//	
//	/**
//	 * 
//	 * @return
//	 */
//	public String getFlagHidden(){
//		return PlanHelper.getFlagHidden();
//	}
//	
//	
//	/**
//	 * 
//	 * @return
//	 */
//	public int getPlanMultimediaRedExcedido(){
//		return PlanHelper.getPlanMultimediaRedExcedido() ;
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	public int getPlanMultimediaRedExcedidoTodoDestino(){
//		return PlanHelper.getPlanMultimediaRedExcedidoTodoDestino() ;
//	}	
//	
//
//    /**
//     * 
//     * @return id plan empresa - estructura tarifaria
//     */
//	public int getPlanEmpresa() {
//		return PlanHelper.getPLAN_EMPRESA();
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	public int getPlanMultimediaRedExcedidoTodoDestinoEmpresas(){
//		return PlanHelper.getPlanMultimediaRedExcedidoTodoDestinoEmpresas();
//	}	
//	
//	public int getPlanGrupalEmpresasDatosExcedidos() {
//		return PlanHelper.getPlanGrupalEmpresas();
//	}
//
//
//
//}
