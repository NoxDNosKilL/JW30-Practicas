package pe.edu.galaxy.training.java.web.persistencia.inf;

import java.util.List;

import pe.edu.galaxy.training.java.web.bean.ProductoBean;
import pe.edu.galaxy.training.java.web.bean.ProductoPrecioBean;
import pe.edu.galaxy.training.java.web.persistencia.exception.PersistenciaException;

public interface ProductoDAO extends GenericoDAO<ProductoBean>{

	public boolean actualizarStock(ProductoBean productoBean) 
			throws PersistenciaException;
	
	public List<ProductoPrecioBean> listarPrecios(ProductoBean productoBean)
			throws PersistenciaException;
	
	public List<ProductoPrecioBean> listarPreciosxCodigo(ProductoBean productoBean)
			throws PersistenciaException;
}
