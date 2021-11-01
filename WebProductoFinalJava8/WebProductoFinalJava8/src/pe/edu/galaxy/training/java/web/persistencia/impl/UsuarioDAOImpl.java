package pe.edu.galaxy.training.java.web.persistencia.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.galaxy.training.java.web.bean.UsuarioBean;
import pe.edu.galaxy.training.java.web.entity.UsuarioEntity;
import pe.edu.galaxy.training.java.web.persistencia.exception.PersistenciaException;
import pe.edu.galaxy.training.java.web.persistencia.inf.UsuarioDAO;

@Transactional
@Repository("usuarioDAO")
public class UsuarioDAOImpl implements UsuarioDAO{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public UsuarioBean validarAcceso(UsuarioBean usuarioBean) throws PersistenciaException {
		UsuarioBean oUsuarioBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("usuario.validarAcceso");

			spq.setParameter("P_USUARIO", usuarioBean.getUsuario());
			spq.setParameter("P_CLAVE", usuarioBean.getClave());

			if (spq.execute()) {
				@SuppressWarnings("unchecked")
				List<UsuarioEntity> lst = (List<UsuarioEntity>) spq.getOutputParameterValue("P_CURSOR");
				if (lst!=null) {
					if (lst.size()>0) {
						UsuarioEntity usuarioEntity= (UsuarioEntity) lst.get(0);
						oUsuarioBean= new UsuarioBean();
						BeanUtils.copyProperties(oUsuarioBean, usuarioEntity);
					}
				}
			}
			em.close();

		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return oUsuarioBean;
	}


	@Override
	public List<UsuarioBean> listar(UsuarioBean usuarioBean) throws PersistenciaException {
		List<UsuarioBean> lstUsuarioBean = new ArrayList<UsuarioBean>();
		
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("usuario.listar");

			spq.setParameter("P_USUARIO", usuarioBean.getUsuario());
			spq.setParameter("P_NOMBRE", usuarioBean.getNombre());

			if (spq.execute()) {
				@SuppressWarnings("unchecked")
				List<UsuarioEntity> lst = (List<UsuarioEntity>) spq.getOutputParameterValue("P_CURSOR");
				if (lst!=null) {
					for (UsuarioEntity usuarioEntity : lst) {
						
						UsuarioBean oUsuarioBean= new UsuarioBean();
						BeanUtils.copyProperties(oUsuarioBean, usuarioEntity);
						lstUsuarioBean.add(oUsuarioBean);
					}
				}
			}
			em.close();

		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return lstUsuarioBean;
	}


	@Override
	public UsuarioBean buscarXCodigo(UsuarioBean t) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean insertar(UsuarioBean t) throws PersistenciaException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean actualizar(UsuarioBean t) throws PersistenciaException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean eliminar(UsuarioBean t) throws PersistenciaException {
		// TODO Auto-generated method stub
		return false;
	}


}
