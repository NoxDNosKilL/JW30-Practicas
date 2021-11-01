package pe.edu.galaxy.training.java.web.presentacion;

import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.ACTUALIZACION;
import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.MODIFICACION;
import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.ELIMINACION;
import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.LISTADO;
import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.REGISTRO;

import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.web.entity.Producto;
import pe.edu.galaxy.training.java.web.persistencia.exception.DAOException;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.ProductoDAO;
import pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum;
import pe.edu.galaxy.training.java.web.presentacion.constantes.Mensajes;
import pe.edu.galaxy.training.java.web.servicio.excepcion.ServiceException;
import pe.edu.galaxy.training.java.web.servicio.interfases.ProductoService;


@Scope("session")
@Controller(value="productoMB")

@Data 	
@EqualsAndHashCode(callSuper=false)

public class ProductoMB extends GenericoMB{
	
	@Autowired //IoC/DI
	private ProductoService productoService;
	
	private ProductoDAO productoDAO;
	
	private Producto producto;
	
	private Producto productoSelecteccionado;
	
	private List<Producto> productos;
	
	@PostConstruct
	public void init() {
		this.setProducto(new Producto());
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
		try {
			productos = productoService.listar(this.getProducto().getNombre());
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

			if (this.getProducto().getId()>0) {
				if (productoService.modificar(this.getProducto())) {
					//System.out.println("Exito de actualización");
					super.msgCRUDExito(ACTUALIZACION);
				} else {
					//System.err.println("Error de actualización");
					super.msgCRUDError(ACTUALIZACION);

				}
					
			}else {
				if (productoService.agregar(this.getProducto())) {
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
			
			if (productoService.eliminar(producto)) {
				this.listar();
				//System.out.println("Exito de eliminación");
				super.msgCRUDExito(ELIMINACION);

			} else {
				//System.err.println("Error de eliminación");
				super.msgCRUDError(ELIMINACION);

			}
		} catch (ServiceException e) {

			System.err.println(e.getMessage());
		}

	}
	
	
	public void eliminar() {

		try {

			if (productoService.eliminar(this.getProductoSelecteccionado())) {
				this.setProducto(new Producto());
				this.listar();
				super.msgCRUDExito(ELIMINACION);
				//System.out.println("Exito de eliminación");
			} else {
				//System.err.println("Error de eliminación");
				super.msgCRUDError(ELIMINACION);

			}
		} catch (ServiceException e) {

			System.err.println(e.getMessage());
		}

	}
	
	
	public String  modificar(Producto producto) {
		try {
			if(producto == null) {
				System.out.println("Producto nulo");
				return "";
			}			
			Producto oProducto = this.getProductoService().buscarXCodigo(producto.getId());

			if(oProducto!= null) {
				this.setProducto(oProducto);
				return "productos_registro";
			}else {
				super.msgCRUDError(MODIFICACION);
				System.out.println("else");
			}
		} catch (Exception e) {
			super.msgCRUDError(MODIFICACION);
			System.out.println("catch");

		}		
		//this.setProducto(producto);
		//return "productos_registro";
		return "";

	}
	
	
	public void limpiar() {
		this.setProducto(new Producto());
		this.listar();
		//System.out.println(this.getProducto().getNombre());
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
