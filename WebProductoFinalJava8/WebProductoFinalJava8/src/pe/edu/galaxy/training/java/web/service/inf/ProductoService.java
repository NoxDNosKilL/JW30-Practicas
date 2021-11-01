package pe.edu.galaxy.training.java.web.service.inf;

import java.util.List;

import pe.edu.galaxy.training.java.web.bean.ProductoBean;
import pe.edu.galaxy.training.java.web.bean.ProductoPrecioBean;
import pe.edu.galaxy.training.java.web.service.exception.ServicioException;

public interface ProductoService extends GenericoService<ProductoBean>{

	public List<ProductoPrecioBean> listarPrecios (ProductoBean productoBean)	throws ServicioException;
	
	public List<ProductoPrecioBean> listarPreciosxCodigo (ProductoBean productoBean) throws ServicioException;
}
