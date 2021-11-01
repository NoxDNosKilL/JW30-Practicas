package pe.edu.galaxy.training.java.web.servicio.interfases;


import java.util.List;
import pe.edu.galaxy.training.java.web.entity.Cliente;
import pe.edu.galaxy.training.java.web.servicio.excepcion.ServiceException;

public interface ClienteService {

	List<Cliente> listar(String razonSocial) throws ServiceException;
	
	Cliente buscarXCodigo(Long codigo) throws ServiceException;
	
	boolean agregar(Cliente cliente) throws ServiceException;
	
	boolean modificar(Cliente cliente) throws ServiceException;
	
	boolean eliminar(Cliente cliente) throws ServiceException;
	
	
	
	
}
