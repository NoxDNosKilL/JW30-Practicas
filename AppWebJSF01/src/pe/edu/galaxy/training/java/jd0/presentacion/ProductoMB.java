package pe.edu.galaxy.training.java.jd0.presentacion;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import lombok.Data;
import pe.edu.galaxy.training.java.jd0.bean.Producto;
import pe.edu.galaxy.training.java.jd0.persistencia.exception.DAOException;
import pe.edu.galaxy.training.java.jd0.persistencia.implemtacion.ProductoDAOImpl;
import pe.edu.galaxy.training.java.jd0.persistencia.interfaces.ProductoDAO;

@SessionScoped
@ManagedBean(name="productoMB")
@Data 	
public class ProductoMB {
	
	private Producto producto;
	
	private List<Producto> productos;
	
	@PostConstruct
	public void init() {
		this.setProducto(new Producto());
		//this.getProducto().setNombre("Impresora Epson");
		this.listar();
	}
	
	public String nuevo() {
		this.setProducto(new Producto());
		return "productos-registro";
	}
	
	public String cancelar() {
		this.setProducto(new Producto());
		this.listar();
		return "productos-listado";
	}
	
	public void listar() {

		
		System.out.println("Nombre -> "+this.producto.getNombre());
		
		try {
			ProductoDAO productoDAO = new ProductoDAOImpl();

			productos = productoDAO.listar(this.getProducto().getNombre());

			productos.forEach(System.out::println);

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}
	}
	
	public void grabar() {
		
		System.out.println("this.getProducto().getId() -> " + this.getProducto().getId());

		try {
			ProductoDAO productoDAO = new ProductoDAOImpl();

			if (this.getProducto().getId()>0) {
				if (productoDAO.modificar(this.getProducto())) {
					System.out.println("Exito de actualización");
				} else {
					System.err.println("Error de actualización");
				}
				
			}else {
				if (productoDAO.agregar(this.getProducto())) {
					System.out.println("Exito de registro");
				} else {
					System.err.println("Error de registro");
				}
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
	
	public void eliminar(Producto producto) {

		try {
			ProductoDAO productoDAO = new ProductoDAOImpl();

			if (productoDAO.eliminar(producto)) {
				this.listar();
				System.out.println("Exito de eliminación");
			} else {
				System.err.println("Error de eliminación");
			}
		} catch (DAOException e) {

			System.err.println(e.getMessage());
		}

	}
	
	public String  modificar(Producto producto) {
		this.setProducto(producto);
		return "productos-registro";
		/*
		try {
			ProductoDAO productoDAO = new ProductoDAOImpl();

			if (productoDAO.eliminar(producto)) {
				this.listar();
				System.out.println("Exito de eliminación");
			} else {
				System.err.println("Error de eliminación");
			}
		} catch (DAOException e) {

			System.err.println(e.getMessage());
		}*/

	}
	
	
	public void limpiar() {
		this.setProducto(new Producto());
		this.listar();
		//this.setProductos(new ArrayList<>());
	}

}
