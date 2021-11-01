package pe.edu.galaxy.training.java.web.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.web.entity.Cliente;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.ClienteDAO;
import pe.edu.galaxy.training.java.web.servicio.excepcion.ServiceException;
import pe.edu.galaxy.training.java.web.servicio.interfases.ClienteService;


@Slf4j
@Data
@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteDAO clienteDAO;
	


	@Override
	public List<Cliente> listar(String razonSocial) throws ServiceException {
		try {
			return this.getClienteDAO().listar(razonSocial);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean agregar(Cliente cliente) throws ServiceException {
		try {
			return this.getClienteDAO().agregar(cliente);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean modificar(Cliente cliente) throws ServiceException {
		try {
			return this.getClienteDAO().modificar(cliente);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean eliminar(Cliente cliente) throws ServiceException {
		try {
			return this.getClienteDAO().eliminar(cliente);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public Cliente buscarXCodigo(Long codigo) throws ServiceException {
		try {
			return this.getClienteDAO().buscarXCodigo(codigo);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}


}
