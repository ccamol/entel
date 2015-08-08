package com.epcs.bean;

import java.util.List;

public class CatalogoServiciosBean {
	private List<ServicioSagenBean> servicios;
	private List<CategorySagenBean> categorias;
	private List<PaginaServiciosSagenBean> paginas;
	private List<FamiliaSuscripcionBean> listFamily;

	public List<PaginaServiciosSagenBean> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<PaginaServiciosSagenBean> paginas) {
		this.paginas = paginas;
	}

	public List<ServicioSagenBean> getServicios() {
		return servicios;
	}

	public void setServicios(List<ServicioSagenBean> servicios) {
		this.servicios = servicios;
	}

	public List<CategorySagenBean> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategorySagenBean> categorias) {
		this.categorias = categorias;
	}

	public List<FamiliaSuscripcionBean> getListFamily() {
		return listFamily;
	}

	public void setListFamily(List<FamiliaSuscripcionBean> listFamily) {
		this.listFamily = listFamily;
	}

}
