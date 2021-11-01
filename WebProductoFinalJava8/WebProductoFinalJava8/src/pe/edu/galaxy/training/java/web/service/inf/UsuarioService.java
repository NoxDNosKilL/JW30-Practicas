package pe.edu.galaxy.training.java.web.service.inf;

import pe.edu.galaxy.training.java.web.bean.UsuarioBean;
import pe.edu.galaxy.training.java.web.service.exception.ServicioException;

public interface UsuarioService extends GenericoService<UsuarioBean>{
	
	public UsuarioBean validarAcceso(UsuarioBean usuarioBean)throws ServicioException;

}
