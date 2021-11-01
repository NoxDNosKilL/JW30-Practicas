package pe.edu.galaxy.training.java.web.bean;


public class ProductoBean extends GenericoBean{

	private String nombre;
	
	private float precio;
	
	private int stock;

	public ProductoBean() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductoBean [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock
				+ "]";
	}
	
}
