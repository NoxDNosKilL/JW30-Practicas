package pe.edu.galaxy.training.java.web.persistencia.interfaces;


import java.util.List;

import pe.edu.galaxy.training.java.web.entity.Usuario;
import pe.edu.galaxy.training.java.web.persistencia.exception.DAOException;



public interface UsuarioDAO {

	List<Usuario> listar(String nombre) throws DAOException;
	
	Usuario buscarXCodigo(Long codigo) throws DAOException;
	
	boolean agregar(Usuario usuario) throws DAOException;
	
	boolean modificar(Usuario usuario) throws DAOException;
	
	boolean eliminar(Usuario usuario) throws DAOException;
	
	//login
	
	Usuario validarAcceso(Usuario usuario) throws DAOException;
	
}