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
	
	public Producto(long id, String nombre) throws ProductoException {
		super();
		this.id = id;
		this.nombre = nombre;

	}


}
