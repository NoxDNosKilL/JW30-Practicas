package pe.edu.galaxy.training.java.jd0.persistencia.interfaces;


import java.util.List;
import pe.edu.galaxy.training.java.jd0.bean.Producto;
import pe.edu.galaxy.training.java.jd0.persistencia.exception.DAOException;

public interface ProductoDAO {

	List<Producto> listar() throws DAOException;
	
	List<Producto> listar(String nombre) throws DAOException;
	
	boolean agregar(Producto producto) throws DAOException;
	
	boolean modificar(Producto producto) throws DAOException;
	
	boolean eliminar(Producto producto) throws DAOException;
	
	
}
