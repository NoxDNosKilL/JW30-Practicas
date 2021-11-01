package pe.edu.galaxy.training.java.web.persistencia.interfaces;


import java.util.List;
import pe.edu.galaxy.training.java.web.entity.Cliente;
import pe.edu.galaxy.training.java.web.persistencia.exception.DAOException;

public interface ClienteDAO {

	List<Cliente> listar(String razonSocial) throws DAOException;
	
	Cliente buscarXCodigo(Long codigo) throws DAOException;
	
	boolean agregar(Cliente cliente) throws DAOException;
	
	boolean modificar(Cliente cliente) throws DAOException;
	
	boolean eliminar(Cliente cliente) throws DAOException;
	
	
	
	
}
