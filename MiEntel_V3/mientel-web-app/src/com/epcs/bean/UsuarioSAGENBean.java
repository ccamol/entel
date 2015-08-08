package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;

public class UsuarioSAGENBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String info;
	private String sexo;
	private String email;
	private String fechaNacimiento;
	
	private DireccionBean direccion;	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public DireccionBean getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionBean direccion) {
		this.direccion = direccion;
	}	
	
}
