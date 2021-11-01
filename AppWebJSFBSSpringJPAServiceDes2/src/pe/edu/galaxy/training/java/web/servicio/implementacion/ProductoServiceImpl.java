package pe.edu.galaxy.training.java.web.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.web.entity.Producto;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.ProductoDAO;
import pe.edu.galaxy.training.java.web.servicio.excepcion.ServiceException;
import pe.edu.galaxy.training.java.web.servicio.interfases.ProductoService;


@Slf4j
@Data
@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoDAO productoDAO;
	

	@Override
	public List<Producto> listar() throws ServiceException {
		try {
			return this.getProductoDAO().listar();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Producto> listar(String nombre) throws ServiceException {
		try {
			return this.getProductoDAO().listar(nombre);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean agregar(Producto producto) throws ServiceException {
		try {
			return this.getProductoDAO().agregar(producto);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean modificar(Producto producto) throws ServiceException {
		try {
			return this.getProductoDAO().modificar(producto);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean eliminar(Producto producto) throws ServiceException {
		try {
			return this.getProductoDAO().eliminar(producto);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public Producto buscarXCodigo(Long codigo) throws ServiceException {
		try {
			return this.getProductoDAO().buscarXCodigo(codigo);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}


}
