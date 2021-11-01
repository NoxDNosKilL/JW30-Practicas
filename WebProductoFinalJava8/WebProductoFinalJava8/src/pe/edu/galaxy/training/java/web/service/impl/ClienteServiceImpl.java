package pe.edu.galaxy.training.java.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.web.bean.ClienteBean;
import pe.edu.galaxy.training.java.web.persistencia.exception.PersistenciaException;
import pe.edu.galaxy.training.java.web.persistencia.inf.ClienteDAO;
import pe.edu.galaxy.training.java.web.service.exception.ServicioException;
import pe.edu.galaxy.training.java.web.service.inf.ClienteService;

@Service("clienteService")
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteDAO clienteDAO;

	@Override
	public List<ClienteBean> listar(ClienteBean clienteBean) throws ServicioException {
		try {
			return this.getClienteDAO().listar(clienteBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public ClienteBean buscarXCodigo(ClienteBean clienteBean) throws ServicioException {
		try {
			return this.getClienteDAO().buscarXCodigo(clienteBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public boolean insertar(ClienteBean clienteBean) throws ServicioException {
		try {
			return this.getClienteDAO().insertar(clienteBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public boolean actualizar(ClienteBean clienteBean) throws ServicioException {
		try {
			return this.getClienteDAO().actualizar(clienteBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public boolean eliminar(ClienteBean clienteBean) throws ServicioException {
		try {
			return this.getClienteDAO().eliminar(clienteBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

}
