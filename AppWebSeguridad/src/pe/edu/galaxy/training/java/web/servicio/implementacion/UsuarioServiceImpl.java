package pe.edu.galaxy.training.java.web.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.web.entity.Usuario;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.UsuarioDAO;
import pe.edu.galaxy.training.java.web.servicio.excepcion.ServiceException;
import pe.edu.galaxy.training.java.web.servicio.interfases.UsuarioService;

@Service
@Data
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioDAO UsuarioDAO;
	

	@Override
	public Usuario validarAcceso(Usuario usuario) throws ServiceException {
		try {
			
			return this.getUsuarioDAO().validarAcceso(usuario);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	

}
