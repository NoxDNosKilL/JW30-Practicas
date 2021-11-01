package pe.edu.galaxy.training.java.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.web.bean.ProveedorBean;
import pe.edu.galaxy.training.java.web.persistencia.exception.PersistenciaException;
import pe.edu.galaxy.training.java.web.persistencia.inf.ProveedorDAO;
import pe.edu.galaxy.training.java.web.service.exception.ServicioException;
import pe.edu.galaxy.training.java.web.service.inf.ProveedorService;

@Service("proveedorService")
public class ProveedorServiceImpl implements  ProveedorService{
	
	@Autowired //IoC- DI
	private ProveedorDAO proveedorDAO; 

	@Override
	public List<ProveedorBean> listar(ProveedorBean proveedorBean) throws ServicioException {
		try {
			return this.getProveedorDAO().listar(proveedorBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public ProveedorBean buscarXCodigo(ProveedorBean proveedorBean) throws ServicioException {
		try {
			return this.getProveedorDAO().buscarXCodigo(proveedorBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public boolean insertar(ProveedorBean proveedorBean) throws ServicioException {
		try {
			return this.getProveedorDAO().insertar(proveedorBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public boolean actualizar(ProveedorBean proveedorBean) throws ServicioException {
		try {
			return this.getProveedorDAO().actualizar(proveedorBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public boolean eliminar(ProveedorBean proveedorBean) throws ServicioException {
		try {
			return this.getProveedorDAO().eliminar(proveedorBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	public ProveedorDAO getProveedorDAO() {
		return proveedorDAO;
	}

	public void setProveedorDAO(ProveedorDAO proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
	}

}
