package pe.edu.galaxy.training.java.jd0.aplicacion;

import java.util.List;
import pe.edu.galaxy.training.java.jd0.bean.Producto;
import pe.edu.galaxy.training.java.jd0.bean.exception.ProductoException;
import pe.edu.galaxy.training.java.jd0.persistencia.exception.DAOException;
import pe.edu.galaxy.training.java.jd0.persistencia.implemtacion.ProductoDAOImpl;
import pe.edu.galaxy.training.java.jd0.persistencia.interfaces.ProductoDAO;

public class AppProducto {

	public static void main(String[] args) {

		 listar();
		
		//listar("Epson");

		//agregar();
		
		//modificar();
		
		//eliminar();
		
	}

	private static void agregar() {

		try {
			ProductoDAO productoDAO = new ProductoDAOImpl();

			Producto producto = new Producto();
			producto.setNombre("Disco Duro externo LG 1TB");
			producto.setPrecio(250);
			producto.setStock(5);

			if (productoDAO.agregar(producto)) {
				System.out.println("Exito de registro");
			} else {
				System.err.println("Error de registro");
			}
		} catch (ProductoException e) {
			System.err.println(e.getMessage());
		} catch (DAOException e) {

			System.err.println(e.getMessage());
		}

	}
	
	private static void modificar() {

		try {
			ProductoDAO productoDAO = new ProductoDAOImpl();

			Producto producto = new Producto();
			producto.setId(6);
			producto.setNombre("Disco Duro externo LG 3TB");
			producto.setPrecio(380);
			producto.setStock(5);

			if (productoDAO.modificar(producto)) {
				System.out.println("Exito de actualizaci�n");
			} else {
				System.err.println("Error de actualizaci�n");
			}
		} catch (ProductoException e) {
			System.err.println(e.getMessage());
		} catch (DAOException e) {

			System.err.println(e.getMessage());
		}

	}
	
	private static void eliminar() {

		try {
			ProductoDAO productoDAO = new ProductoDAOImpl();

			Producto producto = new Producto();
			producto.setId(7);

			if (productoDAO.eliminar(producto)) {
				System.out.println("Exito de eliminaci�n");
			} else {
				System.err.println("Error de eliminaci�n");
			}
		} catch (DAOException e) {

			System.err.println(e.getMessage());
		}

	}


	private static void listar() {

		try {
			ProductoDAO productoDAO = new ProductoDAOImpl();

			List<Producto> productos = productoDAO.listar();
			/*
			 * for (Producto producto : productos) { System.out.println(producto); }
			 */

			productos.forEach(System.out::println);

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}

	}

	private static void listar(String nombre) {

		try {
			ProductoDAO productoDAO = new ProductoDAOImpl();

			List<Producto> productos = productoDAO.listar(nombre);

			productos.forEach(System.out::println);

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}

	}

}
