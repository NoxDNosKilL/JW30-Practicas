package pe.edu.galaxy.training.java.web.persistencia.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.galaxy.training.java.web.bean.ProductoBean;
import pe.edu.galaxy.training.java.web.bean.ProductoPrecioBean;
import pe.edu.galaxy.training.java.web.entity.ProductoEntity;
import pe.edu.galaxy.training.java.web.entity.ProductoPrecioEntity;
import pe.edu.galaxy.training.java.web.persistencia.exception.PersistenciaException;
import pe.edu.galaxy.training.java.web.persistencia.inf.ProductoDAO;

@Transactional
@Repository("productoDAO")
public class ProductoDAOImpl implements ProductoDAO{
	
	@PersistenceContext
	private EntityManager em;

	public ProductoDAOImpl() {
		
	}

	public List<ProductoBean> listar(ProductoBean productoBean) throws PersistenciaException {
		
		List<ProductoBean> lstProductoBeans = new ArrayList<ProductoBean>();
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.listar");
			// IN
			spq.setParameter("P_NOMBRE", productoBean.getNombre());
			spq.setParameter("P_ID_USUARIO", productoBean.getAudIdUsuario());

			if (spq.execute()) {
				@SuppressWarnings("unchecked")
				List<ProductoEntity> lstProductoEntity = (List<ProductoEntity>) spq.getOutputParameterValue("P_CURSOR");
				for (ProductoEntity productoEntity : lstProductoEntity) {
					ProductoBean oProductoBean = new ProductoBean();
					BeanUtils.copyProperties(oProductoBean, productoEntity);
					lstProductoBeans.add(oProductoBean);
				}
			}
			em.close();

			return lstProductoBeans;
		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
	}

	public ProductoBean buscarXCodigo(ProductoBean productoBean) throws PersistenciaException {
		
		ProductoBean oProductoBean =null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.buscarXCodigo");
			// IN
			spq.setParameter("P_CODIGO", productoBean.getCodigo());

			if (spq.execute()) {
				@SuppressWarnings("unchecked")
				List<ProductoEntity> lstProductoEntity = (List<ProductoEntity>) spq.getOutputParameterValue("P_CURSOR");
				if (lstProductoEntity!=null && lstProductoEntity.size()>0) {
					oProductoBean= new ProductoBean();
					BeanUtils.copyProperties(oProductoBean, lstProductoEntity.get(0));
				}
			}
			em.close();

		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return oProductoBean;
	}

	public boolean insertar(ProductoBean productoBean) throws PersistenciaException {
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.insertar");
			
			// IN
			spq.setParameter("P_NOMBRE", productoBean.getNombre());
			spq.setParameter("P_PRECIO", productoBean.getPrecio());
			spq.setParameter("P_STOCK", productoBean.getStock());
				
			// Auditoria
			spq.setParameter("P_AUD_IDUSUARIO", productoBean.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", productoBean.getAudSession());
			spq.setParameter("P_AUD_IP", productoBean.getAudIp());
			
			spq.execute();
			
			Object id = spq.getOutputParameterValue(1); // P_CODIGO
			if (id != null) {
				productoBean.setCodigo(Long.valueOf(id.toString()));
				sw= true;
			}
			em.close();

			
		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return sw;
	}

	public boolean actualizar(ProductoBean productoBean) throws PersistenciaException {
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.actualizar");
			
			// IN
			spq.setParameter("P_CODIGO", productoBean.getCodigo());
			spq.setParameter("P_PRECIO", productoBean.getPrecio());
			spq.setParameter("P_NOMBRE", productoBean.getNombre());
			spq.setParameter("P_PRECIO", productoBean.getPrecio());
			spq.setParameter("P_STOCK", productoBean.getStock());
				
			// Auditoria
			spq.setParameter("P_AUD_IDUSUARIO", productoBean.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", productoBean.getAudSession());
			spq.setParameter("P_AUD_IP", productoBean.getAudIp());
			
			spq.execute();

			em.close();
			sw=true;
		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return sw;
	}

	public boolean eliminar(ProductoBean productoBean) throws PersistenciaException {
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.eliminar");
			
			// IN
			spq.setParameter("P_CODIGO", productoBean.getCodigo());
				
			// Auditoria
			spq.setParameter("P_AUD_IDUSUARIO", productoBean.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", productoBean.getAudSession());
			spq.setParameter("P_AUD_IP", productoBean.getAudIp());
			
			spq.execute();

			em.close();
			sw=true;
		} catch (Exception e) {
			throw new PersistenciaException(e);
		}
		return sw;
	}

	public boolean actualizarStock(ProductoBean productoBean) throws PersistenciaException {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductoPrecioBean> listarPrecios(ProductoBean productoBean) throws PersistenciaException {
		
		
		List<ProductoPrecioBean> lstProductoPrecioBeans = new ArrayList<ProductoPrecioBean>();
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.listarPrecios");
			// IN
			spq.setParameter("P_NOMBRE", productoBean.getNombre());

			if (spq.execute()) {
				
				List<ProductoPrecioEntity> lstProductoPrecioEntity 
				= (List<ProductoPrecioEntity>) spq.getOutputParameterValue("P_CURSOR");
				
				for (ProductoPrecioEntity productoPrecioEntity : lstProductoPrecioEntity) {
					ProductoPrecioBean oProductoPrecioBean = new ProductoPrecioBean();
					BeanUtils.copyProperties(oProductoPrecioBean, productoPrecioEntity);
					lstProductoPrecioBeans.add(oProductoPrecioBean);
				}
			}
			em.close();

			return lstProductoPrecioBeans;
		} catch (Exception e) {
			throw new PersistenciaException(e);
		}

	}

	@Override
	public List<ProductoPrecioBean> listarPreciosxCodigo(ProductoBean productoBean) throws PersistenciaException {
		
		
		List<ProductoPrecioBean> lstProductoPrecioBeans = new ArrayList<ProductoPrecioBean>();
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.listarPreciosxCodigo");
			// IN
			spq.setParameter("P_CODIGO", productoBean.getCodigo());

			if (spq.execute()) {
				
				List<ProductoPrecioEntity> lstProductoPrecioEntity 
				= (List<ProductoPrecioEntity>) spq.getOutputParameterValue("P_CURSOR");
				
				for (ProductoPrecioEntity productoPrecioEntity : lstProductoPrecioEntity) {
					ProductoPrecioBean oProductoPrecioBean = new ProductoPrecioBean();
					BeanUtils.copyProperties(oProductoPrecioBean, productoPrecioEntity);
					lstProductoPrecioBeans.add(oProductoPrecioBean);
				}
			}
			em.close();

			return lstProductoPrecioBeans;
		} catch (Exception e) {
			throw new PersistenciaException(e);
		}

	}
		

}
