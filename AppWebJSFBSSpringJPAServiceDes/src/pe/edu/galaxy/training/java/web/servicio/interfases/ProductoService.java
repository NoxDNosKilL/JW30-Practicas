package pe.edu.galaxy.training.java.web.servicio.interfases;

import java.util.List;

import pe.edu.galaxy.training.java.web.entity.Producto;
import pe.edu.galaxy.training.java.web.servicio.excepcion.ServiceException;

public interface ProductoService {
	List<Producto> listar() throws ServiceException;

	List<Producto> listar(String nombre) throws ServiceException;
	
	Producto buscarXCodigo(Long codigo) throws ServiceException;


	boolean agregar(Producto producto) throws ServiceException;

	boolean modificar(Producto producto) throws ServiceException;

	boolean eliminar(Producto producto) throws ServiceException;
	
	
}
