package pe.edu.galaxy.training.java.web.bean;

public class ProveedorBean extends GenericoBean {

	
	private String razonSocial;
	
	private String ruc;
	
	private String direccion;
	
	private String correo;

	private String telefono;

	
	public ProveedorBean() {
		super();
	}

	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	public String getRuc() {
		return ruc;
	}


	public void setRuc(String ruc) {
		this.ruc = ruc;
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

}
