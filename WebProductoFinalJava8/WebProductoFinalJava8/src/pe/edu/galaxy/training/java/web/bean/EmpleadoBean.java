package pe.edu.galaxy.training.java.web.bean;

import java.io.Serializable;

public class EmpleadoBean extends GenericoBean implements Serializable{

	private static final long serialVersionUID = 1965925594331321531L;

	private String apellidoPaterno="";
	
	private String apellidoMaterno="";
	
	private String nombre="";
	
	private String nroDocumento;
	
	private String direccion;
	
	private String correo;
	
	private String telefono;
	  
	  
	
	public EmpleadoBean() {
		super();
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public String getApellidoPaterno() {
		return apellidoPaterno;
	}


	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}


	public String getApellidoMaterno() {
		return apellidoMaterno;
	}


	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getNroDocumento() {
		return nroDocumento;
	}


	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getNombreCompleto() {
		return this.getApellidoPaterno().toUpperCase()+
				((this.getApellidoMaterno()!=null)?this.getApellidoMaterno().toUpperCase():"")+
				", "+
				this.getNombre();
	}


	@Override
	public String toString() {
		return "EmpleadoBean [apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", nombre="
				+ nombre + ", nroDocumento=" + nroDocumento + ", direccion=" + direccion + ", correo=" + correo
				+ ", telefono=" + telefono + "]";
	}
}