package pe.edu.galaxy.training.java.web.persistencia.interfaces;



import pe.edu.galaxy.training.java.web.entity.Usuario;
import pe.edu.galaxy.training.java.web.persistencia.exception.DAOException;

public interface UsuarioDAO {

	Usuario validar(String usuario, String clave) throws DAOException;

	
}