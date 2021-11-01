package pe.edu.galaxy.training.java.web.persistencia.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.galaxy.training.java.web.bean.ClienteBean;
import pe.edu.galaxy.training.java.web.bean.UsuarioBean;
import pe.edu.galaxy.training.java.web.entity.ClienteEntity;
import pe.edu.galaxy.training.java.web.entity.UsuarioEntity;
import pe.edu.galaxy.training.java.web.persistencia.exception.PersistenciaException;
import pe.edu.galaxy.training.java.web.persistencia.inf.ClienteDAO;

@Transactional
@Repository("clienteDAO")
public class ClienteDAOImpl implements ClienteDAO{
	
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<ClienteBean> listar(ClienteBean clienteBean) throws PersistenciaException {
		List<ClienteBean> lstClienteBean = new ArrayList<>();
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("cliente.listar");
			spq.setParameter("P_RAZON_SOCIAL", clienteBean.getRazonSocial());
			if (spq.execute()) {
				@SuppressWarnings("unchecked")
				List<ClienteEntity> lst = (List<ClienteEntity>) spq.getOutputParameterValue("P_CURSOR");
				if (lst!=null) {
					for (ClienteEntity clienteEntity : lst) {
						ClienteBean oClienteBean= new ClienteBean();
						BeanUtils.copyProperties(oClienteBean, clienteEntity);
						lstClienteBean.add(oClienteBean);
					}
				}
			}
			em.close();
		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return lstClienteBean;
	}

	@Override
	public ClienteBean buscarXCodigo(ClienteBean t) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertar(ClienteBean t) throws PersistenciaException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizar(ClienteBean t) throws PersistenciaException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(ClienteBean t) throws PersistenciaException {
		// TODO Auto-generated method stub
		return false;
	}

}
