package pe.edu.galaxy.training.java.web.model;

public class Producto{

	private long id;
	private String nombre;
	
	public Producto() {
		super();
	}

	public Producto(long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
