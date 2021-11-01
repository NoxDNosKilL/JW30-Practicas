package pe.edu.galaxy.training.java.web.bean;

import java.io.Serializable;

public class UsuarioBean extends GenericoBean implements Serializable{

	private static final long serialVersionUID = 6817537814663320705L;

	private String usuario;

	private String clave;

	private String nombre;


	public UsuarioBean() {
	}


	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	@Override
	public String toString() {
		return super.toString()+ " UsuarioBean [usuario=" + usuario + ", clave=" + clave
				+ ", nombre=" + nombre + "]";
	}
}