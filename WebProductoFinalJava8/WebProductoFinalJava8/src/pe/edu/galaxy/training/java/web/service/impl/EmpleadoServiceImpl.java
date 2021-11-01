package pe.edu.galaxy.training.java.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.web.bean.EmpleadoBean;
import pe.edu.galaxy.training.java.web.persistencia.exception.PersistenciaException;
import pe.edu.galaxy.training.java.web.persistencia.inf.EmpleadoDAO;
import pe.edu.galaxy.training.java.web.service.exception.ServicioException;
import pe.edu.galaxy.training.java.web.service.inf.EmpleadoService;

@Service("empleadoService")
public class EmpleadoServiceImpl implements EmpleadoService{
	
	@Autowired 
	private EmpleadoDAO empleadoDAO;


	public List<EmpleadoBean> listar(EmpleadoBean empleadoBean) throws ServicioException {
		try {
			return this.getEmpleadoDAO().listar(empleadoBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}
	public EmpleadoBean buscarXCodigo(EmpleadoBean empleadoBean) throws ServicioException {
		try {
			return this.getEmpleadoDAO().buscarXCodigo(empleadoBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	public boolean insertar(EmpleadoBean empleadoBean) throws ServicioException {
		try {
			return this.getEmpleadoDAO().insertar(empleadoBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	public boolean actualizar(EmpleadoBean empleadoBean) throws ServicioException {
		try {
			return this.getEmpleadoDAO().actualizar(empleadoBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}

	}

	public boolean eliminar(EmpleadoBean empleadoBean) throws ServicioException {
		try {
			return this.getEmpleadoDAO().eliminar(empleadoBean);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	public EmpleadoDAO getEmpleadoDAO() {
		return empleadoDAO;
	}

	public void setEmpleadoDAO(EmpleadoDAO empleadoDAO) {
		this.empleadoDAO = empleadoDAO;
	}

}
