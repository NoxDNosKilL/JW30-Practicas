package pe.edu.galaxy.persistencia.interfaces;

import java.util.List;

import pe.edu.galaxy.bean.Postulante;
import pe.edu.galaxy.persistencia.exception.DAOException;


public interface PostulanteDAO {

	List<Postulante> listar() throws DAOException;
	
	List<Postulante> listar(String nombre) throws DAOException;
		
	boolean agregar(Postulante producto) throws DAOException;
	
	boolean modificar(Postulante producto) throws DAOException;
	
	boolean eliminar(Postulante producto) throws DAOException;
}
