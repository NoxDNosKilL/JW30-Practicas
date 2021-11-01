package pe.edu.galaxy.training.java.web.persistencia.inf;

import pe.edu.galaxy.training.java.web.bean.UsuarioBean;
import pe.edu.galaxy.training.java.web.persistencia.exception.PersistenciaException;

public interface UsuarioDAO extends GenericoDAO<UsuarioBean>{

	public UsuarioBean validarAcceso(UsuarioBean usuarioBean)throws PersistenciaException;
	
}
