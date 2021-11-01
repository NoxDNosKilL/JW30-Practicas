package pe.edu.galaxy.training.java.web.persistencia.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.galaxy.training.java.web.bean.ProveedorBean;
import pe.edu.galaxy.training.java.web.entity.ProveedorEntity;
import pe.edu.galaxy.training.java.web.persistencia.exception.PersistenciaException;
import pe.edu.galaxy.training.java.web.persistencia.inf.ProveedorDAO;

@Transactional
@Repository("proveedorDAO")
public class ProveedorDAOImpl implements ProveedorDAO{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<ProveedorBean> listar(ProveedorBean proveedorBean) throws PersistenciaException {
		
		List<ProveedorBean> lstProveedorBean = new ArrayList<ProveedorBean>();
		
		try {
			
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("proveedor.listar");

			spq.setParameter("P_RAZON_SOCIAL", proveedorBean.getRazonSocial());
			spq.setParameter("P_RUC", proveedorBean.getRuc());

			if (spq.execute()) {
				@SuppressWarnings("unchecked")
				List<ProveedorEntity> lst = (List<ProveedorEntity>) spq.getOutputParameterValue("P_CURSOR");
				if (lst!=null) {
					for (ProveedorEntity proveedorEntity : lst) {
						ProveedorBean oProveedorBean= new ProveedorBean();
						BeanUtils.copyProperties(oProveedorBean, proveedorEntity);
						lstProveedorBean.add(oProveedorBean);
					}
				}
			}
			em.close();

		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return lstProveedorBean;
	}

	@Override
	public ProveedorBean buscarXCodigo(ProveedorBean t) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertar(ProveedorBean proveedorBean) throws PersistenciaException {
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("proveedor.insertar");
			
			// IN
			spq.setParameter("P_RAZON_SOCIAL", proveedorBean.getRazonSocial());
			spq.setParameter("P_RUC", proveedorBean.getRuc());
			spq.setParameter("P_DIRECCION", proveedorBean.getDireccion());
			spq.setParameter("P_CORREO", proveedorBean.getCorreo());
			spq.setParameter("P_TELEFONO", proveedorBean.getTelefono());
				
			// Auditoria
			spq.setParameter("P_AUD_IDUSUARIO", proveedorBean.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", proveedorBean.getAudSession());
			spq.setParameter("P_AUD_IP", proveedorBean.getAudIp());

			
			spq.execute();
			
			Object id = spq.getOutputParameterValue(1); // P_ID_PROVEEDOR
			if (id != null) {
				proveedorBean.setCodigo(Long.valueOf(id.toString()));
				sw= true;
			}
			em.close();

			
		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return sw;
	}

	@Override
	public boolean actualizar(ProveedorBean proveedorBean) throws PersistenciaException {
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("proveedor.actualizar");
			
			// IN
			spq.setParameter("P_ID_PROVEEDOR", proveedorBean.getCodigo());
			
			spq.setParameter("P_RAZON_SOCIAL", proveedorBean.getRazonSocial());
			spq.setParameter("P_RUC", proveedorBean.getRuc());
			spq.setParameter("P_DIRECCION", proveedorBean.getDireccion());
			spq.setParameter("P_CORREO", proveedorBean.getCorreo());
			spq.setParameter("P_TELEFONO", proveedorBean.getTelefono());
				
			// Auditoria
			spq.setParameter("P_AUD_IDUSUARIO", proveedorBean.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", proveedorBean.getAudSession());
			spq.setParameter("P_AUD_IP", proveedorBean.getAudIp());

			spq.execute();
			em.close();
			
			return true;
			
		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
	}

	@Override
	public boolean eliminar(ProveedorBean proveedorBean) throws PersistenciaException {
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("proveedor.eliminar");
			
			// IN
			spq.setParameter("P_ID_PROVEEDOR", proveedorBean.getCodigo());
				
			// Auditoria
			spq.setParameter("P_AUD_IDUSUARIO", proveedorBean.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", proveedorBean.getAudSession());
			spq.setParameter("P_AUD_IP", proveedorBean.getAudIp());

			spq.execute();

			em.close();

			sw= true;
			
		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return sw;
	}

}
