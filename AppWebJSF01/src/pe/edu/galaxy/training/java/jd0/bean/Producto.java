package pe.edu.galaxy.training.java.jd0.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pe.edu.galaxy.training.java.jd0.bean.exception.ProductoException;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Producto {
	
	private long id;
	private String nombre;
	private int stock;
	private double precio;
	private boolean estado;
	
	public Producto(long id, String nombre, double precio) throws ProductoException {
		super();
		this.id = id;
		this.nombre = nombre;
		this.setPrecio(precio);
	}

	public void setPrecio(double precio) throws ProductoException{
		if (precio<0) {
			throw new ProductoException("El precio no puede ser negativo");
		}
		this.precio = precio;
	}

}
