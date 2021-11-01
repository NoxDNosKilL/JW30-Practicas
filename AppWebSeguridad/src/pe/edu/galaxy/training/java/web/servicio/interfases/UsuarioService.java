package pe.edu.galaxy.training.java.web.servicio.interfases;


import java.util.List;
import pe.edu.galaxy.training.java.web.entity.Usuario;
import pe.edu.galaxy.training.java.web.servicio.excepcion.ServiceException;

public interface UsuarioService {

	
	//login
	
	Usuario validarAcceso(Usuario usuario) throws ServiceException;
	
	
}
