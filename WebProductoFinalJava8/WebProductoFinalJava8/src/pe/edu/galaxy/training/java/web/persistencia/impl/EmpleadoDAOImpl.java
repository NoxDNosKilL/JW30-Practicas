package pe.edu.galaxy.training.java.web.persistencia.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.galaxy.training.java.web.bean.EmpleadoBean;
import pe.edu.galaxy.training.java.web.entity.EmpleadoEntity;
import pe.edu.galaxy.training.java.web.persistencia.exception.PersistenciaException;
import pe.edu.galaxy.training.java.web.persistencia.inf.EmpleadoDAO;

@Transactional
@Repository("empleadoDAO")
public class EmpleadoDAOImpl implements EmpleadoDAO{
	
	@PersistenceContext
	EntityManager em;


	@Override
	public List<EmpleadoBean> listar(EmpleadoBean empleadoBean) throws PersistenciaException {
		List<EmpleadoBean> lstEmpleadoBean = new ArrayList<EmpleadoBean>();
		
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("empleado.listar");

			spq.setParameter("P_APELLIDO_PATERNO", empleadoBean.getApellidoPaterno());
			spq.setParameter("P_APELLIDO_MATERNO", empleadoBean.getApellidoMaterno());
			spq.setParameter("P_NOMBRE", empleadoBean.getNombre());

			if (spq.execute()) {
				@SuppressWarnings("unchecked")
				List<EmpleadoEntity> lst = (List<EmpleadoEntity>) spq.getOutputParameterValue("P_CURSOR");
				if (lst!=null) {
					for (EmpleadoEntity empleadoEntity : lst) {
						
						EmpleadoBean oEmpleadoBean= new EmpleadoBean();
						BeanUtils.copyProperties(oEmpleadoBean, empleadoEntity);
						lstEmpleadoBean.add(oEmpleadoBean);
					}
				}
			}
			em.close();

		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return lstEmpleadoBean;
	}


	@Override
	public EmpleadoBean buscarXCodigo(EmpleadoBean empleadoBean) throws PersistenciaException {
		EmpleadoBean oEmpleadoBean = null;
		
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("empleado.buscarXCodigo");

			spq.setParameter("P_ID_EMPLEADO", empleadoBean.getCodigo());

			if (spq.execute()) {
				@SuppressWarnings("unchecked")
				List<EmpleadoEntity> lst = (List<EmpleadoEntity>) spq.getOutputParameterValue("P_CURSOR");
				if (lst!=null) {
					if (lst.size()>0) {
						EmpleadoEntity empleadoEntity= lst.get(0);
						oEmpleadoBean= new EmpleadoBean();
						BeanUtils.copyProperties(oEmpleadoBean, empleadoEntity);
					}
					
				}
			}
			em.close();

		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return oEmpleadoBean;
	}


	@Override
	public boolean insertar(EmpleadoBean empleadoBean) throws PersistenciaException {
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("empleado.insertar");
			
			// IN
			spq.setParameter("P_APELLIDO_PATERNO", empleadoBean.getApellidoPaterno());
			spq.setParameter("P_APELLIDO_MATERNO", empleadoBean.getApellidoMaterno());
			spq.setParameter("P_NOMBRE", empleadoBean.getNombre());
			spq.setParameter("P_NRO_DOCUMENTO", empleadoBean.getNroDocumento());
			spq.setParameter("P_DIRECCION", empleadoBean.getDireccion());
			spq.setParameter("P_CORREO", empleadoBean.getCorreo());
			spq.setParameter("P_TELEFONO", empleadoBean.getTelefono());

			// Auditoria
			spq.setParameter("P_AUD_IDUSUARIO", empleadoBean.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", empleadoBean.getAudSession());
			spq.setParameter("P_AUD_IP", empleadoBean.getAudIp());
			
			spq.execute();
			
			Object id = spq.getOutputParameterValue(1); // P_ID_EMPLEADO
			if (id != null) {
				empleadoBean.setCodigo(Long.valueOf(id.toString()));
				sw= true;
			}
			em.close();

			
		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return sw;
	}


	@Override
	public boolean actualizar(EmpleadoBean empleadoBean) throws PersistenciaException {
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("empleado.actualizar");
			
			// IN
			spq.setParameter("P_ID_EMPLEADO", empleadoBean.getCodigo());
			spq.setParameter("P_APELLIDO_PATERNO", empleadoBean.getApellidoPaterno());
			spq.setParameter("P_APELLIDO_MATERNO", empleadoBean.getApellidoMaterno());
			spq.setParameter("P_NOMBRE", empleadoBean.getNombre());
			spq.setParameter("P_NRO_DOCUMENTO", empleadoBean.getNroDocumento());
			spq.setParameter("P_DIRECCION", empleadoBean.getDireccion());
			spq.setParameter("P_CORREO", empleadoBean.getCorreo());
			spq.setParameter("P_TELEFONO", empleadoBean.getTelefono());

			// Auditoria
			spq.setParameter("P_AUD_IDUSUARIO", empleadoBean.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", empleadoBean.getAudSession());
			spq.setParameter("P_AUD_IP", empleadoBean.getAudIp());
			
			spq.execute();

			em.close();

			sw=true;
		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return sw;
	}


	@Override
	public boolean eliminar(EmpleadoBean empleadoBean) throws PersistenciaException {
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("empleado.eliminar");
			
			// IN
			spq.setParameter("P_ID_EMPLEADO", empleadoBean.getCodigo());

			// Auditoria
			spq.setParameter("P_AUD_IDUSUARIO", empleadoBean.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", empleadoBean.getAudSession());
			spq.setParameter("P_AUD_IP", empleadoBean.getAudIp());
			
			spq.execute();

			em.close();

			sw=true;
		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return sw;
	}


}
