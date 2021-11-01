package pe.edu.galaxy.training.java.web.bean;


public class ClienteBean  extends GenericoBean {

	

	private String razonSocial;
	
	private String ruc;
	
	private String direccion;
	
	private String telefono;
	
	private String comentario;

	public ClienteBean() {
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	@Override
	public String toString() {
		return "ClienteBean [razonSocial=" + razonSocial + ", ruc=" + ruc + ", direccion=" + direccion + ", telefono="
				+ telefono + ", comentario=" + comentario + "]";
	}

}
