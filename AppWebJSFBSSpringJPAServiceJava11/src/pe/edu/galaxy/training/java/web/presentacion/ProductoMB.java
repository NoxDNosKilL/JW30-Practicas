package pe.edu.galaxy.training.java.web.presentacion;

import static pe.edu.galaxy.training.java.web.presentacion.enums.CrudEnum.ACTUALIZACION;
import static pe.edu.galaxy.training.java.web.presentacion.enums.CrudEnum.ELIMINACION;
import static pe.edu.galaxy.training.java.web.presentacion.enums.CrudEnum.REGISTRO;
import static pe.edu.galaxy.training.java.web.presentacion.enums.CrudEnum.MODIFICACION;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.web.entity.Producto;
import pe.edu.galaxy.training.java.web.presentacion.enums.CrudEnum;
import pe.edu.galaxy.training.java.web.servicio.exception.ServiceException;
import pe.edu.galaxy.training.java.web.servicio.insterfaces.ProductoService;

@Scope("session")
@Controller(value="productoMB")
@Data
@EqualsAndHashCode(callSuper=false)
public class ProductoMB extends GenericoMB {
	
	@Autowired //IoC/DI
	private ProductoService productoService;
	
	private Producto producto;
	
	private Producto productoSeleccionado;
	
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
	
	public void seleccionar(Producto producto) {
		this.setProductoSeleccionado(producto);
	}
	
	public void listar() {
		try {
			productos = productoService.listar(this.getProducto().getNombre());
		} catch (ServiceException e) {
			super.msgCRUDError(CrudEnum.LISTADO);
		}
	}
	
	public void grabar() {	
		if (!validar()) {
			return;
		}
		
		try {
			if (this.getProducto().getId()>0) {
				if (productoService.modificar(this.getProducto())) {
				    super.msgCRUDExito(ACTUALIZACION);
				} else {
					 super.msgCRUDError(ACTUALIZACION);
				}
			}else {
				if (productoService.agregar(this.getProducto())) {
					super.msgCRUDExito(REGISTRO);
				} else {
					super.msgCRUDError(REGISTRO);
				}
			}
		} catch (ServiceException e) {
			super.msgCRUDError(CrudEnum.GRABAR);
		}
	}
	
	public void eliminar() {
		try {
			if (productoService.eliminar(this.getProductoSeleccionado())) {
				this.setProducto(new Producto());
				this.listar();
				super.msgCRUDExito(ELIMINACION);
			} else {
				super.msgCRUDError(ELIMINACION);
			}
		} catch (ServiceException e) {
			super.msgCRUDError(ELIMINACION);
		}
	}
	
	public String  modificar(Producto producto) {
		
		try {
			if (producto==null) {
				return "";
			}
			Producto oProducto = this.getProductoService().buscarXCodigo(producto.getId());
			if (oProducto!=null) {
				this.setProducto(oProducto);
				return "productos_registro";
			}else {
				super.msgCRUDError(MODIFICACION);
			}
			
		} catch (Exception e) {
			super.msgCRUDError(MODIFICACION);
			
		}
		return "";
	}
	
	public void limpiar() {
		this.setProducto(new Producto());
		this.listar();
	}
	
	private boolean validar() {
		
		String nombre=this.getProducto().getNombre();
		
		if (nombre==null) {
			super.msgAlerta("El nombre es requerido");
			return false;
		}
		nombre=nombre.trim();
		
		if (nombre.length()<5 || nombre.length()>240) {
			super.msgAlerta("El nombre debe tener como mínimo 5 caracteres y máximo 240");
			return false;
		}
		
		if (this.getProducto().getPrecio()<=0) {
			super.msgAlerta("El precio debe ser mayor que cero");
			return false;
		}
		if (this.getProducto().getStock()<=0) {
			super.msgAlerta("El stock debe ser mayor que cero");
			return false;
		}
		
		return true;
	}

}

