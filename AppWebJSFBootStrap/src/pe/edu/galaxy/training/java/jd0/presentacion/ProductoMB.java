package pe.edu.galaxy.training.java.jd0.presentacion;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.jd0.bean.Producto;
import pe.edu.galaxy.training.java.jd0.persistencia.exception.DAOException;
import pe.edu.galaxy.training.java.jd0.persistencia.implemtacion.ProductoDAOImpl;
import pe.edu.galaxy.training.java.jd0.persistencia.interfaces.ProductoDAO;
import pe.edu.galaxy.training.java.jd0.presentacion.constantes.CrudEnum;
import pe.edu.galaxy.training.java.jd0.presentacion.constantes.Mensajes;

import static pe.edu.galaxy.training.java.jd0.presentacion.constantes.CrudEnum.REGISTRO;
import static pe.edu.galaxy.training.java.jd0.presentacion.constantes.CrudEnum.ACTUALIZACION;
import static pe.edu.galaxy.training.java.jd0.presentacion.constantes.CrudEnum.ELIMINACION;
import static pe.edu.galaxy.training.java.jd0.presentacion.constantes.CrudEnum.LISTADO;


@SessionScoped
@ManagedBean(name="productoMB")
@Data 	
@EqualsAndHashCode(callSuper=false)

public class ProductoMB extends GenericoMB{
	
	private Producto producto;
	
	private Producto productoSelecteccionado;
	
	private List<Producto> productos;
	
	@PostConstruct
	public void init() {
		this.setProducto(new Producto());
		//this.getProducto().setNombre("Impresora Epson");
		this.listar();
	}
	
	public String nuevo() {
		this.setProducto(new Producto());
		return "productos_registro";
	}
	
	public String cancelar() {
		this.setProducto(new Producto());
		this.listar();
		return "productos_listado";
	}
	
	public void listar() {

		
		System.out.println("Nombre -> "+this.producto.getNombre());
		
		try {
			ProductoDAO productoDAO = new ProductoDAOImpl();
			productos = productoDAO.listar(this.getProducto().getNombre());
			productos.forEach(System.out::println);
		} catch (Exception e) {
			super.msgCRUDError(LISTADO);
		}
	}
	
	public void grabar() {
		
		if (!validar()) {
			return;
		} 
		
		try {
			ProductoDAO productoDAO = new ProductoDAOImpl();

			if (this.getProducto().getId()>0) {
				if (productoDAO.modificar(this.getProducto())) {
					//System.out.println("Exito de actualización");
					super.msgCRUDExito(ACTUALIZACION);
				} else {
					//System.err.println("Error de actualización");
					super.msgCRUDError(ACTUALIZACION);

				}
					
			}else {
				if (productoDAO.agregar(this.getProducto())) {
					//System.out.println("Exito de registro");
					super.msgCRUDExito(REGISTRO);

				} else {
					//System.err.println("Error de registro");
					super.msgCRUDError(REGISTRO);

				}
			}
			
		} catch (Exception e) {
			System.err.println(Mensajes.MSG_MAN_ERROR_GRA);
		}

	}
	
	public void eliminar(Producto producto) {

		try {
			ProductoDAO productoDAO = new ProductoDAOImpl();
			
			if (productoDAO.eliminar(producto)) {
				this.listar();
				//System.out.println("Exito de eliminación");
				super.msgCRUDExito(ELIMINACION);

			} else {
				//System.err.println("Error de eliminación");
				super.msgCRUDError(ELIMINACION);

			}
		} catch (DAOException e) {

			System.err.println(e.getMessage());
		}

	}
	
	
	public void eliminar() {

		try {
			ProductoDAO productoDAO = new ProductoDAOImpl();

			if (productoDAO.eliminar(this.getProductoSelecteccionado())) {
				this.setProducto(new Producto());
				this.listar();
				
				super.msgCRUDExito(ELIMINACION);
				
				//FacesContext context = FacesContext.getCurrentInstance();
				//context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Exito de eliminación"));
				
				//System.out.println("Exito de eliminación");
			} else {
				//System.err.println("Error de eliminación");
				super.msgCRUDError(ELIMINACION);

			}
		} catch (DAOException e) {

			System.err.println(e.getMessage());
		}

	}
	
	
	public String  modificar(Producto producto) {
		this.setProducto(producto);
		return "productos_registro";
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
	}

		
	public void seleccionar(Producto producto) {
		this.setProductoSelecteccionado(producto);
	}
	
	
	private boolean validar () {
		
		String nombre = this.getProducto().getNombre();
		
		if(nombre == null) {
			super.msgAlerta("Nombre requerido");
			return false;
		}
		
		nombre = nombre.trim();
		
		if(nombre.length()<5 || nombre.length()>240) {
			super.msgAlerta("El nombre debe tener como minimo 5 caracteres y maximo 240");
			return false;
		}	
		
		if(this.getProducto().getPrecio()<=0) {
			super.msgAlerta("El precio debe de ser mayor que 0");
			return false;
		}		
		if(this.getProducto().getStock()<=0) {
			super.msgAlerta("El stock debe de ser mayor que 0");
			return false;
		}	
		
		return true;
	}
}
